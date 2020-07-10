package controller.COR;

public class HighlightHandlerFactory {

    private static HighlightHandler highlightHandler = new HighlightHandlerWithOne();

    public static HighlightHandler getHighlightHandler() {
        return highlightHandler;
    }
}
