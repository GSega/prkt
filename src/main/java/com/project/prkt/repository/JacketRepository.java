package com.project.prkt.repository;

import com.project.prkt.model.Jacket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Repository
public interface JacketRepository extends JpaRepository<Jacket, Long> {

    // ----- show all -----
    List<Jacket> findAllByOrderById();

    // ----- search -----
    List<Jacket> findAllByNameContainingIgnoreCase(String partOfName);
}
