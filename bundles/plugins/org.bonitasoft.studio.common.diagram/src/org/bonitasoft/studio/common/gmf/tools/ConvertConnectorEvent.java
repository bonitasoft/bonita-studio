///**
// * Copyright (C) 2010 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//
//package org.bonitasoft.studio.common.gmf.tools;
//
//import org.bonitasoft.studio.common.HookDefinition;
//import org.bonitasoft.studio.model.process.AbstractProcess;
//import org.bonitasoft.studio.model.process.Activity;
//import org.bonitasoft.studio.model.process.ConnectableElement;
//import org.bonitasoft.studio.model.process.Connector;
//import org.bonitasoft.studio.model.process.SubProcess;
//import org.bonitasoft.studio.model.process.Task;
//import org.bonitasoft.studio.model.process.TimerEvent;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.core.runtime.IAdaptable;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.emf.transaction.TransactionalEditingDomain;
//import org.eclipse.gmf.runtime.common.core.command.CommandResult;
//import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
//
///**
// * @author Romain Bioteau
// *
// */
//public class ConvertConnectorEvent extends AbstractTransactionalCommand {
//
//	private Connector connector;
//
//	public ConvertConnectorEvent(TransactionalEditingDomain domain, Connector c) {
//		super(domain, "Convert Connector Event Command",getWorkspaceFiles(c));
//		this.connector = c ;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.emf.common.command.Command#canUndo()
//	 */
//	public boolean canUndo() {
//		return false;
//	}
//
//	@Override
//	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
//			IAdaptable info) throws ExecutionException {
//		connector.setEvent(getTargetEvent((ConnectableElement)connector.eContainer(), connector.getEvent()));
//		return CommandResult.newOKCommandResult(connector);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.emf.common.command.Command#execute()
//	 */
//	public static String getTargetEvent(ConnectableElement targetContainer, String srcEvent) {
//		if(srcEvent != null){
//			if(targetContainer instanceof Task && !isTaskEvent(srcEvent)){
//				return switchToTaskEvent(srcEvent);
//			}else if(targetContainer instanceof Task && isTaskEvent(srcEvent)){
//				return srcEvent ;
//			}else if(targetContainer instanceof AbstractProcess && !isProcessEvent(srcEvent)){
//				return switchToProcessEvent(srcEvent);
//			}else if(targetContainer instanceof AbstractProcess && isProcessEvent(srcEvent)){
//				return srcEvent;
//			}else if((targetContainer instanceof SubProcess || targetContainer instanceof Activity) && !isAutomaticEvent(srcEvent)){
//				return switchToAutomaticEvent(srcEvent);
//			}else if((targetContainer instanceof SubProcess || targetContainer instanceof Activity) && isAutomaticEvent(srcEvent)){
//				return srcEvent;
//			}else if(targetContainer instanceof TimerEvent){
//				return switchToTimerEvent(srcEvent);
//			} else {
//				return srcEvent;
//			}
//		}else{
//			return null ;
//		}
//	}
//
//	public static boolean isTaskEvent(String srcEvent) {
//		if(srcEvent.equals(HookDefinition.Event.taskOnStart.toString())
//				|| srcEvent.equals(HookDefinition.Event.taskOnFinish.toString())
//				|| srcEvent.equals(HookDefinition.Event.taskOnCancel.toString())
//				|| srcEvent.equals(HookDefinition.Event.taskOnResume.toString())
//				|| srcEvent.equals(HookDefinition.Event.taskOnSuspend.toString())
//				|| srcEvent.equals(HookDefinition.Event.taskOnReady.toString()))
//			return true;
//
//		return false ;
//	}
//
//	public static boolean isProcessEvent(String srcEvent) {
//		if(srcEvent.equals(HookDefinition.Event.instanceOnAbort.toString())
//				|| srcEvent.equals(HookDefinition.Event.instanceOnCancel.toString())
//				|| srcEvent.equals(HookDefinition.Event.instanceOnStart.toString())
//				|| srcEvent.equals(HookDefinition.Event.instanceOnFinish.toString())){
//			return true;
//		}
//
//		return false ;
//	}
//
//	public static boolean isAutomaticEvent(String srcEvent) {
//		if(srcEvent.equals(HookDefinition.Event.automaticOnEnter.toString())
//				|| srcEvent.equals(HookDefinition.Event.automaticOnExit.toString()))
//			return true;
//
//		return false ;
//	}
//
//	private static String switchToTimerEvent(String srcEvent) {
//		return null;
//	}
//
//	private static String switchToAutomaticEvent(String srcEvent) {
//		if(srcEvent != null){
//			if(srcEvent.equals(HookDefinition.Event.instanceOnFinish.toString())
//					|| srcEvent.equals(HookDefinition.Event.instanceOnCancel.toString())
//					|| srcEvent.equals(HookDefinition.Event.instanceOnAbort.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnFinish.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnCancel.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnResume.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnSuspend.toString())){
//				return HookDefinition.Event.automaticOnExit.toString();
//			}else if(srcEvent.equals(HookDefinition.Event.instanceOnStart.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnReady.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnStart.toString())){
//				return HookDefinition.Event.automaticOnEnter.toString();
//			}
//		}
//		return HookDefinition.Event.automaticOnExit.toString();
//	}
//
//	private static String switchToProcessEvent(String srcEvent) {
//		if(srcEvent != null){
//			if(srcEvent.equals(HookDefinition.Event.automaticOnExit.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnResume.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnSuspend.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnFinish.toString())){
//				return HookDefinition.Event.instanceOnFinish.toString();
//			}else if( srcEvent.equals(HookDefinition.Event.taskOnReady.toString())
//					|| srcEvent.equals(HookDefinition.Event.taskOnStart.toString())
//					|| srcEvent.equals(HookDefinition.Event.automaticOnEnter.toString())){
//				return HookDefinition.Event.instanceOnStart.toString();
//			}else if(srcEvent.equals(HookDefinition.Event.taskOnCancel.toString())){
//				return HookDefinition.Event.instanceOnCancel.toString();
//			}
//		}
//		return HookDefinition.Event.instanceOnFinish.toString();
//	}
//
//	private static String switchToTaskEvent(String srcEvent) {
//		if(srcEvent != null){
//			if(srcEvent.equals(HookDefinition.Event.instanceOnFinish.toString())
//					|| srcEvent.equals(HookDefinition.Event.automaticOnExit.toString())){
//				return HookDefinition.Event.taskOnFinish.toString();
//			}else if(srcEvent.equals(HookDefinition.Event.instanceOnStart.toString())
//					|| srcEvent.equals(HookDefinition.Event.automaticOnEnter.toString())){
//				return HookDefinition.Event.taskOnStart.toString();
//			}else if(srcEvent.equals(HookDefinition.Event.instanceOnCancel.toString())
//					|| srcEvent.equals(HookDefinition.Event.instanceOnAbort.toString())){
//				return HookDefinition.Event.taskOnCancel.toString();
//			}
//		}
//		return HookDefinition.Event.taskOnFinish.toString();
//	}
//
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.emf.common.command.Command#getLabel()
//	 */
//	public String getLabel() {
//		return "Convert Connector Event Command"; //$NON-NLS-1$
//	}
//
//}
