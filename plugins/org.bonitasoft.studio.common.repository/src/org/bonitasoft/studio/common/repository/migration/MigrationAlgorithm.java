/**
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
package org.bonitasoft.studio.common.repository.migration;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;


/**
 * This interface is to be used when writing a transformation on a {@link MainProcess}.
 * It is typically used by {@link ProcessMigrationTool}, where the transformation is an update
 * of process contents to import older versions of processes.
 * @author Mickael Istria
 */
public interface MigrationAlgorithm {

	/**
	 * @param process The artifact should be transformed to newer version
	 */
	void applyTo(DiagramEditPart diagramEp,TransactionalEditingDomain editingDomain);

}
