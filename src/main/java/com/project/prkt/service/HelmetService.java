package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.Helmet;
import com.project.prkt.model.Rider;
import com.project.prkt.repository.HelmetRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Helmet> showAllHelmets(){
        return helmetRepository.findAll();
    }

    public void saveHelmetToDB(Helmet helmet){
        helmetRepository.save(helmet);
    }

    //----------all available helmets according to the dates
    public List<Helmet> allAvailableHelmets(Date dateOfArrival, Date dateOfReturn, List<Booking> allBookings) {
        List<Helmet> allHelmets = helmetRepository.findAll();
        for (Booking booking : allBookings) {
            if (
                    (
                            (dateOfArrival.equals(booking.getDateOfArrival()) || dateOfArrival.after(booking.getDateOfArrival()))
                                    &&
                                    ((dateOfArrival.before(booking.getDateOfReturn())) || (dateOfArrival.equals(booking.getDateOfReturn())))
                    )
                            ||
                            (
                                    ((dateOfReturn.equals(booking.getDateOfArrival())) || (dateOfReturn.after(booking.getDateOfArrival())))
                                            &&
                                            ((dateOfReturn.equals(booking.getDateOfReturn())) || dateOfReturn.before(booking.getDateOfReturn()))
                            )
            ) {
                for (Rider rider : booking.getListOfRiders()) {
                    allHelmets.remove(rider.getAssignedEquipment().getHelmet());
                }
            }
        }
        return allHelmets;
    }


}