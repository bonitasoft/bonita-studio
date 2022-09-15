package org.bonitasoft.studio.configuration.ui.dialog;

import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog extending the {@link InputDialog} to have a checkbox allowing to duplicate or not an environment specific.
 */
public class NewEnvironmentDialog extends InputDialog {

    private String selectedEnv = "Local";
    private boolean duplicateEnv = false;

    public NewEnvironmentDialog(Shell activeShell, String createEnvironmentTitle, String name, String initialValue,
            IInputValidator validator) {
        super(activeShell, createEnvironmentTitle, name, initialValue, validator);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        var dbc = new DataBindingContext();
        var composite = (Composite) super.createDialogArea(parent);
        var duplicateEnvComposite = new Composite(composite, SWT.NONE);
        duplicateEnvComposite.setLayout(new GridLayout(2, false));
        var duplicateCheckBox = new Button(duplicateEnvComposite, SWT.CHECK);
        duplicateCheckBox.setText(Messages.duplicateEnvironmentDesc);

        var envStore = RepositoryManager.getInstance().getRepositoryStore(EnvironmentRepositoryStore.class);

        var comboWidget = new ComboWidget.Builder()
                .withItems(envStore.getChildren().stream().map(IDisplayable::toDisplayName).filter(Optional::isPresent)
                        .map(Optional::get).toArray(String[]::new))
                .readOnly()
                .bindTo(PojoProperties.value("selectedEnv", String.class).observe(this))
                .inContext(dbc)
                .createIn(duplicateEnvComposite);

        var observableCheckBox = WidgetProperties.buttonSelection().observe(duplicateCheckBox);

        dbc.bindValue(observableCheckBox,
                WidgetProperties.enabled().observe(comboWidget.getCombo()));
        dbc.bindValue(observableCheckBox, PojoProperties.value("duplicateEnv", boolean.class).observe(this));

        return composite;
    }

    public String getSelectedEnv() {
        return selectedEnv;
    }

    public void setSelectedEnv(String selectedEnv) {
        this.selectedEnv = selectedEnv;
    }

    public boolean isDuplicateEnv() {
        return duplicateEnv;
    }

    public void setDuplicateEnv(boolean duplicateEnv) {
        this.duplicateEnv = duplicateEnv;
    }

}
