/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.property.section.editingsupport;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterTypeLabelProvider;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class ParameterTypeEditingSupport extends EditingSupport {

    private TransactionalEditingDomain transactionalEditingDomain;
    public static String[] types ;
    static {
        types = new String[]{String.class.getName(),Boolean.class.getName(),Integer.class.getName(),Double.class.getName()} ;
    }

    @Override
    protected void initializeCellEditorValue(CellEditor cellEditor,
            ViewerCell cell) {
        super.initializeCellEditorValue(cellEditor, cell);
    }




    public ParameterTypeEditingSupport(ColumnViewer viewer, TransactionalEditingDomain transactionalEditingDomain) {
        super(viewer);
        this.transactionalEditingDomain = transactionalEditingDomain ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
     */
    @Override
    protected boolean canEdit(Object element) {
        return element != null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
     */
    @Override
    protected CellEditor getCellEditor(Object element) {
        ComboBoxViewerCellEditor editor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(), SWT.READ_ONLY) ;
        editor.setContentProvider(new ArrayContentProvider()) ;
        editor.setLabelProvider(new ParameterTypeLabelProvider()) ;
        editor.setInput(types) ;
        return editor ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
     */
    @Override
    protected Object getValue(Object element) {
        if(element instanceof Parameter){
            return ((Parameter) element).getTypeClassname() ;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void setValue(Object element, Object value) {
        if(element != null && value != null && transactionalEditingDomain != null){
            CompoundCommand cc = new CompoundCommand() ;
            cc.append(SetCommand.create(transactionalEditingDomain, element, ParameterPackage.Literals.PARAMETER__TYPE_CLASSNAME, value)) ;
            transactionalEditingDomain.getCommandStack().execute(cc) ;
            getViewer().refresh() ;
        }
    }


    public void setTransactionalEditingDomain(TransactionalEditingDomain transactionalEditingDomain) {
        this.transactionalEditingDomain = transactionalEditingDomain;
    }

}
