package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardTextFieldDouble extends DashboardTextField<Double> {

    public static final int TYPE = 11;

    public DashboardTextFieldDouble(String label, int min, int max, DashboardEventListener<Double> actionListener) {
        super(TYPE, label, min, max);
        setActionListener(actionListener);
    }

    public DashboardTextFieldDouble(String label, int min, int max) {
        super(TYPE, label, min, max);
    }

    public DashboardTextFieldDouble(JSONObject json) {
        super(json);
    }

}
