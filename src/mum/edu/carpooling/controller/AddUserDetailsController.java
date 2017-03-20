package mum.edu.carpooling.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mum.edu.carpooling.domain.User;
import mum.edu.carpooling.service.UserService;
import mum.edu.carpooling.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/addUserDetails")
public class AddUserDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserService userService = new UserServiceImpl();
	
    public AddUserDetailsController() {
        super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dipatcher = request.getRequestDispatcher("addUserDetails.jsp");
		dipatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullname = request.getParameter("fullName");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String dobStr = request.getParameter("dob");
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipCode = request.getParameter("zipCode");
		
		User user = new User();
		user.setFullname(fullname);
		
		if(gender.equals("male")) {
			user.setGender(true);
		} else {
			user.setGender(false);
		}
		
		user.setEmail(email);
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
		Date dob;
		try {
			dob = df.parse(dobStr);
			user.setDob(dob);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		user.setStreet(street);
		user.setCity(city);
		user.setState(state);
		user.setZipCode(zipCode);
		
		userService.addUserDetails(user);
		
		RequestDispatcher dipatcher = request.getRequestDispatcher("wellcome.jsp");
		dipatcher.forward(request, response);	
	}
}
