/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.wizard.page;

import static org.bonitasoft.studio.common.Messages.bosProductName;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.ui.wizard.ConnectionInfo;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Baptiste Mesta
 */
public class ConnectToRepositoryWizardPage extends WizardPage {

    private final ConnectionInfo connectionInfo;

    public ConnectToRepositoryWizardPage(final ConnectionInfo connectionInfo) {
        super(ConnectToRepositoryWizardPage.class.getName());
        setTitle(Messages.connectToRepository_title);
        setDescription(Messages.bind(Messages.connectToRepository_desc, new Object[] { bosProductName }));
        setImageDescriptor(Pics.getWizban());
        this.connectionInfo = connectionInfo;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final DataBindingContext dataBindingContext = new DataBindingContext();
        WizardPageSupport.create(this, dataBindingContext);

        final Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = null;
        GridData data = null;

        layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginHeight = layout.marginWidth = 0;
        composite.setLayout(layout);

        final Label urlLabel = new Label(composite, SWT.NONE);
        urlLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        urlLabel.setText(Messages.url);

        final Composite select = new Composite(composite, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        select.setLayout(layout);
        data = new GridData(GridData.FILL_HORIZONTAL);
        select.setLayoutData(data);

        final Combo urlCombo = new Combo(select, SWT.NULL);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        urlCombo.setLayoutData(data);
        urlCombo.setItems(connectionInfo.getURLHistory());

        dataBindingContext.bindValue(SWTObservables.observeText(urlCombo)
                , PojoObservables.observeValue(connectionInfo, "url"),
                updateValueStrategy().withValidator(mandatoryValidator(Messages.url)).create(),
                null);

        final Label nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setText(Messages.user_name);
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        final Combo nameCombo = new Combo(composite, SWT.BORDER);
        nameCombo.setItems(connectionInfo.getUsernameHistory());
        data = GridDataFactory.swtDefaults().create();
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH;
        nameCombo.setLayoutData(data);

        dataBindingContext.bindValue(SWTObservables.observeText(nameCombo)
                , PojoObservables.observeValue(connectionInfo, "username"));

        final Label passwordLabel = new Label(composite, SWT.NONE);
        passwordLabel.setText(Messages.password);
        passwordLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Text passwordText = new Text(composite, SWT.PASSWORD | SWT.BORDER);
        data = GridDataFactory.swtDefaults().create();
        data.widthHint = IDialogConstants.ENTRY_FIELD_WIDTH + 100;
        passwordText.setLayoutData(data);

        dataBindingContext.bindValue(SWTObservables.observeText(passwordText, SWT.Modify)
                , PojoObservables.observeValue(connectionInfo, "password"));

        new Label(composite, SWT.NONE);

        final Button savePasswordCheckbox = new Button(composite, SWT.CHECK);
        savePasswordCheckbox.setText(Messages.save_password);
        savePasswordCheckbox.setLayoutData(GridDataFactory.swtDefaults().create());

        dataBindingContext.bindValue(SWTObservables.observeSelection(savePasswordCheckbox)
                , PojoObservables.observeValue(connectionInfo, "savePassword"));

        setControl(composite);
    }
}
