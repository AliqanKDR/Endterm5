package repositories;

import data.interfaces.IDB;
import entities.Category;
import entities.Post;
import repositories.interfaces.IPostRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostRepository implements IPostRepository {
    private final IDB db;

    public PostRepository(IDB db) {
        this.db = db;
    }

    // This method adds post to database
    @Override
    public boolean addPost(Post post) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO post (title, description, added_date, category_id) VALUES(?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, post.getTitle());
            st.setString(2, post.getDescription());
            st.setDate(3, Date.valueOf(LocalDate.now()));
            st.setInt(4, post.getCategory().getId());

            st.execute();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // This method finds post by id and returns it
    @Override
    public Post getPost(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT post.id, post.title, post.description, post.added_date, category.id, category.name FROM post INNER JOIN category ON post.category_id=category.id WHERE post.id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Post post = new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        new Category(rs.getInt(5), rs.getString(6)));

                return post;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds posts by title and returns them
    @Override
    public List<Post> getPosts(String title) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT post.id, post.title, post.description, post.added_date, category.id, category.name FROM post INNER JOIN category ON post.category_id=category.id WHERE post.title LIKE '%" + title + "%'";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            List<Post> posts = new ArrayList<>();

            while (rs.next()) {
                Post post = new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        new Category(rs.getInt(5), rs.getString(6)));

                posts.add(post);
            }

            return posts;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds all posts and returns them
    @Override
    public List<Post> getAllPosts() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT post.id, post.title, post.description, post.added_date, category.id, category.name FROM post INNER JOIN category ON post.category_id=category.id";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            List<Post> posts = new ArrayList<>();

            while (rs.next()) {
                Post post = new Post(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4).toLocalDate(),
                        new Category(rs.getInt(5), rs.getString(6)));

                posts.add(post);
            }

            return posts;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds post by id and removes it
    @Override
    public boolean removePost(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM post WHERE id=?";
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
}
