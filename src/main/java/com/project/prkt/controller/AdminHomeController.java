package com.project.prkt.controller;

import com.project.prkt.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
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
        Date today = new Date();
        model.addAttribute("date", today);
        model.addAttribute("bookingsForTheDate", bookingService.showBookingsForTheDate(today));
        return "admin/bookings_by_date";
    }

    // ----- bookings for tomorrow -----
    @GetMapping("/show-tomorrow")
    public String showBookingsForTomorrow(Model model) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);

        Date tomorrow = c.getTime();
        model.addAttribute("date", tomorrow);
        model.addAttribute("bookingsForTheDate", bookingService.showBookingsForTheDate(tomorrow));
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
