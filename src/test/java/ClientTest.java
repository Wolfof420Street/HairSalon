import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class ClientTest {
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    }
    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM clients *;";
            con.createQuery(sql).executeUpdate();
        }
    }
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
        firstClient.save();
        Client secondClient = new Client("Brian Marete");
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
    public void getId_clentsInstantiateWithAnID_1() {
        Client myClient = new Client("Tom Orenge");
        myClient.save();
        assertEquals(1, myClient.getId()>0 );
    }


    @Test
    public void equals_returnsTrueifDescriptionsAreTheSame(){
        Client firstClient = new Client ("Erick");
        Client secondClient = new Client ("Erick");
        assertTrue(firstClient.equals(secondClient));
    }
    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
        Client myClient = new Client("Erick");
        myClient.save();
        assertTrue(Client.all().get(0).equals(myClient));
    }
    @Test
    public void save_assignsIdToObject() {
        Client myClient = new Client("Wolf");
        myClient.save();
        Client savedClient = Client.all().get(0);
        assertEquals(myClient.getId(), savedClient.getId());
    }
    @Test
    public void find_returnsTaskWithSameId_secondTask() {
        Client firstClient = new Client("Wolf");
        firstClient.save();
        Client secondClient = new Client ("Gerrit");
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
    }
}