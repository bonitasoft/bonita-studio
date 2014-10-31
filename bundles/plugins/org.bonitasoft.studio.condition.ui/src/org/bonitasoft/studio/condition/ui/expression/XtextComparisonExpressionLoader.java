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
package org.bonitasoft.studio.condition.ui.expression;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.StringInputStream;

import com.google.inject.Injector;

/**
 * @author Romain Bioteau
 *
 */
public class XtextComparisonExpressionLoader {

    private final Injector injector;

    public XtextComparisonExpressionLoader(final Injector injector) {
        this.injector = injector;
    }

    public Operation_Compare loadConditionExpression(final String comparisonExpression, final EObject context) throws ComparisonExpressionLoadException {
        final Resource resource = loadResource(comparisonExpression, context);
        final EList<EObject> contents = resource.getContents();
        if (contents.isEmpty()) {
            throw new ComparisonExpressionLoadException("Failed to laod comparison expression " + comparisonExpression);
        }
        if (context != null && context.eResource() != null) {
            return resolveProxies(resource, context.eResource().getResourceSet());
        }
        return (Operation_Compare) contents.get(0);
    }

    protected IFile createTmpFile(final String content, final IProject project) throws IOException {
        final IFile file = project.getFile("somefile.cmodel");
        if (file.exists()) {
            try {
                file.delete(true, null);
            } catch (final CoreException e) {
                throw new IOException(e);
            }
        }
        if (content != null && !content.isEmpty()) {
            final InputStream is = new StringInputStream(content, "UTF-8");
            try {
                file.create(is, true, null);
            } catch (final CoreException e) {
                throw new IOException(e);
            }
        }
        return file;
    }

    protected List<String> getAccessibleReferences(final EObject context) {
        final List<String> accessibleObjects = new ArrayList<String>();
        for (final Data d : ModelHelper.getAccessibleData(context)) {
            accessibleObjects.add(ModelHelper.getEObjectID(d));
        }
        final AbstractProcess process = ModelHelper.getParentProcess(context);
        if (process != null) {
            for (final Parameter p : process.getParameters()) {
                accessibleObjects.add(ModelHelper.getEObjectID(p));
            }
        }
        return accessibleObjects;
    }

    public Resource loadResource(final String comparisonExpression, final EObject context) throws ComparisonExpressionLoadException {
        final XtextResourceSetProvider xtextResourceSetProvider = injector.getInstance(XtextResourceSetProvider.class);
        final IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        final ResourceSet resourceSet = xtextResourceSetProvider.get(project);
        IFile file;
        try {
            file = createTmpFile(comparisonExpression, project);
        } catch (final IOException e) {
            throw new ComparisonExpressionLoadException("Failed to create a temporary file for comparison expression " + comparisonExpression, e);
        }
        Resource resource = null;
        if (file.exists()) {
            resource = resourceSet.getResource(URI.createPlatformResourceURI(file.getFullPath().toOSString(), true), true);
        } else {
            resource = resourceSet.createResource(URI.createPlatformResourceURI(file.getFullPath().toOSString(), true));
        }

        final ConditionModelGlobalScopeProvider globalScopeProvider = injector.getInstance(ConditionModelGlobalScopeProvider.class);
        globalScopeProvider.setAccessibleEObjects(getAccessibleReferences(context));
        return resource;
    }


    public Operation_Compare resolveProxies(final Resource resource, final ResourceSet resourceSet) {
        EcoreUtil2.resolveLazyCrossReferences(resource, CancelIndicator.NullImpl);
        final Operation_Compare compareOp = (Operation_Compare) resource.getContents().get(0);
        final List<Expression_ProcessRef> allRefs = ModelHelper.getAllItemsOfType(compareOp, ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF);
        for (final Expression_ProcessRef ref : allRefs) {
            final EObject proxy = ref.getValue();
            if (proxy.eIsProxy()) {
                ref.eSet(ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF__VALUE, EcoreUtil.resolve(proxy, resourceSet));
            }
        }
        return compareOp;
    }

}
