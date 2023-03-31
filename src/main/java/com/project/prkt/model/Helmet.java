package com.project.prkt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Helmet extends Equipment{
    public enum Size{XS, S, M, L, XL}

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


    public List<AssignedEquipment> getAssignedEquipmentList() {
        if(assignedEquipmentList == null){
            return new ArrayList<>();
        }
        return assignedEquipmentList;
    }

    public void setAssignedEquipmentList(List<AssignedEquipment> assignedEquipmentList) {
        this.assignedEquipmentList = assignedEquipmentList;
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
