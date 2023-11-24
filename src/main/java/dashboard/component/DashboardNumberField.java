package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardNumberField extends DashboardTextFieldAbstract<Long, Long> {

    public static final int TYPE = 12;
    private long value = 0L;

    public DashboardNumberField(String label, long min, long max, DashboardEventListener<Long> actionListener) {
        super(TYPE, label, min, max);
        setActionListener(actionListener);
    }

    public DashboardNumberField(String label, long min, long max) {
        super(TYPE, label, min, max);
    }

    public DashboardNumberField(JSONObject json) {
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
