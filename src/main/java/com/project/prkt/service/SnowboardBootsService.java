package com.project.prkt.service;

import com.project.prkt.model.SnowboardBoots;
import com.project.prkt.repository.SnowboardBootsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
        return snowboardBootsRepository.findAllByOrderById();
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

//    ----- Search by name -----
    public List<SnowboardBoots> findByPartOfName(String search) {
        return snowboardBootsRepository.findByNameContaining(search);
    }

//    ----- Sorts -----
    public List<SnowboardBoots> sortAllByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return snowboardBootsRepository.findAll(sort);
    }
}