package com.project.prkt.model;

import javax.persistence.*;

@Entity
public class AssignedEquipment {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @OneToOne(mappedBy = "assignedEquipment")
    private Rider rider;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Snowboard snowboard;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private SnowboardBoots snowboardBoots;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Ski ski;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private SkiBoots skiBoots;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Jacket jacket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Snowboard getSnowboard() {
        if (snowboard == null) {
            return new Snowboard();
        }
        return snowboard;
    }

    public void setSnowboard(Snowboard snowboard) {
        this.snowboard = snowboard;
    }

    public SnowboardBoots getSnowboardBoots() {
        if (snowboardBoots == null) {
            return new SnowboardBoots();
        }
        return snowboardBoots;
    }

    public void setSnowboardBoots(SnowboardBoots snowboardBoots) {
        this.snowboardBoots = snowboardBoots;
    }

    public Ski getSki() {
        if (ski == null) {
            return new Ski();
        }
        return ski;
    }

    public void setSki(Ski ski) {
        this.ski = ski;
    }

    public SkiBoots getSkiBoots() {
        if (skiBoots == null) {
            return new SkiBoots();
        }
        return skiBoots;
    }

    public void setSkiBoots(SkiBoots skiBoots) {
        this.skiBoots = skiBoots;
    }

    public Jacket getJacket() {
        if (jacket == null) {
            return new Jacket();
        }
        return jacket;
    }

    public void setJacket(Jacket jacket) {
        this.jacket = jacket;
    }

    @Override
    public String toString() {
        return "AssignedEquipment{" +
                ", " + snowboard.toString() +
                ", " + snowboardBoots.toString() +
                ", " + ski.toString() +
                ", " + skiBoots.toString() +
                ", " + jacket.toString() +
                '}';
    }
}
