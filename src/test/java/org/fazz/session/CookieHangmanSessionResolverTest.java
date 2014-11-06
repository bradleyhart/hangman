package org.fazz.session;


import org.fazz.model.Hangman;
import org.fazz.model.Word;
import org.fazz.service.Hangmen;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CookieHangmanSessionResolverTest {

    private static final Cookie[] COOKIES = new Cookie[]{};
    private static final String HANGMAN_ID = "hangmanId";
    private static final Cookie HANGMAN_COOKIE = new Cookie("anything", "anything");
    private static final Word WORD = Word.word("anything");

    private  Hangman hangman = Hangman.hangman(WORD);
    private  Hangman savedHangman = Hangman.hangman(WORD);

    private HangmanCookies hangmanCookies = mock(HangmanCookies.class);
    private Hangmen hangmen = mock(Hangmen.class);

    private CookieHangmanSessionResolver cookieHangmanSessionResolver = new CookieHangmanSessionResolver(hangmen, hangmanCookies);

    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void setup(){
        when(request.getCookies()).thenReturn(COOKIES);

        when(hangmen.getRandomWord()).thenReturn(WORD);

        when(hangmen.add(hangman)).thenReturn(savedHangman);
        savedHangman.setId(HANGMAN_ID);

        when(hangmanCookies.newCookie(HANGMAN_ID)).thenReturn(HANGMAN_COOKIE);
    }

    @Test
    public void createNewHangmanAndCookieWhenThereIsNoHangmanCookie(){
        when(hangmanCookies.getValue(COOKIES)).thenReturn(null);

        Hangman result = cookieHangmanSessionResolver.getOrCreateHangman(request, response);

        verify(response).addCookie(HANGMAN_COOKIE);
        assertThat(result, is(equalTo(savedHangman)));
    }

    @Test
    public void returnExistingHangmanWhenThereIsACookie(){
        when(hangmanCookies.getValue(COOKIES)).thenReturn(HANGMAN_ID);
        when(hangmen.get(HANGMAN_ID)).thenReturn(hangman);

        Hangman result = cookieHangmanSessionResolver.getOrCreateHangman(request, response);

        assertThat(result, is(equalTo(hangman)));
    }

    @Test
    public void createNewHangmanWhenThereIsACookieButHangmanNotFound(){
        when(hangmanCookies.getValue(COOKIES)).thenReturn(HANGMAN_ID);
        when(hangmen.get(HANGMAN_ID)).thenReturn(null);

        Hangman result = cookieHangmanSessionResolver.getOrCreateHangman(request, response);

        verify(response).addCookie(HANGMAN_COOKIE);
        assertThat(result, is(equalTo(savedHangman)));
    }


}
