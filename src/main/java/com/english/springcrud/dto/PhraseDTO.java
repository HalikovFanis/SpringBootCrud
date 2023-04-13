package com.english.springcrud.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class PhraseDTO {

    @NotEmpty(message = "Напишите фразу")
    @Size(max = 200, message = "Слишком длинное предложение!")
    @Pattern(regexp = "^[А-Яа-я]+.{1,200}", message = "Фраза должна быть на русском языке!")
    private String rusPhrase;

    @NotEmpty(message = "Напишите фразу")
    @Size(max = 200, message = "Слишком длинное предложение!")
    @Pattern(regexp = "^[A-Za-z]+.{1,200}", message = "Фраза должна быть на английском языке!")
    private String engPhrase;

    private String tense;

    private String form;

}
