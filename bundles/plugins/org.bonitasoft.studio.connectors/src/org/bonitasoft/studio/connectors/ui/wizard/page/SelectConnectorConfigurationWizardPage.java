/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.provider.ConnectorConfigurationContentProvider;
import org.bonitasoft.studio.connectors.ui.provider.ConnectorConfigurationLabelProvider;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Aurelie Zara
 *
 */
public class SelectConnectorConfigurationWizardPage extends WizardPage implements ISelectionChangedListener,IDoubleClickListener{

	private FilteredTree filterTree;

	public SelectConnectorConfigurationWizardPage() {
		super(SelectConnectorConfigurationWizardPage.class.getName());
		setTitle(Messages.selectConnectorConfigurationWizardPageTitle);
		setDescription(Messages.selectConnectorConfigurationWizardPageDescription);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 10, 10, 0).create());
		filterTree = new FilteredTree(composite, SWT.SINGLE | SWT.BORDER, new PatternFilter(), true);
        filterTree.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
        filterTree.getViewer().setLabelProvider(new ConnectorConfigurationLabelProvider());
        filterTree.getViewer().setContentProvider(new ConnectorConfigurationContentProvider());
        filterTree.getViewer().addSelectionChangedListener(this) ;
        filterTree.getViewer().addDoubleClickListener(this) ;
        filterTree.getViewer().setInput(new Object());
        filterTree.getViewer().addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer arg0, Object arg1, Object element) {
            	ITreeContentProvider contentProvider = (ITreeContentProvider)filterTree.getViewer().getContentProvider();
				if (element instanceof Category){
					
            		if(!contentProvider.hasChildren(element)){
            			return false;
            		}
            		boolean hasElements=false;
            		for(Object c : contentProvider.getChildren(element)){
            					hasElements=hasElements || select(arg0,element,c);
            				}
            		return hasElements;
            	}else if(element instanceof ConnectorDefinition){
            		return contentProvider.getChildren(element).length > 0;
    
    			}
                return element instanceof ConnectorConfiguration;
            }
        }) ;
        filterTree.getViewer().expandAll();
        filterTree.setFocus();
        setControl(composite);
	}

	
	 public ConnectorConfiguration getSelectedConfiguration() {
	        Object selection = ((IStructuredSelection) filterTree.getViewer().getSelection()).getFirstElement();
	        if(selection instanceof ConnectorConfiguration){
	            return (ConnectorConfiguration) selection;
	        }
	        return null;
	    }
	 
	 	
	  @Override
	    public boolean canFlipToNextPage() {
	        return ((IStructuredSelection) filterTree.getViewer().getSelection()).getFirstElement() instanceof ConnectorConfiguration  ;
	    }
	  
	  @Override
	  public void doubleClick(DoubleClickEvent event) {
	        Object selection =  ((IStructuredSelection) event.getSelection()).getFirstElement() ;
	        if(selection instanceof Category || selection instanceof ConnectorDefinition){
	            filterTree.getViewer().expandToLevel(selection, 1) ;
	        }else if(selection instanceof ConnectorConfiguration){
	            if(getNextPage() != null){
	                getContainer().showPage(getNextPage());
	            }
	        }
	    }

	  
	  
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if(canFlipToNextPage()){
			getWizard().getNextPage(this);
		}
		getContainer().updateButtons();
		
	}
	
	  protected void refresh(){
	        if(filterTree != null && !filterTree.isDisposed()){
	            filterTree.getViewer().setContentProvider(new ConnectorConfigurationContentProvider()) ;
	            filterTree.getViewer().setInput(new Object()) ;
	        }
	    }
}
