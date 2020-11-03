/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.providers;

import java.util.List;

import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapFunctionField;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Maxence Raoux
 * 
 */
public class TreeContentProvider implements ITreeContentProvider {

	private List<SapFunctionField> fields;

	public TreeContentProvider(List<SapFunctionField> fields) {
		this.fields = fields;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return fields.toArray(new Object[fields.size()]);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof SapFunctionField) {
			final SapFunctionField field = (SapFunctionField) parentElement;
			if (field.isStructure() || field.isTable()) {
				return field.getFieldsList().toArray(
						new Object[field.getFieldsList().size()]);
			}
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof SapFunctionField) {
			final SapFunctionField field = (SapFunctionField) element;
			return field.getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof SapFunctionField) {
			final SapFunctionField field = (SapFunctionField) element;
			return (field.isStructure() || field.isTable());
		}
		return false;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
