package dashboard.component;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class DashboardTitle extends DashboardComponent {

    public static final int TYPE = 13;

    private final String text;

    public DashboardTitle(String text) {
        super(TYPE);
        this.text = text;
    }

    public DashboardTitle(JSONObject json) {
        super(json);
        text = json.getString("text");
    }

    public String getText() {
        return text;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("text", text);
        return json;
    }

}
