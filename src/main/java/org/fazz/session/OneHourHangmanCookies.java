package org.fazz.session;

import org.fazz.session.HangmanCookies;

import javax.servlet.http.Cookie;

public class OneHourHangmanCookies implements HangmanCookies {

    private static final int ONE_HOUR_IN_SECONDS = 3600;
    private static final String COOKIE_NAME = "hangman";

    @Override
    public Cookie newCookie(String value) {
        Cookie cookie = new Cookie(COOKIE_NAME, value);
        cookie.setMaxAge(ONE_HOUR_IN_SECONDS);
        return cookie;
    }

    @Override
    public String getValue(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(COOKIE_NAME)){
                return cookie.getValue();
            }
        }
        return null;
    }

}
