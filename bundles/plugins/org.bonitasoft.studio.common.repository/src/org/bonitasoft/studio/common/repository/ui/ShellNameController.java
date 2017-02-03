/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common.repository.ui;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.eclipse.swt.widgets.Shell;

public class ShellNameController {

    private Shell shell;

    public ShellNameController(Shell shell) {
        this.shell = shell;
    }

    public void update(Repository activeRepository) {
        String shellTitle = shell.getText();
        final int index = shellTitle.indexOf(" - ");
        shellTitle = index > 0 ? shellTitle.substring(0, index)
                : shellTitle;
        if (!activeRepository.getName()
                .equals(RepositoryPreferenceConstant.DEFAULT_REPOSITORY_NAME)) {
            shellTitle = shellTitle + " - " + activeRepository.getName();
        }
        shell.setText(shellTitle);
    }
}
