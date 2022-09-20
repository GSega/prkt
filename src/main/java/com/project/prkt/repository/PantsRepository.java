package com.project.prkt.repository;

import com.project.prkt.model.Pants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PantsRepository extends JpaRepository<Pants, Long> {

    // ----- show all -----
    List<Pants> findAllByOrderBySize();

    // ----- search -----
    List<Pants> findAllByNameContainingIgnoreCase(String partOfName);

}
