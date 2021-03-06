package dashboard.component;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class DashboardText extends DashboardComponent {

    public static final int TYPE = 5;

    public enum Style { DEFAULT, ERROR, SUCCESS }

    private final String text;
    private Style style = Style.DEFAULT;

    public DashboardText(String text) {
        super(TYPE);
        this.text = text;
    }

    public DashboardText(JSONObject json) {
        super(json);
        text = json.getString("text");
        style = Style.values()[json.getInt("style")];
    }

    public String getText() {
        return text;
    }

    public Style getStyle() {
        return style;
    }

    public DashboardText setStyle(Style style) {
        this.style = style;
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("text", text);
        json.put("style", style.ordinal());
        return json;
    }

}
