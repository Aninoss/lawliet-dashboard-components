package dashboard.component;

import org.json.JSONObject;

public abstract class DashboardEmojiField extends ActionComponent<String> {

    private final String label;

    public DashboardEmojiField(int type, String label) {
        super(type);
        this.label = label;
    }

    public DashboardEmojiField(JSONObject json) {
        super(json);
        label = json.getString("label");
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void trigger(String emoji) {
        super.trigger(emoji);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        return json;
    }

}
