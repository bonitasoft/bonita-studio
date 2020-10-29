/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.editor.contribution;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.editor.FormEditor;

public class DeleteContributionItem extends ContributionItem {

    private static final String ID = "org.bonitasoft.studio.la.ui.editor.delete";
    private FormEditor formEditor;

    public DeleteContributionItem(FormEditor formEditor) {
        super(ID);
        this.formEditor = formEditor;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        final ToolItem item = new ToolItem(parent, SWT.PUSH);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ID);
        item.setText(Messages.delete);
        item.setToolTipText(String.format(Messages.deleteContainer, formEditor.getEditorInput().getName()));
        Image deleteImage = PreferenceUtil.isDarkTheme()
                ? Pics.getImage(PicsConstants.bin_16_dark)
                : Pics.getImage(PicsConstants.bin_16);
        item.setImage(deleteImage);
        item.addListener(SWT.Selection, event -> onClick(parent.getShell()));
    }

    private void onClick(Shell shell) {
        String name = formEditor.getEditorInput().getName();
        if (MessageDialog.openQuestion(shell, org.bonitasoft.studio.ui.i18n.Messages.deleteConfirmation,
                String.format(org.bonitasoft.studio.ui.i18n.Messages.deleteConfirmationMessage, "\n" + name))) {
            ApplicationFileStore applicationFileStore = RepositoryManager.getInstance()
                    .getRepositoryStore(ApplicationRepositoryStore.class).getChild(name, true);
            applicationFileStore.close();
            applicationFileStore.delete();
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
                    org.bonitasoft.studio.ui.i18n.Messages.deleteDoneTitle,
                    Messages.deleteSingleDoneMessage);
        }
    }

}
