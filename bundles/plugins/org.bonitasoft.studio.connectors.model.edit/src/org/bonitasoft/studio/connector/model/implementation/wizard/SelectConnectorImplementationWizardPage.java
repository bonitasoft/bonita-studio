/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.model.implementation.wizard;

import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SelectConnectorImplementationWizardPage extends WizardPage implements  IDoubleClickListener {

    private TableViewer table;
    protected Connector connector;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private ConnectorImplementation selectedImplementation;
    private final IContentProvider contentProvider;
    private final LabelProvider labelProvider;

    public SelectConnectorImplementationWizardPage(String pageTitle, String pageDescription,
            IContentProvider contentProvider, LabelProvider labelProvider) {
        super(SelectConnectorImplementationWizardPage.class.getName());
        setTitle(pageTitle);
        setDescription(pageDescription);
        this.contentProvider = contentProvider;
        this.labelProvider = labelProvider;
    }

    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 10, 10, 0).create());
        table = new TableViewer(composite, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        table.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        table.setContentProvider(contentProvider);
        table.setLabelProvider(labelProvider);
        table.addDoubleClickListener(this);

        table.setInput(new Object());

        context = new EMFDataBindingContext();

        IValidator selectionValidator = value -> {
            if (value == null) {
                return ValidationStatus.error(Messages.selectAImplWarning);
            }
            return Status.OK_STATUS;
        };

        UpdateValueStrategy selectionStrategy = new UpdateValueStrategy();
        selectionStrategy.setBeforeSetValidator(selectionValidator);

        context.bindValue(ViewerProperties.singleSelection().observe(table),
                PojoProperties.value("selectedImplementation", ConnectorImplementation.class).observe(this),
                selectionStrategy, null);


        pageSupport = WizardPageSupport.create(this, context);
        setControl(composite);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
        if (pageSupport != null) {
            pageSupport.dispose();
        }
    }

    protected void refresh() {
        if (table != null && !table.getTable().isDisposed()) {
            table.setInput(new Object());
        }
    }

    @Override
    public boolean canFlipToNextPage() {
        return ((IStructuredSelection) table.getSelection()).getFirstElement() instanceof ConnectorImplementation;
    }


    @Override
    public void doubleClick(DoubleClickEvent event) {
        Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
        if (selection instanceof ConnectorImplementation) {
            if (getNextPage() != null) {
                getContainer().showPage(getNextPage());
            } else {
                if (getWizard().performFinish()) {
                    ((WizardDialog) getContainer()).close();
                }
            }
        }
    }

    public ConnectorImplementation getSelectedImplementation() {
        return selectedImplementation;
    }

    public void setSelectedImplementation(ConnectorImplementation selectedImplementation) {
        this.selectedImplementation = selectedImplementation;
    }

}
