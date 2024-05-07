package dashboard.component;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class DashboardSeparator extends DashboardComponent {

    public static final int TYPE = 4;

    private boolean largeGap = false;

    public DashboardSeparator() {
        super(TYPE);
    }

    public DashboardSeparator(boolean largeGap) {
        super(TYPE);
        this.largeGap = largeGap;
    }

    public DashboardSeparator(JSONObject json) {
        super(json);
        if (json.has("large_gap")) {
            largeGap = json.getBoolean("large_gap");
        }
    }

    public boolean getLargeGap() {
        return largeGap;
    }

    public void setLargeGap(boolean largeGap) {
        this.largeGap = largeGap;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("large_gap", largeGap);
        return json;
    }

}
