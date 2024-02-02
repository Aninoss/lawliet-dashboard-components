package dashboard.component;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class DashboardText extends DashboardComponent {

    public static final int TYPE = 5;

    public enum Style { DEFAULT, ERROR, SUCCESS, WARNING, SECONDARY, BOLD }

    private final String text;
    private Style style = Style.DEFAULT;
    private String url;

    public DashboardText(String text) {
        super(TYPE);
        this.text = text;
    }

    public DashboardText(JSONObject json) {
        super(json);
        text = json.getString("text");
        style = Style.values()[json.getInt("style")];
        if (json.has("url")) {
            url = json.getString("url");
        }
    }

    public String getText() {
        return text;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("text", text);
        json.put("style", style.ordinal());
        json.put("url", url);
        return json;
    }

}
