/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 */
package org.bonitasoft.studio.common.jface.databinding;

import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;

/**
 * @author Baptiste Mesta
 *
 */
public class CellEditorViewerProperties extends SimpleValueProperty {
	public Object getValueType() {
		return StructuredViewer.class;
	}

	protected Object doGetValue(Object source) {
		if(source instanceof ComboBoxViewerCellEditor){
			return ((ComboBoxViewerCellEditor) source).getViewer();
		}
		return null;
	}

	protected void doSetValue(Object source, Object value) {
		((ComboBoxViewerCellEditor) source).getViewer().setSelection(new StructuredSelection(value));
	}

	public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
		return null;
	}

	public String toString() {
		return super.toString();
	}
}