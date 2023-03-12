/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard.control;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.rest.api.extension.core.repository.DependencyRestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.rest.api.extension.ui.wizard.RestAPIExtensionLabelProvider;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.bonitasoft.studio.ui.wizard.listener.WizardDoubleClickListener;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SelectRestAPIExtensionControlSupplier implements ControlSupplier {

    private static final int TABLE_WIDTH_HINT = 600;
    private final RestAPIExtensionRepositoryStore repositoryStore;
    protected final WidgetFactory widgetFactory;
    private final IObservableValue fileStoreObservable;

    public SelectRestAPIExtensionControlSupplier(final RestAPIExtensionRepositoryStore repositoryStore,
            final WidgetFactory widgetFactory,
            final IObservableValue fileStoreObservable) {
        this.repositoryStore = repositoryStore;
        this.widgetFactory = widgetFactory;
        this.fileStoreObservable = fileStoreObservable;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final TableViewer restApiExtensionViewer = widgetFactory.newTableViewer(mainComposite, "restApiTable");
        restApiExtensionViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(TABLE_WIDTH_HINT, SWT.DEFAULT).span(2, 1)
                        .create());
        restApiExtensionViewer.addFilter(new ViewerFilter() {
            
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return !(element instanceof DependencyRestAPIExtensionFileStore);
            }
        });
        restApiExtensionViewer.setContentProvider(ArrayContentProvider.getInstance());
        restApiExtensionViewer.setLabelProvider(new RestAPIExtensionLabelProvider());
        restApiExtensionViewer.setInput(repositoryStore.getChildren());
        restApiExtensionViewer.addDoubleClickListener(new WizardDoubleClickListener((WizardDialog) wizardContainer));
        ctx.bindValue(ViewersObservables.observeSingleSelection(restApiExtensionViewer), fileStoreObservable,
                updateValueStrategy().withValidator(value -> value == null
                        ? ValidationStatus.error(Messages.emptySelectionErrorMessage) : ValidationStatus.ok()).create(),
                null);
        return mainComposite;
    }

}
