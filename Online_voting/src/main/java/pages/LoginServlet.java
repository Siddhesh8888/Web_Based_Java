package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import pojo.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/login" , loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private UserDaoImpl userDao;
	private CandidateDaoImpl CanDao;
	
	public void init() throws ServletException {
		
		try
		{
			userDao = new UserDaoImpl();
			CanDao = new CandidateDaoImpl();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void destroy() {
		
		try
		{
			userDao.cleanup();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()) {
			
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			
			User user = userDao.authenticateUser(email, password);
			
			if(user==null)
			{
				pw.print("<h1>Invalid user and password!!!</h1>");
			}
			else
			{//login successfull
				
				pw.print("<h1>Login Successful!!!</h1>");
				
				HttpSession hs = request.getSession();
				
				hs.setAttribute("user", user);
				hs.setAttribute("user_dao", userDao);
				hs.setAttribute("cadidate_dao", CanDao);
				
				if(user.getRole().equals("admin"))
				{
					response.sendRedirect("admin_page");//if user is admin redirected to admin page  
				}
				else
				{
					if(user.isVotingStatus()) {
						response.sendRedirect("logout");//if user is already voted redirect to logout page
					}
					else {
					response.sendRedirect("candidate_page");//user redirect to candidate page to do voting
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
