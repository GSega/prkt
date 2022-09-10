package com.project.prkt.service;

import com.project.prkt.model.Rider;
import com.project.prkt.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
