package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
public class TenseController {

    private final PhraseService phraseService;

    @Autowired
    public TenseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/{first}/{tense}/{tense}/")
    public String tense(@PathVariable("first") String first,
                         @PathVariable("tense") String tense,
                         Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/" + first + "/" + tense + "/" + tense;
    }

    @GetMapping("/{first}/{tense}/affirmative")
    public String affirmative(@PathVariable("first") String first,
                              @PathVariable("tense") String tense,
                              Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", tense);
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/" + first + "/" + tense + "/affirmative";
    }

    @GetMapping("/{first}/{tense}/questions")
    public String questions(@PathVariable("first") String first,
                            @PathVariable("tense") String tense,
                            Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", tense);
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/" + first + "/" + tense + "/questions";
    }

    @GetMapping("/{first}/{tense}/negative")
    public String negative(@PathVariable("first") String first,
                           @PathVariable("tense") String tense,
                           Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", tense);
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/" + first + "/" + tense + "/negative";
    }

}
