package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardTextField extends DashboardTextFieldAbstract<String, Integer> {

    public static final int TYPE = 10;
    private String value = "";

    public DashboardTextField(String label, int min, int max, DashboardEventListener<String> actionListener) {
        super(TYPE, label, min, max);
        setActionListener(actionListener);
    }

    public DashboardTextField(String label, int min, int max) {
        super(TYPE, label, min, max);
    }

    public DashboardTextField(JSONObject json) {
        super(json);
        value = json.getString("value");
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("value", value);
        return json;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
