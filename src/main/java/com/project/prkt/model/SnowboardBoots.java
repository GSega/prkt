package com.project.prkt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Nikolai Khriapov
 */
@Data
@Entity
public class SnowboardBoots extends Equipment {

    public enum Stiffness {
        UNKNOWN(),
        SOFT(),
        MEDIUM(),
        HARD();

        private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("equipment");

        public String toString() {
            return resourceBundle.getString("snowboard_boots.stiffness." + name());
        }
    }

    public enum Size {
        OTHER,
        RU36_EU37_MM235,
        RU34_EU35_MM240,
        RU37_EU38_MM245,
        RU38_EU39_MM250,
        RU39_EU40_MM255,
        RU40_EU41_MM260,
        RU41_EU42_MM265,
        RU415_EU425_MM275,
        RU42_EU43_MM275,
        RU425_EU435_MM280,
        RU43_EU44_MM285,
        RU44_EU45_MM290,
        RU45_EU46_MM300,
        RU46_EU47_MM310,
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

        private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("equipment");

        public String toString() {
            return resourceBundle.getString("snowboard_boots.size." + name());
        }
    }

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @javax.validation.constraints.Size(min = 3, max = 30, message = "{validation.snowboard_boots.invalid_name}")
    private String name;
    private EquipmentCondition condition;
    private Size size;
    private Stiffness stiffness;
    @OneToMany(mappedBy = "snowboardBoots")
    private List<AssignedEquipment> listOfAssignedEquipment;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnowboardBoots that = (SnowboardBoots) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && condition == that.condition &&
                size == that.size && stiffness == that.stiffness;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, condition, size, stiffness);
    }

    @Override
    public String toString() {
        return "SnowboardBoots{" +
                "id=" + id +
                ", name='" + name +
                ", condition=" + condition.name() +
                ", size=" + size.name() +
                ", stiffness=" + stiffness.name() +
                '}';
    }
}
