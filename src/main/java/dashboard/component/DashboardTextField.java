package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardTextField extends DashboardTextFieldAbstract<String> {

    public static final int TYPE = 10;

    public DashboardTextField(String label, int min, int max, DashboardEventListener<String> actionListener) {
        super(TYPE, label, min, max);
        setActionListener(actionListener);
    }

    public DashboardTextField(String label, int min, int max) {
        super(TYPE, label, min, max);
    }

    public DashboardTextField(JSONObject json) {
        super(json);
    }

}
