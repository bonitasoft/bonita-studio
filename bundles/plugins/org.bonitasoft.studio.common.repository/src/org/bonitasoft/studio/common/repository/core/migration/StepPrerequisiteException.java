package org.bonitasoft.studio.common.repository.core.migration;

import java.io.Serial;

public class StepPrerequisiteException extends Exception {

    @Serial
    private static final long serialVersionUID = -5785138722364190670L;
    
    public StepPrerequisiteException(String message, Throwable cause) {
        super(message, cause);
    }

}
