package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardMultiLineTextField extends DashboardTextFieldAbstract<String> {

    public static final int TYPE = 11;

    public DashboardMultiLineTextField(String label, int min, int max, DashboardEventListener<String> actionListener) {
        super(TYPE, label, min, max);
        setActionListener(actionListener);
    }

    public DashboardMultiLineTextField(String label, int min, int max) {
        super(TYPE, label, min, max);
    }

    public DashboardMultiLineTextField(JSONObject json) {
        super(json);
    }

}
