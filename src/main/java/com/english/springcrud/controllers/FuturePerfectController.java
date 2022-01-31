package com.english.springcrud.controllers;


import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class FuturePerfectController {
    private final PhraseService phraseService;

    public FuturePerfectController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/perfect/futurePerfect/futurePerfect")
    public String futurePerfect(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/futurePerfect/futurePerfect";
    }

    @GetMapping("/perfect/futurePerfect/affirmative")
    public String futurePerfectAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "futurePerfect");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/futurePerfect/affirmative";
    }

    @GetMapping("/perfect/futurePerfect/questions")
    public String futurePerfectQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "futurePerfect");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/futurePerfect/questions";
    }

    @GetMapping("/perfect/futurePerfect/negative")
    public String futurePerfectNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "futurePerfect");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/futurePerfect/negative";
    }
}
