package dashboard.component;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class DashboardText extends DashboardComponent {

    public static final int TYPE = 5;

    private final String text;

    public DashboardText(String text) {
        super(TYPE);
        this.text = text;
    }

    public DashboardText(JSONObject json) {
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
