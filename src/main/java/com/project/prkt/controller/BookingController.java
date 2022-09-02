package com.project.prkt.controller;

import com.project.prkt.model.Booking;
import com.project.prkt.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Nikolai Khriapov
 */

@Controller
@RequestMapping("/client/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // ----- show all -----
    @GetMapping()
    public String snowAllBookings(Model model) {
        model.addAttribute("allBookings", bookingService.showAllBookings());
        return "booking/show_all";
    }

    // ----- add new -----
    @GetMapping("add-new")
    public String createNewBooking(Model model) {
        model.addAttribute("newBooking", new Booking());
        return "booking/add_new";
    }

    @PostMapping()
    public String addNewBookingToDB(@ModelAttribute("newBooking") Booking booking) {
        bookingService.addNewBookingToDB(booking);
        return "redirect:/client/booking";
    }
}
