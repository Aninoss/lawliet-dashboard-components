package dashboard.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dashboard.listener.DashboardEventListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardSelectionMenu extends ActionComponent<Object> {

    public static final int TYPE = 3;

    public enum DataType { TEXT_CHANNELS, VOICE_CHANNELS, MEMBERS, ROLES, OTHER }

    private final String label;
    private final DataType dataType;
    private final int min;
    private final int max;
    private List<Object> selectedData = Collections.emptyList();
    private List<Object> customData = null;

    public DashboardSelectionMenu(String label, DataType dataType, int min, int max, DashboardEventListener<Object> actionListener) {
        this(label, dataType, min, max);
        setActionListener(actionListener);
    }

    public DashboardSelectionMenu(String label, DataType dataType, int min, int max) {
        super(TYPE);
        this.label = label;
        this.dataType = dataType;
        this.min = min;
        this.max = max;
    }

    public DashboardSelectionMenu(JSONObject json) {
        super(json);
        label = json.getString("label");
        dataType = DataType.values()[json.getInt("data_type")];
        min = json.getInt("min");
        max = json.getInt("max");

        ArrayList<Object> newSelectedData = new ArrayList<>();
        JSONArray selectedDataJson = json.getJSONArray("selected_data");
        for (int i = 0; i < selectedDataJson.length(); i++) {
            newSelectedData.add(selectedDataJson.get(i));
        }
        selectedData = Collections.unmodifiableList(newSelectedData);

        if (json.has("custom_data") && !json.isNull("custom_data")) {
            ArrayList<Object> newCustomData = new ArrayList<>();
            JSONArray customDataJson = json.getJSONArray("custom_data");
            for (int i = 0; i < customDataJson.length(); i++) {
                newCustomData.add(customDataJson.get(i));
            }
            customData = Collections.unmodifiableList(newCustomData);
        }
    }

    public String getLabel() {
        return label;
    }

    public DataType getDataType() {
        return dataType;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public List<Object> getCustomData() {
        return customData;
    }

    public void setCustomData(List<Object> customData) {
        this.customData = customData;
    }

    public List<Object> getSelectedData() {
        return selectedData;
    }

    public void setSelectedData(List<Object> selectedData) {
        this.selectedData = selectedData;
    }

    public void triggerAdd(Object element) {
        trigger("add", element);
    }

    public void triggerRemove(Object element) {
        trigger("remove", element);
    }

    public void triggerSet(Object element) {
        trigger("set", element);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        json.put("data_type", dataType.ordinal());
        json.put("min", min);
        json.put("max", max);

        JSONArray selectedDataJson = new JSONArray();
        for (Object element : selectedData) {
            selectedDataJson.put(element);
        }
        json.put("selected_data", selectedDataJson);

        if (customData != null) {
            JSONArray customDataJson = new JSONArray();
            for (Object element : customData) {
                customDataJson.put(element);
            }
            json.put("custom_data", customDataJson);
        }

        return json;
    }

}
