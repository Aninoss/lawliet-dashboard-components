package dashboard.container;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class VerticalContainer extends DashboardContainer {

    public static final int TYPE = 1;

    public VerticalContainer(DashboardComponent... children) {
        super(TYPE, children);
    }

    public VerticalContainer(JSONObject json) {
        super(json);
    }

}
