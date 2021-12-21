package dashboard.data;

import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

public class GridRow {

    private final String id;
    private final String[] rowValues;

    public GridRow(String id, String[] rowValues) {
        this.id = id;
        this.rowValues = rowValues;
    }

    public String getId() {
        return id;
    }

    public String[] getRowValues() {
        return rowValues;
    }

    public String getRowValue(int i) {
        return rowValues[i];
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);

        JSONArray valuesJson = new JSONArray();
        for (String rowValue : rowValues) {
            valuesJson.put(rowValue);
        }
        jsonObject.put("values", valuesJson);

        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GridRow)) return false;
        GridRow gridRow = (GridRow) o;
        return id.equals(gridRow.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static GridRow fromJson(JSONObject jsonObject) {
        String rowId = jsonObject.getString("id");
        JSONArray valuesJson = jsonObject.getJSONArray("values");
        String[] values = new String[valuesJson.length()];
        for (int i = 0; i < valuesJson.length(); i++) {
            values[i] = valuesJson.getString(i);
        }
        return new GridRow(rowId, values);
    }

}
