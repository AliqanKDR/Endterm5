import controllers.Controller;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.CategoryRepository;
import repositories.PostRepository;
import repositories.RoleRepository;
import repositories.UserRepository;
import repositories.interfaces.ICategoryRepository;
import repositories.interfaces.IPostRepository;
import repositories.interfaces.IRoleRepository;
import repositories.interfaces.IUserRepository;

public class Main {

    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IRoleRepository roleRepository = new RoleRepository(db);
        IPostRepository postRepository = new PostRepository(db);
        IUserRepository userRepository = new UserRepository(db);
        ICategoryRepository categoryRepository = new CategoryRepository(db);
        Controller controller = new Controller(categoryRepository, userRepository, postRepository, roleRepository);
        App app = new App(controller);
        app.start();
    }
}
