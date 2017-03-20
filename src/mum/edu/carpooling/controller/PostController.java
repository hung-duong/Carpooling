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

import mum.edu.carpooling.domain.Post;
import mum.edu.carpooling.repository.PostRepository;

/**
 * Servlet implementation class PostController
 */
@WebServlet("/Post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PostRepository postDB = new PostRepository();
		
		ArrayList<Post> posts = postDB.Select();
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (Post p : posts) {
			sb.append("{\n");
			sb.append("\"id\": \"").append(p.getPostId().toString()).append("\",\n");
			sb.append("\"username\": \"").append(p.getUserName()).append("\",\n");
			sb.append("\"posttype\": \"").append(p.getPostType().toString()).append("\",\n");
			sb.append("\"datecreated\": \"").append(df.format(p.getDateCreated())).append("\",\n");
			sb.append("\"title\": \"").append(p.getTitle()).append("\",\n");
			sb.append("\"body\": \"").append(p.getBody()).append("\"\n");
			sb.append("},\n");
        }
        if (posts.size() > 0)
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
