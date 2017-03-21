package mum.edu.carpooling.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mum.edu.carpooling.service.UserCommentService;
import mum.edu.carpooling.service.UserPostService;
import mum.edu.carpooling.service.impl.UserCommentServiceImpl;
import mum.edu.carpooling.service.impl.UserPostServiceImpl;

/**
 * Servlet implementation class DelPostController
 */
@WebServlet("/DelPost")
public class DelPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelPostController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer postId = Integer.valueOf(request.getParameter("postId"));
		UserPostService postService = new UserPostServiceImpl();
		boolean ret = postService.removeUserPost(postId);
		
		if (ret) {
			// del all comments of the postid
			UserCommentService commService = new UserCommentServiceImpl();
			commService.delelteComments(postId);		
		}
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		if (ret) 
			out.println("{\"return\":\"true\"}");
		else
			out.println("{\"return\":\"false\"}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
