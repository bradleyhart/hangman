package org.fazz.config;

import org.fazz.service.Hangmen;
import org.fazz.session.CookieHangmanSessionResolver;
import org.fazz.session.HangmanCookies;
import org.fazz.session.HangmanSessionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private Hangmen hangmen;

    @Autowired
    private HangmanCookies hangmanCookies;

    @Bean
    public HangmanSessionResolver hangmanSessionResolver(){
        return new CookieHangmanSessionResolver(hangmen, hangmanCookies);
    }

}
