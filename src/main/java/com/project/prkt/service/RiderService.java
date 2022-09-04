package com.project.prkt.service;

import com.project.prkt.model.Rider;
import com.project.prkt.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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





    // for work with the Booking classes

    // ----- show all -----
    public List<Rider> showAllRiders() {
        return riderRepository.findAllByOrderById();
    }

    // ----- edit -----
    public Rider showOneRiderById(Long id) {
        return riderRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Snowboard with id = " + id + " not found!"));
    }
}
