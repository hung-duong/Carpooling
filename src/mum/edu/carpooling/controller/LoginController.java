package mum.edu.carpooling.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mum.edu.carpooling.domain.User;
import mum.edu.carpooling.service.UserCredentialsService;
import mum.edu.carpooling.service.impl.UserCredentialsServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserCredentialsService credentialsService = new UserCredentialsServiceImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		RequestDispatcher dipatcher = null;
		if(user != null) {
			dipatcher = request.getRequestDispatcher("WEB-INF/views/welcome.jsp");
		} else {
			dipatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
		}
		
		dipatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isLogin = false;
		RequestDispatcher dipatcher = null;
		String username = request.getParameter("");
		String password = request.getParameter("");
		try {
			isLogin = credentialsService.authenticate(username, password);
		} catch (Exception e) {
			request.setAttribute("error", "Username/Password not found");
			dipatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dipatcher.forward(request, response);
			return;
		}
		
		if (!isLogin) {
			request.setAttribute("error", "Username/Password not found");
			dipatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dipatcher.forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		
		dipatcher = request.getRequestDispatcher("WEB-INF/views/welcome.jsp");
		dipatcher.forward(request, response);
	}

}
