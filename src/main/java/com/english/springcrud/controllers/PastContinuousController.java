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
public class PastContinuousController {

    private final PhraseService phraseService;
    public PastContinuousController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/continuous/pastContinuous/pastContinuous")
    public String pastContinuous(Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/pastContinuous/pastContinuous";
    }

    @GetMapping("/continuous/pastContinuous/affirmative")
    public String pastContinuousAffirmative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate", "pastContinuous");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/pastContinuous/affirmative";
    }

    @GetMapping("/continuous/pastContinuous/questions")
    public String pastContinuousQuestions(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate", "pastContinuous");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/pastContinuous/questions";
    }

    @GetMapping("/continuous/pastContinuous/negative")
    public String pastContinuousNegative(Model model, Principal principal) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate", "pastContinuous");
        model.addAttribute("phrases", phrases);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        return "/continuous/pastContinuous/negative";
    }

    @GetMapping("/continuous/{tense}/{form}")
    public String createPhraseFormContinuous(@PathVariable(value = "tense") String tense,
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
        return "/continuous/" + tense + "/" + tense;
    }

    @PostMapping("/continuous/{tense}/{form}")
    public String createPhraseContinues(@PathVariable(value = "tense") String tense,
                                        @PathVariable(value = "form") String form,
                                        @Valid Phrase phrase, BindingResult bindingResult, Principal principal) {
        phrase.setTense(tense);
        phrase.setForm(form);

        if (bindingResult.hasErrors()) {
            return "/edit_phrase";}

        phraseService.savePhrase(principal, phrase);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/continuous/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/continuous/{tense}/questions";
            case "negativeCreate":
                return "redirect:/continuous/{tense}/negative";
        }
        return "redirect:/continuous/{tense}/{tense}";
    }

    @GetMapping("/continuous/{tense}/delete_phrase/{form}/{id}")
    public String deletePhraseContinuous(@PathVariable(value = "id") Long id,
                               @PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form) {
        phraseService.deleteById(id);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/continuous/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/continuous/{tense}/questions";
            case "negativeCreate":
                return "redirect:/continuous/{tense}/negative";
        }
        return "redirect:/continuous/" + tense + "/" + tense;
    }

    @GetMapping("/continuous/{tense}/{form}/{id}")
    public String updatePhraseFormContinuous(@PathVariable("id") Long id,
                                   @PathVariable(value = "tense") String tense,
                                   @PathVariable(value = "form") String form,
                                   Model model, Principal principal) {
        Phrase phrase = phraseService.findById(id);
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        model.addAttribute("phrase", phrase);
        phrase.setTense(tense);
        return "/edit_phrase";
    }

    @PostMapping("/continuous/{tense}/{form}/edit_phrase")
    public String updatePhraseContinuous(@PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form,
                               @Valid Phrase phrase, BindingResult bindingResult, Principal principal) {
        phrase.setTense(tense);
        phrase.setForm(form);

        if (bindingResult.hasErrors()) {
            return "/edit_phrase";}
        phraseService.savePhrase(principal, phrase);
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/continuous/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/continuous/{tense}/questions";
            case "negativeCreate":
                return "redirect:/continuous/{tense}/negative";
        }
        return "/continuous/" + tense + "/" + form;
    }
}
