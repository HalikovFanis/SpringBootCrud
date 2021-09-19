package com.english.springcrud.services;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.repository.PhraseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhraseService {

    private final PhraseRepo phraseRepo;
    @Autowired
    public PhraseService(PhraseRepo phraseRepo) {
        this.phraseRepo = phraseRepo;
    }

    public Phrase findById(Long id) {
        return phraseRepo.getOne(id);
    }

    public List<Phrase> findAll() {
        return phraseRepo.findAll();
    }

    public Phrase savePhrase(Phrase phrase) {
        return phraseRepo.save(phrase);
    }

    public void deleteById(Long id) {
        phraseRepo.deleteById(id);
    }
}
