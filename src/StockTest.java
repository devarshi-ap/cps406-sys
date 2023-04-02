import org.junit.Test;
import static org.junit.Assert.*;

public class StockTest {
    @Test
    public void testToString() {
        int[] prices = { 100, 110, 105, 120, 95 };
        Stock stock = new Stock("AAPL", "NASDAQ", prices, 1000);
        String expected = "------+ AAPL (NASDAQ)\nMarket Price: \t$100\nOpening Price: \t$110\nClosing Price: \t$105\nHigh Price: \t$120\nLow Price: \t$95\n";
        String actual = stock.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSTS() {
        Stock stock = new Stock("Apple Inc.", "AAPL", new int[] { 141, 143, 142, 144, 140 }, 100_000_000);
        assertEquals("AAPL", stock.getSTS());
    }

    @Test
    public void testGetPrices() {
        int[] prices = { 100, 95, 102, 105, 90 };
        Stock stock = new Stock("Nike Inc.", "NKE", prices, 10000);

        assertArrayEquals(prices, stock.getPrices());
    }

    @Test
    public void testGetPricesEmpty() {
        int[] prices = new int[5];
        Stock stock = new Stock("Amazon", "AMZN", prices, 10000);

        assertArrayEquals(prices, stock.getPrices());
    }

    @Test
    public void testGetPricesNegative() {
        int[] prices = { 100, 200, 300, 400, 500 };
        Stock stock = new Stock("Alphabet Inc.", "GOOG", prices, 10000);

        assertArrayEquals(prices, stock.getPrices());
    }
}