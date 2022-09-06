package com.project.prkt.controller;

import com.project.prkt.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // add "index" or "home" view
    @GetMapping()
    public String showAdminMainPage(){
        return "admin/main_page";
    }
    @GetMapping("/info-equipment")
    public String showEqipmentChoisepage(){
        return "admin/info_equipment";
    }

    // hyperlink to bookings
    // hyperlink to equipment
    // hyperlink to clients
    // hyperlink to riders



    // ----- orders for today -----
    @GetMapping("/show-today")
    public String showBookingsForToday(Model model) {
        Date today = new Date();
        model.addAttribute("date", today);
        model.addAttribute("bookingsForTheDate", bookingService.showBookingsForTheDate(today));
        return "admin/bookings_by_date";
    }

    // ----- orders for tomorrow -----
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
}
