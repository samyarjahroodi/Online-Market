package service.impl;

import base.baseService.baseServiceImpl.BaseEntityServiceImpl;
import entity.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class UserServiceImpl
        extends BaseEntityServiceImpl<Integer, User, UserRepository>
        implements UserService {
    Scanner scanner = new Scanner(System.in);

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    public void register() throws SQLException {
        User user = new User(null);
        try {
            System.out.println("Enter your name");
            user.setName(scanner.nextLine());
            System.out.println("Enter your username");
            user.setUsername(scanner.nextLine());
            while (true) {
                if (!repository.doesUserExist(user.getUsername())) {
                    System.out.println("This username is new");
                    break;
                } else {
                    System.out.println("This username already exist");
                    System.out.println("Enter new username");
                    user.setUsername(scanner.nextLine());
                    break;
                }
            }

            System.out.println("Enter your password");
            user.setPassword(scanner.nextLine());
            int result = repository.register(user);
            if (result != 0) {
                System.out.println("SUCCESSFULLY ADDED TO THE DATABASE");
            } else {
                System.out.println("THERE IS A PROBLEM");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while accessing the database: " + e.getMessage());
        }


    }

    public void login() throws SQLException {
        User user = new User(null);
        boolean usernameExists = false;
        try {
            while (!usernameExists) {
                System.out.println("Enter your username");
                String username = scanner.next();
                usernameExists = repository.doesUserExist(username);

                if (usernameExists) {
                    System.out.println("YOUR USERNAME EXISTS");
                    user.setUsername(username);
                } else {
                    System.out.println("THERE IS NO MATCH IN DATABASE");
                }
            }
            while (true) {
                System.out.println("Enter your password");
                user.setPassword(scanner.next());
                boolean loginSuccessful = repository.login(user);

                if (loginSuccessful) {
                    System.out.println("SUCCESSFULLY LOGGED IN");
                    break;
                } else {
                    System.out.println("THERE IS A PROBLEM WITH EITHER YOUR USERNAME OR PASSWORD");
                }
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while accessing the database: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }

    }
}

