package com.project.prkt.controller;

import com.project.prkt.model.Booking;
import com.project.prkt.model.Rider;
import com.project.prkt.service.BookingService;
import com.project.prkt.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Nikolai Khriapov
 */

@Controller
@RequestMapping("/admin/info-booking")
public class BookingController {

    private final BookingService bookingService;
    private final RiderService riderService;

    @Autowired
    public BookingController(BookingService bookingService, RiderService riderService) {
        this.bookingService = bookingService;
        this.riderService = riderService;
    }

    // ----- show all bookings -----
    @GetMapping()
    public String showAllBookings(Model model) {
        model.addAttribute("allBookings", bookingService.showAllBookings());
        return "booking/show_all";
    }

    // ----- add new booking -----
    @GetMapping("/add-new")
    public String createNewBooking(Model model) {
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("allRiders", riderService.showAllRiders());
        return "booking/add_new";
    }

    @PostMapping()
    public String addNewBookingToDB(@ModelAttribute("newBooking") Booking newBooking,
                                    @ModelAttribute("newRiderId") Long newRiderId) {
        if (newRiderId != 0) {
            newBooking.addToListOfRiders(riderService.showOneRiderById(newRiderId));
        }
        bookingService.addNewBookingToDB(newBooking);
        return "redirect:/client/booking-rider/new-rider?id=" + newBooking.getId(); // change to admin/rider/add-new
    }

    // ----- delete booking -----
    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/admin/info-booking";
    }

    // ----- edit booking info -----
    @GetMapping("/edit/{id}")
    public String showOneBooking(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookingToBeUpdated", bookingService.showOneBookingById(id));
        model.addAttribute("allRiders", riderService.showAllRiders());
        model.addAttribute("riderToBeAddedId", 0);
        return "booking/edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateBookingById(@PathVariable("id") Long bookingToBeUpdatedId,
                                    @ModelAttribute("oneBooking") @Valid Booking updatedBooking) {
        bookingService.updateBookingById(bookingToBeUpdatedId, updatedBooking);
        return "redirect:/admin/info-booking/edit/{id}";
    }

    //// ----- edit booking info / edit rider in booking -----
    //// ----- edit booking info / remove rider from booking -----
    @GetMapping("/edit/remove")
    public String removeRiderFromBooking(@RequestParam("bid") Long bookingToBeUpdatedId,
                                         @RequestParam("rid") Long riderToBeRemovedId) {
        Booking bookingToBeUpdated = bookingService.showOneBookingById(bookingToBeUpdatedId);
        Rider riderToBoUpdated = riderService.showOneRiderById(riderToBeRemovedId);
        bookingService.removeRiderFromBooking(bookingToBeUpdated, riderToBoUpdated);
        return "redirect:/admin/info-booking/edit/" + bookingToBeUpdatedId;
    }

    //// ----- edit booking info / add existing rider to booking -----

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


}
