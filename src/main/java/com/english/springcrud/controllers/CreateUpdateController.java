package com.english.springcrud.controllers;


import com.english.springcrud.dto.PhraseDTO;
import com.english.springcrud.models.Phrase;
import com.english.springcrud.services.PhraseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CreateUpdateController {

    private final PhraseService phraseService;
    private final ModelMapper modelMapper;

    @Autowired
    public CreateUpdateController(PhraseService phraseService, ModelMapper modelMapper) {
        this.phraseService = phraseService;
        this.modelMapper = modelMapper;
    }

    public String selectForm(String form, String result) {
        switch (form) {
            case "affirmativeCreate":
                return "redirect:/{first}/{tense}/affirmative";
            case "questionsCreate":
                return "redirect:/{first}/{tense}/questions";
            case "negativeCreate":
                return "redirect:/{first}/{tense}/negative";
        }
        return result;
    }

    public Phrase convertToPhrase(PhraseDTO phraseDTO) {
        return modelMapper.map(phraseDTO, Phrase.class);
    }

    public PhraseDTO convertToPhraseDTO(Phrase phrase) {
        return modelMapper.map(phrase, PhraseDTO.class);
    }

    @GetMapping("/{first}/{tense}/{form}")
    public String createPhraseForm(@PathVariable("first") String first,
                                   @PathVariable("tense") String tense,
                                   @PathVariable("form") String form,
                                   PhraseDTO phrase_rus, PhraseDTO phrase_eng,
                                   Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        model.addAttribute("rusPhrase", phrase_rus);
        model.addAttribute("engPhrase", phrase_eng);

        if (form.equals("affirmativeCreate") ||
                form.equals("questionsCreate") ||
                form.equals("negativeCreate")) {
            return "/" + form;
        } else {
            return "/" + first + "/" + tense + "/" + tense;
        }
    }

    @PostMapping("/{first}/{tense}/{form}")
    public String createPhrase(@PathVariable("first") String first,
                               @PathVariable("tense") String tense,
                               @PathVariable("form") String form,
                               @Valid PhraseDTO phraseDTO,
                               BindingResult bindingResult,
                               Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        phraseDTO.setTense(tense);
        phraseDTO.setForm(form);

        if (bindingResult.hasErrors()) {
            if (form.equals("affirmativeCreate") ||
                    form.equals("questionsCreate") ||
                    form.equals("negativeCreate")) {
                return "/" + form;
            }
        }

        phraseService.savePhrase(principal, convertToPhrase(phraseDTO));

        return selectForm(form, "redirect:/" + first + "/" + tense + "/" + tense);
    }

    @GetMapping("/{first}/{tense}/delete_phrase/{form}/{id}")
    public String deletePhrase(@PathVariable("first") String first,
                               @PathVariable("id") Long id,
                               @PathVariable("tense") String tense,
                               @PathVariable("form") String form) {
        phraseService.deleteById(id);

        return selectForm(form, "redirect:/" + first + "/" + tense + "/" + tense);
    }

    @GetMapping("/{first}/{tense}/{form}/{id}")
    public String updatePhraseForm(@PathVariable("first") String first,
                                   @PathVariable("id") Long id,
                                   @PathVariable("tense") String tense,
                                   @PathVariable("form") String form,
                                   Model model, Principal principal) {
        PhraseDTO phraseDTO = convertToPhraseDTO(phraseService.findById(id));
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        model.addAttribute("phraseDTO", phraseDTO);
        phraseDTO.setTense(tense);
        return "/edit_phrase";
    }

    @PostMapping("/{first}/{tense}/{form}/edit_phrase")
    public String updatePhrase(@PathVariable("first") String first,
                               @PathVariable("tense") String tense,
                               @PathVariable("form") String form,
                               @Valid PhraseDTO phraseDTO,
                               BindingResult bindingResult,
                               Model model, Principal principal) {
        model.addAttribute("user", phraseService.getUserByPrincipal(principal));
        phraseDTO.setTense(tense);
        phraseDTO.setForm(form);

        if (bindingResult.hasErrors()) {
            return "/edit_phrase";
        }

        phraseService.savePhrase(principal, convertToPhrase(phraseDTO));
        return selectForm(form, "/" + first + "/" + tense + "/" + form);
    }
}
