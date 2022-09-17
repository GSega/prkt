package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.EquipmentCondition;
import com.project.prkt.model.Rider;
import com.project.prkt.model.Snowboard;
import com.project.prkt.repository.SnowboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Service
public class SnowboardService {

    private final SnowboardRepository snowboardRepository;

    @Autowired
    public SnowboardService(SnowboardRepository snowboardRepository) {
        this.snowboardRepository = snowboardRepository;
    }

    // ----- show all -----
    public List<Snowboard> showAllSnowboards() {
        return snowboardRepository.findAllByOrderById();
    }

    // ----- add new -----
    public void addNewSnowboardToDB(Snowboard snowboard) {
        snowboardRepository.save(snowboard);
    }

    // ----- edit -----
    public Snowboard showOneSnowboardById(Long id) {
        return snowboardRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Snowboard with id = " + id + " not found!"));
    }

    public void updateSnowboardById(Long id, Snowboard updatedSnowboard) {
        Snowboard snowboardToBeUpdated = showOneSnowboardById(id);

        snowboardToBeUpdated.setName(updatedSnowboard.getName());
        snowboardToBeUpdated.setCondition(updatedSnowboard.getCondition());
        snowboardToBeUpdated.setSize(updatedSnowboard.getSize());
        snowboardToBeUpdated.setStiffness(updatedSnowboard.getStiffness());
        snowboardToBeUpdated.setArch(updatedSnowboard.getArch());
        snowboardToBeUpdated.setBindingSize(updatedSnowboard.getBindingSize());

        snowboardRepository.save(snowboardToBeUpdated);
    }

    //// ----- edit booking info / assign equipment to riders -----
    public List<Snowboard> showAllAvailableSnowboards(Date dateOfArrival, Date dateOfReturn, List<Booking> allBookings) {
        //get all snowboards
        List<Snowboard> listOfAvailableSnowboards = snowboardRepository.findAllByOrderBySize();
        //remove equipment that is broken, in service, or otherwise not ready
        for (Iterator<Snowboard> iterator = listOfAvailableSnowboards.iterator(); iterator.hasNext(); ) {
            Snowboard oneSnowboard = iterator.next();
            if (oneSnowboard.getCondition().equals(EquipmentCondition.BROKEN) ||
                    oneSnowboard.getCondition().equals(EquipmentCondition.SERVICE) ||
                    oneSnowboard.getCondition().equals(EquipmentCondition.UNKNOWN)) {
                iterator.remove();
            }
        }
        //remove already assigned equipment
        for (Booking booking : allBookings) {
            if (((dateOfArrival.after(booking.getDateOfArrival()) || dateOfArrival.equals(booking.getDateOfArrival())) &&
                    (dateOfArrival.before(booking.getDateOfReturn()) || dateOfArrival.equals(booking.getDateOfReturn()))) ||
                    ((dateOfReturn.after(booking.getDateOfArrival()) || dateOfReturn.equals(booking.getDateOfArrival())) &&
                            (dateOfReturn.before(booking.getDateOfReturn()) || dateOfReturn.equals(booking.getDateOfReturn())))) {
                for (Rider rider : booking.getListOfRiders()) {
                    listOfAvailableSnowboards.remove(rider.getAssignedEquipment().getSnowboard());
                }
            }
        }
        return listOfAvailableSnowboards;
    }

    // ----- delete -----
    public void deleteSnowboardById(Long id) {
        snowboardRepository.deleteById(id);
    }

    // ----- search -----
    public List<Snowboard> showSnowboardsByPartOfName(String partOfName) {
        return snowboardRepository.findAllByNameContainingIgnoreCase(partOfName);
    }

    // ----- sort -----
    public List<Snowboard> sortAllSnowboardsByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return snowboardRepository.findAll(sort);
    }
}
