import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testToString() {
        User user = new User("John", "john@example.com");
        String expected = "\nID - " + user.getId() + "\nName - John\nEmail - john@example.com\n";
        String actual = user.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetId() {
        User user = new User("John", "john@example.com");
        int expected = Market.generateID() - 1;
        int actual = user.getId();
        assertEquals(expected, actual);
    }
}
