package com.project.prkt.controller;

import com.project.prkt.model.Ski;
import com.project.prkt.service.SkiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/info_equipment/ski")
public class SkiController {
    private final SkiService skiService;

    @Autowired
    public SkiController(SkiService skiService) {
        this.skiService = skiService;
    }

    @GetMapping()
    public String showAllSki(Model model){
        model.addAttribute("allSki", skiService.findAll());
        return "ski/show_all";
    }

    @GetMapping("/{id}")
    public String showOneSki(@PathVariable("id") Long id, Model model) {
        model.addAttribute("oneSkiFrom", skiService.findById(id));
        return "ski/show_one";
    }

    @PostMapping("/add_new")
    public void addNewSnowboardBoots(@ModelAttribute("ski") Ski ski) {
        skiService.addToDatabase(ski);
    }

    @PutMapping("/update")
    public void updateSnowboardBootsAvailableById(Long id, @ModelAttribute("available") boolean available) {
        skiService.updateAvailableById(id, available);
    }

    @DeleteMapping("/addNew")
    public void deleteSnowboardBoots(Long id) {
        skiService.deleteFromDatabase(id);
    }


}

