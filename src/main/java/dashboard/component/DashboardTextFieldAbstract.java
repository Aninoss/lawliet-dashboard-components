package dashboard.component;

import org.json.JSONObject;

public abstract class DashboardTextFieldAbstract<T, U> extends ActionComponent<T> {

    private final String label;
    private final U min;
    private final U max;
    private String placeholder = "";
    private boolean editButton = true;

    public DashboardTextFieldAbstract(int type, String label, U min, U max) {
        super(type);
        this.label = label;
        this.min = min;
        this.max = max;
    }

    public DashboardTextFieldAbstract(JSONObject json) {
        super(json);
        label = json.getString("label");
        min = (U) json.get("min");
        max = (U) json.get("max");
        placeholder = json.getString("placeholder");
        editButton = json.getBoolean("edit_button");
    }

    public String getLabel() {
        return label;
    }

    public U getMin() {
        return min;
    }

    public U getMax() {
        return max;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public boolean getEditButton() {
        return editButton;
    }

    public void setEditButton(boolean editButton) {
        this.editButton = editButton;
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
        json.put("edit_button", editButton);
        return json;
    }

}
