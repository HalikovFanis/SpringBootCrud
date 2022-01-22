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
public class FutureSimpleController {
    private final PhraseService phraseService;
    @Autowired
    public FutureSimpleController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/futureSimple/affirmative")
    public String futureSimpleAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "futureSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "futureSimple/affirmative";
    }

    @GetMapping("/futureSimple/questions")
    public String futureSimpleQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "futureSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "futureSimple/questions";
    }

    @GetMapping("/futureSimple/negative")
    public String futureSimpleNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "futureSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "futureSimple/negative";
    }
}
