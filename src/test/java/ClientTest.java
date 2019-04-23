import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class ClientTest {

    @Test
    public void Client_instantiatesCorrectly_true() {
        Client myClient = new Client("Drew");
        assertEquals(true, myClient instanceof Client);
    }

    @Test
    public void Client_instantiatesWithDescription_String() {
        Client myClient = new Client("Drew Stephens");
        assertEquals("Drew Stephens", myClient.getDescription());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() {
        Client myClient = new Client("ken Haj");
        assertEquals(LocalDateTime.now().getDayOfWeek(), myClient.getCreatedAt().getDayOfWeek());
    }
    @Test
    public void all_returnsAllInstancesOfClient_true() {
        Client firstClient = new Client("Bill Odida");
        Client secondClient = new Client("Brian Marete");
        assertEquals(true, Client.all().contains(firstClient));
        assertEquals(true, Client.all().contains(secondClient));
    }
}