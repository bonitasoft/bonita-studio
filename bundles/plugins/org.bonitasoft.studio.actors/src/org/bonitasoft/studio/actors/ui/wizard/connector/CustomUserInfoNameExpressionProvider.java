/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA 02110-1301, USA.
 **/
package org.bonitasoft.studio.actors.ui.wizard.connector;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * @author Elias Ricken de Medeiros
 *
 */
public class CustomUserInfoNameExpressionProvider implements IExpressionNatureProvider {


    private final OrganizationRepositoryStore store;
    private final String activeOrgFileName;

    public CustomUserInfoNameExpressionProvider(final OrganizationRepositoryStore store, final String activeOrgFileName) {
        this.store = store;
        this.activeOrgFileName = activeOrgFileName;
    }

    @Override
    public Expression[] getExpressions(final EObject context) {
        final OrganizationFileStore organizationFileStore = store.getChild(activeOrgFileName, true);
        Organization organization;
        try {
            organization = organizationFileStore.getContent();
        } catch (ReadFileStoreException e) {
            return new Expression[0];
        }
        final CustomUserInfoDefinitions infoDefContainer = organization.getCustomUserInfoDefinitions();
        return getExpressions(infoDefContainer);
    }

    private Expression[] getExpressions(final CustomUserInfoDefinitions infoDefContainer) {
        final List<Expression> exprList = new ArrayList<Expression>();
        if(infoDefContainer != null) {
            final EList<CustomUserInfoDefinition> infoDefElements = infoDefContainer.getCustomUserInfoDefinition();
            for (final CustomUserInfoDefinition infoDefinition : infoDefElements) {
                exprList.add(ExpressionHelper.createConstantExpression(infoDefinition.getName(), String.class.getName()));
            }
        }
        return exprList.toArray(new Expression[exprList.size()]);
    }

}
