package com.project.prkt.repository;

import com.project.prkt.model.Snowboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Repository
public interface SnowboardRepository extends JpaRepository<Snowboard, Long> {

    // ----- show all -----
    List<Snowboard> findAllByOrderById();

    // ----- search -----
    List<Snowboard> findAllByNameContainingIgnoreCase(String partOfName);
}
