/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.ui.wizard;

import org.bonitasoft.studio.common.repository.RepositoryNameValidator;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class RepositoryNameControlSupplier implements ControlSupplier {

    private String repositoryName;
    private IObservableValue<String> repositoryNameObservable;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.wizard.ControlSupplier#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.jface.wizard.IWizardContainer,
     * org.eclipse.core.databinding.DataBindingContext)
     */
    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.swtDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        repositoryNameObservable = PojoProperties.value("repositoryName", String.class).observe(this);
        new TextWidget.Builder()
                .withLabel(Messages.workspaceLocation)
                .useNativeRender()
                .fill()
                .grabHorizontalSpace()
                .withMessage(Messages.workspaceLocationHint)
                .bindTo(repositoryNameObservable)
                .withValidator(new RepositoryNameValidator())
                .inContext(ctx)
                .createIn(composite);
        return composite;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public void updateRepositoryName(String name) {
        repositoryNameObservable.setValue(name);
    }

}
