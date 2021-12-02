package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardTextFieldString extends DashboardTextField<String> {

    public static final int TYPE = 10;

    public DashboardTextFieldString(String label, int min, int max, DashboardEventListener<String> actionListener) {
        super(TYPE, label, min, max);
        setActionListener(actionListener);
    }

    public DashboardTextFieldString(String label, int min, int max) {
        super(TYPE, label, min, max);
    }

    public DashboardTextFieldString(JSONObject json) {
        super(json);
    }

}
