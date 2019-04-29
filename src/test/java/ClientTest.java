import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class ClientTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Test
    public void Client_instantiatesCorrectly_true() {
        Client myClient = new Client("Drew", 1);
        assertEquals(true, myClient instanceof Client);
    }

    @Test
    public void Client_instantiatesWithDescription_String() {
        Client myClient = new Client("Drew Stephens", 1);
        assertEquals("Drew Stephens", myClient.getDescription());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() {
        Client myClient = new Client("ken Haj", 1);
        assertEquals(LocalDateTime.now().getDayOfWeek(), myClient.getCreatedAt().getDayOfWeek());
    }
    @Test
    public void all_returnsAllInstancesOfClient_true() {
        Client firstClient = new Client("Bill Odida", 1);
        firstClient.save();
        Client secondClient = new Client("Brian Marete", 1);
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(0).equals(secondClient));
    }
    /*
    @Test
    public void clear_emptiesAllClientssFromArrayList_0() {
        Client myClient = new Client("lawrence Karanja");
        Client.clear();
        assertEquals(Client.all().size(), 0);
    }
    */
    @Test
    public void getId_clientsInstantiateWithAnID_1() {
        Client myClient = new Client("Tom Orenge", 1);
        myClient.save();
        assertEquals(1, myClient.getId()>0 );
    }


    @Test
    public void equals_returnsTrueifDescriptionsAreTheSame(){
        Client firstClient = new Client ("Erick", 1);
        Client secondClient = new Client ("Erick", 1);
        assertTrue(firstClient.equals(secondClient));
    }
    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
        Client myClient = new Client("Erick", 1);
        myClient.save();
        assertTrue(Client.all().get(0).equals(myClient));
    }
    @Test
    public void save_assignsIdToObject() {
        Client myClient = new Client("Wolf", 1);
        myClient.save();
        Client savedClient = Client.all().get(0);
        assertEquals(myClient.getId(), savedClient.getId());
    }
    @Test
    public void find_returnsTaskWithSameId_secondTask() {
        Client firstClient = new Client("Wolf", 1);
        firstClient.save();
        Client secondClient = new Client ("Gerrit", 1);
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
    }
    @Test
    public void update_updatesClientDescription_true() {
        Client myClient = new Client("Tom Orenge", 1);
        myClient.save();
        myClient.update("Wolf");
        assertEquals("Wolf", myClient.find(myClient.getId()).getDescription());
    }
    @Test
    public void delete_deletesClient_true() {
        Client myClient = new Client("Mow the lawn", 1);
        myClient.save();
        int myClientId = myClient.getId();
        myClient.delete();
        assertEquals(null, Client.find(myClientId));
    }
}