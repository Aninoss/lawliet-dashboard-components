package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardImageUpload extends ActionComponent<String> {

    public static final int TYPE = 8;

    private final String label;
    private final String dir;
    private final int max;
    private List<String> values = Collections.emptyList();


    public DashboardImageUpload(String label, String dir, int max, DashboardEventListener<String> actionListener) {
        this(label, dir, max);
        setActionListener(actionListener);
    }

    public DashboardImageUpload(String label, String dir, int max) {
        super(TYPE);
        this.label = label;
        this.dir = dir;
        this.max = max;
    }

    public DashboardImageUpload(JSONObject json) {
        super(json);
        label = json.getString("label");
        dir = json.has("dir") ? json.getString("dir") : "temp";
        max = json.has("max") ? json.getInt("max") : 1;

        if (json.has("values")) {
            ArrayList<String> newValues = new ArrayList<>();
            JSONArray valuesJson = json.getJSONArray("values");
            for (int i = 0; i < valuesJson.length(); i++) {
                newValues.add(valuesJson.getString(i));
            }
            values = Collections.unmodifiableList(newValues);
        }
    }

    public String getLabel() {
        return label;
    }

    public String getDir() {
        return dir;
    }

    public int getMax() {
        return max;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public void triggerAdd(List<String> filenames) {
        String filenamesJoined = String.join(",", filenames);
        trigger("add", filenamesJoined);
    }

    public void triggerRemove(String filenames) {
        trigger("remove", filenames);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        json.put("dir", dir);
        json.put("max", max);

        JSONArray valuesJson = new JSONArray();
        values.forEach(valuesJson::put);
        json.put("values", valuesJson);

        return json;
    }

}
