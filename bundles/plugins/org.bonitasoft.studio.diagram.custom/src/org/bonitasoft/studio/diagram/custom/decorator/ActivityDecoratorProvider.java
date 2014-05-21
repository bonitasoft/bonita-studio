/**
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

package org.bonitasoft.studio.diagram.custom.decorator;

import org.bonitasoft.studio.diagram.custom.decorator.subprocessevent.ErrorSubProcessEventStartDecorator;
import org.bonitasoft.studio.diagram.custom.decorator.subprocessevent.MessageSubProcessEventDecorator;
import org.bonitasoft.studio.diagram.custom.decorator.subprocessevent.SignalSubProcessEventDecorator;
import org.bonitasoft.studio.diagram.custom.decorator.subprocessevent.TimerSubProcessEventDecorator;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;


/**
 * @author Romain Bioteau
 * @author Aurelien Pupier: integrate decorator for SubProcess Evenemential
 * Decorator provider for the review decorator class
 */
public class ActivityDecoratorProvider
extends AbstractProvider
implements IDecoratorProvider {

    /** The key used for the timer decoration */
    public static final String TIMER = "Timer_Decorator"; //$NON-NLS-1$
    /** The key used for the connector decoration */
    public static final String CONNECTOR = "Connector_Decorator"; //$NON-NLS-1$
    /** The key used for the task decoration */
    public static final String HUMAN_TASK = "Human_Task_Decorator"; //$NON-NLS-1$
    /** The key used for the subprocess decoration */
    public static final String SUBPROCESS_DECORATOR = "Subprocess_Decorator"; //$NON-NLS-1$
    /** The key used for the multi-instance decoration */
    public static final String MULTIINSTANCE_DECORATOR = "MultiInstance_Decorator"; //$NON-NLS-1$
    /** The key used for the multi-instance sequential decoration */
    public static final String MULTIINSTANCE_DECORATOR_SEQUENTIAL = "MultiInstance_Decorator_Decorator"; //$NON-NLS-1$

    public static final String MULTIINSTANCE_SUBPROCESS_DECORATOR = "MultiInstance_Subprocess_Decorator"; //$NON-NLS-1$
    public static final String MULTIINSTANCE_SEQUENTIAL_SUBPROCESS_DECORATOR = "MultiInstance_Sequential_Subprocess_Decorator"; //$NON-NLS-1$

    public static final String LOOP_SUBPROCESS_DECORATOR = "Loop_Subprocess_Decorator"; //$NON-NLS-1$

    public static final String LOOP_DECORATOR = "Loop_Decorator"; //$NON-NLS-1$

    /** The key used for the receive task decoration */
    public static final String RECEIVETASK_DECORATOR = "ReceiveTask_Decorator"; //$NON-NLS-1$
    /** The key used for the receive task decoration */
    public static final String SENDTASK_DECORATOR = "SendTask_Decorator"; //$NON-NLS-1$
    /** The key used for the receive task decoration */
    public static final String SERVICETASK_DECORATOR = "ServiceTask_Decorator"; //$NON-NLS-1$
    /** The key used for the receive task decoration */
    public static final String SCRIPTTASK_DECORATOR = "ScriptTask_Decorator"; //$NON-NLS-1$

    /** The key used for the timer decoration of SubProcess Evenemential */
    public static final String SUBPROCEVENT_TIMER_DECORATOR = "SubProc_Timer_Decorator"; //$NON-NLS-1$
    /** The key used for the signal decoration of SubProcess Evenemential */
    public static final String SUBPROCEVENT_SIGNAL_DECORATOR = "SubProc_Signal_Decorator"; //$NON-NLS-1$
    /** The key used for the message decoration of SubProcess Evenemential */
    public static final String SUBPROCEVENT_MESSAGE_DECORATOR = "SubProc_Message_Decorator"; //$NON-NLS-1$
    /** The key used for the message decoration of SubProcess Evenemential */
    public static final String SUBPROCEVENT_ERROR_DECORATOR = "SubProc_Error_Decorator"; //$NON-NLS-1$



    public void createDecorators(IDecoratorTarget decoratorTarget) {
        GraphicalEditPart node = getDecoratorTargetNode(decoratorTarget);
        if (node != null) {
            decoratorTarget.installDecorator(CONNECTOR,new ConnectorDecorator(decoratorTarget,this));
            decoratorTarget.installDecorator(HUMAN_TASK, new HumanTaskDecorator(decoratorTarget,this));
            decoratorTarget.installDecorator(SUBPROCESS_DECORATOR, new SubProcessDecorator(decoratorTarget,this));
            decoratorTarget.installDecorator(MULTIINSTANCE_DECORATOR, new MultiInstanceParalellDecorator(decoratorTarget, this));
            decoratorTarget.installDecorator(MULTIINSTANCE_DECORATOR_SEQUENTIAL, new MultiInstanceSequentialDecorator(decoratorTarget, this));
            decoratorTarget.installDecorator(MULTIINSTANCE_SEQUENTIAL_SUBPROCESS_DECORATOR, new MultiInstanceSequentialSubProcessDecorator(decoratorTarget, this));
            decoratorTarget.installDecorator(MULTIINSTANCE_SUBPROCESS_DECORATOR, new MultiInstanceParalellSubprocessDecorator(decoratorTarget, this));
            decoratorTarget.installDecorator(LOOP_DECORATOR, new LoopTaskDecorator(decoratorTarget, this));
            decoratorTarget.installDecorator(LOOP_SUBPROCESS_DECORATOR, new LoopSubprocessTaskDecorator(decoratorTarget, this));
            decoratorTarget.installDecorator(RECEIVETASK_DECORATOR, new ReceiveTaskDecorator(decoratorTarget,this));
            decoratorTarget.installDecorator(SENDTASK_DECORATOR, new SendTaskDecorator(decoratorTarget,this));
            decoratorTarget.installDecorator(SERVICETASK_DECORATOR,  new ServiceTaskDecorator(decoratorTarget,this));
            decoratorTarget.installDecorator(SCRIPTTASK_DECORATOR, new ScriptTaskDecorator(decoratorTarget,this));
            decoratorTarget.installDecorator(SUBPROCEVENT_TIMER_DECORATOR, new TimerSubProcessEventDecorator(decoratorTarget));
            decoratorTarget.installDecorator(SUBPROCEVENT_MESSAGE_DECORATOR,new MessageSubProcessEventDecorator(decoratorTarget));
            decoratorTarget.installDecorator(SUBPROCEVENT_SIGNAL_DECORATOR, new SignalSubProcessEventDecorator(decoratorTarget));
            decoratorTarget.installDecorator(SUBPROCEVENT_ERROR_DECORATOR, new ErrorSubProcessEventStartDecorator(decoratorTarget));

        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.common.core.internal.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    public boolean provides(IOperation operation) {
        Assert.isNotNull(operation);

        if (!(operation instanceof CreateDecoratorsOperation)) {
            return false;
        }

        IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation)
                .getDecoratorTarget();
        GraphicalEditPart ep = getDecoratorTargetNode(decoratorTarget) ;
        return ep != null && (((IGraphicalEditPart) ep).resolveSemanticElement() instanceof Activity || ((IGraphicalEditPart) ep).resolveSemanticElement() instanceof SubProcessEvent ) ;
    }

    /**
     * getDecoratorTargetClassifier Utility method to determine if the
     * decoratorTarget is a supported type for this decorator and return the
     * associated EditPart element.
     * 
     * @param decoratorTarget
     *            IDecoratorTarget to check and return valid Classifier target.
     * @return node GraphicalEditPart if IDecoratorTarget can be supported, null
     *         otherwise.
     */
    public static GraphicalEditPart getDecoratorTargetNode(IDecoratorTarget decoratorTarget) {
        if(decoratorTarget == null){
            return null;
        }
        ShapeNodeEditPart node = (ShapeNodeEditPart) decoratorTarget.getAdapter(ShapeNodeEditPart.class);
        if(node != null ){
            return node ;
        }

        return null;

    }

}
