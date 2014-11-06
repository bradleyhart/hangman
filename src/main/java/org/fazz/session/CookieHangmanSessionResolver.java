package org.fazz.session;

import org.fazz.model.Hangman;
import org.fazz.service.Hangmen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.fazz.model.Hangman.hangman;

public class CookieHangmanSessionResolver implements HangmanSessionResolver {

    private Hangmen hangmen;
    private HangmanCookies hangmanCookies;

    public CookieHangmanSessionResolver(Hangmen hangmen, HangmanCookies hangmanCookies) {
        this.hangmen = hangmen;
        this.hangmanCookies = hangmanCookies;
    }

    @Override
    public Hangman getOrCreateHangman(HttpServletRequest request, HttpServletResponse response) {
        String hangmanId = hangmanCookies.getValue(request.getCookies());
        if (hangmanId != null) {
            Hangman hangman = hangmen.get(hangmanId);
            if (hangman != null) {
                return hangman;
            }
        }

        Hangman newHangman = hangmen.add(hangman(hangmen.getRandomWord()));
        response.addCookie(hangmanCookies.newCookie(newHangman.getId()));

        return newHangman;
    }

}
