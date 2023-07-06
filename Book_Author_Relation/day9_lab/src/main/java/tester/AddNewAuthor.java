package tester;

import org.hibernate.SessionFactory;

import dao.AuthorDaoImpl;
import pojos.Author;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;
public class AddNewAuthor {

	public static void main(String[] args) {
		try(SessionFactory sf = getFactory();
				Scanner sc = new Scanner(System.in))
		{
			AuthorDaoImpl dao = new AuthorDaoImpl();
			//id first name ,last name , email(unique) , pwd + ???????
			System.out.println("Enter Auther details : First name Last Name Email Password");
			System.out.println(dao.addNewAuthor(new Author(sc.next(), sc.next(), sc.next(), sc.next())));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
