package dashboard.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dashboard.data.DiscordEntity;
import dashboard.listener.DashboardEventListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardDiscordEntitySelection extends ActionComponent<Long> {

    public static final int TYPE = 3;

    public enum DataType { TEXT_CHANNELS, VOICE_CHANNELS, MEMBERS, ROLES }

    private final String label;
    private final DataType dataType;
    private final boolean canBeEmpty;
    private final int max;
    private List<DiscordEntity> selectedData = Collections.emptyList();

    public DashboardDiscordEntitySelection(String label, DataType dataType, boolean canBeEmpty, int max, DashboardEventListener<Long> actionListener) {
        this(label, dataType, canBeEmpty, max);
        setActionListener(actionListener);
    }

    public DashboardDiscordEntitySelection(String label, DataType dataType, boolean canBeEmpty, int max) {
        super(TYPE);
        this.label = label;
        this.dataType = dataType;
        this.canBeEmpty = canBeEmpty;
        this.max = max;
    }

    public DashboardDiscordEntitySelection(JSONObject json) {
        super(json);
        label = json.getString("label");
        dataType = DataType.values()[json.getInt("data_type")];
        canBeEmpty = json.getBoolean("can_be_empty");
        max = json.getInt("max");

        ArrayList<DiscordEntity> newSelectedData = new ArrayList<>();
        JSONArray selectedDataJson = json.getJSONArray("selected_data");
        for (int i = 0; i < selectedDataJson.length(); i++) {
            JSONObject entityJson = selectedDataJson.getJSONObject(i);
            DiscordEntity discordEntity = new DiscordEntity(entityJson.getLong("id"), entityJson.getString("name"));
            newSelectedData.add(discordEntity);
        }
        selectedData = Collections.unmodifiableList(newSelectedData);
    }

    public String getLabel() {
        return label;
    }

    public DataType getDataType() {
        return dataType;
    }

    public boolean canBeEmpty() {
        return canBeEmpty;
    }

    public int getMax() {
        return max;
    }

    public List<DiscordEntity> getSelectedData() {
        return selectedData;
    }

    public void setSelectedData(List<DiscordEntity> selectedData) {
        this.selectedData = selectedData;
    }

    public void triggerAdd(long entityId) {
        trigger("add", entityId);
    }

    public void triggerRemove(long entityId) {
        trigger("remove", entityId);
    }

    public void triggerSet(long entityId) {
        trigger("set", entityId);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        json.put("data_type", dataType.ordinal());
        json.put("can_be_empty", canBeEmpty);
        json.put("max", max);

        JSONArray selectedDataJson = new JSONArray();
        for (DiscordEntity discordEntity : selectedData) {
            JSONObject entityJson = new JSONObject();
            entityJson.put("id", discordEntity.getId());
            entityJson.put("name", discordEntity.getName());
            selectedDataJson.put(entityJson);
        }
        json.put("selected_data", selectedDataJson);

        return json;
    }

}
