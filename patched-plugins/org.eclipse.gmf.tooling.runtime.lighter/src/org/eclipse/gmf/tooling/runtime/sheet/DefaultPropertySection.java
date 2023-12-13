/*
 * Copyright (c) 2007, 2009 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 *    Michael Golubev (Montages) - [407242] - common code extracted to gmft.runtime 
 */
package org.eclipse.gmf.tooling.runtime.sheet;

import java.util.ArrayList;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AdvancedPropertySection;
import org.eclipse.gmf.runtime.emf.ui.properties.sections.PropertySheetEntry;
import org.eclipse.gmf.runtime.emf.ui.properties.sections.UndoableModelPropertySheetEntry;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

public class DefaultPropertySection extends AdvancedPropertySection implements IPropertySourceProvider {

	public IPropertySource getPropertySource(Object object) {
		if (object instanceof IPropertySource) {
			return (IPropertySource) object;
		}
		AdapterFactory af = getAdapterFactory(object);
		if (af != null) {
			IItemPropertySource ips = (IItemPropertySource) af.adapt(object, IItemPropertySource.class);
			if (ips != null) {
				return new PropertySource(object, ips);
			}
		}
		if (object instanceof IAdaptable) {
			return (IPropertySource) ((IAdaptable) object).getAdapter(IPropertySource.class);
		}
		return null;
	}

	@Override
	protected IPropertySourceProvider getPropertySourceProvider() {
		return this;
	}

	/**
	 * Default implementation does not transform anything. 
	 * Subclass may override, e.g by calling alternative default implementation {@link DefaultPropertySection#transformSelectionToDomain(Object)} 
	 * @return by default does not transform anything. 
	 */
	protected Object transformSelection(Object selected) {
		return selected;
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (selection.isEmpty() || false == selection instanceof StructuredSelection) {
			super.setInput(part, selection);
			return;
		}
		final StructuredSelection structuredSelection = ((StructuredSelection) selection);
		ArrayList<Object> transformedSelection = new ArrayList<Object>(structuredSelection.size());
		for (Object next : structuredSelection.toList()) {
			Object r = transformSelection(next);
			if (r != null) {
				transformedSelection.add(r);
			}
		}
		super.setInput(part, new StructuredSelection(transformedSelection));
	}

	protected AdapterFactory getAdapterFactory(Object object) {
		if (getEditingDomain() instanceof AdapterFactoryEditingDomain) {
			return ((AdapterFactoryEditingDomain) getEditingDomain()).getAdapterFactory();
		}
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object);
		if (editingDomain != null) {
			return ((AdapterFactoryEditingDomain) editingDomain).getAdapterFactory();
		}
		return null;
	}

	/**
	* Utility for subclasses, default modify/unwrap selection to semantic element
	*/
	protected Object transformSelectionToDomain(Object selected) {
		if (selected instanceof EditPart) {
			Object model = ((EditPart) selected).getModel();
			return model instanceof View ? ((View) model).getElement() : null;
		}
		if (selected instanceof View) {
			return ((View) selected).getElement();
		}
		if (selected instanceof IAdaptable) {
			View view = (View) ((IAdaptable) selected).getAdapter(View.class);
			if (view != null) {
				return view.getElement();
			}
		}
		return selected;
	}

	/**
	 * Utility for subclasses to ensures the whole page is read-only. 
	 * When needed, this method is expected to be called immediately after creation of the page controls. 
	 */
	protected void forcePageReadOnly() {
		ROEntry root = new ROEntry(OperationHistoryFactory.getOperationHistory());
		root.setPropertySourceProvider(getPropertySourceProvider());
		page.setRootEntry(root);
	}

	protected static class ROEntry extends UndoableModelPropertySheetEntry {

		public ROEntry(IOperationHistory operationHistory) {
			super(operationHistory);
		}

		@Override
		public CellEditor getEditor(Composite parentComposite) {
			return null; // do not allow editing
		}

		@Override
		protected PropertySheetEntry createChildEntry() {
			return new ROEntry(getOperationHistory());
		}

	}

}
