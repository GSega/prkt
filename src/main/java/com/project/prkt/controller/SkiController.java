package com.project.prkt.controller;

import com.project.prkt.model.Ski;
import com.project.prkt.service.SkiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

  /*  @GetMapping("/{id}")
    public String showOneSki(@PathVariable("id") Long id, Model model) {
        model.addAttribute("oneSki", skiService.findById(id));
        return "ski/show_one";
    }*/

    @GetMapping("/add_new")
    public String addNewSki(Model model) {
       model.addAttribute("ski", new Ski());
       return "ski/add_new";
    }
    @PostMapping()
    public String sendNewSkiToDatabase(@ModelAttribute("ski") Ski ski) {

        skiService.addToDatabase(ski);
        return "redirect:/admin/info_equipment/ski/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,  Model model){
        model.addAttribute("ski", skiService.findById(id));

        return "ski/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute("ski") Ski ski){

        skiService.updateById(id, ski);
        return "redirect:/admin/info_equipment/ski";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        skiService.deleteFromDatabase(id);
        return "redirect:/admin/info_equipment/ski/";
    }

    @GetMapping("/search_by_name")
    public String showSnowboardBootsByPartOfName(Model model, @RequestParam("search") String search) {
        model.addAttribute("snowboardBootsByName", skiService.findByPartOfName(search));
        return "ski/search";
    }

    //    ----- Sorts -----
    @GetMapping("/sort")
    public String sortAllByParameter(@RequestParam("parameter") String parameter,
                                     @RequestParam("sortDirection") String sortDirection, Model model) {
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("allSki", skiService.sortAllByParameter(parameter, sortDirection));
        return "ski/show_all";
    }



}

