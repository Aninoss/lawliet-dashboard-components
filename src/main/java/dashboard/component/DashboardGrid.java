package dashboard.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dashboard.data.GridRow;
import dashboard.listener.DashboardEventListener;
import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardGrid extends ActionComponent<String> {

    public static final int TYPE = 16;

    private final String[] header;
    private final List<GridRow> rows;
    private String rowButton = "";

    public DashboardGrid(String[] header, List<GridRow> rows, DashboardEventListener<String> actionListener) {
        this(header, rows);
        setActionListener(actionListener);
    }

    public DashboardGrid(String[] header, List<GridRow> rows) {
        super(TYPE);
        this.header = header;
        this.rows = rows;
    }

    public DashboardGrid(JSONObject json) {
        super(json);
        JSONArray headerJson = json.getJSONArray("header");
        header = new String[headerJson.length()];
        for (int i = 0; i < headerJson.length(); i++) {
            header[i] = headerJson.getString(i);
        }

        JSONArray rowsJson = json.getJSONArray("rows");
        ArrayList<GridRow> newRows = new ArrayList<>();
        for (int i = 0; i < rowsJson.length(); i++) {
            JSONObject rowJson = rowsJson.getJSONObject(i);
            newRows.add(GridRow.fromJson(rowJson));
        }
        rows = Collections.unmodifiableList(newRows);
        rowButton = json.getString("row_button");
    }

    public String[] getHeader() {
        return header;
    }

    public List<GridRow> getRows() {
        return rows;
    }

    public void setRowButton(String rowButton) {
        this.rowButton = rowButton;
    }

    public String getRowButton() {
        return rowButton;
    }

    public void triggerRow(String rowId) {
        trigger(rowId);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();

        JSONArray headerJson = new JSONArray();
        for (String header : header) {
            headerJson.put(header);
        }
        json.put("header", headerJson);

        JSONArray rowsJson = new JSONArray();
        for (GridRow row : rows) {
            rowsJson.put(row.toJson());
        }
        json.put("rows", rowsJson);
        json.put("row_button", rowButton);
        return json;
    }

}
