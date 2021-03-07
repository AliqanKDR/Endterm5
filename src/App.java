import controllers.Controller;
import entities.Category;
import entities.Post;
import entities.Role;
import entities.User;


import java.util.List;
import java.util.Scanner;

public class App {
    private final Controller controller;
    private final Scanner scanner;

    public App(Controller controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean loginRegistrationChoice = true;
        int loginRegistrationOption = 0;

        while (true) {
            if (loginRegistrationChoice) {
                System.out.println("Please, choose one option:\n1)Registration\n2)Login\n3)Exit");
                loginRegistrationOption = scanner.nextInt();
            }

            if (loginRegistrationOption == 1) {
                boolean registered = registrationMode();
                if (registered) {
                    loginRegistrationChoice = false;
                    loginRegistrationOption = 2;
                }
            } else if (loginRegistrationOption == 2) {
                User loggedUser = loginMode();
                if (loggedUser != null) {
                    if (loggedUser.getRole().getName().equals("user")) {
                        userMode(loggedUser);
                    } else {
                        adminMode(loggedUser);
                    }
                    loginRegistrationChoice = true;
                }
            } else {
                break;
            }
        }
    }

    private boolean registrationMode() {
        System.out.println("***Registration***");
        System.out.println("Write name:");
        String name = scanner.next();
        System.out.println("Write surname:");
        String surname = scanner.next();
        System.out.println("Write username:");
        String username = scanner.next();
        if (controller.getUser(username) != null) {
            System.out.println("This username is used! Please, write another username!");
            return false;
        }
        System.out.println("Write password:");
        String password = scanner.next();

        return controller.registration(name, surname, username, password);
    }

    private User loginMode() {
        System.out.println("***Login***");
        System.out.println("Write username:");
        String username = scanner.next();
        System.out.println("Write password:");
        String password = scanner.next();

        return controller.login(username, password);
    }

    private void userMode(User user) {
        System.out.println("Hello, " + user.getName());

        while (true) {
            System.out.println("Please, choose one option:\n1)My Profile\n2)Delete my Profile\n3)Posts\n4)Save post\n5)Delete post\n6)Saved posts\n7)Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println(user.toString());
            } else if (choice == 2) {
                controller.removeUserById(user.getId());
                break;
            } else if (choice == 3) {
                System.out.println("Please, choose any option:\n1)All posts\n2)Posts by title");
                int option = scanner.nextInt();

                if (option == 1) {
                    List<Post> postList = controller.getPosts();
                    for (Post post : postList) {
                        System.out.println(post.toString());
                    }
                } else if (option == 2) {
                    System.out.println("Write title");
                    String title = scanner.next();

                    List<Post> postList = controller.getPosts(title);
                    for (Post post : postList) {
                        System.out.println(post.toString());
                    }
                }
            } else if (choice == 4) {
                List<Post> postList = controller.getPosts();

                for (Post post : postList) {
                    System.out.println(post.toString());
                }

                System.out.println("Choose any post!");
                int id = scanner.nextInt();
                controller.addPostToSavedPosts(user.getId(), id);
            } else if (choice == 5) {
                System.out.println("Your saved posts:");

                List<Post> posts = controller.savedPosts(user.getId());

                for (Post post : posts) {
                    System.out.println(post.toString());
                }

                System.out.println("Choose any post!");
                int id = scanner.nextInt();

                controller.removePostFromSavedPosts(user.getId(), id);
            } else if (choice == 6) {
                System.out.println("Your saved posts");

                List<Post> postList = controller.savedPosts(user.getId());

                for (Post post : postList) {
                    System.out.println(post.toString());
                }
            } else {
                break;
            }
        }
    }

    private void adminMode(User user) {
        System.out.println("Hello, " + user.getName());

        while (true) {
            System.out.println("Please, choose one option:\n1)My Profile\n2)Delete my Profile\n3)Posts\n4)Save post\n5)Delete post\n6)Saved posts\n7)List of Users\n8)Add User\n9)Delete User\n10)Add post\n11)Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println(user.toString());
            } else if (choice == 2) {
                controller.removeUserById(user.getId());
                break;
            } else if (choice == 3) {
                System.out.println("Please, choose any option:\n1)All posts\n2)Posts by title");
                int option = scanner.nextInt();

                if (option == 1) {
                    List<Post> postList = controller.getPosts();
                    for (Post post : postList) {
                        System.out.println(post.toString());
                    }
                } else if (option == 2) {
                    System.out.println("Write title");
                    String title = scanner.next();

                    List<Post> postList = controller.getPosts(title);
                    for (Post post : postList) {
                        System.out.println(post.toString());
                    }
                }
            } else if (choice == 4) {
                List<Post> postList = controller.getPosts();

                for (Post post : postList) {
                    System.out.println(post.toString());
                }

                System.out.println("Choose any post!");
                int id = scanner.nextInt();
                controller.addPostToSavedPosts(user.getId(), id);
            } else if (choice == 5) {
                System.out.println("Your saved posts:");

                List<Post> postList = controller.savedPosts(user.getId());

                for (Post post : postList) {
                    System.out.println(post.toString());
                }

                System.out.println("Choose any post! Write post's id!");
                int id = scanner.nextInt();

                controller.removePostFromSavedPosts(user.getId(), id);
            } else if (choice == 6) {
                System.out.println("Your saved posts:");

                List<Post> postList = controller.savedPosts(user.getId());

                for (Post post : postList) {
                    System.out.println(post.toString());
                }
            } else if (choice == 7) {
                System.out.println("Users:");

                List<User> userList = controller.listOfUsers();
            } else if (choice == 8) {
                System.out.println("Write name:");
                String name = scanner.next();
                System.out.println("Write surname:");
                String surname = scanner.next();
                System.out.println("Write username:");
                String username = scanner.next();
                if (controller.getUser(username) != null) {
                    System.out.println("This username is used! Please, write another username!");
                }
                System.out.println("Write password:");
                String password = scanner.next();

                System.out.println("Roles:");
                List<Role> roles = controller.getRoles();

                for (Role role : roles) {
                    System.out.println(role.toString());
                }

                System.out.println("Please, choose one role!");
                int id = scanner.nextInt();

                Role role = controller.getRole(id);

                controller.addUser(name, surname, username, password, role);
            } else if (choice == 9) {
                System.out.println("Users:");

                List<User> userList = controller.listOfUsers();

                System.out.println("Please, choose one user to delete! Write user's id!");
                int id = scanner.nextInt();

                controller.removeUserById(id);
            } else if (choice == 10) {
                System.out.println("Write title!");
                String title = scanner.next();
                System.out.println("Write description!");
                String description = scanner.next();

                List<Category> categoryList = controller.getCategories();

                System.out.println("Categories:");

                for (Category category : categoryList) {
                    System.out.println(category.toString());
                }

                System.out.println("Please, choose one category!");

                int category_id = scanner.nextInt();

                controller.addPost(title, description, controller.getCategory(category_id));
            } else {
                break;
            }
        }
    }
}