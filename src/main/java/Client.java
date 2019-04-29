import java.time.LocalDateTime;
import java.util.List;
import org.sql2o.*;

public class Client {
    private String Description;
    private LocalDateTime CreatedAt;
    private int id;
    private int stylistId;

    public Client(String description, int stylistId) {
        this.Description = description;
        CreatedAt = LocalDateTime.now();
        this.stylistId = stylistId;
           }
    public void update(String description) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE clients SET description = :description WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public String getDescription() {
        return Description;
    }

    public LocalDateTime getCreatedAt() {

        return CreatedAt;
    }

    public int getId() {
        return id;
    }
    public int getStylistId() {
        return  stylistId;
    }
    public static Client find (int id){
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM clients where id=:id";
            Client client = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
            return client;
        }
    }
    public static List<Client> all() {
        String sql = "SELECT id, description, stylistID FROM clients";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Client.class);
        }
    }
    @Override
    public boolean equals(Object otherClient) {
        if (!(otherClient instanceof Client)) {
            return false;
        } else {
            Client newClient = (Client) otherClient;
            return this.getDescription().equals(newClient.getDescription()) &&
                    this.getId() == newClient.getId() &&
                    this.getStylistId() == newClient.getStylistId();
        }

    }
    public void delete() {
        try(Connection con=DB. sql2o.open()){
            String sql = "DELETE FROM clients WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO clients (description, stylistId) VALUES (:description, :stylistId)";
            this.id= (int)
            con.createQuery(sql, true)
                    .addParameter("description", this.Description)
                    .addParameter("stylistId", this.stylistId)
                    .executeUpdate()
                    .getKey();
        }

    }
    }
