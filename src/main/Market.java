package main;

import java.util.HashMap;
import java.util.Scanner;

public class Market {
    public static HashMap<String, Stock> stocks = new HashMap<>();
    private static int idCounter = 0;

    public static void main(String[] args) {
        // main program to run
        Scanner scanner = new Scanner(System.in);

        Investor investor = new Investor(true);
        Admin admin;

        // prompt to run as Admin or Investor
        String userInput;
        do {
            System.out.print("Run program as Admin (0) || Investor (1) : ");
            userInput = scanner.nextLine();
            if (!userInput.equals("0") && !userInput.equals("1"))
                System.out.println("Try again!");
        } while (!userInput.equals("0") && !userInput.equals("1"));

        

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
            System.out.println("\n------+" + admin + "\n------+\n");
        } else {
            // INVESTOR
            String initial_amount;
            do {
                System.out.print("Enter initial amount in wallet ($100,000,000 MAX) : ");
                initial_amount = scanner.nextLine();
                
                if (initial_amount.length() > 9 || Integer.valueOf(initial_amount) > 100_000_000)
                    System.out.println("Try again!");

            } while (initial_amount.length() > 9 || Integer.valueOf(initial_amount) > 100_000_000);

            investor = new Investor(name, email, Integer.valueOf(initial_amount));
            admin = new Admin("Bill gates", "bill@gmail.com"); // default dummy admin
            admin.newInvestor(investor);

            System.out.println("\n------+" + investor + "\n------+");
            System.out.println("\n------+" + admin + "\n------+\n");
        }

        // infinite prompt
        String cmd;
        do {
            System.out.print("> ");
            cmd = scanner.nextLine();

            if (cmd == null || cmd.equals("")) 
			{
				System.out.println("Please enter a valid command. See 'manpage' for more info.");
            }
            else if (cmd.equalsIgnoreCase("MAN"))
			{
                //manpage();
            }
            else if (cmd.equalsIgnoreCase("LOG")) 
			{
                if (userInput.equals("0")){
                    System.out.println("Cannot use this command in this context. (INVESTOR ONLY).");
                }
                else 
                {
                    System.out.println(investor.getTransactions());
                }
            }
            else if (cmd == null || cmd.equals("")) 
			{
			
            }
            
        } while (!cmd.equals("exit"));

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
}