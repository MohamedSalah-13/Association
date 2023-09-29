package com.hamza.associations.view;

import com.hamza.associations.view.details.AssociationDetails;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

//@SpringBootApplication
public class OpenDetails extends Application {

    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        launch(OpenDetails.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("details.fxml"));
//        fxmlLoader.setControllerFactory(springContext::getBean);
//        fxmlLoader.setLocation(getClass().getResource("details.fxml"));
        fxmlLoader.setControllerFactory(aClass -> springContext.getBean(aClass));
        Parent root = fxmlLoader.load();
        stage.setTitle("Sample app");
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
        springContext = new SpringApplicationBuilder(AssociationDetails.class).run();
    }

}
