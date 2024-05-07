package dashboard.container;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class DashboardListContainer extends DashboardContainer {

    public static final int TYPE = 19;

    public DashboardListContainer(DashboardComponent... children) {
        super(TYPE, children);
    }

    public DashboardListContainer(JSONObject json) {
        super(json);
    }

}
