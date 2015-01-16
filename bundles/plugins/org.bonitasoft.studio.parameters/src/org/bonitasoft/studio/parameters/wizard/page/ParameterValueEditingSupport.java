/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.wizard.page;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class ParameterValueEditingSupport extends EditingSupport {

    private final WizardPage page;

    public ParameterValueEditingSupport(ColumnViewer viewer,WizardPage page) {
        super(viewer);
        this.page =  page ;
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
        final TextCellEditor editor = new TextCellEditor((Composite) getViewer().getControl()) ;
        editor.setValidator(new ICellEditorValidator() {

            @Override
            public String isValid(Object value) {
                String input = (String) value ;
                Parameter param = (Parameter) element ;
                String typeName = param.getTypeClassname() ;
                if (!input.isEmpty()) {
                	if(typeName.equals(Integer.class.getName())){
                		try{
                			Integer.parseInt(input) ;
                		}catch (NumberFormatException e) {
                			return Messages.invalidInteger ;
                    }
                	}else if(typeName.equals(Double.class.getName())){
                		try{
                			Double.parseDouble(input) ;
                		}catch (NumberFormatException e) {
                        return Messages.invalidDouble ;
                    }
                }
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
        if(element instanceof Parameter){
            String paramValue = ((Parameter) element).getValue();
            if(paramValue == null){
                return "" ;
            }else{
                return paramValue ;
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void setValue(Object element, Object value) {
        if(element != null && value != null){
            ((Parameter)element).setValue(value.toString()) ;
            getViewer().refresh() ;
            page.getWizard().getContainer().updateMessage() ;
        }
    }


}
