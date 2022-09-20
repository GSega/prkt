package com.project.prkt.repository;

import com.project.prkt.model.SkiBoots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkiBootsRepository extends JpaRepository<SkiBoots, Long> {

    List<SkiBoots> findAllByOrderBySize();

    List<SkiBoots> findAllByNameContaining(String search);
}
