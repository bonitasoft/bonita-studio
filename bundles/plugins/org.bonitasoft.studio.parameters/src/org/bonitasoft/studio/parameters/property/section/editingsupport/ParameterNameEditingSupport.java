/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
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

	public ParameterNameEditingSupport(ColumnViewer viewer, TransactionalEditingDomain transactionalEditingDomain, CellEditorValidationStatusListener listener) {
		super(viewer);
		this.transactionalEditingDomain = transactionalEditingDomain;
		this.listener = listener;
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
				String input = (String) value ;
			
				final IStatus javaConventionNameStatus = new GroovyReferenceValidator(Messages.name).validate(value.toString());
				if(!javaConventionNameStatus.isOK()){
					return javaConventionNameStatus.getMessage();
				}
				
				final IStatus lenghtNameStatus = new InputLengthValidator(Messages.name, 50).validate(input);
				if(!lenghtNameStatus.isOK()){
					return lenghtNameStatus.getMessage();
				}
				Parameter param = (Parameter) element ;
				AbstractProcess process = (AbstractProcess) param.eContainer() ;
				for(Parameter p : process.getParameters()){
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
	protected Object getValue(Object element) {
		if(element instanceof Parameter){
			return ((Parameter) element).getName() ;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object value) {
		if(element != null && value != null && transactionalEditingDomain != null){
			AbstractProcess process = (AbstractProcess) ((EObject)element).eContainer() ;
			if(process != null){
				CompoundCommand cc = new CompoundCommand() ;
				cc.append(SetCommand.create(transactionalEditingDomain, element, ParameterPackage.Literals.PARAMETER__NAME, value)) ;
				transactionalEditingDomain.getCommandStack().execute(cc) ;
				getViewer().refresh() ;
			}
		}
	}

	public void setTransactionalEditingDomain(TransactionalEditingDomain transactionalEditingDomain) {
		this.transactionalEditingDomain = transactionalEditingDomain;
	}



}
