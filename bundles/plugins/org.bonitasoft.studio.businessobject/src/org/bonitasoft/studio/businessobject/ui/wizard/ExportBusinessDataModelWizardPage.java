/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.io.File;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 * 
 */
public class ExportBusinessDataModelWizardPage extends WizardPage {

    private String destinationPath;

    protected ExportBusinessDataModelWizardPage(
            BusinessObjectModelRepositoryStore businessObjectDefinitionStore) {
        super(ExportBusinessDataModelWizardPage.class.getName());
        destinationPath = System.getProperty("user.home");
    }

    public void createControl(Composite parent) {
        EMFDataBindingContext context = new EMFDataBindingContext();
        final Composite mainComposite = createMainComposite(parent);
        createDestinationTextAndLabel(mainComposite, context);
        WizardPageSupport.create(this, context);
        setControl(mainComposite);
    }

    protected Composite createMainComposite(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        mainComposite.setLayoutData(GridDataFactory.swtDefaults().grab(true, true).create());
        return mainComposite;
    }

    protected void createDestinationTextAndLabel(Composite mainComposite,
            DataBindingContext context) {
        final Composite composite = new Composite(mainComposite, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label destinationLabel = new Label(composite, SWT.NONE);
        destinationLabel.setText(Messages.destinationPath + " *");
        destinationLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).grab(false, false).create());

        final Text destinationText = new Text(composite, SWT.BORDER);
        destinationText.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());

        UpdateValueStrategy targetToModelStrategy = new UpdateValueStrategy();
        targetToModelStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.destinationPath));
        targetToModelStrategy.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object value) {
                return validateFilePath(value);
            }
        });
        context.bindValue(SWTObservables.observeText(destinationText, SWT.Modify), PojoObservables.observeValue(this, "destinationPath"),
                targetToModelStrategy, null);

        final Button browseButton = new Button(composite, SWT.PUSH);
        browseButton.setText(Messages.browse);
        browseButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).create());
        browseButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                DirectoryDialog directoryDialog = new DirectoryDialog(Display.getDefault().getActiveShell(), SWT.SAVE);
                String path = directoryDialog.open();
                if (path != null) {
                    destinationText.setText(path);
                }
            }
        });

    }

    protected IStatus validateFilePath(Object value) {
        if (value instanceof String) {
            File file = new File(value.toString());
            if (file.exists() && file.isDirectory()) {
                return ValidationStatus.ok();
            }
        }
        return ValidationStatus.error(Messages.invalidDestinationPath);
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

}
