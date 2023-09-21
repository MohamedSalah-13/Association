package com.hamza.associations.controller;

import com.hamza.associations.entity.Association;
import com.hamza.associations.service.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/association")
@Component
@RestController
public class AssociationController {

    @Autowired
    private AssociationService associationService;

    @GetMapping()
    public List<Association> findAllAssociations() {
        return this.associationService.findAllAssociations();
    }

    @GetMapping("/{num}")
    public Optional<Association> findAssociationById(@PathVariable("num") Long num) {
        return associationService.findAssociationById(num);
    }

    @PostMapping("/insert")
    public Association saveAssociation(@RequestBody Association association) {
        return associationService.insert(association);
    }

    @PostMapping("/saveList")
    public List<Association> saveAssociationList(@RequestBody List<Association> associationList) {
        return associationService.savelist(associationList);
    }

    @PutMapping("/update")
    public Association updateAssociation(@RequestBody Association association) {
        return associationService.update(association);
    }

    @DeleteMapping("/{num}")
    public void deleteAssociation(@PathVariable("num") Long num) {
        associationService.deleteAssociation(num);
    }
}

