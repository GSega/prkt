package com.project.prkt.service;

import com.project.prkt.model.Rider;
import com.project.prkt.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
