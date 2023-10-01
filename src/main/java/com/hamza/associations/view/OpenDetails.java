package com.hamza.associations.view;

import com.hamza.associations.AssociationsApplication;
import com.hamza.associations.entity.Association;
import com.hamza.associations.service.FloorService;
import com.hamza.associations.view.details.AssociationDetails;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class OpenDetails extends Application {

    private ConfigurableApplicationContext springContext;
    private final FloorService floorService;
    private final Association association;

    public OpenDetails(FloorService floorService, Association association) {
        this.floorService = floorService;
        this.association = association;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/details.fxml"));
//        fxmlLoader.setControllerFactory(springContext::getBean);
//        fxmlLoader.setLocation(getClass().getResource("details.fxml"));
//        fxmlLoader.setControllerFactory(aClass -> springContext.getBean(aClass));
        AssociationDetails details = new AssociationDetails(floorService, association);
        fxmlLoader.setController(details);
        Parent root = fxmlLoader.load();
        stage.setTitle("Details");
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(AssociationsApplication.class).run();
    }

}
