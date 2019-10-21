package org.bonitasoft.studio.engine.export;

public class BarCreationException extends Exception {

    public BarCreationException(String message) {
        super(message);
    }

    public BarCreationException(String message, Throwable t) {
        super(message, t);
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMessage());
        Throwable cause = getCause();
        while (cause != null) {
            sb.append("\n");
            sb.append(cause.getMessage());
            cause = cause.getCause();
        }
        return sb.toString();
    }

}
