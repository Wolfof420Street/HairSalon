import java.time.LocalDateTime;
import java.util.List;
import org.sql2o.*;

public class Client {
    private String Description;
    private LocalDateTime CreatedAt;
    private int Id;

    public Client(String description) {
        this.Description = description;
        CreatedAt = LocalDateTime.now();
           }

    public String getDescription() {
        return Description;
    }

    public LocalDateTime getCreatedAt() {

        return CreatedAt;
    }

    public int getId() {
        return Id;
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
        String sql = "SELECT id, description FROM clients";
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
                    this.getId() == newClient.getId();
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO clients (description) VALUES (:description)";
            con.createQuery(sql)
                    .addParameter("description", this.Description)
                    .executeUpdate()
                    .getKey();
        }
    }
    }
