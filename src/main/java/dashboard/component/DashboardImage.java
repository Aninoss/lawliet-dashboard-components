package dashboard.component;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class DashboardImage extends DashboardComponent {

    public static final int TYPE = 7;

    private final String url;

    public DashboardImage(String url) {
        super(TYPE);
        this.url = url;
    }

    public DashboardImage(JSONObject json) {
        super(json);
        url = json.getString("url");
    }

    public String getUrl() {
        return url;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("url", url);
        return json;
    }

}
