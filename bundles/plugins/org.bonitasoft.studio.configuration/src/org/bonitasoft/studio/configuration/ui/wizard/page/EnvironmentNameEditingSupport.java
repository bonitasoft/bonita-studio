/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration.ui.wizard.page;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class EnvironmentNameEditingSupport extends EditingSupport {

	public EnvironmentNameEditingSupport(ColumnViewer viewer) {
		super(viewer);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
	 */
	@Override
	protected boolean canEdit(Object element) {
		return !((Environment)element).getName().equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor getCellEditor(final Object element) {
		TextCellEditor editor = new TextCellEditor((Composite) getViewer().getControl(), SWT.NONE) ;
		editor.setValidator(new ICellEditorValidator() {
			
			@Override
			public String isValid(Object input) {
				if(input == null || input.toString().isEmpty()){
					return Messages.emptyName ;
				}
				IRepositoryStore<?> store = RepositoryManager.getInstance().getRepositoryStore(EnvironmentRepositoryStore.class) ;
				if(input.toString().equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON) || store.getChild(input.toString()+"."+EnvironmentRepositoryStore.ENV_EXT, true) != null){
					return Messages.alreadyExists ;
				}
				return null;
			}
		}) ;
		return  editor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(Object element) {
		if(element instanceof Environment){
			return ((Environment) element).getName() ;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object value) {
		if(element != null && value != null){
			((Environment)element).setName(value.toString()) ;
			getViewer().refresh() ;
		}
	}





}
