package mum.edu.carpooling.service;

import java.util.ArrayList;

import mum.edu.carpooling.domain.Comment;

public interface UserCommentService {
	public ArrayList<Comment> getComments(Integer postId);
	public boolean insertComment(Integer postId, String userName, String comment);
	public boolean delelteComments(Integer postId);
}
