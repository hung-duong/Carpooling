package mum.edu.carpooling.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mum.edu.carpooling.service.UserCredentialsService;
import mum.edu.carpooling.service.impl.UserCredentialsServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserCredentialsService credentialsService = new UserCredentialsServiceImpl();
	
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		RequestDispatcher dipatcher = null;
		if(user != null) {
			dipatcher = request.getRequestDispatcher("welcome.jsp");
		} else {
			dipatcher = request.getRequestDispatcher("login.jsp");
		}
		
		dipatcher.forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean isLogin = false;
		RequestDispatcher dipatcher = null;
		try {
			isLogin = credentialsService.authenticate(username, password);
		} catch (Exception e) {
			request.setAttribute("error", "Username/Password not found");
			dipatcher = request.getRequestDispatcher("login.jsp");
			dipatcher.forward(request, response);
			return;
		}
		
		if (!isLogin) {
			request.setAttribute("error", "Username/Password not found");
			dipatcher = request.getRequestDispatcher("login.jsp");
			dipatcher.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		
		dipatcher = request.getRequestDispatcher("welcome.jsp");
		dipatcher.forward(request, response);
	}

}
