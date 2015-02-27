/*******************************************************************************
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.action;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.refactoring.core.RefactorPair;

public class ParameterRefactorPair extends RefactorPair<Parameter, Parameter> {

	public ParameterRefactorPair(Parameter newValue, Parameter oldValue) {
		super(newValue, oldValue);
	}
	
	@Override
	public String getNewValueName() {
		if(getNewValue() != null){
			return getNewValue().getName();
		} else {
			return super.getNewValueName();
		}
	}
	
	@Override
	public String getOldValueName() {
		return getOldValue().getName();
	}
}
