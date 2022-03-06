package dashboard.component;

import org.json.JSONObject;

public abstract class DashboardTextFieldAbstract<T> extends ActionComponent<T> {

    private final String label;
    private final long min;
    private final long max;
    private String placeholder = "";

    public DashboardTextFieldAbstract(int type, String label, long min, long max) {
        super(type);
        this.label = label;
        this.min = min;
        this.max = max;
    }

    public DashboardTextFieldAbstract(JSONObject json) {
        super(json);
        label = json.getString("label");
        min = json.getLong("min");
        max = json.getLong("max");
        placeholder = json.getString("placeholder");
    }

    public String getLabel() {
        return label;
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void trigger(T value) {
        super.trigger(value);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        json.put("min", min);
        json.put("max", max);
        json.put("placeholder", placeholder);
        return json;
    }

}
