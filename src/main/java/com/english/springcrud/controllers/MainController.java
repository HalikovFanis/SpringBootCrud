package com.english.springcrud.controllers;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    private final PhraseService phraseService;
    @Autowired
    public MainController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/A0")
    public String a0(Model model) {
        List<Phrase> phrases = phraseService.findAll();
        model.addAttribute("phrases", phrases);
        return "A0";
    }

    @GetMapping("/create_phrase")
    public String createPhraseForm(Phrase phrase) {
        return "create_phrase";
    }

    @PostMapping("/create_phrase")
    public String createPhrase(@Valid Phrase phrase, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/create_phrase";
        phraseService.savePhrase(phrase);
        return "redirect:/A0";
    }

    @GetMapping("/delete_phrase/{id}")
    public String deletePhrase(@PathVariable("id") Long id) {
        phraseService.deleteById(id);
        return "redirect:/A0";
    }

    @GetMapping("/edit_phrase/{id}")
    public String updatePhraseForm(@PathVariable("id") Long id, Model model) {
        Phrase phrase = phraseService.findById(id);
        model.addAttribute("phrase", phrase);
        return "edit_phrase";
    }

    @PostMapping("/edit_phrase")
    public String updatePhrase(@Valid Phrase phrase, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/edit_phrase";
        phraseService.savePhrase(phrase);
        return "redirect:/A0";
    }
}
