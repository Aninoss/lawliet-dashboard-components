package dashboard.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dashboard.data.DiscordEntity;
import dashboard.listener.DashboardEventListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardComboBox extends ActionComponent<String> {

    public static final int TYPE = 3;

    public enum DataType { TEXT_CHANNELS, VOICE_CHANNELS, MEMBERS, ROLES, CUSTOM }

    private final String label;
    private final DataType dataType;
    private final boolean canBeEmpty;
    private final int max;
    private final List<DiscordEntity> values;
    private boolean allowCustomValues = false;
    private List<DiscordEntity> selectedValues = Collections.emptyList();

    public DashboardComboBox(String label, DataType dataType, boolean canBeEmpty, int max, DashboardEventListener<String> actionListener) {
        this(label, dataType, canBeEmpty, max);
        setActionListener(actionListener);
    }

    public DashboardComboBox(String label, DataType dataType, boolean canBeEmpty, int max) {
        super(TYPE);
        this.label = label;
        this.dataType = dataType;
        this.values = null;
        this.canBeEmpty = canBeEmpty;
        this.max = max;
    }

    public DashboardComboBox(String label, List<DiscordEntity> values, boolean canBeEmpty, int max, DashboardEventListener<String> actionListener) {
        this(label, values, canBeEmpty, max);
        setActionListener(actionListener);
    }

    public DashboardComboBox(String label, List<DiscordEntity> values, boolean canBeEmpty, int max) {
        super(TYPE);
        this.label = label;
        this.dataType = DataType.CUSTOM;
        this.values = values;
        this.canBeEmpty = canBeEmpty;
        this.max = max;
    }

    public DashboardComboBox(JSONObject json) {
        super(json);
        label = json.getString("label");
        dataType = DataType.values()[json.getInt("data_type")];
        canBeEmpty = json.getBoolean("can_be_empty");
        max = json.getInt("max");
        allowCustomValues = json.getBoolean("allow_custom_values");

        if (dataType == DataType.CUSTOM) {
            ArrayList<DiscordEntity> newValues = new ArrayList<>();
            JSONArray valuesJson = json.getJSONArray("values");
            for (int i = 0; i < valuesJson.length(); i++) {
                JSONObject entityJson = valuesJson.getJSONObject(i);
                newValues.add(DiscordEntity.fromJson(entityJson));
            }
            values = Collections.unmodifiableList(newValues);
        } else {
            values = null;
        }

        ArrayList<DiscordEntity> newSelectedValues = new ArrayList<>();
        JSONArray selectedValuesJson = json.getJSONArray("selected_values");
        for (int i = 0; i < selectedValuesJson.length(); i++) {
            JSONObject entityJson = selectedValuesJson.getJSONObject(i);
            newSelectedValues.add(DiscordEntity.fromJson(entityJson));
        }
        selectedValues = Collections.unmodifiableList(newSelectedValues);
    }

    public String getLabel() {
        return label;
    }

    public DataType getDataType() {
        return dataType;
    }

    public boolean getCanBeEmpty() {
        return canBeEmpty;
    }

    public int getMax() {
        return max;
    }

    public List<DiscordEntity> getValues() {
        return values;
    }

    public List<DiscordEntity> getSelectedValues() {
        return selectedValues;
    }

    public void setSelectedValues(List<DiscordEntity> selectedValues) {
        this.selectedValues = selectedValues;
    }

    public boolean getAllowCustomValues() {
        return allowCustomValues;
    }

    public void setAllowCustomValues(boolean allowCustomValues) {
        this.allowCustomValues = allowCustomValues;
    }

    public void triggerAdd(String entityId) {
        trigger("add", entityId);
    }

    public void triggerRemove(String entityId) {
        trigger("remove", entityId);
    }

    public void triggerSet(String entityId) {
        trigger("set", entityId);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        json.put("data_type", dataType.ordinal());
        json.put("can_be_empty", canBeEmpty);
        json.put("max", max);
        json.put("allow_custom_values", allowCustomValues);

        if (values != null) {
            JSONArray valuesJson = new JSONArray();
            values.forEach(discordEntity -> valuesJson.put(discordEntity.toJson()));
            json.put("values", valuesJson);
        }

        JSONArray selectedValuesJson = new JSONArray();
        selectedValues.forEach(discordEntity -> selectedValuesJson.put(discordEntity.toJson()));
        json.put("selected_values", selectedValuesJson);

        return json;
    }

}
