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
package org.bonitasoft.studio.exporter.bpmn.transfo.expression;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.exporter.bpmn.transfo.data.DataScope;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TItemDefinition;

/**
 * @author Romain Bioteau
 *
 */
public class VariableFormalExpressionTransformer extends FormalExpressionTransformer {

    private final DataScope dataScope;

    public VariableFormalExpressionTransformer(final DataScope dataScope) {
        this.dataScope = dataScope;
    }

    @Override
    protected TFormalExpression addContent(final Expression bonitaExpression, final TFormalExpression formalExpression) {
        checkNotNull(bonitaExpression);
        checkNotNull(formalExpression);
        checkArgument(ExpressionConstants.VARIABLE_TYPE.equals(bonitaExpression.getType()), "Expression type is invalid. Expected %s but was %s",
                ExpressionConstants.VARIABLE_TYPE, bonitaExpression.getType());
        final EList<EObject> referencedElements = bonitaExpression.getReferencedElements();
        checkArgument(!referencedElements.isEmpty(), "Missing referenced elements for variable expression %s", bonitaExpression.getName());

        final Data bonitaData = (Data) referencedElements.get(0);
        if (bonitaData != null) {
            final TItemDefinition bpmnData = dataScope.get(ModelHelper.getDataReferencedInExpression(bonitaData));
            if (bonitaData.isTransient()) {
                if (bpmnData != null) {
                    FeatureMapUtil.addText(formalExpression.getMixed(), "getActivityProperty('"
                            + ((Element) bonitaData.eContainer().eContainer().eContainer().eContainer()).getName() + "','" + bpmnData.getId() + "')");
                } else {//fallback
                    FeatureMapUtil.addText(formalExpression.getMixed(), "getActivityProperty('"
                            + ((Element) bonitaData.eContainer().eContainer().eContainer().eContainer()).getName() + "','" + bonitaExpression.getContent()
                            + "')");
                }
            } else {
                if (bpmnData != null) {
                    FeatureMapUtil.addText(formalExpression.getMixed(), "getDataObject('" + bonitaData.getName() + "')");
                } else {//fallback
                    FeatureMapUtil.addText(formalExpression.getMixed(), "getDataObject('" + bonitaExpression.getContent() + "')");
                }
            }
        }
        return formalExpression;
    }

}
