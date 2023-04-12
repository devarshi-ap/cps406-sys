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
    private ArrayList<String> transactions = new ArrayList<String>();
    private ArrayList<String> watchlist = new ArrayList<String>();
    private HashMap<String, Integer> portfolio = new HashMap<String, Integer>();

    /**
     * Instantiate a Investor object
     */
    public Investor(String name, String email, int initial_amount) { // Investor
        super(name, email);
        this.wallet = initial_amount;
    }

    public Investor(boolean val) { // Investor
        super(true);
        this.wallet = 0;
    }

    /**
     * Returns an Investor object as a String
     * 
     * @return String of Investor object
     */
    public String toString() {
        return super.toString() + "Funds - " + this.wallet;
    }

    /**
     * Returns the amount of money in Investor's wallet
     * 
     * @return int value of wallet
     */
    public int getWallet() {
        return this.wallet;
    }

    /**
     * Adds money to Investor's wallet by an int amount.
     * Prints an error message if the amount is not a positive integer.
     * or wallet funds exceed $100 million after adding amount
     * 
     * @param amount
     */
    public void deposit(int amount) {
        if ((amount >= 0) && ((this.wallet + amount) <= 100_000_000)) {
            this.wallet += amount;
            System.out.println("\n...Deposited!\n(new wallet : $ " + this.wallet + ")\n");
        } else {
            System.out.println("Exceeds Wallet Max or Invalid Amount Entered!\n");
        }
    }

    /**
     * Subtracts money from Investor's wallet by an int amount
     * Prints an error message if the amount is not a positive integer
     * or wallet funds fall below $0 after subtracting amount
     * 
     * @param amount
     */
    public void withdraw(int amount) {
        this.wallet -= amount;
    }

    /**
     * Investor buys a stock and adds the stock to the HashMap portfolio
     * 
     * @param sts
     * @param shares
     */
    public void buy(String sts, int shares) {
        if (Market.verifyStock(sts)) {
            if (shares <= Market.stocks.get(sts).floating_shares) {
                // sts exists and enough floating shares present, buy stock
                int dollar_amt = Market.stocks.get(sts).market_price * shares;

                if ((dollar_amt >= 0) && ((this.wallet - dollar_amt) >= 0)) {
                    this.withdraw(dollar_amt);

                    // add to transactions
                    String txn = "BUY-" + sts.toUpperCase() + "-" + shares;
                    this.addTransaction(txn);

                    // subtract from stock floating-shares (fewer left)
                    Market.stocks.get(sts).addToFloatingShares(0 - shares);

                    // add stock to user's portfolio
                    if (this.inPortfolio(sts)) {
                        this.updatePortfolio(sts, this.getPortfolio().get(sts) + shares);
                    } 
                    else {
                        this.addToPortfolio(sts, shares);   
                    }

                    System.out.println("Successfully bought " + shares + " shares of " + sts + "!\n");
                } else {
                    System.out.println("Insufficient Funds or Invalid Amount Entered!\n");
                }
            }
        }
    }

    /**
     * Investor sells a stock
     * 
     * @param sts
     * @param shares
     */
    public void sell(String sts, int shares) {
        if (this.getPortfolio().get(sts) >= shares) {
            int dollar_amt = Market.stocks.get(sts).market_price * shares;
            this.deposit(dollar_amt);

            // add to transactions
            String txn = "SELL-" + sts + "-" + shares;
            this.addTransaction(txn);

            // add to stock floating-shares (more available)
            Market.stocks.get(sts).addToFloatingShares(shares);

            if (shares == this.getPortfolio().get(sts)) {
                // remove stock from user's portfolio
                this.removeFromPortfolio(sts);
            } else {
                // update stock shares
                this.updatePortfolio(sts, this.getPortfolio().get(sts) - shares);
            }

            System.out.println("Successfully sold " + shares + " shares of " + sts + "!\n");
        } else {
            System.out.println("Only have " + this.getPortfolio().get(sts) + " shares of " + sts + "\n");
        }
    }

    /**
     * Exports the users transctions into a text file called 'Transactions.txt'
     */
    public void exportTransactions() throws IOException {
        if (!this.transactions.isEmpty()) {
            PrintWriter pw = new PrintWriter(new FileWriter("Transactions.txt", false));
            for (String txn : this.transactions) {
                pw.println(txn);
            }
            pw.close();
            System.out.println("Transactions exported successfully to Transactions.txt!\n");
        } else
            System.out.println("User has no transactions to export.\n");
    }

    /**
     * Returns the transcations to be used for exporting
     * 
     * @return String of transactions
     */
    public ArrayList<String> getTransactions() {
        return this.transactions;
    }

    /**
     * Adds trancsations to the export list
     *
     * @param txn
     */
    public void addTransaction(String txn) {
        this.transactions.add(txn);
    }

    /**
     * 
     * Returns the portfolio of the user
     * 
     * @return Hashmap of portfolio
     */
    public HashMap<String, Integer> getPortfolio() {
        return this.portfolio;
    }

    /**
     * Adds stock to portfolio
     *
     * @param sts
     * @param shares
     */
    public void addToPortfolio(String sts, int shares) {
        this.portfolio.put(sts, shares);
    }

     /**
     * Check if stock is in portfolio
     *
     * @param sts
     */
    public boolean inPortfolio(String sts) {
        return this.portfolio.keySet().contains(sts);
    }

    /**
     * Remove stock from portfolio
     *
     * @param sts
     */
    public void removeFromPortfolio(String sts) {
        this.portfolio.remove(sts);
    }

    /**
     * Update stock shares in portfolio
     *
     * @param sts
     * @param new_shares
     */
    public void updatePortfolio(String sts, int new_shares) {
        this.portfolio.put(sts, new_shares);
    }

    /**
     * Returns the watch list of the user
     * 
     * @return String Watchlist
     */
    public ArrayList<String> getWatchlist() {
        return this.watchlist;
    }

    /**
     * Adds stock to watch list
     *
     * @param sts
     */
    public void addToWatchlist(String sts) {
        this.watchlist.add(sts.toUpperCase());
    }

    /**
     * Remove stock from watch list
     *
     * @param sts
     */
    public void removeFromWatchlist(String sts) {
        for (int i = 0; i < this.watchlist.size(); i++) {
            if (this.watchlist.get(i).equals(sts)) {
                this.watchlist.remove(i);
            }
        }
    }

    /**
     * Returns boolean of whether or not stock is in watchlist
     * 
     * @param sts
     * @return true/false if watchlist contains stock
     */
    public boolean inWatchlist(String sts) {
        for (int i = 0; i < this.watchlist.size(); i++)
            if (this.watchlist.get(i).equals(sts))
                return true;
        return false;
    }
}
