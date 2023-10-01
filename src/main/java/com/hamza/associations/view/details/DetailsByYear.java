package com.hamza.associations.view.details;

import com.hamza.associations.entity.Association;
import com.hamza.associations.service.AssociationService;
import com.salah.utils.fx.table.Column;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import org.hamza.extension.type.MonthsType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.salah.utils.fx.table.Table_Setting.createColumn;

public class DetailsByYear extends VBox {

    private TableView<Details> tableView;
    private final AssociationService associationService;

    public DetailsByYear(AssociationService associationService) {
        this.associationService = associationService;
        this.setSpacing(5);
        tableView = new TableView<>();
        getChildren().add(tableView);

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
        }
//        tableView.getColumns().get(0).getStyleClass().add(Setting_Language.NAME_COLUMN);
//        tableView.getColumns().get(tableView.getColumns().size() - 1).getStyleClass().add(Setting_Language.NAME_COLUMN);

        List<Details> list = new ArrayList<>();
        List<Association> allAssociations = associationService.findAllAssociations();
        for (Association allAssociation : allAssociations) {
            double amount = allAssociation.getAmount();
            int countMonth = allAssociation.getCount_month();
            Date startDate = allAssociation.getStart_date();

            LocalDate localDate = LocalDate.parse(startDate.toString()).plusMonths(1);

            // ass 8000
            // floor = 5 * amount 3000
            // floor = 6 * amount 5000

            int f1 = 5;
            int f2 = 6;

            list.add(new Details(allAssociation.getName(),
                    amount, amount, amount, amount, 5 * 3000, 6 * 5000, amount, amount, amount, amount, amount, amount, 0.0));

        }

        tableView.setItems(FXCollections.observableList(list));

    }


}
