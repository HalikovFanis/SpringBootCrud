package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class FuturePerfectContController {
    private final PhraseService phraseService;

    public FuturePerfectContController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/perfectCont/futurePerfectCont/futurePerfectCont")
    public String futurePerfectCont(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/futurePerfectCont/futurePerfectCont";
    }

    @GetMapping("/perfectCont/futurePerfectCont/affirmative")
    public String futurePerfectContAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "futurePerfectCont");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/futurePerfectCont/affirmative";
    }

    @GetMapping("/perfectCont/futurePerfectCont/questions")
    public String futurePerfectContQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "futurePerfectCont");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/futurePerfectCont/questions";
    }

    @GetMapping("/perfectCont/futurePerfectCont/negative")
    public String futurePerfectContNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "futurePerfectCont");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/futurePerfectCont/negative";
    }
}
