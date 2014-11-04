package org.fazz.cars.integration.mongo;

import org.fazz.model.Hangman;
import org.fazz.mongo.MongoDb;
import org.fazz.query.LetterQuery;
import org.fazz.service.MongoDbHangmen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;


import java.util.List;

import static org.fazz.model.Hangman.car;
import static org.fazz.query.LetterQuery.carSearch;
import static org.hamcrest.CoreMatchers.is;

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
    public void findCarThatMatchesExactly() {
        Hangman audi = car("Audi", "A6", 2004, 40000);
        mongoDbHangmen.add(audi);
        mongoDbHangmen.add(car("Jaguar", "X6", 2005, 30000));
        mongoDbHangmen.add(car("Nissan", "ZX", 2006, 10000));

        LetterQuery search = carSearch();
        search.setModel("A6");
        search.setMake("Audi");
        search.setYear(2004);
        search.setPrice(40000);

        List<Hangman> hangmans = mongoDbHangmen.match(search);

        assertThat(hangmans.size(), is(1));
        assertThat(hangmans.get(0), is(audi));
    }

    @Test
    public void findCarThatMatchesMultiple() {
        Hangman audi1 = car("Audi", "A6", 2004, 40000);
        Hangman audi2 = car("Audi", "A6", 2004, 40000);
        mongoDbHangmen.add(audi1);
        mongoDbHangmen.add(audi2);
        mongoDbHangmen.add(car("Jaguar", "X6", 2005, 30000));
        mongoDbHangmen.add(car("Nissan", "ZX", 2006, 10000));

        LetterQuery search = carSearch();
        search.setModel("A6");
        search.setMake("Audi");
        search.setYear(2004);
        search.setPrice(40000);

        List<Hangman> hangmans = mongoDbHangmen.match(search);

        assertThat(hangmans.size(), is(2));
        assertThat(hangmans.get(0), is(audi1));
        assertThat(hangmans.get(1), is(audi2));
    }

    @Test
    public void findCarThatMatchesMakeCriteria() {
        Hangman audi = car("Audi", "A6", 2004, 40000);
        mongoDbHangmen.add(audi);
        mongoDbHangmen.add(car("Jaguar", "X6", 2005, 30000));
        mongoDbHangmen.add(car("Nissan", "ZX", 2006, 10000));

        LetterQuery search = carSearch();
        search.setMake("Audi");

        List<Hangman> hangmans = mongoDbHangmen.match(search);

        assertThat(hangmans.size(), is(1));
        assertThat(hangmans.get(0), is(audi));
    }

    @Test
    public void findCarThatMatchesModelCriteria() {
        Hangman audi = car("Audi", "A6", 2004, 40000);
        mongoDbHangmen.add(audi);
        mongoDbHangmen.add(car("Jaguar", "X6", 2005, 30000));
        mongoDbHangmen.add(car("Nissan", "ZX", 2006, 10000));

        LetterQuery search = carSearch();
        search.setModel("A6");

        List<Hangman> hangmans = mongoDbHangmen.match(search);

        assertThat(hangmans.size(), is(1));
        assertThat(hangmans.get(0), is(audi));
    }

    @Test
    public void findCarThatMatchesPriceCriteria() {
        Hangman audi = car("Audi", "A6", 2004, 40000);
        mongoDbHangmen.add(audi);
        mongoDbHangmen.add(car("Jaguar", "X6", 2005, 30000));
        mongoDbHangmen.add(car("Nissan", "ZX", 2006, 10000));

        LetterQuery search = carSearch();
        search.setPrice(40000);

        List<Hangman> hangmans = mongoDbHangmen.match(search);

        assertThat(hangmans.size(), is(1));
        assertThat(hangmans.get(0), is(audi));
    }

    @Test
    public void findCarThatMatchesYearCriteria() {
        Hangman audi = car("Audi", "A6", 2004, 40000);
        mongoDbHangmen.add(audi);
        mongoDbHangmen.add(car("Jaguar", "X6", 2005, 30000));
        mongoDbHangmen.add(car("Nissan", "ZX", 2006, 10000));

        LetterQuery search = carSearch();
        search.setYear(2004);

        List<Hangman> hangmans = mongoDbHangmen.match(search);

        assertThat(hangmans.size(), is(1));
        assertThat(hangmans.get(0), is(audi));
    }

    @Test
    public void findCarThatMatchesCombinations() {
        Hangman audi = car("Audi", "A6", 2004, 40000);
        mongoDbHangmen.add(audi);
        mongoDbHangmen.add(car("Jaguar", "X6", 2005, 30000));
        mongoDbHangmen.add(car("Nissan", "ZX", 2006, 10000));

        LetterQuery search = carSearch();
        search.setYear(2004);
        search.setModel("A6");

        List<Hangman> hangmans = mongoDbHangmen.match(search);

        assertThat(hangmans.size(), is(1));
        assertThat(hangmans.get(0), is(audi));
    }

    @Test
    public void findCarThatMatchesShouldIgnoreBlankString() {
        Hangman audi = car("Audi", "A6", 2004, 40000);
        mongoDbHangmen.add(audi);
        mongoDbHangmen.add(car("Jaguar", "X6", 2005, 30000));
        mongoDbHangmen.add(car("Nissan", "ZX", 2006, 10000));

        LetterQuery search = carSearch();
        search.setModel("");
        search.setMake("");
        search.setPrice(40000);

        List<Hangman> hangmans = mongoDbHangmen.match(search);

        assertThat(hangmans.size(), is(1));
        assertThat(hangmans.get(0), is(audi));
    }

    @Test
    public void findNoCarsWhenOneCriteriaDoesNotMatch() {
        Hangman audi = car("Audi", "A6", 2004, 40000);
        mongoDbHangmen.add(audi);
        mongoDbHangmen.add(car("Jaguar", "X6", 2005, 30000));
        mongoDbHangmen.add(car("Nissan", "ZX", 2006, 10000));

        LetterQuery search = carSearch();
        search.setYear(2004);
        search.setModel("A10");

        List<Hangman> hangmans = mongoDbHangmen.match(search);

        assertThat(hangmans.size(), is(0));
    }

}
