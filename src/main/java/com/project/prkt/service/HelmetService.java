package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.EquipmentCondition;
import com.project.prkt.model.Helmet;
import com.project.prkt.model.Rider;
import com.project.prkt.repository.HelmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HelmetService {
    private final HelmetRepository helmetRepository;

    @Autowired
    public HelmetService(HelmetRepository helmetRepository) {
        this.helmetRepository = helmetRepository;
    }

    public List<Helmet> showAllHelmets() {
        return helmetRepository.findAll();
    }

    public void saveHelmetToDB(Helmet helmet) {
        helmetRepository.save(helmet);
    }

    //----------all available helmets according to the dates
    public List<Helmet> allAvailableHelmets(Date dateOfArrival, Date dateOfReturn, List<Booking> allBookings) {
        List<Helmet> listOfAvailableHelmets = helmetRepository.findAll();

        listOfAvailableHelmets.removeIf(oneSnowboard ->
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
                    listOfAvailableHelmets.remove(rider.getAssignedEquipment().getSnowboard());
                }
            }
        }
        return listOfAvailableHelmets;
    }


    public Helmet showOneHelmetById(Long id) {
        return helmetRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Helmet with id = " + id + " not found!"));
    }

    public void updateHelmetById(Long id, Helmet helmetNewInfo) {
        Helmet helmet = showOneHelmetById(id);
        helmet.setName(helmetNewInfo.getName());
        helmet.setSize(helmetNewInfo.getSize());
        helmet.setCondition(helmetNewInfo.getCondition());
        helmetRepository.save(helmet);
    }

    public void deleteHelmetById(Long id) {
        helmetRepository.deleteById(id);
    }
    //------sort function---------
    public List<Helmet> sortAllHelmetsByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return helmetRepository.findAll(sort);
    }

    public List<Helmet> showHelmetsByPartOfName(String partOfName) {
       return helmetRepository.findAllByNameContainingIgnoreCase(partOfName);
    }

    // ----- assign equipment to riders in booking -----

}