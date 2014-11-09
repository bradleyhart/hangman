package org.fazz.model;

import org.junit.Test;

import java.util.Set;

import static org.fazz.model.Guess.guess;
import static org.fazz.model.Hangman.hangman;
import static org.fazz.model.Hit.hit;
import static org.fazz.model.Word.word;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HangmanTest {

    @Test
    public void getNumberOfGuesses(){
        Hangman hangman = hangman(word("panda"));
        hangman.take(guess('a'));
        hangman.take(guess('b'));

        assertThat(hangman.getAttempts(), is(2));
    }

    @Test
    public void getWordLength(){
        Hangman hangman = hangman(word("panda"));

        assertThat(hangman.getWordLength(), is(5));
    }

    @Test
    public void getHits(){
        Hangman hangman = hangman(word("panda"));
        hangman.take(guess('a'));
        hangman.take(guess('b'));
        hangman.take(guess('n'));

        Set<Hit> hits = hangman.getHits();

        assertThat(hits.size(), is(3));
        assertThat(hits.contains(hit(1, 'a')), is(true));
        assertThat(hits.contains(hit(4, 'a')), is(true));
        assertThat(hits.contains(hit(2, 'n')), is(true));

    }

    @Test
    public void getHitsWorksForLastAndFirstCharacters(){
        Hangman hangman = hangman(word("panda"));
        hangman.take(guess('p'));
        hangman.take(guess('a'));

        Set<Hit> hits = hangman.getHits();

        assertThat(hits.size(), is(3));
        assertThat(hits.contains(hit(0, 'p')), is(true));
        assertThat(hits.contains(hit(1, 'a')), is(true));
        assertThat(hits.contains(hit(4, 'a')), is(true));

    }

//    <span data-word-index="${index}">${hits.characterAtIndex(index)}</span>

    @Test
    public void canKnowIfThereIsAHitAtIndex(){
        Hangman hangman = hangman(word("panda"));
        hangman.take(guess('p'));
        hangman.take(guess('a'));

        assertThat(hangman.isHitAtIndex(0), is(true));
        assertThat(hangman.isHitAtIndex(1), is(true));
        assertThat(hangman.isHitAtIndex(2), is(false));
        assertThat(hangman.isHitAtIndex(3), is(false));
        assertThat(hangman.isHitAtIndex(4), is(true));
    }

    @Test
    public void canGetCharacterAtIndex(){
        Hangman hangman = hangman(word("panda"));
        hangman.take(guess('p'));
        hangman.take(guess('a'));

        assertThat(hangman.characterAtIndex(0), is('p'));
        assertThat(hangman.characterAtIndex(1), is('a'));
        assertThat(hangman.characterAtIndex(4), is('a'));
    }

}
