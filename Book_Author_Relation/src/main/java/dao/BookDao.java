package dao;

import pojos.Book;

public interface BookDao {
	
	String addNewBook(Book newBook, long authorId);

}
