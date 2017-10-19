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
package org.bonitasoft.studio.model.process.diagram.form.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTable2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTableEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.Group2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.GroupEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfo2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfoEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBox2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBoxEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.Table2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TableEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfo2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfoEditPart;
import org.bonitasoft.studio.model.process.diagram.form.part.ProcessVisualIDRegistry;
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
public class FormNavigatorContentProvider implements ICommonContentProvider {

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
	public FormNavigatorContentProvider() {
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
			ArrayList<FormNavigatorItem> result = new ArrayList<FormNavigatorItem>();
			ArrayList<View> topViews = new ArrayList<View>(resource.getContents().size());
			for (EObject o : resource.getContents()) {
				if (o instanceof View) {
					topViews.add((View) o);
				}
			}
			result.addAll(createNavigatorItems(selectViewsByType(topViews, FormEditPart.MODEL_ID), file, false));
			return result.toArray();
		}

		if (parentElement instanceof FormNavigatorGroup) {
			FormNavigatorGroup group = (FormNavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof FormNavigatorItem) {
			FormNavigatorItem navigatorItem = (FormNavigatorItem) parentElement;
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

		case FormEditPart.VISUAL_ID: {
			LinkedList<FormAbstractNavigatorItem> result = new LinkedList<FormAbstractNavigatorItem>();
			Diagram sv = (Diagram) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CheckBoxSingleFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ComboFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(DateFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ListFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PasswordFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(RadioFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SelectFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SuggestBoxEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAreaFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(RichTextAreaFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubmitFormButtonEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NextFormButtonEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PreviousFormButtonEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(GroupEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageInfoEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextInfoEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(FileWidgetEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CheckBoxMultipleFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ImageWidgetEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(HiddenWidgetEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(DurationFormFieldEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(FormButtonEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TableEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(DynamicTableEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IFrameWidgetEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(HtmlWidgetEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			return result.toArray();
		}

		case GroupEditPart.VISUAL_ID: {
			LinkedList<FormAbstractNavigatorItem> result = new LinkedList<FormAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PreviousFormButton2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CheckBoxSingleFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ComboFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NextFormButton2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(DateFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Group2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ListFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubmitFormButton2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PasswordFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(RadioFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SelectFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAreaFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(RichTextAreaFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(FileWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CheckBoxMultipleFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(DurationFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(FileWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CheckBoxMultipleFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(HiddenWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ImageWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageInfo2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextInfo2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(FormButton2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Table2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(DynamicTable2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IFrameWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(HtmlWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SuggestBox2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			return result.toArray();
		}

		case Group2EditPart.VISUAL_ID: {
			LinkedList<FormAbstractNavigatorItem> result = new LinkedList<FormAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PreviousFormButton2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CheckBoxSingleFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ComboFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(NextFormButton2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(DateFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Group2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ListFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SubmitFormButton2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(PasswordFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(RadioFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SelectFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextAreaFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(RichTextAreaFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(FileWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CheckBoxMultipleFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(DurationFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(FileWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(CheckBoxMultipleFormField2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(HiddenWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(ImageWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(MessageInfo2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(TextInfo2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(FormButton2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(Table2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(DynamicTable2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(IFrameWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(HtmlWidget2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ProcessVisualIDRegistry.getType(SuggestBox2EditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement, false));
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
		return FormEditPart.MODEL_ID.equals(ProcessVisualIDRegistry.getModelID(view));
	}

	/**
	 * @generated
	 */
	private Collection<FormNavigatorItem> createNavigatorItems(Collection<View> views, Object parent, boolean isLeafs) {
		ArrayList<FormNavigatorItem> result = new ArrayList<FormNavigatorItem>(views.size());
		for (View nextView : views) {
			result.add(new FormNavigatorItem(nextView, parent, isLeafs));
		}
		return result;
	}

	/**
	* @generated
	*/
	public Object getParent(Object element) {
		if (element instanceof FormAbstractNavigatorItem) {
			FormAbstractNavigatorItem abstractNavigatorItem = (FormAbstractNavigatorItem) element;
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
