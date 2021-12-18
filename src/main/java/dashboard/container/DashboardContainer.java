package dashboard.container;

import java.util.*;
import dashboard.ActionReceivable;
import dashboard.ActionResult;
import dashboard.ActionSendable;
import dashboard.DashboardComponent;
import dashboard.listener.ActionSendListener;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DashboardContainer extends DashboardComponent implements ActionReceivable, ActionSendable {

    private final static Logger LOGGER = LoggerFactory.getLogger(DashboardContainer.class);

    private final ArrayList<DashboardComponent> children = new ArrayList<>();
    private boolean card = false;
    private ActionSendListener actionSendListener = null;

    public DashboardContainer(int type, DashboardComponent... children) {
        super(type);
        add(children);
    }

    public DashboardContainer(JSONObject json) {
        super(json);
        card = json.getBoolean("card");
        JSONArray childrenJson = json.getJSONArray("children");
        for (int i = 0; i < childrenJson.length(); i++) {
            JSONObject elementJson = childrenJson.getJSONObject(i);
            DashboardComponent element = DashboardComponent.generate(elementJson);
            element.setParent(this);
            children.add(element);
        }
    }

    public boolean isCard() {
        return card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public ActionSendListener getActionSendListener() {
        return actionSendListener;
    }

    public void setActionSendListener(ActionSendListener actionSendListener) {
        this.actionSendListener = actionSendListener;
    }

    public void add(DashboardComponent... elements) {
        add(Arrays.asList(elements));
    }

    public void add(Collection<DashboardComponent> elements) {
        children.addAll(elements);
        elements.forEach(e -> e.setParent(this));
    }

    public DashboardComponent remove(int index) {
        DashboardComponent element = children.remove(index);
        element.setParent(null);
        return element;
    }

    public void remove(DashboardComponent... elements) {
        remove(Arrays.asList(elements));
    }

    public void remove(Collection<DashboardComponent> elements) {
        children.removeAll(elements);
        elements.forEach(e -> e.setParent(null));
    }

    public List<DashboardComponent> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public int size() {
        return children.size();
    }

    @Override
    public void sendAction(JSONObject action, String confirmationMessage) {
        DashboardContainer parent = getParent();
        JSONObject newAction = new JSONObject();
        newAction.put("id", getId());
        newAction.put("content", action);

        if (actionSendListener != null) {
            try {
                actionSendListener.accept(newAction, confirmationMessage);
            } catch (Throwable e) {
                LOGGER.error("Error in action send listener", e);
            }
        }
        if (parent != null) {
            parent.sendAction(newAction, confirmationMessage);
        }
    }

    @Override
    public ActionResult receiveAction(JSONObject action) {
        JSONObject childJson = action.getJSONObject("content");
        String id = childJson.getString("id");
        DashboardComponent element = children.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        if (element instanceof ActionReceivable) {
            return ((ActionReceivable) element).receiveAction(childJson);
        } else {
            LOGGER.error("Received dashboard action for invalid element: {}", id);
            return null;
        }
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        JSONArray childrenJson = new JSONArray();
        for (DashboardComponent child : children) {
            childrenJson.put(child.toJSON());
        }
        json.put("children", childrenJson);
        json.put("card", card);
        return json;
    }

}
