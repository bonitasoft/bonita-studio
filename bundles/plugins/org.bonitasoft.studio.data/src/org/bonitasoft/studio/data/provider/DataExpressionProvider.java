/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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

package org.bonitasoft.studio.data.provider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.BusinessObjectType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class DataExpressionProvider implements IExpressionProvider {

    private final ComposedAdapterFactory adapterFactory;
    private final AdapterFactoryLabelProvider adapterLabelProvider;

    public DataExpressionProvider() {
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
    }

    @Override
    public Set<Expression> getExpressions(final EObject context) {
        final Set<Expression> result = new HashSet<Expression>();

        Form form = null;
        PageFlow pf = null;
        if (context instanceof Widget) {
            form = ModelHelper.getForm((Widget) context);
        } else if (context instanceof Form) {
            form = (Form) context;
        } else if (context instanceof PageFlow) {
            pf = (PageFlow) context;
        }

        if (form != null) {
            final EObject formContainer = form.eContainer();
            if (formContainer != null) {
                if (form.eContainmentFeature() != null && (formContainer instanceof PageFlow || formContainer instanceof ViewPageFlow)) {
                    for (final Data d : getDataInForm(form, formContainer)) {
                        result.add(createExpression(d));
                    }
                }
            }
        } else if (pf != null) {
            for (final Data d : ModelHelper.getAccessibleData(pf)) {
                result.add(createExpression(d));
            }
        } else if (context instanceof EObject) {
            for (final Data d : ModelHelper.getAccessibleData(context, true)) {
                result.add(createExpression(d));
            }
        }

        final FlowElement parentFlowElement = ModelHelper.getParentFlowElement(context);
        if (parentFlowElement instanceof MultiInstantiable) {
            final MultiInstanceType type = ((MultiInstantiable) parentFlowElement).getType();
            if (type == MultiInstanceType.PARALLEL || type == MultiInstanceType.SEQUENTIAL
                    && !((MultiInstantiable) parentFlowElement).isUseCardinality()) {
                final Expression iteratorExpression = ((MultiInstantiable) parentFlowElement).getIteratorExpression();
                if (iteratorExpression != null
                        && iteratorExpression.getName() != null
                        && !iteratorExpression.getName().isEmpty()) {
                    final Data d = dataFromIteratorExpression((MultiInstantiable) parentFlowElement, iteratorExpression);
                    result.add(createExpression(d));
                }
            }
        }

        return result;
    }

    public static Data dataFromIteratorExpression(final MultiInstantiable parentFlowElement, final Expression iteratorExpression) {
        final String returnType = iteratorExpression.getReturnType();
        Data d = null;
        if (returnType != null) {
            final DataType dt = getDataTypeFrom(returnType, iteratorExpression, parentFlowElement);
            if (dt instanceof BusinessObjectType) {
                d = ProcessFactory.eINSTANCE.createBusinessObjectData();
                ((JavaObjectData) d).setClassName(returnType);
            } else if (dt instanceof JavaType) {
                d = ProcessFactory.eINSTANCE.createJavaObjectData();
                ((JavaObjectData) d).setClassName(returnType);
            } else {
                d = ProcessFactory.eINSTANCE.createData();
            }
            d.setName(iteratorExpression.getName());
            d.setDataType(dt);
        }
        return d;
    }

    private static DataType getDataTypeFrom(final String returnType, final Expression iteratorExpression, final MultiInstantiable parentFlowElement) {
        final MainProcess diagram = ModelHelper.getMainProcess(parentFlowElement);
        if (returnType.equals(Boolean.class.getName())) {
            return ModelHelper.getDataTypeForID(diagram, DataTypeLabels.booleanDataType);
        } else if (returnType.equals(String.class.getName())) {
            return ModelHelper.getDataTypeForID(diagram, DataTypeLabels.stringDataType);
        } else if (returnType.equals(Double.class.getName())) {
            return ModelHelper.getDataTypeForID(diagram, DataTypeLabels.doubleDataType);
        } else if (returnType.equals(Long.class.getName())) {
            return ModelHelper.getDataTypeForID(diagram, DataTypeLabels.longDataType);
        } else if (returnType.equals(Integer.class.getName())) {
            return ModelHelper.getDataTypeForID(diagram, DataTypeLabels.integerDataType);
        } else if (parentFlowElement.getCollectionDataToMultiInstantiate() instanceof BusinessObjectData) {
            return ModelHelper.getDataTypeForID(diagram, DataTypeLabels.businessObjectType);
        } else {
            return ModelHelper.getDataTypeForID(diagram, DataTypeLabels.javaDataType);
        }
    }

    protected List<Data> getDataInForm(final Form form, final EObject formContainer) {
        return ModelHelper.getAccessibleDataInForms(formContainer, form.eContainmentFeature());
    }

    @Override
    public String getExpressionType() {
        return ExpressionConstants.VARIABLE_TYPE;
    }

    @Override
    public Image getIcon(final Expression expression) {
        if (expression.getReferencedElements().isEmpty()) {
            return null;
        }

        final EObject reference = expression.getReferencedElements().get(0);
        return adapterLabelProvider.getImage(reference);
    }

    @Override
    public String getProposalLabel(final Expression expression) {
        return expression.getName();
    }



    private Expression createExpression(final Data d) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(getExpressionType());
        exp.setContent(d.getName());
        exp.setName(d.getName());
        exp.setReturnType(org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(d));
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(d));
        return exp;
    }

    @Override
    public boolean isRelevantFor(final EObject context) {
        return true;
    }

    @Override
    public Image getTypeIcon() {
        return Pics.getImage(PicsConstants.data);
    }

    @Override
    public String getTypeLabel() {
        return Messages.variableType;
    }

    @Override
    public IExpressionEditor getExpressionEditor(final Expression expression, final EObject context) {
        return new DataExpressionEditor();
    }



}

