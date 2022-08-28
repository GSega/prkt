package com.project.prkt.service;

import com.project.prkt.model.EquipmentCondition;
import com.project.prkt.model.Ski;
import com.project.prkt.repository.SkiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkiService {
    private final SkiRepository skiRepository;

    @Autowired
    public SkiService(SkiRepository skiRepository) {
        this.skiRepository = skiRepository;
    }



    public List<Ski> findAll(){
        return skiRepository.findAll();
    }

    public Ski findById(Long id){ // шо за пустые скобочи и стрелочка такие?
        return skiRepository.findById(id).orElseThrow(() -> new IllegalStateException("лыжи с id=" + id + "не найдены"));
    }

    public void addToDatabase(Ski ski){
        skiRepository.save(ski);
    }

    public void updateAvailableById(Long id, boolean available){
        Ski skiToBeUpdated = findById(id); // так вроде тож должно сработать
        skiToBeUpdated.setAvailable(available);
    }
    public void updateCondition(Long id, EquipmentCondition equipmentCondition){
        Ski skiToBeUpdated = findById(id);
        skiToBeUpdated.setCondition(equipmentCondition);
    }

    public void deleteFromDatabase(Long id) {
        skiRepository.deleteById(id);
    }

    // public void updateName
    // public void updateStiffness
    // public void updateSize

}
