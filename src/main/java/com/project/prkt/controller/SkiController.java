package com.project.prkt.controller;

import com.project.prkt.model.Ski;
import com.project.prkt.service.SkiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
        model.addAttribute("oneSki", skiService.findById(id));
        return "ski/show_one";
    }

    @GetMapping("/add_new")
    public String addNewSki(Model model) {
       model.addAttribute("newSki", new Ski());
       return "ski/add_new";
    }
    @PostMapping()
    public String sendNewSkiToDatabase(@ModelAttribute("ski") Ski ski){
        skiService.addToDatabase(ski);
        return "redirect:/admin/info_equipment/ski/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,  Model model){
        model.addAttribute("skiToEdit", skiService.findById(id));

        return "ski/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute("ski") Ski ski){
        skiService.updateById(id, ski);
        return null;
    }














/*
    @PutMapping("/update")
    public void updateSnowboardBootsAvailableById(Long id, @ModelAttribute("available") boolean available) {
        skiService.updateAvailableById(id, available);
    }

    @DeleteMapping("/addNew")
    public void deleteSnowboardBoots(Long id) {
        skiService.deleteFromDatabase(id);
    }



    @PostMapping()
    public String addNewSkiToDatabase(@ModelAttribute("ski") @Valid Ski ski, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "snowboard_boots/add_new";
        }
        skiService.addToDatabase(ski);
        return "redirect:/admin/info_equipment/snowboard_boots";
    }*/

}

