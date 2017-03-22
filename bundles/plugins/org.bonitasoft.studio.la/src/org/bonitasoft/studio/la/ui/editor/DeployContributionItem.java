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
package org.bonitasoft.studio.la.ui.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.jface.dialog.ProblemsDialog;
import org.bonitasoft.studio.common.jface.dialog.TypedLabelProvider;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.core.DeployApplicationRunnable;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.editor.FormEditor;

public class DeployContributionItem extends ContributionItem {

    private static final String ID = "org.bonitasoft.studio.la.ui.editor.deploy";
    private final FormEditor formEditor;

    public DeployContributionItem(FormEditor formEditor) {
        super(ID);
        this.formEditor = formEditor;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        final ToolItem item = new ToolItem(parent, SWT.PUSH);
        item.setToolTipText(Messages.deploy);
        item.setImage(LivingApplicationPlugin.getImage("icons/upload_24.png"));
        item.addListener(SWT.Selection, event -> onClick(parent.getShell()));
    }

    private void onClick(Shell shell) {
        final GetApiSessionOperation apiSessionOperation = new GetApiSessionOperation();
        final String name = formEditor.getEditorInput().getName();
        if (formEditor.isDirty() && MessageDialog.openQuestion(shell, Messages.saveBeforeDeployTitle,
                String.format(Messages.saveBeforeDeploy, name))) {
            formEditor.doSave(new NullProgressMonitor());
        }
        final ApplicationRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(ApplicationRepositoryStore.class);
        final ApplicationFileStore applicationFileStore = store.getChild(name);
        try {
            final APISession apiSession = apiSessionOperation.execute();
            final ApplicationAPI applicationAPI = BOSEngineManager.getInstance().getApplicationAPI(apiSession);
            final DeployApplicationRunnable deployApplicationRunnable = new DeployApplicationRunnable(applicationAPI,
                    applicationFileStore);
            deployApplicationRunnable.run(new NullProgressMonitor());
            final IStatus status = deployApplicationRunnable.getStatus();
            if (status.isOK()) {
                new DeployedAppDialog(shell, applicationFileStore).open();
            } else {
                MessageDialog.openError(shell, Messages.deployFailedTitle,
                        status.getMessage());
            }
        } catch (InvocationTargetException | InterruptedException | BonitaHomeNotSetException | ServerAPIException
                | UnknownAPITypeException e) {
            new ExceptionDialogHandler().openErrorDialog(shell, Messages.deployFailedTitle, e);
        } finally {
            apiSessionOperation.logout();
        }

    }

    private class DeployedAppDialog extends ProblemsDialog<ApplicationNode> {

        private final ApplicationFileStore applicationFileStore;

        public DeployedAppDialog(Shell parentShell, ApplicationFileStore applicationFileStore) {
            super(parentShell, Messages.deployDoneTitle, Messages.deployDoneMessage, MessageDialog.INFORMATION,
                    new String[] { IDialogConstants.OK_LABEL });
            this.applicationFileStore = applicationFileStore;
        }

        @Override
        protected TypedLabelProvider<ApplicationNode> getTypedLabelProvider() {
            return new TypedLabelProvider<ApplicationNode>() {

                @Override
                public String getText(ApplicationNode element) {
                    return String.format("%s (%s) - ../apps/%s", element.getDisplayName(), element.getVersion(),
                            element.getToken());
                }

                @Override
                public Image getImage(ApplicationNode element) {
                    return applicationFileStore.getIcon();
                }
            };
        }

        @Override
        protected Collection<ApplicationNode> getInput() {
            try {
                return applicationFileStore.getContent().getApplications();
            } catch (final ReadFileStoreException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
