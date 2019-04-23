import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Client {
    private String mDescription;
    private LocalDateTime mCreatedAt;
    private static List<Client> instances = new ArrayList<Client>();
    private int mId;

    public Client(String description) {
        mDescription = description;
        mCreatedAt = LocalDateTime.now();
        instances.add(this);
        mId = instances.size();
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

    public static void clear() {
        instances.clear();
    }
    public int getId() {
        return mId;
    }
}
