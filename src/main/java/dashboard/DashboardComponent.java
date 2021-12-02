package dashboard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import dashboard.component.*;
import dashboard.container.HorizontalPusher;
import dashboard.container.DashboardContainer;
import dashboard.container.HorizontalContainer;
import dashboard.container.VerticalContainer;
import org.json.JSONObject;
import util.RandomUtil;

public abstract class DashboardComponent {

    private final int type;
    private final String id;
    private boolean visible = true;
    private final HashMap<String, String> cssProperties = new HashMap<>();
    private DashboardContainer parent = null;

    public DashboardComponent(int type) {
        this.type = type;
        id = RandomUtil.generateRandomString(10);
    }

    public DashboardComponent(JSONObject json) {
        type = json.getInt("type");
        id = json.getString("id");
        visible = json.getBoolean("visible");
        JSONObject cssJson = json.getJSONObject("css");
        for (String key : cssJson.keySet()) {
            cssProperties.put(key, cssJson.getString(key));
        }
    }

    public int getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public DashboardContainer getParent() {
        return parent;
    }

    public void setParent(DashboardContainer parent) {
        this.parent = parent;
    }

    public void putCssProperty(String key, String value) {
        putCssProperties(key, value);
    }

    public void putCssProperties(String... s) {
        String key = null;
        for (int i = 0; i < s.length; i++) {
            if (i % 2 == 0) {
                key = s[i];
            } else {
                cssProperties.put(key, s[i]);
            }
        }
    }

    public Map<String, String> getCssProperties() {
        return Collections.unmodifiableMap(cssProperties);
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("id", id);
        json.put("visible", visible);
        JSONObject cssJson = new JSONObject();
        cssProperties.forEach(cssJson::put);
        json.put("css", cssJson);
        return json;
    }

    public static DashboardComponent generate(JSONObject json) {
        int type = json.getInt("type");
        return switch (type) {
            case HorizontalContainer.TYPE -> new HorizontalContainer(json);
            case VerticalContainer.TYPE -> new VerticalContainer(json);
            case DashboardButton.TYPE -> new DashboardButton(json);
            case DashboardSelectionMenu.TYPE -> new DashboardSelectionMenu(json);
            case DashboardSeparator.TYPE -> new DashboardSeparator(json);
            case DashboardText.TYPE -> new DashboardText(json);
            case HorizontalPusher.TYPE -> new HorizontalPusher(json);
            case DashboardImage.TYPE -> new DashboardImage(json);
            case DashboardImageUpload.TYPE -> new DashboardImageUpload(json);
            case DashboardSwitch.TYPE -> new DashboardSwitch(json);
            case DashboardTextFieldString.TYPE -> new DashboardTextFieldString(json);
            case DashboardTextFieldDouble.TYPE -> new DashboardTextFieldDouble(json);
            case DashboardTextFieldInt.TYPE -> new DashboardTextFieldInt(json);
            default -> null;
        };
    }

}
