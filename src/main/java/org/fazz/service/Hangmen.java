package org.fazz.service;

import org.fazz.model.Hangman;
import org.fazz.model.Word;

import java.util.List;

public interface Hangmen {

    Hangman add(Hangman hangman);

    Hangman get(String ca);

    List<Hangman> all();

    void add(Word word);

    Word getRandomWord();

    Hangman update(Hangman hangman);

    void delete(Hangman hangman);
}
