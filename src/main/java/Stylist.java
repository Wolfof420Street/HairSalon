import java.util.List;
import org.sql2o.*;



public class Stylist {
    private String Name;
    private int id;



    public Stylist(String name) {
        this.Name = name;
            }

    public String getName()
    {
        return Name;
    }

    public static List<Stylist> all()
    {
        String sql = "SELECT id, name FROM stylists";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }


    public int getId()
    {
        return id;
    }

    public static Stylist find(int id) {

        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM stylists where id=:id";
            Stylist stylist = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Stylist.class);
            return stylist;
        }
    }

    public  List<Client> getClients()
    {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where stylistId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Client.class);
        }

    }
    @Override
    public boolean equals(Object otherStylist) {
        if (!(otherStylist instanceof Stylist)) {
            return false;
        } else {
            Stylist newStylist = (Stylist) otherStylist;
            return this.getName().equals(newStylist.getName())&&
                    this.getId() == newStylist.getId();
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO stylists (name ) VALUES (:name )";
            con.createQuery(sql, true)
                    .addParameter("name", this.Name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM stylists WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }
    }