/**
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.la.application.ui.editor.contribution;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.dialog.RenameXMLFileDialog;
import org.bonitasoft.studio.ui.validator.ExtensionSupported;
import org.bonitasoft.studio.ui.validator.FileNameValidator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class RenameContributionItem extends ContributionItem {

    private static final String ID = "org.bonitasoft.studio.la.ui.editor.rename";
    private final ApplicationFormPage formPage;

    public RenameContributionItem(ApplicationFormPage formPage) {
        super(ID);
        this.formPage = formPage;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        final ToolItem item = new ToolItem(parent, SWT.PUSH);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ID);
        item.setText(org.bonitasoft.studio.ui.i18n.Messages.rename);
        item.setToolTipText(org.bonitasoft.studio.ui.i18n.Messages.rename);
        item.setImage(Pics.getImage(PicsConstants.edit));
        item.addListener(SWT.Selection, event -> onClick(parent.getShell()));
    }

    private void onClick(Shell shell) {
        ApplicationRepositoryStore applicationRepositoryStore = appStore();
        String name = formPage.getEditor().getEditorInput().getName();
        ApplicationFileStore applicationFileStore = applicationRepositoryStore.getChild(name, true);

        if (!formPage.isDirty() || formPage.isDirty()
                && MessageDialog.openQuestion(shell, org.bonitasoft.studio.ui.i18n.Messages.saveBeforeTitle,
                        String.format(org.bonitasoft.studio.ui.i18n.Messages.saveBeforeMessage, name))) {
            formPage.doSave(new NullProgressMonitor());
            FileNameValidator validator = new FileNameValidator(appStore(), ExtensionSupported.XML,
                    name.endsWith(".xml") ? name.replace(".xml", "") : name);
            RenameXMLFileDialog.open(shell, applicationFileStore, validator);
        }
    }

    protected ApplicationRepositoryStore appStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ApplicationRepositoryStore.class);
    }

}
