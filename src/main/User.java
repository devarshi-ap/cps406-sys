package main;

public class User {
    /**
     * Instance variables for User objects
     */
    private int id;
    private String name, email;

    /**
     * Instantiate User object
     */
    public User(String name, String email) { // Admin (0 in wallet)
        this.name = name;
        this.email = email;
        this.id = Market.generateID();
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return "ID - " + this.id + "\n" + "Name - " + this.name + "\nEmail - " + this.email + "\n";
    }
}
