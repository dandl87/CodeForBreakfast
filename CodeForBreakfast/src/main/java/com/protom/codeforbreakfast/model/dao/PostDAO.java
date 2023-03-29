package com.protom.codeforbreakfast.model.dao;

import java.util.ArrayList;

import com.protom.codeforbreakfast.model.entity.Post;
 

public interface PostDAO {
	
	boolean createPost(Post post);
	Post readPost(int idPost);
	boolean updatePost(Post post); 
	boolean deletePost(int idPost);  
	ArrayList<Post> readAllPosts();
	ArrayList<Post> readAllPostsOfPage(int n);

}
