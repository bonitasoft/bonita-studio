/**
 * Copyright (C) 2017 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.la.application.ui.control;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.widgets.CustomStackLayout;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.control.model.AddApplicationMode;
import org.bonitasoft.studio.la.application.ui.control.model.ApplicationDescriptorWithFileName;
import org.bonitasoft.studio.la.application.ui.control.provider.ApplicationDescriptorWithFileNameLabelProvider;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationDescriptorValidators;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationTokenUnicityValidator;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.bonitasoft.studio.ui.wizard.listener.WizardDoubleClickListener;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class AddApplicationDescriptorPage implements ControlSupplier {

    private final RepositoryAccessor repositoryAccessor;
    private final ApplicationFormPage applicationFormPage;

    private SelectObservableValue<AddApplicationMode> addModeObservable;
    private Composite addNewComposite;
    private Composite addFromComposite;
    private TextWidget newTokenTextWidget;
    private TextWidget newDisplayNameTextWidget;
    private TextWidget newVersionTextWidget;
    private TextWidget fromExistingTextWidget;
    private String newToken;
    private String displayName;
    private String version;
    private String newTokenFromExisting;
    private IViewerObservableValue observeSingleSelection;
    private TableViewer viewer;
    private List<ApplicationDescriptorWithFileName> existingApplicationDescriptors;

    public AddApplicationDescriptorPage(ApplicationFormPage applicationFormPage, String newToken, String displayName,
            String version) {
        this.repositoryAccessor = applicationFormPage.getRepositoryAccessor();
        this.applicationFormPage = applicationFormPage;
        this.newToken = newToken;
        this.newTokenFromExisting = newToken;
        this.displayName = displayName;
        this.version = version;

        existingApplicationDescriptors = getExistingApplicationDescriptors();
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer container, DataBindingContext ctx) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());;

        createRadioButtons(composite);
        createStackComposite(composite, ctx, container);

        return composite;
    }

    private void createStackComposite(Composite parent, DataBindingContext ctx, IWizardContainer wizardContainer) {
        final Composite stackComposite = new Composite(parent, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final CustomStackLayout stackLayout = new CustomStackLayout(stackComposite);
        stackComposite.setLayout(stackLayout);

        createAddNewComposite(stackComposite, ctx);
        createAddFromComposite(stackComposite, ctx, wizardContainer);

        ctx.bindValue(PojoProperties.value("topControl").observe(stackLayout), addModeObservable,
                UpdateStrategyFactory.neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(ConverterBuilder.<AddApplicationMode, Composite> newConverter()
                                .fromType(AddApplicationMode.class)
                                .toType(Composite.class)
                                .withConvertFunction(mode -> {
                                    switch (mode) {
                                        case FROM:
                                            return addFromComposite;
                                        case NEW:
                                        default:
                                            return addNewComposite;
                                    }
                                }).create())
                        .create());
        ctx.updateModels();
    }

    private void createAddFromComposite(Composite parent, DataBindingContext ctx, IWizardContainer wizardContainer) {
        addFromComposite = new Composite(parent, SWT.NONE);
        addFromComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        addFromComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        fromExistingTextWidget = new TextWidget.Builder()
                .withLabel(Messages.newApplicationDescriptorToken)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(PojoProperties.value("newTokenFromExisting").observe(this))
                .withTargetToModelStrategy(updateValueStrategy().withValidator(ApplicationDescriptorValidators
                        .addApplicationTokenValidator(
                                new ApplicationTokenUnicityValidator.Builder(repositoryAccessor,
                                        applicationFormPage.getWorkingCopy(), applicationFormPage.getEditorInput().getName())
                                                .create(),
                                AddApplicationMode.FROM, addModeObservable)))
                .inContext(ctx)
                .createIn(addFromComposite);
        observeSingleSelection = ViewerProperties.singleSelection()
                .observe(createTableViewer(addFromComposite, wizardContainer));
        MultiValidator selectionValidator = new MultiValidator() {

            @Override
            protected IStatus validate() {
                if (addModeObservable.getValue().equals(AddApplicationMode.FROM)) {
                    return observeSingleSelection.getValue() == null
                            ? ValidationStatus.error("")
                            : ValidationStatus.ok();
                }
                return ValidationStatus.ok();
            }
        };
        ctx.addValidationStatusProvider(selectionValidator);
    }

    private TableViewer createTableViewer(Composite parent, IWizardContainer wizardContainer) {
        Composite viewerComposite = new Composite(parent, SWT.NONE);
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 0).create());
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label viewerdescription = new Label(viewerComposite, SWT.NONE);
        viewerdescription.setText(Messages.existingApplicationDescriptors);
        viewer = new TableViewer(viewerComposite,
                SWT.SINGLE | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        viewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.setContentProvider(ArrayContentProvider.getInstance());
        viewer.setLabelProvider(new ApplicationDescriptorWithFileNameLabelProvider());
        viewer.setComparator(new ViewerComparator());
        viewer.setInput(existingApplicationDescriptors);
        viewer.addDoubleClickListener(new WizardDoubleClickListener((WizardDialog) wizardContainer));
        return viewer;
    }

    private List<ApplicationDescriptorWithFileName> getExistingApplicationDescriptors() {
        List<ApplicationDescriptorWithFileName> applicationDescriptorList = new ArrayList<>();
        applicationFormPage.getRepositoryAccessor().getRepositoryStore(ApplicationRepositoryStore.class)
                .getChildren().stream()
                .forEach(fileStore -> {
                    try {
                        fileStore.getContent().getApplications().stream()
                                .map(applicationNode -> new ApplicationDescriptorWithFileName(applicationNode,
                                        fileStore.getName()))
                                .forEach(applicationDescriptorList::add);
                    } catch (ReadFileStoreException e) {
                        BonitaStudioLog.debug("Unable to retrieve ApplicationFileStore content", e,
                                LivingApplicationPlugin.PLUGIN_ID);
                    }
                });

        return applicationDescriptorList;
    }

    private void createAddNewComposite(Composite parent, DataBindingContext ctx) {

        addNewComposite = new Composite(parent, SWT.NONE);
        addNewComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        addNewComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        newTokenTextWidget = new TextWidget.Builder()
                .withLabel(Messages.applicationToken)
                .withMessage(Messages.applicationTokenMessage)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("newToken").observe(this))
                .inContext(ctx)
                .withTargetToModelStrategy(updateValueStrategy().withValidator(ApplicationDescriptorValidators
                        .addApplicationTokenValidator(
                                new ApplicationTokenUnicityValidator.Builder(repositoryAccessor,
                                        applicationFormPage.getWorkingCopy(), applicationFormPage.getEditorInput().getName())
                                                .create(),
                                AddApplicationMode.NEW, addModeObservable)))
                .createIn(addNewComposite);

        newDisplayNameTextWidget = new TextWidget.Builder()
                .withLabel(Messages.displayName)
                .withMessage(Messages.displayNameMessage)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("displayName").observe(this))
                .inContext(ctx)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(ApplicationDescriptorValidators
                                .addApplicationDisplayNameValidator(AddApplicationMode.NEW, addModeObservable))
                        .create())
                .createIn(addNewComposite);

        newVersionTextWidget = new TextWidget.Builder()
                .withLabel(Messages.version)
                .labelAbove()
                .widthHint(150)
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("version").observe(this))
                .inContext(ctx)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new customEmptyInputValidator(org.bonitasoft.studio.ui.i18n.Messages.required,
                                addModeObservable))
                        .create())
                .createIn(addNewComposite);
    }

    private void createRadioButtons(Composite parent) {
        Composite radioComposite = new Composite(parent, SWT.NONE);
        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        Button newApplicationDescriptor = new Button(radioComposite, SWT.RADIO);
        newApplicationDescriptor.setText(Messages.addNew);
        newApplicationDescriptor.setLayoutData(GridDataFactory.fillDefaults().create());

        Button fromExistingApplicationDescriptor = new Button(radioComposite, SWT.RADIO);
        fromExistingApplicationDescriptor.setText(Messages.addFromExistingAppDescriptor);
        fromExistingApplicationDescriptor.setLayoutData(GridDataFactory.fillDefaults().create());
        fromExistingApplicationDescriptor.setEnabled(!existingApplicationDescriptors.isEmpty());

        addModeObservable = new SelectObservableValue();
        addModeObservable.addOption(AddApplicationMode.NEW,
                WidgetProperties.buttonSelection().observe(newApplicationDescriptor));
        addModeObservable.addOption(AddApplicationMode.FROM,
                WidgetProperties.buttonSelection().observe(fromExistingApplicationDescriptor));
        addModeObservable.setValue(AddApplicationMode.NEW);
        addModeObservable.addValueChangeListener(e -> modeChanged());
    }

    private void modeChanged() {
        newTokenTextWidget.getValueBinding().validateTargetToModel();
        newDisplayNameTextWidget.getValueBinding().validateTargetToModel();
        newVersionTextWidget.getValueBinding().validateTargetToModel();
        fromExistingTextWidget.getValueBinding().validateTargetToModel();
    }

    public boolean isNew() {
        return addModeObservable.getValue().equals(AddApplicationMode.NEW);
    }

    public ApplicationNode getSelection() {
        return ((ApplicationDescriptorWithFileName) observeSingleSelection.getValue()).getApplicationNode();
    }

    public String getNewToken() {
        return newToken;
    }

    public void setNewToken(String newToken) {
        this.newToken = newToken;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNewTokenFromExisting() {
        return newTokenFromExisting;
    }

    public void setNewTokenFromExisting(String newTokenFromExisting) {
        this.newTokenFromExisting = newTokenFromExisting;
    }

}

class customEmptyInputValidator extends EmptyInputValidator {

    private SelectObservableValue<AddApplicationMode> currentMode;

    protected customEmptyInputValidator(String errorMessage, SelectObservableValue<AddApplicationMode> currentMode) {
        super(errorMessage, IStatus.ERROR);
        this.currentMode = currentMode;
    }

    @Override
    public boolean isValid(Optional<String> value) {
        if (currentMode.getValue().equals(AddApplicationMode.NEW)) {
            return super.isValid(value);
        }
        return true;
    }

}
