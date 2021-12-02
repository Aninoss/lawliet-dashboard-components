package dashboard.listener;

import dashboard.ActionResult;
import dashboard.DashboardEvent;

public interface DashboardEventListener<T> {

    ActionResult accept(DashboardEvent<T> event) throws Throwable;

}
