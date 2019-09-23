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
package org.bonitasoft.studio.model.process.diagram.part;

import java.util.Set;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @generated
 */
public class DiagramEditorContextMenuProvider extends DiagramContextMenuProvider {

	/**
	* @generated
	*/
	private IWorkbenchPart part;

	/**
	* @generated
	*/
	private DeleteElementAction deleteAction;

	/**
	* @generated
	*/
	public DiagramEditorContextMenuProvider(IWorkbenchPart part, EditPartViewer viewer) {
		super(part, viewer);
		this.part = part;
		deleteAction = new DeleteElementAction(part);
		deleteAction.init();
	}

	/**
	* @generated
	*/
	public void dispose() {
		if (deleteAction != null) {
			deleteAction.dispose();
			deleteAction = null;
		}
		super.dispose();
	}

	/**
	*@Generated BonitaSoft
	*/
	@Override
	protected void addDefaultExclusions() {
		super.addDefaultExclusions();
		final Set exclusionSet = getExclusionSet();
		exclusionSet.add("org.eclipse.releng.tools.advancedFixCopyrights");
		exclusionSet.add("groovyfile");
		exclusionSet.add("groovyresource");
	}

	/**
	*@Generated BonitaSoft
	*/
	public void buildContextMenu(final IMenuManager menu) {
		getViewer().flush();
	}
}
