package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.ProtectiveShorts;
import com.project.prkt.model.Rider;
import com.project.prkt.repository.ProtectiveShortsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Service
public class ProtectiveShortsService {

    private final ProtectiveShortsRepository protectiveShortsRepository;

    @Autowired
    public ProtectiveShortsService(ProtectiveShortsRepository protectiveShortsRepository) {
        this.protectiveShortsRepository = protectiveShortsRepository;
    }

    // ----- show all -----
    public List<ProtectiveShorts> showAllProtectiveShorts() {
        return protectiveShortsRepository.findAllByOrderById();
    }

    // ----- add new -----
    public void addNewProtectiveShortsToDB(ProtectiveShorts protectiveShorts) {
        protectiveShortsRepository.save(protectiveShorts);
    }

    // ----- edit -----
    public ProtectiveShorts showOneProtectiveShortsById(Long id) {
        return protectiveShortsRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("ProtectiveShorts with id = " + id + " not found!"));
    }

    public void updateProtectiveShortsById(Long id, ProtectiveShorts updatedProtectiveShorts) {
        ProtectiveShorts protectiveShortsToBeUpdated = showOneProtectiveShortsById(id);

        protectiveShortsToBeUpdated.setName(updatedProtectiveShorts.getName());
        protectiveShortsToBeUpdated.setAvailable(updatedProtectiveShorts.isAvailable());
        protectiveShortsToBeUpdated.setCondition(updatedProtectiveShorts.getCondition());
        protectiveShortsToBeUpdated.setSize(updatedProtectiveShorts.getSize());

        protectiveShortsRepository.save(protectiveShortsToBeUpdated);
    }

    //// ----- edit booking info / assign equipment to riders -----
    public void changeProtectiveShortsAvailableById(Long protectiveShortsId) {
        ProtectiveShorts protectiveShortsToBeUpdated = showOneProtectiveShortsById(protectiveShortsId);
        protectiveShortsToBeUpdated.setAvailable(true ? false : true);
        protectiveShortsRepository.save(protectiveShortsToBeUpdated);
    }

    public List<ProtectiveShorts> showAllAvailableProtectiveShorts(Date dateOfArrival, Date dateOfReturn, List<Booking> allBookings) {
        List<ProtectiveShorts> listOfAvailableProtectiveShorts = protectiveShortsRepository.findAll();
        for (Booking booking : allBookings) {
            if (((dateOfArrival.after(booking.getDateOfArrival()) || dateOfArrival.equals(booking.getDateOfArrival())) &&
                    (dateOfArrival.before(booking.getDateOfReturn()) || dateOfArrival.equals(booking.getDateOfReturn()))) ||
                    ((dateOfReturn.after(booking.getDateOfArrival()) || dateOfReturn.equals(booking.getDateOfArrival())) &&
                            (dateOfReturn.before(booking.getDateOfReturn()) || dateOfReturn.equals(booking.getDateOfReturn())))) {
                for (Rider rider : booking.getListOfRiders()) {
                    listOfAvailableProtectiveShorts.remove(rider.getAssignedEquipment().getProtectiveShorts());
                }
            }
        }
        return listOfAvailableProtectiveShorts;
    }

    // ----- delete -----
    public void deleteProtectiveShortsById(Long id) {
        protectiveShortsRepository.deleteById(id);
    }

    // ----- search -----
    public List<ProtectiveShorts> showProtectiveShortsByPartOfName(String partOfName) {
        return protectiveShortsRepository.findAllByNameContainingIgnoreCase(partOfName);
    }

    // ----- sort -----
    public List<ProtectiveShorts> sortAllProtectiveShortsByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return protectiveShortsRepository.findAll(sort);
    }
}
