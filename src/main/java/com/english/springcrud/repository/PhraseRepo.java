package com.english.springcrud.repository;

import com.english.springcrud.models.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhraseRepo extends JpaRepository<Phrase, Long> {
}
