package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardNumberField extends DashboardTextFieldAbstract<Long> {

    public static final int TYPE = 12;

    public DashboardNumberField(String label, int min, int max, DashboardEventListener<Long> actionListener) {
        super(TYPE, label, min, max);
        setActionListener(actionListener);
    }

    public DashboardNumberField(String label, int min, int max) {
        super(TYPE, label, min, max);
    }

    public DashboardNumberField(JSONObject json) {
        super(json);
    }

}
