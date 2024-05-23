package dashboard.data;

import org.json.JSONObject;

import java.util.Objects;

public class DiscordEntity {

    private final String id;
    private final String name;
    private final String iconUrl;

    public DiscordEntity(String id, String name) {
        this.id = id;
        this.name = name;
        this.iconUrl = null;
    }

    public DiscordEntity(String id, String name, String iconUrl) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public JSONObject toJson() {
        JSONObject entityJson = new JSONObject();
        entityJson.put("id", id);
        entityJson.put("name", name);
        entityJson.put("icon_url", iconUrl);
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
        return new DiscordEntity(
                jsonObject.getString("id"),
                jsonObject.getString("name"),
                jsonObject.has("icon_url") ? jsonObject.getString("icon_url") : null
        );
    }

}
