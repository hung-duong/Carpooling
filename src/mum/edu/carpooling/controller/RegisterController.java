package mum.edu.carpooling.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mum.edu.carpooling.domain.UserCredentials;
import mum.edu.carpooling.formatter.UserCredentialsFormatter;
import mum.edu.carpooling.service.UserCredentialsService;
import mum.edu.carpooling.service.impl.UserCredentialsServiceImpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserCredentialsService credentialsService = new UserCredentialsServiceImpl();
	
    public RegisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dipatcher = request.getRequestDispatcher("register.jsp");
		dipatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        
        UserCredentials user = UserCredentialsFormatter.parse(json);
        UserCredentials userExist = credentialsService.findOne(user.getUsername());
		if(userExist != null) {
			response.sendError(HttpServletResponse.SC_CONFLICT, "");
			return;
		}
		
		credentialsService.addUser(user.getUsername(), user.getPassword());
		
		HttpSession session = request.getSession();
		session.setAttribute("username", user.getUsername());
		
		response.sendError(HttpServletResponse.SC_OK, "");
	}

}
