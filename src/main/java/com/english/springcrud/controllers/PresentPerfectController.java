package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PresentPerfectController {
    private final PhraseService phraseService;

    public PresentPerfectController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/perfect/presentPerfect/presentPerfect")
    public String presentPerfect(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/presentPerfect/presentPerfect";
    }

    @GetMapping("/perfect/presentPerfect/affirmative")
    public String presentPerfectAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "presentPerfect");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/presentPerfect/affirmative";
    }

    @GetMapping("/perfect/presentPerfect/questions")
    public String presentPerfectQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "presentPerfect");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/presentPerfect/questions";
    }

    @GetMapping("/perfect/presentPerfect/negative")
    public String presentPerfectNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "presentPerfect");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/presentPerfect/negative";
    }
}
