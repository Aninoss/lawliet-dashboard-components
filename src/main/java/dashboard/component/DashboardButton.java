package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardButton extends ActionComponent<Void> {

    public static final int TYPE = 2;

    public enum Style { PRIMARY, DEFAULT, DANGER }

    private final String text;
    private Style style = Style.DEFAULT;

    public DashboardButton(String text, DashboardEventListener<Void> actionListener) {
        this(text);
        setActionListener(actionListener);
    }

    public DashboardButton(String text) {
        super(TYPE);
        this.text = text;
    }

    public DashboardButton(JSONObject json) {
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

    public void setStyle(Style style) {
        this.style = style;
    }

    public void trigger() {
        trigger(null);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("text", text);
        json.put("style", style.ordinal());
        return json;
    }

}
