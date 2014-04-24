package com.bala.interact;

import com.bala.interact.com.bala.interact.config.SpringMongoDBConfig;
import com.bala.interact.modal.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by balaji on 21/4/2014.
 */
public class DBMainApp {



    public static void  main(String args[] ){
        System.out.print("Hey I am in  job");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoDBConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

        User user = new User("balab", "password123");



        mongoOperation.save(user);

        System.out.print("Hey I am done with job");
    }
}
