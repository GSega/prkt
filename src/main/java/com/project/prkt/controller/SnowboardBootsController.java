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

    @GetMapping("/add_new")
    public String addNewSnowboardBoots(Model model) {
        model.addAttribute("newSnowboardBoots", new SnowboardBoots());
        return "snowboard_boots/add_new";
    }

    @PostMapping()
    public String addNewSnowboardBootsToDatabase(@ModelAttribute("snowboardBoots") SnowboardBoots snowboardBoots) {
        snowboardBootsService.addToDatabase(snowboardBoots);
        return "redirect:/admin/info_equipment/snowboard_boots";
    }

    @GetMapping("/edit/{id}")
    public String showOneSnowboardBoots(@PathVariable("id") Long id, Model model) {
        model.addAttribute("snowboardBoots", snowboardBootsService.findById(id));
        return "snowboard_boots/edit";
    }

    @PatchMapping("/{id}")
    public String updateSnowboardBootsById(@PathVariable Long id, @ModelAttribute("snowboardBoots") SnowboardBoots snowboardBoots) {
        snowboardBootsService.updateById(id, snowboardBoots);
        return "redirect:/admin/info_equipment/snowboard_boots";
    }

    @DeleteMapping("/{id}")
    public String deleteSnowboardBoots(@PathVariable("id") Long id) {
        snowboardBootsService.deleteFromDatabase(id);
        return "redirect:/admin/info_equipment/snowboard_boots";
    }
}
