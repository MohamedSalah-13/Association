package com.hamza.associations.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Iterator;

import static com.salah.utils.fx.Utils.setTextFormatter;

public class Floor_Number extends Dialog<HashMap<Integer, Double>> {

    private final HashMap<Integer, Double> hashMap;
    private final VBox vBox_center = new VBox(5);
    private final int size;
    private Text text_amount;
    private final double amount;
    private double rest;

    public Floor_Number(int size, double amount) {
        this.size = size;
        this.amount = amount;
        hashMap = new HashMap<>();
        for (int i = 1; i <= size; i++) {
            hashMap.put(i, 0.0);
        }

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

    private double sumHash() {
        double sum = 0.0;
        for (double f : hashMap.values()) {
            sum += f;
        }
        return sum;
    }

    private HBox add_box() {
        ComboBox<Integer> comboBox = new ComboBox<>();
        getIntegerList(comboBox);
        TextField textField = new TextField();
        textField.setPrefWidth(70);
        setTextFormatter(textField);


        textField.textProperty().addListener((observableValue, integer, t1) -> {
            rest = amount - (sumHash() + Double.parseDouble(t1));
            text_amount.setText(String.valueOf(rest));
            if (rest < 0) {
                textField.setText("0.0");
                return;
            }
        });

        comboBox.valueProperty().addListener((observableValue, integer, t1) -> {
            double v = Double.parseDouble(textField.getText());
            hashMap.replace(t1, v);
        });

        comboBox.setOnMouseClicked(mouseEvent -> {
            hashMap.replace(comboBox.getValue(), 0.0);
            getIntegerList(comboBox);
        });


        Button add = new Button("+");
        Button remove_add = new Button("-");
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.getChildren().addAll(comboBox, textField, add, remove_add);

        add.setOnAction(actionEvent -> {
            if (vBox_center.getChildren().size() < size && rest > 0.0)
                vBox_center.getChildren().add(add_box());
        });

        remove_add.setOnAction(actionEvent -> {
            if (vBox_center.getChildren().size() > 1) {
                hashMap.replace(comboBox.getValue(), 0.0);
                vBox_center.getChildren().remove(hBox);
            }
        });

        return hBox;
    }

    private void getIntegerList(ComboBox<Integer> comboBox) {
        comboBox.getItems().clear();

        for (int i = 1; i <= size; i++) {
            if (hashMap.get(i) == 0.0) {
                comboBox.getItems().add(i);
            }
        }
    }


    private void print() {
        Iterator keys = hashMap.keySet().iterator();

        // أي وضعنا أسماء البلاد فيه .values بداخل الكائن h هنا قمنا بتخزين جميع قيم الكائن
        Iterator values = hashMap.values().iterator();

        System.out.println("The table below contains all Codes & Countries \n");
        System.out.println("---------------------");
        System.out.println("Code \t | Country");
        System.out.println("---------------------");

        // لا يزال يحتوي على مفاتيح keys هنا أنشأنا حلقة تستمر في تكرار نفسها طالما أن الكائن
        // الفكرة هنا المرور على جميع المفاتيح الموجودة و عرض كل مفتاح موجود و قيمته
        while (keys.hasNext()) {
            System.out.println(keys.next() + "\t | " + values.next());
        }
        System.out.println("---------------------\n");
    }
}