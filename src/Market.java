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
     * Retrieve stock object from stocks HashMap
     * 
     * @param String sts
     * @return stock object
     */
    public Stock getStock(String sts) {
        return stocks.get(sts.toUpperCase());
    }

    /**
     * Verify stock exists
     * 
     * @param sts Stock ticker symbol
     * 
     * @return Boolean value corresponding to if the stock exists
     */
    public static boolean verifyStock(String sts) {
        return stocks.containsKey(sts.toUpperCase());
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

                        list-stocks\t\t<displays a list of all the stocks in the market>
                        get-stock\t\t<gets relevant prices of a given stock>
                        man\t\t\t<displays the manual page for this market system>
                        exit\t\t\t<exits market program>

                        ---> INVESTOR-ONLY
                        wallet\t\t\t<get amount of money in user's wallet>
                        buy\t\t\t<buy a stock>
                        sell\t\t\t<sell a stock>
                        log\t\t\t<displays user's recent transactions>
                        export\t\t\t<exports file containing user's log of transactions>
                        watch-add\t\t<add stock to watchlist>
                        watch-rem\t\t<remove stock from watchlist>
                        watchlist\t\t<get user's watchlist>
                        portfolio\t\t<get user's trading portfolio>
                        deposit\t\t\t<deposit funds into wallet>

                        ---> ADMIN-ONLY
                        new-stock\t\t<adds a new stock into the market>
                        list-users\t\t<displays a list of all user's in the market>
                        overwrite-watchlist\t<overwrites data on an investor's watchlist>
                        *remove-user\t\t<removes a user from the market>

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

    /**
     * Reads stock data from file and adds to stocks hash map
     * 
     */
    public void readStocks() throws FileNotFoundException {
        File f = new File("utils/stocks.txt");
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            Stock stock;
            String[] data;

            String line = sc.nextLine();
            data = line.split(",");
            int[] prices = { Integer.valueOf(data[2]), Integer.valueOf(data[3]), Integer.valueOf(data[4]),
                    Integer.valueOf(data[5]), Integer.valueOf(data[6]) };
            stock = new Stock(data[0], data[1], prices, Integer.valueOf(data[7]));
            stocks.put(data[1], stock);
        }

        sc.close();
    }
}