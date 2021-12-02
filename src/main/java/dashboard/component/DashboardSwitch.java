package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardSwitch extends ActionComponent<Boolean> {

    public static final int TYPE = 9;

    private final String label;
    private boolean checked = false;
    private String subtitle = null;

    public DashboardSwitch(String label, DashboardEventListener<Boolean> actionListener) {
        this(label);
        setActionListener(actionListener);
    }

    public DashboardSwitch(String label) {
        super(TYPE);
        this.label = label;
    }

    public DashboardSwitch(JSONObject json) {
        super(json);
        label = json.getString("label");
        checked = json.getBoolean("checked");
        if (json.has("subtitle") && !json.isNull("subtitle")) {
            subtitle = json.getString("subtitle");
        }
    }

    public String getLabel() {
        return label;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public void trigger(Boolean activated) {
        super.trigger(activated);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        json.put("checked", checked);
        if (subtitle != null) {
            json.put("subtitle", subtitle);
        }
        return json;
    }

}
