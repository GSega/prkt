package com.project.prkt.controller;

import com.project.prkt.model.Rider;
import com.project.prkt.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/booking-rider")
public class RiderController {
    RiderService riderService;
    @Autowired
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }

    //add new
    @GetMapping("/new-rider")
    public String createNewRider(Model model){
        model.addAttribute("newRider", new Rider());
        return "rider/add_new";
    }

    @PostMapping("/add")
    public String addNewRiderToDb(@ModelAttribute("newRider") Rider rider){
        riderService.addNewRiderToDB(rider);
        return "rider/add_new";  // later change to "redirect: /client/booking-rider";
    }

}
