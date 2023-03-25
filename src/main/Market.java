package main;
import java.util.HashMap;

public class Market {
    private HashMap<String, Stock> stocks;
    private static int idCounter = 0;

    public static void main(String[] args) {
        // main program to run
    }

    public HashMap<String, Stock> getAllStocks() {
        return this.stocks;
    }

    public boolean verifyStock(String sts) {
        return this.stocks.containsKey(sts);
    }

    public void end() {
        System.exit(0);
    }

    public void manpage() {
        System.out.println("manpage");
    }

    // return current id counter and increment it
    public static int generateID() {
        return idCounter++;
    }
}