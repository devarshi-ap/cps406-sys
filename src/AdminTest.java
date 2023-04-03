import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AdminTest {
    @Test
    public void testToString() {
        Admin admin = new Admin("John Doe", "johndoe@example.com");
        String expected = "\nID - 20\nName - John Doe\nEmail - johndoe@example.com\nClients - 4";
        assertEquals(expected, admin.toString());
    }

    @Test
    public void testHasInvestor() {
        Admin admin = new Admin("John", "john@example.com");
        Investor investor = new Investor("Bob", "bob@example.com", 50000);
        admin.newInvestor(investor);

        // Ensure that the admin has the investor by ID
        assertTrue(admin.hasInvestor(investor.getId()));

        // Ensure that the admin does not have an invalid ID
        assertFalse(admin.hasInvestor(-1));
    }

    @Test
    public void testGetInvestor() {
        Admin admin = new Admin("John", "john@example.com");
        Investor investor = new Investor("Bob", "bob@example.com", 50000);
        admin.newInvestor(investor);

        // Ensure the investor can be retrieved by ID
        Investor retrievedInvestor = admin.getInvestor(investor.getId());
        assertNotNull(retrievedInvestor);
        assertEquals(investor, retrievedInvestor);

        // Ensure attempting to retrieve an invalid ID returns null
        Investor invalidInvestor = admin.getInvestor(-1);
        assertNull(invalidInvestor);
    }

    private Admin admin;

    @Before
    public void setUp() {
        admin = new Admin("John Doe", "john@example.com");
        admin.newInvestor("Alice", "alice@example.com", 1000);
        admin.newInvestor("Bob", "bob@example.com", 2000);
    }
}
