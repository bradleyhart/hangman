package org.fazz.mongo

import org.fazz.config.persistence.MongoConfig
import org.fazz.model.Hangman
import org.fazz.model.Word
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.data.mongodb.core.MongoTemplate

import static org.fazz.model.Word.word
import static org.fazz.mongo.MongoDb.isEmpty
import static org.fazz.mongo.MongoDb.isRunning

class MongoDb {

    private static boolean started = false
    private static AnnotationConfigApplicationContext mongoContext

    public static void main(String[] args) {
//        isRunning()
        isEmpty()

        getMongoTemplate().insert(word("monkey"))
        getMongoTemplate().insert(word("tiger"))
        getMongoTemplate().insert(word("lion"))
        getMongoTemplate().insert(word("hippo"))
    }

    static isRunning() {
        new Thread({
            String[] roots = ["script"]
            GroovyScriptEngine engine = new GroovyScriptEngine(roots)
            engine.run("StartMongoDb.groovy", new Binding())
        }).start()
        started = true
    }

    static def isEmpty() {
        getMongoTemplate().dropCollection(Hangman)
        getMongoTemplate().dropCollection(Word)
    }

    static def getMongoContext() {
        if (!mongoContext) {
            mongoContext = new AnnotationConfigApplicationContext(MongoConfig)
        }
        mongoContext
    }

    static MongoTemplate getMongoTemplate() {
        getMongoContext().getBean(MongoTemplate)
    }
}
