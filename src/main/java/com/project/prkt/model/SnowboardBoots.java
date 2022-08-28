package com.project.prkt.model;

import javax.persistence.*;
import java.util.Objects;

/*
 * specify values for "enum Size" - DONE
 */

@Entity
@Table
public class SnowboardBoots extends Equipment {
    public enum Stiffness {SOFT, MEDIUM, HARD}

    public enum Size {
        RU36_EU37_MM235("RU36/EU37/235mm"),
        RU34_EU35_MM240("RU34/EU35/240mm"),
        RU37_EU38_MM245("RU37/EU38/245mm"),
        RU38_EU39_MM250("RU38/EU39/250mm"),
        RU39_EU40_MM255("RU39/EU40/255mm"),
        RU40_EU41_MM260("RU40/EU41/260mm"),
        RU41_EU42_MM265("RU41/EU42/265mm"),
        RU415_EU425_MM275("RU41.5/EU42.5/275mm"),
        RU42_EU43_MM275("RU42/EU43/275mm"),
        RU425_EU435_MM280("RU42.5/EU43.5/280mm"),
        RU43_EU44_MM285("RU43/EU44/285mm"),
        RU44_EU45_MM290("RU44/EU45/290mm"),
        RU45_EU46_MM300("RU45/EU46/300mm"),
        RU46_EU47_MM310("RU46/EU47/310mm"),
        DETSK26_MM165("Junior 26/165mm"),
        DETSK28_MM175("Junior 28/175mm"),
        DETSK30_MM185("Junior 30/185mm"),
        DETSK31_MM195("Junior 31/195mm"),
        DETSK32_MM205("Junior 32/205mm"),
        DETSK33_MM210("Junior 33/210mm"),
        DETSK34_MM215("Junior 34/215mm"),
        DETSK35_MM225("Junior 35/225mm"),
        OTHER("Other");

        private final String displayValue;

        Size(String displayValue) {
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }

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
                ", size: " + size.getDisplayValue() +
                ", stiffness: " + stiffness;
    }
}
