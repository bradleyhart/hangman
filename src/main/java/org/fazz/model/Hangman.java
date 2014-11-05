package org.fazz.model;

import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.builder.EqualsBuilder.*;
import static org.apache.commons.lang3.builder.HashCodeBuilder.*;

public class Hangman {

    private String id;
    private Word word;
    private Set<Guess> guesses;

    private Hangman(Word word) {
        this.word = word;
        this.guesses = new HashSet<>();
    }

    public Guess take(Guess guess){
        this.guesses.add(guess);
        return guess;
    }

    public String getId() {
        return id;
    }

    public Set<Guess> getGuesses() {
        return guesses;
    }

    public Word getWord() {
        return word;
    }

    public static Hangman hangman(Word word) {
        return new Hangman(word);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }
}
