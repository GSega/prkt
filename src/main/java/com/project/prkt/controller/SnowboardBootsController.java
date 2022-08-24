package com.project.prkt.controller;

        import com.project.prkt.service.SnowboardBootsService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/snowboard_boots")
public class SnowboardBootsController {

    private final SnowboardBootsService snowboardBootsService;

    @Autowired
    public SnowboardBootsController(SnowboardBootsService snowboardBootsService) {
        this.snowboardBootsService = snowboardBootsService;
    }

    @GetMapping()
    public String showboardBoots() {
        return "snowboard_boots";
    }

    @GetMapping ("/add")
    public void showAllSnowboardBoots() {
        snowboardBootsService.findAll();
    }
}
