package com.hamza.associations.view;

import com.hamza.associations.entity.Association;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

@Component
public class AssociationDetails {

    @FXML
    private TableView<Association> tableView;

    @FXML
    public void initialize() {

    }
}
