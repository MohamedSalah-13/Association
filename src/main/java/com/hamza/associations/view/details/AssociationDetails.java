package com.hamza.associations.view.details;

import com.hamza.associations.entity.Association;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AssociationDetails {

    @FXML
    private TableView<Association> tableView;

    @FXML
    public void initialize() {

    }
}
