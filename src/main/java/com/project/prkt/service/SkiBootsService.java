package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.EquipmentCondition;
import com.project.prkt.model.Rider;
import com.project.prkt.model.SkiBoots;
import com.project.prkt.repository.SkiBootsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SkiBootsService {
    private final SkiBootsRepository skiBootsRepository;

    @Autowired
    public SkiBootsService(SkiBootsRepository skiBootsRepository) {
        this.skiBootsRepository = skiBootsRepository;
    }

    public List<SkiBoots> showAllSkiBoots() {
        return skiBootsRepository.findAllByOrderBySize();
    }

    public void addNewSkiBootsToDB(SkiBoots skiBoots) {
        skiBootsRepository.save(skiBoots);
    }

    public SkiBoots showOneSkiBootsById(Long id) {
        return skiBootsRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("skiBoots with id = " + id + " not found!"));
    }

    public void updateSkiBootsById(Long id, SkiBoots updatedSkiBoots) {
        SkiBoots skiBootsToBeUpdated = showOneSkiBootsById(id);

        skiBootsToBeUpdated.setName(updatedSkiBoots.getName());
        skiBootsToBeUpdated.setCondition(updatedSkiBoots.getCondition());
        skiBootsToBeUpdated.setSize(updatedSkiBoots.getSize());
        skiBootsToBeUpdated.setStiffness(updatedSkiBoots.getStiffness());

        skiBootsRepository.save(skiBootsToBeUpdated);
    }

    public void deleteSkiBootsById(Long id) {
        skiBootsRepository.deleteById(id);
    }

    public List<SkiBoots> showSkiBootsByPartOfName(String partOfName) {
        return skiBootsRepository.findAllByNameContaining(partOfName);
    }

    public List<SkiBoots> sortAllSkiBootsByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return skiBootsRepository.findAll(sort);
    }





    public List<SkiBoots> showAllAvailableSkiBoots(Date dateOfArrival, Date dateOfReturn, List<Booking> allBookings) {
        List<SkiBoots> listOfAvailableSkiBoots = skiBootsRepository.findAllByOrderBySize();
        listOfAvailableSkiBoots.removeIf(oneSnowboard ->
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
                    listOfAvailableSkiBoots.remove(rider.getAssignedEquipment().getSnowboard());
                }
            }
        }
        return listOfAvailableSkiBoots;
    }
}
