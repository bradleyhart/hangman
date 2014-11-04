package org.fazz.service;

import org.fazz.model.Hangman;
import org.fazz.model.Word;
import org.fazz.query.LetterQuery;

import java.util.List;

public interface Hangmen {

    void add(Hangman hangman);

    Hangman get(String ca);

    List<Hangman> all();

    List<Hangman> match(LetterQuery letterQuery);

    void add(Word word);

    Word getRandomWord();
}
