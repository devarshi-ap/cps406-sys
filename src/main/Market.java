package main;
import java.util.HashMap;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;


public class Market {
    public static HashMap<String, Stock> stocks;
    private static int idCounter = 0;

    public static void main(String[] args) {
        // main program to run
        


        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you using this program as an Admin(0) or Investor(1)?\n> ");

        String userInput = scanner.nextLine();

        if (userInput == "0") {
            Admin user;
        }
        else if (userInput == "1") {
            Investor user;
        }
        else{
            while (userInput != "0" | userInput != "1"){
                scanner = new Scanner(System.in);
                System.out.println("Please input the respective value for Admin/Investor access (0/1).\n> ");
                userInput = scanner.nextLine();
            }
            if (userInput == "0") {
                Admin user;
            }
            else if (userInput == "1") {
                Investor user;
            }
        }
    }

    /**
     * Return all stocks
     * 
     * @return   A hash map of every stock 
     */
    public HashMap<String, Stock> getAllStocks() {
        return stocks;
    }

    /**
     * Verify stock exists
     * 
     * @param   sts Stock ticker symbol
     * 
     * @return   Boolean value corresponding to if the stock exists
     */
    public static boolean verifyStock(String sts) {
        return stocks.containsKey(sts);
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
        Help Manual
        -----------------------------------------------

        exit
        log
        export
        stock-price
        new-stock
        volume
        buy
        sell-stock
        add-watch
        del-watch
        get-watch
        get-portfolio
        wallet
        deposit
        man
        overwrite-watchlist
        list-users
        remove-user
        list-stocks
        
        -----------------------------------------------
        """);
    }

     /**
     * Generate ID for new user
     * 
     * @return   Incremented ID 
     */
    public static int generateID() {
        return idCounter++;
    }
}