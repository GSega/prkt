package com.project.prkt.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Gloves extends Equipment{
    enum Size {XS, S, M, L, XL}

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @javax.validation.constraints.Size(min = 3, max = 30, message = "{message.invalid_name}")
    private String name;
    private Size size;
    private EquipmentCondition condition;
    @OneToMany(mappedBy = "gloves")
    private List<AssignedEquipment> listOfAssignedEquipment;

    public List<AssignedEquipment> getListOfAssignedEquipment() {
        if(listOfAssignedEquipment == null){
            return new ArrayList<>();
        }
        return listOfAssignedEquipment;
    }

    public void setListOfAssignedEquipment(List<AssignedEquipment> listOfAssignedEquipment) {
        this.listOfAssignedEquipment = listOfAssignedEquipment;
    }

    @Override
    public String toString() {
        return "Gloves{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", condition=" + condition +
                ", listOfAssignedEquipment=" + listOfAssignedEquipment +
                '}';
    }
}
