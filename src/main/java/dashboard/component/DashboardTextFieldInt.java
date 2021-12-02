package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardTextFieldInt extends DashboardTextField<Integer> {

    public static final int TYPE = 12;

    public DashboardTextFieldInt(String label, int min, int max, DashboardEventListener<Integer> actionListener) {
        super(TYPE, label, min, max);
        setActionListener(actionListener);
    }

    public DashboardTextFieldInt(String label, int min, int max) {
        super(TYPE, label, min, max);
    }

    public DashboardTextFieldInt(JSONObject json) {
        super(json);
    }

}
