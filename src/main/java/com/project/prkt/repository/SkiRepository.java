package com.project.prkt.repository;

import com.project.prkt.model.Ski;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkiRepository extends JpaRepository<Ski, Long> {

    List<Ski> findAllByOrderById();

    List<Ski> findByNameContaining(String search);



    List<Ski> findAllByAvailableIsTrue();
}
