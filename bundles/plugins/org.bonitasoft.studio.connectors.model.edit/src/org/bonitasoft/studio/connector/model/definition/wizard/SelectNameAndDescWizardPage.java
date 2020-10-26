/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.model.definition.wizard;

import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class SelectNameAndDescWizardPage extends WizardPage implements IWizardPage {


    protected final Connector connector;
    protected final Set<EStructuralFeature> featureToCheckForUniqueID;
    protected final EObject container;
    private EMFDataBindingContext context;
    private final Connector originalConnector;

    public SelectNameAndDescWizardPage(EObject container , Connector connectorWorkingCopy,Connector originalConnector,Set<EStructuralFeature> featureToCheckForUniqueID) {
        super(SelectNameAndDescWizardPage.class.getName());
        setTitle(Messages.specifyName_wizardTitle);
        setDescription(Messages.specifyName_wizardDesc);
        connector = connectorWorkingCopy;
        this.originalConnector  = originalConnector ;
        this.featureToCheckForUniqueID = featureToCheckForUniqueID ;
        this.container = container ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        context = new EMFDataBindingContext() ;
        Composite composite = doCreateControl(parent,context);
        WizardPageSupport.create(this, context) ;
        setControl(composite);
    }

    @Override
    public void dispose() {
        super.dispose();
        if(context != null){
            context.dispose() ;
        }
    }

    /**
     * @param parent
     * @param context
     * @return
     */
    protected Composite doCreateControl(Composite parent, EMFDataBindingContext context) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        Label nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        nameLabel.setText(Messages.dataNameLabel);

        final Text nameText = new Text(composite, SWT.BORDER);
        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());

        Label descLabel = new Label(composite, SWT.NONE);
        descLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());
        descLabel.setText(Messages.dataDescriptionLabel);

        final Text descText = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP);
        descText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).hint(SWT.DEFAULT, 50).create());

        UpdateValueStrategy nameStrategy = new UpdateValueStrategy() ;
        nameStrategy.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object value) {
                if(value == null || value.toString().isEmpty()){
                    return ValidationStatus.error(Messages.nameIsEmpty) ;
                }
                if(container != null){
                    for(EStructuralFeature feature : featureToCheckForUniqueID){
                        List<?> otherConnectors = (List<?>) container.eGet(feature) ;
                        for(Object c : otherConnectors){
                            if(!c.equals(originalConnector) && ((Connector) c).getName().equals(value)){
                                return  ValidationStatus.error(Messages.nameAlreadyExists) ;
                            }
                        }
                    }
                }
                return Status.OK_STATUS;
            }
        }) ;


        context.bindValue(SWTObservables.observeText(nameText, SWT.Modify), EMFObservables.observeValue(connector, ProcessPackage.Literals.ELEMENT__NAME),nameStrategy,null);
        UpdateValueStrategy descStrategy = new UpdateValueStrategy();
        descStrategy.setBeforeSetValidator(new InputLengthValidator(Messages.dataDescriptionLabel, 255));
        context.bindValue(SWTObservables.observeText(descText, SWT.Modify), EMFObservables.observeValue(connector, ProcessPackage.Literals.ELEMENT__DOCUMENTATION),descStrategy,null) ;


        return composite;
    }

    @Override
    public IWizardPage getPreviousPage() {
        IWizard wizard = getWizard();
        if(wizard != null){
            return wizard.getPreviousPage(this);
        }
        return super.getPreviousPage();
    }

}
