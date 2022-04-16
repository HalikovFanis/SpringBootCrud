package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class PastPerfectController {
    private final PhraseService phraseService;

    public PastPerfectController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/perfect/pastPerfect/pastPerfect")
    public String pastPerfect(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/pastPerfect/pastPerfect";
    }

    @GetMapping("/perfect/pastPerfect/affirmative")
    public String pastPerfectAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "pastPerfect");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/pastPerfect/affirmative";
    }

    @GetMapping("/perfect/pastPerfect/questions")
    public String pastPerfectQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "pastPerfect");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/pastPerfect/questions";
    }

    @GetMapping("/perfect/pastPerfect/negative")
    public String pastPerfectNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "pastPerfect");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfect/pastPerfect/negative";
    }

    @GetMapping("/perfect/{tense}/{form}")
    public String createPhraseFormPerfect(@PathVariable(value = "tense") String tense,
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
        return "/perfect/" + tense + "/" + tense;
    }

    @PostMapping("/perfect/{tense}/{form}")
    public String createPhrasePerfect(@PathVariable(value = "tense") String tense,
                                      @PathVariable(value = "form") String form,
                                      @Valid Phrase phrase, BindingResult bindingResult,
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
                return "redirect:/perfect/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/perfect/{tense}/questions";
            case "negativeCreate":
                return "redirect:/perfect/{tense}/negative";
        }
        return "redirect:/perfect/{tense}/{tense}";
    }

    @GetMapping("/perfect/{tense}/delete_phrase/{form}/{id}")
    public String deletePhrasePerfect(@PathVariable(value = "id") Long id,
                                      @PathVariable(value = "tense") String tense,
                                      @PathVariable(value = "form") String form) {
        phraseService.deleteById(id);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/perfect/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/perfect/{tense}/questions";
            case "negativeCreate":
                return "redirect:/perfect/{tense}/negative";
        }
        return "redirect:/perfect/" + tense + "/" + tense;
    }

    @GetMapping("/perfect/{tense}/{form}/{id}")
    public String updatePhraseFormPerfect(@PathVariable("id") Long id,
                                          @PathVariable(value = "tense") String tense,
                                          @PathVariable(value = "form") String form,
                                          Model model, Principal principal) {
        Phrase phrase = phraseService.findById(id);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        model.addAttribute("phrase", phrase);
        phrase.setTense(tense);
        return "/edit_phrase";
    }

    @PostMapping("/perfect/{tense}/{form}/edit_phrase")
    public String updatePhrasePerfect(@PathVariable(value = "tense") String tense,
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
                return "redirect:/perfect/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/perfect/{tense}/questions";
            case "negativeCreate":
                return "redirect:/perfect/{tense}/negative";
        }
        return "/perfect/" + tense + "/" + form;
    }
}
