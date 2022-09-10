package com.project.prkt.controller;

import com.project.prkt.model.SkiBoots;
import com.project.prkt.service.SkiBootsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/admin/info-equipment/ski-boots")
public class SkiBootsController {
    private final SkiBootsService skiBootsService;

    public SkiBootsController(SkiBootsService skiBootsService) {
        this.skiBootsService = skiBootsService;
    }

    @GetMapping()
    public String showAllSkiBoots(Model model){
        model.addAttribute("allSkiBoots", skiBootsService.showAllSkiBoots());
        return "ski_boots/show_all";

    }
    //add new. view
    @GetMapping("/add-new")
    public String createNewSkiBoots(Model model) {
        model.addAttribute("newSkiBoots", new SkiBoots());
        return "ski_boots/add_new";
    }
    //add new. save to db
    @PostMapping()
    public String addNewSkiBootsToDB(@ModelAttribute("newSkiBoots") @Valid SkiBoots skiBoots,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "ski_boots/add_new";
        }
        skiBootsService.addNewSkiBootsToDB(skiBoots);
        return "redirect:/admin/info-equipment/ski-boots";
    }

    //edit. view
    @GetMapping("/edit/{id}")
    public String showOneSkiBoots(@PathVariable("id") Long id, Model model) {
        model.addAttribute("skiBootsToUpdate", skiBootsService.showOneSkiBootsById(id));
        return "ski_boots/edit";
    }
    //edit. save to db
    @PatchMapping("/{id}")
    public String updateSkiBoots(@PathVariable("id") Long id,
                                 @ModelAttribute("skiBootsToUpdate") @Valid SkiBoots updatedSkiBoots,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("id", id);
            model.addAttribute("skiBootsToUpdate", updatedSkiBoots);
            return "ski_boots/edit";
        }
        skiBootsService.updateSkiBootsById(id, updatedSkiBoots);
        return "redirect:/admin/info-equipment/ski-boots";
    }
    //delete
    @DeleteMapping("/{id}")
    public String deleteSkiBoots(@PathVariable("id") Long id) {
        skiBootsService.deleteSkiBootsById(id);
        return "redirect:/admin/info-equipment/ski-boots";
    }

    @GetMapping("/search-by-name")
    public String showSkiBootsByPartOfName(@RequestParam("partOfName") String partOfName, Model model) {
        model.addAttribute("skiBootsByPartOfName", skiBootsService.showSkiBootsByPartOfName(partOfName));
        return "ski_boots/search";
    }

    @GetMapping("/sort")
    public String sortAllSkiBootsByParameter(@RequestParam("parameter") String parameter,
                                                   @RequestParam("sortDirection") String sortDirection,
                                                   Model model) {
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("allSkiBoots", skiBootsService.sortAllSkiBootsByParameter(parameter, sortDirection));
        // the above attributeName must be the same as in method "showAllSkiBoots"
        return "ski_boots/show_all";
    }





}
