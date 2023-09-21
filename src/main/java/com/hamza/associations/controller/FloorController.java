package com.hamza.associations.controller;

import com.hamza.associations.entity.Floor;
import com.hamza.associations.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/floor")
@Component
@RestController
public class FloorController {

    @Autowired
    private FloorService floorService;

    @GetMapping()
    public List<Floor> findAllFloor() {
        return this.floorService.findAll();
    }
}

