/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.property.section.provider;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ParameterTypeLabelProvider extends ColumnLabelProvider {
	
	@Override
	public String getText(Object element) {
		if(element instanceof Parameter){
			String t = ((Parameter) element).getTypeClassname() ;
			if(t != null){
				if(t.equals(String.class.getName())){
					return Messages.textType ;
				}else if(t.equals(Integer.class.getName())){
					return Messages.integerType ;
				}else if(t.equals(Double.class.getName())){
					return Messages.doubleType ;
				}else if(t.equals(Boolean.class.getName())){
					return Messages.booleanType ;
				}
			}
		}else if(element instanceof String){
			String t = (String) element ;
			if(t != null){
				if(t.equals(String.class.getName())){
					return Messages.textType ;
				}else if(t.equals(Integer.class.getName())){
					return Messages.integerType ;
				}else if(t.equals(Double.class.getName())){
					return Messages.doubleType ;
				}else if(t.equals(Boolean.class.getName())){
					return Messages.booleanType ;
				}
			}
		}
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		return null ;
	}
	
	
}
