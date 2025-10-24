package myfirstlib;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void whenAccountCreatedWithInitialBalanceThenAccountHaveSameBalance() {
        BankAccount account = new BankAccount(1000);
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void when500DepositedThenBalanceIncreased() {
        BankAccount account = new BankAccount(1000);
        account.deposit(500);
        assertEquals(1500, account.getBalance());
    }

    @Test
    public void whenWithdrawThenBalanceDecrease() {
        BankAccount account = new BankAccount(1000);
        account.withdraw(300);
        assertEquals(700, account.getBalance());
    }

    @Test
    public void whenCreateNewUserThenUserSavedInFile() {
        String fileName = "users.ser";
        HashMap<String, User> users = new HashMap<>();
        users.put("testUser", new User("testUser", "password"));
        SerializationHelper.saveUsersToFile(users, fileName);
        HashMap<String, User> loadedUsers = SerializationHelper.loadUsersFromFile(fileName);
        assertNotNull(loadedUsers.get("testUser"));
    }

    @Test
    public void whenTransferToAccountThenSenderBalanceDecreasedAndReceiverBalanceIncreased() {
        BankAccount sender = new BankAccount(1000);
        BankAccount receiver = new BankAccount(0);
        sender.accountTransfer(receiver, 500);
        assertEquals(sender.getBalance(), receiver.getBalance());
    }

    @Test
    public void whenInvalidInputForDepositThenBalanceStaySame() {
        BankAccount account = new BankAccount(0);
        account.deposit(-1000);
        assertEquals(0, account.getBalance());
    }

    @Test
    public void whenInvalidInputForWithdrawThenBalanceStaySame() {
        BankAccount account = new BankAccount(0);
        account.withdraw(-1000);
        assertEquals(0, account.getBalance());
    }
}
