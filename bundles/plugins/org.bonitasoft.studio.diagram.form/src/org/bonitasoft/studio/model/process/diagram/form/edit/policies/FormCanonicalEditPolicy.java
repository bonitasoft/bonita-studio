/*
* Copyright (C) 2009-2015 BonitaSoft S.A.
* Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.process.diagram.form.edit.policies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTableEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.GroupEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfoEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBoxEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TableEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfoEditPart;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessDiagramUpdater;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessNodeDescriptor;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetViewMutabilityCommand;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class FormCanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	* @generated
	*/
	protected void refreshOnActivate() {
		// Need to activate editpart children before invoking the canonical refresh for EditParts to add event listeners
		List<?> c = getHost().getChildren();
		for (int i = 0; i < c.size(); i++) {
			((EditPart) c.get(i)).activate();
		}
		super.refreshOnActivate();
	}

	/**
	* @generated
	*/
	protected EStructuralFeature getFeatureToSynchronize() {
		return FormPackage.eINSTANCE.getForm_Widgets();
	}

	/**
	* @generated
	*/
	@SuppressWarnings("rawtypes")

	protected List getSemanticChildrenList() {
		View viewObject = (View) getHost().getModel();
		LinkedList<EObject> result = new LinkedList<EObject>();
		List<ProcessNodeDescriptor> childDescriptors = ProcessDiagramUpdater.getForm_1000SemanticChildren(viewObject);
		for (ProcessNodeDescriptor d : childDescriptors) {
			result.add(d.getModelElement());
		}
		return result;
	}

	/**
	* @generated
	*/
	protected boolean isOrphaned(Collection<EObject> semanticChildren, final View view) {
		return isMyDiagramElement(view) && !semanticChildren.contains(view.getElement());
	}

	/**
	* @generated
	*/
	private boolean isMyDiagramElement(View view) {
		int visualID = ProcessVisualIDRegistry.getVisualID(view);
		switch (visualID) {
		case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
		case ComboFormFieldEditPart.VISUAL_ID:
		case DateFormFieldEditPart.VISUAL_ID:
		case ListFormFieldEditPart.VISUAL_ID:
		case PasswordFormFieldEditPart.VISUAL_ID:
		case RadioFormFieldEditPart.VISUAL_ID:
		case SelectFormFieldEditPart.VISUAL_ID:
		case SuggestBoxEditPart.VISUAL_ID:
		case TextFormFieldEditPart.VISUAL_ID:
		case TextAreaFormFieldEditPart.VISUAL_ID:
		case RichTextAreaFormFieldEditPart.VISUAL_ID:
		case SubmitFormButtonEditPart.VISUAL_ID:
		case NextFormButtonEditPart.VISUAL_ID:
		case PreviousFormButtonEditPart.VISUAL_ID:
		case GroupEditPart.VISUAL_ID:
		case MessageInfoEditPart.VISUAL_ID:
		case TextInfoEditPart.VISUAL_ID:
		case FileWidgetEditPart.VISUAL_ID:
		case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
		case ImageWidgetEditPart.VISUAL_ID:
		case HiddenWidgetEditPart.VISUAL_ID:
		case DurationFormFieldEditPart.VISUAL_ID:
		case FormButtonEditPart.VISUAL_ID:
		case TableEditPart.VISUAL_ID:
		case DynamicTableEditPart.VISUAL_ID:
		case IFrameWidgetEditPart.VISUAL_ID:
		case HtmlWidgetEditPart.VISUAL_ID:
			return true;
		}
		return false;
	}

	/**
	* @generated
	*/
	protected void refreshSemantic() {
		if (resolveSemanticElement() == null) {
			return;
		}
		LinkedList<IAdaptable> createdViews = new LinkedList<IAdaptable>();
		List<ProcessNodeDescriptor> childDescriptors = ProcessDiagramUpdater
				.getForm_1000SemanticChildren((View) getHost().getModel());
		LinkedList<View> orphaned = new LinkedList<View>();
		// we care to check only views we recognize as ours
		LinkedList<View> knownViewChildren = new LinkedList<View>();
		for (View v : getViewChildren()) {
			if (isMyDiagramElement(v)) {
				knownViewChildren.add(v);
			}
		}
		// alternative to #cleanCanonicalSemanticChildren(getViewChildren(), semanticChildren)
		//
		// iteration happens over list of desired semantic elements, trying to find best matching View, while original CEP
		// iterates views, potentially losing view (size/bounds) information - i.e. if there are few views to reference same EObject, only last one 
		// to answer isOrphaned == true will be used for the domain element representation, see #cleanCanonicalSemanticChildren()
		for (Iterator<ProcessNodeDescriptor> descriptorsIterator = childDescriptors.iterator(); descriptorsIterator
				.hasNext();) {
			ProcessNodeDescriptor next = descriptorsIterator.next();
			String hint = ProcessVisualIDRegistry.getType(next.getVisualID());
			LinkedList<View> perfectMatch = new LinkedList<View>(); // both semanticElement and hint match that of NodeDescriptor
			for (View childView : getViewChildren()) {
				EObject semanticElement = childView.getElement();
				if (next.getModelElement().equals(semanticElement)) {
					if (hint.equals(childView.getType())) {
						perfectMatch.add(childView);
						// actually, can stop iteration over view children here, but
						// may want to use not the first view but last one as a 'real' match (the way original CEP does
						// with its trick with viewToSemanticMap inside #cleanCanonicalSemanticChildren
					}
				}
			}
			if (perfectMatch.size() > 0) {
				descriptorsIterator.remove(); // precise match found no need to create anything for the NodeDescriptor
				// use only one view (first or last?), keep rest as orphaned for further consideration
				knownViewChildren.remove(perfectMatch.getFirst());
			}
		}
		// those left in knownViewChildren are subject to removal - they are our diagram elements we didn't find match to,
		// or those we have potential matches to, and thus need to be recreated, preserving size/location information.
		orphaned.addAll(knownViewChildren);
		//
		ArrayList<CreateViewRequest.ViewDescriptor> viewDescriptors = new ArrayList<CreateViewRequest.ViewDescriptor>(
				childDescriptors.size());
		for (ProcessNodeDescriptor next : childDescriptors) {
			String hint = ProcessVisualIDRegistry.getType(next.getVisualID());
			IAdaptable elementAdapter = new CanonicalElementAdapter(next.getModelElement(), hint);
			CreateViewRequest.ViewDescriptor descriptor = new CreateViewRequest.ViewDescriptor(elementAdapter,
					Node.class, hint, ViewUtil.APPEND, false, host().getDiagramPreferencesHint());
			viewDescriptors.add(descriptor);
		}

		boolean changed = deleteViews(orphaned.iterator());
		//
		CreateViewRequest request = getCreateViewRequest(viewDescriptors);
		Command cmd = getCreateViewCommand(request);
		if (cmd != null && cmd.canExecute()) {
			SetViewMutabilityCommand.makeMutable(new EObjectAdapter(host().getNotationView())).execute();
			executeCommand(cmd);
			@SuppressWarnings("unchecked")

			List<IAdaptable> nl = (List<IAdaptable>) request.getNewObject();
			createdViews.addAll(nl);
		}
		if (changed || createdViews.size() > 0) {
			postProcessRefreshSemantic(createdViews);
		}

		makeViewsImmutable(createdViews);
	}

	/**
	* @generated
	*/
	@SuppressWarnings("serial")
	protected static class Domain2Notation extends HashMap<EObject, View> {
		/**
		* @generated
		*/
		public boolean containsDomainElement(EObject domainElement) {
			return this.containsKey(domainElement);
		}

		/**
		* @generated
		*/
		public View getHinted(EObject domainEObject, String hint) {
			return this.get(domainEObject);
		}

		/**
		* @generated
		*/
		public void putView(EObject domainElement, View view) {
			if (!containsKey(view.getElement())) {
				this.put(domainElement, view);
			}
		}

	}
}
