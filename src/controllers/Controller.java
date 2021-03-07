package controllers;

import entities.Category;
import entities.Post;
import entities.Role;
import entities.User;
import repositories.interfaces.ICategoryRepository;
import repositories.interfaces.IPostRepository;
import repositories.interfaces.IRoleRepository;
import repositories.interfaces.IUserRepository;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private final ICategoryRepository categoryRepository;
    private final IUserRepository userRepository;
    private final IPostRepository postRepository;
    private final IRoleRepository roleRepository;

    public Controller(ICategoryRepository categoryRepository, IUserRepository userRepository, IPostRepository postRepository, IRoleRepository roleRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.roleRepository = roleRepository;
    }

    // This method returns user who logged in
    public User login(String username, String password) {
        User user = userRepository.getUser(username);

        if (user.getPassword().equals(password)) {
            System.out.println("You logged in successfully!");
            return user;
        }

        System.out.println("Your username or password is not correct!");
        return null;
    }

    // This method registers user
    public boolean registration(String name, String surname, String username, String password) {
        User user = new User(name, surname, username, password, new Role(1));

        boolean isAdded = userRepository.addUser(user);

        if (isAdded) {
            System.out.println("You were registered successfully!");
        } else {
            System.out.println("Your registration was failed!");
        }

        return isAdded;
    }

    // This method finds user by id and returns it
    public User userProfile(int id) {
        User user = userRepository.getUser(id);

        System.out.println(user.toString());

        return user;
    }

    // This method finds user by id and removes it
    public boolean removeUserById(int id) {
        boolean isDeleted = userRepository.removeUser(id);

        if (isDeleted) {
            System.out.println("You deleted account successfully!");
            return true;
        }

        System.out.println("Account has not been deleted!");
        return false;
    }

    // This method finds post by id and returns it
    public Post getPostById(int id) {
        Post post = postRepository.getPost(id);

        if (post != null) {
            return post;
        } else {
            System.out.println("There is no post with id=" + id);
            return null;
        }
    }

    // This method finds posts by title and returns them
    public List<Post> getPostsByTitle(String title) {
        List<Post> posts = postRepository.getPosts(title);

        if (!posts.isEmpty()) {
            return posts;
        } else {
            System.out.println("There is no post with title=" + title);
            return null;
        }
    }

    // This method finds posts and returns them
    public List<Post> getPosts() {
        List<Post> posts = postRepository.getAllPosts();

        if (!posts.isEmpty()) {
            return posts;
        } else {
            System.out.println("There is no post");
            return null;
        }
    }

    // This method adds post to saved posts of user
    public boolean addPostToSavedPosts(int userId, int postId) {
        boolean isAdded = userRepository.savePost(userId, postId);

        if (isAdded) {
            System.out.println("You saved post");
        } else {
            System.out.println("You didn't save post");
        }

        return isAdded;
    }

    // This method removes post from saved posts of user
    public boolean removePostFromSavedPosts(int userId, int postId) {
        boolean isDeleted = userRepository.removePost(userId, postId);

        if (isDeleted) {
            System.out.println("You deleted post");
        }
        else {
            System.out.println("You didn't delete post");
        }

        return isDeleted;
    }

    // This method returns all users
    public List<User> listOfUsers() {
        List<User> users = userRepository.getAllUsers();

        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user.toString());
            }
        }
        else {
            System.out.println("There is no user");
        }

        return users;
    }

    // This method adds user
    public boolean addUser(String name, String surname, String username, String password, Role role) {
        User user = new User(name, surname, username, password, role);

        boolean isAdded = userRepository.addUser(user);

        if (isAdded) {
            System.out.println("You added user!");
        } else {
            System.out.println("You didn't add user!");
        }

        return isAdded;
    }

    // This method adds post
    public boolean addPost(String title, String description, Category category) {
        boolean isAdded = postRepository.addPost(new Post(title, description, LocalDate.now(), category));

        if (isAdded) {
            System.out.println("You added post");
        }
        else {
            System.out.println("You didn't add post");
        }

        return isAdded;
    }

    // This method returns user by username
    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    // This method returns user's saved posts
    public List<Post> savedPosts(int userId) {
        return userRepository.getAllSavedPosts(userId);
    }

    // This method returns all roles
    public List<Role> getRoles() {
        return roleRepository.getRoles();
    }

    // This method returns role by id
    public Role getRole(int id) {
        return roleRepository.getRole(id);
    }

    // This method returns all categories
    public List<Category> getCategories() {
        return categoryRepository.allCategories();
    }

    // This method returns category by id
    public Category getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    // This method returns posts by title
    public List<Post> getPosts(String title) {
        return postRepository.getPosts(title);
    }
}
