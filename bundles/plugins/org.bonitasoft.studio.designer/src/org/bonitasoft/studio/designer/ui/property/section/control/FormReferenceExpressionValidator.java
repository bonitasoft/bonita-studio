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
package org.bonitasoft.studio.designer.ui.property.section.control;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 */
@Creatable
public class FormReferenceExpressionValidator implements IExpressionValidator {

    private Expression inputExpression;

    private final RepositoryAccessor repositoryAccessor;

    private final EStructuralFeature feature;

    public FormReferenceExpressionValidator(final RepositoryAccessor repositoryAccessor, final EStructuralFeature feature) {
        this.repositoryAccessor = repositoryAccessor;
        this.feature = feature;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
     */
    @Override
    public IStatus validate(final Object value) {
        final String content = inputExpression.getContent();
        final WebPageRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class);
        String errorMessage = null;
        if (ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING.equals(feature)) {
            errorMessage = Messages.bind(Messages.pageDoesntExist, content);
        } else {
            errorMessage = Messages.bind(Messages.formDoesntExist, content);
        }
        return repositoryStore.getChild(content) == null ? ValidationStatus.error(errorMessage) : ValidationStatus.ok();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#setInputExpression(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public void setInputExpression(final Expression inputExpression) {
        this.inputExpression = inputExpression;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#setDomain(org.eclipse.emf.edit.domain.EditingDomain)
     */
    @Override
    public void setDomain(final EditingDomain domain) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#setContext(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setContext(final EObject context) {
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#isRelevantForExpressionType(java.lang.String)
     */
    @Override
    public boolean isRelevantForExpressionType(final String type) {
        return ExpressionConstants.FORM_REFERENCE_TYPE.equals(type);
    }

}
