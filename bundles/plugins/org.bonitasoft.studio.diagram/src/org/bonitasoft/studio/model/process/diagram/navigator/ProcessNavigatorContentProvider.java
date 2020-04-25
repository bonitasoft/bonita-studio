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
package org.bonitasoft.studio.model.process.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGatewayEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Activity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundarySignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundarySignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryTimerEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndErrorEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndErrorEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndSignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndTerminatedEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndTerminatedEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Event2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGatewayEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchSignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchTimerEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent3EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent4EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent5EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent6EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartErrorEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartErrorEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartSignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartTimerEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventSubProcessCompartment2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventSubProcessCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotation2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationAttachmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGatewayEditPart;
import org.bonitasoft.studio.model.process.diagram.part.Messages;
import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * @generated
 */
public class ProcessNavigatorContentProvider implements ICommonContentProvider {

	/**
	* @generated
	*/
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	* @generated
	*/
	private Viewer myViewer;

	/**
	* @generated
	*/
	private AdapterFactoryEditingDomain myEditingDomain;

	/**
	* @generated
	*/
	private WorkspaceSynchronizer myWorkspaceSynchronizer;

	/**
	* @generated
	*/
	private Runnable myViewerRefreshRunnable;

	/**
	* @generated
	*/
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public ProcessNavigatorContentProvider() {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
			public Object get(Object key) {
				if (!containsKey(key)) {
					put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		myViewerRefreshRunnable = new Runnable() {
			public void run() {
				if (myViewer != null) {
					myViewer.refresh();
				}
			}
		};
		myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain, new WorkspaceSynchronizer.Delegate() {
			public void dispose() {
			}

			public boolean handleResourceChanged(final Resource resource) {
				unloadAllResources();
				asyncRefresh();
				return true;
			}

			public boolean handleResourceDeleted(Resource resource) {
				unloadAllResources();
				asyncRefresh();
				return true;
			}

			public boolean handleResourceMoved(Resource resource, final URI newURI) {
				unloadAllResources();
				asyncRefresh();
				return true;
			}
		});
	}

	/**
	* @generated
	*/
	public void dispose() {
		myWorkspaceSynchronizer.dispose();
		myWorkspaceSynchronizer = null;
		myViewerRefreshRunnable = null;
		myViewer = null;
		unloadAllResources();
		((TransactionalEditingDomain) myEditingDomain).dispose();
		myEditingDomain = null;
	}

	/**
	* @generated
	*/
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myViewer = viewer;
	}

	/**
	* @generated
	*/
	void unloadAllResources() {
		for (Resource nextResource : myEditingDomain.getResourceSet().getResources()) {
			nextResource.unload();
		}
	}

	/**
	* @generated
	*/
	void asyncRefresh() {
		if (myViewer != null && !myViewer.getControl().isDisposed()) {
			myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
		}
	}

	/**
	* @generated
	*/
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	* @generated
	*/
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	* @generated
	*/
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	* @generated
	*/
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource resource = myEditingDomain.getResourceSet().getResource(fileURI, true);
			ArrayList<ProcessNavigatorItem> result = new ArrayList<ProcessNavigatorItem>();
			ArrayList<View> topViews = new ArrayList<View>(resource.getContents().size());
			for (EObject o : resource.getContents()) {
				if (o instanceof View) {
					topViews.add((View) o);
				}
			}
			result.addAll(createNavigatorItems(selectViewsByType(topViews, MainProcessEditPart.MODEL_ID), file, false));
			return result.toArray();
		}

		if (parentElement instanceof ProcessNavigatorGroup) {
			ProcessNavigatorGroup group = (ProcessNavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof ProcessNavigatorItem) {
			ProcessNavigatorItem navigatorItem = (ProcessNavigatorItem) parentElement;
			if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
				return EMPTY_ARRAY;
			}
			return getViewChildren(navigatorItem.getView(), parentElement);
		}

		return EMPTY_ARRAY;
	}

	/**
	* @generated
	*/
	private Object[] getViewChildren(View view, Object parentElement) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {

		case MainProcessEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Diagram sv = (Diagram) view;
			ProcessNavigatorGroup links = new ProcessNavigatorGroup(Messages.NavigatorGroupName_MainProcess_1000_links,
					"icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ANDGatewayEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TaskEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CallActivityEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ReceiveTaskEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SendTaskEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ServiceTaskEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ScriptTaskEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(XORGatewayEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(InclusiveGatewayEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ActivityEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartMessageEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndMessageEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartTimerEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CatchLinkEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ThrowLinkEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartSignalEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndSignalEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndErrorEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartErrorEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndTerminatedEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			if (!links.isEmpty()) {
				result.add(links);
			}
			return result.toArray();
		}

		case StartEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartEvent_2002_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartEvent_2002_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EndEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndEvent_2003_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndEvent_2003_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case TaskEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Task_2004_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Task_2004_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryTimerEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundarySignalEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ActivityEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Activity_2006_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Activity_2006_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent6EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case PoolEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Pool_2007_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews, ProcessVisualIDRegistry.getType(LaneEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ANDGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(CallActivity2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(SubProcessEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Task2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ReceiveTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(SendTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ServiceTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ScriptTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(XORGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Activity2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(TextAnnotation2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(CatchLinkEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ThrowLinkEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndErrorEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndTerminatedEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Event2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolPoolCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(InclusiveGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case XORGatewayEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_XORGateway_2008_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_XORGateway_2008_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ANDGatewayEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ANDGateway_2009_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ANDGateway_2009_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case StartMessageEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartMessageEvent_2010_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartMessageEvent_2010_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EndMessageEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndMessageEvent_2011_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndMessageEvent_2011_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateCatchMessageEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchMessageEvent_2013_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchMessageEvent_2013_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateThrowMessageEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateThrowMessageEvent_2014_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateThrowMessageEvent_2014_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case TextAnnotationEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_TextAnnotation_2015_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_TextAnnotation_2015_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case StartTimerEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartTimerEvent_2016_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartTimerEvent_2016_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateCatchTimerEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchTimerEvent_2017_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchTimerEvent_2017_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case CatchLinkEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_CatchLinkEvent_2018_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_CatchLinkEvent_2018_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ThrowLinkEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ThrowLinkEvent_2019_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ThrowLinkEvent_2019_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateThrowSignalEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateThrowSignalEvent_2020_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateThrowSignalEvent_2020_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateCatchSignalEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchSignalEvent_2021_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchSignalEvent_2021_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case StartSignalEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartSignalEvent_2022_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartSignalEvent_2022_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EndSignalEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndSignalEvent_2023_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndSignalEvent_2023_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Event_2024_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Event_2024_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ReceiveTaskEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ReceiveTask_2025_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ReceiveTask_2025_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent3EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case SendTaskEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_SendTask_2026_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_SendTask_2026_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ServiceTaskEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ServiceTask_2027_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ServiceTask_2027_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent4EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ScriptTaskEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ScriptTask_2028_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ScriptTask_2028_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent5EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EndErrorEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndErrorEvent_2029_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndErrorEvent_2029_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case InclusiveGatewayEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_InclusiveGateway_2030_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_InclusiveGateway_2030_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case SubProcessEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_SubProcessEvent_2031_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ANDGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(CallActivity2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Task2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ReceiveTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(SendTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ServiceTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ScriptTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(XORGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Activity2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(TextAnnotation2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndErrorEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndTerminatedEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartErrorEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Event2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(InclusiveGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case StartErrorEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartErrorEvent_2033_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartErrorEvent_2033_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EndTerminatedEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndTerminatedEvent_2035_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndTerminatedEvent_2035_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case CallActivityEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_CallActivity_2036_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_CallActivity_2036_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundarySignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case StartEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartEvent_3002_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartEvent_3002_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EndEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndEvent_3003_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndEvent_3003_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Task2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Task_3005_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Task_3005_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryTimerEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundarySignalEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Activity2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Activity_3006_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Activity_3006_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent6EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case LaneEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Lane_3007_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Task2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ANDGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(CallActivity2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(SubProcessEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(XORGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(SendTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ReceiveTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ServiceTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ScriptTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Activity2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(TextAnnotation2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ThrowLinkEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(CatchLinkEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndErrorEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndTerminatedEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Event2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneLaneCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(InclusiveGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case XORGateway2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_XORGateway_3008_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_XORGateway_3008_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ANDGateway2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ANDGateway_3009_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ANDGateway_3009_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EndMessageEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndMessageEvent_3011_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndMessageEvent_3011_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case StartMessageEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartMessageEvent_3012_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartMessageEvent_3012_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateCatchMessageEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchMessageEvent_3013_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchMessageEvent_3013_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateThrowMessageEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateThrowMessageEvent_3014_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateThrowMessageEvent_3014_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case TextAnnotation2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_TextAnnotation_3015_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_TextAnnotation_3015_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case StartTimerEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartTimerEvent_3016_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartTimerEvent_3016_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateCatchTimerEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchTimerEvent_3017_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchTimerEvent_3017_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ThrowLinkEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ThrowLinkEvent_3018_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ThrowLinkEvent_3018_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case CatchLinkEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_CatchLinkEvent_3019_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_CatchLinkEvent_3019_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EndSignalEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndSignalEvent_3020_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndSignalEvent_3020_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateCatchSignalEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchSignalEvent_3021_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateCatchSignalEvent_3021_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateThrowSignalEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateThrowSignalEvent_3022_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateThrowSignalEvent_3022_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case StartSignalEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartSignalEvent_3023_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartSignalEvent_3023_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case Event2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Event_3024_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_Event_3024_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case SendTask2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_SendTask_3025_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_SendTask_3025_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ReceiveTask2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ReceiveTask_3026_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ReceiveTask_3026_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent3EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ServiceTask2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ServiceTask_3027_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ServiceTask_3027_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent4EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ScriptTask2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ScriptTask_3028_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_ScriptTask_3028_outgoinglinks, "icons/outgoingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent5EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case IntermediateErrorCatchEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3029_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3029_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case IntermediateErrorCatchEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3030_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3030_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case IntermediateErrorCatchEvent3EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3031_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3031_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case IntermediateErrorCatchEvent4EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3032_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3032_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case IntermediateErrorCatchEvent5EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3033_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3033_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case IntermediateErrorCatchEvent6EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3034_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_IntermediateErrorCatchEvent_3034_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case BoundaryMessageEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundaryMessageEvent_3035_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundaryMessageEvent_3035_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case BoundaryMessageEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundaryMessageEvent_3036_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundaryMessageEvent_3036_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case BoundaryTimerEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundaryTimerEvent_3043_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundaryTimerEvent_3043_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case BoundaryTimerEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundaryTimerEvent_3044_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundaryTimerEvent_3044_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case EndErrorEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndErrorEvent_3050_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndErrorEvent_3050_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case InclusiveGateway2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_InclusiveGateway_3051_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_InclusiveGateway_3051_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case BoundarySignalEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundarySignalEvent_3052_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundarySignalEvent_3052_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case BoundarySignalEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundarySignalEvent_3053_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_BoundarySignalEvent_3053_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case SubProcessEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_SubProcessEvent_3058_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ANDGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(CallActivity2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Task2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ReceiveTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(SendTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ServiceTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(ScriptTask2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(XORGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Activity2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(TextAnnotation2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndSignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndErrorEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(EndTerminatedEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(StartErrorEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(Event2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ProcessVisualIDRegistry.getType(InclusiveGateway2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case StartErrorEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartErrorEvent_3060_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_StartErrorEvent_3060_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case EndTerminatedEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndTerminatedEvent_3062_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_EndTerminatedEvent_3062_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case CallActivity2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_CallActivity_3063_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_CallActivity_3063_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryTimerEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundarySignalEvent2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_NonInterruptingBoundaryTimerEvent_3064_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_NonInterruptingBoundaryTimerEvent_3064_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Node sv = (Node) view;
			ProcessNavigatorGroup outgoinglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_NonInterruptingBoundaryTimerEvent_3065_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_NonInterruptingBoundaryTimerEvent_3065_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SequenceFlowEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationAttachmentEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case SequenceFlowEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ProcessNavigatorGroup target = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_SequenceFlow_4001_target, "icons/linkTargetNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup source = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_SequenceFlow_4001_source, "icons/linkSourceNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_SequenceFlow_4001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ANDGatewayEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CallActivityEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ReceiveTaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SendTaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ServiceTaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ScriptTaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(XORGatewayEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(InclusiveGatewayEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ActivityEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartTimerEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CatchLinkEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ThrowLinkEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartSignalEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndSignalEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndErrorEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartErrorEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndTerminatedEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ANDGateway2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CallActivity2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ReceiveTask2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SendTask2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ServiceTask2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ScriptTask2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(XORGateway2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Activity2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartTimerEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartSignalEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndSignalEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndErrorEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndTerminatedEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartErrorEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Event2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(InclusiveGateway2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ThrowLinkEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CatchLinkEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ANDGatewayEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CallActivityEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ReceiveTaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SendTaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ServiceTaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ScriptTaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(XORGatewayEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(InclusiveGatewayEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ActivityEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartMessageEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndMessageEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartTimerEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CatchLinkEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ThrowLinkEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartSignalEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndSignalEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndErrorEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartErrorEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndTerminatedEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryTimerEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundarySignalEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryTimerEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundarySignalEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ANDGateway2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CallActivity2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Task2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ReceiveTask2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent3EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SendTask2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ServiceTask2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent4EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ScriptTask2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent5EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(XORGateway2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Activity2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent6EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartMessageEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndMessageEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartTimerEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartSignalEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndSignalEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndErrorEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndTerminatedEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartErrorEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Event2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(InclusiveGateway2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ThrowLinkEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CatchLinkEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case MessageFlowEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ProcessNavigatorGroup target = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_MessageFlow_4002_target, "icons/linkTargetNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup source = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_MessageFlow_4002_source, "icons/linkSourceNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			ProcessNavigatorGroup incominglinks = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_MessageFlow_4002_incominglinks, "icons/incomingLinksNavigatorGroup.gif", //$NON-NLS-1$
					parentElement);
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ReceiveTaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ReceiveTask2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SendTaskEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndMessageEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEventEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SendTask2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndMessageEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEvent2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}

		case TextAnnotationAttachmentEditPart.VISUAL_ID: {
			LinkedList<ProcessAbstractNavigatorItem> result = new LinkedList<ProcessAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ProcessNavigatorGroup target = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_TextAnnotationAttachment_4003_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ProcessNavigatorGroup source = new ProcessNavigatorGroup(
					Messages.NavigatorGroupName_TextAnnotationAttachment_4003_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ANDGatewayEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CallActivityEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ReceiveTaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SendTaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ServiceTaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ScriptTaskEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(XORGatewayEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(InclusiveGatewayEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ActivityEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PoolEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartTimerEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CatchLinkEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ThrowLinkEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartSignalEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndSignalEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndErrorEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartErrorEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndTerminatedEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryTimerEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundarySignalEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundaryTimerEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(BoundarySignalEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ANDGateway2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CallActivity2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Task2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ReceiveTask2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent3EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SendTask2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ServiceTask2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent4EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ScriptTask2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent5EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(XORGateway2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Activity2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateErrorCatchEvent6EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowMessageEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotation2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartTimerEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchTimerEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartSignalEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateThrowSignalEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IntermediateCatchSignalEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndSignalEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndErrorEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(EndTerminatedEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartErrorEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Event2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(InclusiveGateway2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(LaneEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(StartEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubProcessEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ThrowLinkEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CatchLinkEvent2EditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotationEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAnnotation2EditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source, true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}
		}
		return EMPTY_ARRAY;
	}

	/**
	* @generated
	*/
	private Collection<View> getLinksSourceByType(Collection<Edge> edges, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeSource = nextEdge.getSource();
			if (type.equals(nextEdgeSource.getType()) && isOwnView(nextEdgeSource)) {
				result.add(nextEdgeSource);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksTargetByType(Collection<Edge> edges, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeTarget = nextEdge.getTarget();
			if (type.equals(nextEdgeTarget.getType()) && isOwnView(nextEdgeTarget)) {
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getOutgoingLinksByType(Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getIncomingLinksByType(Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getChildrenByType(Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getDiagramLinksByType(Collection<Diagram> diagrams, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (Diagram nextDiagram : diagrams) {
			result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}

	// TODO refactor as static method
	/**
	 * @generated
	 */
	private Collection<View> selectViewsByType(Collection<View> views, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (View nextView : views) {
			if (type.equals(nextView.getType()) && isOwnView(nextView)) {
				result.add(nextView);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return MainProcessEditPart.MODEL_ID.equals(ProcessVisualIDRegistry.getModelID(view));
	}

	/**
	 * @generated
	 */
	private Collection<ProcessNavigatorItem> createNavigatorItems(Collection<View> views, Object parent,
			boolean isLeafs) {
		ArrayList<ProcessNavigatorItem> result = new ArrayList<ProcessNavigatorItem>(views.size());
		for (View nextView : views) {
			result.add(new ProcessNavigatorItem(nextView, parent, isLeafs));
		}
		return result;
	}

	/**
	* @generated
	*/
	public Object getParent(Object element) {
		if (element instanceof ProcessAbstractNavigatorItem) {
			ProcessAbstractNavigatorItem abstractNavigatorItem = (ProcessAbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	* @generated
	*/
	public boolean hasChildren(Object element) {
		return element instanceof IFile || getChildren(element).length > 0;
	}

}
