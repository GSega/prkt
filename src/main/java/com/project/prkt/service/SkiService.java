package com.project.prkt.service;

import com.project.prkt.model.EquipmentCondition;
import com.project.prkt.model.Ski;
import com.project.prkt.repository.SkiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public Ski findById(Long id){ // в лямбду пока тупо верим. разберемся позже
        return skiRepository.findById(id).orElseThrow(() -> new IllegalStateException("лыжи с id=" + id + "не найдены"));
    }

    public void addToDatabase(Ski ski){
        skiRepository.save(ski);
    }

    public void updateById(Long id, Ski updatedSki){
        Ski skiToBeUpdated = findById(id);
        skiToBeUpdated.setName(updatedSki.getName());
        skiToBeUpdated.setAvailable(updatedSki.isAvailable());
        skiToBeUpdated.setSize(updatedSki.getSize());
        skiToBeUpdated.setStiffness(updatedSki.getStiffness());
        skiToBeUpdated.setCondition(updatedSki.getCondition());
        skiRepository.save(skiToBeUpdated);
    }

    public void deleteFromDatabase(Long id) {
        skiRepository.deleteById(id);
    }


    public List<Ski> sortAllByParameter(String parameter, String sortDirection) { //parameter - имя поля по которому сортируем
        //в пеерменную типа sort надо положить инф о поле и направлении сортировки.ниже сравнивается направление с константой ASC и  записыватся в виде
        //Sort.by(поле по которому сортируем).ascending() или Sort.by(поле по которому сортируем).ascending()
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return skiRepository.findAll(sort);
    }
    public List<Ski> findByPartOfName(String search) {
        return skiRepository.findByNameContaining(search);
    }
}
