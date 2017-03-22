package mum.edu.carpooling.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import mum.edu.carpooling.domain.Post;
import mum.edu.carpooling.helper.DBConnection;
public class PostRepository {
	private Connection dbConnect;
	
	public PostRepository() {
		dbConnect = DBConnection.getConnection();
	}
	
	//Insert new post from user
	public void Insert(String userName, String title, String body, int postType) throws SQLException{
		try{
						
			String sql = "INSERT INTO posts(username,title,body,posttype,datecreated) VALUES (?,?,?,?,now())";
			PreparedStatement pStatement = (PreparedStatement) dbConnect.prepareStatement(sql);
			pStatement.setString(1, userName);
			pStatement.setString(2, title);
			pStatement.setString(3, body);
			pStatement.setInt(4, postType);
			pStatement.execute();
			
		}
		catch(ParseException e){
			e.printStackTrace();
		}
	}
	
	//delete the post from user
	public void Delete(int postId){
		try{
			String sql = "DELETE FROM posts WHERE postid=?";
			java.sql.PreparedStatement pStatement = dbConnect.prepareStatement(sql);
			pStatement.setInt(1, postId);			
			pStatement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public ArrayList<Post> Select(){
		ArrayList<Post> posts = new ArrayList<Post>();
		try{
			String sql = "SELECT username,postid,body,title,posttype,datecreated FROM posts  order by datecreated DESC";
			PreparedStatement prepSatement = null;
			if (dbConnect != null)
				prepSatement = dbConnect.prepareStatement(sql);
			else {
				System.out.println("dbConnection is null");
			}

			ResultSet rs = prepSatement.executeQuery();
			while(rs.next()){
				Post post = new Post();
				post.setUserName(rs.getString("username"));
				post.setPostId(rs.getInt("postid"));
				post.setTitle(rs.getString("title"));
				post.setBody(rs.getString("body"));
				post.setDateCreated(rs.getTimestamp ("datecreated"));
				post.setPostType(rs.getInt("posttype"));
				posts.add(post);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return posts;
	}

	public boolean isNewPost(String userName) {
	
		try {
			String sql = "select username,datecreated from posts where "
					+ "datecreated in (select max(datecreated) from posts where username!=?) "
					+ "and datecreated > (NOW() - INTERVAL 15 second)";	
			
			PreparedStatement prepSatement = null;
			if (dbConnect != null)
				prepSatement = dbConnect.prepareStatement(sql);
			else 
				System.out.println("dbConnection is null");
			
			prepSatement.setString(1, userName);
			
			ResultSet rs = prepSatement.executeQuery();
			if (!rs.next())  
				return false;
			else
				return true;
		}
		catch(Exception e) {
				e.printStackTrace();
		}

		return false;
	}
}