import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;


public class AdminTest {
    @Test
    public void testToString() {
        Admin admin = new Admin("John Doe", "johndoe@example.com");
        String expected = "\nID - 0\nName - John Doe\nEmail - johndoe@example.com\nClients - 4";
        assertEquals(expected, admin.toString());
    }
    public void testHasInvestor() {
        Admin admin = new Admin("John", "john@example.com");
        Investor investor = new Investor("Bob", "bob@example.com", 50000);
        admin.newInvestor(investor);

        // Ensure that the admin has the investor by ID
        assertTrue(admin.hasInvestor(investor.getId()));

        // Ensure that the admin does not have an invalid ID
        assertFalse(admin.hasInvestor(-1));
    }
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

    @Test
    public void testGetAllInvestors() {
        HashMap<Integer, Investor> expectedInvestors = new HashMap<>();
        expectedInvestors.put(1, new Investor("Steve Carell", "steve@gmail.com", 100_000));
        expectedInvestors.put(2, new Investor("Christian Bale", "cbale@yahoo.com", 200_000));
        expectedInvestors.put(3, new Investor("Ryan Gosling", "ryan@hotmail.com", 300_000));
        expectedInvestors.put(4, new Investor("Brad Pitt", "brad@gmail.com", 400_000));
        expectedInvestors.put(5, new Investor("Alice", "alice@example.com", 1000));
        expectedInvestors.put(6, new Investor("Bob", "bob@example.com", 2000));

        assertEquals(expectedInvestors, admin.getAllInvestors());
    }
}
