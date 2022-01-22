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
public class PastSimpleController {

    private final PhraseService phraseService;
    @Autowired
    public PastSimpleController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/pastSimple/pastSimple")
    public String pastSimple(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/pastSimple/pastSimple";
    }

    @GetMapping("/pastSimple/affirmative")
    public String pastSimpleAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "pastSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "pastSimple/affirmative";
    }

    @GetMapping("/pastSimple/questions")
    public String pastSimpleQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "pastSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "pastSimple/questions";
    }

    @GetMapping("/pastSimple/negative")
    public String pastSimpleNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "pastSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "pastSimple/negative";
    }

    @GetMapping("/{tense}/{form}")
    public String createPhraseForm(@PathVariable(value = "tense") String tense,
                                   @PathVariable(value = "form") String form,
                                   Phrase phrase_rus, Phrase phrase_eng, Model model) {
        model.addAttribute("rusPhrase", phrase_rus);
        model.addAttribute("engPhrase", phrase_eng);
        switch (form) {
            case "affirmativeCreate":
                return "pastSimple/affirmativeCreate";
            case "questionsCreate":
                return "pastSimple/questionsCreate";
            case "negativeCreate":
                return "pastSimple/negativeCreate";
        }
        return tense + "/" + tense;
    }

    @PostMapping("/{tense}/{form}")
    public String createPhrase(@PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form,
                               @Valid Phrase phrase, BindingResult bindingResult, Principal principal) {
        phrase.setTense(tense);
        phrase.setForm(form);

        if (bindingResult.hasErrors()) {
            return tense + "/" + form;}

        phraseService.savePhrase(principal, phrase);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/{tense}/questions";
            case "negativeCreate":
                return "redirect:/{tense}/negative";
        }
        return "redirect:/{tense}/{tense}";
    }

    @GetMapping("/{tense}/delete_phrase/{form}/{id}")
    public String deletePhrase(@PathVariable(value = "id") Long id,
                               @PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form) {
        phraseService.deleteById(id);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/{tense}/questions";
            case "negativeCreate":
                return "redirect:/{tense}/negative";
        }
        return "redirect:/" + tense + "/" + tense;
    }

    @GetMapping("/{tense}/{form}/{id}")
    public String updatePhraseForm(@PathVariable("id") Long id,
                                   @PathVariable(value = "tense") String tense,
                                   @PathVariable(value = "form") String form,
                                   Model model) {
        Phrase phrase = phraseService.findById(id);
        model.addAttribute("phrase", phrase);
        phrase.setTense(tense);
        return "pastSimple/edit_phrase";
    }

    @PostMapping("/{tense}/{form}/edit_phrase")
    public String updatePhrase(@PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form,
                               @Valid Phrase phrase, BindingResult bindingResult, Principal principal) {
        phrase.setTense(tense);
        phrase.setForm(form);

        if (bindingResult.hasErrors()) {
            return tense + "/edit_phrase";}
        phraseService.savePhrase(principal, phrase);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/{tense}/questions";
            case "negativeCreate":
                return "redirect:/{tense}/negative";
        }
        return tense + "/" + form;
    }
}
