package repositories;

import data.interfaces.IDB;
import entities.Role;
import repositories.interfaces.IRoleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository implements IRoleRepository {
    private final IDB db;

    public RoleRepository(IDB db) {
        this.db = db;
    }

    // This method finds role by id and returns it
    @Override
    public Role getRole(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM role WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Role role = new Role(rs.getInt("id"), rs.getString("name"));

                return role;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds category by name and returns it
    @Override
    public Role getRole(String name) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM role WHERE name = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Role role = new Role(rs.getInt("id"), rs.getString("name"));

                return role;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // This method finds all roles and returns them
    @Override
    public List<Role> getRoles() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM role";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            List<Role> roles = new ArrayList<>();

            while (rs.next()) {
                Role role = new Role(rs.getInt("id"), rs.getString("name"));

                roles.add(role);
            }

            return roles;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
