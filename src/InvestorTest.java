import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class InvestorTest {

    @Test
    public void testGetTransactions() {
        Investor investor = new Investor("John", "john@example.com", 10000);
        ArrayList<String> transactions = new ArrayList<String>();
        transactions.add("BUY-AAPL-10");
        transactions.add("SELL-MSFT-20");
        investor.addTransaction("BUY-AAPL-10");
        investor.addTransaction("SELL-MSFT-20");
        assertEquals(transactions, investor.getTransactions());
    }

    @Test
    public void testGetWatchlist() {
        Investor investor = new Investor("John", "john@example.com", 10000);
        ArrayList<String> watchlist = new ArrayList<String>();
        watchlist.add("AAPL");
        watchlist.add("MSFT");
        investor.addToWatchlist("AAPL");
        investor.addToWatchlist("MSFT");
        assertEquals(watchlist, investor.getWatchlist());
    }

    @Test
    public void testToString() {
        Investor investor = new Investor("John", "john@example.com", 10000);
        assertEquals("\nID - 40\nName - John\nEmail - john@example.com\nFunds - 10000", investor.toString());
    }

    @Test
    public void testInWatchList() {
        // Test if a stock is in the watchlist
        Investor investor = new Investor("John", "john@example.com", 10000);
        investor.addToWatchlist("AAPL");
        assertTrue(investor.inWatchlist("AAPL"));

        // Test if a stock is not in the watchlist
        assertFalse(investor.inWatchlist("GOOGL"));
    }

    @Test
    public void testInPortfolio() {
        // Test if a stock is in the portfolio
        Investor investor = new Investor("John", "john@example.com", 10000);
        investor.addToPortfolio("AAPL", 10);
        assertTrue(investor.inPortfolio("AAPL"));

        // Test if a stock is not in the portfolio
        assertFalse(investor.inPortfolio("GOOGL"));
    }

    @Test
    public void testBuy() {
        Market market = new Market();
        market.addStock("AAPL", new Stock("Apple", "AAPL",new int[] {160, 159, 160, 160, 157}, 100000));
        
        int initialShares = Market.stocks.get("AAPL").floating_shares;

        Investor investor = new Investor("Arib", "arib@example.com", 50000);
        int sharesBought = 10;
        String sts = "AAPL";

        investor.buy(sts, sharesBought);
        //Illustrate the transaction using this test variable 
        String txn = "BUY-" + sts.toUpperCase() + "-" + sharesBought;

        assertTrue(investor.inPortfolio("AAPL"));
        assertEquals(txn, investor.getTransactions().get(0));
        assertEquals(50000 - 10 * market.getStock("AAPL").market_price, investor.getWallet());
        assertEquals(initialShares - sharesBought, Market.stocks.get("AAPL").floating_shares);
    }

    @Test 
    public void testSell() {
        Market market = new Market();
        market.addStock("TSLA", new Stock("Tesla", "TSLA",new int[] {190, 191, 192, 192, 187}, 100000));
        ArrayList<String> transactions = new ArrayList<String>();

        int initialShares = Market.stocks.get("TSLA").floating_shares;

        Investor investor = new Investor("Arib", "arib@example.com", 50000);
        int sharesBought = 20;
        String sts = "TSLA";

        //First buy a stock to later sell it
        investor.buy(sts, sharesBought);
        int walletAfterBuy = 50000 - 20 * market.getStock("TSLA").market_price;
        //Illustrate the transaction using this test variable 
        String txnBuy = "BUY-" + sts.toUpperCase() + "-" + sharesBought;
        transactions.add(txnBuy);

        //Now sell a stock
        int sharesSold = 15;
        investor.sell(sts, sharesSold);
        int moneyEarned = 15 * market.getStock("TSLA").market_price;
        //Illustrate the transaction using this test variable 
        String txnSell = "SELL-" + sts.toUpperCase() + "-" + sharesSold;
        transactions.add(txnSell);

        assertTrue(investor.inPortfolio("TSLA"));
        assertEquals(transactions.toString(), investor.getTransactions().toString());
        assertEquals(walletAfterBuy + moneyEarned, investor.getWallet());
        assertEquals((initialShares - sharesBought) + sharesSold, Market.stocks.get("TSLA").floating_shares);
    }

    @Test
    public void testDeposit() {
        Investor investor = new Investor("Arib", "arib@example.com", 50000);

        int originalFunds = investor.getWallet();
        int depositAmount = 10000;
        investor.deposit(depositAmount);

        assertEquals(originalFunds + depositAmount, investor.getWallet());
    }

    /*
     * DEPOSIT
     * BUY
     * SELL
     * EXPORT
     */
}