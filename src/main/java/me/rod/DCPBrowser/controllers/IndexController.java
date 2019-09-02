package me.rod.DCPBrowser.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }

    @GetMapping("/browse")
    public String browse(Model model) {
        model.addAttribute("sourceOne", "GitHub");
        model.addAttribute("sourceTwo", "DCP Website");
        return "browse";
    }
}
