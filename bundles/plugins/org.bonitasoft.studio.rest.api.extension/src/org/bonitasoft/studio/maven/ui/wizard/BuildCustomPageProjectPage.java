/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.pathValidator;

import java.io.File;

import org.bonitasoft.studio.common.jface.databinding.validator.TypedValidator;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.rest.api.extension.ui.wizard.RestAPIExtensionLabelProvider;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckable;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class BuildCustomPageProjectPage extends WizardPage {

    private static final int TABLE_WIDTH_HINT = 700;

    private CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore;
    private DataBindingContext context;
    private IObservableValue<String> locationObservable;
    private IObservableSet<CustomPageProjectFileStore> selectedFileStores;

    protected WidgetFactory widgetFactory;

    public BuildCustomPageProjectPage(CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore,
            WidgetFactory widgetFactory, IObservableValue<String> locationObservable,
            IObservableSet<CustomPageProjectFileStore> selectedFileStores) {
        super(BuildCustomPageProjectPage.class.getName());
        this.repositoryStore = repositoryStore;
        this.widgetFactory = widgetFactory;
        this.locationObservable = locationObservable;
        this.selectedFileStores = selectedFileStores;
    }

    @Override
    public void createControl(final Composite parent) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());

        context = new DataBindingContext();

        createSelectButtonComposite(mainComposite);
        createViewer(mainComposite, context);
        new Label(mainComposite, SWT.NONE); // filler
        createLocationComposite(mainComposite, context);

        WizardPageSupport.create(this, context);
        setControl(mainComposite);
    }

    private void createViewer(Composite parent, DataBindingContext ctx) {
        CheckboxTableViewer viewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER);
        viewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().hint(TABLE_WIDTH_HINT, SWT.DEFAULT).grab(true, true).create());
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new RestAPIExtensionLabelProvider());
        viewer.setInput(repositoryStore.getChildren());
        ctx.bindSet(selectedFileStores,
                ViewerProperties.checkedElements(CustomPageProjectFileStore.class).observe((ICheckable) viewer));
        ctx.addValidationStatusProvider(new MultiValidator() {

            @Override
            protected IStatus validate() {
                return selectedFileStores.isEmpty()
                        ? ValidationStatus.error(Messages.emptySelectionErrorMessage)
                        : ValidationStatus.ok();
            }
        });
    }

    private void createSelectButtonComposite(Composite parent) {
        Composite buttonComposite = new Composite(parent, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        new ButtonWidget.Builder()
                .withLabel(org.bonitasoft.studio.application.i18n.Messages.selectAll)
                .fill()
                .onClick(e -> selectedFileStores.addAll(repositoryStore.getChildren()))
                .createIn(buttonComposite);

        new ButtonWidget.Builder()
                .withLabel(org.bonitasoft.studio.application.i18n.Messages.selectNone)
                .fill()
                .onClick(e -> selectedFileStores.clear())
                .createIn(buttonComposite);
    }

    private void createLocationComposite(Composite mainComposite, DataBindingContext context) {
        Composite locationComposite = new Composite(mainComposite, SWT.NONE);
        locationComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).create());
        locationComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        widgetFactory.newLabel(locationComposite, Messages.targetLocation);
        Text locationText = widgetFactory.newText(locationComposite);
        context.bindValue(WidgetProperties.text(SWT.Modify).observe(locationText),
                locationObservable,
                UpdateStrategyFactory.updateValueStrategy().withValidator(pathValidator(Messages.targetLocation)).create(),
                null);

        Button browseButton = new Button(locationComposite, SWT.PUSH);
        browseButton.setText(Messages.browse);
        browseButton
                .setLayoutData(GridDataFactory.fillDefaults().minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        browseButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                handleDestinationBrowseButtonPressed();
            }

        });
    }

    protected void handleDestinationBrowseButtonPressed() {
        final DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(), SWT.SAVE | SWT.SHEET);
        final String currentSourceString = locationObservable.getValue();
        final int lastSeparatorIndex = currentSourceString.lastIndexOf(File.separator);
        if (lastSeparatorIndex != -1) {
            dialog.setFilterPath(currentSourceString.substring(0,
                    lastSeparatorIndex));
        }
        final String selectedFileName = dialog.open();
        if (selectedFileName != null) {
            locationObservable.setValue(null);
            locationObservable.setValue(selectedFileName);
        }
    }

    public IObservableSet<CustomPageProjectFileStore> observeSelectedFileStore() {
        return selectedFileStores;
    }

    private IValidator selectionValidator() {
        return new TypedValidator<CustomPageProjectFileStore, IStatus>() {

            @Override
            protected IStatus doValidate(final CustomPageProjectFileStore value) {
                return value == null ? ValidationStatus.error(Messages.emptySelectionErrorMessage) : ValidationStatus.ok();
            }

        };
    }

}
