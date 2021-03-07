package repositories.interfaces;

import entities.User;
import entities.Post;

import java.util.List;

public interface IUserRepository {
    boolean addUser(User user);
    User getUser(int id);
    User getUser(String username);
    List<User> getAllUsers();
    boolean removeUser(int id);
    boolean savePost(int userId, int postId);
    List<Post> getAllSavedPosts(int userId);
    boolean removePost(int userId, int postId);
}
