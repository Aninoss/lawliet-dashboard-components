package dashboard.container;

import dashboard.DashboardComponent;
import org.json.JSONObject;

public class HorizontalContainer extends DashboardContainer {

    public static final int TYPE = 0;

    public enum Alignment { TOP, CENTER, BOTTOM }

    private Alignment alignment = Alignment.TOP;
    private boolean allowWrap = false;

    public HorizontalContainer(DashboardComponent... children) {
        super(TYPE, children);
    }

    public HorizontalContainer(JSONObject json) {
        super(json);
        alignment = Alignment.values()[json.getInt("alignment")];
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public boolean getAllowWrap() {
        return allowWrap;
    }

    public void setAllowWrap(boolean allowWrap) {
        this.allowWrap = allowWrap;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("alignment", alignment.ordinal());
        return json;
    }

}
