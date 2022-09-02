package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.model.Snowboard;
import com.project.prkt.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // ----- show all -----
    public List<Booking> showAllBookings() {
        return bookingRepository.findAllByOrderById();
    }

    // ----- add new -----
    public void addNewBookingToDB(Booking booking) {
        bookingRepository.save(booking);
    }

    public void addNewSnowboardToBooking(Long id, Snowboard snowboard) {
        Booking bookingToBeUpdated = bookingRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Booking with id = " + id + " not found!"));
        bookingToBeUpdated.addToListOfSnowboards(snowboard);
        bookingRepository.save(bookingToBeUpdated);
        System.out.println("Snowboards: " + bookingToBeUpdated.getListOfSnowboards().size());
    }

    // ----- edit -----
    public Booking showOneBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Booking with id = " + id + " not found!"));
    }

    public void updateBookingById(Long bookingToBeUpdatedId, Booking updatedBooking, Snowboard updatedSnowboard) {
        Booking bookingToBeUpdated = showOneBookingById(bookingToBeUpdatedId);

        bookingToBeUpdated.setBookingSurname(updatedBooking.getBookingSurname());
        bookingToBeUpdated.setPhone1(updatedBooking.getPhone1());
        bookingToBeUpdated.setPhone2(updatedBooking.getPhone2());
        bookingToBeUpdated.setDateOfArrival(updatedBooking.getDateOfArrival());
        bookingToBeUpdated.setDateOfReturn(updatedBooking.getDateOfReturn());
        bookingToBeUpdated.getListOfSnowboards().clear();
        bookingToBeUpdated.addToListOfSnowboards(updatedSnowboard);

        bookingRepository.save(bookingToBeUpdated);
    }

    // ----- delete -----
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    // ----- search -----
    public List<Booking> showBookingsByParameter(String parameter) {
        List<Booking> searchResult = new ArrayList<>();
        for (Booking oneBooking : bookingRepository.findAllByBookingSurnameContaining(parameter)) {
            if (!searchResult.contains(oneBooking)) {
                searchResult.add(oneBooking);
            }
        }
        for (Booking oneBooking : bookingRepository.findAllByPhone1Containing(parameter)) {
            if (!searchResult.contains(oneBooking)) {
                searchResult.add(oneBooking);
            }
        }
        for (Booking oneBooking : bookingRepository.findAllByPhone2Containing(parameter)) {
            if (!searchResult.contains(oneBooking)) {
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
}
