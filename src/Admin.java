import java.util.HashMap;

public class Admin extends User {
    /**
     * Instance variables for Admin objects
     */
    private HashMap<Integer, Investor> investors = new HashMap<>();

    /**
     * Instantiate an Admin object
     */
    public Admin(String name, String email) {
        super(name, email);
        this.newInvestor("Steve Carell", "steve@gmail.com", 100_000);
        this.newInvestor("Christian Bale", "cbale@yahoo.com", 200_000);
        this.newInvestor("Ryan Gosling", "ryan@hotmail.com", 300_000);
        this.newInvestor("Brad Pitt", "brad@gmail.com", 400_000);
    }

    /**
     * Return Admin object as a string
     * 
     * @return String of Admin object
     */
    public String toString() {
        return super.toString() + "Clients - " + this.getAllInvestors().size();
    }

    /**
     * Return all investors stored in the HashMap
     * 
     * @return HashMap containing all investors
     */
    public HashMap<Integer, Investor> getAllInvestors() {
        return this.investors;
    }

    /**
     * Find and retrieve an investor object from
     * the investors HashMap by using an id
     * 
     * @param int id
     * @return investor object
     */
    public Investor getInvestor(int id) {
        return this.investors.get(id);
    }

    /**
     * Check in the investors HashMap if investor exists using a given id
     * Return True if investor exists, False otherwise
     * 
     * @param int id
     * @return True if investor exists, False otherwise
     */
    public boolean hasInvestor(int id) {
        return this.investors.keySet().contains(id);
    }

    /**
     * Creates a new Investor object and then adds the object to the HashMap
     * 
     * @param name
     * @param email
     * @param initial_amount
     */
    public void newInvestor(String name, String email, int initial_amount) {
        Investor inv = new Investor(name, email, initial_amount);
        this.investors.put(inv.getId(), inv);
    }

    /**
     * Adds investor object into the HashMap
     * 
     * @param investor
     */
    public void newInvestor(Investor investor) {
        this.investors.put(investor.getId(), investor);
    }

    /**
     * Removes investor object from the HashMap
     * 
     * @param id
     */
    public void remInvestor(int id) {
        this.investors.remove(id);
    }
}
