package main;
import java.util.HashMap;
import java.util.ArrayList;

public class Admin {
    /**
     * Instance variables for Admin objects
     */
    private int id;
    private String email;
    private HashMap<Integer, User> users;

    /**
     * Instantiate an Admin object
     */
    public Admin(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public HashMap<Integer, User> getAllUsers() {
        return this.users;
    }

    public void newUser(String name, String email, int initial_amount) {

    }
}
