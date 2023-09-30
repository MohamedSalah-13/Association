package com.hamza.associations.view.details;

import com.hamza.associations.entity.Floor;
import com.hamza.associations.service.FloorService;
import com.salah.utils.fx.table.Column;
import com.salah.utils.fx.table.Table_Setting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
//@Scope("prototype")
public class AssociationDetails {

    @FXML
    private TableView<Floor> tableView;
    private final FloorService floorService;
    @Setter
    private static Long id;


    @Autowired
    public AssociationDetails(FloorService floorService) {
        this.floorService = floorService;
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
    }

    private void refreshData() {
        tableView.setItems(FXCollections.observableList(floorService.findAllByAssociation_Id(1L)));
    }
}
