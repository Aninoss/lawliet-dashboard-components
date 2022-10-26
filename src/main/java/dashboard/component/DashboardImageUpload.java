package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardImageUpload extends ActionComponent<String> {

    public static final int TYPE = 8;

    private final String label;
    private final String dir;

    public DashboardImageUpload(String label, String dir, DashboardEventListener<String> actionListener) {
        this(label, dir);
        setActionListener(actionListener);
    }

    public DashboardImageUpload(String label, String dir) {
        super(TYPE);
        this.label = label;
        this.dir = dir;
    }

    public DashboardImageUpload(JSONObject json) {
        super(json);
        label = json.getString("label");
        if (json.has("dir")) {
            dir = json.getString("dir");
        } else {
            dir = "temp";
        }
    }

    public String getLabel() {
        return label;
    }

    public String getDir() {
        return dir;
    }

    @Override
    public void trigger(String filename) {
        super.trigger(filename);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        json.put("dir", dir);
        return json;
    }

}
