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
package org.bonitasoft.studio.properties.sections.index;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author aurelie
 *
 */
public class SearchIndexNameUnicityValidator implements IExpressionValidator {

	private EObject context;
	private EditingDomain domain;
	private Expression  inputExpression;

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
	 */
	@Override
	public IStatus validate(final Object value) {
		if (context instanceof Pool){
			final List<SearchIndex> searchIndexes = ((Pool)context).getSearchIndexes();
			for (final SearchIndex searchIndex:searchIndexes){
				if (searchIndex.getName().getContent()!=null && ! searchIndex.getName().getContent().isEmpty() && !searchIndex.getName().equals(inputExpression)){
					if (searchIndex.getName().getContent().equals(value)){
						return ValidationStatus.error(Messages.bind(Messages.searchIndexUnicityError,value));
					}
				}
			}
		}
		return Status.OK_STATUS;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#setInputExpression(org.bonitasoft.studio.model.expression.Expression)
	 */
	@Override
	public void setInputExpression(final Expression inputExpression) {
		this.inputExpression = inputExpression;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#setDomain(org.eclipse.emf.edit.domain.EditingDomain)
	 */
	@Override
	public void setDomain(final EditingDomain domain) {
		this.domain = domain;

	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#setContext(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void setContext(final EObject context) {
		this.context = context;

	}

    @Override
    public boolean isRelevantForExpressionType(final String type) {
        return ExpressionConstants.CONSTANT_TYPE.equals(type);
    }

}
