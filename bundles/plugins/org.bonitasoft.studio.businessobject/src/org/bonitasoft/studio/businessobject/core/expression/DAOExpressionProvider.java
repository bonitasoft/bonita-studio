/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.core.expression;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IType;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class DAOExpressionProvider implements IExpressionProvider {

    @Inject
    private RepositoryAccessor repositoryAccessor;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressions(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Set<Expression> getExpressions(final EObject context) {
        final Set<Expression> result = new HashSet<>();
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> boStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        for (final IType t : boStore.allBusinessObjectDao(repositoryAccessor.getCurrentRepository().getJavaProject())) {
            result.add(createExpression(t));
        }
        return result;
    }

    protected Expression createExpression(final IType daoType) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        String elementName = daoType.getElementName();
        elementName = Character.toLowerCase(elementName.charAt(0)) + elementName.substring(1, elementName.length());
        expression.setName(elementName);
        expression.setContent(elementName);
        expression.setReturnType(daoType.getFullyQualifiedName());
        expression.setReturnTypeFixed(true);
        expression.setType(getExpressionType());
        return expression;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionType()
     */
    @Override
    public String getExpressionType() {
        return ExpressionConstants.DAO_TYPE;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getIcon(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public Image getIcon(final Expression expression) {
        return Pics.getImage("query.png", BusinessObjectPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeIcon()
     */
    @Override
    public Image getTypeIcon() {
        return Pics.getImage("query.png", BusinessObjectPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getProposalLabel(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public String getProposalLabel(final Expression expression) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(final EObject context) {
        return getBusinessFileStore() != null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeLabel()
     */
    @Override
    public String getTypeLabel() {
        return Messages.businessObjectDAO;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionEditor(org.bonitasoft.studio.model.expression.Expression,
     * org.eclipse.emf.ecore.EObject)
     */
    @Override
    public IExpressionEditor getExpressionEditor(final Expression expression, final EObject context) {
        return null;
    }

    protected BusinessObjectModel getBusinessObjectModel() {
        final BusinessObjectModelFileStore fileStore = getBusinessFileStore();
        if (fileStore != null) {
            return fileStore.getContent();
        }
        return null;
    }

    protected BusinessObjectModelFileStore getBusinessFileStore() {
        return ((BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore>) repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class))
                        .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
    }
}
