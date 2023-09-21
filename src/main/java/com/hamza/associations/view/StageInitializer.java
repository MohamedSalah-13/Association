package com.hamza.associations.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<MainApplication.event> {
    @Value("classpath:/view/associations.fxml")
    private Resource resource;
    private String string;
    private ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String s, ApplicationContext applicationContext) {
        this.string = s;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(MainApplication.event event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            Parent parent = fxmlLoader.load();
            Stage stage = event.getStage();
            stage.setScene(new Scene(parent, 800, 600));
            stage.setTitle(string);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
