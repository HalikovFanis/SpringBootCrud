package com.english.springcrud.repository;

import com.english.springcrud.models.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhraseRepo extends JpaRepository<Phrase, Long> {
    List<Phrase> findAllByForm(String form);
}
