package com.project.prkt.controller;

import com.project.prkt.model.Gloves;
import com.project.prkt.service.GlovesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/info-equipment/gloves")
public class GlovesController {
    private final GlovesService glovesService;
    @Autowired
    public GlovesController(GlovesService glovesService) {
        this.glovesService = glovesService;
    }

    //----show all----
    @GetMapping
    public String showAllGloves(Model model){
        model.addAttribute("allGloves", glovesService.showAllGloves());
        return "gloves/show_all";
    }

    //-----add new show page---
    @GetMapping("add-new")
    public String createNewGloves(Model model){
        model.addAttribute("newGloves", new Gloves());
        return "gloves/add_new";
    }
    //-----save new gloves to db-----
    @PostMapping()
    public String addNewGlovesToDB(@ModelAttribute("newGloves") @Valid Gloves gloves,
                                  BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "gloves/add_new";
        }
        glovesService.saveNewGlovesToDB(gloves);
        return "redirect:/admin/info-equipment/gloves";
    }
    //--------edit show page----------
    @GetMapping("/edit/{id}")
    public String showOneGloves(@PathVariable("id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("glovesToUpdate", glovesService.showOneById(id));
        return "gloves/edit";
    }

    //-------- edit save changes----
    @PatchMapping("/edit/{id}")
    public String updateGloves(@PathVariable("id") Long id,
                              @ModelAttribute("glovesToUpdate") @Valid Gloves updatedGloves,
                              BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("id", id);
            model.addAttribute("glovesToUpdate", updatedGloves);
            return "gloves/edit";
        }
        glovesService.updateGlovesById(id, updatedGloves);
        return "redirect:/admin/info-equipment/gloves";
    }

    //------delete-----
    @DeleteMapping("{id}")
    public String deleteGloves(@PathVariable("id") Long id){
        glovesService.deleteGlovesById(id);
        return "redirect:/admin/info-equipment/gloves";
    }

    //------ search-----
    @GetMapping("/search-by-name")
    public String showGlovesByPartOfName(@RequestParam("partOfName") String partOfName, Model model){
        model.addAttribute("glovesByPartOfName", glovesService.showGlovesByPartOfName(partOfName));
        model.addAttribute("partOfName", partOfName);
        return "gloves/search";
    }

    //------- sort------
    @GetMapping("/sort")
    public String sortAllGlovesByParameter(@RequestParam("parameter") String parameter,
                                          @RequestParam("sortDirection") String sortDirection,
                                          Model model){
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("allGloves", glovesService.sortAllGlovesByParameter(parameter, sortDirection));
        return "gloves/show_all";
    }


}
