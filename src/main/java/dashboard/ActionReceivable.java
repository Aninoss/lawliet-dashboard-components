package dashboard;

import org.json.JSONObject;

public interface ActionReceivable {

    ActionResult receiveAction(JSONObject action);

}
