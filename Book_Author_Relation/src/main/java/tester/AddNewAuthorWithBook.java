package tester;
import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.AuthorDaoImpl;
import pojos.Author;
import pojos.Book;
public class AddNewAuthorWithBook {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory();
				Scanner sc = new Scanner(System.in))
		{
			AuthorDaoImpl authordao = new AuthorDaoImpl();
			System.out.println("Enter Author Details : First Name Last Name Email Password");
			Author author = new Author(sc.next(),sc.next(),sc.next(),sc.next());
			
			for(int i = 0 ; i < 2 ; i++)
			{
				System.out.println("Enter Book Details : Book_Title  Book_price");
				
				author.addBook(new Book(sc.next(), sc.nextDouble()));
			}
			System.out.println(authordao.addNewAuthor(author));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
