package org.fazz.servlet;


import org.fazz.session.OneHourHangmanCookies;
import org.junit.Test;

import javax.servlet.http.Cookie;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class OneHourHangmanCookiesTest {

    private OneHourHangmanCookies cookies = new OneHourHangmanCookies();

    @Test
    public void createsCookieWithOneHourExpiry(){
        assertThat(cookies.newCookie("anything").getMaxAge(), is(3600));
    }

    @Test
    public void createsCookieWithNameHangman(){
        assertThat(cookies.newCookie("anything").getName(), is(equalTo("hangman")));
    }

    @Test
    public void createsCookieWithValue(){
        assertThat(cookies.newCookie("value").getValue(), is(equalTo("value")));
    }

    @Test
    public void getValueOfHangmanCookie(){
        assertThat(cookies.getValue(new Cookie[]{
                new Cookie("bob", "bill"),
                new Cookie("hangman", "winner"),
                new Cookie("jim", "oh dear")
        }), is(equalTo("winner")));
    }

    @Test
    public void getValueReturnsNullWhenNoHangmanCookie(){
        assertThat(cookies.getValue(new Cookie[]{
                new Cookie("bob", "bill"),
                new Cookie("jim", "oh dear")
        }), is(nullValue()));
    }

}
