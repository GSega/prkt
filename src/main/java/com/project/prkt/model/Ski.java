package com.project.prkt.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ResourceBundle;
@Data
@Entity
@Table
public class Ski extends Equipment{
    public enum Stiffness {
        UNKNOWN,
        SOFT,
        MEDIUM,
        HARD;

        private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("equipment");

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
    private EquipmentCondition condition;
    private String size; //пишем руками.  размер лыж - это цифры от 80 до 200
    private Stiffness stiffness;


    @Override
    public String toString() {
        return "Ski{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", condition=" + condition +
                ", size='" + size + '\'' +
                ", stiffness=" + stiffness +
                '}';
    }
}
