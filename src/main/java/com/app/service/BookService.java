package com.app.service;


import com.app.domain.Book;
import com.app.domain.BookDao;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rohat
 */
@RestController
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    @Autowired()
    BookDao mongobook;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public List<Book> listAllBook() 
    {
        return mongobook.listBook();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Map<String, String> createBook(@RequestBody Map<String, Object> bookMap) {
    	Book book =new Book(bookMap.get("bookname").toString(),bookMap.get("author").toString());
    	String status=mongobook.insertBook(book);
        Map<String, String> response = new LinkedHashMap<String, String>();
        response.put("message",status);
        logger.info("System " + response);
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{bookmame}")
    public Map<String, String> deleteBook(@PathVariable("bookmame") String bookmame){
        String status =mongobook.deleteBook(bookmame);
        Map<String, String> response = new HashMap<String, String>();
        response.put("message", status);
        logger.info("System "+response);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update/{updatedbook}")
    public Map<String, String> updateBook(@PathVariable("updatedbook") String updatedbook, @RequestBody Map<String, Object> bookMap) {
    	Book book =new Book(bookMap.get("bookname").toString(),bookMap.get("author").toString());
    	String status =mongobook.updateBook(book,updatedbook);
        Map<String, String> response = new LinkedHashMap<String, String>();
        response.put("message", status);
        logger.info("System " + response);
        return response;
    }
}
