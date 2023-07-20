/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.providers;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapFunctionField;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * @author Maxence Raoux
 * 
 */
public class TreeLabelProvider implements ILabelProvider {
	private List<ILabelProviderListener> listeners;
	boolean preserveCase;

	public TreeLabelProvider() {
		listeners = new ArrayList<ILabelProviderListener>();
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void dispose() {
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof SapFunctionField) {
			SapFunctionField field = (SapFunctionField) element;
			if(field.isOptionnal()) {
				return field.getName();
			}
			else {
				return field.getName() + " *";
			}
		} else {
			return null;
		}
	}
}
