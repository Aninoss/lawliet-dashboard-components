package dashboard.container;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class HorizontalPusher extends DashboardComponent {

    public static final int TYPE = 6;

    public HorizontalPusher() {
        super(TYPE);
    }

    public HorizontalPusher(JSONObject json) {
        super(json);
    }

}
