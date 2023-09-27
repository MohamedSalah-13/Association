package com.hamza.associations.view;

import com.hamza.associations.entity.Association;
import com.hamza.associations.entity.Floor;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.salah.utils.fx.Utils.setTextFormatter;

public class Floor_NumberWithFloor extends Dialog<List<Floor>> {

    //    private final HashMap<Integer, Double> hashMap;
    private final List<Floor> hashMap = new ArrayList<>();
    private final VBox vBox_center = new VBox(5);
    private final int size;
    private final double amount;
    private Text text_amount;
    private double rest;
    private Association association;

    public Floor_NumberWithFloor(int size, double amount, Association association) {
        this.size = size;
        this.amount = amount;
        this.association = association;
//        hashMap = new HashMap<>();
/*        for (int i = 1; i <= size; i++) {
            hashMap.put(i, 0.0);
        }*/

        DialogPane var3 = this.getDialogPane();
        var3.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        this.setResultConverter((var1x) -> {
            ButtonBar.ButtonData var2 = var1x == null ? null : var1x.getButtonData();
            return var2 == ButtonBar.ButtonData.OK_DONE ? hashMap : null;
        });


        Text text = new Text("count :- " + size);
        Text text_amount = new Text("amount :- " + amount);

        vBox_center.getChildren().add(add_box());

        ScrollPane scrollPane = new ScrollPane(vBox_center);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(5));

        VBox vBox = new VBox();
        VBox.setVgrow(scrollPane, Priority.SOMETIMES);
        vBox.setPadding(new Insets(5));
        vBox.setSpacing(10);
        vBox.setPrefSize(300, 260);

        vBox.getChildren().addAll(text, text_amount, scrollPane, lastBox());
        this.getDialogPane().setContent(vBox);
    }

    private HBox lastBox() {
        HBox hBox = new HBox(5);
        Text text = new Text("the rest :-");
        text_amount = new Text("0");
        hBox.getChildren().addAll(text, text_amount);
        return hBox;
    }

//    private double sumHash() {
//        double sum = 0.0;
//        for (double f : hashMap.values()) {
//            sum += f;
//        }
//        return sum;
//    }

    private HBox add_box() {
        Floor floor = new Floor();
        floor.setAssociation(association);
        ComboBox<Integer> comboBox = new ComboBox<>();
        getIntegerList(comboBox);
        TextField textField = new TextField();
        textField.setPrefWidth(70);
        setTextFormatter(textField);


        comboBox.valueProperty().addListener((observableValue, integer, t1) -> {
            double amount1 = Double.parseDouble(textField.getText());
            floor.setNumber_floor(t1);
            floor.setAmount(amount1);
        });

        comboBox.setOnMouseClicked(mouseEvent -> {
            getIntegerList(comboBox);
        });


        Button add = new Button("+");
        Button remove_add = new Button("-");
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.getChildren().addAll(comboBox, textField, add, remove_add);

        add.setOnAction(actionEvent -> {
            if (vBox_center.getChildren().size() < size)
                vBox_center.getChildren().add(add_box());
        });

        remove_add.setOnAction(actionEvent -> {
            if (vBox_center.getChildren().size() > 1) {
                hashMap.remove(floor);
                vBox_center.getChildren().remove(hBox);
            }
        });

        hashMap.add(floor);
        return hBox;
    }

    private void getIntegerList(ComboBox<Integer> comboBox) {
        comboBox.getItems().clear();

        for (int i = 1; i <= size; i++) {
            comboBox.getItems().add(i);
        }
    }

}