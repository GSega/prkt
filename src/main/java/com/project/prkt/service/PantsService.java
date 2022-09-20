package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.EquipmentCondition;
import com.project.prkt.model.Pants;
import com.project.prkt.model.Rider;
import com.project.prkt.repository.PantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PantsService {
    private final PantsRepository pantsRepository;
    @Autowired
    public PantsService(PantsRepository pantsRepository) {
        this.pantsRepository = pantsRepository;
    }

    //-------show all-------
    public List<Pants> showAllPants(){
        return pantsRepository.findAllByOrderBySize();
    }

    //------show one------
    public Pants showOneById(Long id){
        return pantsRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Pants with id = " + id + "not found"));
    }

    //------add new-----

    public void saveNewPantsToDB(Pants pants){
        pantsRepository.save(pants);
    }

    //------delete------
    public void deletePantsById(Long id){
        pantsRepository.deleteById(id);
    }

    //-----update pants info----
    public void updatePantsById(Long id, Pants updatedPants){
        Pants pants = showOneById(id);
        pants.setName(updatedPants.getName());
        pants.setCondition(updatedPants.getCondition());
        pants.setSize(updatedPants.getSize());
        pantsRepository.save(pants);
    }
    //----list of available for booking pants -----
    public List<Pants> showAllAvailablePants(Date dateOfArrival, Date dateOfReturn, List<Booking> allBookings){
        List<Pants> listOfAvailablePants = pantsRepository.findAllByOrderBySize();
        listOfAvailablePants.removeIf(onePants ->
                onePants.getCondition().equals(EquipmentCondition.BROKEN) ||
                        onePants.getCondition().equals(EquipmentCondition.SERVICE) ||
                        onePants.getCondition().equals(EquipmentCondition.UNKNOWN));
        //remove already assigned equipment
        for (Booking booking : allBookings) {
            if (((dateOfArrival.after(booking.getDateOfArrival()) || dateOfArrival.equals(booking.getDateOfArrival())) &&
                    (dateOfArrival.before(booking.getDateOfReturn()) || dateOfArrival.equals(booking.getDateOfReturn()))) ||
                    ((dateOfReturn.after(booking.getDateOfArrival()) || dateOfReturn.equals(booking.getDateOfArrival())) &&
                            (dateOfReturn.before(booking.getDateOfReturn()) || dateOfReturn.equals(booking.getDateOfReturn()))) ||
                    (dateOfArrival.before(booking.getDateOfArrival()) && dateOfReturn.after(booking.getDateOfReturn()))) {
                for (Rider rider : booking.getListOfRiders()) {
                    listOfAvailablePants.remove(rider.getAssignedEquipment().getSnowboard());
                }
            }
        }
        return listOfAvailablePants;
    }
    //---------find be part of name
    public List<Pants> showPantsByPartOfName(String partOfName){
        return pantsRepository.findAllByNameContainingIgnoreCase(partOfName);
    }

    public List<Pants> sortAllPantsByParameter(String parameter, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return pantsRepository.findAll(sort);
    }

    // ----- assign equipment to riders in booking -----

}
