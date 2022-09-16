package com.project.prkt.repository;

import com.project.prkt.model.Helmet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelmetRepository extends JpaRepository<Helmet, Long> {
    List<Helmet> findAllByNameContainingIgnoreCase(String partOfName);
}
