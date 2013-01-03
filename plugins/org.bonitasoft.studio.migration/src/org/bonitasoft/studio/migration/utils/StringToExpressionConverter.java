/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.utils;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class StringToExpressionConverter {

	private Model model;

	public StringToExpressionConverter(Model model) {
		Assert.isNotNull(model);
		this.model = model ;
	}

	public Instance parse(String stringToParse,String returnType,boolean fixedReturnType) {
		if(returnType == null || returnType.isEmpty()){//Default return type is String
			returnType = String.class.getName();
		}
		if(stringToParse == null || stringToParse.isEmpty()){ //Returns an empty expression
			return createExpressionInstance(model,null, null, returnType, ExpressionConstants.CONSTANT_TYPE, false);
		}
		

		final List<Instance> data = model.getAllInstances("process.Data");
		
		
		
		return null;
	}



	public static Instance createExpressionInstance(Model model,String name, String content,String returnType,String expresisonType,boolean fixedReturnType){
		final Instance instance = model.newInstance("expression.Expression");
		instance.set(ExpressionPackage.Literals.EXPRESSION__NAME, name);
		instance.set(ExpressionPackage.Literals.EXPRESSION__CONTENT, content);
		instance.set(ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName());
		instance.set(ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE_FIXED, fixedReturnType);
		instance.set(ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.CONSTANT_TYPE);
		return instance;
	}

	
}
