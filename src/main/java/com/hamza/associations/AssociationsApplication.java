package com.hamza.associations;

import com.hamza.associations.entity.Association;
import com.hamza.associations.entity.Floor;
import com.hamza.associations.service.AssociationService;
import com.hamza.associations.view.MainApplication;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class AssociationsApplication implements CommandLineRunner {

    public static void main(String[] args) {
//        SpringApplication.run(AssociationsApplication.class, args);
        Application.launch(MainApplication.class, args);
    }

    @Autowired
    private AssociationService associationService;

    @Override
    public void run(String... args) throws Exception {
        extracted("Mohamed", 5000, 10);
        extracted("Hamza", 10000, 12);
    }

    private void extracted(String name, int amount, int countMonth) {
        double am = (double) amount / 2;
        Random random = new Random();

        Association association = new Association();
        association.setName(name);
        association.setAmount(amount);
        association.setCount_month(countMonth);
//        association.setCreate_date(new Date());
        association.setStart_date(new Date());
        List<Floor> floor = new ArrayList<>();
        floor.add(new Floor(random.nextInt(countMonth + 1), am));
        floor.add(new Floor(random.nextInt(countMonth + 2), am));
        association.setFloor(floor);
        associationService.insert(association);
    }
}