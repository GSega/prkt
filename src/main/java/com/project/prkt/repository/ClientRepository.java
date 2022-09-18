package com.project.prkt.repository;

import com.project.prkt.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // ----- show all -----
    List<Client> findAllByOrderById();

    // ----- search -----
    List<Client> findAllBySurnameContainingIgnoreCaseOrPhone1ContainingIgnoreCaseOrPhone2ContainingIgnoreCase(
            String search1, String search2, String search3
    );
}
