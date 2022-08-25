package com.project.prkt.controller;

import com.project.prkt.model.SnowboardBoots;
import com.project.prkt.service.SnowboardBootsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/info_equipment/snowboard_boots")
public class SnowboardBootsController {

    private final SnowboardBootsService snowboardBootsService;

    @Autowired
    public SnowboardBootsController(SnowboardBootsService snowboardBootsService) {
        this.snowboardBootsService = snowboardBootsService;
    }

    @GetMapping()
    public String showAllSnowboardBoots(Model model) {
        model.addAttribute("allSnowboardBoots", snowboardBootsService.findAll());
        return "snowboard_boots/show_all";
    }

    @GetMapping("/{id}")
    public String showOneSnowboardBoots(@PathVariable("id") Long id, Model model) {
        model.addAttribute("oneSnowboardBoots", snowboardBootsService.findById(id));
        return "snowboard_boots/show_one";
    }

    @PostMapping("/add_new")
    public void addNewSnowboardBoots(@ModelAttribute("snowboardBoots") SnowboardBoots snowboardBoots) {
        snowboardBootsService.addToDatabase(snowboardBoots);
    }

    @PutMapping("/update")
    public void updateSnowboardBootsAvailableById(Long id, @ModelAttribute("available") boolean available) {
        snowboardBootsService.updateAvailableById(id, available);
    }

    @DeleteMapping("/addNew")
    public void deleteSnowboardBoots(Long id) {
        snowboardBootsService.deleteFromDatabase(id);
    }
}
