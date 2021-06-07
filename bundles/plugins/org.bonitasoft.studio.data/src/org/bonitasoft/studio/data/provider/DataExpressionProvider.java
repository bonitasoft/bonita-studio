/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.data.provider;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class DataExpressionProvider implements IExpressionProvider {

    private final ComposedAdapterFactory adapterFactory;
    private final AdapterFactoryLabelProvider adapterLabelProvider;
    private final RepositoryAccessor repositoryAccessor;

    @Inject
    public DataExpressionProvider(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
    }

    @Override
    public Set<Expression> getExpressions(final EObject context) {
        final Set<Expression> result = new HashSet<>();

        for (final Data d : ModelHelper.getAccessibleData(context, true)) {
            result.add(createExpression(d));
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
                    final Data d = ExpressionHelper.dataFromIteratorExpression((MultiInstantiable) parentFlowElement,
                            iteratorExpression,
                            mainProcess(parentFlowElement));
                    result.add(createExpression(d));
                }
            }
        }

        return result;
    }

    private MainProcess mainProcess(final FlowElement parentFlowElement) {
        final MainProcess mainProcess = ModelHelper.getMainProcess(parentFlowElement);
        return mainProcess != null ? mainProcess : mainProcessFromStore(parentFlowElement);
    }

    private MainProcess mainProcessFromStore(final FlowElement parentFlowElement) {
        final Pool pool = ModelHelper.getParentPool(parentFlowElement);
        final DiagramRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        return ModelHelper.getMainProcess(repositoryStore.findProcess(pool.getName(), pool.getVersion()));
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
        return createExpression(d, getExpressionType());
    }
    
    private Expression createExpression(final Data d, String expressionType) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(expressionType);
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
        return null;
    }

}
