package com.project.prkt.controller;

import com.project.prkt.model.Helmet;
import com.project.prkt.service.HelmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/info-equipment/helmet")
public class HelmetController {
    private final HelmetService helmetService;

    @Autowired
    public HelmetController(HelmetService helmetService) {
        this.helmetService = helmetService;
    }

    //----------show all page-------
    @GetMapping()
    public String showAllHelmets(Model model) {
        model.addAttribute("allHelmet", helmetService.showAllHelmets());
        return "helmet/show_all";
    }

    //---------add new page----------
    @GetMapping("/add-new")
    public String addNewHelmet(Model model) {
        model.addAttribute("helmet", new Helmet());
        return "helmet/add_new";
    }

    //----------add new save to db---------
    @PostMapping()
    public String sendNewHelmet(@Valid @ModelAttribute("helmet") Helmet helmet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "helmet/add_new";
        }
        helmetService.saveHelmetToDB(helmet);
        return "redirect:/admin/info-equipment/helmet";
    }

    //-----------edit page--------
    @GetMapping("/edit/{id}")
    public String showOneHelmet (@PathVariable("id") Long id, Model model){
        model.addAttribute("helmetToUpdate", helmetService.showOneHelmetById(id));
        return "helmet/edit";
    }
    //-------edit patch----------
    @PatchMapping("/{id}")
    public String updateHelmet (@PathVariable("id") Long id,
                                @Valid @ModelAttribute("helmetToBeUpdated") Helmet helmetToBeUpdated,
                                BindingResult bindingResult,
                                Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("helmetToUpdate", helmetToBeUpdated);
            model.addAttribute("id", id);
            return "helmet/edit";
        }
        helmetService.updateHelmetById(id, helmetToBeUpdated);
        return "redirect:/admin/info-equipment/helmet";
    }
    // ---------- delete --------
    @DeleteMapping("/{id}")
    public String deleteHelmet (@PathVariable("id") Long id){
        helmetService.deleteHelmetById(id);
        return "redirect:/admin/info-equipment/helmet";
    }
    //-----------sort-------------
    @GetMapping("/sort")
    public String sortAllHelmetsByParameter(@RequestParam("parameter") String parameter,
                                            @RequestParam("sortDirection") String sortDirection,
                                            Model model) {
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("allHelmet", helmetService.sortAllHelmetsByParameter(parameter, sortDirection));
        return "helmet/show_all";
    }

    //------------search---------
    @GetMapping("/search-by-name")
    public String showHelmetsByPartOfName(@RequestParam("partOfName") String partOfName, Model model) {
        model.addAttribute("helmetByPartOfName", helmetService.showHelmetsByPartOfName(partOfName));
        model.addAttribute("partOfName", partOfName);
        return "helmet/search";
    }

}
