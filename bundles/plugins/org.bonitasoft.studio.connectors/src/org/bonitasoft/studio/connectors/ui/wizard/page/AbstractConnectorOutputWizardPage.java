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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractConnectorOutputWizardPage extends WizardPage implements IBonitaVariableContext {

    private EObject elementContainer;
    private ConnectorDefinition definition;
    protected final EMFDataBindingContext context;
    private Connector connector;
    private WizardPageSupport pageSupport;
	private IWizardPage previousPageBackup;
	private boolean isPageFlowContext = false;
	private IExpressionNatureProvider storageExpressionProvider;
    private DefinitionResourceProvider messageProvider;


	public AbstractConnectorOutputWizardPage(){
        super(AbstractConnectorOutputWizardPage.class.getName());
        setTitle(Messages.outputMapping) ;
        setDescription(Messages.outputMappingDesc);
        context = new EMFDataBindingContext() ;
    }


    protected abstract Control doCreateControl(Composite parent,EMFDataBindingContext context) ;

    @Override
    public final void createControl(Composite parent) {
        setControl(doCreateControl(parent,context)) ;
        pageSupport = WizardPageSupport.create(this, context) ;
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

    public ConnectorDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(ConnectorDefinition definition) {
        this.definition = definition;
    }

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public EObject getElementContainer() {
        return elementContainer;
    }

    public void setElementContainer(EObject elementContainer) {
        this.elementContainer = elementContainer;
    }

    public DefinitionResourceProvider getMessageProvider() {
        return messageProvider;
    }

    @Override
    public IWizardPage getPreviousPage() {
        if(previousPageBackup != null){
        	return previousPageBackup;
        }
    	
    	final IWizard wizard = getWizard();
        if(wizard != null){
            return wizard.getPreviousPage(this);
        }
        return super.getPreviousPage();
    }
    
    @Override
    public void setPreviousPage(IWizardPage page) {
    	this.previousPageBackup = page;
    	super.setPreviousPage(page);
    }
    
    @Override
    public boolean isPageFlowContext() {
    	return isPageFlowContext;
    }

    
    @Override
    public void setIsPageFlowContext(boolean isPageFlowContext) {
    	this.isPageFlowContext=isPageFlowContext;
    }

    public IExpressionNatureProvider getStorageExpressionProvider() {
  		return storageExpressionProvider;
  	}

	public void setStorageExpressionProvider(
			IExpressionNatureProvider storageExpressionProvider) {
		this.storageExpressionProvider = storageExpressionProvider;
	}

    public void setMessageProvider(DefinitionResourceProvider messageProvider) {
        this.messageProvider = messageProvider;
    }
}
