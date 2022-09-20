package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.EquipmentCondition;
import com.project.prkt.model.Gloves;
import com.project.prkt.model.Rider;
import com.project.prkt.repository.GlovesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GlovesService {
    private final GlovesRepository glovesRepository;
    @Autowired
    public GlovesService(GlovesRepository glovesRepository) {
        this.glovesRepository = glovesRepository;
    }

    //-------show all-------

    public List<Gloves> showAllGloves(){
        return glovesRepository.findAllByOrderById();
    }

    //------show one------

    public Gloves showOneById(Long id){
        return glovesRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Gloves with id = " + id + "not found"));
    }

    //------add new-----

    public void saveNewGlovesToDB(Gloves gloves){
        glovesRepository.save(gloves);
    }

    //------delete------

    public void deleteGlovesById(Long id){
        glovesRepository.deleteById(id);
    }

    //-----update gloves info----

    public void updateGlovesById(Long id, Gloves updatedGloves){
        Gloves gloves = showOneById(id);
        gloves.setName(updatedGloves.getName());
        gloves.setCondition(updatedGloves.getCondition());
        gloves.setSize(updatedGloves.getSize());
        glovesRepository.save(gloves);
    }

    //----list of available for booking gloves -----
    public List<Gloves> showAllAvailableGloves(Date dateOfArrival, Date dateOfReturn, List<Booking> allBookings){
        List<Gloves> listOfAvailableGloves = glovesRepository.findAll();

        listOfAvailableGloves.removeIf(oneSnowboard ->
                oneSnowboard.getCondition().equals(EquipmentCondition.BROKEN) ||
                        oneSnowboard.getCondition().equals(EquipmentCondition.SERVICE) ||
                        oneSnowboard.getCondition().equals(EquipmentCondition.UNKNOWN));
        //remove already assigned equipment
        for (Booking booking : allBookings) {
            if (((dateOfArrival.after(booking.getDateOfArrival()) || dateOfArrival.equals(booking.getDateOfArrival())) &&
                    (dateOfArrival.before(booking.getDateOfReturn()) || dateOfArrival.equals(booking.getDateOfReturn()))) ||
                    ((dateOfReturn.after(booking.getDateOfArrival()) || dateOfReturn.equals(booking.getDateOfArrival())) &&
                            (dateOfReturn.before(booking.getDateOfReturn()) || dateOfReturn.equals(booking.getDateOfReturn()))) ||
                    (dateOfArrival.before(booking.getDateOfArrival()) && dateOfReturn.after(booking.getDateOfReturn()))) {
                for (Rider rider : booking.getListOfRiders()) {
                    listOfAvailableGloves.remove(rider.getAssignedEquipment().getSnowboard());
                }
            }
        }
        return listOfAvailableGloves;
    }
    //---------find be part of name-------

    public List<Gloves> showGlovesByPartOfName(String partOfName){
        return glovesRepository.findAllByNameContainingIgnoreCase(partOfName);
    }

    public List<Gloves> sortAllGlovesByParameter(String parameter, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return glovesRepository.findAll(sort);
    }

    // ----- assign equipment to riders in booking -----

}
