/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.wizard;

import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.forbiddenCharactersValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.ui.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.wizard.control.StringListViewer;
import org.bonitasoft.studio.maven.ui.wizard.validator.AllJavaIdentifierValidator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.rest.api.extension.core.RestAPIAddressResolver;
import org.bonitasoft.studio.rest.api.extension.core.validation.UniquePathTemplateValidator;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class NewRestAPIProjectAdvancedConfigurationPage extends WizardPage {

    private static final int WIDTH_HINT = 400;
    private final RestAPIExtensionArchetypeConfiguration configuration;
    private final WidgetFactory widgetFactory;
    private final ExtensionRepositoryStore repositoryStore;
    private final RestAPIAddressResolver restAPIAddressResolver;

    public NewRestAPIProjectAdvancedConfigurationPage(final WidgetFactory widgetFactory,
            final RestAPIExtensionArchetypeConfiguration configuration,
            final ExtensionRepositoryStore repositoryStore,
            RestAPIAddressResolver restAPIAddressResolver) {
        super(NewRestAPIProjectAdvancedConfigurationPage.class.getName());
        this.configuration = configuration;
        this.repositoryStore = repositoryStore;
        this.widgetFactory = widgetFactory;
        this.restAPIAddressResolver = restAPIAddressResolver;
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

        final DataBindingContext context = new DataBindingContext();
        WizardPageSupport.create(this, context);

        createPathTemplateControl(mainComposite, context);
        createBDMDependencyOptionControl(mainComposite, context);
        createPermissionsControl(mainComposite, context);

        setControl(mainComposite);
    }

    private void createPermissionsControl(final Composite mainComposite, final DataBindingContext context) {
        final Label description = widgetFactory.newLabel(mainComposite, Messages.permissionsDesciption, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(15, 5)
                .hint(WIDTH_HINT, SWT.DEFAULT).span(2, 1).create());

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(3, 3).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.FILL).grab(false, true).create());

        final Button addButton = widgetFactory.newButton(buttonComposite, Messages.add);
        final Button removeButton = widgetFactory.newButton(buttonComposite, Messages.remove);

        final IObservableList input = PojoProperties.list("permissions").observe(configuration);
        final StringListViewer permissionViewer = new StringListViewer(mainComposite, Messages.permissions, "permission",
                widgetFactory);
        permissionViewer.setInput(input);
        context.addValidationStatusProvider(notEmptyPermissionsValidator(input));
        context.addValidationStatusProvider(new AllJavaIdentifierValidator(input));
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addPermission(input, permissionViewer);
            }

        });
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                input.removeAll(permissionViewer.getSelection());
            }
        });
        final TableViewer viewer = permissionViewer.getViewer();
        context.bindValue(WidgetProperties.enabled().observe(removeButton),
                ViewerProperties.singleSelection().observe(viewer), null,
                updateValueStrategy().withConverter(convertToEnablement()).create());
    }

    private IConverter convertToEnablement() {
        return new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                return fromObject != null;
            }
        };
    }

    protected MultiValidator notEmptyPermissionsValidator(final IObservableList input) {
        return new org.eclipse.core.databinding.validation.MultiValidator() {

            @Override
            protected IStatus validate() {
                if (input.isEmpty()) {
                    return ValidationStatus.error(Messages.emptyPermissionsError);
                }
                return ValidationStatus.ok();
            }
        };
    }

    private void addPermission(final IObservableList input, final StringListViewer permissionViewer) {
        final String[] existingPermissions = (String[]) input.toArray(new String[input.size()]);
        final String newName = NamingUtils.generateNewName(Set.of(existingPermissions), "newPermission", 1);
        input.add(newName);
        permissionViewer.editElement(newName);
    }

    private void createPathTemplateControl(final Composite mainComposite, final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, Messages.pathTemplate);
        final Text pathTemplateText = widgetFactory.newText(mainComposite);
        pathTemplateText.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                "org.bonitasoft.studio.rest.api.extension.ui.wizard.pathTemplateText");
        widgetFactory.createHintDecorator(pathTemplateText, SWT.LEFT, Messages.pathTemplateHint);

        widgetFactory.newLabel(mainComposite, Messages.address);
        final CLabel targetURL = new CLabel(mainComposite, SWT.NONE);
        targetURL.setText(restAPIAddressResolver.getAddress(configuration.getPathTemplate()));
        targetURL.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(WIDTH_HINT, SWT.DEFAULT).indent(10, 0).create());

        final ISWTObservableValue templateTextObservable = WidgetProperties.text(SWT.Modify).observe(pathTemplateText);
        context.bindValue(templateTextObservable,
                PojoProperties.value("pathTemplate", String.class).observe(configuration),
                updateValueStrategy().withValidator(multiValidator()
                        .addValidator(mandatoryValidator(Messages.pathTemplate))
                        .addValidator(forbiddenCharactersValidator(Messages.pathTemplate, '#', '%', '$', ' '))
                        .addValidator(new UniquePathTemplateValidator(repositoryStore))).create(),
                null);
        templateTextObservable.addValueChangeListener(event -> {
            final String value = event.diff.getNewValue().toString();
            targetURL.setText(restAPIAddressResolver.getAddress(configuration.getPathTemplate()));
            mainComposite.layout();
        });
    }

    private void createBDMDependencyOptionControl(final Composite mainComposite, final DataBindingContext context) {
        widgetFactory.newLabel(mainComposite, "");
        final Button bdmOptionButton = widgetFactory.newCheckbox(mainComposite, Messages.addBDMDependencies);
        bdmOptionButton.setEnabled(configuration.isEnableBDMDependencies());
        context.bindValue(org.eclipse.jface.databinding.swt.typed.WidgetProperties.buttonSelection().observe(bdmOptionButton),
                PojoProperties.value("enableBDMDependencies", Boolean.class).observe(configuration));
        final ControlDecoration decoration = new ControlDecoration(bdmOptionButton, SWT.RIGHT);
        decoration.setMarginWidth(0);
        decoration.setDescriptionText(Messages.bdmDependenciesHint);
        decoration.setImage(Pics.getImage(PicsConstants.hint));
        decoration.show();
    }

}
