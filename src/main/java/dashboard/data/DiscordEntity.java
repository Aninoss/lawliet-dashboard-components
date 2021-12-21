package dashboard.data;

import java.util.Objects;
import org.json.JSONObject;

public class DiscordEntity {

    private final String id;
    private final String name;

    public DiscordEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJson() {
        JSONObject entityJson = new JSONObject();
        entityJson.put("id", id);
        entityJson.put("name", name);
        return entityJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscordEntity)) return false;
        DiscordEntity that = (DiscordEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static DiscordEntity fromJson(JSONObject jsonObject) {
        return new DiscordEntity(jsonObject.getString("id"), jsonObject.getString("name"));
    }

}
