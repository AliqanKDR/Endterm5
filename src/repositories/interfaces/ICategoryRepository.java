package repositories.interfaces;

import entities.Category;
import entities.Role;

import java.util.List;

public interface ICategoryRepository {
    Category getCategory(int id);
    Category getCategory(String name);
    List<Category> allCategories();
}
