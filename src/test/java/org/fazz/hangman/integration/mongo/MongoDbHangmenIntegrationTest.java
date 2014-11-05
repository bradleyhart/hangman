package org.fazz.hangman.integration.mongo;

import org.fazz.model.Hangman;
import org.fazz.mongo.MongoDb;
import org.fazz.service.MongoDbHangmen;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;

import static org.fazz.model.Guess.guess;
import static org.fazz.model.Hangman.hangman;
import static org.fazz.model.Word.word;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class MongoDbHangmenIntegrationTest {


    private MongoDbHangmen mongoDbHangmen;

    @Before
    public void startMongo() {
        MongoDb.isRunning();
        MongoDb.isEmpty();
    }

    @Before
    public void initializeController() {
        mongoDbHangmen = new MongoDbHangmen(MongoDb.getMongoTemplate());
    }

    @Test
    public void getRandomWord() throws Exception {
        mongoDbHangmen.add(word("spiderman"));
        mongoDbHangmen.add(word("batman"));
        mongoDbHangmen.add(word("superman"));

        assertThat(repeatUntilFound(() -> mongoDbHangmen.getRandomWord().equals(word("spiderman"))), is(true));
        assertThat(repeatUntilFound(() -> mongoDbHangmen.getRandomWord().equals(word("batman"))), is(true));
        assertThat(repeatUntilFound(() -> mongoDbHangmen.getRandomWord().equals(word("superman"))), is(true));
    }

    @Test
    public void insertAndRetrieveHangman() throws Exception {
        Hangman hangman = hangman(word("canary"));
        hangman.take(guess('s'));
        hangman.take(guess('y'));

        Hangman saved = mongoDbHangmen.add(hangman);

        Hangman retrieved = mongoDbHangmen.get(saved.getId());

        assertThat(saved, is(equalTo(retrieved)));
        assertThat(retrieved.getWord(), is(equalTo(word("canary"))));
        assertThat(retrieved.getGuesses().size(), is(2));
        assertThat(retrieved.getGuesses().contains(guess('s')), is(true));
        assertThat(retrieved.getGuesses().contains(guess('y')), is(true));
    }

    @Test
    public void retrieveAllHangmen() throws Exception {
        mongoDbHangmen.add(hangman(word("canary")));
        mongoDbHangmen.add(hangman(word("parrot")));
        mongoDbHangmen.add(hangman(word("falcon")));
        mongoDbHangmen.add(hangman(word("chicken")));

        List<Hangman> hangmen = mongoDbHangmen.all();

        assertThat(hangmen.size(), is(equalTo(4)));
    }

    @Test
    public void updateAHangman() throws Exception {
        Hangman saved = mongoDbHangmen.add(hangman(word("canary")));

        saved.take(guess('f'));
        mongoDbHangmen.update(saved);
        Hangman updated = mongoDbHangmen.get(saved.getId());

        assertThat(updated.getGuesses().iterator().next(), is(guess('f')));
    }

    @Test
    public void deleteAHangman() throws Exception {
        Hangman saved = mongoDbHangmen.add(hangman(word("canary")));
        mongoDbHangmen.delete(saved);

        assertThat(mongoDbHangmen.get(saved.getId()), nullValue());
    }



    static Boolean repeatUntilFound(Callable<Boolean> callable) throws Exception {
        for (int i = 0; i < 100; i++) {
            Boolean found = callable.call();
            if (found) return true;
        }
        return false;
    }

}
