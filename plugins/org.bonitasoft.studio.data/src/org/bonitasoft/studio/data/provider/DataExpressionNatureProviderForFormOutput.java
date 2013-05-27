/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
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
package org.bonitasoft.studio.data.provider;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.PageFlow;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Aurelien Pupier
 * /!\ not declared in extension points, we don't want it in the list, it is only used in Operation output
 *
 */
public class DataExpressionNatureProviderForFormOutput extends ExpressionContentProvider implements IExpressionNatureProvider {

	private EObject context;
	private DataExpressionProvider dataProvider;

	public DataExpressionNatureProviderForFormOutput(){
		dataProvider = (DataExpressionProvider) ExpressionEditorService.getInstance().getExpressionProvider(ExpressionConstants.VARIABLE_TYPE);
	}
	
	protected List<Data> getDataInForm(Form form, final EObject formContainer) {
		return ModelHelper.getAccessibleDataInFormsWithNoRestriction((PageFlow) formContainer, form.eContainmentFeature());
	}

	@Override
	public Expression[] getExpressions() {
		
		final Set<Expression> expressions = dataProvider.getExpressions(context);
		final Set<Expression> expressionsToReturn = new HashSet<Expression>();
		final List<Data> accessibleDatas = ModelHelper.getAccessibleData(context,true);
		Iterator<Expression> iter = expressions.iterator();
		while(iter.hasNext()){
			Expression expr = iter.next();
			
			if (ExpressionConstants.VARIABLE_TYPE.equals(expr.getType())){
				Data data = getReferencedData(accessibleDatas, expr);
				if (data !=null && !data.isTransient()){
					expressionsToReturn.add(expr);
				}
			} else {
				expressionsToReturn.add(expr);
			}
		}
		expressionsToReturn.addAll(ExpressionEditorService.getInstance().getExpressionProvider(ExpressionConstants.DOCUMENT_REF_TYPE).getExpressions(context));
		return expressionsToReturn.toArray(new Expression[expressionsToReturn.size()]);
	}

	@Override
	public void setContext(EObject context) {
		this.context = context;

	}

	@Override
	public EObject getContext() {
		return context;
	}


	private Data getReferencedData(List<Data> datas,Expression expr){
		for (Data data:datas){
			if (data.getName().equals(expr.getName())){
				return data;
			}
		}
		return null;
	}
}
