package com.english.springcrud.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "phrases")
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rus_phrase")
    @NotEmpty(message = "Напишите фразу")
    @Size(max = 200, message = "Слишком длинное предложение!")
    @Pattern(regexp = "^[А-Яа-я]+.{1,200}", message = "Фраза должна быть на русском языке")
    private String rusPhrase;

    @Column(name = "eng_phrase")
    @NotEmpty(message = "Напишите фразу")
    @Size(max = 200, message = "Слишком длинное предложение!")
    @Pattern(regexp = "^[A-Za-z]+.{1,200}", message = "Фраза должна быть на английском языке")
    private String engPhrase;

    @Column(name = "tense")
    private String tense;

    @Column(name = "form")
    private String form;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
