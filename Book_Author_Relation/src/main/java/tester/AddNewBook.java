package tester;

import org.hibernate.SessionFactory;

import dao.BookDaoImpl;
import pojos.Book;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;
public class AddNewBook {

	public static void main(String[] args) {
		
		try(SessionFactory sf = getFactory();
				Scanner sc = new Scanner(System.in))
		{
			BookDaoImpl dao = new BookDaoImpl();
			System.out.println("Enter Book Details : Book_Title  Book_price Author_Id");
			System.out.println(dao.addNewBook(new Book(sc.next(), sc.nextDouble()), sc.nextLong()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
