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

import org.bonitasoft.studio.common.jface.CellEditorValidationStatusListener;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CompoundCommand;
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
public class ParameterNameEditingSupport extends EditingSupport {

	private TransactionalEditingDomain transactionalEditingDomain;
	private final CellEditorValidationStatusListener listener;

	public ParameterNameEditingSupport(final ColumnViewer viewer, final TransactionalEditingDomain transactionalEditingDomain, final CellEditorValidationStatusListener listener) {
		super(viewer);
		this.transactionalEditingDomain = transactionalEditingDomain;
		this.listener = listener;
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
		editor.setValidator(new ICellEditorValidator() {

			@Override
			public String isValid(final Object value) {
				final String input = (String) value ;

				final IStatus javaConventionNameStatus = new GroovyReferenceValidator(Messages.name).validate(value.toString());
				if(!javaConventionNameStatus.isOK()){
					return javaConventionNameStatus.getMessage();
				}

				final IStatus lenghtNameStatus = new InputLengthValidator(Messages.name, 50).validate(input);
				if(!lenghtNameStatus.isOK()){
					return lenghtNameStatus.getMessage();
				}
				final Parameter param = (Parameter) element ;
				final AbstractProcess process = (AbstractProcess) param.eContainer() ;
				for(final Parameter p : process.getParameters()){
					if(!p.equals(param)){
						if(p.getName().equals(input)){
							return Messages.invalidName ;
						}
					}
				}
				return null;
			}
		}) ;
		listener.setCellEditor(editor);
		editor.addListener(listener);
		return  editor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(final Object element) {
		if(element instanceof Parameter){
			return ((Parameter) element).getName() ;
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
				cc.append(SetCommand.create(transactionalEditingDomain, element, ParameterPackage.Literals.PARAMETER__NAME, value)) ;
				transactionalEditingDomain.getCommandStack().execute(cc) ;
				getViewer().refresh() ;
			}
		}
	}

	public void setTransactionalEditingDomain(final TransactionalEditingDomain transactionalEditingDomain) {
		this.transactionalEditingDomain = transactionalEditingDomain;
	}



}
