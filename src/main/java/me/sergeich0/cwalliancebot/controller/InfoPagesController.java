package me.sergeich0.cwalliancebot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoPagesController {

    @Value("${telegram.bot.username}")
    private String botName;

    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/help")
    @PreAuthorize("permitAll()")
    public String getHelpPage(Model model) {
        model.addAttribute("botName", botName);
        return "help";
    }
}
