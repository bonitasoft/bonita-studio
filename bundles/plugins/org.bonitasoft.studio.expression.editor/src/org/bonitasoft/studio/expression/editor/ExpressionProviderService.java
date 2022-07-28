/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.provider.ExpressionComparator;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Romain Bioteau
 */
@Singleton
public class ExpressionProviderService {

    private static final String PROVIDER_CLASS_ATTRIBUTE = "providerClass";
    private static final String EXPRESSION_PROVIDER_ID = "org.bonitasoft.studio.expression.expressionProvider";
    private static final String EXPRESSION_FILTER_ID = "org.bonitasoft.studio.expression.expressionFilter";
    private static final String EXPRESSION_FILTER_CLASS_ATTRIBUTE = "viewerFilterClass";
    private static final String EXPRESSION_FILTER_PRIORITY_ATTRIBUTE = "priority";
    private static final String EXPRESSION_FILTER_ID_ATTRIBUTE = "id";

    private static ExpressionProviderService INSTANCE;

    private final Map<String, List<ViewerFilter>> expressionFilters = new HashMap<>();
    private final ExtensionContextInjectionFactory extensionContextInjectionFactory;
    private Map<String, IExpressionProvider> expressionProviderByType;

    public static ExpressionProviderService getInstance() {
        return INSTANCE;
    }

    ExpressionProviderService() {
        INSTANCE = this;
        extensionContextInjectionFactory = new ExtensionContextInjectionFactory();
    }

    @PostConstruct
    protected void init(final IEclipseContext context) {
        expressionProviderByType = new HashMap<>();
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(EXPRESSION_PROVIDER_ID);
        for (final IConfigurationElement element : elements) {
            try {
                final IExpressionProvider provider = extensionContextInjectionFactory.make(element, PROVIDER_CLASS_ATTRIBUTE,
                        IExpressionProvider.class, context);
                expressionProviderByType.put(provider.getExpressionType(), provider);
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        context.set(ExpressionProviderService.class, this);
    }

    public Set<IExpressionProvider> getExpressionProviders() {
        return new HashSet<>(expressionProviderByType.values());
    }

    /**
     * Returns all ViewerFilter declared sorted from highest priority to lowest
     *
     * @param id
     * @return
     */
    public List<ViewerFilter> getExpressionFilters(final String id) {
        if (expressionFilters.get(id) == null) {
            final List<ViewerFilter> filters = new ArrayList<>();
            final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                    .getConfigurationElements(EXPRESSION_FILTER_ID);
            final List<IConfigurationElement> sortedConfigElems = new ArrayList<>();
            for (final IConfigurationElement elem : elements) {
                sortedConfigElems.add(elem);
            }

            Collections.sort(sortedConfigElems, new Comparator<IConfigurationElement>() {

                @Override
                public int compare(final IConfigurationElement e1, final IConfigurationElement e2) {
                    int p1 = 0;
                    int p2 = 0;
                    try {
                        p1 = Integer.parseInt(e1.getAttribute(EXPRESSION_FILTER_PRIORITY_ATTRIBUTE));
                    } catch (final NumberFormatException e) {
                        p1 = 0;
                    }
                    try {
                        p2 = Integer.parseInt(e2.getAttribute(EXPRESSION_FILTER_PRIORITY_ATTRIBUTE));
                    } catch (final NumberFormatException e) {
                        p2 = 0;
                    }
                    return p2 - p1; //Highest Priority first
                }

            });

            for (final IConfigurationElement element : sortedConfigElems) {
                if (element.getAttribute(EXPRESSION_FILTER_ID_ATTRIBUTE).equals(id)) {
                    try {
                        final ViewerFilter filter = (ViewerFilter) element
                                .createExecutableExtension(EXPRESSION_FILTER_CLASS_ATTRIBUTE);
                        filters.add(filter);
                    } catch (final Exception e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
            expressionFilters.put(id, filters);
        }

        return expressionFilters.get(id);
    }

    public synchronized IExpressionProvider getExpressionProvider(final String type) {
        return expressionProviderByType.get(type);
    }

    public Set<Expression> getRelevantExpressions(EObject context) {
        final Set<Expression> expressionsSet = new TreeSet<>(new ExpressionComparator());
        for (final IExpressionProvider provider : expressionProviderByType.values()) {
            if (provider.isRelevantFor(context)) {
                final Set<Expression> expressions = provider.getExpressions(context);
                if (expressions != null) {
                    expressionsSet.addAll(expressions);
                }
            }
        }
        return expressionsSet;
    }

}
