package org.fazz.controller;

import org.fazz.model.Guess;
import org.fazz.model.Hangman;
import org.fazz.service.Hangmen;
import org.fazz.session.HangmanSessionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HangmanController {

    private HangmanSessionResolver hangmanSessionResolver;
    private Hangmen hangmen;

    @Autowired
    public HangmanController(HangmanSessionResolver hangmanSessionResolver, Hangmen hangmen) {
        this.hangmanSessionResolver = hangmanSessionResolver;
        this.hangmen = hangmen;
    }

    @RequestMapping(value = "/hangman", method = RequestMethod.GET)
    public ModelAndView hangman(HttpServletRequest request, HttpServletResponse response) {
        final Hangman hangman = hangmanSessionResolver.getOrCreateHangman(request, response);
        return new ModelAndView("hangman") {{
            addObject("hangman", hangman);
        }};
    }

    @RequestMapping(value = "/guess", method = RequestMethod.POST)
    @ResponseBody
    public Hangman guess(@RequestParam("hangmanId") String hangmanId, @RequestParam("guess") Character guessCharacter) {
        Hangman hangman = hangmen.get(hangmanId);
        hangman.take(Guess.guess(guessCharacter));
        hangmen.update(hangman);
        return hangman;
    }

    @RequestMapping(value = "/hangmen", method = RequestMethod.GET)
    public ModelAndView hangmen() {
        return new ModelAndView("hangmen") {{
            addObject("hangmen", hangmen.all());
        }};
    }

    @RequestMapping(value = "/hangman", method = RequestMethod.POST)
    public ModelAndView hangmanGuess(@RequestParam("hangmanId") String hangmanId, @RequestParam("guess") Character guessCharacter) {
        Hangman hangman = hangmen.get(hangmanId);
        hangman.take(Guess.guess(guessCharacter));
        return new ModelAndView("hangman") {{
            addObject("hangman", hangmen.update(hangman));
        }};
    }

}
