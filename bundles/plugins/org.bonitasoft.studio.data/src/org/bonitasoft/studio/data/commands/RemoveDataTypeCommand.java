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
 
package org.bonitasoft.studio.data.commands;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * @author Romain Bioteau
 *
 */
public class RemoveDataTypeCommand extends AbstractTransactionalCommand  {
	
	private AbstractProcess container;
	private DataType toRemoveData;

	public RemoveDataTypeCommand(TransactionalEditingDomain editingDomain,EObject container, DataType toRemoveData) {
		super(editingDomain, RemoveDataTypeCommand.class.getName(), getWorkspaceFiles(container));
		this.container = ModelHelper.getMainProcess(container);
		this.toRemoveData = toRemoveData;
	}

	/**
	 * @param container
	 */
	private void removeReferencesInChildren(Container container) {
		for (Element item : container.getElements()) {
			// an element can be both ConnectableElement
			// and Container (eg Processes)
			if (item instanceof ConnectableElement) {
				removeReferences((ConnectableElement)item);
			}
			if (item instanceof Container) {
				removeReferencesInChildren((Container)item);
			}
		}
	}

	/**
	 * @param item
	 */
	private void removeReferences(ConnectableElement item) {
		List<Data> soonRemoved = new ArrayList<Data>();
		for (Data data : item.getData()) {
			if (data.getDataType().equals(toRemoveData)) {
				soonRemoved.add(data);
			}
		}
		item.getData().removeAll(soonRemoved);
	}


	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
	
		//GroovyDocumentUtil.deleteGroovyType(toRemoveData);
		removeReferences(container);
		removeReferencesInChildren(container);
		((AbstractProcess)container).getDatatypes().remove(toRemoveData);
		
		return CommandResult.newOKCommandResult();
	}
}

