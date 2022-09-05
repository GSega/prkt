package com.project.prkt.controller;

import com.project.prkt.model.Rider;
import com.project.prkt.service.BookingService;
import com.project.prkt.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/new-rider")
    public String createNewRider(Model model){
        model.addAttribute("newRider", new Rider());
        return "rider/add_new";
    }

    @PostMapping("/add")
    public String addNewRiderToDb(@ModelAttribute("newRider") Rider rider, @RequestParam("id") Long orderId){
        riderService.addNewRiderToDB(rider);
        //bookingService.addNewRiderToBooking(orderId, rider); uncomment after merge
        return "rider/add_new";  // later change to "redirect: /client/booking-rider";
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteRider(@PathVariable("id") Long id){
        riderService.deleteRiderById(id);
    }

    //show all
    @GetMapping()
    public String showAllRiders (Model model){
        model.addAttribute("allRiders", riderService.showAllRiders());
        model.addAttribute("allBookings", bookingService.showAllBookings());
        return "rider/show_all";
    }

    //edit
    @GetMapping("/edit/{id}")
    public String showOneRider(@PathVariable("id") Long id, Model model){
        model.addAttribute("riderToBeUpdated", riderService.showOneRiderById(id));
        return "rider/edit";
        }
    @PatchMapping("/edit/{id}")
    public String updateRiderById(@PathVariable("id") Long riderToBeUpdatedId,
                                  @ModelAttribute("oneUpdatedRider") Rider oneUpdatedRider){
        riderService.updateRiderById(riderToBeUpdatedId, oneUpdatedRider);
        return "redirect: /admin/info-rider";
        }

}
