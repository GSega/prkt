package com.project.prkt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Pants extends Equipment{
    enum Size {XS, S, M, L, XL}

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @javax.validation.constraints.Size(min = 3, max = 30, message = "{message.invalid_name}")
    private String name;
    private Size size;
    private EquipmentCondition condition;
    @OneToMany(mappedBy = "pants")
    private List<AssignedEquipment> listOfAssignedEquipment;

    public Pants() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public EquipmentCondition getCondition() {
        return condition;
    }

    public void setCondition(EquipmentCondition condition) {
        this.condition = condition;
    }

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
        return "Pants{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", condition=" + condition +
                ", size=" + size +
                ", listOfAssignedEquipment=" + listOfAssignedEquipment +
                '}';
    }
}
