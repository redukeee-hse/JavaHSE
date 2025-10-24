package bank;

import java.util.List;
import java.util.Scanner;

public class BankAccountManager {

    public void createAccount(Scanner scanner, List<BankAccount> accounts) {
        System.out.print("\nEnter initial balance: ");
        try {
            String input = scanner.nextLine().trim();  
            double initialBalance = Double.parseDouble(input);
            
            if (initialBalance >= 0) {
                BankAccount newAccount = new BankAccount(initialBalance);
                accounts.add(newAccount);
                System.out.println("\nAccount created with account number: " + newAccount.getAccountNumber());
            } else {
                System.out.println("Account not created. Initial balance must be >= 0.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Account not created. Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public BankAccount findAccountByNumber(int accountNumber, List<BankAccount> accounts) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void printSortedAccounts(List<BankAccount> accounts) {
        accounts.sort((a, b) -> Double.compare(a.getBalance(), b.getBalance()));
        System.out.println("\nAccounts:\n");
        for (BankAccount account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
        }
    }

    public void printSortedTransactionsByAmount(BankAccount account) {
        List<BankAccount.Transaction> transactions = java.util.Arrays.asList(account.getTransactions());
        transactions.sort((a, b) -> Double.compare(a.getAmount(), b.getAmount()));
        System.out.println("\nTransactions sorted by amount:");
        for (BankAccount.Transaction t : transactions) {
            System.out.println(t.getType() + ": " + t.getAmount() + " at " + t.getTimestamp());
        }
    }

    public void printSortedTransactionsByDate(BankAccount account) {
        List<BankAccount.Transaction> transactions = java.util.Arrays.asList(account.getTransactions());
        transactions.sort((a, b) -> a.getTimestamp().compareTo(b.getTimestamp()));
        System.out.println("\nTransactions sorted by date:");
        for (BankAccount.Transaction t : transactions) {
            System.out.println(t.getType() + ": " + t.getAmount() + " at " + t.getTimestamp());
        }
    }

    public void printSortedTransactionsByType(BankAccount account) {
        List<BankAccount.Transaction> transactions = java.util.Arrays.asList(account.getTransactions());
        transactions.sort((a, b) -> a.getType().compareTo(b.getType()));
        System.out.println("\nTransactions sorted by type:");
        for (BankAccount.Transaction t : transactions) {
            System.out.println(t.getType() + ": " + t.getAmount() + " at " + t.getTimestamp());
        }
    }

    public void viewInfoOfAccount(int accountNumber, List<BankAccount> accounts, Scanner scanner, BankAccountManager bankAccountManager, MenuPrinter menuPrinter) {
        BankAccount account = bankAccountManager.findAccountByNumber(accountNumber, accounts);
        if (account != null) {
            while (true) {
                try {
                    menuPrinter.printAccountMenu();
                    switch (scanner.nextInt()) {
                        case 1:
                            System.out.print("\nEnter deposit amount: ");
                            try { 
                                if (!scanner.hasNextDouble()) {
                                    throw new Exception("Invalid deposit amount.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                scanner.next();
                                break;
                            }
                            double depositAmount = scanner.nextDouble();
                            account.deposit(depositAmount);
                            break;
                        case 2:
                            System.out.print("\nEnter withdrawal amount: ");
                            try {
                                if (!scanner.hasNextDouble()) {
                                    throw new Exception("Invalid withdrawal amount.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                scanner.next();
                                break;
                            }
                            double withdrawAmount = scanner.nextDouble();
                            account.withdraw(withdrawAmount);
                            break;
                        case 3:
                            System.out.print("Enter transfer amount: "); 
                            double transferAmount = scanner.nextDouble();
                            bankAccountManager.printSortedAccounts(accounts);
                            System.out.print("Enter account to transfer: "); 
                            int accountNum = scanner.nextInt();
                            account.accountTransfer(bankAccountManager.findAccountByNumber(accountNum, accounts), transferAmount);
                            break;
                        case 4: 
                            System.out.println("\nCurrent balance: " + account.getBalance());
                            break;
                        case 5:
                            menuPrinter.printTransactionMenu();
                            switch (scanner.nextInt()) {
                                case 1:
                                    bankAccountManager.printSortedTransactionsByAmount(account);
                                    break;
                                case 2:
                                    bankAccountManager.printSortedTransactionsByDate(account);
                                    break;
                                case 3:
                                    bankAccountManager.printSortedTransactionsByType(account);
                                    break;
                                case 4:
                                    System.out.println("\nExiting...");
                                    return;

                                default:
                                    System.out.println("\nInvalid option. Please try again.\n");
                                    break;
                            }
                            break;
                        case 6:
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
}
