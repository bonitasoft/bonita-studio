/*
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.process.diagram.edit.parts;

import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * @generated
 */
public class ProcessEditPartFactory implements EditPartFactory {

	/**
	* @generated
	*/
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (ProcessVisualIDRegistry.getVisualID(view)) {

			case MainProcessEditPart.VISUAL_ID:
				return new MainProcessEditPart(view);

			case ANDGatewayEditPart.VISUAL_ID:
				return new ANDGatewayEditPart(view);

			case ANDGatewayLabelEditPart.VISUAL_ID:
				return new ANDGatewayLabelEditPart(view);

			case StartEventEditPart.VISUAL_ID:
				return new StartEventEditPart(view);

			case StartEventLabelEditPart.VISUAL_ID:
				return new StartEventLabelEditPart(view);

			case EndEventEditPart.VISUAL_ID:
				return new EndEventEditPart(view);

			case EndEventLabelEditPart.VISUAL_ID:
				return new EndEventLabelEditPart(view);

			case TaskEditPart.VISUAL_ID:
				return new TaskEditPart(view);

			case TaskNameEditPart.VISUAL_ID:
				return new TaskNameEditPart(view);

			case CallActivityEditPart.VISUAL_ID:
				return new CallActivityEditPart(view);

			case CallActivityNameEditPart.VISUAL_ID:
				return new CallActivityNameEditPart(view);

			case SubProcessEventEditPart.VISUAL_ID:
				return new SubProcessEventEditPart(view);

			case SubProcessEventLabelEditPart.VISUAL_ID:
				return new SubProcessEventLabelEditPart(view);

			case ReceiveTaskEditPart.VISUAL_ID:
				return new ReceiveTaskEditPart(view);

			case ReceiveTaskLabelEditPart.VISUAL_ID:
				return new ReceiveTaskLabelEditPart(view);

			case SendTaskEditPart.VISUAL_ID:
				return new SendTaskEditPart(view);

			case SendTaskLabelEditPart.VISUAL_ID:
				return new SendTaskLabelEditPart(view);

			case ServiceTaskEditPart.VISUAL_ID:
				return new ServiceTaskEditPart(view);

			case ServiceTaskLabelEditPart.VISUAL_ID:
				return new ServiceTaskLabelEditPart(view);

			case ScriptTaskEditPart.VISUAL_ID:
				return new ScriptTaskEditPart(view);

			case ScriptTaskLabelEditPart.VISUAL_ID:
				return new ScriptTaskLabelEditPart(view);

			case XORGatewayEditPart.VISUAL_ID:
				return new XORGatewayEditPart(view);

			case XORGatewayLabelEditPart.VISUAL_ID:
				return new XORGatewayLabelEditPart(view);

			case InclusiveGatewayEditPart.VISUAL_ID:
				return new InclusiveGatewayEditPart(view);

			case InclusiveGatewayLabelEditPart.VISUAL_ID:
				return new InclusiveGatewayLabelEditPart(view);

			case ActivityEditPart.VISUAL_ID:
				return new ActivityEditPart(view);

			case ActivityNameEditPart.VISUAL_ID:
				return new ActivityNameEditPart(view);

			case PoolEditPart.VISUAL_ID:
				return new PoolEditPart(view);

			case PoolNameEditPart.VISUAL_ID:
				return new PoolNameEditPart(view);

			case StartMessageEventEditPart.VISUAL_ID:
				return new StartMessageEventEditPart(view);

			case StartMessageEventLabelEditPart.VISUAL_ID:
				return new StartMessageEventLabelEditPart(view);

			case EndMessageEventEditPart.VISUAL_ID:
				return new EndMessageEventEditPart(view);

			case EndMessageEventLabelEditPart.VISUAL_ID:
				return new EndMessageEventLabelEditPart(view);

			case IntermediateCatchMessageEventEditPart.VISUAL_ID:
				return new IntermediateCatchMessageEventEditPart(view);

			case IntermediateCatchMessageEventLabelEditPart.VISUAL_ID:
				return new IntermediateCatchMessageEventLabelEditPart(view);

			case IntermediateThrowMessageEventEditPart.VISUAL_ID:
				return new IntermediateThrowMessageEventEditPart(view);

			case IntermediateThrowMessageEventLabelEditPart.VISUAL_ID:
				return new IntermediateThrowMessageEventLabelEditPart(view);

			case TextAnnotationEditPart.VISUAL_ID:
				return new TextAnnotationEditPart(view);

			case TextAnnotationTextEditPart.VISUAL_ID:
				return new TextAnnotationTextEditPart(view);

			case IntermediateCatchTimerEventEditPart.VISUAL_ID:
				return new IntermediateCatchTimerEventEditPart(view);

			case IntermediateCatchTimerEventLabelEditPart.VISUAL_ID:
				return new IntermediateCatchTimerEventLabelEditPart(view);

			case StartTimerEventEditPart.VISUAL_ID:
				return new StartTimerEventEditPart(view);

			case StartTimerEventLabelEditPart.VISUAL_ID:
				return new StartTimerEventLabelEditPart(view);

			case CatchLinkEventEditPart.VISUAL_ID:
				return new CatchLinkEventEditPart(view);

			case CatchLinkEventLabelEditPart.VISUAL_ID:
				return new CatchLinkEventLabelEditPart(view);

			case ThrowLinkEventEditPart.VISUAL_ID:
				return new ThrowLinkEventEditPart(view);

			case ThrowLinkEventLabelEditPart.VISUAL_ID:
				return new ThrowLinkEventLabelEditPart(view);

			case IntermediateThrowSignalEventEditPart.VISUAL_ID:
				return new IntermediateThrowSignalEventEditPart(view);

			case IntermediateThrowSignalEventLabelEditPart.VISUAL_ID:
				return new IntermediateThrowSignalEventLabelEditPart(view);

			case IntermediateCatchSignalEventEditPart.VISUAL_ID:
				return new IntermediateCatchSignalEventEditPart(view);

			case IntermediateCatchSignalEventLabelEditPart.VISUAL_ID:
				return new IntermediateCatchSignalEventLabelEditPart(view);

			case StartSignalEventEditPart.VISUAL_ID:
				return new StartSignalEventEditPart(view);

			case StartSignalEventLabelEditPart.VISUAL_ID:
				return new StartSignalEventLabelEditPart(view);

			case EndSignalEventEditPart.VISUAL_ID:
				return new EndSignalEventEditPart(view);

			case EndSignalEventLabelEditPart.VISUAL_ID:
				return new EndSignalEventLabelEditPart(view);

			case EventEditPart.VISUAL_ID:
				return new EventEditPart(view);

			case EndErrorEventEditPart.VISUAL_ID:
				return new EndErrorEventEditPart(view);

			case EndErrorEventLabelEditPart.VISUAL_ID:
				return new EndErrorEventLabelEditPart(view);

			case StartErrorEventEditPart.VISUAL_ID:
				return new StartErrorEventEditPart(view);

			case StartErrorEventLabelEditPart.VISUAL_ID:
				return new StartErrorEventLabelEditPart(view);

			case EndTerminatedEventEditPart.VISUAL_ID:
				return new EndTerminatedEventEditPart(view);

			case EndTerminatedEventLabelEditPart.VISUAL_ID:
				return new EndTerminatedEventLabelEditPart(view);

			case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEvent2EditPart(view);

			case IntermediateErrorCatchEventLabelEditPart.VISUAL_ID:
				return new IntermediateErrorCatchEventLabelEditPart(view);

			case BoundaryMessageEventEditPart.VISUAL_ID:
				return new BoundaryMessageEventEditPart(view);

			case BoundaryMessageEventLabelEditPart.VISUAL_ID:
				return new BoundaryMessageEventLabelEditPart(view);

			case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID:
				return new NonInterruptingBoundaryTimerEventEditPart(view);

			case NonInterruptingBoundaryTimerEventNameEditPart.VISUAL_ID:
				return new NonInterruptingBoundaryTimerEventNameEditPart(view);

			case BoundaryTimerEventEditPart.VISUAL_ID:
				return new BoundaryTimerEventEditPart(view);

			case BoundaryTimerEventLabelEditPart.VISUAL_ID:
				return new BoundaryTimerEventLabelEditPart(view);

			case BoundarySignalEventEditPart.VISUAL_ID:
				return new BoundarySignalEventEditPart(view);

			case BoundarySignalEventLabelEditPart.VISUAL_ID:
				return new BoundarySignalEventLabelEditPart(view);

			case IntermediateErrorCatchEventEditPart.VISUAL_ID:
				return new IntermediateErrorCatchEventEditPart(view);

			case IntermediateErrorCatchEventLabel2EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEventLabel2EditPart(view);

			case BoundaryMessageEvent2EditPart.VISUAL_ID:
				return new BoundaryMessageEvent2EditPart(view);

			case BoundaryMessageEventLabel2EditPart.VISUAL_ID:
				return new BoundaryMessageEventLabel2EditPart(view);

			case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID:
				return new NonInterruptingBoundaryTimerEvent2EditPart(view);

			case NonInterruptingBoundaryTimerEventName2EditPart.VISUAL_ID:
				return new NonInterruptingBoundaryTimerEventName2EditPart(view);

			case BoundaryTimerEvent2EditPart.VISUAL_ID:
				return new BoundaryTimerEvent2EditPart(view);

			case BoundaryTimerEventLabel2EditPart.VISUAL_ID:
				return new BoundaryTimerEventLabel2EditPart(view);

			case BoundarySignalEvent2EditPart.VISUAL_ID:
				return new BoundarySignalEvent2EditPart(view);

			case BoundarySignalEventLabel2EditPart.VISUAL_ID:
				return new BoundarySignalEventLabel2EditPart(view);

			case ANDGateway2EditPart.VISUAL_ID:
				return new ANDGateway2EditPart(view);

			case ANDGatewayLabel2EditPart.VISUAL_ID:
				return new ANDGatewayLabel2EditPart(view);

			case EndEvent2EditPart.VISUAL_ID:
				return new EndEvent2EditPart(view);

			case EndEventLabel2EditPart.VISUAL_ID:
				return new EndEventLabel2EditPart(view);

			case CallActivity2EditPart.VISUAL_ID:
				return new CallActivity2EditPart(view);

			case CallActivityName2EditPart.VISUAL_ID:
				return new CallActivityName2EditPart(view);

			case Task2EditPart.VISUAL_ID:
				return new Task2EditPart(view);

			case TaskName2EditPart.VISUAL_ID:
				return new TaskName2EditPart(view);

			case ReceiveTask2EditPart.VISUAL_ID:
				return new ReceiveTask2EditPart(view);

			case ReceiveTaskLabel2EditPart.VISUAL_ID:
				return new ReceiveTaskLabel2EditPart(view);

			case IntermediateErrorCatchEvent3EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEvent3EditPart(view);

			case IntermediateErrorCatchEventLabel3EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEventLabel3EditPart(view);

			case SendTask2EditPart.VISUAL_ID:
				return new SendTask2EditPart(view);

			case SendTaskLabel2EditPart.VISUAL_ID:
				return new SendTaskLabel2EditPart(view);

			case ServiceTask2EditPart.VISUAL_ID:
				return new ServiceTask2EditPart(view);

			case ServiceTaskLabel2EditPart.VISUAL_ID:
				return new ServiceTaskLabel2EditPart(view);

			case IntermediateErrorCatchEvent4EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEvent4EditPart(view);

			case IntermediateErrorCatchEventLabel4EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEventLabel4EditPart(view);

			case ScriptTask2EditPart.VISUAL_ID:
				return new ScriptTask2EditPart(view);

			case ScriptTaskLabel2EditPart.VISUAL_ID:
				return new ScriptTaskLabel2EditPart(view);

			case IntermediateErrorCatchEvent5EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEvent5EditPart(view);

			case IntermediateErrorCatchEventLabel5EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEventLabel5EditPart(view);

			case XORGateway2EditPart.VISUAL_ID:
				return new XORGateway2EditPart(view);

			case XORGatewayLabel2EditPart.VISUAL_ID:
				return new XORGatewayLabel2EditPart(view);

			case Activity2EditPart.VISUAL_ID:
				return new Activity2EditPart(view);

			case ActivityName2EditPart.VISUAL_ID:
				return new ActivityName2EditPart(view);

			case IntermediateErrorCatchEvent6EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEvent6EditPart(view);

			case IntermediateErrorCatchEventLabel6EditPart.VISUAL_ID:
				return new IntermediateErrorCatchEventLabel6EditPart(view);

			case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
				return new IntermediateCatchMessageEvent2EditPart(view);

			case IntermediateCatchMessageEventLabel2EditPart.VISUAL_ID:
				return new IntermediateCatchMessageEventLabel2EditPart(view);

			case StartMessageEvent2EditPart.VISUAL_ID:
				return new StartMessageEvent2EditPart(view);

			case StartMessageEventLabel2EditPart.VISUAL_ID:
				return new StartMessageEventLabel2EditPart(view);

			case EndMessageEvent2EditPart.VISUAL_ID:
				return new EndMessageEvent2EditPart(view);

			case EndMessageEventLabel2EditPart.VISUAL_ID:
				return new EndMessageEventLabel2EditPart(view);

			case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
				return new IntermediateThrowMessageEvent2EditPart(view);

			case IntermediateThrowMessageEventLabel2EditPart.VISUAL_ID:
				return new IntermediateThrowMessageEventLabel2EditPart(view);

			case TextAnnotation2EditPart.VISUAL_ID:
				return new TextAnnotation2EditPart(view);

			case TextAnnotationText2EditPart.VISUAL_ID:
				return new TextAnnotationText2EditPart(view);

			case StartTimerEvent2EditPart.VISUAL_ID:
				return new StartTimerEvent2EditPart(view);

			case StartTimerEventLabel2EditPart.VISUAL_ID:
				return new StartTimerEventLabel2EditPart(view);

			case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
				return new IntermediateCatchTimerEvent2EditPart(view);

			case IntermediateCatchTimerEventLabel2EditPart.VISUAL_ID:
				return new IntermediateCatchTimerEventLabel2EditPart(view);

			case StartSignalEvent2EditPart.VISUAL_ID:
				return new StartSignalEvent2EditPart(view);

			case StartSignalEventLabel2EditPart.VISUAL_ID:
				return new StartSignalEventLabel2EditPart(view);

			case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
				return new IntermediateThrowSignalEvent2EditPart(view);

			case IntermediateThrowSignalEventLabel2EditPart.VISUAL_ID:
				return new IntermediateThrowSignalEventLabel2EditPart(view);

			case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
				return new IntermediateCatchSignalEvent2EditPart(view);

			case IntermediateCatchSignalEventLabel2EditPart.VISUAL_ID:
				return new IntermediateCatchSignalEventLabel2EditPart(view);

			case EndSignalEvent2EditPart.VISUAL_ID:
				return new EndSignalEvent2EditPart(view);

			case EndSignalEventLabel2EditPart.VISUAL_ID:
				return new EndSignalEventLabel2EditPart(view);

			case EndErrorEvent2EditPart.VISUAL_ID:
				return new EndErrorEvent2EditPart(view);

			case EndErrorEventLabel2EditPart.VISUAL_ID:
				return new EndErrorEventLabel2EditPart(view);

			case EndTerminatedEvent2EditPart.VISUAL_ID:
				return new EndTerminatedEvent2EditPart(view);

			case EndTerminatedEventLabel2EditPart.VISUAL_ID:
				return new EndTerminatedEventLabel2EditPart(view);

			case StartErrorEvent2EditPart.VISUAL_ID:
				return new StartErrorEvent2EditPart(view);

			case StartErrorEventLabel2EditPart.VISUAL_ID:
				return new StartErrorEventLabel2EditPart(view);

			case Event2EditPart.VISUAL_ID:
				return new Event2EditPart(view);

			case InclusiveGateway2EditPart.VISUAL_ID:
				return new InclusiveGateway2EditPart(view);

			case InclusiveGatewayLabel2EditPart.VISUAL_ID:
				return new InclusiveGatewayLabel2EditPart(view);

			case LaneEditPart.VISUAL_ID:
				return new LaneEditPart(view);

			case LaneNameEditPart.VISUAL_ID:
				return new LaneNameEditPart(view);

			case StartEvent2EditPart.VISUAL_ID:
				return new StartEvent2EditPart(view);

			case StartEventLabel2EditPart.VISUAL_ID:
				return new StartEventLabel2EditPart(view);

			case SubProcessEvent2EditPart.VISUAL_ID:
				return new SubProcessEvent2EditPart(view);

			case SubProcessEventLabel2EditPart.VISUAL_ID:
				return new SubProcessEventLabel2EditPart(view);

			case ThrowLinkEvent2EditPart.VISUAL_ID:
				return new ThrowLinkEvent2EditPart(view);

			case ThrowLinkEventLabel2EditPart.VISUAL_ID:
				return new ThrowLinkEventLabel2EditPart(view);

			case CatchLinkEvent2EditPart.VISUAL_ID:
				return new CatchLinkEvent2EditPart(view);

			case CatchLinkEventLabel2EditPart.VISUAL_ID:
				return new CatchLinkEventLabel2EditPart(view);

			case SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID:
				return new SubProcessEventSubProcessCompartmentEditPart(view);

			case PoolPoolCompartmentEditPart.VISUAL_ID:
				return new PoolPoolCompartmentEditPart(view);

			case LaneLaneCompartmentEditPart.VISUAL_ID:
				return new LaneLaneCompartmentEditPart(view);

			case SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID:
				return new SubProcessEventSubProcessCompartment2EditPart(view);

			case SequenceFlowEditPart.VISUAL_ID:
				return new SequenceFlowEditPart(view);

			case SequenceFlowNameEditPart.VISUAL_ID:
				return new SequenceFlowNameEditPart(view);

			case MessageFlowEditPart.VISUAL_ID:
				return new MessageFlowEditPart(view);

			case MessageFlowLabelEditPart.VISUAL_ID:
				return new MessageFlowLabelEditPart(view);

			case TextAnnotationAttachmentEditPart.VISUAL_ID:
				return new TextAnnotationAttachmentEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	* @generated
	*/
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	* @generated
	*/
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	* @generated
	*/
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		* @generated
		*/
		private WrappingLabel wrapLabel;

		/**
		* @generated
		*/
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		* @generated
		*/
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		* @generated
		*/
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				if (getWrapLabel().isTextWrapOn() && getWrapLabel().getText().length() > 0) {
					//Adjust editor location
					rect.x = rect.x - 5;
					if (rect.width < 75) {
						rect.width = 75;
					}
					rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
				} else {
					int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
					rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
				}
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	* @generated
	*/
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		* @generated
		*/
		private Label label;

		/**
		* @generated
		*/
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		* @generated
		*/
		public Label getLabel() {
			return label;
		}

		/**
		* @generated
		*/
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
