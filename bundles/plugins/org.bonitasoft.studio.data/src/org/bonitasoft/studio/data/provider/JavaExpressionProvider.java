/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import java.util.Collections;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class JavaExpressionProvider implements IExpressionProvider {

	@Override
	public Set<Expression> getExpressions(EObject context) {
		return Collections.emptySet();
	}

	@Override
	public String getExpressionType() {
		return ExpressionConstants.JAVA_TYPE;
	}

	@Override
	public Image getIcon(Expression expression) {
		return Pics.getImage(PicsConstants.java);
	}

	@Override
	public String getProposalLabel(Expression expression) {
		return expression.getName();
	}

	@Override
	public boolean isRelevantFor(EObject context) {
		IExpressionProvider provider = ExpressionProviderService.getInstance().getExpressionProvider(ExpressionConstants.VARIABLE_TYPE) ;
		if(provider != null){
			for(Expression exp : provider.getExpressions(context)){
				if(exp.getReferencedElements().get(0) instanceof JavaObjectData || ((Data)exp.getReferencedElements().get(0)).isMultiple()){
					return true ;
				}
			}
		}
		
		return false;
	}

	public Image getTypeIcon() {
		return Pics.getImage(PicsConstants.java);
	}

	@Override
	public String getTypeLabel() {
		return Messages.javaType;
	}

	@Override
	public IExpressionEditor getExpressionEditor(Expression expression,EObject context) {
		return new JavaExpressionEditor();
	}

	

}
