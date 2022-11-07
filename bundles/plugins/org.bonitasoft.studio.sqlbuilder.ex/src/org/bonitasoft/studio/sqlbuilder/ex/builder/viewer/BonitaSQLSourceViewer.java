/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.sqlbuilder.ex.builder.viewer;

import org.eclipse.datatools.sqltools.sqlbuilder.actions.SQLBuilderActionBarContributor;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLEditorDocumentProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLSourceViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLSourceViewerConfiguration;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaSQLSourceViewer extends SQLSourceViewer {
	
	private final boolean handleImproper;

    public BonitaSQLSourceViewer(SQLDomainModel sqlDomainModel, Composite parent, boolean handleImproper) {
		super(sqlDomainModel,parent,handleImproper);
		this.handleImproper = handleImproper ;
		getSourceViewer().unconfigure();
	}
    
    public void setDocumentProvider(SQLEditorDocumentProvider documentProvider) {
       this.documentProvider = documentProvider;
    }
    
	 @Override
    public void menuAboutToShow(IMenuManager menu) {
	        final SQLBuilderActionBarContributor contributor = sqlbuilder.getActionBarContributor();
	        if (contributor != null) {
	            menu.removeAll();
	            addAction(menu, "Reparse"); //$NON-NLS-1$
	            menu.add(new Separator("Text Operations")); //$NON-NLS-1$
	            
	            addAction(menu, "Cut"); //$NON-NLS-1$
	            addAction(menu, "Copy"); //$NON-NLS-1$
	            addAction(menu, "Paste"); //$NON-NLS-1$
	            menu.add(new Separator("Content Assist Options")); //$NON-NLS-1$
	            addAction(menu, "ContentAssistProposal"); //$NON-NLS-1$
	            addAction(menu, "ContentTip"); //$NON-NLS-1$
	            
	            if (handleImproper) {
	                menu.add(new Separator());
	                final IAction revertToPreviousAction = contributor.getAction( SQLBuilderActionBarContributor.REVERT_TO_PREVIOUS_ACTION_ID );
	                if (revertToPreviousAction != null) {
	                    menu.add(revertToPreviousAction);
	                }
	                
	                final IAction revertToDefaultAction = contributor.getAction( SQLBuilderActionBarContributor.REVERT_TO_DEFAULT_ACTION_ID );
	                if (revertToDefaultAction != null) {
	                    menu.add(revertToDefaultAction);                    
	                }
	                
	                final boolean proper = sqlDomainModel.isProper();
	                revertToPreviousAction.setEnabled(!proper);
	                revertToDefaultAction.setEnabled(!proper);
	            }

	            final IAction changeStatementTypeAction = contributor.getAction( SQLBuilderActionBarContributor.CHANGE_STATEMENT_TYPE_ACTION_ID );
	            menu.add(changeStatementTypeAction);
	            changeStatementTypeAction.setEnabled(true);
	            
	            menu.add( new Separator("Omit Current Schema")); //$NON-NLS-1$
	            final IAction omitCurrentSchemaAction = contributor.getAction( SQLBuilderActionBarContributor.OMIT_CURRENT_SCHEMA_ACTION_ID );
	            menu.add(omitCurrentSchemaAction);
	            omitCurrentSchemaAction.setEnabled(true);
	        }
	    }

	 public SourceViewer getSourceViewer(){
		 return sourceViewer ;
	 }
	 
    public SQLSourceViewerConfiguration getConfiguration() {
        return configuration;
    }
    
    public void setConfiguration(SQLSourceViewerConfiguration configuration) {
        this.configuration = configuration;
    }

	 @Override
	public boolean reparse() {
		super.reparse();
		return true ;
	}
}
