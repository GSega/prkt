package com.project.prkt.controller;

import com.project.prkt.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Nikolai Khriapov
 * @author Sergei Gavrilov
 */

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    private final BookingService bookingService;

    @Autowired
    public AdminHomeController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // admin home page
    @GetMapping()
    public String showAdminMainPage(Model model) {
        model.addAttribute("allBookings", bookingService.showAllIncompleteBookings());
        return "admin/main_page";
    }

    // hyperlink to equipment
    @GetMapping("/info-equipment")
    public String showEquipmentChoicePage() {
        return "admin/info_equipment";
    }

    // ----- bookings for today -----
    @GetMapping("/show-today")
    public String showBookingsForToday(Model model) {
        Date todayFrom = bookingService.getToday()[0];
        Date todayTo = bookingService.getToday()[1];

        model.addAttribute("date", todayFrom);
        model.addAttribute("bookingsForTheDate", bookingService.showBookingsForTheDate(todayFrom, todayTo));
        return "admin/bookings_by_date";
    }

    // ----- bookings for tomorrow -----
    @GetMapping("/show-tomorrow")
    public String showBookingsForTomorrow(Model model) {
        Date tomorrowFrom = bookingService.getTomorrow()[0];
        Date tomorrowTo = bookingService.getTomorrow()[1];

        model.addAttribute("date", tomorrowFrom);
        model.addAttribute("bookingsForTheDate", bookingService.showBookingsForTheDate(tomorrowFrom, tomorrowTo));
        return "admin/bookings_by_date";
    }

    // ----- mark booking completed -----
    @GetMapping("/change-booking-completed/{id}")
    public String changeBookingCompleted(@PathVariable("id") Long bookingId) {
        bookingService.markBookingCompleted(bookingId);
        return "redirect:/admin";
    }

    // ----- delete booking -----
    @DeleteMapping("/delete-booking/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/admin";
    }
}
