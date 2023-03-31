package com.project.prkt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Nikolai Khriapov
 */
@Data
@Entity
public class ProtectiveShorts extends Equipment {

    enum Size {
        XS,
        S,
        M,
        L,
        XL
    }

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @javax.validation.constraints.Size(min = 3, max = 30, message = "{validation.protective_shorts.invalid_name}")
    private String name;
    private EquipmentCondition condition;
    private Size size;
    @OneToMany(mappedBy = "protectiveShorts")
    private List<AssignedEquipment> listOfAssignedEquipment;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtectiveShorts that = (ProtectiveShorts) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && condition == that.condition && size == that.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, condition, size);
    }

    @Override
    public String toString() {
        return "ProtectiveShorts{" +
                "id=" + id +
                ", name='" + name +
                ", condition=" + condition.name() +
                ", size=" + size.name() +
                '}';
    }
}
