package org.fazz.service;

import com.mongodb.DBCollection;
import org.fazz.model.Hangman;
import org.fazz.query.LetterQuery;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

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

    private DBCollection carCollection() {
        return mongoTemplate.getCollection("car");
    }

}
