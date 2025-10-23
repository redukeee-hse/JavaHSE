package bank;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<BankAccount> accounts;
    String login;
    String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        accounts = new ArrayList<>();
    }


    public String getPassword() {
        return this.password;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return this.accounts;
    }

    
}
