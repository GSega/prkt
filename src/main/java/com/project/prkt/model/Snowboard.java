package com.project.prkt.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Nikolai Khriapov
 */

@Entity
@Table
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
    private boolean available;
    private EquipmentCondition condition;
    @Pattern(regexp = "(1[0-6][0-9]|170)([w|W]?)", message = "{snowboard.message.invalid_size}")
    private String size;
    private Stiffness stiffness;
    private Arch arch;
    private BindingSize bindingSize;
    //    @JoinColumn(name = "bookingId")
    @ManyToMany(mappedBy = "listOfSnowboards")
    private List<Booking> listOfBookings;

    public Snowboard() {
    }

    public Snowboard(String name, boolean available, EquipmentCondition condition, String size, Stiffness stiffness, Arch arch, BindingSize bindingSize, List<Booking> listOfBookings) {
        this.name = name;
        this.available = available;
        this.condition = condition;
        this.size = size;
        this.stiffness = stiffness;
        this.arch = arch;
        this.bindingSize = bindingSize;
        this.listOfBookings = listOfBookings;
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

    public List<Booking> getListOfBookings() {
        return listOfBookings;
    }

    public void setListOfBookings(List<Booking> listOfBookings) {
        this.listOfBookings = listOfBookings;
    }

    public void addToListOfBookings(Booking booking) {
        if (listOfBookings == null) {
            listOfBookings = new ArrayList<>();
        }
        this.listOfBookings.add(booking);
    }

    @Override
    public String toString() {
        return "Snowboard -> " +
                "id: " + id +
                ", name: '" + name +
                ", available: " + (available ? "available" : "not available") +
                ", condition: " + condition.toString() +
                ", size: " + size +
                ", stiffness: " + stiffness.toString() +
                ", arch: " + arch.toString() +
                ", bindingSize: " + bindingSize.toString();
    }
}
