package org.fazz.session;

import javax.servlet.http.Cookie;

public interface HangmanCookies {

    Cookie newCookie(String value);

    String getValue(Cookie[] cookies);

}
