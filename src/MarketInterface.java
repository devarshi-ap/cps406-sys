import java.io.IOException;
import java.util.Scanner;

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
            System.out.println();

            // 🟢 EMPTY (do nothing)
            if (cmd == null || cmd.equals("")) {
                System.out.println("");
            }

            // 🟢 manpage
            else if (cmd.equalsIgnoreCase("MAN")) {
                market.manpage();
            }

            // 🟡 log transactions (user only)
            else if (cmd.equalsIgnoreCase("LOG")) {
                if (isAdmin)
                    System.out.println("Cannot use this command in this context. (INVESTOR ONLY).");
                else
                    System.out.println(investor.getTransactions());
            }

            // 🟡 export transactions to file (user only)
            else if (cmd.equalsIgnoreCase("EXPORT")) {
                if (isAdmin)
                    System.out.println("Cannot use this command in this context. (INVESTOR ONLY).");
                else
                    investor.exportTransactions();
            }

            // 🟡 add new stock (admin only)
            else if (cmd.equalsIgnoreCase("NEW-STOCK")) {
                if (isAdmin) {
                    //
                } else {
                    System.out.println("Cannot use this command in this context. (ADMIN ONLY).");
                }
            }

            // 🟡 get prices of given stock
            else if (cmd.equalsIgnoreCase("STOCK-PRICE")) {
                System.out.print("Enter STS  : ");
                String sts = scanner.nextLine();

                if (Market.verifyStock(sts))
                    System.out.print(market.getAllStocks().get(cmd));
                else
                    System.out.println("STS (" + sts + ") not found.");
            }

            // 🟡 list all stocks
            else if (cmd.equalsIgnoreCase("LIST-STOCKS")) {
                for (Stock stock : market.getAllStocks().values())
                    System.out.println(stock);
            }
            
            // 🟡 get stock volume
            else if (cmd.equalsIgnoreCase("VOLUME")) {
                System.out.print("Please enter the Stock Ticker Symbol (STS) of your desired stock. \n> ");
                String sts = scanner.nextLine();

                if (Market.verifyStock(sts)) {
                    Stock stock = market.getAllStocks().get(sts);
                    System.out.println(sts + " Volume : " + stock.getVolume() + "\n");
                } else {
                    System.out.println("STS (" + sts + ") not found.");
                }
            }
            
            // 🟡 get amount in wallet (user only)
            else if (cmd.equalsIgnoreCase("WALLET")) {
                if (isAdmin)
                    System.out.println("Cannot use this command in this context. (INVESTOR ONLY).");
                else
                    System.out.println("Your current balance is : \t $" + investor.getWallet());
            } 
            
            // 🟡 deposit funds into wallet (user only)
            else if (cmd.equalsIgnoreCase("DEPOSIT")) {
                if (isAdmin) {
                    System.out.println("Cannot use this command in this context. (INVESTOR ONLY).");
                } else {
                    System.out.println("Please enter the amount that you would like to deposit. \n> ");
                    cmd = scanner.nextLine();

                    investor.deposit(Integer.valueOf(cmd));
                }
            } else {
                System.out.println("Please enter a valid command. See command 'man' for the manual page.");
            }

        } while (!cmd.equals("exit"));

        scanner.close();
    }
}
