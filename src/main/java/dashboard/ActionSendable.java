package dashboard;

import org.json.JSONObject;

public interface ActionSendable {

    void sendAction(JSONObject action, String confirmationMessage);

}
