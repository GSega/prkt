package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.Jacket;
import com.project.prkt.model.Rider;
import com.project.prkt.repository.JacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Service
public class JacketService {

    private final JacketRepository jacketRepository;

    @Autowired
    public JacketService(JacketRepository jacketRepository) {
        this.jacketRepository = jacketRepository;
    }

    // ----- show all -----
    public List<Jacket> showAllJackets() {
        return jacketRepository.findAllByOrderById();
    }

    // ----- add new -----
    public void addNewJacketToDB(Jacket jacket) {
        jacketRepository.save(jacket);
    }

    // ----- edit -----
    public Jacket showOneJacketById(Long id) {
        return jacketRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Jacket with id = " + id + " not found!"));
    }

    public void updateJacketById(Long id, Jacket updatedJacket) {
        Jacket jacketToBeUpdated = showOneJacketById(id);

        jacketToBeUpdated.setName(updatedJacket.getName());
        jacketToBeUpdated.setAvailable(updatedJacket.isAvailable());
        jacketToBeUpdated.setCondition(updatedJacket.getCondition());
        jacketToBeUpdated.setSize(updatedJacket.getSize());

        jacketRepository.save(jacketToBeUpdated);
    }

    //// ----- edit booking info / assign equipment to riders -----
    public void changeJacketAvailableById(Long jacketId) {
        Jacket jacketToBeUpdated = showOneJacketById(jacketId);
        jacketToBeUpdated.setAvailable(true ? false : true);
        jacketRepository.save(jacketToBeUpdated);
    }

    public List<Jacket> showAllAvailableJackets(Date dateOfArrival, Date dateOfReturn, List<Booking> allBookings) {
        List<Jacket> listOfAvailableJackets = jacketRepository.findAll();
        for (Booking booking : allBookings) {
            if (((dateOfArrival.after(booking.getDateOfArrival()) || dateOfArrival.equals(booking.getDateOfArrival())) &&
                    (dateOfArrival.before(booking.getDateOfReturn()) || dateOfArrival.equals(booking.getDateOfReturn()))) ||
                    ((dateOfReturn.after(booking.getDateOfArrival()) || dateOfReturn.equals(booking.getDateOfArrival())) &&
                            (dateOfReturn.before(booking.getDateOfReturn()) || dateOfReturn.equals(booking.getDateOfReturn())))) {
                for (Rider rider : booking.getListOfRiders()) {
                    listOfAvailableJackets.remove(rider.getAssignedEquipment().getJacket());
                }
            }
        }
        return listOfAvailableJackets;
    }

    // ----- delete -----
    public void deleteJacketById(Long id) {
        jacketRepository.deleteById(id);
    }

    // ----- search -----
    public List<Jacket> showJacketsByPartOfName(String partOfName) {
        return jacketRepository.findAllByNameContainingIgnoreCase(partOfName);
    }

    // ----- sort -----
    public List<Jacket> sortAllJacketsByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return jacketRepository.findAll(sort);
    }
}
