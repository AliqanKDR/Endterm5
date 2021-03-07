package repositories.interfaces;

import entities.Post;

import java.util.List;

public interface IPostRepository {
    boolean addPost(Post post);
    Post getPost(int id);
    List<Post> getPosts(String title);
    List<Post> getAllPosts();
    boolean removePost(int id);
}
