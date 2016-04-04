package com.app.domain;

import java.util.List;

public interface BookDao {

	List<Book> listBook();
	public String insertBook(Book book);
	public String updateBook(Book book,String updatedbook);
	public String deleteBook(String book);
	
}
