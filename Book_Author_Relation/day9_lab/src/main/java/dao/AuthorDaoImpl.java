package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import static utils.HibernateUtils.getFactory;
import pojos.Author;

public class AuthorDaoImpl implements AuthorDao{

	@Override
	public String addNewAuthor(Author newAuthor) {
		String msg = "Author is not added!!";
		
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			session.save(newAuthor);
			tx.commit();
			msg = "new Author added successfully !!!!";
			
		}
		catch (Exception e) {
			if(tx!=null)
				tx.rollback();
			throw e;
		}
		return msg;
	}

}
