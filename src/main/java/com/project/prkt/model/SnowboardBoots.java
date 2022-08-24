package com.project.prkt.model;

import java.util.Objects;

enum Stiffness {SOFT, MEDIUM, HARD}

public class SnowboardBoots extends Boots {
    private Long id;
    private Stiffness stiffness;

    public SnowboardBoots() {
    }

    public SnowboardBoots(Long id, String equipmentName, boolean available, EquipmentCondition condition, Size size, Stiffness stiffness) {
        this.id = id;
        this.equipmentName = equipmentName;
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

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
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
                equipmentName.equals(that.equipmentName) && condition == that.condition &&
                size == that.size && stiffness == that.stiffness;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equipmentName, available, condition, size, stiffness);
    }

    @Override
    public String toString() {
        return "SnowboardBoots{" + "stiffness=" + stiffness + ", size=" + size + ", id=" + id +
                ", equipmentName='" + equipmentName + ", available=" + available + ", condition=" + condition + "}";
    }
}
