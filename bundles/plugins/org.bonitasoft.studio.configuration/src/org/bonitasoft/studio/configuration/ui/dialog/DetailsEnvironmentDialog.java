package org.bonitasoft.studio.configuration.ui.dialog;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.databinding.validator.FileNameValidator;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.common.ui.jface.databinding.DialogSupport;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.validator.LengthValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class DetailsEnvironmentDialog extends Dialog {

    private static final int MAX_NAME_LENGTH = 80;
    private String nameEnv;
    private String descEnv;
    private Environment env;
    private DataBindingContext dbc;
    private IObservableValue<Boolean> notLocalEnvObservable;

    public DetailsEnvironmentDialog(Shell shell, Environment env) {
        super(shell);
        this.env = env;
        this.nameEnv = env.getName();
        this.descEnv = Objects.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURATION, env.getName())
                ? Messages.localEnvironmentDesc : env.getDescription();
    }

    @Override
    protected Control createContents(Composite parent) {
        Control control = super.createContents(parent);
        DialogSupport.create(this, dbc);
        return control;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        var composite = (Composite) super.createDialogArea(parent);
        dbc = new DataBindingContext();

        composite.setLayout(GridLayoutFactory.swtDefaults().margins(20, 20).create());

        notLocalEnvObservable = new WritableValue<>(
                !Objects.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURATION, env.getName()), Boolean.class);
        var nameObservable = PojoProperties.value("nameEnv", String.class).observe(this);
        var descriptionObservable = PojoProperties.value("descEnv", String.class).observe(this);
        var changeValidationStatusProvider = new org.eclipse.core.databinding.validation.MultiValidator() {

            @Override
            protected IStatus validate() {
                return Objects.equals(nameObservable.getValue(), env.getName())
                        && Objects.equals(descriptionObservable.getValue(), env.getDescription())
                                ? ValidationStatus.error("No changes")
                                : ValidationStatus.ok();
            }
        };
        dbc.addValidationStatusProvider(changeValidationStatusProvider);

        var nameWidget = new TextWidget.Builder()
                .withId(SWTBotConstants.SWTBOT_ID_ENV_NAME)
                .labelAbove()
                .withLabel(Messages.name)
                .widthHint(500)
                .grabHorizontalSpace()
                .fill()
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(new FileNameValidator(Messages.name),
                                        existingEnvValidator(RepositoryManager.getInstance()
                                                .getRepositoryStore(EnvironmentRepositoryStore.class)),
                                        new EmptyInputValidator.Builder()
                                                .withMessage(org.bonitasoft.studio.ui.i18n.Messages.required)
                                                .create(),
                                        new LengthValidator.Builder().maxLength(MAX_NAME_LENGTH)
                                                .withMessage(String.format(Messages.maxNameLength, MAX_NAME_LENGTH))
                                                .create())
                                .create())
                        .withConverter(IConverter.<String, String> create(text -> text != null ? text.trim() : null)))
                .bindTo(nameObservable)
                .inContext(dbc)
                .createIn(composite);

        var descriptionWidget = new TextAreaWidget.Builder()
                .withId(SWTBotConstants.SWTBOT_ID_ENV_DESC)
                .labelAbove()
                .useNativeRender()
                .withLabel(Messages.descripiton)
                .heightHint(100)
                .widthHint(500)
                .grabVerticalSpace()
                .grabHorizontalSpace()
                .fill()
                .bindTo(descriptionObservable)
                .inContext(dbc)
                .createIn(composite);

        dbc.bindValue(WidgetProperties.editable().observe(nameWidget.getTextControl()), notLocalEnvObservable);
        dbc.bindValue(WidgetProperties.editable().observe(descriptionWidget.getTextControl()), notLocalEnvObservable);

        return composite;
    }

    /**
     * Validate if there is only 1 environment with the same name in the current existing environments.
     * 
     * @return A {@link IStatus}
     */
    IValidator<String> existingEnvValidator(EnvironmentRepositoryStore environmentRepositoryStore) {
        return envName -> (environmentRepositoryStore.getChild(envName + ".xml", false) == null
                && !existsInDifferentCase(envName, environmentRepositoryStore))
                || Objects.equals(envName, env.getName()) ? ValidationStatus.ok()
                        : ValidationStatus.error(Messages.alreadyExists);
    }

    private boolean existsInDifferentCase(String envName, EnvironmentRepositoryStore environmentRepositoryStore) {
        return environmentRepositoryStore.getChildren().stream()
                .map(IDisplayable::toDisplayName).filter(Optional::isPresent).map(Optional::get)
                .map(String::toLowerCase)
                .anyMatch(envName::equalsIgnoreCase);
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        if (Boolean.TRUE.equals(notLocalEnvObservable.getValue())) {
            createButton(parent, IDialogConstants.OK_ID, Messages.modify, true);
        }
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CLOSE_LABEL, false);
    }

    @Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        if (this.nameEnv != null) {
            shell.setText(String.format(Messages.detailsEnvironment, nameEnv));
        }
    }

    public String getNameEnv() {
        return nameEnv;
    }

    public void setNameEnv(String nameEnv) {
        this.nameEnv = nameEnv;
    }

    public String getDescEnv() {
        return descEnv;
    }

    public void setDescEnv(String descEnv) {
        this.descEnv = descEnv;
    }

}
