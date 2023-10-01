package com.hamza.associations.view.details;

import com.hamza.associations.entity.Association;
import com.hamza.associations.entity.Floor;
import com.hamza.associations.service.FloorService;
import com.salah.utils.fx.table.Column;
import com.salah.utils.fx.table.Table_Setting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Component
//@Scope("prototype")
public class AssociationDetails {

    @FXML
    private TableView<Floor> tableView;

    private final FloorService floorService;
    private final Association association;


    public AssociationDetails(FloorService floorService, Association association) {
        this.floorService = floorService;
        this.association = association;
    }

    @FXML
    public void initialize() {
        getTable();
        refreshData();
    }

    private void getTable() {
        tableView.getColumns().clear();
        ObservableList<Floor> list = FXCollections.observableArrayList();

        List<Column<?>> columns = new ArrayList<>(Arrays.asList(
                new Column<>(Integer.class, "number_floor", "الدور"),
                new Column<>(String.class, "due_date", "تاريخ الاستحقاق")
        ));
        Table_Setting.createTable(tableView, columns, list);
        tableView.getColumns().add(0, Table_Setting.column_number());
    }

    private void refreshData() {
        List<Floor> allByAssociationId = floorService.findAllByAssociation_Id(association.getId());
        for (Floor floor : allByAssociationId) {
            LocalDate localDate = LocalDate.parse(association.getStart_date().toString());
            floor.setDue_date(localDate.plusMonths(floor.getNumber_floor()));
        }
        tableView.setItems(FXCollections.observableList(allByAssociationId));
    }
}
