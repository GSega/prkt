package com.project.prkt.controller;

import com.project.prkt.model.Helmet;
import com.project.prkt.service.HelmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping()
    public String sendNewHelmet(@Valid @ModelAttribute("helmet") Helmet helmet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "helmet/add_new";
        }
        helmetService.saveHelmetToDB(helmet);
        return "redirect:/admin/info-equipment/helmet";
    }
}
