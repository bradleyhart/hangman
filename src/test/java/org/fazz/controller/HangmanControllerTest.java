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

    @Before
    public void setup(){

    }

    @Test
    public void returnCorrectView(){
        when(hangmanSessionResolver.getOrCreateHangman(request, response)).thenReturn(hangman(word("")));

        ModelAndView modelAndView = hangmanController
                .hangman(request, response);

        assertThat(modelAndView.getViewName(), is(equalTo("hangman")));
    }

    @Test
    public void addAttemptsToModel(){
        Hangman hangman = hangman(word(""));
        hangman.take(Guess.guess('a'));
        hangman.take(Guess.guess('b'));

        when(hangmanSessionResolver.getOrCreateHangman(request, response)).thenReturn(hangman);

        ModelAndView modelAndView = hangmanController
                .hangman(request, response);

        assertThat((Integer)modelAndView.getModel().get("attempts"), is(2));
    }

    @Test
    public void addWordLengthToModel(){
        Hangman hangman = hangman(word("abc"));

        when(hangmanSessionResolver.getOrCreateHangman(request, response)).thenReturn(hangman);

        ModelAndView modelAndView = hangmanController
                .hangman(request, response);

        assertThat((Integer)modelAndView.getModel().get("wordLength"), is(3));
    }

    @Test
    public void addHitsToModel(){
        Hangman hangman = hangman(word("ab"));
        hangman.take(Guess.guess('a'));

        when(hangmanSessionResolver.getOrCreateHangman(request, response)).thenReturn(hangman);

        ModelAndView modelAndView = hangmanController
                .hangman(request, response);

        assertThat(((Set<Hit>)modelAndView.getModel().get("hits")).iterator().next(), is(equalTo(hit(0, 'a'))));
    }

    @Test
    public void addIdToModel(){
        Hangman hangman = hangman(word("ab"));
        hangman.setId("id");

        when(hangmanSessionResolver.getOrCreateHangman(request, response)).thenReturn(hangman);

        ModelAndView modelAndView = hangmanController
                .hangman(request, response);

        assertThat(((String) modelAndView.getModel().get("id")), is(equalTo("id")));
    }

    @Test
    public void guessAddedToHangmanAndUpdated(){
        when(hangmen.get("id")).thenReturn(hangman(word("abc")));

        Hangman hangman = hangmanController.guess("id", 'a');

        assertThat(hangman.getGuesses().contains(Guess.guess('a')), is(true));
        verify(hangmen).update(hangman);
    }

}
