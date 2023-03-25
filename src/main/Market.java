package main;

import java.util.HashMap;
import java.util.Scanner;

public class Market {
    public static HashMap<String, Stock> stocks = new HashMap<>();
    private static int idCounter = 0;

    public static void main(String[] args) {
        // main program to run
        Scanner scanner = new Scanner(System.in);

        // prompt to run as Admin or Investor
        String userInput;
        do {
            System.out.print("Run program as Admin (0) || Investor (1) : ");
            userInput = scanner.nextLine();
            if (!userInput.equals("0") && !userInput.equals("1"))
                System.out.println("Try again!");
        } while (!userInput.equals("0") && !userInput.equals("1"));

        Investor investor;
        Admin admin;

        // prompt for User details
        System.out.print("Enter name : ");
        String name = scanner.nextLine();

        System.out.print("Enter email : ");
        String email = scanner.nextLine();

        // invoke appropriate constructor on user
        if (userInput.equals("0")) {
            // ADMIN
            System.out.println("You've chosen to play as Admin.");
            
            admin = new Admin(name, email);
            
            System.out.println("\n------+" + admin + "\n------+");
        } else {
            // INVESTOR
            int initial_amount;
            do {
                System.out.print("Enter initial amount in wallet ($100,000,000 MAX) : ");
                initial_amount = scanner.nextInt();
                if (initial_amount > 100_000_000) System.out.println("Try again!");
            } while (initial_amount > 100_000_000);
            
            investor = new Investor(name, email, initial_amount);
            admin = new Admin("Bill gates", "bill@gmail.com"); // default dummy admin
            admin.newInvestor(investor);
            
            System.out.println("\n------+" + investor + "\n------+");
            System.out.println("\n------+" + admin + "\n------+");
        }
        
        

        scanner.close();
    }

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
                        exit\t\t\t<>
                        log\t\t\t<>
                        export\t\t\t<>
                        stock-price\t\t<>
                        new-stock\t\t<>
                        volume\t\t\t<>
                        buy\t\t\t<>
                        sell-stock\t\t<>
                        add-watch\t\t<>
                        del-watch\t\t<>
                        get-watch\t\t<>
                        get-portfolio\t\t<>
                        wallet\t\t\t<>
                        deposit\t\t\t<>
                        man\t\t\t<>
                        overwrite-watchlist\t<>
                        list-users\t\t<>
                        remove-user\t\t<>
                        list-stocks\t\t<>
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
}