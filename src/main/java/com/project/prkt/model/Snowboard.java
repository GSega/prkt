package com.project.prkt.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Nikolai Khriapov
 */

@Data
@Entity
public class Snowboard extends Equipment {

    enum Stiffness {
        UNKNOWN,
        SOFT,
        MEDIUM,
        HARD;

        private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("equipment");

        @Override
        public String toString() {
            return resourceBundle.getString("snowboard.stiffness." + name());
        }
    }

    enum Arch {
        UNKNOWN,
        CAMBER,
        FLAT,
        ROCKER;

        private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("equipment");

        @Override
        public String toString() {
            return resourceBundle.getString("snowboard.arch." + name());
        }
    }

    enum BindingSize {
        S,
        M,
        L
    }

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @Size(min = 3, max = 30, message = "{validation.snowboard.invalid_name}")
    private String name;
    private EquipmentCondition condition;
    @Pattern(regexp = "(1[0-6][0-9]|170)([w|W]?)", message = "{validation.snowboard.invalid_size}")
    private String size;
    private Stiffness stiffness;
    private Arch arch;
    private BindingSize bindingSize;
    @OneToMany(mappedBy = "snowboard")
    private List<AssignedEquipment> listOfAssignedEquipment;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snowboard snowboard = (Snowboard) o;
        return Objects.equals(id, snowboard.id) && Objects.equals(name, snowboard.name) &&
                condition == snowboard.condition && Objects.equals(size, snowboard.size) &&
                stiffness == snowboard.stiffness && arch == snowboard.arch && bindingSize == snowboard.bindingSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, condition, size, stiffness, arch, bindingSize);
    }

    @Override
    public String toString() {
        return "Snowboard{" +
                "id=" + id +
                ", name='" + name +
                ", condition=" + condition.name() +
                ", size='" + size +
                ", stiffness=" + stiffness.name() +
                ", arch=" + arch.name() +
                ", bindingSize=" + bindingSize.name() +
                '}';
    }
}
