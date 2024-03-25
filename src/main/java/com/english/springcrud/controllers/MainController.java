package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private final PhraseService phraseService;

    @Autowired
    public MainController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "about";
    }

    @GetMapping("/map")
    public String map(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "map";
    }
}
