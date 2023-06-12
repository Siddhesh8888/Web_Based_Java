package pages;

import java.io.IOException;
import static utils.ValidationRule.*;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoImpl;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private UserDaoImpl userdao;
	@Override
	public void init() throws ServletException {
		try {
			userdao = new UserDaoImpl();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		
		try {
			userdao.cleanup();;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		
		
		try(PrintWriter pw = response.getWriter())
		{
			//<!--  id | first_name | last_name | email             | password | dob        | status | role -->
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String password = request.getParameter("pass");
			String dob = request.getParameter("dob");
			
			Date dob1 = validateDate(dob);
			if(dob1==null)
				pw.print("<h3>Your Age is less than 21 <a href='register.html'/>Click here to register</h3>");
				
			
			else {
				String msg = userdao.voterAdd(fname, lname, email, password, dob1);

				pw.print("<h3>" + msg + "</h3>");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
