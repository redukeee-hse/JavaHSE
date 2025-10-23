package bank;

import java.io.*;
import java.util.HashMap;

public class SerializationHelper {

    public static void saveUsersToFile(HashMap<String, User> users, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(users);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static HashMap<String, User> loadUsersFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (HashMap<String, User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return new HashMap<>(); 
        }
    }
}
