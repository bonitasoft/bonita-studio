/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;


public class ComparisonExpressionUtil {

    public static Collection<? extends EObject> computeReferencedElement(final EObject context, final Resource resource) {
        final Collection<EObject> res = new HashSet<EObject>();
        final Operation_Compare compareOp = (Operation_Compare) resource.getContents().get(0);
        if(compareOp != null){
            final List<Expression_ProcessRef> references = ModelHelper.getAllItemsOfType(compareOp, ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF);
            for(final Expression_ProcessRef ref : references){
                final EObject dep = getResolvedDependency(context, ref);
                res.add(ExpressionHelper.createDependencyFromEObject(dep));
            }
        }
        return res;
    }

    public static EObject getResolvedDependency(final EObject context, final Expression_ProcessRef ref) {
        EObject dep = resolveProxy(context, ref.getValue());
        final List<EObject> orignalDep = ModelHelper.getAllItemsOfType( ModelHelper.getMainProcess(context), dep.eClass());
        for(final EObject d : orignalDep){
            if(EcoreUtil.equals(dep, d)){
                dep = d;
                break;
            }
        }
        return dep;
    }

    private static EObject resolveProxy(final EObject context, final EObject ref) {
        ResourceSet rSet = null;
        if(ref.eIsProxy()){
            rSet =context.eResource().getResourceSet();
        }
        final EObject dep = EcoreUtil2.resolve(ref, rSet);
        if(rSet != null){
            rSet.getResources().remove(ref.eResource());
        }
        return dep;
    }
}
