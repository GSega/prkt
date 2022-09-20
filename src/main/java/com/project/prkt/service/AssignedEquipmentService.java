package com.project.prkt.service;

import com.project.prkt.model.AssignedEquipment;
import com.project.prkt.repository.AssignedEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nikolai Khriapov
 */

@Service
public class AssignedEquipmentService {

    private final AssignedEquipmentRepository assignedEquipmentRepository;

    @Autowired
    public AssignedEquipmentService(AssignedEquipmentRepository assignedEquipmentRepository) {
        this.assignedEquipmentRepository = assignedEquipmentRepository;
    }

    public void addNewAssignedEquipmentToDB(AssignedEquipment assignedEquipment) {
        assignedEquipmentRepository.save(assignedEquipment);
    }
}
