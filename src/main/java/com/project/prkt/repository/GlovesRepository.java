package com.project.prkt.repository;

import com.project.prkt.model.Gloves;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GlovesRepository extends JpaRepository<Gloves, Long> {
    //------show all----
    List<Gloves> findAllByOrderBySize();
    //-------search-----
    List<Gloves> findAllByNameContainingIgnoreCase(String partOfName);


}
