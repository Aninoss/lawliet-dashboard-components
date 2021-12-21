package dashboard.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dashboard.data.DiscordEntity;
import dashboard.listener.DashboardEventListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardSelect extends ActionComponent<String> {

    public static final int TYPE = 14;

    private final String label;
    private final boolean canBeEmpty;
    private final List<DiscordEntity> values;
    private DiscordEntity selectedValue;

    public DashboardSelect(String label, List<DiscordEntity> values, boolean canBeEmpty, DashboardEventListener<String> actionListener) {
        this(label, values, canBeEmpty);
        setActionListener(actionListener);
    }

    public DashboardSelect(String label, List<DiscordEntity> values, boolean canBeEmpty) {
        super(TYPE);
        this.label = label;
        this.values = values;
        this.canBeEmpty = canBeEmpty;
    }

    public DashboardSelect(JSONObject json) {
        super(json);
        label = json.getString("label");
        canBeEmpty = json.getBoolean("can_be_empty");

        ArrayList<DiscordEntity> newValues = new ArrayList<>();
        JSONArray selectedDataJson = json.getJSONArray("values");
        for (int i = 0; i < selectedDataJson.length(); i++) {
            JSONObject entityJson = selectedDataJson.getJSONObject(i);
            newValues.add(DiscordEntity.fromJson(entityJson));
        }
        values = Collections.unmodifiableList(newValues);

        if (json.has("selected_value") && !json.isNull("selected_value")) {
            JSONObject entityJson = json.getJSONObject("selected_value");
            selectedValue = DiscordEntity.fromJson(entityJson);
        }
    }

    public String getLabel() {
        return label;
    }

    public boolean getCanBeEmpty() {
        return canBeEmpty;
    }

    public List<DiscordEntity> getValues() {
        return values;
    }

    public DiscordEntity getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(DiscordEntity selectedValue) {
        this.selectedValue = selectedValue;
    }

    public void triggerSet(String entityId) {
        trigger("set", entityId);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("label", label);
        json.put("can_be_empty", canBeEmpty);
        json.put("selected_value", selectedValue.toJson());

        if (values != null) {
            JSONArray valuesJson = new JSONArray();
            values.forEach(discordEntity -> valuesJson.put(discordEntity.toJson()));
            json.put("values", valuesJson);
        }

        return json;
    }

}
