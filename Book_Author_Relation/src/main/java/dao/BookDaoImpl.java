package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static utils.HibernateUtils.getFactory;

import pojos.Author;
import pojos.Book;

public class BookDaoImpl implements BookDao{

	@Override
	public String addNewBook(Book newBook, long authorId) {
		String msg = "Book not added!!";
		
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			Author author = session.get(Author.class, authorId);
			author.addBook(newBook);
			session.persist(newBook);
			tx.commit();
			msg = "Book added successfully!!!";
			
		}
		catch (Exception e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return msg;
	}

}
