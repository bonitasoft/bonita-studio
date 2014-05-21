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

import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.EnumType;
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
public class UpdateDataTypeCommand extends AbstractTransactionalCommand {

	private DataType dataType;
	private String name;
	private String description;
	private AbstractProcess process ;
	private List<String> literals;

	
	public UpdateDataTypeCommand(TransactionalEditingDomain editingDomain,DataType dataType, String name, String description, List<String> literals) {
		super(editingDomain,UpdateDataTypeCommand.class.getName(),getWorkspaceFiles(dataType));
		this.name = name;
		this.description = description;
		this.dataType = dataType;
		this.process = ModelHelper.getMainProcess(dataType);
		this.literals = literals ;
	}
	

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
	//	GroovyDocumentUtil.deleteGroovyType(dataType);
		process.getDatatypes().remove(dataType);

		((EnumType)dataType).getLiterals().clear();
		
		for(String lit : literals){
			((EnumType)dataType).getLiterals().add(lit);
		}

		dataType.setName(NamingUtils.toJavaIdentifier(NamingUtils.convertToId(name),true));
		dataType.setDocumentation(description);
		process.getDatatypes().add(dataType);
		
	//	GroovyDocumentUtil.createGroovyType(dataType,literals);
		return CommandResult.newOKCommandResult();
	}

}
