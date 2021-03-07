package repositories.interfaces;

import entities.Role;

import java.util.List;

public interface IRoleRepository {
    Role getRole(int id);
    Role getRole(String name);
    List<Role> getRoles();
}
