package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PresentPerfectContController {
    private final PhraseService phraseService;

    public PresentPerfectContController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/perfectCont/presentPerfectCont/affirmative")
    public String presentPerfectContAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "presentPerfectCont");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/presentPerfectCont/affirmative";
    }

    @GetMapping("/perfectCont/presentPerfectCont/questions")
    public String presentPerfectContQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "presentPerfectCont");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/presentPerfectCont/questions";
    }

    @GetMapping("/perfectCont/presentPerfectCont/negative")
    public String presentPerfectContNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "presentPerfectCont");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/presentPerfectCont/negative";
    }
}
