package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import pojo.Candidate;
import pojo.User;

/**
 * Servlet implementation class CandidateServlet
 */
@WebServlet("/candidate_page")
public class CandidateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			
			response.setContentType("text/html");
			
			HttpSession hs = request.getSession();
			
			User user = (User) hs.getAttribute("user");
			
			if(user!=null) {
				
				pw.print("<h3>Hello " + user.getFirstName()+" "+user.getLastName()+"</h3>");
				CandidateDaoImpl canDao =(CandidateDaoImpl) hs.getAttribute("cadidate_dao");
				List<Candidate> candidateList = canDao.getAllCandidate();
				
				pw.print("<form action='logout'>");
				for(Candidate c : candidateList)
				{
					pw.print("<h5><input type='radio' name='cid' value='"+c.getCandidateId() +"'/>"+c.getName()+"</h5>");
				}
				
				pw.print("<input type='submit' value='vote'>");
				pw.print("<form>");
			}
			else
			{
				pw.print("<h4>session tracking failed!!!!</h4>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
