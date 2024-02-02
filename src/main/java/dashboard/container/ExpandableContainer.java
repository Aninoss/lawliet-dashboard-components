package dashboard.container;

import org.json.JSONObject;

public class ExpandableContainer extends DashboardContainer {

    public static final int TYPE = 18;

    public ExpandableContainer(DashboardContainer header, DashboardContainer expandedBody) {
        super(TYPE, header, expandedBody);
    }

    public ExpandableContainer(JSONObject json) {
        super(json);
    }

}
