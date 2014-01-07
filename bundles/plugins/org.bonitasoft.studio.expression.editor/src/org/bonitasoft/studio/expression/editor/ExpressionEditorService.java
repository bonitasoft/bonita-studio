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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Romain Bioteau
 *
 */
public class ExpressionEditorService {

    private static final String PROVIDER_CLASS_ATTRIBUTE = "providerClass";
    private static final String EXPRESSION_PROVIDER_ID = "org.bonitasoft.studio.expression.expressionProvider";
    private static final String EXPRESSION_FILTER_ID = "org.bonitasoft.studio.expression.expressionFilter";
    private static final String EXPRESSION_FILTER_CLASS_ATTRIBUTE = "viewerFilterClass";
    private static final String EXPRESSION_FILTER_PRIORITY_ATTRIBUTE= "priority";
    private static final String EXPRESSION_FILTER_ID_ATTRIBUTE = "id";

    private static ExpressionEditorService INSTANCE ;

    private Set<IExpressionProvider>  expressionProviders ;
    private final Map<String,List<ViewerFilter>> expressionFilters;

    public static ExpressionEditorService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ExpressionEditorService();
        }
        return INSTANCE ;
    }

    private ExpressionEditorService(){
        getExpressionProviders() ;
        expressionFilters = new HashMap<String, List<ViewerFilter>>() ;
    }

    public Set<IExpressionProvider> getExpressionProviders() {
        if(expressionProviders == null){
            expressionProviders = new HashSet<IExpressionProvider>() ;
            IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(EXPRESSION_PROVIDER_ID) ;
            for(IConfigurationElement element : elements){
                try {
                    IExpressionProvider provider = (IExpressionProvider) element.createExecutableExtension(PROVIDER_CLASS_ATTRIBUTE) ;
                    expressionProviders.add(provider) ;
                }catch (Exception e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        }
        return expressionProviders;
    }

    /**
     * Returns all ViewerFilter declared sorted from highest priority to lowest
     * @param id
     * @return
     */
    public List<ViewerFilter> getExpressionFilters(String id) {
        if(expressionFilters.get(id) == null){
            List<ViewerFilter> filters = new ArrayList<ViewerFilter>() ;
            IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(EXPRESSION_FILTER_ID) ;
            List<IConfigurationElement> sortedConfigElems = new ArrayList<IConfigurationElement>() ;
            for(IConfigurationElement elem : elements){
                sortedConfigElems.add(elem) ;
            }

            Collections.sort(sortedConfigElems, new Comparator<IConfigurationElement>() {

                @Override
                public int compare(IConfigurationElement e1, IConfigurationElement e2) {
                    int	p1 = 0;
                    int p2 = 0 ;
                    try{
                        p1 = Integer.parseInt(e1.getAttribute(EXPRESSION_FILTER_PRIORITY_ATTRIBUTE));
                    }catch (NumberFormatException e) {
                        p1 = 0 ;
                    }
                    try{
                        p2 = Integer.parseInt(e2.getAttribute(EXPRESSION_FILTER_PRIORITY_ATTRIBUTE));
                    }catch (NumberFormatException e) {
                        p2 = 0 ;
                    }
                    return  p2-p1; //Highest Priority first
                }

            }) ;

            for (IConfigurationElement element : sortedConfigElems){
                if(element.getAttribute(EXPRESSION_FILTER_ID_ATTRIBUTE).equals(id)){
                    try {
                        ViewerFilter filter = (ViewerFilter) element.createExecutableExtension(EXPRESSION_FILTER_CLASS_ATTRIBUTE) ;
                        filters.add(filter) ;
                    }catch (Exception e) {
                        BonitaStudioLog.error(e) ;
                    }
                }
            }
            expressionFilters.put(id, filters) ;
        }

        return expressionFilters.get(id);
    }

    public synchronized IExpressionProvider getExpressionProvider(String type) {
        for(IExpressionProvider provider : getExpressionProviders()){
            if(provider.getExpressionType().equals(type)){
                return provider ;
            }
        }
        return null;
    }
}
