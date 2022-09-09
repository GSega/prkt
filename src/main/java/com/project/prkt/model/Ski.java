package com.project.prkt.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ResourceBundle;
import java.util.Objects;
import com.project.prkt.model.EquipmentCondition;

@Entity
@Table
public class Ski extends Equipment{
    public enum Stiffness {
        UNKNOWN("Н.У."),
        SOFT("мягкие"),
        MEDIUM ("средней жесткости"),
        HARD("жесткие");

        private final String displayValue;
        Stiffness(String displayValue){
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }
    //public enum Size {} //вряд ли будет полезно делать Size через enum
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @Size(min = 3, max = 30, message = "Имя должно содержать от 3 до 30 символов")
    private String name;
    private boolean available;
    private EquipmentCondition condition;
    private String size; //пишем руками.  размер лыж - это цифры от 80 до 200
    private Stiffness stiffness;

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
        return available == ski.available && id.equals(ski.id) && name.equals(ski.name) && condition == ski.condition && size.equals(ski.size) && stiffness == ski.stiffness;
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
