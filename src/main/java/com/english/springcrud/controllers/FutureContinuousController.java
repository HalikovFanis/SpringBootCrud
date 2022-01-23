package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class FutureContinuousController {
    private final PhraseService phraseService;

    public FutureContinuousController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/continuous/futureContinuous/affirmative")
    public String futureContinuousAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "futureContinuous");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/futureContinuous/affirmative";
    }

    @GetMapping("/continuous/futureContinuous/questions")
    public String futureContinuousQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "futureContinuous");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/futureContinuous/questions";
    }

    @GetMapping("/continuous/futureContinuous/negative")
    public String futureContinuousNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "futureContinuous");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/futureContinuous/negative";
    }
}
