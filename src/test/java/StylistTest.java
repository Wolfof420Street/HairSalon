import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {
    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String deleteClientsQuery = "DELETE FROM clients *;";
            String deleteStylistsQuery = "DELETE FROM stylists *;";
            con.createQuery(deleteClientsQuery).executeUpdate();
            con.createQuery(deleteStylistsQuery).executeUpdate();
        }
    }

    @Test
    public void stylist_instantiatesCorrectly_true() {
        Stylist testStylist = new Stylist("Muigai");
        assertEquals(true, testStylist instanceof Stylist);
    }
    @Test
    public void getName_stylistInstantiatesWithName_Home() {
        Stylist testStylist = new Stylist("Muigai");
        assertEquals("Muigai", testStylist.getName());
    }
    @Test
    public void all_returnsAllInstancesOfStylist_true() {
        Stylist firstStylist = new Stylist("Djibril");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Trevor");
        secondStylist.save();
        assertEquals(true, Stylist.all().get(0).equals(firstStylist));
        assertEquals(true, Stylist.all().get(1).equals(secondStylist));
    }
    /*@Test
    public void clear_emptiesAllStylistFromList_0() {
        Stylist testStylist = new Stylist("Eric");
        Stylist.clear();
        assertEquals(Stylist.all().size(), 0);
    }

     */
    @Test
    public void getId_StylistsInstantiateWithAnId_1() {
        Stylist testStylist = new Stylist("Mike");
        testStylist.save();
        assertEquals(1, testStylist.getId());
    }

    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
        Stylist firstStylist = new Stylist("Dave");
        firstStylist.save();
        Stylist secondStylist = new Stylist ("Erikk");
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }
/*
    @Test
    public void getClients_initiallyReturnsEmptyList_ArrayList() {
        Stylist.clear();
        Stylist testStylist = new Stylist("Gang");
        assertEquals(0, testStylist.getClients().size());
    }
    /*
    @Test
    public void addClients_addsClientsToList_true() {
        Stylist testStylist = new Stylist("Wolf");
        Client testClient = new Client("Mister");
        testStylist.addClient(testClient);
        assertTrue(testStylist.getClients().contains(testClient));
    }
         */
    @Test
    public void equals_returnsTrueIfNamesAreTheSame() {
        Stylist firstStylist = new Stylist("Dave");
        Stylist secondStylist = new Stylist ("Dave");
        assertTrue(firstStylist.equals(secondStylist));
    }
    @Test
    public void save_savesIntoDatabase_true() {
        Stylist myStylist = new Stylist("Wolf");
        myStylist.save();
        assertTrue(Stylist.all().get(0).equals(myStylist));
    }
    @Test
    public void save_assignsIdToObject() {
        Stylist myStylist = new Stylist("Homebizo");
        myStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(myStylist.getId(), savedStylist.getId());
    }
    }
