/*******************************************************************************
 * Copyright (c) 2001, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal.views.properties.tabbed.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Viewer representing the property sheet page. On the left side it
 * contains a list of tabs and on the right side it contains the
 * current selected tab.
 *
 * @author Anthony Hunter
 */
public class TabbedPropertyViewer extends StructuredViewer {

	protected TabbedPropertyList list;
	protected List elements;
	protected IWorkbenchPart part;

	/**
	 * Constructor for TabbedPropertyViewer.
	 *
	 * @param list
	 *            the TabbedPropertyList.
	 */
	public TabbedPropertyViewer(TabbedPropertyList list) {
		this.list = list;
		hookControl(list);
		elements = new ArrayList();
	}

	/**
	 * Returns the element with the given index from this list viewer.
	 * Returns <code>null</code> if the index is out of range.
	 *
	 * @param index the zero-based index
	 * @return the element at the given index, or <code>null</code> if the
	 *   index is out of range
	 */
	public Object getElementAt(int index) {
		if (index >= 0 && index < elements.size()) {
			return elements.get(index);
		}
		return null;
	}

	/**
	 * Returns the zero-relative index of the item which is currently
	 * selected in the receiver, or -1 if no item is selected.
	 *
	 * @return the index of the selected item
	 */
	public int getSelectionIndex() {
		return list.getSelectionIndex();
	}

	protected Widget doFindInputItem(Object element) {
		/* not implemented */
		return null;
	}

	protected Widget doFindItem(Object element) {
		/* not implemented */
		return null;
	}

	protected void doUpdateItem(Widget item, Object element, boolean fullMap) {
		/* not implemented */
	}

	protected List getSelectionFromWidget() {
		int index = list.getSelectionIndex();
		if (index == TabbedPropertyList.NONE) {
			return Collections.EMPTY_LIST;
		}
		List result = new ArrayList(1);
		result.add(getElementAt(index));
		return result;
	}

	protected void internalRefresh(Object element) {
		/* not implemented */
	}

	public void reveal(Object element) {
		/* not implemented */
	}

	/**
	 * We do not consider multiple selections. Only the first
	 * element will represent the selection.
	 */
	protected void setSelectionToWidget(List l, boolean reveal) {
		if (l == null || l.size() == 0) { // clear selection
			list.deselectAll();
		} else {
			Object object = l.get(0);
			int index = -1;
			for (int i = 0; i < elements.size(); i++) {
				if (elements.get(i) == object) {
					index = i;
				}
			}
			Assert.isTrue(index != -1, "Could not set the selected tab in the tabbed property viewer");//$NON-NLS-1$
			list.select(index);
		}
	}

	public Control getControl() {
		return list;
	}

	protected void inputChanged(Object input, Object oldInput) {
		elements.clear();
		Object[] children = getSortedChildren(getRoot());
		list.removeAll();
		for (Object child : children) {
			elements.add(child);
			mapElement(child, list);
		}
		list.setElements(children);
	}

	/**
	 * Set the input for viewer.
	 *
	 * @param part
	 *            the workbench part.
	 * @param selection
	 *            the selection in the workbench part.
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		this.part = part;
		setInput(selection);
	}

	/**
	 * Get the current workbench part.
	 *
	 * @return the current workbench part.
	 */
	public IWorkbenchPart getWorkbenchPart() {
		return part;
	}

	/**
	 * Returns the elements in this list viewer.

	 * @return the elements in this list viewer.
	 * @since 3.5
	 */
	public List getElements() {
		return elements;
	}
}
