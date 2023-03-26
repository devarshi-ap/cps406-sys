package main;
import java.util.HashMap;
import java.util.Scanner;

public class MarketInterface {


    public static void main(String[] args) {
        // main program to run
        Market market = new Market();

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
                market.manpage();
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

}
