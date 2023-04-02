import org.junit.Test;
import static org.junit.Assert.*;

public class AdminTest {
    @Test
    public void testToString() {
        Admin admin = new Admin("John Doe", "johndoe@example.com");
        String expected = "\nID - 0\nName - John Doe\nEmail - johndoe@example.com\nClients - 4";
        assertEquals(expected, admin.toString());
    }
}
