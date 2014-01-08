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

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.actors.action.RefactorActorOperation;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.CellEditorValidationStatusListener;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 *
 */
public class ActorNameEditingSupport extends EditingSupport {

    private TransactionalEditingDomain transactionalEditingDomain;
    private final CellEditorValidationStatusListener listener;

    public ActorNameEditingSupport(ColumnViewer viewer, TransactionalEditingDomain transactionalEditingDomain,CellEditorValidationStatusListener listener) {
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
                if(input.isEmpty()){
                    return Messages.nameIsEmpty;
                }
                if (input.length()>50){
                	return Messages.nameTooLong;
                }
                Actor actor = (Actor) element ;
                AbstractProcess process = ModelHelper.getParentProcess(actor) ;
                for(Actor a : process.getActors()){
                    if(!a.equals(actor)){
                        if(a.getName().equals(input)){
                            return Messages.nameAlreadyExists ;
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
        if(element instanceof Actor){
            return ((Actor) element).getName() ;
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
               // transactionalEditingDomain.getCommandStack().execute(SetCommand.create(transactionalEditingDomain, element, ProcessPackage.Literals.ELEMENT__NAME, value)) ;
            	executeOperation(process, (String)value, element);
                getViewer().refresh() ;
            }
        }
    }

    public void setTransactionalEditingDomain(TransactionalEditingDomain transactionalEditingDomain) {
        this.transactionalEditingDomain = transactionalEditingDomain;
    }

	private void executeOperation(AbstractProcess process,String newValue,Object element){
			RefactorActorOperation operation = new RefactorActorOperation(process, (Actor)element);
			operation.setNewValue(newValue);
			IProgressService service = PlatformUI.getWorkbench().getProgressService();
			try {
				service.busyCursorWhile(operation);
			} catch (InvocationTargetException e) {
				BonitaStudioLog.error(e);
			} catch (InterruptedException e) {
				BonitaStudioLog.error(e);
			}

	}

}
