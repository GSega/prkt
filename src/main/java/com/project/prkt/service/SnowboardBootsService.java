package com.project.prkt.service;

import com.project.prkt.model.SnowboardBoots;
import com.project.prkt.repository.SnowboardBootsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Service
public class SnowboardBootsService {

    private final SnowboardBootsRepository snowboardBootsRepository;

    @Autowired
    public SnowboardBootsService(SnowboardBootsRepository snowboardBootsRepository) {
        this.snowboardBootsRepository = snowboardBootsRepository;
    }

    // ----- show all -----
    public List<SnowboardBoots> showAllSnowboardBoots() {
        return snowboardBootsRepository.findAllByOrderById();
    }

    // ----- add new -----
    public void addNewSnowboardBootsToDB(SnowboardBoots snowboardBoots) {
        snowboardBootsRepository.save(snowboardBoots);
    }

    // ----- edit -----
    public SnowboardBoots showOneSnowboardBootsById(Long id) {
        return snowboardBootsRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("snowboardBoots with id = " + id + " not found!"));
    }

    public void updateSnowboardBootsById(Long id, SnowboardBoots updatedSnowboardBoots) {
        SnowboardBoots snowboardBootsToBeUpdated = showOneSnowboardBootsById(id);

        snowboardBootsToBeUpdated.setName(updatedSnowboardBoots.getName());
        snowboardBootsToBeUpdated.setAvailable(updatedSnowboardBoots.isAvailable());
        snowboardBootsToBeUpdated.setCondition(updatedSnowboardBoots.getCondition());
        snowboardBootsToBeUpdated.setSize(updatedSnowboardBoots.getSize());
        snowboardBootsToBeUpdated.setStiffness(updatedSnowboardBoots.getStiffness());

        snowboardBootsRepository.save(snowboardBootsToBeUpdated);
    }

    //// ----- edit booking info / assign equipment to riders -----
    public void changeSnowboardBootsAvailableById(Long snowboardBootsId) {
        SnowboardBoots snowboardBootsToBeUpdated = showOneSnowboardBootsById(snowboardBootsId);
        snowboardBootsToBeUpdated.setAvailable(true ? false : true);
        snowboardBootsRepository.save(snowboardBootsToBeUpdated);
    }

    // ----- delete -----
    public void deleteSnowboardBootsById(Long id) {
        snowboardBootsRepository.deleteById(id);
    }

    // ----- search -----
    public List<SnowboardBoots> showSnowboardBootsByPartOfName(String partOfName) {
        return snowboardBootsRepository.findAllByNameContaining(partOfName);
    }

    // ----- sort -----
    public List<SnowboardBoots> sortAllSnowboardBootsByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return snowboardBootsRepository.findAll(sort);
    }
}
