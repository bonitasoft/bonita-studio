/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import java.util.Arrays;

import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.operation.BuildAndExportCustomPageOperation;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.handler.CustomPageProjectSelectionProvider;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;

public abstract class BuildCustomPageWizard extends Wizard {

    private static final String LAST_TARGET_LOCATION_SETTING = "LAST_TARGET_LOCATION_SETTING";
    private final CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore;
    private final WidgetFactory widgetFactory;
    private final IObservableSet<CustomPageProjectFileStore> selectedFileStoreObservable;
    private final IObservableValue<String> locationObservable;
    private final DialogSettingsHelper dialogSettingsHelper;

    public BuildCustomPageWizard(CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore,
            WidgetFactory widgetFactory,
            CustomPageProjectSelectionProvider selectionProvider) {
        this.repositoryStore = repositoryStore;
        this.widgetFactory = widgetFactory;
        selectedFileStoreObservable = new WritableSet<>(Arrays.asList(selectionProvider.getSelection()),
                CustomPageProjectFileStore.class);
        locationObservable = new WritableValue<>();
        dialogSettingsHelper = new DialogSettingsHelper(BuildCustomPageWizard.class.getName());
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.buildWizardTitle);
    }

    @Override
    public void addPages() {
        locationObservable
                .setValue(dialogSettingsHelper.get(LAST_TARGET_LOCATION_SETTING, System.getProperty("user.home")));
        final BuildCustomPageProjectPage selectionPage = new BuildCustomPageProjectPage(repositoryStore, widgetFactory,
                locationObservable, selectedFileStoreObservable);
        selectionPage.setTitle(getSelectionPageTitle());
        selectionPage.setDescription(getSelectionPageDescription());
        addPage(selectionPage);
    }

    protected abstract String getSelectionPageDescription();

    protected abstract String getSelectionPageTitle();

    @Override
    public boolean performFinish() {
        dialogSettingsHelper.put(LAST_TARGET_LOCATION_SETTING, locationObservable.getValue());
        BuildAndExportCustomPageOperation operation = new BuildAndExportCustomPageOperation();
        operation.run(selectedFileStoreObservable, locationObservable.getValue(), getContainer());
        MultiStatus status = operation.getStatus();
        switch (status.getSeverity()) {
            case ValidationStatus.CANCEL:
                openDialog(Messages.buildCancelTitle, Messages.buildCancelMessage, status);
                break;
            case ValidationStatus.OK:
            case ValidationStatus.INFO:
                openDialog(Messages.buildSuccess, String.format(Messages.buildSuccessMessage, locationObservable.getValue()),
                        status);
                break;
            default:
                openDialog(Messages.buildFailure, Messages.buildFailureMessage, status);
                break;
        }
        return status.isOK();
    }

    private void openDialog(String title, String message, MultiStatus status) {
        new MultiStatusDialog(getShell(),
                title,
                message,
                new String[] { IDialogConstants.OK_LABEL },
                status).open();
    }

}
