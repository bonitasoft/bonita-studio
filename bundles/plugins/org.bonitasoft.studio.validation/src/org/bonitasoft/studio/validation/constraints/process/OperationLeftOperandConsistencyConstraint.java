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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * 
 * Check that the left operand update a valid and consistent data/document/...
 * 
 * @author Romain Bioteau
 * @author Baptiste Mesta
 *
 */
public class OperationLeftOperandConsistencyConstraint extends AbstractLiveValidationMarkerConstraint {


	@Override
	protected IStatus performLiveValidation(IValidationContext ctx) {
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
		return "org.bonitasoft.studio.validation.constraints.operationleftoperandconsistency";
	}

	@Override
	protected IStatus performBatchValidation(IValidationContext ctx) {
		final Operation operation = (Operation) ctx.getTarget();
		Expression leftOperand = operation.getLeftOperand();
		if(leftOperand != null && leftOperand.getContent() != null && !leftOperand.getContent().isEmpty()) {
			String type = leftOperand.getType();
			if(ExpressionConstants.VARIABLE_TYPE.equals(type)) {
				if(leftOperand.getReferencedElements().isEmpty()) {
					Widget widget = ModelHelper.getParentWidget(operation);
					if(widget == null || !widget.isReadOnly()) {
						return ctx.createFailureStatus(Messages.bind(Messages.inconsistentLeftOperand,leftOperand.getName()));
					}
				}
			}
			if(ExpressionConstants.CONSTANT_TYPE.equals(type)) {
				Widget widget = ModelHelper.getParentWidget(operation);
				if(widget == null || !widget.isReadOnly()) {
					return ctx.createFailureStatus(Messages.bind(Messages.inconsistentLeftOperand,leftOperand.getName()));
				}
			}
		}
		return ctx.createSuccessStatus();
	}

}
