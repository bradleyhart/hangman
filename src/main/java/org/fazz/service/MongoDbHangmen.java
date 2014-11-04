package org.fazz.service;

import org.fazz.model.Hangman;
import org.fazz.model.Word;
import org.fazz.query.LetterQuery;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.fazz.util.MongoDriverFactory.dbObject;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.query;

public class MongoDbHangmen implements Hangmen {

    private MongoTemplate mongoTemplate;

    public MongoDbHangmen(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void add(Hangman hangman) {
        mongoTemplate.insert(hangman);
    }

    @Override
    public Hangman get(String id) {
        return mongoTemplate.findById(id, Hangman.class);
    }

    @Override
    public List<Hangman> all() {
        return mongoTemplate.findAll(Hangman.class);
    }

    @Override
    public List<Hangman> match(LetterQuery letterQuery) {
        return mongoTemplate.find(query(letterQuery.toMongoCriteria()), Hangman.class);
    }

    @Override
    public void add(Word word) {
        mongoTemplate.insert(word);
    }

    @Override
    public Word getRandomWord() {
        double random = Math.random();

        Word greaterThanRandom = mongoTemplate.findOne(
                query(where("random").gte(random)).
                        with(new Sort(Sort.Direction.ASC, "random")), Word.class);
        if (greaterThanRandom != null) return greaterThanRandom;

        return mongoTemplate.findOne(
                query(where("random").lte(random)).
                        with(new Sort(Sort.Direction.DESC, "random")),
                Word.class);
    }


}
