package com.project.prkt.model;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @author Nikolai Khriapov
 */

@Entity
public class Client {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;
    @NotEmpty(message = "{client.message.invalid_surname}")
    private String surname;
    @Pattern(regexp = "[\\d]\\([\\d]{3}\\)[\\d]{3}-[\\d]{2}-[\\d]{2}", message = "{client.message.invalid_phone_number}")
    private String phone1;
    private String phone2;
    @OneToOne(mappedBy = "client")
    private Booking booking;

    public Client() {
    }

    public Client(String surname, String phone1, String phone2) {
        this.surname = surname;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
}
