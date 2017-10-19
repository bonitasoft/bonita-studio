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
package org.bonitasoft.studio.model.process.diagram.providers;

import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ProcessValidationProvider {

	/**
	* @generated
	*/
	private static boolean constraintsActive = false;

	public static boolean DISABLE_VALIDATION = false;

	/**
	* @generated
	*/
	public static boolean shouldConstraintsBePrivate() {
		return false;
	}

	/**
	* @generated
	*/
	public static void runWithConstraints(TransactionalEditingDomain editingDomain, Runnable operation) {
		if (!DISABLE_VALIDATION) {
			final Runnable op = operation;
			Runnable task = new Runnable() {
				public void run() {
					try {
						constraintsActive = true;
						op.run();
					} finally {
						constraintsActive = false;
					}
				}
			};
			if (editingDomain != null) {
				try {
					editingDomain.runExclusive(task);
				} catch (Exception e) {
					ProcessDiagramEditorPlugin.getInstance().logError("Validation failed", e); //$NON-NLS-1$
				}
			} else {
				task.run();
			}
		}
	}

	/**
	* @generated
	*/
	static boolean isInDefaultEditorContext(Object object) {
		if (shouldConstraintsBePrivate() && !constraintsActive) {
			return false;
		}
		if (object instanceof View) {
			return constraintsActive
					&& MainProcessEditPart.MODEL_ID.equals(ProcessVisualIDRegistry.getModelID((View) object));
		}
		return true;
	}

}
