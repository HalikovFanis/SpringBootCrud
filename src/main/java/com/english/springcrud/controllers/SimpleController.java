package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller

public class SimpleController {

    private final PhraseService phraseService;
    @Autowired
    public SimpleController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/pastSimple/pastSimple")
    public String pastSimple() {
        return "/pastSimple/pastSimple";
    }

    @GetMapping("/pastSimple/affirmative")
    public String pastSimpleAffirmative(Model model) {
        List<Phrase> phrases = phraseService.findAllByForm("affirmativeCreate");
        model.addAttribute("phrases", phrases);
        return "pastSimple/affirmative";
    }

    @GetMapping("/pastSimple/questions")
    public String pastSimpleQuestions(Model model) {
        List<Phrase> phrases = phraseService.findAllByForm("questionsCreate");
        model.addAttribute("phrases", phrases);
        return "pastSimple/questions";
    }

    @GetMapping("/pastSimple/negative")
    public String pastSimpleNegative(Model model) {
        List<Phrase> phrases = phraseService.findAllByForm("negativeCreate");
        model.addAttribute("phrases", phrases);
        return "pastSimple/negative";
    }


    @GetMapping("/{tense}/{form}")
    public String createPhraseForm(@PathVariable(value = "tense") String tense,
                                   @PathVariable(value = "form") String form,
                                   Phrase phrase_rus, Phrase phrase_eng, Model model) {
        phrase_rus.setRusPhrase("Введите предложение на русском");
        phrase_eng.setEngPhrase("Add english phrase");
        model.addAttribute("rusPhrase", phrase_rus);
        model.addAttribute("engPhrase", phrase_eng);
        if (form.equals("affirmativeCreate")) {
            return "pastSimple/affirmativeCreate";
        } else if (form.equals("questionsCreate")) {
            return "pastSimple/questionsCreate";
        } else if (form.equals("negativeCreate")) {
            return "pastSimple/negativeCreate";
        }
        return "pastSimple";
    }

    @PostMapping("/{tense}/{form}")
    public String createPhrase(@PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form,
                               @Valid Phrase phrase, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/{tense}/{form}";}
        phrase.setTense(tense);
        phrase.setForm(form);
        phraseService.savePhrase(phrase);
        if (form.equals("affirmativeCreate")) {
            return "redirect:/{tense}/affirmative";
        } else if (form.equals("questionsCreate")) {
            return "redirect:/{tense}/questions";
        } else if (form.equals("negativeCreate")) {
            return "redirect:/{tense}/negative";
        }
        return "redirect:/{tense}/{tense}";
    }

    @GetMapping("/{tense}/delete_phrase/{form}/{id}")
    public String deletePhrase(@PathVariable(value = "id") Long id,
                               @PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form) {
        phraseService.deleteById(id);
        if (form.equals("affirmativeCreate")) {
            return "redirect:/{tense}/affirmative";
        } else if (form.equals("questionsCreate")) {
            return "redirect:/{tense}/questions";
        } else if (form.equals("negativeCreate")) {
            return "redirect:/{tense}/negative";
        }
        return "redirect:/{tense}/{tense}";
    }

    @GetMapping("/{tense}/{form}/edit_phrase/{id}")
    public String updatePhraseForm(@PathVariable("id") Long id,
                                   @PathVariable(value = "tense") String tense,
                                   @PathVariable(value = "form") String form,
                                   Model model) {
        Phrase phrase = phraseService.findById(id);
        model.addAttribute("phrase", phrase);
        phrase.setTense(tense);
        return "pastSimple/edit_phrase";
    }

    @PostMapping("/{tense}/{form}/edit_phrase/edit_phrase")
    public String updatePhrase(@PathVariable(value = "tense") String tense,
                               @PathVariable(value = "form") String form,
                               @Valid Phrase phrase, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:A0";
        phrase.setTense(tense);
        phrase.setForm(form);
        phraseService.savePhrase(phrase);
        if (form.equals("affirmativeCreate")) {
            return "redirect:/{tense}/affirmative";
        } else if (form.equals("questionsCreate")) {
            return "redirect:/{tense}/questions";
        } else if (form.equals("negativeCreate")) {
            return "redirect:/{tense}/negative";
        }
        return "redirect:A0";
    }
}
