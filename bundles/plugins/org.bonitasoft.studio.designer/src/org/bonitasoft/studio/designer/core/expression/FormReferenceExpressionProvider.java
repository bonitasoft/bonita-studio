/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.designer.core.expression;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.FormMapping;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class FormReferenceExpressionProvider implements IExpressionProvider {

    @Inject
    private RepositoryAccessor repositoryAccessor;

    @Override
    public Set<Expression> getExpressions(final EObject context) {
        final WebPageRepositoryStore webPageRepositoryStore = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class);
        final Set<Expression> result = new HashSet<>();
        for (final WebPageFileStore fStore : webPageRepositoryStore.getChildren()) {
            final String name = fStore.getCustomPageName();
            if (name != null) {
                result.add(ExpressionHelper.createFormReferenceExpression(name, fStore.getUUID()));
            }
        }
        return result;
    }

    @Override
    public String getExpressionType() {
        return ExpressionConstants.FORM_REFERENCE_TYPE;
    }

    @Override
    public Image getIcon(final Expression expression) {
        return null;
    }

    @Override
    public Image getTypeIcon() {
        return null;
    }

    @Override
    public String getProposalLabel(final Expression expression) {
        return expression.getName();
    }

    @Override
    public boolean isRelevantFor(final EObject context) {
        return context instanceof FormMapping;
    }

    @Override
    public String getTypeLabel() {
        return null;
    }

    @Override
    public IExpressionEditor getExpressionEditor(final Expression expression, final EObject context) {
        return null;
    }

}
