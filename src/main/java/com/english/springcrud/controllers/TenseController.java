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
    public String simple(@PathVariable(value = "first") String first,
                         @PathVariable(value = "tense") String tense,
                         Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/" + first + "/" + tense + "/" + tense;
    }

    @GetMapping("/{first}/{tense}/affirmative")
    public String simpleAffirmative(@PathVariable(value = "first") String first,
                                    @PathVariable(value = "tense") String tense,
                                    Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", tense);
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/" + first + "/" + tense + "/affirmative";
    }

    @GetMapping("/{first}/{tense}/questions")
    public String simpleQuestions(@PathVariable(value = "first") String first,
                                  @PathVariable(value = "tense") String tense,
                                  Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", tense);
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/" + first + "/" + tense + "/questions";
    }

    @GetMapping("/{first}/{tense}/negative")
    public String simpleNegative(@PathVariable(value = "first") String first,
                                 @PathVariable(value = "tense") String tense,
                                 Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", tense);
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/" + first + "/" + tense + "/negative";
    }

    @GetMapping("/{first}/{tense}/{form}")
    public String createPhraseFormSimple(@PathVariable(value = "first") String first,
                                         @PathVariable(value = "tense") String tense,
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
        return "/" + first + "/" + tense + "/" + tense;
    }

    @PostMapping("/{first}/{tense}/{form}")
    public String createPhraseSimple(@PathVariable(value = "first") String first,
                                     @PathVariable(value = "tense") String tense,
                                     @PathVariable(value = "form") String form,
                                     @Valid Phrase phrase,
                                     BindingResult bindingResult,
                                     Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        phrase.setTense(tense);
        phrase.setForm(form);

        if (bindingResult.hasErrors()) {
            switch (form) {
                case "affirmativeCreate":
                    return "/affirmativeCreate";
                case "questionsCreate":
                    return "/questionsCreate";
                case "negativeCreate":
                    return "/negativeCreate";
            }
        }

        phraseService.savePhrase(principal, phrase);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/{first}/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/{first}/{tense}/questions";
            case "negativeCreate":
                return "redirect:/{first}/{tense}/negative";
        }
        return "redirect:/{first}/{tense}/{tense}";
    }

    @GetMapping("/{first}/{tense}/delete_phrase/{form}/{id}")
    public String deletePhraseSimple(@PathVariable(value = "first") String first,
                                     @PathVariable(value = "id") Long id,
                                     @PathVariable(value = "tense") String tense,
                                     @PathVariable(value = "form") String form) {
        phraseService.deleteById(id);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/{first}/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/{first}/{tense}/questions";
            case "negativeCreate":
                return "redirect:/{first}/{tense}/negative";
        }
        return "redirect:/" + first + "/" + tense + "/" + tense;
    }

    @GetMapping("/{first}/{tense}/{form}/{id}")
    public String updatePhraseFormSimple(@PathVariable(value = "first") String first,
                                         @PathVariable("id") Long id,
                                         @PathVariable(value = "tense") String tense,
                                         @PathVariable(value = "form") String form,
                                         Model model, Principal principal) {
        Phrase phrase = phraseService.findById(id);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        model.addAttribute("phrase", phrase);
        phrase.setTense(tense);
        return "/edit_phrase";
    }

    @PostMapping("/{first}/{tense}/{form}/edit_phrase")
    public String updatePhrase(@PathVariable(value = "first") String first,
                               @PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form,
                               @Valid Phrase phrase, BindingResult bindingResult,
                               Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        phrase.setTense(tense);
        phrase.setForm(form);

        if (bindingResult.hasErrors()) {
            return "/edit_phrase";
        }
        phraseService.savePhrase(principal, phrase);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/{first}/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/{first}/{tense}/questions";
            case "negativeCreate":
                return "redirect:/{first}/{tense}/negative";
        }
        return "/" + first + "/" + tense + "/" + form;
    }
}
