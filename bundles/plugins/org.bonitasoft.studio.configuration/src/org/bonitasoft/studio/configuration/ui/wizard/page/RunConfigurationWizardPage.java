/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import java.util.List;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 */
public class RunConfigurationWizardPage extends WizardPage implements IProcessConfigurationWizardPage {

    private static String GET_USERS_COMMAND = "org.bonitasoft.studio.actors.command.getUsers";

    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private TextWidget textWidget;
    private IValidator<String> userNameValidator;
    private CommandExecutor commandExecutor = new CommandExecutor();
    protected List<String> users;
    private SimpleContentProposalProvider userProposalProvider;

    public RunConfigurationWizardPage() {
        super(RunConfigurationWizardPage.class.getName());
        setTitle(Messages.runConfigurationTitle);
        setDescription(Messages.runConfigurationDesc);
        userNameValidator = createUserNameValidator();
        userProposalProvider = new SimpleContentProposalProvider();
        userProposalProvider.setFiltering(true);
    }

    protected IValidator<String> createUserNameValidator() {
        return name -> name == null || name.isEmpty()
                ? ValidationStatus.warning(Messages.usernameIsMissingForConfiguration)
                : users != null && !users.contains(name)
                        ? ValidationStatus.error(String.format(Messages.unknownUser, name))
                        : ValidationStatus.ok();
    }

    @Override
    public void createControl(Composite parent) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        Label loginHint = new Label(mainComposite, SWT.WRAP);
        loginHint.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        loginHint.setText(Messages.runLoginMessage);

        textWidget = new TextWidget.Builder()
                .labelAbove()
                .withLabel(Messages.authenticatedUser)
                .fill()
                .grabHorizontalSpace()
                .withProposalProvider(userProposalProvider)
                .createIn(mainComposite);

        setControl(mainComposite);
    }

    @Override
    public void updatePage(AbstractProcess process, Configuration configuration) {
        if (context != null) {
            context.dispose();
        }
        if (pageSupport != null) {
            pageSupport.dispose();
        }
        context = new EMFDataBindingContext();

        UpdateValueStrategy targetStrategy = new UpdateValueStrategy();
        targetStrategy.setBeforeSetValidator(userNameValidator);
        context.bindValue(textWidget.observeText(SWT.Modify),
                EMFObservables.observeValue(configuration, ConfigurationPackage.Literals.CONFIGURATION__USERNAME),
                targetStrategy, null);
        users = (List<String>) commandExecutor.executeCommand(GET_USERS_COMMAND, null);
        userProposalProvider.setProposals(users.toArray(new String[users.size()]));
        pageSupport = WizardPageSupport.create(this, context);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
        if (pageSupport != null) {
            pageSupport.dispose();
        }
    }

    @Override
    public String isConfigurationPageValid(Configuration conf) {
        if (conf != null) {
            IStatus status = userNameValidator.validate(conf.getUsername());
            if (status.getSeverity() != IStatus.OK) {
                return status.getMessage();
            }
        }
        return null;
    }

    @Override
    public Image getConfigurationImage() {
        return Pics.getImage("run.png", ConfigurationPlugin.getDefault());
    }

    @Override
    public boolean isDefault() {
        return false;
    }

}
