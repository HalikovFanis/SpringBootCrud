package com.english.springcrud.services;

import com.english.springcrud.models.Phrase;
import com.english.springcrud.repository.PhraseRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PhraseServiceTest {

    @Mock
    private PhraseRepo phraseRepo;

    @InjectMocks
    private PhraseService phraseService;

    List<Phrase> phrases = new ArrayList<>();

    @BeforeEach
    void getPhrases() {
        Phrase phrase1 = new Phrase();
        Phrase phrase2 = new Phrase();
        Phrase phrase3 = new Phrase();
        phrase1.setId(1L);
        phrase1.setTense("pastSimple");
        phrase1.setForm("affirmative");
        phrase2.setId(2L);
        phrase2.setTense("pastContinuous");
        phrase2.setForm("negative");
        phrase3.setId(3L);
        phrase3.setTense("pastSimple");
        phrase3.setForm("affirmative");
        phrases = List.of(phrase1, phrase2, phrase3);
    }


    @Test
    @DisplayName("Сервис находит фразу по ID")
    public void shouldReturnPhraseById() {
        Phrase phrase2 = phrases.get(1);
        Mockito.when(phraseRepo.findById(2L)).thenReturn(Optional.of(phrase2));

        Phrase actual = phraseService.findById(2L);

        Assertions.assertNull(phraseService.findById(15L));
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(phrase2.getId(), actual.getId());
        Assertions.assertEquals(phrase2.getEngPhrase(), actual.getEngPhrase());
    }

    @Test
    @DisplayName("Сервис сохранения фразы")
    public void shouldSavePhrase() {
        Phrase phrase3 = phrases.get(2);
        phrase3.setEngPhrase("Hello for save");

        Mockito.when(phraseRepo.save(phrase3)).thenReturn(phrase3);

        Phrase actualSave = phraseService.savePhrase(any(Principal.class), phrase3);

        Assertions.assertNotNull(actualSave);
        Assertions.assertEquals(phrase3, actualSave);
    }

    @Test
    @DisplayName("Получаем весь список фраз при tense==NULL or form==NULL")
    public void shouldFindAllByFormAndTenseWhenFormOrTenseIsNull() {

        Mockito.when(phraseRepo.findAll()).thenReturn(phrases);

        List<Phrase> actualPhrases = phraseService.findAllByFormAndTense(null, null);

        Assertions.assertNotNull(actualPhrases);
        Assertions.assertEquals(3, actualPhrases.size());
    }

    @Test
    @DisplayName("Получаем список фраз по фильтру tense and form")
    public void shouldFindAllByFormAndTense() {
        String tense = "pastSimple";
        String form = "affirmative";
        List<Phrase> phrasesFilter = phrases.stream()
                .filter(e -> e.getTense().equals(tense))
                .filter(e -> e.getForm().equals(form))
                .collect(Collectors.toList());

        Mockito.when(phraseRepo.findAllByFormAndTense(form, tense))
                .thenReturn(phrasesFilter);

        List<Phrase> actualPhrases = phraseService.findAllByFormAndTense(form, tense);

        Assertions.assertEquals(2, actualPhrases.size());
    }
}
