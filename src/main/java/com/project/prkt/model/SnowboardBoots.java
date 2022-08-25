package com.project.prkt.model;

import javax.persistence.*;
import java.util.Objects;

/*
 * specify values for "enum Size"
 */

@Entity
@Table
public class SnowboardBoots extends Equipment {
    public enum Stiffness {SOFT, MEDIUM, HARD}
    public enum Size {RU43_EU44_MM285, MEDIUM, LARGE} //change

    @Id
    private Long id;
    private String name;
    private boolean available;
    private EquipmentCondition condition;
    private Size size;
    private Stiffness stiffness;

    public SnowboardBoots() {
    }

    public SnowboardBoots(Long id, String name, boolean available, EquipmentCondition condition, Size size, Stiffness stiffness) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.condition = condition;
        this.size = size;
        this.stiffness = stiffness;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
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
        if (o == null || getClass() != o.getClass()) return false;
        SnowboardBoots that = (SnowboardBoots) o;
        return id.equals(that.id) && available == that.available &&
                name.equals(that.name) && condition == that.condition &&
                size == that.size && stiffness == that.stiffness;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, available, condition, size, stiffness);
    }

    @Override
    public String toString() {
        return "Snowboard Boots -> " +
                "id: " + id +
                ", name: " + name +
                ", available: " + (available ? "available" : "not available") +
                ", condition: " + condition +
                ", size: " + size +
                ", stiffness: " + stiffness;
    }
}
