package com.hamza.associations.view;

import com.hamza.associations.entity.Floor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.List;

import static com.salah.utils.fx.Utils.setTextFormatter;

public class Add_Box extends HBox {

    @Getter
    private final ComboBox<Integer> comboBox;
    @Getter
    private final TextField textField;

    public Add_Box(Floor floor1, List<Floor> list, VBox vBox, List<Integer> comboList) {
        comboBox = new ComboBox<>();
        textField = new TextField();
        textField.setPrefWidth(70);
        setTextFormatter(textField);

        comboBox.getItems().clear();
        comboBox.getItems().addAll(comboList);

        textField.setText(String.valueOf(floor1.getAmount()));
        comboBox.getSelectionModel().select((Integer) floor1.getNumber_floor());

        setSpacing(5);
        Button add = new Button("+");
        Button remove_add = new Button("-");
        getChildren().addAll(comboBox, textField, add, remove_add);

        add.setOnAction(actionEvent -> {
            Floor floor = new Floor(1, 1000);
            vBox.getChildren().add(new Add_Box(floor, list, vBox, comboList));
            list.add(floor);
            list.forEach(System.out::println);
        });

        remove_add.setOnAction(actionEvent -> {
            if (vBox.getChildren().size() > 1) {
                list.remove(floor1);
                vBox.getChildren().remove(this);
            }
        });

    }

}
