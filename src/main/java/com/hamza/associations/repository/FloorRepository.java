package com.hamza.associations.repository;

import com.hamza.associations.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    void deleteByAssociation_Id(Long id);
}
