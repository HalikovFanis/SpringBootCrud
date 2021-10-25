package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/simple")
public class SimpleController {

    private final PhraseService phraseService;
    @Autowired
    public SimpleController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("pastSimple/pastSimple")
    public String pastSimple() {
        return "simple/pastSimple/pastSimple";
    }

    @GetMapping("pastSimple/pastSimpleAffirmative")
    public String pastSimpleAffirmative(Model model) {
        List<Phrase> phrases = phraseService.findAll();
        model.addAttribute("phrases", phrases);
        return "simple/pastSimple/pastSimpleAffirmative";
    }

    @GetMapping("pastSimple/pastSimpleNegative")
    public String pastSimpleNegative() {
        return "simple/pastSimple/pastSimpleNegative";
    }

    @GetMapping("pastSimple/pastSimpleQuestions")
    public String pastSimpleQuestions() {
        return "simple/pastSimple/pastSimpleQuestions";
    }

    @GetMapping("/pastSimple/pastSimpleCreatePhrase")
    public String createPhraseForm(Phrase phrase_rus, Phrase phrase_eng, Model model) {
        phrase_rus.setRusPhrase("Введите предложение на русском");
        phrase_eng.setEngPhrase("Add english phrase");
        model.addAttribute("rusPhrase", phrase_rus);
        model.addAttribute("engPhrase", phrase_eng);
        return "/simple/pastSimple/pastSimpleCreatePhrase";
    }

    /*@PostMapping("/{tense}/pastSimpleCreatePhrase")
    public String createPhrase(@PathVariable("tense") String tense,
                                @Valid Phrase phrase, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/simple/{tense}/pastSimpleCreatePhrase";}
        phrase.setTense(tense);
        phraseService.savePhrase(phrase);
        return "redirect:/simple/{tense}/pastSimpleAffirmative";
    }*/
    @PostMapping("/{tense}/{form}")
    public String createPhrase(@PathVariable("tense") String tense,
                               @PathVariable("form") String form,
                               @Valid Phrase phrase, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/simple/{tense}/{form}";}
        phrase.setTense(tense);
        phrase.setForm(form);
        phraseService.savePhrase(phrase);
        return "redirect:/simple/{tense}/pastSimpleAffirmative";
    }

    @GetMapping("/{tense}/delete_phrase/{id}")
    public String deletePhrase(@PathVariable("id") Long id,
                               @PathVariable("tense") String tense) {
        phraseService.deleteById(id);
        return "redirect:/simple/{tense}/pastSimpleAffirmative";
    }
}
