package com.project.prkt.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class SkiBoots extends Equipment {

    public enum Stiffness {
        UNKNOWN(),
        SOFT(),
        MEDIUM(),
        HARD(),
        s40(),
        s50(),
        s60(),
        s70(),
        s80(),
        s90(),
        s100(),
    }

    public enum Size {
        OTHER,
        RU36_EU37_MM235,
        RU37_EU38_MM245,
        RU39_EU40_MM255,
        RU40_EU41_MM260,
        RU41_EU42_MM265,
        RU42_EU43_MM275,
        RU43_EU44_MM285,
        RU44_EU45_MM295,
        RU45_EU46_MM305,
        RU46_EU47_MM315,
        JUNIOR26_MM165,
        JUNIOR28_MM175,
        JUNIOR30_MM185,
        JUNIOR31_MM195,
        JUNIOR32_MM205,
        JUNIOR33_MM210,
        JUNIOR34_MM215,
        JUNIOR35_MM225
    }

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @javax.validation.constraints.Size(min = 3, max = 30, message = "{snowboard_boots.message.invalid_name}")
    private String name;
    private boolean available;
    private EquipmentCondition condition;
    private SnowboardBoots.Size size;
    private SnowboardBoots.Stiffness stiffness;

    public SkiBoots(String name, boolean available, EquipmentCondition condition, SnowboardBoots.Size size, SnowboardBoots.Stiffness stiffness) {
        this.name = name;
        this.available = available;
        this.condition = condition;
        this.size = size;
        this.stiffness = stiffness;
    }

    public SkiBoots() {
    }

    //below are getter for id field and getters and setters for thr rest of fields
    //and standard equals, hashCode, toString

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

    public SnowboardBoots.Size getSize() {
        return size;
    }

    public void setSize(SnowboardBoots.Size size) {
        this.size = size;
    }

    public SnowboardBoots.Stiffness getStiffness() {
        return stiffness;
    }

    public void setStiffness(SnowboardBoots.Stiffness stiffness) {
        this.stiffness = stiffness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkiBoots skiBoots)) return false;
        return available == skiBoots.available && id.equals(skiBoots.id) && name.equals(skiBoots.name) && condition == skiBoots.condition && size == skiBoots.size && stiffness == skiBoots.stiffness;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, available, condition, size, stiffness);
    }

    @Override
    public String toString() {
        return "SkiBoots{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", condition=" + condition +
                ", size=" + size +
                ", stiffness=" + stiffness +
                '}';
    }
}
