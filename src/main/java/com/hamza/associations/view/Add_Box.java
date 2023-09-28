package com.hamza.associations.view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.List;

import static com.salah.utils.fx.Utils.setTextFormatter;

public class Add_Box<T, V> extends HBox {

    @Getter
    private final ComboBox<V> comboBox;
    @Getter
    private final TextField textField;

    public Add_Box(T t, List<T> list, VBox vBox, List<V> comboList) {
        comboBox = new ComboBox<>();
        textField = new TextField();
        textField.setPrefWidth(70);
        setTextFormatter(textField);

        setSpacing(5);
        Button add = new Button("+");
        Button remove_add = new Button("-");
        getChildren().addAll(comboBox, textField, add, remove_add);

        add.setOnAction(actionEvent -> {
            list.add(t);
            vBox.getChildren().add(new Add_Box<>(t, list, vBox, comboList));
        });

        remove_add.setOnAction(actionEvent -> {
            if (vBox.getChildren().size() > 1) {
                list.remove(t);
                vBox.getChildren().remove(this);
            }
        });

        comboBox.getItems().clear();
        comboBox.getItems().addAll(comboList);
    }

}
