package com.hamza.associations.view.details;

import com.hamza.associations.entity.Association;
import com.hamza.associations.entity.Floor;
import com.hamza.associations.service.AssociationService;
import com.hamza.associations.service.FloorService;
import com.salah.utils.fx.table.Column;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.hamza.extension.type.MonthsType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.salah.utils.fx.table.Table_Setting.createColumn;

public class DetailsByYear extends VBox {

    private TableView<Details> tableView;
    private Spinner<Integer> spinner;

    private final AssociationService associationService;
    private final FloorService floorService;

    public DetailsByYear(AssociationService associationService, FloorService floorService) {
        this.associationService = associationService;
        this.floorService = floorService;
        tableView = new TableView<>();
        spinner = new Spinner<>();
        HBox hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(new Text("Year"), spinner);
        VBox.setVgrow(tableView, Priority.SOMETIMES);


        this.setPadding(new Insets(5));
        this.setSpacing(5);
        getChildren().addAll(hBox, tableView);
        getTable();
    }

    private void getTable() {
        tableView.getColumns().clear();
        String[] strings = {"name", "jan", "feb", "mar", "april", "may", "jun", "july", "aug", "sep", "oct", "nov", "des", "totals"};
        Column<?> column;
        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                column = new Column<>(String.class, strings[i], "اليوم" + "/" + "الشهر");
            } else if (i == strings.length - 1) {
                column = new Column<>(Double.class, strings[i], "totals");
            } else
                column = new Column<>(Double.class, strings[i], MonthsType.getTypeById(i));

            tableView.getColumns().add(createColumn(column));
        }

        // color for table
//        tableView.getStylesheets().add(Style_Sheet.COLOR_Table);

        for (int i = 0; i < tableView.getColumns().size(); i++) {
//            tableView.getColumns().get(i).getStyleClass().add(Setting_Language.NAME_COLUMN_CENTER);
            tableView.getColumns().get(i).setMinWidth(100);
            if (i != 0)
                extracted(i);
        }
//        tableView.getColumns().get(0).getStyleClass().add(Setting_Language.NAME_COLUMN);
//        tableView.getColumns().get(tableView.getColumns().size() - 1).getStyleClass().add(Setting_Language.NAME_COLUMN);

        HashMap<Integer, Double> hashMap = new HashMap<>();

        List<Details> list = new ArrayList<>();
        List<Association> allAssociations = associationService.findAllAssociations();
        for (Association allAssociation : allAssociations) {
            int countMonth = allAssociation.getCount_month();
            double amount = allAssociation.getAmount();
            List<Floor> floor = floorService.findAllByAssociation_Id(allAssociation.getId());
            for (int i = 1; i <= 12; i++) {
                hashMap.put(i, amount);
            }

            for (Floor value : floor) {
                int numberFloor = value.getNumber_floor();
                for (int i = 1; i <= 12; i++) {
//                    hashMap.put(i, amount);
                    if (i == numberFloor) {
                        hashMap.put(i, value.getAmount() * countMonth);

                    }
                }
            }

            list.add(new Details(allAssociation.getName(),
                    hashMap.get(1), hashMap.get(2), hashMap.get(3), hashMap.get(4), hashMap.get(5), hashMap.get(6),
                    hashMap.get(7), hashMap.get(8), hashMap.get(9), hashMap.get(10), hashMap.get(11), hashMap.get(12), amount));

        }


        double sumJan = list.stream().mapToDouble(Details::getJan).sum();
        double sumFeb = list.stream().mapToDouble(Details::getFeb).sum();

        // add totals
        list.add(new Details("Totals",
                sumJan, sumFeb, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0));


        tableView.setItems(FXCollections.observableList(list));


    }

    private void extracted(int i) {
        TableColumn<Details, Double> thirdColumn = (TableColumn<Details, Double>) tableView.getColumns().get(i);
        thirdColumn.setCellFactory(new Callback<>() {
            public TableCell<Details, Double> call(TableColumn param) {

                return new TableCell<>() {
                    @Override
                    public void updateItem(Double item, boolean empty) {
                        TableRow<Details> tableRow = getTableRow();
                        super.updateItem(item, empty);
                        if (!isEmpty()) {

                            // Get fancy and change color based on data
                            if (item > tableRow.getTableView().getItems().get(getIndex()).getTotals())
//                                this.setTextFill(Color.BLUEVIOLET);
                                this.setTextFill(Color.RED);
                            setText(String.valueOf(item));
                        }
                    }
                };
            }
        });
    }

}
