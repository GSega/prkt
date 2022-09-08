package com.project.prkt.controller;

import com.project.prkt.model.Rider;
import com.project.prkt.service.BookingService;
import com.project.prkt.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sergei Gavrilov
 */

@Controller
@RequestMapping("/admin/info-riders")
public class RiderController {
    BookingService bookingService;
    RiderService riderService;

    @Autowired
    public RiderController(RiderService riderService, BookingService bookingService) {
        this.riderService = riderService;
        this.bookingService = bookingService;
    }


    //add new
    @GetMapping("/add-new")
    public String createNewRider(Model model, @RequestParam("id") Long bookingId) {
        model.addAttribute("newRider", new Rider());
        model.addAttribute("bookingId", bookingId);
        return "rider/add_new";
    }

    @PostMapping("/add")
    public String addNewRiderToDb(@ModelAttribute("newRider") Rider rider, @RequestParam("id") Long bookingId) {
        riderService.addNewRiderToDB(rider);
        bookingService.addNewRiderToBooking(bookingId, rider);
        return "redirect:/admin/info-riders/add-new?id=" + bookingId;
    }

    //delete
    @DeleteMapping("/{id}")
    public String deleteRider(@PathVariable("id") Long id) {
        riderService.deleteRiderById(id);
        return "redirect:/admin/info-riders"; //вроде потом надо void сдеалть
    }

    //show all
    @GetMapping()
    public String showAllRiders(Model model) {
        model.addAttribute("allRiders", riderService.showAllRiders());
        model.addAttribute("allBookings", bookingService.showAllBookings());
        return "rider/show_all";
    }

    //edit
    @GetMapping("/edit") //во view к адресу добавлен параметр ?id=...&bookingid=...
    public String showOneRider(@RequestParam(value = "id") Long id,
                               @RequestParam(value = "bookingid", required = false) Long bookingId,
                               Model model) {
        model.addAttribute("riderToBeUpdated", riderService.showOneRiderById(id));
        model.addAttribute("bookingId", bookingId);
        model.addAttribute("idOfRider", id);
        //добавить <input hidden...> во вбюшку rider/edit ???
        return "rider/edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateRiderById(@PathVariable("id") Long riderToBeUpdatedId,
                                  @ModelAttribute("riderToBeUpdated") Rider oneUpdatedRider,
                                  @ModelAttribute("bookingId") Long bookingId) {
        riderService.updateRiderById(riderToBeUpdatedId, oneUpdatedRider);

        return "redirect:/admin/info-booking"; //пусть пока туда летит. void почемуто крашит метод


    }

}

