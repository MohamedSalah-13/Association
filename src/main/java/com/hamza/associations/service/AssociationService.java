package com.hamza.associations.service;

import com.hamza.associations.entity.Association;
import com.hamza.associations.repository.AssociationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationService {

    @Autowired
    private AssociationRepository associations;


    public List<Association> findAllAssociations() {
        return associations.findAll();
    }

    public Optional<Association> findAssociationById(Long id) {
        return associations.findById(id);
    }

    public Association insert(Association association) {
        return associations.save(association);
    }

    public Association update(Association association) {
        return associations.save(association);
    }

    public void deleteAssociation(Long id) {
        associations.deleteById(id);
    }

    public List<Association> savelist(List<Association> associationList) {
       return associations.saveAll(associationList);
    }
}
