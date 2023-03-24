package main;
// import java.util.Scanner;
import java.util.HashMap;

public class Market {
    private HashMap<Integer, User> users;
    private HashMap<String, Stock> stocks;

    public static void main(String[] args) {
        // main program to run
    }

    public HashMap<String, Stock> getAllStocks() {
        return this.stocks;
    }

    public HashMap<Integer, User> getAllUsers() {
        return this.users;
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