
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

    public User(boolean val) { // Admin (0 in wallet)
        this.name = "null";
        this.email = "null";
        this.id = -1;
    }

    /**
     * Return ID of User
     * 
     * @return user ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Return User object as a String
     * 
     * @return String of User object
     */
    public String toString() {
        return "\nID - " + this.id + "\n" + "Name - " + this.name + "\nEmail - " + this.email + "\n";
    }
}
