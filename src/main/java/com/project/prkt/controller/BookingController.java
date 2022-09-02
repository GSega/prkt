package com.project.prkt.controller;

import com.project.prkt.model.Booking;
import com.project.prkt.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    // ----- edit -----
    @GetMapping("/edit/{id}")
    public String showOneBooking(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookingToUpdate", bookingService.showOneBookingById(id));
        return "booking/edit";
    }

    @PatchMapping("/{id}")
    public String updateBookingById(@PathVariable("id") Long id,
                                    @ModelAttribute("oneBooking") Booking booking) {
        bookingService.updateBookingById(id, booking);
        return "redirect:/client/booking";
    }

    // ----- delete -----
    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/client/booking";
    }

    // ----- search -----
    @GetMapping("/search")
    public String searchBookingsByParameter(@RequestParam("parameter") String parameter, Model model) {
        model.addAttribute("bookingsByParameter", bookingService.showBookingsByParameter(parameter));
        return "booking/search";
    }

    // ----- sort -----
    @GetMapping("/sort")
    public String sortBookingsByParameter(@RequestParam("parameter") String parameter,
                                          @RequestParam("sortDirection") String sortDirection,
                                          Model model) {
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("allBookings", bookingService.sortAllBookingsByParameter(parameter, sortDirection));
        return "booking/show_all";
    }

    // ----- show_one -----
    @GetMapping("/view/{id}")
    public String showOneBookingById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("foundBooking", bookingService.showOneBookingById(id));
        return "booking/show_one";
    }
}
