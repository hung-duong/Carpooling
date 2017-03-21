package mum.edu.carpooling.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import mum.edu.carpooling.domain.Comment;
import mum.edu.carpooling.helper.DBConnection;

public class CommentRepository {
private Connection dbConnect;
	
	public CommentRepository() {
		dbConnect = DBConnection.getConnection();
	}
	
	//Insert new comment from user
	public void Insert(String userName, int postId, String comment) throws SQLException{
		try{
						
			String sql = "INSERT INTO comments(username,postid,comment,datecreated) VALUES (?,?,?,now())";
			PreparedStatement pStatement = (PreparedStatement) dbConnect.prepareStatement(sql);
			pStatement.setString(1, userName);
			pStatement.setInt(2, postId);
			pStatement.setString(3, comment);
			pStatement.execute();
			
		}
		catch(ParseException e){
			e.printStackTrace();
		}
	}
	
	//delete the comment from postid
	public void Delete(int postId){
		try{
			String sql = "DELETE FROM comments WHERE postid=?";
			java.sql.PreparedStatement pStatement = dbConnect.prepareStatement(sql);
			pStatement.setInt(1, postId);			
			pStatement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Comment> Select(int postId){
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try{
			String sql ="SELECT commentid, username,postid,comment,datecreated FROM comments WHERE postid=?";
			PreparedStatement statement = (PreparedStatement) dbConnect.prepareStatement(sql);
			statement.setInt(1, postId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				Comment com = new Comment();
				com.setComId(rs.getInt("commentid"));
				com.setUserName(rs.getString("username"));
				com.setPostId(rs.getInt("postid"));
				com.setComment(rs.getString("comment"));
				com.setDateCreated(rs.getDate("datecreated"));
				comments.add(com);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
/*		finally{
			try{
				dbConnect.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
*/		return comments;
	}
}
