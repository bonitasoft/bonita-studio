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

import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Iterators.forArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.engine.bdm.dao.BusinessObjectDAO;
import org.bonitasoft.engine.expression.ExpressionConstants;
import org.bonitasoft.forms.server.api.IFormExpressionsAPI;
import org.bonitasoft.forms.server.validator.AbstractFormValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.codehaus.groovy.eclipse.editor.highlighting.IHighlightingExtender;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IRegion;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.rules.IRule;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * @author Romain Bioteau
 */
public class BonitaSyntaxHighlighting implements IHighlightingExtender {

    private static final String BDM_CLIENT_POJO_JAR_NAME = "bdm-client-pojo.jar";
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
        BONITA_KEYWORDS.add(AbstractFormValidator.CLICKED_BUTTON_VARNAME);
        BONITA_KEYWORDS.add(ExpressionConstants.TASK_ASSIGNEE_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(IFormExpressionsAPI.USER_LOCALE);
    }

    public BonitaSyntaxHighlighting() {
    }

    @Override
    public List<String> getAdditionalGJDKKeywords() {
        final List<String> keyWords = new ArrayList<String>(BONITA_KEYWORDS);
        keyWords.addAll(allBusinessObjectDao());
        return keyWords;
    }

    protected List<String> allBusinessObjectDao() {
        final List<String> result = new ArrayList<String>();
        final IJavaProject javaProject = currentJavaProject();

        IType daoType = null;
        try {
            daoType = javaProject.findType(BusinessObjectDAO.class.getName());
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(String.format("Failed to retrieve %s type", BusinessObjectDAO.class.getName()), e);
        }
        final ITypeHierarchy newTypeHierarchy = typeHierarchy(javaProject, daoType);
        if (newTypeHierarchy != null) {
            for (final IType t : newTypeHierarchy.getAllSubtypes(daoType)) {
                result.add(unCapitalizeFirstLetter(t.getElementName()));
            }
        }
        return result;
    }

    protected ITypeHierarchy typeHierarchy(final IJavaProject javaProject, final IType daoType) {
        IRegion newRegion = null;
        try {
            newRegion = regionWithBDM(javaProject);
        } catch (final JavaModelException e) {
            BonitaStudioLog.error("Failed to compute region for BDM", e);
        }
        ITypeHierarchy newTypeHierarchy = null;
        try {
            newTypeHierarchy = javaProject.newTypeHierarchy(daoType, newRegion,
                    Repository.NULL_PROGRESS_MONITOR);
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(String.format("Failed to compute %s hierarchy", daoType.getElementName()), e);
        }
        return newTypeHierarchy;
    }

    private String unCapitalizeFirstLetter(final String elementName) {
        return Character.toLowerCase(elementName.charAt(0)) + elementName.substring(1, elementName.length());
    }

    protected IJavaProject currentJavaProject() {
        return RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
    }

    protected IRegion regionWithBDM(final IJavaProject javaProject) throws JavaModelException {
        final IRegion newRegion = JavaCore.newRegion();
        final IClasspathEntry repositoryDependenciesClasspathEntry = find(asIterable(javaProject.getRawClasspath()), repositoryDependenciesEntry(), null);
        final IPackageFragmentRoot[] fragmentRoots = javaProject.findPackageFragmentRoots(repositoryDependenciesClasspathEntry);
        final IPackageFragmentRoot packageFragmentRoot = find(asIterable(fragmentRoots), withElementName(BDM_CLIENT_POJO_JAR_NAME), null);
        if (packageFragmentRoot != null) {
            newRegion.add(packageFragmentRoot);
        }
        return newRegion;
    }

    private Predicate<IPackageFragmentRoot> withElementName(final String elementName) {
        return new Predicate<IPackageFragmentRoot>() {

            @Override
            public boolean apply(final IPackageFragmentRoot input) {
                return elementName.equals(input.getElementName());
            }
        };
    }

    private <T> Iterable<T> asIterable(final T[] elements) {
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                if (elements == null) {
                    return Iterators.emptyIterator();
                }
                return forArray(elements);
            }
        };
    }

    protected Predicate<IClasspathEntry> repositoryDependenciesEntry() {
        return new Predicate<IClasspathEntry>() {

            @Override
            public boolean apply(final IClasspathEntry input) {
                return input.getPath().equals(new Path("repositoryDependencies"));
            }
        };
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
