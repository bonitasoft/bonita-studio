/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class RunConfigurationWizardPage extends WizardPage implements IProcessConfigurationWizardPage {

    private EMFDataBindingContext context;
    private Text usernameText;
    private Text passwordText;
    private Text anonymousUsernameText;
    private Text anonymousPasswordText;
    private AbstractProcess process;
    
    
    private final IValidator userNameValidator = new IValidator() {

        @Override
        public IStatus validate(Object input) {
            if(input == null || input.toString().isEmpty()){
                return ValidationStatus.warning(Messages.usernameIsMissingForConfiguration);
            }
            return Status.OK_STATUS;
        }
    };
    
    
    private WizardPageSupport pageSupport;

    public RunConfigurationWizardPage() {
        super(RunConfigurationWizardPage.class.getName());
        setTitle(Messages.runConfigurationTitle) ;
        setDescription(Messages.runConfigurationDesc) ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
    	
        final Composite mainComposite = new Composite(parent,SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;

        // Authentification user / login by default
        final Composite loginComposite = new Composite(mainComposite,SWT.NONE) ;
        loginComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        loginComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create()) ;

        final Label authenticatedUser= new Label(loginComposite, SWT.NONE);
        authenticatedUser.setText(Messages.authenticatedUser);
        authenticatedUser.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        authenticatedUser.setFont(BonitaStudioFontRegistry.getActiveFont());
        
        final Label loginHint = new Label(loginComposite, SWT.WRAP) ;
        loginHint.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create()) ;
        loginHint.setText(Messages.runLoginMessage) ;

        final Label usernameLabel = new Label(loginComposite, SWT.NONE) ;
        usernameLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create()) ;
        usernameLabel.setText(Messages.username) ;

        usernameText = new Text(loginComposite, SWT.BORDER) ;
        usernameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        final Label passwordLabel = new Label(loginComposite, SWT.NONE) ;
        passwordLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create()) ;
        passwordLabel.setText(Messages.password) ;

        passwordText = new Text(loginComposite, SWT.BORDER | SWT.PASSWORD) ;
        passwordText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        setControl(mainComposite) ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#updatePage(org.bonitasoft.studio.model.process.AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public void updatePage(AbstractProcess process, Configuration configuration) {
    	this.process=process;
        if(context != null){
            context.dispose() ;
        }
        if(pageSupport != null){
            pageSupport.dispose() ;
        }
        context = new EMFDataBindingContext() ;
        UpdateValueStrategy targetStrategy = new UpdateValueStrategy() ;
        targetStrategy.setBeforeSetValidator(userNameValidator) ;
        context.bindValue(SWTObservables.observeText(usernameText, SWT.Modify), EMFObservables.observeValue(configuration, ConfigurationPackage.Literals.CONFIGURATION__USERNAME),targetStrategy,null) ;
        context.bindValue(SWTObservables.observeText(passwordText, SWT.Modify), EMFObservables.observeValue(configuration, ConfigurationPackage.Literals.CONFIGURATION__PASSWORD)) ;

        pageSupport = WizardPageSupport.create(this, context) ;

    }

    @Override
    public void dispose() {
        super.dispose();
        if(context != null){
            context.dispose() ;
        }
        if(pageSupport != null){
            pageSupport.dispose() ;
        }
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#isConfigurationPageValid()
     */
    @Override
    public String isConfigurationPageValid(Configuration conf) {
        if(conf != null){
            IStatus status =  userNameValidator.validate(conf.getUsername()) ;
            if(status.getSeverity() != IStatus.OK){
                return status.getMessage() ;
            }
        }
        
        return null;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#getConfigurationImage()
     */
    @Override
    public Image getConfigurationImage() {
        return Pics.getImage("run.png",ConfigurationPlugin.getDefault());
    }

    @Override
    public boolean isDefault() {
        return false;
    }

}
