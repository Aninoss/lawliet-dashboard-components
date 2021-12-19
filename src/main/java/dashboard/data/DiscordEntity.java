package dashboard.data;

public class DiscordEntity {

    private final long id;
    private final String name;

    public DiscordEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
