package com.project.prkt.controller;

import com.project.prkt.model.SnowboardBoots;
import com.project.prkt.service.SnowboardBootsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/info_equipment/snowboard_boots")
public class SnowboardBootsController {

    private final SnowboardBootsService snowboardBootsService;

    @Autowired
    public SnowboardBootsController(SnowboardBootsService snowboardBootsService) {
        this.snowboardBootsService = snowboardBootsService;
    }

//    ----- Show all -----
    @GetMapping()
    public String showAllSnowboardBoots(Model model) {
        model.addAttribute("allSnowboardBoots", snowboardBootsService.findAll());
        return "snowboard_boots/show_all";
    }

//    ----- Add new -----
    @GetMapping("/add_new")
    public String addNewSnowboardBoots(Model model) {
        model.addAttribute("snowboardBoots", new SnowboardBoots());
        return "snowboard_boots/add_new";
    }

    @PostMapping()
    public String addNewSnowboardBootsToDatabase(@ModelAttribute("snowboardBoots") @Valid SnowboardBoots snowboardBoots,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "snowboard_boots/add_new";
        }
        snowboardBootsService.addToDatabase(snowboardBoots);
        return "redirect:/admin/info_equipment/snowboard_boots";
    }

//    ----- Edit -----
    @GetMapping("/edit/{id}")
    public String showOneSnowboardBoots(@PathVariable("id") Long id, Model model) {
        model.addAttribute("snowboardBoots", snowboardBootsService.findById(id));
        return "snowboard_boots/edit";
    }

    @PatchMapping("/{id}")
    public String updateSnowboardBootsById(@PathVariable Long id,
                                           @ModelAttribute("snowboardBoots") @Valid SnowboardBoots snowboardBoots,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "snowboard_boots/edit";
        }
        snowboardBootsService.updateById(id, snowboardBoots);
        return "redirect:/admin/info_equipment/snowboard_boots";
    }

//    ----- Delete -----
    @DeleteMapping("/{id}")
    public String deleteSnowboardBoots(@PathVariable("id") Long id) {
        snowboardBootsService.deleteFromDatabase(id);
        return "redirect:/admin/info_equipment/snowboard_boots";
    }

//    ----- Search by name -----
    @GetMapping("/search_by_name")
    public String showSnowboardBootsByPartOfName(Model model, @RequestParam("search") String search) {
        model.addAttribute("snowboardBootsByName", snowboardBootsService.findByPartOfName(search));
        return "snowboard_boots/search";
    }

//    ----- Sorts -----
    @GetMapping("/sort")
    public String sortAllByParameter(@RequestParam("parameter") String parameter,
                                  @RequestParam("sortDirection") String sortDirection, Model model) {
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("allSnowboardBoots", snowboardBootsService.sortAllByParameter(parameter, sortDirection));
        return "snowboard_boots/show_all";
    }
}
