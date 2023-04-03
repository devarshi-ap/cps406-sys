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
        Investor investor = new Investor("Arib", "arib@example.com", 50000);
        investor.buy("AAPL", 10);

        assertTrue(investor.inPortfolio("AAPL"));
        assertEquals(50000 - 10 * Market.getStock("AAPL").market_price, investor.getWallet());
    }
    /*
     * DEPOSIT
     * BUY
     * SELL
     * EXPORT
     */
}