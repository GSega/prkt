package com.project.prkt.repository;

import com.project.prkt.model.SnowboardBoots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Repository
public interface SnowboardBootsRepository extends JpaRepository<SnowboardBoots, Long> {

    // ----- show all -----
    List<SnowboardBoots> findAllByOrderById();

    // ----- search -----
    List<SnowboardBoots> findAllByNameContainingIgnoreCase(String search);
}