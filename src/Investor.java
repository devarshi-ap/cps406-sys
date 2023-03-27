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
        } else {
            System.out.println("Exceeds Wallet Max or Invalid Amount Entered!");
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
                this.addToPortfolio(sts, shares);

                System.out.println("Successfully bought " + shares + " shares of " + sts + "!\n");
            } else {
                System.out.println("Insufficient Funds or Invalid Amount Entered!\n");
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
        if (this.getPortfolio().containsKey(sts)) {
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
        
        if (this.transactions != null){
            PrintWriter pw = new PrintWriter(new FileWriter("Transactions.txt", false));
            for (String txn : this.transactions) {
                pw.print(txn);
            }
            pw.close();
        }
        else
        {
            System.out.println("User has no transactions to export.");
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
        this.watchlist.add(sts.toUpperCase());
    }

    public void removeFromWatchlist(String sts) {
        for (int i = 0; i < this.watchlist.size(); i++) {
            if (this.watchlist.get(i) == sts) {
                this.watchlist.remove(i);
            }
        }
    }

    public boolean inWatchlist(String sts) {
        for (int i = 0; i < this.watchlist.size(); i++)
            if (this.watchlist.get(i) == sts)
                return true;
        return false;
    }

}
