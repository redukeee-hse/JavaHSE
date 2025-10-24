package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bank.*;

public class Main {
    public static void main(String[] args) {
        runSimulation();
    }

    public static void runSimulation() {
        String fileName = "users.ser";
        MenuPrinter menuPrinter = new MenuPrinter();
        BankAccountManager bankAccountManager = new BankAccountManager();
        HashMap<String, User> users = SerializationHelper.loadUsersFromFile(fileName); 
        UserManager userManager = new UserManager(users);
        User currentUser = userManager.userSignIn();
        ArrayList<BankAccount> accounts = currentUser.getBankAccounts();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                menuPrinter.printMainMenu();
                String input = scanner.nextLine().trim();
                try {
                    int choice = Integer.parseInt(input);
                    
                    switch (choice) {
                        case 1: 
                            bankAccountManager.createAccount(scanner, accounts);
                            break;
                        case 2: 
                            bankAccountManager.printSortedAccounts(accounts);
                            break;
                        case 3: 
                            System.out.print("Enter account number: ");
                            try {
                                int accountNumber = scanner.nextInt();
                                scanner.nextLine(); 
                                bankAccountManager.viewInfoOfAccount(accountNumber, accounts, scanner, bankAccountManager, menuPrinter);
                            } catch (Exception e) {
                                System.out.println("Invalid account number.\n");
                            }
                            break;
                        case 4: 
                            bankAccountManager.printSortedAccounts(accounts);
                            break;
                        case 5:
                            System.out.println("Exiting...");
                            scanner.close();
                            SerializationHelper.saveUsersToFile(users, fileName);
                            return;
                        default:
                            System.out.println("Invalid option. Please try again.\n");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.\n");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
