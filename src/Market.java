import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Market {
    public static HashMap<String, Stock> stocks = new HashMap<>();
    private static int idCounter = 0;

    /**
     * Return all stocks
     * 
     * @return A hash map of every stock
     */
    public HashMap<String, Stock> getAllStocks() {
        return stocks;
    }

    /**
     * Verify stock exists
     * 
     * @param sts Stock ticker symbol
     * 
     * @return Boolean value corresponding to if the stock exists
     */
    public static boolean verifyStock(String sts) {
        return stocks.containsKey(sts);
    }

    public void addStock(String sts, Stock stock) {
        stocks.put(sts, stock);
    }

    /**
     * Exit program with success status
     */
    public void end() {
        System.exit(0);
    }

    /**
     * Print manual page with list of functions
     */
    public void manpage() {
        System.out.println(
                """
                        Manual Page
                        -----------------------------------------------+
                        exit\t\t\t<exits market program>
                        log\t\t\t<displays user's recent transactions (INVESTOR ONLY)>
                        export\t\t\t<exports file containing user's log of transactions (INVESTOR ONLY)>
                        stock-price\t\t<gets relevant prices of a given stock>
                        new-stock\t\t<adds a new stock into the market (ADMIN ONLY)>
                        volume\t\t\t<get volume of a stock>
                        buy\t\t\t<buy a stock (INVESTOR ONLY)>
                        sell\t\t\t<sell a stock (INVESTOR ONLY)>
                        add-watch\t\t<add stock to watchlist (INVESTOR ONLY)>
                        del-watch\t\t<delete stock from watchlist (INVESTOR ONLY)>
                        get-watch\t\t<get user's watchlist (INVESTOR ONLY)>
                        get-portfolio\t\t<get user's trading portfolio (INVESTOR ONLY)>
                        wallet\t\t\t<get amount of money in user's wallet (INVESTOR ONLY)>
                        deposit\t\t\t<deposit funds into wallet (INVESTOR ONLY)>
                        withdraw\t\t<withdraw funds from wallet (INVESTOR ONLY)>
                        man\t\t\t<displays the manual page for this market system>
                        overwrite-watchlist\t<overwrites data on an investor's watchlist (ADMIN ONLY)>
                        list-users\t\t<displays a list of all user's in the market (ADMIN ONLY)>
                        remove-user\t\t<removes a user from the market (ADMIN ONLY)>
                        list-stocks\t\t<displays a list of all the stocks in the market>
                        -----------------------------------------------+
                        """);
    }

    /**
     * Generate ID for new user
     * 
     * @return Incremented ID
     */
    public static int generateID() {
        return idCounter++;
    }

    public void readStocks() throws FileNotFoundException {
        File f = new File("src/stocks.txt");
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            Stock stock;
            String[] data;

            String line = sc.nextLine();
            data = line.split(",");
            int[] prices = { Integer.valueOf(data[2]), Integer.valueOf(data[3]), Integer.valueOf(data[4]),
                    Integer.valueOf(data[5]), Integer.valueOf(data[6]) };
            stock = new Stock(data[0], data[1], prices, Integer.valueOf(data[7]), Integer.valueOf(data[8]));
            stocks.put(data[1], stock);
        }

        sc.close();
    }
}