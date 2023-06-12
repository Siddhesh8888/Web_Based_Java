package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.UserDaoImpl;
import pojo.User;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		//http://localhost:8080/day2/logout?cid=4
		try(PrintWriter pw = response.getWriter()){
			
			pw.print("<h3>In Logout Page!!!</h3>");
			
			HttpSession hs = request.getSession();
			
			User user = (User) hs.getAttribute("user");
			if(user!=null)
			{
				if(user.isVotingStatus())
				{
					pw.print("<h4>You already voted!!!</h4>");
				}
				else
				{
					pw.print("<h2>Thank You "+user.getFirstName()+" "+user.getLastName()+"</h2>");
					UserDaoImpl userdao = (UserDaoImpl) hs.getAttribute("user_dao");
					CandidateDaoImpl candao = (CandidateDaoImpl) hs.getAttribute("cadidate_dao");
					
					int candidateId = Integer.parseInt(request.getParameter("cid"));
					
					pw.print("<h3>"+userdao.updateVoterStatus(user.getId())+"</h3>");
					
					pw.print("<h3>"+candao.addVotes(candidateId)+"</h3>");
					
				}
			}
			else
			{
				pw.print("<h4>session tracking failed!!</h4>");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
