package org.fazz.hangman.integration.mongo;

import org.fazz.mongo.MongoDb;
import org.fazz.service.MongoDbHangmen;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.fazz.model.Word.word;
import static org.hamcrest.CoreMatchers.is;
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

    static Boolean repeatUntilFound(Callable<Boolean> callable) throws Exception {
        for (int i = 0; i < 100; i++) {
            Boolean found = callable.call();
            if(found) return true;
        }
        return false;
    }

//    @Test
//    public void findCarThatMatchesMultiple() {
//        Hangman audi1 = hangman("Audi", "A6", 2004, 40000);
//        Hangman audi2 = hangman("Audi", "A6", 2004, 40000);
//        mongoDbHangmen.add(audi1);
//        mongoDbHangmen.add(audi2);
//        mongoDbHangmen.add(hangman("Jaguar", "X6", 2005, 30000));
//        mongoDbHangmen.add(hangman("Nissan", "ZX", 2006, 10000));
//
//        LetterQuery search = carSearch();
//        search.setModel("A6");
//        search.setMake("Audi");
//        search.setYear(2004);
//        search.setPrice(40000);
//
//        List<Hangman> hangmans = mongoDbHangmen.match(search);
//
//        assertThat(hangmans.size(), is(2));
//        assertThat(hangmans.get(0), is(audi1));
//        assertThat(hangmans.get(1), is(audi2));
//    }
//
//    @Test
//    public void findCarThatMatchesMakeCriteria() {
//        Hangman audi = hangman("Audi", "A6", 2004, 40000);
//        mongoDbHangmen.add(audi);
//        mongoDbHangmen.add(hangman("Jaguar", "X6", 2005, 30000));
//        mongoDbHangmen.add(hangman("Nissan", "ZX", 2006, 10000));
//
//        LetterQuery search = carSearch();
//        search.setMake("Audi");
//
//        List<Hangman> hangmans = mongoDbHangmen.match(search);
//
//        assertThat(hangmans.size(), is(1));
//        assertThat(hangmans.get(0), is(audi));
//    }
//
//    @Test
//    public void findCarThatMatchesModelCriteria() {
//        Hangman audi = hangman("Audi", "A6", 2004, 40000);
//        mongoDbHangmen.add(audi);
//        mongoDbHangmen.add(hangman("Jaguar", "X6", 2005, 30000));
//        mongoDbHangmen.add(hangman("Nissan", "ZX", 2006, 10000));
//
//        LetterQuery search = carSearch();
//        search.setModel("A6");
//
//        List<Hangman> hangmans = mongoDbHangmen.match(search);
//
//        assertThat(hangmans.size(), is(1));
//        assertThat(hangmans.get(0), is(audi));
//    }
//
//    @Test
//    public void findCarThatMatchesPriceCriteria() {
//        Hangman audi = hangman("Audi", "A6", 2004, 40000);
//        mongoDbHangmen.add(audi);
//        mongoDbHangmen.add(hangman("Jaguar", "X6", 2005, 30000));
//        mongoDbHangmen.add(hangman("Nissan", "ZX", 2006, 10000));
//
//        LetterQuery search = carSearch();
//        search.setPrice(40000);
//
//        List<Hangman> hangmans = mongoDbHangmen.match(search);
//
//        assertThat(hangmans.size(), is(1));
//        assertThat(hangmans.get(0), is(audi));
//    }
//
//    @Test
//    public void findCarThatMatchesYearCriteria() {
//        Hangman audi = hangman("Audi", "A6", 2004, 40000);
//        mongoDbHangmen.add(audi);
//        mongoDbHangmen.add(hangman("Jaguar", "X6", 2005, 30000));
//        mongoDbHangmen.add(hangman("Nissan", "ZX", 2006, 10000));
//
//        LetterQuery search = carSearch();
//        search.setYear(2004);
//
//        List<Hangman> hangmans = mongoDbHangmen.match(search);
//
//        assertThat(hangmans.size(), is(1));
//        assertThat(hangmans.get(0), is(audi));
//    }
//
//    @Test
//    public void findCarThatMatchesCombinations() {
//        Hangman audi = hangman("Audi", "A6", 2004, 40000);
//        mongoDbHangmen.add(audi);
//        mongoDbHangmen.add(hangman("Jaguar", "X6", 2005, 30000));
//        mongoDbHangmen.add(hangman("Nissan", "ZX", 2006, 10000));
//
//        LetterQuery search = carSearch();
//        search.setYear(2004);
//        search.setModel("A6");
//
//        List<Hangman> hangmans = mongoDbHangmen.match(search);
//
//        assertThat(hangmans.size(), is(1));
//        assertThat(hangmans.get(0), is(audi));
//    }
//
//    @Test
//    public void findCarThatMatchesShouldIgnoreBlankString() {
//        Hangman audi = hangman("Audi", "A6", 2004, 40000);
//        mongoDbHangmen.add(audi);
//        mongoDbHangmen.add(hangman("Jaguar", "X6", 2005, 30000));
//        mongoDbHangmen.add(hangman("Nissan", "ZX", 2006, 10000));
//
//        LetterQuery search = carSearch();
//        search.setModel("");
//        search.setMake("");
//        search.setPrice(40000);
//
//        List<Hangman> hangmans = mongoDbHangmen.match(search);
//
//        assertThat(hangmans.size(), is(1));
//        assertThat(hangmans.get(0), is(audi));
//    }
//
//    @Test
//    public void findNoCarsWhenOneCriteriaDoesNotMatch() {
//        Hangman audi = hangman("Audi", "A6", 2004, 40000);
//        mongoDbHangmen.add(audi);
//        mongoDbHangmen.add(hangman("Jaguar", "X6", 2005, 30000));
//        mongoDbHangmen.add(hangman("Nissan", "ZX", 2006, 10000));
//
//        LetterQuery search = carSearch();
//        search.setYear(2004);
//        search.setModel("A10");
//
//        List<Hangman> hangmans = mongoDbHangmen.match(search);
//
//        assertThat(hangmans.size(), is(0));
//    }

}
