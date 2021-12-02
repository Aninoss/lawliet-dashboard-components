package dashboard.component;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DashboardTextField<T> extends ActionComponent<T> {

    private final static Logger LOGGER = LoggerFactory.getLogger(DashboardTextField.class);

    private final String label;
    private final int min;
    private final int max;
    private T value = null;
    private String placeholder = "";
    private boolean multiLine = false;

    public DashboardTextField(int type, String label, int min, int max) {
        super(type);
        this.label = label;
        this.min = min;
        this.max = max;
    }

    public DashboardTextField(JSONObject json) {
        super(json);
        label = json.getString("label");
        min = json.getInt("min");
        max = json.getInt("max");
        if (json.has("value") && !json.isNull("value")) {
            try {
                value = (T) json.get("value");
            } catch (ClassCastException e) {
                LOGGER.error("Invalid data type of received dashboard action or placeholder", e);
            }
        }
        placeholder = json.getString("placeholder");
        multiLine = json.getBoolean("multi_line");
    }

    public String getLabel() {
        return label;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public boolean isMultiLine() {
        return multiLine;
    }

    public void setMultiLine(boolean multiLine) {
        this.multiLine = multiLine;
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
        if (value != null) {
            json.put("value", value);
        }
        json.put("placeholder", placeholder);
        json.put("multi_line", multiLine);
        return json;
    }

}
