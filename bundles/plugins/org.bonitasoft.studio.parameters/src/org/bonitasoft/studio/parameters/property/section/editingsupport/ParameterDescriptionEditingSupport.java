/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.parameters.property.section.editingsupport;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class ParameterDescriptionEditingSupport extends EditingSupport {

	private TransactionalEditingDomain transactionalEditingDomain;

	public ParameterDescriptionEditingSupport(final ColumnViewer viewer, final TransactionalEditingDomain transactionalEditingDomain) {
		super(viewer);
		this.transactionalEditingDomain = transactionalEditingDomain;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
	 */
	@Override
	protected boolean canEdit(final Object element) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor getCellEditor(final Object element) {
		final TextCellEditor editor = new TextCellEditor((Composite) getViewer().getControl(), SWT.NONE) ;
		return  editor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(final Object element) {
		if(element instanceof Parameter){
			return ((Parameter) element).getDescription() ;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(final Object element, final Object value) {
		if(element != null && value != null && transactionalEditingDomain != null){
			final AbstractProcess process = (AbstractProcess) ((EObject)element).eContainer() ;
			if(process != null){
				final CompoundCommand cc = new CompoundCommand() ;
				cc.append(SetCommand.create(transactionalEditingDomain, element, ParameterPackage.Literals.PARAMETER__DESCRIPTION, value)) ;
				transactionalEditingDomain.getCommandStack().execute(cc) ;
				getViewer().refresh() ;
			}
		}
	}

	public void setTransactionalEditingDomain(final TransactionalEditingDomain transactionalEditingDomain) {
		this.transactionalEditingDomain = transactionalEditingDomain;
	}



}
