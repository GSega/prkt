package com.project.prkt.model;

import javax.persistence.*;
import java.util.ResourceBundle;

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
        s100();

        private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("ski_boots");

        public String toString() {
            return resourceBundle.getString("ski_boots.stiffness." + name());
        }
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
        JUNIOR_26_MM165,
        JUNIOR_28_MM175,
        JUNIOR_30_MM185,
        JUNIOR_31_MM195,
        JUNIOR_32_MM205,
        JUNIOR_33_MM210,
        JUNIOR_34_MM215,
        JUNIOR_35_MM225,
        JUNIOR_36_MM235,
        JUNIOR_37_MM245,
        JUNIOR_39_MM255;

        private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("ski_boots");

        public String toString() {
            return resourceBundle.getString("ski_boots.size." + name());
        }
    }

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @javax.validation.constraints.Size(min = 3, max = 30, message = "{validation.ski_boots.invalid_name}")
    private String name;
    private EquipmentCondition condition;
    private SkiBoots.Size size;
    private SkiBoots.Stiffness stiffness;

    public SkiBoots() {
    }

    //below are getter for id field and getters and setters for the rest of the fields
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

    public EquipmentCondition getCondition() {
        return condition;
    }

    public void setCondition(EquipmentCondition condition) {
        this.condition = condition;
    }

    public SkiBoots.Size getSize() {
        return size;
    }

    public void setSize(SkiBoots.Size size) {
        this.size = size;
    }

    public SkiBoots.Stiffness getStiffness() {
        return stiffness;
    }

    public void setStiffness(SkiBoots.Stiffness stiffness) {
        this.stiffness = stiffness;
    }

    @Override
    public String toString() {
        return "SkiBoots{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", condition=" + condition +
                ", size=" + size +
                ", stiffness=" + stiffness +
                '}';
    }
}
