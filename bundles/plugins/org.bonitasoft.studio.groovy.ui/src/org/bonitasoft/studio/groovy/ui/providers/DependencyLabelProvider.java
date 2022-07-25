/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui.providers;

import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class DependencyLabelProvider extends LabelProvider {

    private final AdapterFactoryLabelProvider adapterLabelProvider;

    public DependencyLabelProvider(final AdapterFactoryLabelProvider adapterLabelProvider) {
        this.adapterLabelProvider = adapterLabelProvider;
    }

    @Override
    public Image getImage(final Object element) {
        if (element instanceof Expression) {
            final IExpressionProvider expressionProvider = ExpressionProviderService.getInstance().getExpressionProvider(((Expression) element).getType());
            if (expressionProvider != null) {
                return expressionProvider.getTypeIcon();
            }
        }
        return adapterLabelProvider.getImage(element);
    }

    @Override
    public String getText(final Object element) {
        return adapterLabelProvider.getText(element);
    }

}
