package com.project.prkt.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

public class BookingCreationRequest {
    private Long clientNumber;
    private Long bookingNumber;
    @NotEmpty(message = "{client.message.invalid_surname}")
    private String surname;
    @Pattern(regexp = "[\\d]\\([\\d]{3}\\)[\\d]{3}-[\\d]{2}-[\\d]{2}", message = "{client.message.invalid_phone_number}")
    private String phone1;
    private String phone2;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{client.message.invalid_date}")
    private Date dateOfArrival;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{client.message.invalid_date}")
    private Date dateOfReturn;
    private List<Rider> listOfRiders;

    public BookingCreationRequest() {
    }

    public BookingCreationRequest(Long clientNumber, Long bookingNumber, String surname, String phone1, String phone2, Date dateOfArrival, Date dateOfReturn, List<Rider> listOfRiders) {
        this.clientNumber = clientNumber;
        this.bookingNumber = bookingNumber;
        this.surname = surname;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.dateOfArrival = dateOfArrival;
        this.dateOfReturn = dateOfReturn;
        this.listOfRiders = listOfRiders;
    }

    public Long getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(Long clientNumber) {
        this.clientNumber = clientNumber;
    }

    public Long getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(Long bookingNumber) {
        this.bookingNumber = bookingNumber;
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

    public List<Rider> getListOfRiders() {
        return listOfRiders;
    }

    public void setListOfRiders(List<Rider> listOfRiders) {
        this.listOfRiders = listOfRiders;
    }

    @Override
    public String toString() {
        return "BookingCreationRequest{" +
                "clientNumber=" + clientNumber +
                ", bookingNumber=" + bookingNumber +
                ", surname='" + surname + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", dateOfArrival=" + dateOfArrival +
                ", dateOfReturn=" + dateOfReturn +
                ", listOfRiders=" + listOfRiders +
                '}';
    }
}
