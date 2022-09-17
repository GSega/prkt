package com.project.prkt.repository;

import com.project.prkt.model.ProtectiveShorts;
import com.project.prkt.model.Snowboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Repository
public interface ProtectiveShortsRepository extends JpaRepository<ProtectiveShorts, Long> {

    // ----- show all -----
    List<ProtectiveShorts> findAllByOrderById();

    // ----- search -----
    List<ProtectiveShorts> findAllByNameContainingIgnoreCase(String partOfName);

    //// ----- edit booking info / assign equipment to riders -----
    List<ProtectiveShorts> findAllByOrderBySize();
}
