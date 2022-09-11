package com.project.prkt.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ResourceBundle;

@Entity
@Table
public class Ski extends Equipment{
    public enum Stiffness {
        UNKNOWN,
        SOFT,
        MEDIUM,
        HARD;

        private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("ski");

        public String toString() {
            return resourceBundle.getString("ski.stiffness." + name());
        }
        }

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    //@NotEmpty(message = "type something!")
    @Size(min = 3, max = 30, message = "{ski.message.invalid_name}")
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
