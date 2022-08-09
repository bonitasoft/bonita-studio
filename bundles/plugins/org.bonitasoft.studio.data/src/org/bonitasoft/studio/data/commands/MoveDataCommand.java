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

import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * @author Romain Bioteau
 * 
 */
public class MoveDataCommand extends AbstractTransactionalCommand {

	private DataAware container;
	private DataAware target;
	private List<Data> datas;
	private List<Data> dataNotMoved;

	public MoveDataCommand(TransactionalEditingDomain editingDomain, DataAware container, List<Data> datas, DataAware target) {
		super(editingDomain, MoveDataCommand.class.getName(), getWorkspaceFiles(datas));
		this.container = container;
		this.target = target;
		this.datas = datas;
		this.dataNotMoved = new ArrayList<Data>();
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		for (Data data : datas) {

			if (data.isTransient()) {
				data.setTransient(false);// transient data not supported on
											// process
			}
			boolean exists = false;
			for (Data d : target.getData()) {
				if (d.getName().equals(data.getName())) {
					exists = true;
				}
			}
			if (!exists) {
				container.getData().remove(data);
				target.getData().add(data);
			} else {
				dataNotMoved.add(data);
			}
		}
		if (dataNotMoved.isEmpty()) {
			return CommandResult.newOKCommandResult();
		} else {
			return CommandResult.newWarningCommandResult("Some Data cannot be moved", dataNotMoved);
		}
	}

}
