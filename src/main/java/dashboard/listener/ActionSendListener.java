package dashboard.listener;

import org.json.JSONObject;

public interface ActionSendListener {

    void accept(JSONObject action, String confirmationMessage) throws Throwable;

}
