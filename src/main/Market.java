package main;
import java.util.HashMap;
import java.util.Scanner;


public class Market {
    public static HashMap<String, Stock> stocks;
    private static int idCounter = 0;

    public static void main(String[] args) {
        // main program to run
        


        Scanner scanner = new Scanner(System.in);
        System.out.println("");

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
        System.out.println("manpage");
    }

    // return current id counter and increment it
    public static int generateID() {
        return idCounter++;
    }
}