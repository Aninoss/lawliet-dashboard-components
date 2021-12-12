package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardImageUpload extends ActionComponent<String> {

    public static final int TYPE = 8;

    private final String label;

    public DashboardImageUpload(String label, DashboardEventListener<String> actionListener) {
        this(label);
        setActionListener(actionListener);
    }

    public DashboardImageUpload(String label) {
        super(TYPE);
        this.label = label;
    }

    public DashboardImageUpload(JSONObject json) {
        super(json);
        label = json.getString("label");
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void trigger(String filename) {
        super.trigger(filename);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        return json;
    }

}
