/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * @author Aurelien Pupier
 *
 */
public class AddDataTypeCommand extends AbstractTransactionalCommand {

	private EnumType dataType;
	private String name;
	private String description;
	private List<String> literals;
	private EObject process;

	public AddDataTypeCommand(TransactionalEditingDomain domain, Element activity, String name, String description,List<String> literals) {
		super(domain, Messages.addDataCommandLabel, getWorkspaceFiles(activity));
		this.process = ModelHelper.getMainProcess(activity);
		this.name = name;
		this.description = description;
		this.literals =literals;		
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		dataType = ProcessFactory.eINSTANCE.createEnumType();
		for(String lit : literals){
			dataType.getLiterals().add(lit);
		}
		dataType.setName(NamingUtils.toJavaIdentifier(NamingUtils.convertToId(name),true));
		dataType.setDocumentation(description);
		((AbstractProcess)process).getDatatypes().add(dataType);

	//	GroovyDocumentUtil.createGroovyType(dataType,literals);
		return CommandResult.newOKCommandResult(dataType);
	}

	/**
	 * @return
	 */
	public DataType getCreatedDataType() {
		return dataType;
	}
}
