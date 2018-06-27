/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

package org.bonitasoft.studio.groovy.ui.contentassist;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.expression.ExpressionConstants;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.codehaus.groovy.eclipse.editor.highlighting.IHighlightingExtender;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.text.rules.IRule;

import com.google.common.base.Function;

/**
 * @author Romain Bioteau
 */
public class BonitaSyntaxHighlighting implements IHighlightingExtender {

    public static List<String> BONITA_KEYWORDS = new ArrayList<String>();

    static {
        BONITA_KEYWORDS.add(ExpressionConstants.API_ACCESSOR.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.ENGINE_EXECUTION_CONTEXT.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.NUMBER_OF_ACTIVE_INSTANCES.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.NUMBER_OF_TERMINATED_INSTANCES.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.NUMBER_OF_COMPLETED_INSTANCES.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.NUMBER_OF_INSTANCES.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.PROCESS_DEFINITION_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.ROOT_PROCESS_INSTANCE_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.PROCESS_INSTANCE_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.ACTIVITY_INSTANCE_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.LOOP_COUNTER.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.LOGGED_USER_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.TASK_ASSIGNEE_ID.getEngineConstantName());
    }

    private RepositoryAccessor repositoryAccessor;

    public BonitaSyntaxHighlighting() {
    }

    BonitaSyntaxHighlighting(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    @Override
    public List<String> getAdditionalGJDKKeywords() {
        final List<String> keyWords = new ArrayList<String>(BONITA_KEYWORDS);
        final BusinessObjectModelRepositoryStore repositoryStore = getBusinessObjectRepositoryStore();
        keyWords.addAll(newArrayList(transform(repositoryStore.allBusinessObjectDao(currentJavaProject()), toDAOAccessorName())));
        return keyWords;
    }

    private BusinessObjectModelRepositoryStore getBusinessObjectRepositoryStore() {
        if (repositoryAccessor != null) {
            return repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        }
        return RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

    private Function<IType, String> toDAOAccessorName() {
        return new Function<IType, String>() {

            @Override
            public String apply(final IType input) {
                return unCapitalizeFirstLetter(input.getElementName());
            }
        };
    }

    private String unCapitalizeFirstLetter(final String elementName) {
        return Character.toLowerCase(elementName.charAt(0)) + elementName.substring(1, elementName.length());
    }

    protected IJavaProject currentJavaProject() {
        return RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
    }

    @Override
    public List<String> getAdditionalGroovyKeywords() {
        return null;
    }

    @Override
    public List<IRule> getAdditionalRules() {
        return null;
    }

}
