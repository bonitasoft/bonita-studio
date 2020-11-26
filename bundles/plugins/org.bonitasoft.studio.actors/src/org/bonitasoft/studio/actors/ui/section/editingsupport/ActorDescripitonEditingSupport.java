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
package org.bonitasoft.studio.actors.ui.section.editingsupport;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
public class ActorDescripitonEditingSupport extends EditingSupport {

	private TransactionalEditingDomain transactionalEditingDomain;

	public ActorDescripitonEditingSupport(ColumnViewer viewer, TransactionalEditingDomain transactionalEditingDomain) {
		super(viewer);
		this.transactionalEditingDomain = transactionalEditingDomain;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
	 */
	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor getCellEditor(final Object element) {
		TextCellEditor editor = new TextCellEditor((Composite) getViewer().getControl(), SWT.NONE) ; 
		editor.setValidator(new ICellEditorValidator() {
			
			@Override
			public String isValid(Object value) {
				String desc = (String)value;
				if (desc.length()>255){
					return Messages.descTooLong;
				}
				return null;
			}
		});
		return  editor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(Object element) {
		if(element instanceof Actor){
			return ((Actor) element).getDocumentation() ;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object value) {
		if(element != null && value != null && transactionalEditingDomain != null){
			AbstractProcess process = ModelHelper.getParentProcess((EObject) element) ;
			if(process != null){
				transactionalEditingDomain.getCommandStack().execute(SetCommand.create(transactionalEditingDomain, element, ProcessPackage.Literals.ELEMENT__DOCUMENTATION, value)) ;
				getViewer().refresh() ;
			}
		}
	}

	public void setTransactionalEditingDomain(TransactionalEditingDomain transactionalEditingDomain) {
		this.transactionalEditingDomain = transactionalEditingDomain;
	}

	


}
