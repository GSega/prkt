package com.project.prkt.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nikolai Khriapov
 */

@Entity
public class Snowboard extends Equipment {

    enum Stiffness {
        UNKNOWN,
        SOFT,
        MEDIUM,
        HARD;

        private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("snowboard");

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

        private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("snowboard");

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
    @Size(min = 3, max = 30, message = "{snowboard.message.invalid_name}")
    private String name;
    private EquipmentCondition condition;
    @Pattern(regexp = "(1[0-6][0-9]|170)([w|W]?)", message = "{snowboard.message.invalid_size}")
    private String size;
    private Stiffness stiffness;
    private Arch arch;
    private BindingSize bindingSize;
    @OneToMany(mappedBy = "snowboard")
    private List<AssignedEquipment> listOfAssignedEquipment;

    public Snowboard() {
    }

    public Snowboard(String name, EquipmentCondition condition, String size, Stiffness stiffness, Arch arch, BindingSize bindingSize) {
        this.name = name;
        this.condition = condition;
        this.size = size;
        this.stiffness = stiffness;
        this.arch = arch;
        this.bindingSize = bindingSize;
    }

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

    public Arch getArch() {
        return arch;
    }

    public void setArch(Arch arch) {
        this.arch = arch;
    }

    public BindingSize getBindingSize() {
        return bindingSize;
    }

    public void setBindingSize(BindingSize bindingSize) {
        this.bindingSize = bindingSize;
    }

    @Override
    public String toString() {
        return "Snowboard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", condition=" + condition +
                ", size='" + size + '\'' +
                ", stiffness=" + stiffness +
                ", arch=" + arch +
                ", bindingSize=" + bindingSize +
                '}';
    }
}
