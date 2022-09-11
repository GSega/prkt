package com.project.prkt.controller;

import com.project.prkt.model.*;
import com.project.prkt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Nikolai Khriapov
 */

@Controller
@RequestMapping("/admin/info-booking")
public class BookingController {

    private final BookingService bookingService;
    private final ClientService clientService;
    private final RiderService riderService;
    private final SnowboardService snowboardService;
    private final SnowboardBootsService snowboardBootsService;
    private final SkiService skiService;
    private final SkiBootsService skiBootsService;

    @Autowired
    public BookingController(BookingService bookingService, ClientService clientService, RiderService riderService,
                             SnowboardService snowboardService, SnowboardBootsService snowboardBootsService,
                             SkiService skiService, SkiBootsService skiBootsService) {
        this.bookingService = bookingService;
        this.clientService = clientService;
        this.riderService = riderService;
        this.snowboardService = snowboardService;
        this.snowboardBootsService = snowboardBootsService;
        this.skiService = skiService;
        this.skiBootsService = skiBootsService;
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
        model.addAttribute("newClientAndNewBookingInfo", new BookingCreationRequest());
        return "booking/add_new";
    }

    @PostMapping()
    public String addNewClientAndBookingToDB(@ModelAttribute("newClientAndNewBookingInfo") @Valid BookingCreationRequest newClientAndNewBookingInfo,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "booking/add_new";
        }
        Client newClient = new Client(newClientAndNewBookingInfo.getSurname(),
                newClientAndNewBookingInfo.getPhone1(), newClientAndNewBookingInfo.getPhone2());
        clientService.addNewClientToDB(newClient);
        Booking newBooking = new Booking();
        bookingService.addNewBookingInfoToNewBooking(newBooking, newClient,
                newClientAndNewBookingInfo.getDateOfArrival(), newClientAndNewBookingInfo.getDateOfReturn());
        bookingService.addNewBookingToDB(newBooking);
        return "redirect:/admin/info-riders/add-new?id=" + newBooking.getId();
    }

    // ----- edit booking info -----
    @GetMapping("/edit/{id}")
    public String showOneBooking(@PathVariable("id") Long bookingToBeUpdatedId, Model model) {
        Booking bookingToBeUpdated = bookingService.showOneBookingById(bookingToBeUpdatedId);
        Client clientToBeUpdated = bookingToBeUpdated.getClient();

        BookingCreationRequest clientAndBookingInfoToBeUpdated = new BookingCreationRequest(
                clientToBeUpdated.getId(), bookingToBeUpdatedId, clientToBeUpdated.getSurname(),
                clientToBeUpdated.getPhone1(), clientToBeUpdated.getPhone2(), bookingToBeUpdated.getDateOfArrival(),
                bookingToBeUpdated.getDateOfReturn(), bookingToBeUpdated.getListOfRiders(), 0L);

        model.addAttribute("clientAndBookingInfoToBeUpdated", clientAndBookingInfoToBeUpdated);
        model.addAttribute("allRiders", riderService.showAllRiders());

        model.addAttribute("assignedEquipmentIds", new EquipmentAssignmentRequest());
        model.addAttribute("allSnowboardsAvailable", snowboardService.showAllSnowboards());
        model.addAttribute("allSnowboardBootsAvailable", snowboardBootsService.showAllSnowboardBoots());
        model.addAttribute("allSkiAvailable", skiService.findAll());
        model.addAttribute("allSkiBootsAvailable", skiBootsService.showAllSkiBoots());
        return "booking/edit";
    }

    //// ----- edit booking info / edit booking and client info -----
    @PatchMapping("/edit/{id}")
    public String updateBookingById(@PathVariable("id") Long bookingToBeUpdatedId,
                                    @ModelAttribute("clientAndBookingInfoToBeUpdated") @Valid BookingCreationRequest updatedClientAndBookingInfo,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", bookingToBeUpdatedId);
            updatedClientAndBookingInfo.setListOfRiders(bookingService.showOneBookingById(bookingToBeUpdatedId).getListOfRiders());
            model.addAttribute("clientAndBookingInfoToBeUpdated", updatedClientAndBookingInfo);
            model.addAttribute("allRiders", riderService.showAllRiders());
            return "booking/edit";
        }
        Booking bookingToBeUpdated = bookingService.showOneBookingById(bookingToBeUpdatedId);

        clientService.updateClientById(bookingToBeUpdated.getClient().getId(), new Client(updatedClientAndBookingInfo.getSurname(),
                updatedClientAndBookingInfo.getPhone1(), updatedClientAndBookingInfo.getPhone2()));

        bookingService.updateBookingById(bookingToBeUpdatedId, new Booking(bookingToBeUpdated.getClient(),
                updatedClientAndBookingInfo.getDateOfArrival(), updatedClientAndBookingInfo.getDateOfReturn()));
        return "redirect:/admin/info-booking/edit/{id}";
    }

    //// ----- edit booking info / assign equipment to riders -----
    @PatchMapping("/edit/assign-equipment/{id}")
    public String assignEquipmentToOneRider(@PathVariable("id") Long bookingToBeUpdatedId,
                                            @ModelAttribute("assignedEquipmentIds") EquipmentAssignmentRequest assignedEquipment) {
        if (assignedEquipment.getAssignedSnowboardId() != null) {
            snowboardService.changeSnowboardAvailableById(assignedEquipment.getAssignedSnowboardId());
        }
        if (assignedEquipment.getAssignedSnowboardId() != null) {
            snowboardBootsService.changeSnowboardBootsAvailableById(assignedEquipment.getAssignedSnowboardBootsId());
        }
        return "redirect:/admin/info-booking/edit/{id}";
    }

    //// ----- edit booking info / add existing rider to booking -----
    @PatchMapping("/edit/add-existing-rider/{bid}")
    public String addExistingRiderToBooking(@PathVariable("bid") Long bookingToBeUpdatedId,
                                            @ModelAttribute("clientAndBookingInfoToBeUpdated") BookingCreationRequest clientAndBookingInfoToBeUpdated) {
        Rider existingRiderToBeAdded = riderService.showOneRiderById(clientAndBookingInfoToBeUpdated.getExistingRiderToBeAddedId());
        Booking bookingToBeUpdated = bookingService.showOneBookingById(bookingToBeUpdatedId);
        bookingService.addExistingRiderToBooking(bookingToBeUpdated, existingRiderToBeAdded);
        return "redirect:/admin/info-booking/edit/" + bookingToBeUpdatedId;
    }

    //// ----- edit booking info / remove rider from booking -----
    @GetMapping("/edit/remove")
    public String removeRiderFromBooking(@RequestParam("bid") Long bookingToBeUpdatedId,
                                         @RequestParam("rid") Long riderToBeRemovedId,
                                         Model model) {
        Booking bookingToBeUpdated = bookingService.showOneBookingById(bookingToBeUpdatedId);
        Rider riderToBoUpdated = riderService.showOneRiderById(riderToBeRemovedId);
        bookingService.removeRiderFromBooking(bookingToBeUpdated, riderToBoUpdated);
        model.addAttribute("allRiders", riderService.showAllRiders());
        return "redirect:/admin/info-booking/edit/" + bookingToBeUpdatedId;
    }

    // ----- delete booking -----
    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/admin/info-booking";
    }

    // ----- mark booking completed -----
    @GetMapping("/change-booking-completed/{id}")
    public String changeBookingCompleted(@PathVariable("id") Long bookingId) {
        bookingService.markBookingCompleted(bookingId);
        return "redirect:/admin/info-booking";
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


}
