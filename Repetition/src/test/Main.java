package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bank.BankAccount;

public class Main {
    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                printMenuOfBank();
                switch (scanner.nextInt()) {
                    case 1: // create account
                        createAccount(scanner, accounts);
                        break;
                    case 2: // view all accounts
                        printSortedAccounts(accounts);
                        break;
                    case 3: // view account by number
                        System.out.print("Enter account number: ");
                        try {
                            int accountNumber = scanner.nextInt();
                            viewInfoOfAccount(accountNumber, accounts, scanner);
                        } catch (Exception e) {
                            System.out.println("Invalid account number.\n");
                        }
                        break;
                    case 4: // sort accounts by balance
                        printSortedAccounts(accounts);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.\n");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    public static void printMenuOfBank() {
        System.out.println("\n1. Create Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Account by Number");
        System.out.println("4. Sort Accounts by Balance");
        System.out.println("5. Exit");
        System.out.print("\nChoose an option: ");
    }

    public static void createAccount(Scanner scanner, List<BankAccount> accounts) {
        System.out.print("\nEnter initial balance: ");
        try {
            double initialBalance = scanner.nextDouble();
            BankAccount newAccount = new BankAccount(initialBalance);
            accounts.add(newAccount);
            System.out.println("\nAccount created with account number: " + newAccount.getAccountNumber());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static BankAccount findAccountByNumber(int accountNumber, List<BankAccount> accounts) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void viewInfoOfAccount(int accountNumber, List<BankAccount> accounts, Scanner scanner) {
        BankAccount account = findAccountByNumber(accountNumber, accounts);
        if (account != null) {
            while (true) {
                try {
                    printMenuOfAccount();
                    switch (scanner.nextInt()) {
                        case 1:
                            System.out.print("\nEnter deposit amount: ");
                            try { 
                                if (!scanner.hasNextDouble()) {
                                    throw new Exception("Invalid deposit amount.");
                                }
                            }
                            catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                scanner.next();
                                break;
                            }
                            double depositAmount = scanner.nextDouble();
                            account.deposit(depositAmount);
                            break;
                        case 2:
                            System.out.print("\nEnter withdrawal amount: ");
                            double withdrawAmount = scanner.nextDouble();
                            account.withdraw(withdrawAmount);
                            break;
                        case 3: // check balance
                            System.out.println("\nCurrent balance: " + account.getBalance());
                            break;
                        case 4:
                            printMenuOfTransactions();
                            switch (scanner.nextInt()) {
                                case 1:
                                    // by amount
                                    printSortedTransactionsByAmount(account);
                                    break;
                                case 2:
                                    // by date
                                    printSortedTransactionsByDate(account);
                                    break;
                                case 3:
                                    // by type
                                    printSortedTransactionsByType(account);
                                    break;
                                case 4:
                                    System.out.println("\nExiting...");
                                    return;

                                default:
                                    System.out.println("\nInvalid option. Please try again.\n");
                                    break;
                            }
                            break;
                        case 5:
                            System.out.println("\nExiting...\n");
                            return;

                        default:
                            System.out.println("\nInvalid option. Please try again.\n");
                            break;

                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } else {
            System.out.println("\nAccount not found.\n");
            return;
        }
    }

    public static void printMenuOfAccount() {
        System.out.println("\n1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. View Transactions");
        System.out.println("5. Exit");
        System.out.print("\nChoose an option: ");
    }

    public static void printMenuOfTransactions() {
        System.out.println("\n1. Sort by Amount");
        System.out.println("2. Sort by Date");
        System.out.println("3. Sort by Type");
        System.out.println("4. Exit");
        System.out.print("\nChoose an option: ");
    }

    public static void printSortedAccounts(List<BankAccount> accounts) {
        accounts.sort((a, b) -> Double.compare(a.getBalance(), b.getBalance()));
        System.out.println("\nAccounts:\n");
        for (BankAccount account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
        }
    }

    public static void printSortedTransactionsByAmount(BankAccount account) {
        List<BankAccount.Transaction> transactions = java.util.Arrays.asList(account.getTransactions());
        transactions.sort((a, b) -> Double.compare(a.getAmount(), b.getAmount()));
        System.out.println("\nTransactions sorted by amount:");
        for (BankAccount.Transaction t : transactions) {
            System.out.println(t.getType() + ": " + t.getAmount() + " at " + t.getTimestamp());
        }
    }

    public static void printSortedTransactionsByDate(BankAccount account) {
        List<BankAccount.Transaction> transactions = java.util.Arrays.asList(account.getTransactions());
        transactions.sort((a, b) -> a.getTimestamp().compareTo(b.getTimestamp()));
        System.out.println("\nTransactions sorted by date:");
        for (BankAccount.Transaction t : transactions) {
            System.out.println(t.getType() + ": " + t.getAmount() + " at " + t.getTimestamp());
        }
    }

    public static void printSortedTransactionsByType(BankAccount account) {
        List<BankAccount.Transaction> transactions = java.util.Arrays.asList(account.getTransactions());
        transactions.sort((a, b) -> a.getType().compareTo(b.getType()));
        System.out.println("\nTransactions sorted by type:");
        for (BankAccount.Transaction t : transactions) {
            System.out.println(t.getType() + ": " + t.getAmount() + " at " + t.getTimestamp());
        }
    }

}
