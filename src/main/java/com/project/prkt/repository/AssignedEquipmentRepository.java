package com.project.prkt.repository;

import com.project.prkt.model.AssignedEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nikolai Khriapov
 */

@Repository
public interface AssignedEquipmentRepository extends JpaRepository<AssignedEquipment, Long> {
}
