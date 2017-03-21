package mum.edu.carpooling.service;

import java.util.ArrayList;

import mum.edu.carpooling.domain.Post;

public interface UserPostService {
	public ArrayList<Post> getUserPosts();
	public boolean insertUserPost(String userName, String title, String content, Integer postType);
	public boolean removeUserPost(Integer postId);
	public boolean isNotification(String userName);
}
