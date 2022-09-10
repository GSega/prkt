package com.project.prkt.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

/**
 * @author Nikolai Khriapov
 */

@Entity
public class Booking {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfArrival;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfReturn;
    private boolean completed;
    @ManyToMany
    @JoinTable(
            name = "booking_rider",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "rider_id")
    )
    private List<Rider> listOfRiders;

    public Booking() {
    }

    public Booking(Client client, Date dateOfArrival, Date dateOfReturn) {
        this.client = client;
        this.dateOfArrival = dateOfArrival;
        this.dateOfReturn = dateOfReturn;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Rider> getListOfRiders() {
        return listOfRiders;
    }

    public void addToListOfRiders(Rider rider) {
        if (listOfRiders == null) {
            listOfRiders = new ArrayList<>();
        }
        this.listOfRiders.add(rider);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", client=" + client +
                ", dateOfArrival=" + dateOfArrival +
                ", dateOfReturn=" + dateOfReturn +
                ", completed=" + completed +
                ", listOfRiders=" + listOfRiders +
                '}';
    }
}
