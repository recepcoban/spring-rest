package com.app.mongoconfig;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DatabaseConfig {
	
	public static DBCollection configure() throws UnknownHostException
	{
		MongoClientURI uri = new MongoClientURI("mongodb://root:12345@ds039058.mongolab.com:39058/books");
        MongoClient client = new MongoClient(uri);
        DB db = client.getDB(uri.getDatabase());
        DBCollection data = db.getCollection("librarybooks");
        
        return data;
	}

}
