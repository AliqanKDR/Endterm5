package repositories;

import data.interfaces.IDB;
import entities.Category;
import entities.Role;
import repositories.interfaces.ICategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    private final IDB db;

    public CategoryRepository(IDB db) {
        this.db = db;
    }

    // This method finds category by id and returns it
    @Override
    public Category getCategory(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM category WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Category category = new Category(rs.getInt("id"), rs.getString("name"));

                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds category by name and returns it
    @Override
    public Category getCategory(String name) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM category WHERE name = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Category category = new Category(rs.getInt("id"), rs.getString("name"));

                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds all categories and returns them
    public List<Category> allCategories() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "select * from category";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            List<Category> categories = new ArrayList<>();

            while(rs.next()) {
                Category category = new Category(rs.getInt("id"), rs.getString("name"));
                categories.add(category);
            }

            return categories;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
