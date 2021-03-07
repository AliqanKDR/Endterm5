package repositories;

import data.interfaces.IDB;
import entities.Category;
import entities.Post;
import entities.Role;
import entities.User;
import repositories.interfaces.IUserRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    // This method adds user to database
    @Override
    public boolean addUser(User user) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO \"user\" (name, surname, username, password, role_id) VALUES(?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setString(3, user.getUsername());
            st.setString(4, user.getPassword());
            st.setInt(5, user.getRole().getId());

            st.execute();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // This method finds user by id and returns it
    @Override
    public User getUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT \"user\".id, \"user\".name, \"user\".surname, \"user\".username, \"user\".password, role.id, role.name FROM \"user\" INNER JOIN role ON \"user\".role_id=role.id WHERE \"user\".id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        new Role(rs.getInt(6), rs.getString(7)));

                return user;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds user by username and returns it
    @Override
    public User getUser(String username) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT \"user\".id, \"user\".name, \"user\".surname, \"user\".username, \"user\".password, role.id, role.name FROM \"user\" INNER JOIN role ON \"user\".role_id=role.id WHERE \"user\".username=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        new Role(rs.getInt(6), rs.getString(7)));

                return user;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds all users and returns them
    @Override
    public List<User> getAllUsers() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT \"user\".id, \"user\".name, \"user\".surname, \"user\".username, \"user\".password, role.id, role.name FROM \"user\" INNER JOIN role ON \"user\".role_id=role.id";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            List<User> users = new ArrayList<>();

            while (rs.next()) {
                User user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        new Role(rs.getInt(6), rs.getString(7)));

                users.add(user);
            }

            return users;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds user by id and removes it
    @Override
    public boolean removeUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM \"user\" WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            st.execute();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // This method adds post to user's saved posts
    @Override
    public boolean savePost(int userId, int postId) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO user_post VALUES (?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, userId);
            st.setInt(2, postId);

            st.execute();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // This method finds all saved posts of user and returns them
    @Override
    public List<Post> getAllSavedPosts(int userId) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT post.id, post.title, post.description, post.added_date, category.id, category.name FROM post INNER JOIN category ON post.category_id=category.id INNER JOIN user_post ON post.id=user_post.post_id INNER JOIN \"user\" ON user_post.user_id=\"user\".id WHERE \"user\".id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();

            List<Post> postList = new ArrayList<>();

            while (rs.next()) {
                Post post = new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        new Category(rs.getInt(5), rs.getString(6)));

                postList.add(post);
            }

            return postList;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds post from saved posts of user and removes it
    @Override
    public boolean removePost(int userId, int postId) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM user_post WHERE user_id=? AND post_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, postId);

            st.execute();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
