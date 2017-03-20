package mum.edu.carpooling.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import mum.edu.carpooling.domain.Post;
import mum.edu.carpooling.repository.PostRepository;
import mum.edu.carpooling.service.UserPostService;

public class UserPostServiceImpl implements UserPostService {
	private PostRepository postDB = new PostRepository();

	@Override
	public ArrayList<Post> getUserPosts() {
		// TODO Auto-generated method stub		
		ArrayList<Post> posts = postDB.Select();
		return posts;
	}

	@Override
	public boolean insertUserPost(String userName, String title, String body, Integer postType) {
		// TODO Auto-generated method stub
		try {
			postDB.Insert(userName, title, body, postType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean removeUserPost(Integer postId) {
		// TODO Auto-generated method stub
		postDB.Delete(postId);
		return true;
	}

}
