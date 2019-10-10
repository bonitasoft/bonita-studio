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

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.StringInputStream;

/**
 * @author Romain Bioteau
 *
 */
public class XtextComparisonExpressionLoader {

    private IModelSearch modelSearch;
    private ConditionModelGlobalScopeProvider globalScopeProvider;
    private IXtextResourceProvider xTextResourceProvider;

    public XtextComparisonExpressionLoader(final ConditionModelGlobalScopeProvider globalScopeProvider,
            IModelSearch modelSearch, IXtextResourceProvider xTextResourceProvider) {
        this.globalScopeProvider = globalScopeProvider;
        this.modelSearch = modelSearch;
        this.xTextResourceProvider = xTextResourceProvider;
    }

    public Operation_Compare loadConditionExpression(final String comparisonExpression, final EObject context) throws ComparisonExpressionLoadException {
        final Resource resource = loadResource(comparisonExpression, context);
        final EList<EObject> contents = resource.getContents();
        if (contents.isEmpty()) {
            throw new ComparisonExpressionLoadException("Failed to load comparison expression " + comparisonExpression);
        }
        if (context != null && context.eResource() != null) {
            return resolveProxies(resource, context.eResource().getResourceSet());
        }
        return (Operation_Compare) contents.get(0);
    }

    public List<String> getAccessibleReferences(final EObject context) {
        final List<String> accessibleObjects = new ArrayList<String>();
        for (final Data d : modelSearch.getAccessibleData(context)) {
            accessibleObjects.add(modelSearch.getEObjectID(d));
        }
        final AbstractProcess process = modelSearch.getDirectParentOfType(context, AbstractProcess.class);
        if (process != null) {
            for (final Parameter p : process.getParameters()) {
                accessibleObjects.add(modelSearch.getEObjectID(p));
            }
        }
        return accessibleObjects;
    }

    public Resource loadResource(final String comparisonExpression, final EObject context) throws ComparisonExpressionLoadException {
        final Resource resource = xTextResourceProvider.getResource(context);
        try (InputStream inputStream = new StringInputStream(requireNonNull(comparisonExpression), "UTF-8")) {
            final Map<String, Object> options = new HashMap<>();
            options.put(XtextResource.OPTION_ENCODING, "UTF-8");
            resource.load(inputStream, options);
        } catch (final IOException e) {
            throw new ComparisonExpressionLoadException("Failed to load Xtext resource.", e);
        }
        globalScopeProvider.setAccessibleEObjects(getAccessibleReferences(context));
        return resource;
    }


    public Operation_Compare resolveProxies(final Resource resource, final ResourceSet resourceSet) {
        EcoreUtil2.resolveLazyCrossReferences(resource, CancelIndicator.NullImpl);
        final Operation_Compare compareOp = (Operation_Compare) resource.getContents().get(0);
        final List<Expression_ProcessRef> allRefs = modelSearch.getAllItemsOfType(compareOp, Expression_ProcessRef.class);
        for (final Expression_ProcessRef ref : allRefs) {
            final EObject proxy = ref.getValue();
            if (proxy.eIsProxy()) {
                ref.eSet(ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF__VALUE, EcoreUtil.resolve(proxy, resourceSet));
            }
        }
        return compareOp;
    }

}
