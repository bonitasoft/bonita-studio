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
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProvider;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

public class WebPageNameResourceChangeListener implements IResourceChangeListener {

    private WebPageFileStore webPageFileStore;
    private IFile jsonFile;
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
        if (jsonFile != null && webPageFileStore != null && expression != null) {
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
                if (jsonFile.getName().equals(delta.getResource().getName())) {
                    expressionItemProvider.setPropertyValue(expression,
                            ExpressionPackage.Literals.EXPRESSION__NAME.getName(),
                            jsonFile.exists() ? webPageFileStore.getDisplayName() : "");
                }
                return true;
            }
        };
    }

    public void setWebPageFileStore(final WebPageFileStore webPageFileStore) {
        this.webPageFileStore = webPageFileStore;
    }

    public void setJSONFile(final IFile jsonFile) {
        this.jsonFile = jsonFile;
    }

    public void setExpression(final Expression expression) {
        this.expression = expression;
    }
}
