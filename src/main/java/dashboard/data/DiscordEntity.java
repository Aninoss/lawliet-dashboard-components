package dashboard.data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscordEntity)) return false;
        DiscordEntity that = (DiscordEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
