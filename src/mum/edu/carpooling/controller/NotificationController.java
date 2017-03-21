package mum.edu.carpooling.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mum.edu.carpooling.domain.Post;
import mum.edu.carpooling.service.UserPostService;
import mum.edu.carpooling.service.impl.UserPostServiceImpl;

/**
 * Servlet implementation class NotificationController
 */
@WebServlet("/Notification")
public class NotificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
				
		if(userName == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		UserPostService postService = new UserPostServiceImpl();
		boolean ret = postService.isNotification(userName);	
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		StringBuffer sb = new StringBuffer();		
		sb.append("{\n");
		if (ret) {
			sb.append("\"notify\":\"yes\"\n");
		}
		else
			sb.append("\"notify\":\"no\"\n");
		sb.append("}\n");
		out.println(sb.toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
