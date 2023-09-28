package com.hamza.associations.view;

import com.hamza.associations.AssociationsApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@SpringBootApplication
@Component
public class OpenDetails extends Application {
    @Value("classpath:/view/details.fxml")
    private Resource resource;

    private ConfigurableApplicationContext springContext;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
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
