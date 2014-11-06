package org.fazz.session;

import org.fazz.model.Hangman;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HangmanSessionResolver {

    Hangman getOrCreateHangman(HttpServletRequest request, HttpServletResponse response);

}
