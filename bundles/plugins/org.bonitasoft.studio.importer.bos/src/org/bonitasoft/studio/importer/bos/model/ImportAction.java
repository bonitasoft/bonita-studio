package org.bonitasoft.studio.importer.bos.model;

import org.bonitasoft.studio.importer.bos.i18n.Messages;

public enum ImportAction {

    KEEP(Messages.keepMessage), OVERWRITE(Messages.overwriteMessage), SMART_IMPORT(Messages.smartImportMessage);

    private String name;

    private ImportAction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
