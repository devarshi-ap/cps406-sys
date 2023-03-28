import java.util.Scanner;
import java.io.IOException;

public class MarketInterface {

    public static void main(String[] args) throws IOException {
        Market market = new Market();
        market.readStocks();

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

        boolean isAdmin = userInput.equals("0");
        boolean isUser = userInput.equals("1");

        // prompt for User details

        System.out.print("Enter name : ");
        String name = scanner.nextLine();

        System.out.print("Enter email : ");
        String email = scanner.nextLine();

        // create appropriate user object

        if (isAdmin) {
            admin = new Admin(name, email);
            System.out.println("You've chosen to play as Admin.");
            System.out.println("\n------+" + admin + "\n------+\n");

        } else {
            String initial_amount;
            do {
                System.out.print("Enter initial amount in wallet ($100,000,000 MAX) : ");
                initial_amount = scanner.nextLine();

                if (initial_amount.length() > 9 || Integer.valueOf(initial_amount) > 100_000_000)
                    System.out.println("Try again!");

            } while (initial_amount.length() > 9 || Integer.valueOf(initial_amount) > 100_000_000);

            // create investor + dummy admin
            investor = new Investor(name, email, Integer.valueOf(initial_amount));
            admin = new Admin("Bill gates", "bill@gmail.com");
            admin.newInvestor(investor);

            System.out.println("\n------+" + investor + "\n------+");
            System.out.println("\n------+" + admin + "\n------+\n");
        }

        // infinite prompt
        String cmd;
        do {
            System.out.print("> ");
            cmd = scanner.nextLine();

            // 🟢 (empty -> do nothing)
            if (cmd == null || cmd.equals("")) {
                System.out.println("");
            }

            // 🟢 manpage
            else if (cmd.equalsIgnoreCase("MAN")) {
                market.manpage();
            }

            // 🟢 log transactions (user only)
            else if (cmd.equalsIgnoreCase("LOG")) {
                System.out.println();
                if (isUser)
                    if (investor.getTransactions() == null || investor.getTransactions().isEmpty())
                        System.out.println("No Transactions Found.");
                    else {
                        for (String txn : investor.getTransactions())
                            System.out.println("-> " + txn);
                        System.out.println();
                    }
                        
                else
                    System.out.println("**USER ONLY**");
            }
            
            // 🟢 list all stocks
            else if (cmd.equalsIgnoreCase("LIST-STOCKS")) {
                System.out.println();
                for (Stock stock : market.getAllStocks().values())
                    System.out.println(stock);
            }
            
            // 🟢 add new stock (admin only)
            else if (cmd.equalsIgnoreCase("NEW-STOCK")) {
                if (isAdmin) {
                    System.out.print("Enter details for new Stock.\nName : ");
                    String stock_name = scanner.nextLine();

                    System.out.print("Enter details for new Stock.\nSTS : ");
                    String sts = scanner.nextLine();

                    System.out.print("Market Price : ");
                    int market_price = scanner.nextInt();
                    
                    System.out.print("Open Price : ");
                    int open_price = scanner.nextInt();

                    System.out.print("Closing Price : ");
                    int close_price = scanner.nextInt();

                    System.out.print("High Price : ");
                    int high_price = scanner.nextInt();

                    System.out.print("Low Price : ");
                    int low_price = scanner.nextInt();

                    System.out.print("# Initial Floating Shares : ");
                    int initial_shares = scanner.nextInt();
                    
                    if (!Market.verifyStock(sts)) {
                        market.addStock(sts, new Stock(stock_name, sts, new int[] {market_price, open_price, close_price, high_price, low_price}, initial_shares));
                        System.out.println(sts + " successfully added!");
                    } else {
                        System.out.println("STS (" + sts + ") already exists.");
                    }
                } else
                    System.out.println("**ADMIN ONLY**");
            }

            // 🟢 buy stock (user only)
            else if (cmd.equalsIgnoreCase("BUY")) {
                if (isUser) {
                    System.out.print("Enter STS : ");
                    String sts = scanner.nextLine();
    
                    if (Market.verifyStock(sts)) {
                        System.out.print("# Shares : ");
                        int shares = scanner.nextInt();
    
                        investor.buy(sts.toUpperCase(), shares);
                    } else
                        System.out.println("STS (" + sts + ") not found.");
                } else
                    System.out.println("**USER ONLY**");
            }

            // 🟢 sell stock (user only)
            else if (cmd.equalsIgnoreCase("SELL")) {
                if (isUser) {
                    System.out.print("Enter STS : ");
                    String sts = scanner.nextLine().toUpperCase();
    
                    if (Market.verifyStock(sts)) {
                        if (investor.inPortfolio(sts)) {
                            System.out.print("# Shares : ");
                            int shares = scanner.nextInt();
                            
                            investor.sell(sts, shares);
                        } else
                            System.out.println("STS (" + sts + ") not in Portfolio.");
                    } else
                        System.out.println("STS (" + sts + ") not found.");
                } else
                    System.out.println("**USER ONLY**");
            }

            // 🟢 get amount in wallet (user only)
            else if (cmd.equalsIgnoreCase("WALLET")) {
                if (isUser)
                    System.out.println("\nWallet Balance :\t$ " + investor.getWallet());
                else
                    System.out.println("**USER ONLY**");
            }

            // 🟢 get prices of given stock
            else if (cmd.equalsIgnoreCase("GET-STOCK")) {
                System.out.print("Enter STS  : ");
                String sts = scanner.nextLine();
                System.out.println();

                if (Market.verifyStock(sts))
                    System.out.println(market.getStock(sts));
                else
                    System.out.println("STS (" + sts + ") not found.");
            }
            
            // 🟢 print watchlist (user-only)
            else if (cmd.equalsIgnoreCase("WATCHLIST")) {
                if (isUser) {
                    if (investor.getWatchlist().isEmpty()) {
                        System.out.println("No items in Watchlist.");
                    } else {
                        for (String item: investor.getWatchlist())
                            System.out.println("- " + item);
                    }
                    System.out.println();
                } else
                    System.out.println("**USER ONLY**");
            }

            // 🟢 print portfolio (user-only)
            else if (cmd.equalsIgnoreCase("PORTFOLIO")) {
                if (isUser) {
                    System.out.println("Portfolio --------------+");
                    for(String sts: investor.getPortfolio().keySet()) {
                        int shares = investor.getPortfolio().get(sts);
                        int dollar_value = shares * market.getStock(sts).market_price * shares;
                        System.out.println(sts + " :\t" + shares + "\t($ " + dollar_value + ")");
                    }
                    System.out.println();
                } else
                    System.out.println("**USER ONLY**");
            }

            // 🟢 add to watchlist (user-only)
            else if (cmd.equalsIgnoreCase("WATCH-ADD")) {
                if (isUser) {
                    System.out.print("Enter STS : ");
                    String sts = scanner.nextLine().toUpperCase();

                    if (Market.verifyStock(sts)) {
                        if (!investor.inWatchlist(sts)) {
                            investor.addToWatchlist(sts);
                            System.out.println(sts + " added to watchlist!");
                        } else {
                            System.out.println(sts + " already in Watchlist.");
                        }
                    } else
                        System.out.println("Invalid STS was entered");
                } else {
                    System.out.println("**USER ONLY**");
                }
            }

            // 🟢 remove from watchlist (user-only)
            else if (cmd.equalsIgnoreCase("WATCH-REM")) {
                if (isUser) {
                    System.out.print("Enter STS : ");
                    String sts = scanner.nextLine().toUpperCase();

                    if (Market.verifyStock(sts)) {
                        if (investor.inWatchlist(sts)) {
                            investor.removeFromWatchlist(sts);
                            System.out.println(sts + " removed from watchlist.\n");
                        } else
                            System.out.println(sts + " not in Watchlist.\n");
                    }
                    else
                        System.out.println("Invalid STS was entered\n");
                } else {
                    System.out.println("**USER ONLY**");
                }
            }

            // 🟢 overwrites (toggles) stock from watchlist of investor ID (admin-only)
            else if (cmd.equalsIgnoreCase("OVERWRITE-WATCHLIST")) {
                if (isAdmin) {
                    System.out.print("Enter Investor ID : ");
                    String id = scanner.nextLine();

                    if (admin.hasInvestor(Integer.valueOf(id))) {
                        System.out.print("Enter STS : ");
                        String sts = scanner.nextLine().toUpperCase();

                        if (Market.verifyStock(sts)) {
                            if (admin.getInvestor(Integer.valueOf(id)).inWatchlist(sts)) {
                                admin.getInvestor(Integer.valueOf(id)).removeFromWatchlist(sts);
                                System.out.println(sts + " removed from watchlist.\n");
                            } else{
                                admin.getInvestor(Integer.valueOf(id)).addToWatchlist(sts);
                                System.out.println(sts + " added to watchlist.\n");
                            }
                        }
                        else
                            System.out.println("Invalid STS was entered\n");
                    }
                    else{
                        System.out.println("Investor with ID: " + id + " not in database.\n");
                    }
                } else {
                    System.out.println("**ADMIN ONLY**");
                }
            }

            // 🟢 deposit funds into wallet (user only)
            else if (cmd.equalsIgnoreCase("DEPOSIT")) {
                if (isUser) {
                    System.out.print("Deposit amount : ");
                    String amount = scanner.nextLine();

                    if (amount.length() > 9 || Integer.valueOf(amount) > 100_000_000)
                        System.out.println("Deposit amount too large.");
                    else
                        investor.deposit(Integer.valueOf(amount));

                } else {
                    System.out.println("**USER ONLY**");
                }
            }

            // 🟢 export transactions to file (user only)
            else if (cmd.equalsIgnoreCase("EXPORT")) {
                if (isUser)
                    investor.exportTransactions();
                else
                    System.out.println("**INVESTOR ONLY**");
            }

            // 🟢 list all system users (admin only)
            else if (cmd.equalsIgnoreCase("list-users")) {
                if (isAdmin)
                    if (!admin.getAllInvestors().isEmpty()) {
                        for(int id: admin.getAllInvestors().keySet())
                            System.out.println(admin.getInvestor(id));
                        System.out.println();
                    } else
                        System.out.println("No Users found in system.");
                else
                    System.out.println("**ADMIN ONLY**");
            }

            // invalid commmand
            else {
                if(!cmd.equalsIgnoreCase("exit"))
                    System.out.println("Please enter a valid command. See command 'man' for the manual page.");
            }

            System.out.println();

        } while (!cmd.equalsIgnoreCase("exit"));

        scanner.close();
    }
}
