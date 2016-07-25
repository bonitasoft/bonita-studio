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
package org.bonitasoft.studio.actors.ui.wizard.page;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class DefaultUserOrganizationWizardPage extends WizardPage {

    private String user ;
    private DataBindingContext context;
    private WizardPageSupport pageSupport;
    private AutoCompleteField autoCompletionField;
    private Set<String> usernames;
    private Text usernameText;
    private Binding binding;

    public DefaultUserOrganizationWizardPage() {
        super(DefaultUserOrganizationWizardPage.class.getName());
        setTitle(Messages.defaultUserOrganizationTitle) ;
        setDescription(Messages.defaultUserOrganizationDesc) ;
        usernames = new HashSet<String>() ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create()) ;

        context = new DataBindingContext() ;

        final Label usernameLabel = new Label(mainComposite, SWT.NONE) ;
        usernameLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create()) ;
        usernameLabel.setText(Messages.userName) ;

        usernameText = new Text(mainComposite, SWT.BORDER) ;
        usernameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

        autoCompletionField = new AutoCompleteField(usernameText, new TextContentAdapter(), new String[]{}) ;

        createBindings();
        pageSupport = WizardPageSupport.create(this,context) ;
        
        setControl(mainComposite) ;
    }

    @Override
    public void dispose() {
        super.dispose();
        if(pageSupport != null){
            pageSupport.dispose() ;
        }
        if(context != null){
            context.dispose() ;
        }

    }
    
    public void createBindings(){
    	
        UpdateValueStrategy strategy = new UpdateValueStrategy() ;
        strategy.setAfterGetValidator(new EmptyInputValidator(Messages.userName)) ;
        strategy.setBeforeSetValidator(new IValidator(){
            @Override
            public IStatus validate(Object value) {
            	String user = (String)value;
            	if (!usernames.contains(user)){
            		return ValidationStatus.error(Messages.bind(Messages.UserDoesntExistError,user));
            	}
				return Status.OK_STATUS;
            	
            }
        });
       binding = context.bindValue(SWTObservables.observeText(usernameText, SWT.Modify), PojoProperties.value(DefaultUserOrganizationWizardPage.class, "user").observe(this),strategy,null) ;	
    }

    public void setOrganization(Organization organization){
    	usernames.clear();
        for(User user : organization.getUsers().getUser()){
            usernames.add(user.getUserName()) ;
        }
        autoCompletionField.setProposals(usernames.toArray(new String[usernames.size()])) ;
        binding.validateTargetToModel();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void refreshBindings(){
    	binding.validateTargetToModel();
    }

}
