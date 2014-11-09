package org.fazz.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static org.apache.commons.lang3.builder.EqualsBuilder.*;
import static org.apache.commons.lang3.builder.HashCodeBuilder.*;
import static org.fazz.model.Hit.hit;

public class Hangman implements Serializable {

    private String id;
    private Word word;
    private Set<Guess> guesses;

    private Hangman(Word word) {
        this.word = word;
        this.guesses = new HashSet<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public Guess take(Guess guess) {
        this.guesses.add(guess);
        return guess;
    }

    public String getId() {
        return id;
    }

    public Set<Guess> getGuesses() {
        return unmodifiableSet(guesses);
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

    public Set<Hit> getHits() {
        Set<Hit> hits = new HashSet<>();
        guesses.stream().forEach((guess) -> addHits(hits, guess));
        return hits;
    }

    private void addHits(Set<Hit> hits, Guess guess) {
        int index = addHit(hits, guess, 0);
        while (isHit(index)) {
            index = addHit(hits, guess, index + 1);
        }
    }

    private boolean isHit(int index) {
        return index >= 0;
    }

    private int addHit(Set<Hit> hits, Guess guess, Integer startIndex) {
        int index = word.toString().indexOf(guess.toString(), startIndex);
        if (index != -1) {
            hits.add(hit(index, guess.getValue()));
        }
        return index;
    }

    public Integer getAttempts() {
        return getGuesses().size();
    }

    public Integer getWordLength() {
        return word.toString().length();
    }

    public Boolean isHitAtIndex(int index) {
        for (Hit hit : getHits()) {
            if(hit.getIndex() == index){
                return true;
            }
        }
        return false;
    }

    public Character characterAtIndex(int index) {
        return getWord().toString().toCharArray()[index];
    }
}
