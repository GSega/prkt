package com.project.prkt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Helmet extends Equipment{
    public enum Size{UNKNOWN, XS, S, M, L, XL}

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @javax.validation.constraints.Size(min = 3, max = 30, message = "{helmet.errormessage.invalid_name}")
    private String name;
    private EquipmentCondition condition;
    private Size size;
    @OneToMany(mappedBy = "helmet")
    private List<AssignedEquipment> assignedEquipmentList;

    public Helmet() {
    }

    public List<AssignedEquipment> getAssignedEquipmentList() {
        if(assignedEquipmentList == null){
            return new ArrayList<>();
        }
        return assignedEquipmentList;
    }

    public void setAssignedEquipmentList(List<AssignedEquipment> assignedEquipmentList) {
        this.assignedEquipmentList = assignedEquipmentList;
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

    public EquipmentCondition getCondition() {
        return condition;
    }

    public void setCondition(EquipmentCondition condition) {
        this.condition = condition;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Helmet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", condition=" + condition +
                ", size=" + size +
                ", assignedEquipmentList=" + assignedEquipmentList +
                '}';
    }
}
