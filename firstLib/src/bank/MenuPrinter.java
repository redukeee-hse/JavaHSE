package bank;

public class MenuPrinter {

    public void printMainMenu() {
        System.out.println("\n1. Create Account");
        System.out.println("2. View All Accounts");
        System.out.println("3. Account by Number");
        System.out.println("4. Sort Accounts by Balance");
        System.out.println("5. Exit");
        System.out.print("\nChoose an option: ");
    }

    public void printAccountMenu() {
        System.out.println("\n1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. View Transactions");
        System.out.println("5. Exit");
        System.out.print("\nChoose an option: ");
    }

    public void printTransactionMenu() {
        System.out.println("\n1. Sort by Amount");
        System.out.println("2. Sort by Date");
        System.out.println("3. Sort by Type");
        System.out.println("4. Exit");
        System.out.print("\nChoose an option: ");
    }
}
