package dashboard;

public class ActionResult {

    private final boolean redraw;
    private String errorMessage = null;
    private String successMessage = null;

    public ActionResult(boolean redraw) {
        this.redraw = redraw;
    }

    public ActionResult withErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public ActionResult withSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
        return this;
    }

    public boolean getRedraw() {
        return redraw;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

}
