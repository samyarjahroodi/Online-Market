package until;

import entity.Cart;
import repository.impl.CartRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.impl.CartServiceImpl;
import service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    UserServiceImpl userService = (UserServiceImpl) ApplicationContext.USER_SERVICE;
    CartServiceImpl cartService = (CartServiceImpl) ApplicationContext.cartService;

    public void menu() throws SQLException {
        System.out.println("----------------------------------------------------------");
        System.out.println("|                 WELCOME TO ONLINE SHOP                 |");
        System.out.println("----------------------------------------------------------");
        System.out.println("First you need to login or register");
        while (true) {
            System.out.println("1- Login");
            System.out.println("2- Register");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    userService.login();
                    break;
                case 2:
                    userService.register();
                    break;
                default: {
                    System.out.println("Invalid choice. Please either 1 (Login) or 2 (Register).");
                    continue;
                }
            }
            break;
        }
        while (true) {
            System.out.println("These are options");
            System.out.println("0-Available products");
            System.out.println("1- add to your cart");
            System.out.println("2- delete from your cart");
            System.out.println("3- edit your cart");
            System.out.println("4- see your items");
            System.out.println("5- see your sum");
            System.out.println("6- if you want to exit press 6");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    cartService.loadAllShoes();
                    cartService.loadAllElectronicAppliances();
                case 1:
                    cartService.add();
                    break;
                case 2:
                    cartService.delete();
                    break;
                case 3:
                    cartService.update();
                    break;
                case 4:
                    System.out.println();
                    cartService.load();
                    break;
                case 5:
                    int sum = cartService.sum();
                    System.out.println("Total sum of your cart: $" + sum);
                    break;
                case 6:
                    System.out.println("Good luck");
                    System.exit(0);
                default:
                    System.out.println("There is no available number for your choice");
            }
            System.out.println("********************************************************************************************************");
            System.out.println("************************************ Developed by Samyar Jahroodi **************************************");
            System.out.println("********************************************************************************************************");


        }


    }
}

