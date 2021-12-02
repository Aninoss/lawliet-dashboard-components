package dashboard.component;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class DashboardSeparator extends DashboardComponent {

    public static final int TYPE = 4;

    public DashboardSeparator() {
        super(TYPE);
    }

    public DashboardSeparator(JSONObject json) {
        super(json);
    }

}
