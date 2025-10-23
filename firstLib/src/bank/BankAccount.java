package bank;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    private double balance;
    private int accountNumber;
    private Transaction[] transactions;
    private int transactionCount;

    public class Transaction implements Serializable {
        private static final long serialVersionUID = 1L;
        private double amount;
        LocalDateTime timestamp;
        private String type;

        public Transaction(double amount, String type) {
            this.amount = amount;
            this.type = type;
            this.timestamp = LocalDateTime.now();
        }

        public double getAmount() {
            return amount;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getType() {
            return type;
        }
    }

    public BankAccount() {
        this.accountNumber = (int) ((float)Math.random() * 1000);
        this.balance = 0.0;
        this.transactions = new Transaction[100000];
        this.transactionCount = 0;
    }

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.accountNumber = (int) ((float)Math.random() * 1000);
            this.transactions = new Transaction[100000];
            this.transactionCount = 0;
            this.balance = initialBalance;
            System.out.println("\nAccount created with initial balance: " + initialBalance + "\n");
        } else {
            System.out.println("\n Invalid initial balance.");
            System.out.println("Account creation failed.\n");
            return;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction(amount, "Deposit");
            System.out.println("\nDeposited: " + amount + "\n");
        }
        else {
            System.out.println("\nInvalid deposit amount.\n");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction(amount, "Withdraw");
            System.out.println("\nWithdraw: " + amount + "\n");
        }
        else {
            System.out.println("\nInvalid withdraw amount.");
            if (amount > balance) {
                System.out.println("Insufficient funds.\n");
            }
            else {
                System.out.println("Amount must be positive.\n");
            }
        }
    }

    public void printBalance() {
        System.out.println(balance);
    }
    public void addTransaction(double amount, String type) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = new Transaction(amount, type);
        } else {
            System.out.println("\nTransaction limit reached.\n");
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Transaction[] getTransactions() {
        Transaction[] currentTransactions = new Transaction[transactionCount];
        System.arraycopy(transactions, 0, currentTransactions, 0, transactionCount);
        return currentTransactions;
    }

    


}