package com.project.prkt.controller;

import com.project.prkt.model.Ski;
import com.project.prkt.service.SkiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/info-equipment/ski")
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
            //add new
    @GetMapping("/add-new")
    public String addNewSki(Model model) {
       model.addAttribute("ski", new Ski());
       return "ski/add_new";
    }
    @PostMapping()
    public String sendNewSkiToDatabase(@Valid @ModelAttribute("ski")  Ski ski,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ski/add_new";
        }

        skiService.addToDatabase(ski);
        return "redirect:/admin/info-equipment/ski";
    }
                // edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,  Model model){
        model.addAttribute("ski", skiService.findById(id));
        model.addAttribute("id", id);

        return "ski/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") Long id,
                       @ModelAttribute("ski") @Valid Ski ski,
                       BindingResult bindingResult,
                       Model model){
        System.out.println("im above ");
        if (bindingResult.hasErrors()){
            model.addAttribute("id", id);
            model.addAttribute("ski", ski);
            return "ski/edit";
        }
        System.out.println("я below проверкой");
        skiService.updateById(id, ski);
        return "redirect:/admin/info-equipment/ski/";
    }
            // delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        skiService.deleteFromDatabase(id);
        return "redirect:/admin/info-equipment/ski/";
    }


    //    ----- Sorts -----
    @GetMapping("/sort")
    public String sortAllByParameter(@RequestParam("parameter") String parameter,
                                     @RequestParam("sortDirection") String sortDirection, Model model) {
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("allSki", skiService.sortAllByParameter(parameter, sortDirection));
        return "ski/show_all";
    }

    @GetMapping("/search-by-name")
    public String skiByPartOfName(Model model, @RequestParam("search") String search) {
        model.addAttribute("skiByName", skiService.findByPartOfName(search));
        return "ski/search";
    }

}

