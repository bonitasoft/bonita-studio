/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import static org.bonitasoft.studio.common.ui.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.databinding.validator.TypedValidator;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.rest.api.extension.ui.wizard.RestAPIExtensionLabelProvider;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SelectCustomPageProjectPage extends WizardPage {

    private static final int TABLE_WIDTH_HINT = 600;
    private final ExtensionRepositoryStore repositoryStore;
    protected final WidgetFactory widgetFactory;
    private final IObservableValue fileStoreObservable;
    private DataBindingContext context;
    private TableViewer viewer;
    private List<ViewerFilter> filters = new ArrayList<>();

    public SelectCustomPageProjectPage(final ExtensionRepositoryStore repositoryStore,
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
        viewer = widgetFactory.newTableViewer(mainComposite, "restApiTable");
        viewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(TABLE_WIDTH_HINT, SWT.DEFAULT).span(2, 1).create());

        viewer.setContentProvider(ArrayContentProvider.getInstance());
        viewer.setLabelProvider(new RestAPIExtensionLabelProvider());
        filters.stream().forEach(viewer::addFilter);
        viewer.setInput(repositoryStore.getChildren());
        viewer.addDoubleClickListener(new IDoubleClickListener() {

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
        context.bindValue(ViewerProperties.singleSelection().observe(viewer), fileStoreObservable,
                updateValueStrategy().withValidator(selectionValidator()).create(), null);
    }
    
    public void addFilter(ViewerFilter filter) {
        filters.add(filter);
    }

    private IValidator selectionValidator() {
        return new TypedValidator<ExtensionProjectFileStore, IStatus>() {

            @Override
            protected IStatus doValidate(final ExtensionProjectFileStore value) {
                return value == null ? ValidationStatus.error(Messages.emptySelectionErrorMessage) : ValidationStatus.ok();
            }

        };
    }

    protected DataBindingContext getContext() {
        return context;
    }

}
