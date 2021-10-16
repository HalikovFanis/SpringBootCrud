package com.english.springcrud.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "phrases")
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rus_phrase")
    @NotEmpty(message = "Введите фразу на русском языке")
    @Size(max = 200, message = "Слишком длинное предложение!")
    @Pattern(regexp = "^[А-Яа-я]+.{1,200}", message = "Фраза должна быть на русском языке")
    private String rusPhrase;

    @Column(name = "eng_phrase")
    @NotEmpty(message = "Введите фразу на английском языке")
    @Size(max = 200, message = "Слишком длинное предложение!")
    @Pattern(regexp = "^[A-Za-z]+.{1,200}", message = "Фраза должна быть на английском языке")
    private String engPhrase;

    @Column(name = "tense")
    private String tense;

    @Column(name = "form")
    private String form;
}
