package mum.edu.carpooling.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mum.edu.carpooling.domain.Comment;
import mum.edu.carpooling.service.UserCommentService;
import mum.edu.carpooling.service.UserPostService;
import mum.edu.carpooling.service.impl.UserCommentServiceImpl;
import mum.edu.carpooling.service.impl.UserPostServiceImpl;

/**
 * Servlet implementation class AddCommentController
 */
@WebServlet("/AddComment")
public class AddCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentController() {
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
		
		String comment = request.getParameter("comment");
		Integer postId = Integer.valueOf(request.getParameter("postId"));
		UserCommentService commService = new UserCommentServiceImpl();
		commService.insertComment(postId, userName, comment);
		
		ArrayList<Comment> comments = commService.getComments(postId);
		
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (Comment c : comments) {
			sb.append("{\n");
			sb.append("\"id\": \"").append(c.getComId().toString()).append("\",\n");
			sb.append("\"postid\": \"").append(c.getPostId().toString()).append("\",\n");
			sb.append("\"username\": \"").append(c.getUserName()).append("\",\n");
			sb.append("\"datecreated\": \"").append(df.format(c.getDateCreated())).append("\",\n");
			sb.append("\"body\": \"").append(c.getComment()).append("\"\n");
			sb.append("},\n");
        }
        if (comments.size() > 0)
			sb.setCharAt(sb.length()-2, ']');
        else
        	sb.append("]");

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
