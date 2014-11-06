package org.fazz.controller;

import org.fazz.model.Hangman;
import org.fazz.session.HangmanSessionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HangmanController {

    private HangmanSessionResolver hangmanSessionResolver;

    @Autowired
    public HangmanController(HangmanSessionResolver hangmanSessionResolver) {
        this.hangmanSessionResolver = hangmanSessionResolver;
    }

    @RequestMapping(value = "/hangman", method = RequestMethod.GET)
    public ModelAndView hangman(HttpServletRequest request, HttpServletResponse response) {
        Hangman hangman = hangmanSessionResolver.getOrCreateHangman(request, response);
        return new ModelAndView("hangman") {{
            addObject("attempts", hangman.getGuesses().size());
            addObject("hits", hangman.getHits());
            addObject("wordLength", hangman.getWord().toString().length());
        }};
    }

}
