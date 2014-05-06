package com.bala.interact;

import com.bala.interact.conf.SpringMongoDBConfig;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
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
        MongoOperations mongoOpsContext = (MongoOperations) ctx.getBean("mongoTemplate");

        DBCollection albums = mongoOpsContext.getCollection("albums");
        DBCursor albumCursor = albums.find();

        System.out.print("Count albums : " + albums.count());

        DBCollection images = mongoOpsContext.getCollection("images");
        DBCursor imagesCursor = images.find();

        System.out.print("Count images : " + images.count());

        while (imagesCursor.hasNext()) {
            DBObject records = imagesCursor.next();
            //System.out.println(records);
        }


        System.out.print("Hey I am done with job");
    }
}
