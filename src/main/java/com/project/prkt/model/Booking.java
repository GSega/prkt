package com.project.prkt.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String phone1;
    private String phone2;
    private String dateOfArrival;
    private String dateOfReturn;
//    private List<Rider> listOfRiders;
    @OneToMany(mappedBy = "booking")
    @Column(name = "list_of_snowboards")
    private List<Snowboard> listOfSnowboards;

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

    public String getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(String dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public String getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public List<Snowboard> getListOfSnowboards() {
        return listOfSnowboards;
    }

    public void setListOfSnowboards(List<Snowboard> listOfSnowboards) {
        this.listOfSnowboards = listOfSnowboards;
    }

    public void addToListOfSnowboards(Snowboard snowboard) {
        this.listOfSnowboards.add(snowboard);
    }
}
