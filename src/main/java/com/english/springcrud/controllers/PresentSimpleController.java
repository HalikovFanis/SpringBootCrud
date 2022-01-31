package com.english.springcrud.controllers;


import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PresentSimpleController {
    private final PhraseService phraseService;
    @Autowired
    public PresentSimpleController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/simple/presentSimple/presentSimple")
    public String presentSimple(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/simple/presentSimple/presentSimple";
    }

    @GetMapping("/simple/presentSimple/affirmative")
    public String presentSimpleAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "presentSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/simple/presentSimple/affirmative";
    }

    @GetMapping("/simple/presentSimple/questions")
    public String presentSimpleQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "presentSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/simple/presentSimple/questions";
    }

    @GetMapping("/simple/presentSimple/negative")
    public String presentSimpleNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "presentSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/simple/presentSimple/negative";
    }
}
