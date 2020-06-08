/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * @author Aurelie Zara
 *
 */
public class TestConnectorOutputWizardPage extends
AbstractConnectorOutputWizardPage {

	List<ExpressionViewer>resultViewers;
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.connectors.ui.wizard.page.AbstractConnectorOutputWizardPage#doCreateControl(org.eclipse.swt.widgets.Composite, org.eclipse.emf.databinding.EMFDataBindingContext)
	 */
	@Override
	protected Control doCreateControl(final Composite parent,
			final EMFDataBindingContext context) {

		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create()) ;

		final AvailableExpressionTypeFilter connectorOutputFilter =  new AvailableExpressionTypeFilter(new String[]{
				ExpressionConstants.CONNECTOR_OUTPUT_TYPE,
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.SCRIPT_TYPE
		}) ;
		final List<Operation> outputOperations = getConnector().getOutputs();

		for (final Operation output:outputOperations){
			final Label expressionLabel = new Label(mainComposite, SWT.READ_ONLY);
			expressionLabel.setText(Messages.connectorExpressionViewerLabel);


			final ExpressionViewer outputExpressionViewer = new ExpressionViewer(mainComposite, SWT.BORDER, ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND);
			outputExpressionViewer.getControl().setLayoutData( GridDataFactory.fillDefaults().grab(true, false).create());
			outputExpressionViewer.addFilter(connectorOutputFilter);
			outputExpressionViewer.setContext(getConnector());
			outputExpressionViewer.setMessage(Messages.connectorExpressionViewerMessage);
			outputExpressionViewer.setExternalDataBindingContext(context);
			outputExpressionViewer.setInput(output);
            outputExpressionViewer.addExpressionValidator(new IExpressionValidator() {

				private Expression inputExpression;

				@Override
				public IStatus validate(final Object value) {
					final Expression exp = inputExpression;
					if(exp.getType().equals(ExpressionConstants.SCRIPT_TYPE) || exp.getType().equals(ExpressionConstants.CONNECTOR_OUTPUT_TYPE)) {
						return ValidationStatus.ok();
					}
					return ValidationStatus.error(Messages.connectorTypeValidationMessage);
				}

				@Override
				public void setInputExpression(final Expression inputExpression) {
					this.inputExpression = inputExpression;
				}

				@Override
				public void setDomain(final EditingDomain domain) {

				}

				@Override
				public void setContext(final EObject context) {

				}

                @Override
                public boolean isRelevantForExpressionType(final String type) {
                    return true;
                }
			});

			context.bindValue(ViewersObservables.observeSingleSelection(outputExpressionViewer), EMFObservables.observeValue(output, ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND));
			outputExpressionViewer.setProposalsFiltering(false);
		}
		return mainComposite ;
	}
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(final boolean isOverviewContext) {

	}

}
