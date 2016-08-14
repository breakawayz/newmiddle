package org.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

/**
 * Created by break on 2016/8/13.
 */
public class mongoUtil {
    public static void main(String[] args) {

        MongoClient mg = new MongoClient ("192.168.0.114", 27017);
        MongoDatabase md = mg.getDatabase("middle");
        System.out.println("Connect to database successfully");
        md.createCollection("test");
        System.out.println("集合创建成功");

    }

    public void test(){
        MongoClient mg = new MongoClient ("192.168.0.114", 27017);
        //查询所有的Database
        for (String name : mg.getDatabaseNames()) {
            System.out.println("dbName: " + name);
        }

        DB db = mg.getDB("test");
        //查询所有的聚集集合
        for (String name : db.getCollectionNames()) {
            System.out.println("collectionName: " + name);
        }

        DBCollection users = db.getCollection("users");

        //查询所有的数据
        DBCursor cur = users.find();
        while (cur.hasNext()) {
            System.out.println(cur.next());
        }
        System.out.println(cur.count());
        System.out.println(cur.getCursorId());
    }
}
