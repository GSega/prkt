package com.project.prkt.service;

import com.project.prkt.model.Booking;
import com.project.prkt.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Booking> showAllBookings() {
        return bookingRepository.findAllByOrderById();
    }

    public void addNewBookingToDB(Booking booking) {
        bookingRepository.save(booking);
    }
}
