package mum.edu.carpooling.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mum.edu.carpooling.domain.UserCredentials;
import mum.edu.carpooling.service.UserCredentialsService;
import mum.edu.carpooling.service.impl.UserCredentialsServiceImpl;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserCredentialsService credentialsService = new UserCredentialsServiceImpl();
	
    public RegisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dipatcher = request.getRequestDispatcher("WEB-INF/views/register.jsp");
		dipatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		RequestDispatcher dipatcher = null;
		UserCredentials user = credentialsService.findOne(username);
		
		if(user != null) {
			response.sendError(HttpServletResponse.SC_CONFLICT, "");
			return;
		}
		
		//credentialsService.addUser(username, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		
		dipatcher = request.getRequestDispatcher("WEB-INF/views/addUserDetails.jsp");
		dipatcher.forward(request, response);
	}

}
