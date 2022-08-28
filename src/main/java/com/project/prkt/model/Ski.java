package com.project.prkt.model;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Ski extends Equipment{
    public enum Stiffness {SOFT, MEDIUM, HARD}
    //public enum Size {SOFT, MEDIUM, HARD} //вряд ли будет полезно делать Size через enum
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //присваивается автоматически
    private Long id;
    private String name;
    private boolean available;
    private EquipmentCondition condition;
    private String size; //пишем руками.  размер лыж - это цифры от 80 до 200
    private Ski.Stiffness stiffness;

    public Ski() {
    }

    public Ski(String name, boolean available, EquipmentCondition condition, String size, Stiffness stiffness) {
        this.name = name;
        this.available = available;
        this.condition = condition;
        this.size = size;
        this.stiffness = stiffness;
    }
    //ниже стандартные геттеры,сеттеры,equals,hashcode,toString
    //поле id только геттер
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public EquipmentCondition getCondition() {
        return condition;
    }

    public void setCondition(EquipmentCondition condition) {
        this.condition = condition;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Stiffness getStiffness() {
        return stiffness;
    }

    public void setStiffness(Stiffness stiffness) {
        this.stiffness = stiffness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ski ski)) return false;
        return available == ski.available && id.equals(ski.id) && Objects.equals(name, ski.name) && condition == ski.condition && Objects.equals(size, ski.size) && stiffness == ski.stiffness;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, available, condition, size, stiffness);
    }

    @Override
    public String toString() {
        return "Ski{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", condition=" + condition +
                ", size='" + size + '\'' +
                ", stiffness=" + stiffness +
                '}';
    }
}
