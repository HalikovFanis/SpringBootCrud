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

    @GetMapping("/simple/pastSimple/pastSimple")
    public String pastSimple(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/simple/pastSimple/pastSimple";
    }

    @GetMapping("/simple/pastSimple/affirmative")
    public String pastSimpleAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "pastSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/simple/pastSimple/affirmative";
    }

    @GetMapping("/simple/pastSimple/questions")
    public String pastSimpleQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "pastSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/simple/pastSimple/questions";
    }

    @GetMapping("/simple/pastSimple/negative")
    public String pastSimpleNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "pastSimple");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/simple/pastSimple/negative";
    }

    @GetMapping("/simple/{tense}/{form}")
    public String createPhraseFormSimple(@PathVariable(value = "tense") String tense,
                                   @PathVariable(value = "form") String form,
                                   Phrase phrase_rus, Phrase phrase_eng, Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        model.addAttribute("rusPhrase", phrase_rus);
        model.addAttribute("engPhrase", phrase_eng);
        switch (form) {
            case "affirmativeCreate":
                return "/affirmativeCreate";
            case "questionsCreate":
                return "/questionsCreate";
            case "negativeCreate":
                return "/negativeCreate";
        }
        return "/simple/" + tense + "/" + tense;
    }

    @PostMapping("/simple/{tense}/{form}")
    public String createPhraseSimple(@PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form,
                               @Valid Phrase phrase, BindingResult bindingResult,
                                     Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        phrase.setTense(tense);
        phrase.setForm(form);

        if (bindingResult.hasErrors()) {
            return "/edit_phrase";}

        phraseService.savePhrase(principal, phrase);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/simple/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/simple/{tense}/questions";
            case "negativeCreate":
                return "redirect:/simple/{tense}/negative";
        }
        return "redirect:/simple/{tense}/{tense}";
    }

    @GetMapping("/simple/{tense}/delete_phrase/{form}/{id}")
    public String deletePhraseSimple(@PathVariable(value = "id") Long id,
                               @PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form) {
        phraseService.deleteById(id);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/simple/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/simple/{tense}/questions";
            case "negativeCreate":
                return "redirect:/simple/{tense}/negative";
        }
        return "redirect:/simple/" + tense + "/" + tense;
    }

    @GetMapping("/simple/{tense}/{form}/{id}")
    public String updatePhraseFormSimple(@PathVariable("id") Long id,
                                   @PathVariable(value = "tense") String tense,
                                   @PathVariable(value = "form") String form,
                                   Model model, Principal principal) {
        Phrase phrase = phraseService.findById(id);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        model.addAttribute("phrase", phrase);
        phrase.setTense(tense);
        return "/edit_phrase";
    }

    @PostMapping("/simple/{tense}/{form}/edit_phrase")
    public String updatePhrase(@PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form,
                               @Valid Phrase phrase, BindingResult bindingResult,
                               Model model, Principal principal) {
        phrase.setTense(tense);
        phrase.setForm(form);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        if (bindingResult.hasErrors()) {
            return "/edit_phrase";}
        phraseService.savePhrase(principal, phrase);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/simple/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/simple/{tense}/questions";
            case "negativeCreate":
                return "redirect:/simple/{tense}/negative";
        }
        return "/simple/" + tense + "/" + form;
    }
}
