package org.bonitasoft.studio.designer.core.exception;

import java.io.Serial;

public class UIDBackendException extends Exception {

    @Serial
    private static final long serialVersionUID = 2096684468038409745L;

    public UIDBackendException(String uidLogs) {
       super(uidLogs);
    }

    public UIDBackendException(String logs, Throwable cause) {
        super(logs, cause);
    }

}
