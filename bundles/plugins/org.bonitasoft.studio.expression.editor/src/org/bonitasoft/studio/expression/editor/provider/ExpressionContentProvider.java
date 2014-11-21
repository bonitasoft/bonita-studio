/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.provider;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.WeakHashMap;

import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class ExpressionContentProvider implements IExpressionNatureProvider {

    private static ExpressionContentProvider INSTANCE;
    protected WeakHashMap<EObject, Expression[]> cachedExpressions = new WeakHashMap<EObject, Expression[]>();

    private ExpressionContentProvider() {

    }

    public static ExpressionContentProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExpressionContentProvider();
        }
        return INSTANCE;
    }

    @Override
    public Expression[] getExpressions(final EObject context) {
        if (context != null && cachedExpressions.get(context) == null) {
            final SortedSet<Expression> expressionsSet = new TreeSet<Expression>(new ExpressionComparator());
            final Set<IExpressionProvider> providers = ExpressionEditorService.getInstance().getExpressionProviders();
            for (final IExpressionProvider provider : providers) {
                if (provider.isRelevantFor(context)) {
                    final Set<Expression> expressions = provider.getExpressions(context);
                    if (expressions != null) {
                        expressionsSet.addAll(expressions);
                    }
                }
            }
            cachedExpressions.put(context, expressionsSet.toArray(new Expression[expressionsSet.size()]));
        }
        final Expression[] expressions = cachedExpressions.get(context);
        if (expressions == null) {
            return new Expression[0];
        }
        return expressions;
    }

    public void clearCache() {
        cachedExpressions.clear();
    }

}
