package mum.edu.carpooling.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mum.edu.carpooling.domain.Comment;
import mum.edu.carpooling.service.UserCommentService;
import mum.edu.carpooling.service.impl.UserCommentServiceImpl;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/Comment")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sPostId = request.getParameter("postId");
		if (sPostId.length() <=0)
			return;
		Integer postId = Integer.valueOf(sPostId);
		UserCommentService comService = new UserCommentServiceImpl();
		ArrayList<Comment> comments = comService.getComments(postId);
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
