package com.project.prkt.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

/**
 * @author Nikolai Khriapov
 */

@Entity
@Table
public class Booking {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    private String bookingSurname;
    private String phone1; // validation for different formats
    private String phone2;
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

    public Booking(String bookingSurname, String phone1, String phone2, Date dateOfArrival,
                   Date dateOfReturn, boolean completed, List<Rider> listOfRiders) {
        this.bookingSurname = bookingSurname;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.dateOfArrival = dateOfArrival;
        this.dateOfReturn = dateOfReturn;
        this.completed = completed;
        this.listOfRiders = listOfRiders;
    }

    public Long getId() {
        return id;
    }

    public String getBookingSurname() {
        return bookingSurname;
    }

    public void setBookingSurname(String bookingSurname) {
        this.bookingSurname = bookingSurname;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
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
                ", bookingSurname='" + bookingSurname +
                ", phone1='" + phone1 +
                ", phone2='" + phone2 +
                ", dateOfArrival=" + dateOfArrival +
                ", dateOfReturn=" + dateOfReturn +
                ", completed=" + completed +
                ", listOfRiders=" + listOfRiders +
                '}';
    }
}
