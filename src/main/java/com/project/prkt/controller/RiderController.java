package com.project.prkt.controller;

import com.project.prkt.model.Rider;
import com.project.prkt.service.BookingService;
import com.project.prkt.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String createNewRider(Model model, @RequestParam(value = "id", required = false) Long bookingId) {
        model.addAttribute("newRider", new Rider());
        model.addAttribute("bookingId", bookingId);
        return "rider/add_new";
    }

    @PostMapping("/add")
    public String addNewRiderToDb(@RequestParam(value = "id", required = false) Long bookingId,
                                  @Valid @ModelAttribute("newRider") Rider rider,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bookingId", bookingId);
            model.addAttribute("newRider", rider);
            return "rider/add_new";

        }
        riderService.addNewRiderToDB(rider);

        if(bookingId != null){
        bookingService.addNewRiderToBooking(bookingId, rider);
            return "redirect:/admin/info-riders/add-new?id=" + bookingId;
        }

        return "redirect:/admin/info-riders/";
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
        return "rider/edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateRiderById(@PathVariable("id") Long riderToBeUpdatedId,
                                  @RequestParam(value = "bookingid", required = false) Long bookingId,
                                  @Valid @ModelAttribute("riderToBeUpdated") Rider oneUpdatedRider,
                                 BindingResult bindingResult,
                                 Model model
                                 /* @ModelAttribute("bookingId") Long bookingId) */){
        if (bindingResult.hasErrors()){
            model.addAttribute("riderToBeUpdated", oneUpdatedRider);
            model.addAttribute("idOfRider", riderToBeUpdatedId);
            model.addAttribute("bookingId", bookingId);
            return "rider/edit";
        }
        riderService.updateRiderById(riderToBeUpdatedId, oneUpdatedRider);
        if(bookingId != null) {
           return "redirect:/admin/info-booking/edit/" + bookingId; //пусть пока туда летит. void почемуто крашит метод
        } else {
            return "redirect:/admin/info-riders";

        }
    }

    //-----------sort----------
    @GetMapping("/sort")
    public String sortAllRidersByParameter(@RequestParam("parameter") String parameter,
                                           @RequestParam("sortDirection") String sortDirection,
                                           Model model){
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("allRiders", riderService.sortAllByParameter(parameter, sortDirection));
        model.addAttribute("allBookings", bookingService.showAllBookings());
        return "rider/show_all";
    }

}

