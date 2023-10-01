package com.hamza.associations.view;

import com.hamza.associations.entity.Floor;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Floor_NumberWithFloor extends Dialog<List<Floor>> {

    private final List<Floor> floorList;
    private final VBox vBox_center;
    private final int size;
    private final double amount;
    private Text text_amount;


    public Floor_NumberWithFloor(int size, double amount, List<Floor> floorList2) {
        this.size = size;
        this.amount = amount;
        this.floorList = floorList2;
        vBox_center = new VBox(5);

        DialogPane var3 = this.getDialogPane();
        var3.setHeaderText("Choose Floor");
        var3.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        this.setResultConverter((var1x) -> {
            ButtonBar.ButtonData var2 = var1x == null ? null : var1x.getButtonData();
            this.floorList.forEach(System.out::println);
            return var2 == ButtonBar.ButtonData.OK_DONE ? this.floorList : null;

        });

        Text text = new Text("count :- " + size);
        Text text_amount = new Text("amount :- " + amount);

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
        extracted(this.floorList);
    }

    private void extracted(List<Floor> floorList) {
        if (!floorList.isEmpty()) {
            for (Floor floor : floorList) {
                vBox_center.getChildren().add(getE(floorList, floor));
            }
        } else {
            Floor floor = new Floor(1, amount);
            floorList.add(floor);
            vBox_center.getChildren().add(getE(floorList, floor));
        }
    }

    private Add_Box getE(List<Floor> floorList, Floor floor) {
        Add_Box floorAddBox = new Add_Box(floor, floorList, vBox_center, getIntegerList());
        ComboBox<Integer> comboBox = floorAddBox.getComboBox();
        TextField textField = floorAddBox.getTextField();
//        textField.setText(String.valueOf(floor.getAmount()));
//        comboBox.getSelectionModel().select(floor.getNumber_floor());
        comboBox.valueProperty().addListener((observableValue, integer, t1) -> {
            if (t1 != null) {
                floor.setNumber_floor(t1);
                floor.setAmount(Double.parseDouble(textField.getText()));
            }
        });

        textField.textProperty().addListener((observableValue, s, t1) -> {
            if (t1 != null) {
                floor.setNumber_floor(comboBox.getSelectionModel().getSelectedItem());
                floor.setAmount(Double.parseDouble(t1));
            }
        });

        return floorAddBox;
    }

    private List<Integer> getIntegerList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i + 1);
        }
        return list;
    }

    private HBox lastBox() {
        HBox hBox = new HBox(5);
        Text text = new Text("the rest :-");
        text_amount = new Text("0");
        hBox.getChildren().addAll(text, text_amount);
        return hBox;
    }

}