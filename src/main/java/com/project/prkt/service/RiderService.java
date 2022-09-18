package com.project.prkt.service;

import com.project.prkt.model.AssignedEquipment;
import com.project.prkt.model.Rider;
import com.project.prkt.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Sergei Gavrilov
 */
@Service
public class RiderService {
    private final RiderRepository riderRepository;
    @Autowired
    public RiderService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public void addNewRiderToDB(Rider rider) {
        riderRepository.save(rider);
    }

    public void deleteRiderById(Long id){
        riderRepository.deleteById(id);
    }

    public List<Rider> showAllRiders(){
        return riderRepository.findAllByOrderById();
    }

    //edit
    public Rider showOneRiderById(Long id){
        return riderRepository.findById(id).orElseThrow(()->
                new IllegalStateException("Rider with id = " + id + " not found!"));
    }

    public void updateRiderById(Long riderToBeUpdatedIid, Rider updatedRider){
        Rider riderToBeUpdated = showOneRiderById(riderToBeUpdatedIid);

        riderToBeUpdated.setName(updatedRider.getName());
        riderToBeUpdated.setFoot(updatedRider.getFoot());
        riderToBeUpdated.setSex(updatedRider.getSex());
        riderToBeUpdated.setHeight(updatedRider.getHeight());
        riderToBeUpdated.setWeight(updatedRider.getWeight());
        riderToBeUpdated.setEquipmentNeededIds(updatedRider.getEquipmentNeededIds());

        riderRepository.save(riderToBeUpdated);
    }

    public void assignEquipmentToRider(Rider rider, AssignedEquipment assignedEquipment) {
        rider.setAssignedEquipment(assignedEquipment);
        riderRepository.save(rider);
    }

    public void removeAssignedEquipment(Rider rider) {
        rider.getAssignedEquipment().setSnowboard(null);
        rider.getAssignedEquipment().setSnowboardBoots(null);
        rider.getAssignedEquipment().setSki(null);
        rider.getAssignedEquipment().setSkiBoots(null);
        rider.getAssignedEquipment().setJacket(null);
        rider.getAssignedEquipment().setPants(null);
        rider.getAssignedEquipment().setKneeProtection(null);
        rider.getAssignedEquipment().setProtectiveShorts(null);
        rider.getAssignedEquipment().setHelmet(null);
        rider.getAssignedEquipment().setGloves(null);

        riderRepository.save(rider);
    }
    //--------sort---------
    public List<Rider> sortAllByParameter(String parameter, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return riderRepository.findAll(sort);
    }
}
