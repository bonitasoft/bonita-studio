/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.editor.editor.ui.contribution;

import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.ui.editors.contribution.AbstractExportContributionItem;
import org.eclipse.swt.widgets.Shell;

public class ExportBDMContributionItem extends AbstractExportContributionItem<AbstractBdmFormPage> {

    private static final String ID = "org.bonitasoft.studio.bdm.ui.editor.export";
    private static final String EXPORT_COMMAND = "org.bonitasoft.studio.businessobject.exportCommand";
    private CommandExecutor commandExecutor;

    public ExportBDMContributionItem(AbstractBdmFormPage formPage) {
        super(ID, formPage);
        commandExecutor = new CommandExecutor();
    }

    @Override
    protected void exportAction(Shell shell) {
        if (commandExecutor.canExecute(EXPORT_COMMAND, null)) {
            commandExecutor.executeCommand(EXPORT_COMMAND, null);
        }
    }

}
