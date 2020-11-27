/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.jface.databinding.validator.TypedValidator;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.rest.api.extension.ui.wizard.RestAPIExtensionLabelProvider;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SelectCustomPageProjectPage extends WizardPage {

    private static final int TABLE_WIDTH_HINT = 600;
    private final CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore;
    protected final WidgetFactory widgetFactory;
    private final IObservableValue fileStoreObservable;
    private DataBindingContext context;

    public SelectCustomPageProjectPage(final CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore,
            final WidgetFactory widgetFactory,
            final IObservableValue fileStoreObservable) {
        super(SelectCustomPageProjectPage.class.getName());
        this.repositoryStore = repositoryStore;
        this.widgetFactory = widgetFactory;
        this.fileStoreObservable = fileStoreObservable;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        context = new DataBindingContext();

        doCreateContent(mainComposite, context);

        WizardPageSupport.create(this, context);
        setControl(mainComposite);
    }

    protected void doCreateContent(final Composite mainComposite, final DataBindingContext context) {
        final TableViewer restApiExtensionViewer = widgetFactory.newTableViewer(mainComposite, "restApiTable");
        restApiExtensionViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(TABLE_WIDTH_HINT, SWT.DEFAULT).span(2, 1).create());

        restApiExtensionViewer.setContentProvider(ArrayContentProvider.getInstance());
        restApiExtensionViewer.setLabelProvider(new RestAPIExtensionLabelProvider());
        restApiExtensionViewer.setInput(repositoryStore.getChildren());
        restApiExtensionViewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(final DoubleClickEvent event) {
                if (getNextPage() != null && canFlipToNextPage()) {
                    getContainer().showPage(getNextPage());
                } else if (getWizard().canFinish()) {
                    if (getWizard().performFinish()) {
                        ((WizardDialog) getContainer()).close();
                    }
                }
            }
        });
        context.bindValue(ViewersObservables.observeSingleSelection(restApiExtensionViewer), fileStoreObservable,
                updateValueStrategy().withValidator(selectionValidator()).create(), null);
    }

    private IValidator selectionValidator() {
        return new TypedValidator<CustomPageProjectFileStore, IStatus>() {

            @Override
            protected IStatus doValidate(final CustomPageProjectFileStore value) {
                return value == null ? ValidationStatus.error(Messages.emptySelectionErrorMessage) : ValidationStatus.ok();
            }

        };
    }

    protected DataBindingContext getContext() {
        return context;
    }

}
