/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.ui.wizard;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.ui.viewer.RepositoryTreeViewer;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 * 
 */
public class SelectBusinessObjectWizardPage extends WizardPage implements IDoubleClickListener {

    private BusinessObjectModelRepositoryStore businessObjectDefinitionStore;

    private BusinessObjectModelFileStore selectedArtifact;

    /**
     * @param pageName
     */
    protected SelectBusinessObjectWizardPage(BusinessObjectModelRepositoryStore businessObjectDefinitionStore) {
        super(SelectBusinessObjectWizardPage.class.getName());
        setTitle(Messages.selectBusinessObjectWizardTitle);
        setDescription(Messages.selectBusinessObjectWizardMessage);
        this.businessObjectDefinitionStore = businessObjectDefinitionStore;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    public void createControl(Composite parent) {
        final DataBindingContext context = createBindingContext();
        WizardPageSupport.create(this, context);
        final Composite mainComposite = createMainComposite(parent);
        doCreateControls(mainComposite, context);
        setControl(mainComposite);
    }

    protected void doCreateControls(Composite mainComposite, DataBindingContext context) {
        final RepositoryTreeViewer treeViewer = createTreeViewer(mainComposite);
        IValidator selectionValidator = new IValidator() {

            @Override
            public IStatus validate(Object value) {
                if (value == null) {
                    return ValidationStatus.error(Messages.noBusinessObjectSelected);
                }
                return ValidationStatus.ok();
            }
        };

        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setBeforeSetValidator(selectionValidator);

        final IViewerObservableValue observeSingleSelection = ViewersObservables.observeSingleSelection(treeViewer);
        context.bindValue(observeSingleSelection, PojoProperties.value("selectedArtifact").observe(this), strategy, null);

        final Button removeButton = createRemoveButton(mainComposite, treeViewer);
        if (removeButton != null) {
            final UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
            enableStrategy.setConverter(new Converter(BusinessObjectModelFileStore.class, Boolean.class) {

                @Override
                public Object convert(Object fromObject) {
                    return fromObject != null;
                }

            });

            context.bindValue(SWTObservables.observeEnabled(removeButton), observeSingleSelection, new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
                    enableStrategy);
        }
    }

    protected RepositoryTreeViewer createTreeViewer(
            final Composite mainComposite) {
        final RepositoryTreeViewer treeViewer = new RepositoryTreeViewer(mainComposite, SWT.BORDER);
        treeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        treeViewer.setInput(businessObjectDefinitionStore.getChildren());
        treeViewer.addDoubleClickListener(this);
        return treeViewer;
    }

    protected Composite createMainComposite(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        return mainComposite;
    }

    protected DataBindingContext createBindingContext() {
        return new DataBindingContext();
    }

    protected Button createRemoveButton(Composite mainComposite, final TreeViewer treeViewer) {
        final Button removeButton = new Button(mainComposite, SWT.PUSH);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
        removeButton.setText(Messages.Remove);
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                deleteBusinessObjectArtifact(treeViewer);
            }
        });
        return removeButton;
    }

    protected void deleteBusinessObjectArtifact(TreeViewer treeViewer) {
        BusinessObjectModelFileStore artifact = getSelectedArtifact();
        if (confirmDelete(artifact)) {
            artifact.delete();
            treeViewer.setSelection(new StructuredSelection());
            treeViewer.remove(artifact);
        }
    }

    protected boolean confirmDelete(BusinessObjectModelFileStore artifact) {
        return FileActionDialog.confirmDeletionQuestion(artifact.getName());
    }

    public BusinessObjectModelFileStore getSelectedArtifact() {
        return selectedArtifact;
    }

    public void setSelectedArtifact(BusinessObjectModelFileStore selectedArtifact) {
        this.selectedArtifact = selectedArtifact;
    }

    @Override
    public void doubleClick(DoubleClickEvent event) {
        IStructuredSelection selection = (IStructuredSelection) event.getSelection();
        setSelectedArtifact((BusinessObjectModelFileStore) selection.getFirstElement());
        if (getWizard().performFinish()) {
            ((WizardDialog) getContainer()).close();
        }
    }

}
