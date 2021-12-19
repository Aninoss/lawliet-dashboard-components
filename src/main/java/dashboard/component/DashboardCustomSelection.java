package dashboard.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dashboard.listener.DashboardEventListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardCustomSelection extends ActionComponent<String> {

    public static final int TYPE = 14;

    private final String label;
    private final int min;
    private final int max;
    private final List<String> data;
    private List<String> selectedData = Collections.emptyList();

    public DashboardCustomSelection(String label, List<String> data, int min, int max, DashboardEventListener<String> actionListener) {
        this(label, data, min, max);
        setActionListener(actionListener);
    }

    public DashboardCustomSelection(String label, List<String> data, int min, int max) {
        super(TYPE);
        this.label = label;
        this.data = data;
        this.min = min;
        this.max = max;
    }

    public DashboardCustomSelection(JSONObject json) {
        super(json);
        label = json.getString("label");
        min = json.getInt("min");
        max = json.getInt("max");

        ArrayList<String> newData = new ArrayList<>();
        JSONArray dataJson = json.getJSONArray("data");
        for (int i = 0; i < dataJson.length(); i++) {
            newData.add(dataJson.getString(i));
        }
        data = Collections.unmodifiableList(newData);

        ArrayList<String> newSelectedData = new ArrayList<>();
        JSONArray selectedDataJson = json.getJSONArray("selected_data");
        for (int i = 0; i < selectedDataJson.length(); i++) {
            newSelectedData.add(selectedDataJson.getString(i));
        }
        selectedData = Collections.unmodifiableList(newSelectedData);
    }

    public String getLabel() {
        return label;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public List<String> getData() {
        return data;
    }

    public List<String> getSelectedData() {
        return selectedData;
    }

    public void setSelectedData(List<String> selectedData) {
        this.selectedData = selectedData;
    }

    public void triggerAdd(String element) {
        trigger("add", element);
    }

    public void triggerRemove(String element) {
        trigger("remove", element);
    }

    public void triggerSet(String element) {
        trigger("set", element);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        json.put("min", min);
        json.put("max", max);

        JSONArray dataJson = new JSONArray();
        for (String data : data) {
            dataJson.put(data);
        }
        json.put("data", dataJson);

        JSONArray selectedDataJson = new JSONArray();
        for (String data : selectedData) {
            selectedDataJson.put(data);
        }
        json.put("selected_data", selectedDataJson);

        return json;
    }

}
