/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.ui.wizard;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.MultiModuleProject;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SelectRepositoryToShare implements ControlSupplier {

    private String projectId;
    private BonitaProject project;

    public SelectRepositoryToShare(BonitaProject project) {
        this.project = project;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.wizard.ControlSupplier#createControl(org.eclipse.swt.widgets.Composite,
     * org.eclipse.jface.wizard.IWizardContainer,
     * org.eclipse.core.databinding.DataBindingContext)
     */
    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.swtDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var repositoryNameObservable = PojoProperties.value("projectId").observe(this);
        repositoryNameObservable.setValue(project.getId());
        new TextWidget.Builder()
                .withLabel(Messages.repository)
                .labelAbove()
                .fill()
                .useNativeRender()
                .grabHorizontalSpace()
                .readOnly(project instanceof MultiModuleProject)
                .bindTo(repositoryNameObservable)
                .withValidator(new ShareableRepositoryValidator(existingProjectIds()))
                .inContext(ctx)
                .createIn(composite);

        return composite;
    }

    private String[] existingProjectIds() {
        return RepositoryManager.getInstance().getAllRepositories().stream()
                .filter(repo -> !repo.isShared())
                .map(repo -> Adapters.adapt(repo,BonitaProject.class))
                .map(BonitaProject::getId)
                .toArray(String[]::new);
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
