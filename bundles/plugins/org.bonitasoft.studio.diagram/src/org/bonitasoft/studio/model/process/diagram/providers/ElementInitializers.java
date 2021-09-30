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
package org.bonitasoft.studio.model.process.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.BoundarySignalEvent;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.EndMessageEvent;
import org.bonitasoft.studio.model.process.EndSignalEvent;
import org.bonitasoft.studio.model.process.EndTerminatedEvent;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.InclusiveGateway;
import org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent;
import org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.ScriptTask;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.ServiceTask;
import org.bonitasoft.studio.model.process.StartErrorEvent;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.XORGateway;
import org.bonitasoft.studio.model.process.decision.DecisionFactory;
import org.bonitasoft.studio.model.process.decision.DecisionTable;
import org.bonitasoft.studio.model.process.diagram.expressions.ProcessOCLFactory;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	* @generated
	*/
	public void init_ANDGateway_2009(ANDGateway instance) {
		try {
			Object value_0 = name_ANDGateway_2009(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ANDGateway_2009(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ANDGateway_2009(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ANDGateway_2009(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartEvent_2002(StartEvent instance) {
		try {
			Object value_0 = name_StartEvent_2002(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartEvent_2002(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartEvent_2002(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartEvent_2002(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndEvent_2003(EndEvent instance) {
		try {
			Object value_0 = name_EndEvent_2003(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndEvent_2003(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndEvent_2003(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndEvent_2003(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Task_2004(Task instance) {
		try {
			Object value_0 = name_Task_2004(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = overrideActorsOfTheLane_Task_2004(instance);
			instance.setOverrideActorsOfTheLane(((java.lang.Boolean) value_1).booleanValue());
			Object value_2 = dynamicDescription_Task_2004(instance);
			instance.setDynamicDescription((Expression) value_2);
			Object value_3 = dynamicLabel_Task_2004(instance);
			instance.setDynamicLabel((Expression) value_3);
			Object value_4 = stepSummary_Task_2004(instance);
			instance.setStepSummary((Expression) value_4);
			Object value_5 = loopCondition_Task_2004(instance);
			instance.setLoopCondition((Expression) value_5);
			Object value_6 = loopMaximum_Task_2004(instance);
			instance.setLoopMaximum((Expression) value_6);
			Object value_7 = cardinalityExpression_Task_2004(instance);
			instance.setCardinalityExpression((Expression) value_7);
			Object value_8 = completionCondition_Task_2004(instance);
			instance.setCompletionCondition((Expression) value_8);
			Object value_9 = iteratorExpression_Task_2004(instance);
			instance.setIteratorExpression((Expression) value_9);
			Object value_10 = contract_Task_2004(instance);
			instance.setContract((Contract) value_10);
			Object value_11 = formMapping_Task_2004(instance);
			instance.setFormMapping((FormMapping) value_11);
			Object value_12 = expectedDuration_Task_2004(instance);
			instance.setExpectedDuration((Expression) value_12);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_CallActivity_2036(CallActivity instance) {
		try {
			Object value_0 = name_CallActivity_2036(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_CallActivity_2036(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_CallActivity_2036(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_CallActivity_2036(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = calledActivityName_CallActivity_2036(instance);
			instance.setCalledActivityName((Expression) value_4);
			Object value_5 = calledActivityVersion_CallActivity_2036(instance);
			instance.setCalledActivityVersion((Expression) value_5);
			Object value_6 = loopCondition_CallActivity_2036(instance);
			instance.setLoopCondition((Expression) value_6);
			Object value_7 = loopMaximum_CallActivity_2036(instance);
			instance.setLoopMaximum((Expression) value_7);
			Object value_8 = cardinalityExpression_CallActivity_2036(instance);
			instance.setCardinalityExpression((Expression) value_8);
			Object value_9 = completionCondition_CallActivity_2036(instance);
			instance.setCompletionCondition((Expression) value_9);
			Object value_10 = iteratorExpression_CallActivity_2036(instance);
			instance.setIteratorExpression((Expression) value_10);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SubProcessEvent_2031(SubProcessEvent instance) {
		try {
			Object value_0 = name_SubProcessEvent_2031(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ReceiveTask_2025(ReceiveTask instance) {
		try {
			Object value_0 = name_ReceiveTask_2025(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ReceiveTask_2025(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ReceiveTask_2025(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ReceiveTask_2025(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_ReceiveTask_2025(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_ReceiveTask_2025(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_ReceiveTask_2025(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_ReceiveTask_2025(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_ReceiveTask_2025(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SendTask_2026(SendTask instance) {
		try {
			Object value_0 = name_SendTask_2026(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_SendTask_2026(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_SendTask_2026(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_SendTask_2026(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_SendTask_2026(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_SendTask_2026(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_SendTask_2026(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_SendTask_2026(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_SendTask_2026(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ServiceTask_2027(ServiceTask instance) {
		try {
			Object value_0 = name_ServiceTask_2027(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ServiceTask_2027(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ServiceTask_2027(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ServiceTask_2027(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_ServiceTask_2027(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_ServiceTask_2027(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_ServiceTask_2027(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_ServiceTask_2027(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_ServiceTask_2027(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ScriptTask_2028(ScriptTask instance) {
		try {
			Object value_0 = name_ScriptTask_2028(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ScriptTask_2028(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ScriptTask_2028(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ScriptTask_2028(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_ScriptTask_2028(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_ScriptTask_2028(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_ScriptTask_2028(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_ScriptTask_2028(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_ScriptTask_2028(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_XORGateway_2008(XORGateway instance) {
		try {
			Object value_0 = name_XORGateway_2008(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_XORGateway_2008(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_XORGateway_2008(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_XORGateway_2008(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_InclusiveGateway_2030(InclusiveGateway instance) {
		try {
			Object value_0 = name_InclusiveGateway_2030(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_InclusiveGateway_2030(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_InclusiveGateway_2030(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_InclusiveGateway_2030(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Activity_2006(Activity instance) {
		try {
			Object value_0 = name_Activity_2006(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_Activity_2006(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_Activity_2006(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_Activity_2006(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_Activity_2006(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_Activity_2006(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_Activity_2006(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_Activity_2006(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_Activity_2006(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Pool_2007(Pool instance) {
		try {
			Object value_0 = name_Pool_2007(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = ProcessOCLFactory.getExpression(0, ProcessPackage.eINSTANCE.getPool(), null)
					.evaluate(instance);
			instance.setVersion((java.lang.String) value_1);
			Object value_2 = searchIndexes_Pool_2007(instance);
			if (value_2 instanceof Collection) {
				instance.getSearchIndexes().clear();
				instance.getSearchIndexes().addAll(((Collection) value_2));
			} else {
				instance.getSearchIndexes().add((SearchIndex) value_2);
			}
			Object value_3 = formMapping_Pool_2007(instance);
			instance.setFormMapping((FormMapping) value_3);
			Object value_4 = overviewFormMapping_Pool_2007(instance);
			instance.setOverviewFormMapping((FormMapping) value_4);
			Object value_5 = contract_Pool_2007(instance);
			instance.setContract((Contract) value_5);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartMessageEvent_2010(StartMessageEvent instance) {
		try {
			Object value_0 = name_StartMessageEvent_2010(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartMessageEvent_2010(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartMessageEvent_2010(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartMessageEvent_2010(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndMessageEvent_2011(EndMessageEvent instance) {
		try {
			Object value_0 = name_EndMessageEvent_2011(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndMessageEvent_2011(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndMessageEvent_2011(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndMessageEvent_2011(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateCatchMessageEvent_2013(IntermediateCatchMessageEvent instance) {
		try {
			Object value_0 = name_IntermediateCatchMessageEvent_2013(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateCatchMessageEvent_2013(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateCatchMessageEvent_2013(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateCatchMessageEvent_2013(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateThrowMessageEvent_2014(IntermediateThrowMessageEvent instance) {
		try {
			Object value_0 = name_IntermediateThrowMessageEvent_2014(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateThrowMessageEvent_2014(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateThrowMessageEvent_2014(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateThrowMessageEvent_2014(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_TextAnnotation_2015(TextAnnotation instance) {
		try {
			Object value_0 = text_TextAnnotation_2015(instance);
			instance.setText((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateCatchTimerEvent_2017(IntermediateCatchTimerEvent instance) {
		try {
			Object value_0 = name_IntermediateCatchTimerEvent_2017(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateCatchTimerEvent_2017(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateCatchTimerEvent_2017(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateCatchTimerEvent_2017(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartTimerEvent_2016(StartTimerEvent instance) {
		try {
			Object value_0 = name_StartTimerEvent_2016(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartTimerEvent_2016(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartTimerEvent_2016(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartTimerEvent_2016(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_CatchLinkEvent_2018(CatchLinkEvent instance) {
		try {
			Object value_0 = name_CatchLinkEvent_2018(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_CatchLinkEvent_2018(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_CatchLinkEvent_2018(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_CatchLinkEvent_2018(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ThrowLinkEvent_2019(ThrowLinkEvent instance) {
		try {
			Object value_0 = name_ThrowLinkEvent_2019(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ThrowLinkEvent_2019(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ThrowLinkEvent_2019(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ThrowLinkEvent_2019(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateThrowSignalEvent_2020(IntermediateThrowSignalEvent instance) {
		try {
			Object value_0 = name_IntermediateThrowSignalEvent_2020(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateThrowSignalEvent_2020(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateThrowSignalEvent_2020(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateThrowSignalEvent_2020(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateCatchSignalEvent_2021(IntermediateCatchSignalEvent instance) {
		try {
			Object value_0 = name_IntermediateCatchSignalEvent_2021(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateCatchSignalEvent_2021(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateCatchSignalEvent_2021(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateCatchSignalEvent_2021(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartSignalEvent_2022(StartSignalEvent instance) {
		try {
			Object value_0 = name_StartSignalEvent_2022(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartSignalEvent_2022(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartSignalEvent_2022(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartSignalEvent_2022(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndSignalEvent_2023(EndSignalEvent instance) {
		try {
			Object value_0 = name_EndSignalEvent_2023(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndSignalEvent_2023(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndSignalEvent_2023(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndSignalEvent_2023(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndErrorEvent_2029(EndErrorEvent instance) {
		try {
			Object value_0 = name_EndErrorEvent_2029(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndErrorEvent_2029(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndErrorEvent_2029(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndErrorEvent_2029(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartErrorEvent_2033(StartErrorEvent instance) {
		try {
			Object value_0 = name_StartErrorEvent_2033(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartErrorEvent_2033(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartErrorEvent_2033(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartErrorEvent_2033(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndTerminatedEvent_2035(EndTerminatedEvent instance) {
		try {
			Object value_0 = name_EndTerminatedEvent_2035(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndTerminatedEvent_2035(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndTerminatedEvent_2035(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndTerminatedEvent_2035(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateErrorCatchEvent_3029(IntermediateErrorCatchEvent instance) {
		try {
			Object value_0 = name_IntermediateErrorCatchEvent_3029(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_BoundaryMessageEvent_3035(BoundaryMessageEvent instance) {
		try {
			Object value_0 = name_BoundaryMessageEvent_3035(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_NonInterruptingBoundaryTimerEvent_3064(NonInterruptingBoundaryTimerEvent instance) {
		try {
			Object value_0 = name_NonInterruptingBoundaryTimerEvent_3064(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_BoundaryTimerEvent_3043(BoundaryTimerEvent instance) {
		try {
			Object value_0 = name_BoundaryTimerEvent_3043(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_BoundarySignalEvent_3052(BoundarySignalEvent instance) {
		try {
			Object value_0 = name_BoundarySignalEvent_3052(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateErrorCatchEvent_3030(IntermediateErrorCatchEvent instance) {
		try {
			Object value_0 = name_IntermediateErrorCatchEvent_3030(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_BoundaryMessageEvent_3036(BoundaryMessageEvent instance) {
		try {
			Object value_0 = name_BoundaryMessageEvent_3036(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_NonInterruptingBoundaryTimerEvent_3065(NonInterruptingBoundaryTimerEvent instance) {
		try {
			Object value_0 = name_NonInterruptingBoundaryTimerEvent_3065(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_BoundaryTimerEvent_3044(BoundaryTimerEvent instance) {
		try {
			Object value_0 = name_BoundaryTimerEvent_3044(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_BoundarySignalEvent_3053(BoundarySignalEvent instance) {
		try {
			Object value_0 = name_BoundarySignalEvent_3053(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ANDGateway_3009(ANDGateway instance) {
		try {
			Object value_0 = name_ANDGateway_3009(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ANDGateway_3009(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ANDGateway_3009(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ANDGateway_3009(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndEvent_3003(EndEvent instance) {
		try {
			Object value_0 = name_EndEvent_3003(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndEvent_3003(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndEvent_3003(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndEvent_3003(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_CallActivity_3063(CallActivity instance) {
		try {
			Object value_0 = name_CallActivity_3063(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_CallActivity_3063(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_CallActivity_3063(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_CallActivity_3063(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = calledActivityName_CallActivity_3063(instance);
			instance.setCalledActivityName((Expression) value_4);
			Object value_5 = calledActivityVersion_CallActivity_3063(instance);
			instance.setCalledActivityVersion((Expression) value_5);
			Object value_6 = loopCondition_CallActivity_3063(instance);
			instance.setLoopCondition((Expression) value_6);
			Object value_7 = loopMaximum_CallActivity_3063(instance);
			instance.setLoopMaximum((Expression) value_7);
			Object value_8 = cardinalityExpression_CallActivity_3063(instance);
			instance.setCardinalityExpression((Expression) value_8);
			Object value_9 = completionCondition_CallActivity_3063(instance);
			instance.setCompletionCondition((Expression) value_9);
			Object value_10 = iteratorExpression_CallActivity_3063(instance);
			instance.setIteratorExpression((Expression) value_10);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Task_3005(Task instance) {
		try {
			Object value_0 = name_Task_3005(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = overrideActorsOfTheLane_Task_3005(instance);
			instance.setOverrideActorsOfTheLane(((java.lang.Boolean) value_1).booleanValue());
			Object value_2 = dynamicDescription_Task_3005(instance);
			instance.setDynamicDescription((Expression) value_2);
			Object value_3 = dynamicLabel_Task_3005(instance);
			instance.setDynamicLabel((Expression) value_3);
			Object value_4 = stepSummary_Task_3005(instance);
			instance.setStepSummary((Expression) value_4);
			Object value_5 = loopCondition_Task_3005(instance);
			instance.setLoopCondition((Expression) value_5);
			Object value_6 = loopMaximum_Task_3005(instance);
			instance.setLoopMaximum((Expression) value_6);
			Object value_7 = cardinalityExpression_Task_3005(instance);
			instance.setCardinalityExpression((Expression) value_7);
			Object value_8 = completionCondition_Task_3005(instance);
			instance.setCompletionCondition((Expression) value_8);
			Object value_9 = iteratorExpression_Task_3005(instance);
			instance.setIteratorExpression((Expression) value_9);
			Object value_10 = contract_Task_3005(instance);
			instance.setContract((Contract) value_10);
			Object value_11 = formMapping_Task_3005(instance);
			instance.setFormMapping((FormMapping) value_11);
			Object value_12 = expectedDuration_Task_3005(instance);
			instance.setExpectedDuration((Expression) value_12);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ReceiveTask_3026(ReceiveTask instance) {
		try {
			Object value_0 = name_ReceiveTask_3026(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ReceiveTask_3026(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ReceiveTask_3026(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ReceiveTask_3026(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_ReceiveTask_3026(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_ReceiveTask_3026(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_ReceiveTask_3026(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_ReceiveTask_3026(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_ReceiveTask_3026(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateErrorCatchEvent_3031(IntermediateErrorCatchEvent instance) {
		try {
			Object value_0 = name_IntermediateErrorCatchEvent_3031(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SendTask_3025(SendTask instance) {
		try {
			Object value_0 = name_SendTask_3025(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_SendTask_3025(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_SendTask_3025(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_SendTask_3025(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_SendTask_3025(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_SendTask_3025(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_SendTask_3025(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_SendTask_3025(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_SendTask_3025(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ServiceTask_3027(ServiceTask instance) {
		try {
			Object value_0 = name_ServiceTask_3027(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ServiceTask_3027(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ServiceTask_3027(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ServiceTask_3027(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_ServiceTask_3027(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_ServiceTask_3027(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_ServiceTask_3027(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_ServiceTask_3027(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_ServiceTask_3027(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateErrorCatchEvent_3032(IntermediateErrorCatchEvent instance) {
		try {
			Object value_0 = name_IntermediateErrorCatchEvent_3032(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ScriptTask_3028(ScriptTask instance) {
		try {
			Object value_0 = name_ScriptTask_3028(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ScriptTask_3028(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ScriptTask_3028(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ScriptTask_3028(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_ScriptTask_3028(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_ScriptTask_3028(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_ScriptTask_3028(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_ScriptTask_3028(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_ScriptTask_3028(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateErrorCatchEvent_3033(IntermediateErrorCatchEvent instance) {
		try {
			Object value_0 = name_IntermediateErrorCatchEvent_3033(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_XORGateway_3008(XORGateway instance) {
		try {
			Object value_0 = name_XORGateway_3008(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_XORGateway_3008(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_XORGateway_3008(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_XORGateway_3008(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Activity_3006(Activity instance) {
		try {
			Object value_0 = name_Activity_3006(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_Activity_3006(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_Activity_3006(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_Activity_3006(instance);
			instance.setStepSummary((Expression) value_3);
			Object value_4 = loopCondition_Activity_3006(instance);
			instance.setLoopCondition((Expression) value_4);
			Object value_5 = loopMaximum_Activity_3006(instance);
			instance.setLoopMaximum((Expression) value_5);
			Object value_6 = cardinalityExpression_Activity_3006(instance);
			instance.setCardinalityExpression((Expression) value_6);
			Object value_7 = completionCondition_Activity_3006(instance);
			instance.setCompletionCondition((Expression) value_7);
			Object value_8 = iteratorExpression_Activity_3006(instance);
			instance.setIteratorExpression((Expression) value_8);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateErrorCatchEvent_3034(IntermediateErrorCatchEvent instance) {
		try {
			Object value_0 = name_IntermediateErrorCatchEvent_3034(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateCatchMessageEvent_3013(IntermediateCatchMessageEvent instance) {
		try {
			Object value_0 = name_IntermediateCatchMessageEvent_3013(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateCatchMessageEvent_3013(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateCatchMessageEvent_3013(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateCatchMessageEvent_3013(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartMessageEvent_3012(StartMessageEvent instance) {
		try {
			Object value_0 = name_StartMessageEvent_3012(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartMessageEvent_3012(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartMessageEvent_3012(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartMessageEvent_3012(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndMessageEvent_3011(EndMessageEvent instance) {
		try {
			Object value_0 = name_EndMessageEvent_3011(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndMessageEvent_3011(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndMessageEvent_3011(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndMessageEvent_3011(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateThrowMessageEvent_3014(IntermediateThrowMessageEvent instance) {
		try {
			Object value_0 = name_IntermediateThrowMessageEvent_3014(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateThrowMessageEvent_3014(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateThrowMessageEvent_3014(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateThrowMessageEvent_3014(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_TextAnnotation_3015(TextAnnotation instance) {
		try {
			Object value_0 = text_TextAnnotation_3015(instance);
			instance.setText((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartTimerEvent_3016(StartTimerEvent instance) {
		try {
			Object value_0 = name_StartTimerEvent_3016(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartTimerEvent_3016(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartTimerEvent_3016(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartTimerEvent_3016(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateCatchTimerEvent_3017(IntermediateCatchTimerEvent instance) {
		try {
			Object value_0 = name_IntermediateCatchTimerEvent_3017(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateCatchTimerEvent_3017(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateCatchTimerEvent_3017(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateCatchTimerEvent_3017(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartSignalEvent_3023(StartSignalEvent instance) {
		try {
			Object value_0 = name_StartSignalEvent_3023(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartSignalEvent_3023(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartSignalEvent_3023(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartSignalEvent_3023(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateThrowSignalEvent_3022(IntermediateThrowSignalEvent instance) {
		try {
			Object value_0 = name_IntermediateThrowSignalEvent_3022(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateThrowSignalEvent_3022(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateThrowSignalEvent_3022(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateThrowSignalEvent_3022(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_IntermediateCatchSignalEvent_3021(IntermediateCatchSignalEvent instance) {
		try {
			Object value_0 = name_IntermediateCatchSignalEvent_3021(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_IntermediateCatchSignalEvent_3021(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_IntermediateCatchSignalEvent_3021(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_IntermediateCatchSignalEvent_3021(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndSignalEvent_3020(EndSignalEvent instance) {
		try {
			Object value_0 = name_EndSignalEvent_3020(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndSignalEvent_3020(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndSignalEvent_3020(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndSignalEvent_3020(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndErrorEvent_3050(EndErrorEvent instance) {
		try {
			Object value_0 = name_EndErrorEvent_3050(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndErrorEvent_3050(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndErrorEvent_3050(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndErrorEvent_3050(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_EndTerminatedEvent_3062(EndTerminatedEvent instance) {
		try {
			Object value_0 = name_EndTerminatedEvent_3062(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_EndTerminatedEvent_3062(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_EndTerminatedEvent_3062(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_EndTerminatedEvent_3062(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartErrorEvent_3060(StartErrorEvent instance) {
		try {
			Object value_0 = name_StartErrorEvent_3060(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartErrorEvent_3060(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartErrorEvent_3060(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartErrorEvent_3060(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_InclusiveGateway_3051(InclusiveGateway instance) {
		try {
			Object value_0 = name_InclusiveGateway_3051(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_InclusiveGateway_3051(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_InclusiveGateway_3051(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_InclusiveGateway_3051(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_Lane_3007(Lane instance) {
		try {
			Object value_0 = name_Lane_3007(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_StartEvent_3002(StartEvent instance) {
		try {
			Object value_0 = name_StartEvent_3002(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_StartEvent_3002(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_StartEvent_3002(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_StartEvent_3002(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SubProcessEvent_3058(SubProcessEvent instance) {
		try {
			Object value_0 = name_SubProcessEvent_3058(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_ThrowLinkEvent_3018(ThrowLinkEvent instance) {
		try {
			Object value_0 = name_ThrowLinkEvent_3018(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_ThrowLinkEvent_3018(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_ThrowLinkEvent_3018(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_ThrowLinkEvent_3018(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_CatchLinkEvent_3019(CatchLinkEvent instance) {
		try {
			Object value_0 = name_CatchLinkEvent_3019(instance);
			instance.setName((java.lang.String) value_0);
			Object value_1 = dynamicDescription_CatchLinkEvent_3019(instance);
			instance.setDynamicDescription((Expression) value_1);
			Object value_2 = dynamicLabel_CatchLinkEvent_3019(instance);
			instance.setDynamicLabel((Expression) value_2);
			Object value_3 = stepSummary_CatchLinkEvent_3019(instance);
			instance.setStepSummary((Expression) value_3);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_SequenceFlow_4001(SequenceFlow instance) {
		try {
			Object value_0 = decisionTable_SequenceFlow_4001(instance);
			instance.setDecisionTable((DecisionTable) value_0);
			Object value_1 = condition_SequenceFlow_4001(instance);
			instance.setCondition((Expression) value_1);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	public void init_MessageFlow_4002(MessageFlow instance) {
		try {
			Object value_0 = name_MessageFlow_4002(instance);
			instance.setName((java.lang.String) value_0);
		} catch (RuntimeException e) {
			ProcessDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	* @generated
	*/
	private java.lang.String name_ANDGateway_2009(ANDGateway self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ANDGateway_2009(ANDGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ANDGateway_2009(ANDGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ANDGateway_2009(ANDGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartEvent_2002(StartEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartEvent_2002(StartEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartEvent_2002(StartEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartEvent_2002(StartEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndEvent_2003(EndEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndEvent_2003(EndEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndEvent_2003(EndEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndEvent_2003(EndEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_Task_2004(Task self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.Boolean overrideActorsOfTheLane_Task_2004(Task self) {
		return !(self.eContainer() instanceof Lane);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_Task_2004(Task self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_Task_2004(Task self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_Task_2004(Task self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_Task_2004(Task self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_Task_2004(Task self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_Task_2004(Task self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_Task_2004(Task self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_Task_2004(Task self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private Contract contract_Task_2004(Task self) {
		return ProcessFactory.eINSTANCE.createContract();
	}

	/**
	* @generated
	*/
	private FormMapping formMapping_Task_2004(Task self) {
		FormMapping mapping = ProcessFactory.eINSTANCE.createFormMapping();
		mapping.setTargetForm(ExpressionHelper.createFormReferenceExpression("", ""));
		return mapping;
	}

	/**
	* @generated
	*/
	private Expression expectedDuration_Task_2004(Task self) {
		return ExpressionHelper.createExpression("", "", ExpressionConstants.CONSTANT_TYPE, Long.class.getName(), true);
	}

	/**
	* @generated
	*/
	private java.lang.String name_CallActivity_2036(CallActivity self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_CallActivity_2036(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_CallActivity_2036(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_CallActivity_2036(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression calledActivityName_CallActivity_2036(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression calledActivityVersion_CallActivity_2036(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_CallActivity_2036(CallActivity self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_CallActivity_2036(CallActivity self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_CallActivity_2036(CallActivity self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_CallActivity_2036(CallActivity self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_CallActivity_2036(CallActivity self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_SubProcessEvent_2031(SubProcessEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_ReceiveTask_2025(ReceiveTask self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ReceiveTask_2025(ReceiveTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ReceiveTask_2025(ReceiveTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ReceiveTask_2025(ReceiveTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_ReceiveTask_2025(ReceiveTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_ReceiveTask_2025(ReceiveTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_ReceiveTask_2025(ReceiveTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_ReceiveTask_2025(ReceiveTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_ReceiveTask_2025(ReceiveTask self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_SendTask_2026(SendTask self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_SendTask_2026(SendTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_SendTask_2026(SendTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_SendTask_2026(SendTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_SendTask_2026(SendTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_SendTask_2026(SendTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_SendTask_2026(SendTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_SendTask_2026(SendTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_SendTask_2026(SendTask self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_ServiceTask_2027(ServiceTask self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ServiceTask_2027(ServiceTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ServiceTask_2027(ServiceTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ServiceTask_2027(ServiceTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_ServiceTask_2027(ServiceTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_ServiceTask_2027(ServiceTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_ServiceTask_2027(ServiceTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_ServiceTask_2027(ServiceTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_ServiceTask_2027(ServiceTask self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_ScriptTask_2028(ScriptTask self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ScriptTask_2028(ScriptTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ScriptTask_2028(ScriptTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ScriptTask_2028(ScriptTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_ScriptTask_2028(ScriptTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_ScriptTask_2028(ScriptTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_ScriptTask_2028(ScriptTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_ScriptTask_2028(ScriptTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_ScriptTask_2028(ScriptTask self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_XORGateway_2008(XORGateway self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_XORGateway_2008(XORGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_XORGateway_2008(XORGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_XORGateway_2008(XORGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_InclusiveGateway_2030(InclusiveGateway self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_InclusiveGateway_2030(InclusiveGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_InclusiveGateway_2030(InclusiveGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_InclusiveGateway_2030(InclusiveGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_Activity_2006(Activity self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_Activity_2006(Activity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_Activity_2006(Activity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_Activity_2006(Activity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_Activity_2006(Activity self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_Activity_2006(Activity self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_Activity_2006(Activity self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_Activity_2006(Activity self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_Activity_2006(Activity self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_Pool_2007(Pool self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private List searchIndexes_Pool_2007(Pool self) {
		List<SearchIndex> searchIndexs = new ArrayList<SearchIndex>(5);
		for (int i = 0; i < 5; i++) {
			SearchIndex searchIndex = ProcessFactory.eINSTANCE.createSearchIndex();
			Expression name = ExpressionFactory.eINSTANCE.createExpression();
			name.setContent("");
			name.setType(ExpressionConstants.CONSTANT_TYPE);
			name.setReturnType(String.class.getName());
			name.setReturnTypeFixed(true);
			searchIndex.setName(name);
			Expression value = ExpressionFactory.eINSTANCE.createExpression();
			value.setContent("");
			value.setReturnType(String.class.getName());
			value.setReturnTypeFixed(true);
			searchIndex.setValue(value);
			searchIndexs.add(searchIndex);
		}
		return searchIndexs;
	}

	/**
	* @generated
	*/
	private FormMapping formMapping_Pool_2007(Pool self) {
		FormMapping mapping = ProcessFactory.eINSTANCE.createFormMapping();
		mapping.setTargetForm(ExpressionHelper.createFormReferenceExpression("", ""));
		return mapping;
	}

	/**
	* @generated
	*/
	private FormMapping overviewFormMapping_Pool_2007(Pool self) {
		FormMapping mapping = ProcessFactory.eINSTANCE.createFormMapping();
		mapping.setTargetForm(ExpressionHelper.createFormReferenceExpression("", ""));
		return mapping;
	}

	/**
	* @generated
	*/
	private Contract contract_Pool_2007(Pool self) {
		return ProcessFactory.eINSTANCE.createContract();
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartMessageEvent_2010(StartMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartMessageEvent_2010(StartMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartMessageEvent_2010(StartMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartMessageEvent_2010(StartMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndMessageEvent_2011(EndMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndMessageEvent_2011(EndMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndMessageEvent_2011(EndMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndMessageEvent_2011(EndMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateCatchMessageEvent_2013(IntermediateCatchMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateCatchMessageEvent_2013(IntermediateCatchMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateCatchMessageEvent_2013(IntermediateCatchMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateCatchMessageEvent_2013(IntermediateCatchMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateThrowMessageEvent_2014(IntermediateThrowMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateThrowMessageEvent_2014(IntermediateThrowMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateThrowMessageEvent_2014(IntermediateThrowMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateThrowMessageEvent_2014(IntermediateThrowMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String text_TextAnnotation_2015(TextAnnotation self) {
		return "";
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateCatchTimerEvent_2017(IntermediateCatchTimerEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateCatchTimerEvent_2017(IntermediateCatchTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateCatchTimerEvent_2017(IntermediateCatchTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateCatchTimerEvent_2017(IntermediateCatchTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartTimerEvent_2016(StartTimerEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartTimerEvent_2016(StartTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartTimerEvent_2016(StartTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartTimerEvent_2016(StartTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_CatchLinkEvent_2018(CatchLinkEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_CatchLinkEvent_2018(CatchLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_CatchLinkEvent_2018(CatchLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_CatchLinkEvent_2018(CatchLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_ThrowLinkEvent_2019(ThrowLinkEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ThrowLinkEvent_2019(ThrowLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ThrowLinkEvent_2019(ThrowLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ThrowLinkEvent_2019(ThrowLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateThrowSignalEvent_2020(IntermediateThrowSignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateThrowSignalEvent_2020(IntermediateThrowSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateThrowSignalEvent_2020(IntermediateThrowSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateThrowSignalEvent_2020(IntermediateThrowSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateCatchSignalEvent_2021(IntermediateCatchSignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateCatchSignalEvent_2021(IntermediateCatchSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateCatchSignalEvent_2021(IntermediateCatchSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateCatchSignalEvent_2021(IntermediateCatchSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartSignalEvent_2022(StartSignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartSignalEvent_2022(StartSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartSignalEvent_2022(StartSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartSignalEvent_2022(StartSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndSignalEvent_2023(EndSignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndSignalEvent_2023(EndSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndSignalEvent_2023(EndSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndSignalEvent_2023(EndSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndErrorEvent_2029(EndErrorEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndErrorEvent_2029(EndErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndErrorEvent_2029(EndErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndErrorEvent_2029(EndErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartErrorEvent_2033(StartErrorEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartErrorEvent_2033(StartErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartErrorEvent_2033(StartErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartErrorEvent_2033(StartErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndTerminatedEvent_2035(EndTerminatedEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndTerminatedEvent_2035(EndTerminatedEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndTerminatedEvent_2035(EndTerminatedEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndTerminatedEvent_2035(EndTerminatedEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateErrorCatchEvent_3029(IntermediateErrorCatchEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_BoundaryMessageEvent_3035(BoundaryMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_NonInterruptingBoundaryTimerEvent_3064(NonInterruptingBoundaryTimerEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_BoundaryTimerEvent_3043(BoundaryTimerEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_BoundarySignalEvent_3052(BoundarySignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateErrorCatchEvent_3030(IntermediateErrorCatchEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_BoundaryMessageEvent_3036(BoundaryMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_NonInterruptingBoundaryTimerEvent_3065(NonInterruptingBoundaryTimerEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_BoundaryTimerEvent_3044(BoundaryTimerEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_BoundarySignalEvent_3053(BoundarySignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_ANDGateway_3009(ANDGateway self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ANDGateway_3009(ANDGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ANDGateway_3009(ANDGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ANDGateway_3009(ANDGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndEvent_3003(EndEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndEvent_3003(EndEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndEvent_3003(EndEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndEvent_3003(EndEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_CallActivity_3063(CallActivity self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_CallActivity_3063(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_CallActivity_3063(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_CallActivity_3063(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression calledActivityName_CallActivity_3063(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression calledActivityVersion_CallActivity_3063(CallActivity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_CallActivity_3063(CallActivity self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_CallActivity_3063(CallActivity self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_CallActivity_3063(CallActivity self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_CallActivity_3063(CallActivity self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_CallActivity_3063(CallActivity self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_Task_3005(Task self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.Boolean overrideActorsOfTheLane_Task_3005(Task self) {
		return !(self.eContainer() instanceof Lane);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_Task_3005(Task self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_Task_3005(Task self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_Task_3005(Task self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_Task_3005(Task self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_Task_3005(Task self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_Task_3005(Task self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_Task_3005(Task self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_Task_3005(Task self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private Contract contract_Task_3005(Task self) {
		return ProcessFactory.eINSTANCE.createContract();
	}

	/**
	* @generated
	*/
	private FormMapping formMapping_Task_3005(Task self) {
		FormMapping mapping = ProcessFactory.eINSTANCE.createFormMapping();
		mapping.setTargetForm(ExpressionHelper.createFormReferenceExpression("", ""));
		return mapping;
	}

	/**
	* @generated
	*/
	private Expression expectedDuration_Task_3005(Task self) {
		return ExpressionHelper.createExpression("", "", ExpressionConstants.CONSTANT_TYPE, Long.class.getName(), true);
	}

	/**
	* @generated
	*/
	private java.lang.String name_ReceiveTask_3026(ReceiveTask self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ReceiveTask_3026(ReceiveTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ReceiveTask_3026(ReceiveTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ReceiveTask_3026(ReceiveTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_ReceiveTask_3026(ReceiveTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_ReceiveTask_3026(ReceiveTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_ReceiveTask_3026(ReceiveTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_ReceiveTask_3026(ReceiveTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_ReceiveTask_3026(ReceiveTask self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateErrorCatchEvent_3031(IntermediateErrorCatchEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_SendTask_3025(SendTask self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_SendTask_3025(SendTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_SendTask_3025(SendTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_SendTask_3025(SendTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_SendTask_3025(SendTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_SendTask_3025(SendTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_SendTask_3025(SendTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_SendTask_3025(SendTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_SendTask_3025(SendTask self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_ServiceTask_3027(ServiceTask self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ServiceTask_3027(ServiceTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ServiceTask_3027(ServiceTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ServiceTask_3027(ServiceTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_ServiceTask_3027(ServiceTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_ServiceTask_3027(ServiceTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_ServiceTask_3027(ServiceTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_ServiceTask_3027(ServiceTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_ServiceTask_3027(ServiceTask self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateErrorCatchEvent_3032(IntermediateErrorCatchEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_ScriptTask_3028(ScriptTask self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ScriptTask_3028(ScriptTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ScriptTask_3028(ScriptTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ScriptTask_3028(ScriptTask self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_ScriptTask_3028(ScriptTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_ScriptTask_3028(ScriptTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_ScriptTask_3028(ScriptTask self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_ScriptTask_3028(ScriptTask self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_ScriptTask_3028(ScriptTask self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateErrorCatchEvent_3033(IntermediateErrorCatchEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_XORGateway_3008(XORGateway self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_XORGateway_3008(XORGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_XORGateway_3008(XORGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_XORGateway_3008(XORGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_Activity_3006(Activity self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_Activity_3006(Activity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_Activity_3006(Activity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_Activity_3006(Activity self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression loopCondition_Activity_3006(Activity self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression loopMaximum_Activity_3006(Activity self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression cardinalityExpression_Activity_3006(Activity self) {
		return NamingUtils.generateConstantExpression("", Integer.class.getName(), true);

	}

	/**
	* @generated
	*/
	private Expression completionCondition_Activity_3006(Activity self) {
		return NamingUtils.generateConstantExpression("", Boolean.class.getName(), true);
	}

	/**
	* @generated
	*/
	private Expression iteratorExpression_Activity_3006(Activity self) {
		return ExpressionHelper.createExpression("multiInstanceIterator", "multiInstanceIterator",
				ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE, Object.class.getName(), false);
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateErrorCatchEvent_3034(IntermediateErrorCatchEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateCatchMessageEvent_3013(IntermediateCatchMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateCatchMessageEvent_3013(IntermediateCatchMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateCatchMessageEvent_3013(IntermediateCatchMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateCatchMessageEvent_3013(IntermediateCatchMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartMessageEvent_3012(StartMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartMessageEvent_3012(StartMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartMessageEvent_3012(StartMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartMessageEvent_3012(StartMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndMessageEvent_3011(EndMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndMessageEvent_3011(EndMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndMessageEvent_3011(EndMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndMessageEvent_3011(EndMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateThrowMessageEvent_3014(IntermediateThrowMessageEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateThrowMessageEvent_3014(IntermediateThrowMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateThrowMessageEvent_3014(IntermediateThrowMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateThrowMessageEvent_3014(IntermediateThrowMessageEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String text_TextAnnotation_3015(TextAnnotation self) {
		return "";
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartTimerEvent_3016(StartTimerEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartTimerEvent_3016(StartTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartTimerEvent_3016(StartTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartTimerEvent_3016(StartTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateCatchTimerEvent_3017(IntermediateCatchTimerEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateCatchTimerEvent_3017(IntermediateCatchTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateCatchTimerEvent_3017(IntermediateCatchTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateCatchTimerEvent_3017(IntermediateCatchTimerEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartSignalEvent_3023(StartSignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartSignalEvent_3023(StartSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartSignalEvent_3023(StartSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartSignalEvent_3023(StartSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateThrowSignalEvent_3022(IntermediateThrowSignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateThrowSignalEvent_3022(IntermediateThrowSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateThrowSignalEvent_3022(IntermediateThrowSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateThrowSignalEvent_3022(IntermediateThrowSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_IntermediateCatchSignalEvent_3021(IntermediateCatchSignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_IntermediateCatchSignalEvent_3021(IntermediateCatchSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_IntermediateCatchSignalEvent_3021(IntermediateCatchSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_IntermediateCatchSignalEvent_3021(IntermediateCatchSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndSignalEvent_3020(EndSignalEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndSignalEvent_3020(EndSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndSignalEvent_3020(EndSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndSignalEvent_3020(EndSignalEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndErrorEvent_3050(EndErrorEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndErrorEvent_3050(EndErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndErrorEvent_3050(EndErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndErrorEvent_3050(EndErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_EndTerminatedEvent_3062(EndTerminatedEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_EndTerminatedEvent_3062(EndTerminatedEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_EndTerminatedEvent_3062(EndTerminatedEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_EndTerminatedEvent_3062(EndTerminatedEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartErrorEvent_3060(StartErrorEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartErrorEvent_3060(StartErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartErrorEvent_3060(StartErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartErrorEvent_3060(StartErrorEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_InclusiveGateway_3051(InclusiveGateway self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_InclusiveGateway_3051(InclusiveGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_InclusiveGateway_3051(InclusiveGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_InclusiveGateway_3051(InclusiveGateway self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_Lane_3007(Lane self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_StartEvent_3002(StartEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_StartEvent_3002(StartEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_StartEvent_3002(StartEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_StartEvent_3002(StartEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_SubProcessEvent_3058(SubProcessEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private java.lang.String name_ThrowLinkEvent_3018(ThrowLinkEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_ThrowLinkEvent_3018(ThrowLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_ThrowLinkEvent_3018(ThrowLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_ThrowLinkEvent_3018(ThrowLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private java.lang.String name_CatchLinkEvent_3019(CatchLinkEvent self) {
		return NamingUtils.getInstance(self).generateName(self, self);
	}

	/**
	* @generated
	*/
	private Expression dynamicDescription_CatchLinkEvent_3019(CatchLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression dynamicLabel_CatchLinkEvent_3019(CatchLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private Expression stepSummary_CatchLinkEvent_3019(CatchLinkEvent self) {
		Expression exp = NamingUtils.generateConstantExpression("");
		exp.setReturnType(String.class.getName());
		exp.setReturnTypeFixed(true);
		return exp;
	}

	/**
	* @generated
	*/
	private DecisionTable decisionTable_SequenceFlow_4001(SequenceFlow self) {
		return DecisionFactory.eINSTANCE.createDecisionTable();
	}

	/**
	* @generated
	*/
	private Expression condition_SequenceFlow_4001(SequenceFlow self) {
		Expression expression = ExpressionFactory.eINSTANCE.createExpression();
		expression.setName("");
		expression.setType(ExpressionConstants.CONSTANT_TYPE);
		expression.setReturnType(java.lang.Boolean.class.getName());
		expression.setReturnTypeFixed(true);
		return expression;
	}

	/**
	* @generated
	*/
	private java.lang.String name_MessageFlow_4002(MessageFlow self) {
		return NamingUtils.getEventDefaultId(self);
	}

	/**
	* @generated
	*/
	public static ElementInitializers getInstance() {
		ElementInitializers cached = ProcessDiagramEditorPlugin.getInstance().getElementInitializers();
		if (cached == null) {
			ProcessDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
