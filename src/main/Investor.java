package main;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Investor extends User {
    /**
     * Instance variables for Investor objects
     */
    private int wallet;
    private ArrayList<String> transactions, watchlist;
    private HashMap<String, Integer> portfolio;

    /**
     * Instantiate a Investor object
     */
    public Investor(String name, String email, int initial_amount) { // Investor
        super(name, email);
        this.wallet = initial_amount;
    }

    public String toString() {
        return super.toString() + "Funds - " + this.wallet + "\n";
    }

    public int getWallet() {
        return this.wallet;
    }

    public void deposit(int amount) {
        if ((amount >= 0) && ((this.wallet + amount) <= 100_000_000)) {
            this.wallet += amount;
        } else {
            System.out.println("Amount must be positive integer & Wallet musn't exceed $100 Million after Deposit!");
        }
    }

    public void withdraw(int amount) {
        if ((amount >= 0) && ((this.wallet - amount) >= 0)) {
            this.wallet -= amount;
        } else {
            System.out.println("Amount must be positive integer & Wallet must be positive after Withdraw!");
        }
    }

    public void buy(String sts, int shares) {
        if (Market.verifyStock(sts)) {
            if (shares <= Market.stocks.get(sts).floating_shares) {
                // sts exists and enough floating shares present, buy stock
                int dollar_amt = Market.stocks.get(sts).market_price * shares;
                this.withdraw(dollar_amt);

                // add to transactions
                String txn = "BUY-" + sts.toUpperCase() + "-" + shares;
                this.addTransaction(txn);

                // subtract from stock floating-shares (fewer left)
                Market.stocks.get(sts).addToFloatingShares(0 - shares);

                // add stock to user's portfolio
                this.addToPortfolio(sts, shares);
            }
        }
    }

    public void sell(String sts, int shares) {
        if (Market.verifyStock(sts) && this.getPortfolio().containsKey(sts)) {
            if (this.getPortfolio().get(sts) >= shares) {
                // sts exists, investor owns stock share(s), and has more or equal amount of
                // shares wanting to be sold, then sell shares
                int dollar_amt = Market.stocks.get(sts).market_price * shares;
                this.deposit(dollar_amt);

                // add to transactions
                String txn = "SELL-" + sts.toUpperCase() + "-" + shares;
                this.addTransaction(txn);

                // add to stock floating-shares (more available)
                Market.stocks.get(sts).addToFloatingShares(shares);

                // add stock to user's portfolio
                this.removeFromPortfolio(sts);
            }
        }
    }

    public void exportTransactions() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("Transactions.txt", false));

        for(String txn: this.transactions) {
            pw.print(txn);
        }
        pw.close();
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
