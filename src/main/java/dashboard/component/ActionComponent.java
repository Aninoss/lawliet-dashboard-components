package dashboard.component;

import dashboard.ActionReceivable;
import dashboard.ActionResult;
import dashboard.DashboardComponent;
import dashboard.DashboardEvent;
import dashboard.container.DashboardContainer;
import dashboard.listener.DashboardEventListener;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ActionComponent<T> extends DashboardComponent implements ActionReceivable {

    private final static Logger LOGGER = LoggerFactory.getLogger(ActionComponent.class);

    private DashboardEventListener<T> actionListener = null;
    private boolean enabled = true;

    public ActionComponent(int type) {
        super(type);
    }

    public ActionComponent(JSONObject json) {
        super(json);
        enabled = json.getBoolean("enabled");
    }

    public void setActionListener(DashboardEventListener<T> actionListener) {
        this.actionListener = actionListener;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    protected void trigger(T data) {
        trigger("generic", data);
    }

    protected void trigger(String type, T data) {
        DashboardContainer parent = getParent();
        if (parent != null) {
            JSONObject action = new JSONObject();
            action.put("id", getId());
            action.put("type", type);
            action.put("data", data);
            parent.sendAction(action);
        }
    }

    @Override
    public ActionResult receiveAction(JSONObject action) {
        if (actionListener != null) {
            String type = action.getString("type");
            if (action.has("data") && !action.isNull("data")) {
                Object o = action.get("data");
                try {
                    DashboardEvent<T> event = new DashboardEvent<>(type, (T) o);
                    return actionListener.accept(event);
                } catch (ClassCastException e) {
                    LOGGER.error("Invalid data type of received dashboard action", e);
                } catch (Throwable throwable) {
                    LOGGER.error("Error in action listener", throwable);
                }
            } else {
                DashboardEvent<T> event = new DashboardEvent<>(type, null);
                try {
                    return actionListener.accept(event);
                } catch (Throwable e) {
                    LOGGER.error("Error in action listener", e);
                }
            }
        }
        return null;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put("enabled", enabled);
        return json;
    }

}
