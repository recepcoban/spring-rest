/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.mongoDao;


import com.app.domain.Book;
import com.app.domain.BookDao;
import com.app.mongoconfig.DatabaseConfig;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author rohat
 */
 @Service
public class MongoBookDao implements BookDao {

	@Override
	public List<Book> listBook() {
		ArrayList<Book> booklist = new ArrayList<>();
		try
		{
    	DBCollection data = DatabaseConfig.configure();
        BasicDBObject orderBy = new BasicDBObject("id", 1);
        DBCursor docs = data.find().sort(orderBy);

       while (docs.hasNext()) {
           DBObject doc = docs.next();
           Book librarybook=new Book(doc.get("bookname").toString(),doc.get("author").toString());
           booklist.add(librarybook);
        }}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        
        return booklist;
	}

	@Override
	public String insertBook(Book book) {
		String status="";
		try
		{
		DBCollection data = DatabaseConfig.configure();
        BasicDBObject insertbook = new BasicDBObject();
        insertbook.put("bookname", book.getBookname());
        insertbook.put("author",  book.getAuthor());
        data.insert(insertbook);
        status="Book created successfully";
		}
		catch(Exception e)
		{   
			status="Book  not created successfully";
			e.printStackTrace();
	     
		}
		return status;
	}

	@Override
	public String updateBook(Book book,String updatedbook) {
		String status="";
		try
		{
			DBCollection data = DatabaseConfig.configure();
	        BasicDBObject updateQuery = new BasicDBObject("bookname",updatedbook);
	        BasicDBObject updated = new BasicDBObject();
	        updated.put("bookname", book.getBookname());
	        updated.put("author",  book.getAuthor());
	        data.update(updateQuery, new BasicDBObject("$set",updated));
	        status="Book updated successfully";
		}
		catch(Exception e)
		{   
			status="Book not updated successfully";
			e.printStackTrace();
		}
		
		return status;
	
	}

	@Override
	public String deleteBook(String book) {
		String status="";
		try
		{
			DBCollection data = DatabaseConfig.configure();
	         BasicDBObject query = new BasicDBObject();
	         query.append("bookname", book);
	         data.remove(query);
	         status="Book deleted successfully";
		}
		catch(Exception e)
		{   
			status="Book not deleted successfully";
			e.printStackTrace();
		}
		 
		return status;
	}
}

