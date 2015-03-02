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
import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * @author Romain Bioteau
 *
 */
public class ParameterValueLabelProvider extends ColumnLabelProvider {

	@Override
	public String getText(Object element) {
		return ((Parameter) element).getValue();
	}
	
}
