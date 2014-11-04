package org.fazz.service;


import org.fazz.model.Hangman;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.fazz.model.Hangman.car;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MongoDbHangmenTest {

    private MongoDbHangmen mongoDbHangmen;
    private MongoTemplate mongoTemplate;

    @Before
    public void initializeController() {
        mongoTemplate = mock(MongoTemplate.class);
        mongoDbHangmen = new MongoDbHangmen(mongoTemplate);
    }

    @Test
    public void getAllCarsFromMongo() {
        final Hangman hangman1 = car("Audi", "A6", 2004, 40000);
        final Hangman hangman2 = car("Jaguar", "X6", 2005, 30000);
        final Hangman hangman3 = car("Nissan", "ZX", 2006, 10000);
        List<Hangman> expectedHangmans = new ArrayList<Hangman>(){{
            add(hangman1);
            add(hangman2);
            add(hangman3);
        }};

        when(mongoTemplate.findAll(Hangman.class)).thenReturn(expectedHangmans);

        List<Hangman> actualHangmans = mongoDbHangmen.all();

        assertThat(actualHangmans, is(equalTo(expectedHangmans)));
    }

    @Test
    public void addsCarToMongo() {
        Hangman hangman = car("Audi", "A6", 2004, 40000);

        mongoDbHangmen.add(hangman);

        verify(mongoTemplate).insert(hangman);
    }

    @Test
    public void getsCarFromMongo() {
        Hangman expectedHangman = car("Audi", "A6", 2004, 40000);
        when(mongoTemplate.findById("car-id", Hangman.class)).thenReturn(expectedHangman);

        Hangman actualHangman = mongoDbHangmen.get("car-id");

        assertThat(actualHangman, is(equalTo(expectedHangman)));
    }


}
