package org.bonitasoft.studio.engine.export;

import java.util.Optional;

import org.eclipse.core.runtime.IStatus;

public class BarCreationException extends Exception {

    private IStatus status;

    public BarCreationException(String message) {
        super(message);
    }

    public BarCreationException(String message, Throwable t) {
        super(message, t);
    }
    
    public BarCreationException(String message, IStatus status) {
        super(message);
        this.status = status;
    }
    
    
    public Optional<IStatus> getStatus() {
        return Optional.ofNullable(status);
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
