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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationsComposite;
import org.bonitasoft.studio.expression.editor.operation.WizardPageOperationsComposite;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorOutputWizardPage extends AbstractConnectorOutputWizardPage {


    private OperationsComposite lineComposite;
   
    
   


	@Override
    protected Control doCreateControl(Composite parent,final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;
        final AvailableExpressionTypeFilter leftFilter =  new AvailableExpressionTypeFilter(new String[]{ 
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.DOCUMENT_REF_TYPE}) ;
        final AvailableExpressionTypeFilter rightFilter =  new AvailableExpressionTypeFilter(new String[]{ 
        		 ExpressionConstants.CONSTANT_TYPE,
        		ExpressionConstants.CONNECTOR_OUTPUT_TYPE,
        		ExpressionConstants.VARIABLE_TYPE,
        		ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.DOCUMENT_TYPE
        }) ;
        CLabel warningLabel = new CLabel(mainComposite, SWT.NONE) ;
        if (!isPageFlowContext()){
        	warningLabel.setText(Messages.transientDataWarning);
        	warningLabel.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK));
        }
        lineComposite = new WizardPageOperationsComposite(null, mainComposite, rightFilter, leftFilter,isPageFlowContext()) ;
        lineComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        IExpressionNatureProvider storageExpressionProvider = getStorageExpressionProvider();
		if(storageExpressionProvider != null){
        	  lineComposite.setStorageExpressionNatureContentProvider(storageExpressionProvider) ;
        }
        lineComposite.setContext(context) ;
        lineComposite.setContext(getElementContainer());
        lineComposite.setEObject(getConnector()) ;
        lineComposite.fillTable() ;
        return mainComposite ;
    }





	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
		return false;
	}





	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(boolean isOverviewContext) {
	}


}
