package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardDurationField extends DashboardTextFieldAbstract<Long> {

    public static final int TYPE = 15;
    private long value = 0L;

    public DashboardDurationField(String label, DashboardEventListener<Long> actionListener) {
        super(TYPE, label, 0, Integer.MAX_VALUE);
        setActionListener(actionListener);
    }

    public DashboardDurationField(String label) {
        super(TYPE, label,0, Integer.MAX_VALUE);
    }

    public DashboardDurationField(JSONObject json) {
        super(json);
        value = json.getLong("value");
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("value", value);
        return json;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

}
