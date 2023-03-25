package main;

import java.util.HashMap;

public class Admin extends User {
    /**
     * Instance variables for Admin objects
     */
    private HashMap<Integer, Investor> investors;

    /**
     * Instantiate an Admin object
     */
    public Admin(String name, String email) {
        super(name, email);
    }

    public String toString() {
        return super.toString() + "\nClients - " + this.getAllInvestors().size();
    }

    public HashMap<Integer, Investor> getAllInvestors() {
        return this.investors;
    }

    public void newInvestor(String name, String email, int initial_amount) {
        Investor inv = new Investor(name, email, initial_amount);
        this.investors.put(inv.getId(), inv);
    }
}
