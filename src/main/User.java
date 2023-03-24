package main;
import java.util.HashMap;
import java.util.ArrayList;

public class User {
    /**
     * Instance variables for User objects
     */
    private int id, wallet;
    private String name, email;
    private ArrayList<String> transactions, watchlist;
    private HashMap<String, Integer> portfolio;

    /**
     * Instantiate a User object
     */
    public User(String name, String email, int initial_amount) {
        this.name = name;
        this.email = email;
        this.wallet = initial_amount;
    }

    public int getWallet() {
        return this.wallet;
    }

    public void deposit(int amount) {
        if ((amount >= 0) && ((this.wallet + amount) > 100_000_000)) {
            this.wallet += amount;
        } else {
            System.out.println("Amount must be positive integer & Wallet musn't exceed $100 Million after Deposit!");
        }
    }

    public void withdraw(int amount) {
        if ((amount >= 0) && ((this.wallet - amount) >= 0)) {
            this.wallet += amount;
        } else {
            System.out.println("Amount must be positive integer & Wallet must be positive after Withdraw!");
        }
    }

    public ArrayList<String> getTransactions() {
        return this.transactions;
    }

    public void addTransaction(String txn) {
        this.transactions.add(txn);
    }

    public HashMap<String, Integer> getPortfolio() {
        return this.portfolio;
    }

    public void addToPortfolio(String sts, int shares) {
        this.portfolio.put(sts, shares);
    }

    public void removeFromPortfolio(String sts) {
        this.portfolio.remove(sts);
    }

    public ArrayList<String> getWatchlist() {
        return this.watchlist;
    }

    public void addToWatchlist(String sts) {
        this.watchlist.add(sts);
    }

    public void removeFromWatchlist(String sts) {
        for (int i = 0; i < this.watchlist.size(); i++) {
            if (this.watchlist.get(i) == sts) {
                this.watchlist.remove(i);
            }
        }
    }
}
