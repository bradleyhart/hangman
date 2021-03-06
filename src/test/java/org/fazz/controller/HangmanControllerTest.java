package org.fazz.controller;

import org.fazz.model.Guess;
import org.fazz.model.Hangman;
import org.fazz.model.Hit;
import org.fazz.service.Hangmen;
import org.fazz.session.HangmanSessionResolver;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.fazz.model.Hangman.hangman;
import static org.fazz.model.Hit.hit;
import static org.fazz.model.Word.word;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HangmanControllerTest {

    private HangmanSessionResolver hangmanSessionResolver = mock(HangmanSessionResolver.class);
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private final Hangmen hangmen = mock(Hangmen.class);
    private HangmanController hangmanController = new HangmanController(hangmanSessionResolver, hangmen);

    @Test
    public void returnCorrectView(){
        when(hangmanSessionResolver.getOrCreateHangman(request, response)).thenReturn(hangman(word("")));

        ModelAndView modelAndView = hangmanController
                .hangman(request, response);

        assertThat(modelAndView.getViewName(), is(equalTo("hangman")));
    }

    @Test
    public void allHangmen(){
        ArrayList<Hangman> hangmen = new ArrayList<Hangman>() {{
            add(hangman(word("one word")));
            add(hangman(word("two words")));
        }};
        when(this.hangmen.all()).thenReturn(hangmen);

        ModelAndView modelAndView = hangmanController
                .hangmen();

        assertThat(modelAndView.getViewName(), is(equalTo("hangmen")));
        assertThat((ArrayList<Hangman>)modelAndView.getModel().get("hangmen"), is(equalTo(hangmen)));
    }

    @Test
    public void addHangmanToModel(){
        Hangman hangman = hangman(word(""));
        hangman.take(Guess.guess('a'));
        hangman.take(Guess.guess('b'));

        when(hangmanSessionResolver.getOrCreateHangman(request, response)).thenReturn(hangman);

        ModelAndView modelAndView = hangmanController
                .hangman(request, response);

        assertThat((Hangman)modelAndView.getModel().get("hangman"), is(hangman));
    }

    @Test
    public void guessAddedToHangmanAndUpdated(){
        when(hangmen.get("id")).thenReturn(hangman(word("abc")));

        Hangman hangman = hangmanController.guess("id", 'a');

        assertThat(hangman.getGuesses().contains(Guess.guess('a')), is(true));
        verify(hangmen).update(hangman);
    }

    @Test
    public void guessAddedToHangmanAndUpdatedWithModelWithView(){
        Hangman hangman = hangman(word("abc"));
        when(hangmen.get("id")).thenReturn(hangman);
        when(hangmen.update(hangman)).thenReturn(hangman);

        ModelAndView modelAndView = hangmanController.hangmanGuess("id", 'a');

        assertThat(((Hangman)modelAndView.getModel().get("hangman")).getGuesses().contains(Guess.guess('a')), is(true));
        verify(hangmen).update(hangman);
    }

}
