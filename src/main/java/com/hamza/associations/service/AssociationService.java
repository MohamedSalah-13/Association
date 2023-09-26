package com.hamza.associations.service;

import com.hamza.associations.entity.Association;
import com.hamza.associations.repository.AssociationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssociationService {

    @Autowired
    private AssociationRepository associations;


    public List<Association> findAllAssociations() {
        List<Association> all = associations.findAll();
        for (Association association : all) {
            Date startDate = association.getStart_date();
            int countMonth = association.getCount_month();
            LocalDate localDate = LocalDate.parse(startDate.toString()).plusMonths(countMonth);
//            Date newDate = DateUtils.addMonths(new Date(), 1);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date utilDate= null;
            try {
                utilDate = formatter.parse(date.toString());
            } catch (ParseException e) {
               e.printStackTrace();
            }
            association.setDate_end(utilDate);
        }
        return all;
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
