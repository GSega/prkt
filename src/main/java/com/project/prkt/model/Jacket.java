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
public class Jacket extends Equipment {

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
    @javax.validation.constraints.Size(min = 3, max = 30, message = "{validation.jacket.invalid_name}")
    private String name;
    private EquipmentCondition condition;
    private Size size;
    @OneToMany(mappedBy = "jacket")
    private List<AssignedEquipment> listOfAssignedEquipment;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jacket jacket = (Jacket) o;
        return Objects.equals(id, jacket.id) && Objects.equals(name, jacket.name) && condition == jacket.condition && size == jacket.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, condition, size);
    }

    @Override
    public String toString() {
        return "Jacket{" +
                "id=" + id +
                ", name='" + name +
                ", condition=" + condition.name() +
                ", size=" + size.name() +
                '}';
    }
}
