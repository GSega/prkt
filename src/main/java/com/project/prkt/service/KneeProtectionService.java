package com.project.prkt.service;

import com.project.prkt.model.*;
import com.project.prkt.repository.KneeProtectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Service
public class KneeProtectionService {

    private final KneeProtectionRepository kneeProtectionRepository;

    @Autowired
    public KneeProtectionService(KneeProtectionRepository kneeProtectionRepository) {
        this.kneeProtectionRepository = kneeProtectionRepository;
    }

    // ----- show all -----
    public List<KneeProtection> showAllKneeProtection() {
        return kneeProtectionRepository.findAllByOrderById();
    }

    // ----- add new -----
    public void addNewKneeProtectionToDB(KneeProtection kneeProtection) {
        kneeProtectionRepository.save(kneeProtection);
    }

    // ----- edit -----
    public KneeProtection showOneKneeProtectionById(Long id) {
        return kneeProtectionRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("KneeProtection with id = " + id + " not found!"));
    }

    public void updateKneeProtectionById(Long id, KneeProtection updatedKneeProtection) {
        KneeProtection kneeProtectionToBeUpdated = showOneKneeProtectionById(id);

        kneeProtectionToBeUpdated.setName(updatedKneeProtection.getName());
        kneeProtectionToBeUpdated.setCondition(updatedKneeProtection.getCondition());
        kneeProtectionToBeUpdated.setSize(updatedKneeProtection.getSize());

        kneeProtectionRepository.save(kneeProtectionToBeUpdated);
    }

    //// ----- edit booking info / assign equipment to riders -----
    public List<KneeProtection> showAllAvailableKneeProtection(Date dateOfArrival, Date dateOfReturn, List<Booking> allBookings) {
        //get all knee protection
        List<KneeProtection> listOfAvailableKneeProtection = kneeProtectionRepository.findAllByOrderBySize();
        //remove equipment that is broken, in service, or otherwise not ready
        for (Iterator<KneeProtection> iterator = listOfAvailableKneeProtection.iterator(); iterator.hasNext(); ) {
            KneeProtection oneKneeProtection = iterator.next();
            if (oneKneeProtection.getCondition().equals(EquipmentCondition.BROKEN) ||
                    oneKneeProtection.getCondition().equals(EquipmentCondition.SERVICE) ||
                    oneKneeProtection.getCondition().equals(EquipmentCondition.UNKNOWN)) {
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
                    listOfAvailableKneeProtection.remove(rider.getAssignedEquipment().getKneeProtection());
                }
            }
        }
        return listOfAvailableKneeProtection;
    }

    // ----- delete -----
    public void deleteKneeProtectionById(Long id) {
        kneeProtectionRepository.deleteById(id);
    }

    // ----- search -----
    public List<KneeProtection> showKneeProtectionByPartOfName(String partOfName) {
        return kneeProtectionRepository.findAllByNameContainingIgnoreCase(partOfName);
    }

    // ----- sort -----
    public List<KneeProtection> sortAllKneeProtectionByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return kneeProtectionRepository.findAll(sort);
    }
}
