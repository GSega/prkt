package com.project.prkt.service;

import com.project.prkt.model.SnowboardBoots;
import com.project.prkt.repository.SnowboardBootsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SnowboardBootsService {

    SnowboardBootsRepository snowboardBootsRepository;

    @Autowired
    public SnowboardBootsService(SnowboardBootsRepository snowboardBootsRepository) {
        this.snowboardBootsRepository = snowboardBootsRepository;
    }

    public List<SnowboardBoots> findAll() {
        List<SnowboardBoots> listOfSnowboardBoots = snowboardBootsRepository.findAll();

        Comparator<SnowboardBoots> comparatorId = new Comparator<>() {
            @Override
            public int compare(SnowboardBoots o1, SnowboardBoots o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                } else if (o1.getId() < o2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        Collections.sort(listOfSnowboardBoots, comparatorId);
        return listOfSnowboardBoots;
    }

    public SnowboardBoots findById(Long id) {
        return snowboardBootsRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("snowboardBoots with id=" + id + " does not exist"));
    }

    public void addToDatabase(SnowboardBoots snowboardBoots) {
        snowboardBootsRepository.save(snowboardBoots);
    }

    public void updateById(Long id, SnowboardBoots updatedSnowboardBoots) {
        SnowboardBoots snowboardBootsToBeUpdated = findById(id);

        System.out.println(snowboardBootsToBeUpdated.toString());
        System.out.println(updatedSnowboardBoots.toString());

        snowboardBootsToBeUpdated.setName(updatedSnowboardBoots.getName());
        snowboardBootsToBeUpdated.setAvailable(updatedSnowboardBoots.isAvailable());
        snowboardBootsToBeUpdated.setCondition(updatedSnowboardBoots.getCondition());
        snowboardBootsToBeUpdated.setSize(updatedSnowboardBoots.getSize());
        snowboardBootsToBeUpdated.setStiffness(updatedSnowboardBoots.getStiffness());

        snowboardBootsRepository.save(snowboardBootsToBeUpdated);
    }

    public void deleteFromDatabase(Long id) {
        snowboardBootsRepository.deleteById(id);
    }
}
