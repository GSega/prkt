package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.Client;
import com.project.prkt.model.Rider;
import com.project.prkt.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Nikolai Khriapov
 */

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // ----- show all bookings -----
    public List<Booking> showAllBookings() {
        return bookingRepository.findAllByOrderById();
    }

    // ----- add new booking -----
    public void addNewClientInfoToNewBooking(Booking newBooking, Client newClient) {
        newBooking.setClient(newClient);
    }

    public void addNewBookingToDB(Booking booking) {
        bookingRepository.save(booking);
    }

    // ----- delete booking -----
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    public void addNewRiderToBooking(Long id, Rider rider) {
        Booking bookingToBeUpdated = bookingRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Booking with id = " + id + " not found!"));
        bookingToBeUpdated.addToListOfRiders(rider);
        bookingRepository.save(bookingToBeUpdated);
    }

    // ----- edit booking info -----
    public Booking showOneBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Booking with id = " + id + " not found!"));
    }

    public void updateBookingById(Long bookingToBeUpdatedId, Booking updatedBooking) {
        Booking bookingToBeUpdated = showOneBookingById(bookingToBeUpdatedId);
        bookingToBeUpdated.setClient(updatedBooking.getClient());
        bookingToBeUpdated.setDateOfArrival(updatedBooking.getDateOfArrival());
        bookingToBeUpdated.setDateOfReturn(updatedBooking.getDateOfReturn());
        bookingRepository.save(bookingToBeUpdated);
    }

    //// ----- edit booking info / edit rider in booking -----
    //// ----- edit booking info / remove rider from booking -----
    public void removeRiderFromBooking(Booking bookingToBeUpdated, Rider riderToBeRemoved) {
        bookingToBeUpdated.getListOfRiders().remove(riderToBeRemoved);
        bookingRepository.save(bookingToBeUpdated);
    }

    //// ----- edit booking info / add rider to booking -----
    public void addExistingRiderToBooking(Booking bookingToBeUpdated, Rider riderToBoAdded) {
        for (Rider oneRider : bookingToBeUpdated.getListOfRiders()) {
            if (oneRider.getId().equals(riderToBoAdded.getId())) {
                return;
            }
        }
        bookingToBeUpdated.getListOfRiders().add(riderToBoAdded);
        bookingRepository.save(bookingToBeUpdated);
    }

    // ----- search -----
    public List<Booking> showBookingsByParameter(String parameter) {
        List<Booking> searchResult = new ArrayList<>();
        List<Booking> allBookings = bookingRepository.findAll();
        for (Booking oneBooking : allBookings) {
            if (oneBooking.getClient().getSurname().toLowerCase().contains(parameter.toLowerCase()) ||
                    oneBooking.getClient().getPhone1().contains(parameter) ||
                    oneBooking.getClient().getPhone2().contains(parameter)) {
                searchResult.add(oneBooking);
            }
        }
        return searchResult;
    }

    // ----- sort -----
    public List<Booking> sortAllBookingsByParameter(String parameter, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(parameter).ascending() : Sort.by(parameter).descending();
        return bookingRepository.findAll(sort);
    }

    // ----- show bookings for the date
    public List<Booking> showBookingsForTheDate(Date date) {
        return bookingRepository.findByDateOfArrival(date);
    }

    // ----- show incomplete bookings -----
    public List<Booking> showAllIncompleteBookings() {
        return bookingRepository.findAllByCompletedFalseOrderByDateOfArrivalAsc();
    }

    // ----- mark booking completed -----
    public void markBookingCompleted(Long bookingId) {
        Booking bookingToChangeCompleted = bookingRepository.findById(bookingId).orElseThrow(() ->
                new IllegalStateException("Booking with id = " + bookingId + " not found!"));
        bookingToChangeCompleted.setCompleted(bookingToChangeCompleted.isCompleted() ? false : true);
        bookingRepository.save(bookingToChangeCompleted);
    }
}
