/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors.contribution;

import org.bonitasoft.studio.ui.UIPlugin;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractFormPage;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public abstract class AbstractExportContributionItem<T extends AbstractFormPage> extends ContributionItem {

    protected final T formPage;
    protected ToolItem item;

    public AbstractExportContributionItem(String ID, T formPage) {
        super(ID);
        this.formPage = formPage;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        item = new ToolItem(parent, SWT.PUSH);
        item.setText(Messages.export);
        item.setToolTipText(Messages.exportTooltips);
        item.setImage(UIPlugin.getImage("icons/export_16.png"));
        item.addListener(SWT.Selection, event -> onClick(parent.getShell()));
    }

    private void onClick(Shell shell) {
        if (formPage.getEditor().isDirty()
                && MessageDialog.openQuestion(shell, Messages.saveBeforeTitle,
                        String.format(Messages.saveBeforeMessage, formPage.getEditor().getEditorInput().getName()))) {
            formPage.getEditor().doSave(new NullProgressMonitor());
        }
        exportAction(shell);
    }

    protected abstract void exportAction(Shell shell);

}
