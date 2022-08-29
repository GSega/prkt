package com.project.prkt.repository;

import com.project.prkt.model.SnowboardBoots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnowboardBootsRepository extends JpaRepository<SnowboardBoots, Long> {

    List<SnowboardBoots> findByNameContaining(String search);
}