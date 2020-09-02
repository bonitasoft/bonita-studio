/**
 * Copyright (C) 2017 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.ui.editors.contribution;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractFormPage;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
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
        Image image = PreferenceUtil.isDarkTheme()
                ? Pics.getImage(PicsConstants.export_16_dark)
                : Pics.getImage(PicsConstants.export_16);
        item.setImage(image);
        item.addListener(SWT.Selection, event -> onClick(parent.getShell()));
    }

    private void onClick(Shell shell) {
        if (formPage.getEditor().isDirty()
                && MessageDialog.openQuestion(shell, Messages.saveBeforeTitle,
                        String.format(Messages.saveBeforeMessage, formPage.getEditor().getEditorInput().getName()))) {
            formPage.getEditor().doSave(new NullProgressMonitor());
            if (formPage.getEditor().isDirty()) {
                MessageDialog.openInformation(shell, Messages.exportCancelTitle, Messages.exportCancel);
                return;
            }
        }
        exportAction(shell);
    }

    protected abstract void exportAction(Shell shell);

}
