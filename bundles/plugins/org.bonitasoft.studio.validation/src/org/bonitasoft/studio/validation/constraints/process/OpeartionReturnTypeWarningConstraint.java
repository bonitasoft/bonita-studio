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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.operation.OperationReturnTypesValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.Info;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * 
 * @author Romain Bioteau
 *
 */
public class OpeartionReturnTypeWarningConstraint extends AbstractLiveValidationMarkerConstraint {

	private final OperationReturnTypesValidator validator =  new OperationReturnTypesValidator();

	@Override
	protected IStatus performLiveValidation(IValidationContext ctx) {
		final EStructuralFeature featureTriggered = ctx.getFeature();
		if(featureTriggered.equals(ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE)){
			final Expression expression = (Expression) ctx.getTarget();
			if(expression.eContainer() instanceof Operation){
				final Operation op = (Operation) expression.eContainer();
				if(op.eContainingFeature().equals(ProcessPackage.Literals.CONNECTOR__OUTPUTS)){
					return ctx.createSuccessStatus();
				}
				if (op.eContainer() instanceof FileWidget){
					FileWidget fileWidget = (FileWidget)op.eContainer();
					if (fileWidget.isDownloadOnly()){
						return ctx.createSuccessStatus();
					}
				}
				validator.setDataExpression(op.getLeftOperand());
				final IStatus status = validator.validate(op.getRightOperand());
				if(!status.isOK()){
					FlowElement el = ModelHelper.getParentFlowElement(op);
					String activityName = null;
					if(el != null){
						activityName = el.getName();
					}
					if(activityName == null){
						return ctx.createFailureStatus(new Object[] {status.getMessage()});
					}else{
						return ctx.createFailureStatus(new Object[] {Messages.bind(Messages.incompatilbeOperationReturnType, status.getMessage(), activityName) });
					}

				}
			}
		}
		return ctx.createSuccessStatus();
	}

	@Override
	protected String getMarkerType(DiagramEditor editor) {
		if(editor instanceof ProcessDiagramEditor){
			return ProcessMarkerNavigationProvider.MARKER_TYPE;
		}else if(editor instanceof FormDiagramEditor){
			return org.bonitasoft.studio.model.process.diagram.form.providers.ProcessMarkerNavigationProvider.MARKER_TYPE;
		}
		return null;
	}

	@Override
	protected String getConstraintId() {
		return "org.bonitasoft.studio.validation.constraints.operationreturntype";
	}

	@Override
	protected IStatus performBatchValidation(IValidationContext ctx) {
		final Expression expression = (Expression) ctx.getTarget();
		if(!ModelHelper.isAnExpressionCopy(expression) && expression.eContainer() instanceof Operation){
			final Operation op = (Operation) expression.eContainer();
			if(op.eContainingFeature().equals(FormPackage.Literals.WIDGET__ACTION)){
				Widget parentWidget = ModelHelper.getParentWidget(op);
				if(parentWidget instanceof FormButton 
						|| parentWidget instanceof Info){
					return ctx.createSuccessStatus();
				}
			}
			if(op.eContainingFeature().equals(ProcessPackage.Literals.CONNECTOR__OUTPUTS)){
				return ctx.createSuccessStatus();
			}
			if (op.eContainer() instanceof FileWidget){
				FileWidget fileWidget = (FileWidget)op.eContainer();
				if (fileWidget.isDownloadOnly()){
					return ctx.createSuccessStatus();
				}
			}
			if(op.getLeftOperand() == null 
					|| op.getLeftOperand().getContent() == null 
					|| op.getLeftOperand().getContent().isEmpty()){
				if(op.getRightOperand() != null 
						&& op.getRightOperand() .getContent() != null 
						&& !op.getRightOperand() .getContent().isEmpty()){
					Element el = ModelHelper.getParentElement(op);
					String activityName = null;
					if(el != null){
						activityName = el.getName();
					}
					return ctx.createFailureStatus(Messages.bind(Messages.leftOperandMissing,activityName));
				}
			}
			validator.setDataExpression(op.getLeftOperand());
			final IStatus status = validator.validate(op.getRightOperand());
			if(!status.isOK()){
				FlowElement el = ModelHelper.getParentFlowElement(op);
				String activityName = null;
				if(el != null){
					activityName = el.getName();
				}
				if(activityName == null){
					return ctx.createFailureStatus(new Object[] {status.getMessage()});
				}else{
					return ctx.createFailureStatus(new Object[] {Messages.bind(Messages.incompatilbeOperationReturnType, status.getMessage(), activityName) });
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
