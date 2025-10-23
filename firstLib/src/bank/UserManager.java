package bank;

import java.util.HashMap;
import java.util.Scanner;

public class UserManager {
    private HashMap<String, User> users;

    public UserManager(HashMap<String, User> users) {
        this.users = users;
    }

    public User userSignIn() {
        Scanner scanner = new Scanner(System.in);
        User user;
        while(true) {
            System.out.println("Do you have an account?[y,n]");
            String hasAccount = scanner.nextLine().trim();
            if (hasAccount.equals("y")) {
                user = signIn(scanner);
                if (user == null) {
                    continue;
                } else {
                    return user;
                }
            } else if (hasAccount.equals("n")) {
                user = signUp(scanner);
                if (user == null) {
                    continue;
                } else {
                    return user;
                }
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    private User signIn(Scanner scanner) {
        System.out.print("Enter your login: ");
        String login = scanner.nextLine().trim();
        User user = users.get(login);
        if (user == null) {
            System.out.printf("Account %s is not found\n", login);
        } else {
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (password.equals(user.getPassword())) {
                System.out.printf("You are welcome, %s\n", login);
                return user;
            } else {
                System.out.println("Incorrect password.");
            }
        }
        return null;
    }

    private User signUp(Scanner scanner) {
        System.out.print("Enter your future login: ");
        String login = scanner.nextLine().trim();
        if (users.get(login) == null) {
            System.out.print("Enter your future password: ");
            String password = scanner.nextLine();
            User user = new User(login, password);
            users.put(login, user);
            System.out.println("Account created for " + login);
            return user;
        } else {
            System.out.println("Account already exists");
        }
        return null;
    }
}
