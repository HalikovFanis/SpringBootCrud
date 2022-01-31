package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class PresentContinuousController {
    private final PhraseService phraseService;

    public PresentContinuousController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/continuous/presentContinuous/presentContinuous")
    public String presentContinuous(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/presentContinuous/presentContinuous";
    }

    @GetMapping("/continuous/presentContinuous/affirmative")
    public String presentContinuousAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "presentContinuous");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/presentContinuous/affirmative";
    }

    @GetMapping("/continuous/presentContinuous/questions")
    public String presentContinuousQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "presentContinuous");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/presentContinuous/questions";
    }

    @GetMapping("/continuous/presentContinuous/negative")
    public String presentContinuousNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "presentContinuous");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/presentContinuous/negative";
    }

}
