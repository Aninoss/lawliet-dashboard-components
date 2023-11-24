package dashboard.component;

import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;

public class DashboardFloatingNumberField extends DashboardTextFieldAbstract<Double, Double> {

    public static final int TYPE = 17;
    private double value = 0.0;

    public DashboardFloatingNumberField(String label, double min, double max, DashboardEventListener<Double> actionListener) {
        super(TYPE, label, min, max);
        setActionListener(actionListener);
    }

    public DashboardFloatingNumberField(String label, double min, double max) {
        super(TYPE, label, min, max);
    }

    public DashboardFloatingNumberField(JSONObject json) {
        super(json);
        value = json.getDouble("value");
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("value", value);
        return json;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
