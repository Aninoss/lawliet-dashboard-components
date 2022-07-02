package dashboard;

public class ActionResult {

    private boolean redraw = false;
    private boolean scrollToTop = false;
    private String errorMessage = null;
    private String successMessage = null;

    public ActionResult withRedraw() {
        this.redraw = true;
        return this;
    }

    public ActionResult withRedrawScrollToTop() {
        this.redraw = true;
        this.scrollToTop = true;
        return this;
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

    public boolean getScrollToTop() {
        return scrollToTop;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

}
