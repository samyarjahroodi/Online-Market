package until;

import java.util.Scanner;

public class Validation {
    static Scanner input = new Scanner(System.in);

    public static String isValidPasswordForRegistration() {
        String password;
        String confirmPassword;
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{7,20}$";

        while (true) {
            System.out.print("Enter a password: ");
            password = input.next();

            if (!password.matches(regex)) {
                System.out.println("THE PASSWORD IS TOO WEAK!!");
                continue;
            }

            System.out.print("Confirm your password: ");
            confirmPassword = input.next();

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Please try again.");
            } else {
                System.out.println("Registration successful!");
                return password;
            }
        }
    }

    public static String isValidUsernameForRegistration() {
        String username;
        String regex = "^[A-Za-z][A-Za-z0-9_]{7,29}$";

        while (true) {
            System.out.print("Enter a username: ");
            username = input.next();
            if (!username.matches(regex)) {
                System.out.println("Invalid username. Username must start with a letter, and be between 8 and 30 characters long, consisting of letters, numbers, and underscores.");
            } else {
                System.out.println("Username is valid!");
                return username;
            }
        }
    }


}
