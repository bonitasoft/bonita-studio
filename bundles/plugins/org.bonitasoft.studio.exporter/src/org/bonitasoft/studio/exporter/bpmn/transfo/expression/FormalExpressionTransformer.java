/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter.bpmn.transfo.expression;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.xml.namespace.QName;

import org.bonitasoft.studio.exporter.bpmn.transfo.BPMNConstants;
import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMNFunction;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TFormalExpression;

/**
 * @author Romain Bioteau
 */
public class FormalExpressionTransformer implements BonitaToBPMNFunction<Expression, TFormalExpression>, BPMNConstants {

    @Override
    public TFormalExpression transform(final Expression bonitaExpression) {
        checkNotNull(bonitaExpression);
        final TFormalExpression formalExpression = newTFormalExpression();
        formalExpression.setId(EcoreUtil.generateUUID());
        formalExpression.setEvaluatesToTypeRef(newQName(JAVA_XMLNS, bonitaExpression.getReturnType()));
        formalExpression.setLanguage(getFormalExpressionLanguage());
        return addContent(bonitaExpression, formalExpression);
    }

    protected QName newQName(final String nsPrefix, final String typeRef) {
        return QName.valueOf(nsPrefix + ":" + typeRef);
    }

    protected TFormalExpression newTFormalExpression() {
        return ModelFactory.eINSTANCE.createTFormalExpression();
    }

    protected TFormalExpression addContent(final Expression bonitaExpression, final TFormalExpression formalExpression) {
        FeatureMapUtil.addText(formalExpression.getMixed(), bonitaExpression.getContent());
        return formalExpression;
    }

    protected String getFormalExpressionLanguage() {
        return XPATH_LANGUAGE_NS;
    }

}
