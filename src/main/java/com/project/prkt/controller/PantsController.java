package com.project.prkt.controller;

import com.project.prkt.model.Pants;
import com.project.prkt.service.PantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/info-equipment/pants")
public class PantsController {
    private final PantsService pantsService;
    @Autowired
    public PantsController(PantsService pantsService) {
        this.pantsService = pantsService;
    }

    //----show all----
    @GetMapping
    public String showAllPants(Model model){
        model.addAttribute("allPants", pantsService.showAllPants());
        return "pants/show_all";
    }

    //-----add new show page---
    @GetMapping("add-new")
    public String createNewPants(Model model){
        model.addAttribute("newPants", new Pants());
        return "pants/add_new";
    }
    //-----save new pants to db-----
    @PostMapping()
    public String addNewPantsToDB(@ModelAttribute("newPants") @Valid Pants pants,
                                  BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "pants/add_new";
        }
        pantsService.saveNewPantsToDB(pants);
        return "redirect:/admin/info-equipment/pants";
    }
    //--------edit show page----------
    @GetMapping("/edit/{id}")
    public String showOnePants(@PathVariable("id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("pantsToUpdate", pantsService.showOneById(id));
        return "pants/edit";
    }

    //-------- edit save changes----
    @PatchMapping("/edit/{id}")
    public String updatePants(@PathVariable("id") Long id,
                              @ModelAttribute("pantsToUpdate") @Valid Pants updatedPants,
                              BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("id", id);
            model.addAttribute("pantsToUpdate", updatedPants);
            return "pants/edit";
        }
        pantsService.updatePantsById(id, updatedPants);
        return "redirect:/admin/info-equipment/pants";
    }

    //------delete-----
    @DeleteMapping("{id}")
    public String deletePants(@PathVariable("id") Long id){
        pantsService.deletePantsById(id);
        return "redirect:/admin/info-equipment/pants";
    }

    //------ search-----
    @GetMapping("/search-by-name")
    public String showPantsByPartOfName(@RequestParam("partOfName") String partOfName, Model model){
        model.addAttribute("pantsByPartOfName", pantsService.showPantsByPartOfName(partOfName));
        model.addAttribute("partOfName", partOfName);
        return "pants/search";
    }

    //------- sort------
    @GetMapping("/sort")
    public String sortAllPantsByParameter(@RequestParam("parameter") String parameter,
                                          @RequestParam("sortDirection") String sortDirection,
                                          Model model){
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("allPants", pantsService.sortAllPantsByParameter(parameter, sortDirection));
        return "pants/show_all";
    }


}
