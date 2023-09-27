package com.hamza.associations.repository;

import com.hamza.associations.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    List<Floor> findAllByAssociation_Id(Long id);
}
