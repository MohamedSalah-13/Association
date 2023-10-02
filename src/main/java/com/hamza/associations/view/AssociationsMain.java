package com.hamza.associations.view;

import com.hamza.associations.entity.Association;
import com.hamza.associations.entity.Floor;
import com.hamza.associations.service.AssociationService;
import com.hamza.associations.service.FloorService;
import com.hamza.associations.view.details.DetailsByYear;
import com.salah.utils.fx.alert.AllAlerts;
import com.salah.utils.fx.table.Column;
import com.salah.utils.fx.table.Table_Setting;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static com.salah.utils.fx.DateConverter.dateConverter;
import static com.salah.utils.fx.Utils.*;

@Component
public class AssociationsMain {
    private final AssociationService associationService;
    private final FloorService floorService;

    @FXML
    private TableView<Association> tableView;
    @FXML
    private TextField field_name, field_amount, field_endDate, number_floor;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label label1, label2, label4, label5, label6;
    @FXML
    private Button save, refresh, btn_add, btn_update, btn_delete, btn_clear;
    @FXML
    private Spinner<Integer> spinnerCount;
    private Long association_id = 0L;
    private List<Floor> floorList = new ArrayList<>();

    @Autowired
    public AssociationsMain(AssociationService associationService, FloorService floorService) {
        this.associationService = associationService;
        this.floorService = floorService;
    }

    @FXML
    public void initialize() {
        getTable();
        otherSetting();
        getEndDate(0);
        refreshData();
    }

    private void otherSetting() {
        String name_text = "Name";
        String text_amount = "Amount";
        String text_start = "Start Date";
        String TEXT_END = "End Date";
        String TEXT_Count = "Count Person";

        label1.setText(name_text);
        label2.setText(text_amount);
        label4.setText(text_start);
        label5.setText(TEXT_END);
        label6.setText(TEXT_Count);

        field_name.setPromptText(name_text);
        field_amount.setPromptText(text_amount);

        // =================== data setting =======================//
        datePicker.setValue(LocalDate.now());
        dateConverter(datePicker);

        // =================== spinner =======================//
        spinnerCount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));
        spinnerCount.getValueFactory().setValue(1);

        whenEnterPressed(field_name, field_amount, spinnerCount);
        setTextFormatter(field_amount);

        // =================== action =======================//

        datePicker.setOnAction(actionEvent -> getEndDate(spinnerCount.getValue()));
        spinnerCount.valueProperty().addListener((observableValue, integer, t1) -> getEndDate(t1));

        btn_clear.setOnAction(actionEvent -> reset());
        save.setOnAction(actionEvent -> saveData());
        refresh.setOnAction(actionEvent -> refreshData());
        btn_add.setOnAction(actionEvent -> addFloorNumbers());
        btn_delete.setOnAction(actionEvent -> deleteAssociation());
        btn_update.setOnAction(actionEvent -> getData());

        tableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                try {
                    new OpenDetails(floorService, tableView.getSelectionModel().getSelectedItem()).start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Stage stage = new Stage();
                stage.setScene(new Scene(new DetailsByYear(associationService, floorService), 700, 400));
                stage.setResizable(true);
                stage.show();
            }
        });

        buttonShowSetting();
    }

    private void addFloorNumbers() {
        Floor_NumberWithFloor floorNumberWithFloor = new Floor_NumberWithFloor(spinnerCount.getValue(),
                Double.parseDouble(field_amount.getText()), floorList);
        Optional<List<Floor>> floors = floorNumberWithFloor.showAndWait();
        floors.ifPresent(floors1 -> {
            floorList = floors1;
            setFloorText(floorList);
        });
    }

    private void deleteAssociation() {
        if (!tableView.getSelectionModel().isEmpty()) {
            Long id = tableView.getSelectionModel().getSelectedItem().getId();
            if (AllAlerts.confirmDelete()) {
                associationService.deleteAssociation(id);
                refreshData();
            }
        }
    }

    private void getData() {
        if (!tableView.getSelectionModel().isEmpty()) {
            Long id = tableView.getSelectionModel().getSelectedItem().getId();
            Optional<Association> associationById = associationService.findAssociationById(id);
            if (associationById.isPresent()) {
                String name = associationById.get().getName();
                double amount = associationById.get().getAmount();
                Date date = associationById.get().getStart_date();
                int countMonth = associationById.get().getCount_month();

                field_name.setText(name);
                field_amount.setText(String.valueOf(amount));
                datePicker.setValue(LocalDate.parse(date.toString()));
                spinnerCount.getValueFactory().setValue(countMonth);
                getEndDate(countMonth);
                floorList = floorService.findAllByAssociation_Id(associationById.get().getId());
                setFloorText(floorList);
                association_id = id;
            }
        }
    }

    private void saveData() {
        String name = field_name.getText();
        double amount = Double.parseDouble(field_amount.getText());
        int countMonth = spinnerCount.getValue();
        String notes = "";

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate value = datePicker.getValue();
        Date date = Date.from(value.atStartOfDay(defaultZoneId).toInstant());

        Association association = new Association(name, amount, date, countMonth, notes);
        for (Floor floor : floorList) {
            floor.setAssociation(association);
        }
        association.setFloor(floorList);
        if (association_id == 0) {
            associationService.insert(association);
        } else {
            association.setId(association_id);
            associationService.update(association);
        }

        AllAlerts.alertSave();
        reset();
        refreshData();
        association_id = 0L;

    }

    private void setFloorText(List<Floor> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i).getNumber_floor());
            if (i != list.size() - 1)
                stringBuilder.append(" - ");
        }
        number_floor.setText(stringBuilder.toString());
    }

    public void getEndDate(int months) {
        LocalDate value = datePicker.getValue();
        field_endDate.setText(value.plusMonths(months).minusDays(1).toString());
    }

    private void getTable() {
        tableView.getColumns().clear();
        ObservableList<Association> list = FXCollections.observableArrayList();

        List<Column<?>> columns = new ArrayList<>(Arrays.asList(
                new Column<>(String.class, "id", "م"),
                new Column<>(String.class, "name", "الاسم"),
                new Column<>(Double.class, "amount", "المبلغ"),
                new Column<>(Double.class, "count_month", "عدد الشهور"),
                new Column<>(Integer.class, "numberOfNames", "عدد الاسماء"),
                new Column<>(String.class, "start_date", "تاريخ البداية"),
                new Column<>(String.class, "date_end", "تاريخ الانتهاء"),
//                new Column<>(String.class, "dueDate", "تاريخ الاستحقاق"),
                new Column<>(String.class, "notes", "ملاحظات")
        ));

        Table_Setting.createTable(tableView, columns, list);
    }

    public void reset() {
        clearAll(field_amount, field_name);
        spinnerCount.getValueFactory().setValue(1);
        number_floor.setText("0");
    }

    private void refreshData() {
        tableView.setItems(FXCollections.observableList(associationService.findAllAssociations()));
    }

    private void buttonShowSetting() {
        save.disableProperty().bind(new BooleanBinding() {
            {
                bind(spinnerCount.valueProperty());
                bind(field_amount.textProperty());
                bind(field_name.textProperty());
                bind(number_floor.textProperty());

            }

            @Override
            protected boolean computeValue() {
                return field_name.getText().isEmpty() ||
                        field_amount.getText().isEmpty() ||
                        Double.parseDouble(field_amount.getText()) == 0.0 ||
                        number_floor.getText().equals("0");
            }

        });

        btn_add.disableProperty().bind(new BooleanBinding() {
            {
                bind(field_amount.textProperty());
                bind(field_name.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return field_name.getText().isEmpty() ||
                        field_amount.getText().isEmpty() ||
                        Double.parseDouble(field_amount.getText()) == 0.0;
            }

        });
    }
}
