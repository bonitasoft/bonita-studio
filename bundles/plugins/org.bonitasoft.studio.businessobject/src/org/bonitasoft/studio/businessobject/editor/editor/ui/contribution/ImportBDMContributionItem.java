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
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ImportBDMContributionItem extends ContributionItem {

    public static final String ID = "org.bonitasoft.studio.bdm.editor.import";
    private static final String IMPORT_COMMAND = "org.bonitasoft.studio.businessobject.command.import";

    protected AbstractBdmFormPage formPage;
    protected ToolItem item;
    private CommandExecutor commandExecutor;

    public ImportBDMContributionItem(AbstractBdmFormPage formPage) {
        super(ID);
        this.formPage = formPage;
        commandExecutor = new CommandExecutor();
    }

    @Override
    public void fill(ToolBar parent, int index) {
        item = new ToolItem(parent, SWT.PUSH);
        item.setText(Messages.importActionName);
        item.setToolTipText(Messages.smartImportDesc);
        item.setImage(Pics.getImage(PicsConstants.import_artifact));
        item.addListener(SWT.Selection, e -> onClick());
    }

    private void onClick() {
        if (commandExecutor.canExecute(IMPORT_COMMAND, null)) {
            commandExecutor.executeCommand(IMPORT_COMMAND, null);
        }
    }

}
