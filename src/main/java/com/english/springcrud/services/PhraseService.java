package com.english.springcrud.services;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.models.User;
import com.english.springcrud.repository.PhraseRepo;
import com.english.springcrud.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
public class PhraseService {

    private final PhraseRepo phraseRepo;
    private final UserRepo userRepo;

    @Autowired
    public PhraseService(PhraseRepo phraseRepo, UserRepo userRepo) {
        this.phraseRepo = phraseRepo;
        this.userRepo = userRepo;
    }

    public Phrase findById(Long id) {
//        return phraseRepo.getOne(id);
        return phraseRepo.findById(id).orElse(null);
    }

    public List<Phrase> findAll() {
        return phraseRepo.findAll();
    }

    public Phrase savePhrase(Principal principal, Phrase phrase) {
        phrase.setUser(getUserByPrincipal(principal));
        log.info("Save new: {}; Author email: {}", phrase.getRusPhrase()+ " - " + phrase.getEngPhrase(),
                phrase.getUser().getEmail());
        return phraseRepo.save(phrase);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userRepo.findByEmail(principal.getName());
    }

    public void deleteById(Long id) {
        phraseRepo.deleteById(id);
    }

    public List<Phrase> findAllByForm(String form, String tense) {
        if (form != null && tense != null) {
            return phraseRepo.findAllByFormAndTense(form, tense);
        }
        return phraseRepo.findAll();
    }
}
