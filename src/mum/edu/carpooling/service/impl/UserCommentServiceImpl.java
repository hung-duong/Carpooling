package mum.edu.carpooling.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import mum.edu.carpooling.domain.Comment;
import mum.edu.carpooling.repository.CommentRepository;
import mum.edu.carpooling.service.UserCommentService;

public class UserCommentServiceImpl implements UserCommentService {
	CommentRepository commentDB = new CommentRepository();

	@Override
	public ArrayList<Comment> getComments(Integer postId) {
		// TODO Auto-generated method stub
		ArrayList<Comment> comments = commentDB.Select(postId);
		return comments;
	}

	@Override
	public boolean insertComment(Integer postId, String userName, String comment) {
		// TODO Auto-generated method stub
		try {
			commentDB.Insert(userName, postId, comment);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean delelteComments(Integer postId) {
		// TODO Auto-generated method stub
		commentDB.Delete(postId);
		return true;
	}

}
