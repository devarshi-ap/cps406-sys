package main;
// import java.util.Scanner;
import java.util.HashMap;

public class Market {
    private HashMap<String, Stock> stocks;

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
}