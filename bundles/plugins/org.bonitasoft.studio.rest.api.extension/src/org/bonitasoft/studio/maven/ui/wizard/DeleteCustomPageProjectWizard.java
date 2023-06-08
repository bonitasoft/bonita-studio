/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.common.ui.jface.FileActionDialog;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.handler.CustomPageProjectSelectionProvider;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.Wizard;

public abstract class DeleteCustomPageProjectWizard extends Wizard {

    private final IObservableValue<ExtensionProjectFileStore> fileStoreObservable;
    private final ExtensionRepositoryStore repositoryStore;
    private final WidgetFactory widgetFactory;

    public DeleteCustomPageProjectWizard(
            final ExtensionRepositoryStore repositoryStore,
            final WidgetFactory widgetFactory,
            final CustomPageProjectSelectionProvider selectionProvider) {
        this.repositoryStore = repositoryStore;
        this.widgetFactory = widgetFactory;
        fileStoreObservable = new WritableValue<ExtensionProjectFileStore>(selectionProvider.getSelection(),
                ExtensionProjectFileStore.class);
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.delete);
    }

    @Override
    public void addPages() {
        super.addPages();
        final SelectCustomPageProjectPage selectionPage = new SelectCustomPageProjectPage(repositoryStore,
                widgetFactory, fileStoreObservable);
        selectionPage.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return element instanceof ExtensionProjectFileStore
                        && !((ExtensionProjectFileStore) element).isReadOnly();
            }
        });
        selectionPage.setTitle(getSelectionPageTitle());
        selectionPage.setDescription(getSelectionPageDeleteDescription());
        addPage(selectionPage);
    }

    protected abstract String getSelectionPageDeleteDescription();

    protected abstract String getSelectionPageTitle();

    @Override
    public boolean performFinish() {
        final ExtensionProjectFileStore fileStore = fileStoreObservable.getValue();
        if (FileActionDialog.confirmDeletionQuestion(IDisplayable.toDisplayName(fileStore).orElse(""))) {
            fileStore.delete();
            return true;
        }
        return false;
    }

}
