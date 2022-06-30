/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.internal.WorkbenchPlugin;

public class DialogSettingsHelper {

    private final String id;

    public DialogSettingsHelper(String id) {
        this.id = id;
    }

    public IDialogSettings getDialogSettings() {
        final IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault()
                .getDialogSettings();
        IDialogSettings settings = workbenchSettings.getSection(id);
        if (settings == null) {
            settings = workbenchSettings.addNewSection(id);
        }
        return settings;
    }

    public String get(String property, String defaultValue) {
        final IDialogSettings dialogSettings = getDialogSettings();
        final String value = dialogSettings.get(property);
        if(value == null){
            return defaultValue;
        }
        return value;
    }

    public void put(String key, String value) {
        final IDialogSettings dialogSettings = getDialogSettings();
        dialogSettings.put(key, value);
    }

}
