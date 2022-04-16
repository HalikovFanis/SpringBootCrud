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
public class PastPerfectContController {
    private final PhraseService phraseService;

    public PastPerfectContController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/perfectCont/pastPerfectCont/pastPerfectCont")
    public String pastPerfectCont(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/pastPerfectCont/pastPerfectCont";
    }

    @GetMapping("/perfectCont/pastPerfectCont/affirmative")
    public String pastPerfectContAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "pastPerfectCont");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/pastPerfectCont/affirmative";
    }

    @GetMapping("/perfectCont/pastPerfectCont/questions")
    public String pastPerfectContQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "pastPerfectCont");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/pastPerfectCont/questions";
    }

    @GetMapping("/perfectCont/pastPerfectCont/negative")
    public String pastPerfectContNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "pastPerfectCont");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/perfectCont/pastPerfectCont/negative";
    }

    @GetMapping("/perfectCont/{tense}/{form}")
    public String createPhraseFormPerfectCont(@PathVariable(value = "tense") String tense,
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
        return "/perfectCont/" + tense + "/" + tense;
    }

    @PostMapping("/perfectCont/{tense}/{form}")
    public String createPhrasePerfectCont(@PathVariable(value = "tense") String tense,
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
                return "redirect:/perfectCont/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/perfectCont/{tense}/questions";
            case "negativeCreate":
                return "redirect:/perfectCont/{tense}/negative";
        }
        return "redirect:/perfectCont/{tense}/{tense}";
    }

    @GetMapping("/perfectCont/{tense}/delete_phrase/{form}/{id}")
    public String deletePhrasePerfectCont(@PathVariable(value = "id") Long id,
                                          @PathVariable(value = "tense") String tense,
                                          @PathVariable(value = "form") String form) {
        phraseService.deleteById(id);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/perfectCont/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/perfectCont/{tense}/questions";
            case "negativeCreate":
                return "redirect:/perfectCont/{tense}/negative";
        }
        return "redirect:/perfectCont/" + tense + "/" + tense;
    }

    @GetMapping("/perfectCont/{tense}/{form}/{id}")
    public String updatePhraseFormPerfectCont(@PathVariable("id") Long id,
                                              @PathVariable(value = "tense") String tense,
                                              @PathVariable(value = "form") String form,
                                              Model model, Principal principal) {
        Phrase phrase = phraseService.findById(id);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        model.addAttribute("phrase", phrase);
        phrase.setTense(tense);
        return "/edit_phrase";
    }

    @PostMapping("/perfectCont/{tense}/{form}/edit_phrase")
    public String updatePhrasePerfectCont(@PathVariable(value = "tense") String tense,
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
                return "redirect:/perfectCont/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/perfectCont/{tense}/questions";
            case "negativeCreate":
                return "redirect:/perfectCont/{tense}/negative";
        }
        return "/perfectCont/" + tense + "/" + form;
    }
}
