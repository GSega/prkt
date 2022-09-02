package com.project.prkt.controller;

import com.project.prkt.model.Booking;
import com.project.prkt.service.BookingService;
import com.project.prkt.service.SnowboardService;
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
    private final SnowboardService snowboardService;

    @Autowired
    public BookingController(BookingService bookingService, SnowboardService snowboardService) {
        this.bookingService = bookingService;
        this.snowboardService = snowboardService;
    }

    // ----- show all -----
    @GetMapping()
    public String snowAllBookings(Model model) {
        model.addAttribute("allBookings", bookingService.showAllBookings());
        return "booking/show_all";
    }

    // ----- add new booking -----
    @GetMapping("/add-new")
    public String createNewBooking(Model model) {
        model.addAttribute("newBooking", new Booking());
        model.addAttribute("allSnowboards", snowboardService.showAllSnowboards());
        return "booking/add_new";
    }

    @PostMapping()
    public String addNewBookingToDB(@ModelAttribute("newBooking") Booking newBooking,
                                    @ModelAttribute("newSnowboardId") Long newSnowboardId) {
        if (newSnowboardId != 0) {
            newBooking.addToListOfSnowboards(snowboardService.showOneSnowboardById(newSnowboardId));
        }
        bookingService.addNewBookingToDB(newBooking);
        return "redirect:/admin/info-booking";
    }


    // ----- edit -----
    @GetMapping("/edit/{id}")
    public String showOneBooking(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookingToBeUpdated", bookingService.showOneBookingById(id));
        model.addAttribute("allSnowboards", snowboardService.showAllSnowboards());
        return "booking/edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateBookingById(@PathVariable("id") Long bookingToBeUpdatedId,
                                    @ModelAttribute("oneBooking") @Valid Booking updatedBooking,
                                    @ModelAttribute("newSnowboardId") Long newSnowboardId) {
        bookingService.updateBookingById(bookingToBeUpdatedId, updatedBooking, snowboardService.showOneSnowboardById(newSnowboardId));
        return "redirect:/admin/info-booking";
    }

    // ----- delete -----
    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/admin/info-booking";
    }

    // ----- show_one -----
    @GetMapping("/show-one/{id}")
    public String showOneBookingById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookingFound", bookingService.showOneBookingById(id));
        return "booking/show_one";
    }
//
//    // ----- search -----
//    @GetMapping("/search")
//    public String searchBookingsByParameter(@RequestParam("parameter") String parameter, Model model) {
//        model.addAttribute("bookingsByParameter", bookingService.showBookingsByParameter(parameter));
//        return "booking/search";
//    }
//
//    // ----- sort -----
//    @GetMapping("/sort")
//    public String sortBookingsByParameter(@RequestParam("parameter") String parameter,
//                                          @RequestParam("sortDirection") String sortDirection,
//                                          Model model) {
//        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
//        model.addAttribute("allBookings", bookingService.sortAllBookingsByParameter(parameter, sortDirection));
//        return "booking/show_all";
//    }


}
