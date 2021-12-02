package dashboard;

public class DashboardEvent<T> {

    private final String type;
    private final T data;

    public DashboardEvent(String type, T data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }

}
