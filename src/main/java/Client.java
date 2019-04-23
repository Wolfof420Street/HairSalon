import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Client {
    private String mDescription;
    private LocalDateTime mCreatedAt;
    private static List<Client> instances = new ArrayList<Client>();

    public Client(String description) {
        mDescription = description;
        mCreatedAt = LocalDateTime.now();
        instances.add(this);
    }

    public String getDescription() {
        return mDescription;
    }

    public LocalDateTime getCreatedAt() {
        return mCreatedAt;
    }

    public static List<Client> all() {
        return instances;
    }
}
