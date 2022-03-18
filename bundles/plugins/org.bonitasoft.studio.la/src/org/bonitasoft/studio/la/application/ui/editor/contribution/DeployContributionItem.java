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

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.application.handler.DeployArtifactsHandler;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.DependencyResolver;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog;
import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog.DeployStrategy;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;

public class DeployContributionItem extends ContributionItem {

    private static final String ID = "org.bonitasoft.studio.la.ui.editor.deploy";
    private final ApplicationFormPage formPage;
    private ToolItem item;
    private DependencyResolver<ApplicationFileStore> dependencyResolver;

    public DeployContributionItem(ApplicationFormPage formPage,
            DependencyResolver<ApplicationFileStore> dependencyResolver) {
        super(ID);
        this.formPage = formPage;
        this.dependencyResolver = dependencyResolver;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        item = new ToolItem(parent, SWT.PUSH);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ID);
        item.setText(Messages.deployContributionLabel);
        item.setToolTipText(Messages.deployTooltip);
        item.setImage(Pics.getImage(PicsConstants.deploy));
        item.addListener(SWT.Selection, event -> deploy(parent.getShell()));
        item.setEnabled(isEnabled());
    }

    public void deploy(Shell shell) {
        final String name = formPage.getEditor().getEditorInput().getName();
        if (formPage.getEditor().isDirty()) {
            DeployStrategy choice = SaveBeforeDeployDialog.open(name);
            if (choice == DeployStrategy.SAVE_AND_DEPLOY) {
                formPage.getEditor().doSave(new NullProgressMonitor());
            } else if (choice == DeployStrategy.CANCEL) {
                return;
            }
        }

        DeployArtifactsHandler deployHandler = new DeployArtifactsHandler();
        ApplicationFileStore applicationFileStore = formPage.getRepositoryAccessor()
                .getRepositoryStore(ApplicationRepositoryStore.class).getChild(name, true);
        List<IRepositoryFileStore<?>> dependencies = dependencyResolver.findDependencies(applicationFileStore);
        dependencies.add(applicationFileStore);
        deployHandler.setDefaultSelection(dependencies);
        try {
            deployHandler.deploy(shell, formPage.getRepositoryAccessor(), PlatformUI.getWorkbench().getProgressService());
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public boolean isEnabled() {
        return !formPage.isErrorState() && !formPage.getWorkingCopy().getApplications().isEmpty();
    }

    @Override
    public void update() {
        if (item != null && !item.isDisposed()) {
            item.setEnabled(isEnabled());
        }
    }

}
