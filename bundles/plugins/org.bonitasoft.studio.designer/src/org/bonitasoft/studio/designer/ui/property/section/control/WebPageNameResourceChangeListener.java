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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProvider;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

public class WebPageNameResourceChangeListener implements IResourceChangeListener {

    private WebPageRepositoryStore webPageStore;
    private Expression expression;
    private final ExpressionItemProvider expressionItemProvider;

    public WebPageNameResourceChangeListener(final ExpressionItemProvider expressionItemProvider) {
        this.expressionItemProvider = expressionItemProvider;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
     */
    @Override
    public void resourceChanged(final IResourceChangeEvent event) {
        if (webPageStore != null && expression != null) {
            try {
                event.getDelta().accept(resourceDeltaVisitor());
            } catch (final CoreException e) {
                BonitaStudioLog.error("Failed to update form reference name", e);
            }
        }
    }

    private IResourceDeltaVisitor resourceDeltaVisitor() {
        return new IResourceDeltaVisitor() {

            @Override
            public boolean visit(final IResourceDelta delta) throws CoreException {
                String name = delta.getResource().getName();
                if (webPageStore.getResource().getLocation().isPrefixOf(delta.getResource().getLocation())
                        && name.endsWith(".json")) {
                        expressionItemProvider.setPropertyValue(expression,
                            ExpressionPackage.Literals.EXPRESSION__NAME.getName(),
                            webPageStore.getDisplayNameFor(expression.getContent()));
                }
                return true;
            }
        };
    }

    public void setWebPageStore(final WebPageRepositoryStore webPageStore) {
        this.webPageStore = webPageStore;
    }


    public void setExpression(final Expression expression) {
        this.expression = expression;
    }
}
