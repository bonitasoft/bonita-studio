/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.repository;

import java.util.Collection;

import org.bonitasoft.studio.model.configuration.Configuration;
import org.eclipse.emf.common.command.Command;

/**
 * @author aurelie Zara
 *
 */
public class SaveConfigurationEMFCommand implements Command {

	private final String label = "saveConfigurationEmfCommand";
	private final String description="generate an EMF command that save configuration and allow undo on save";
	private ProcessConfigurationFileStore processConfigurationFileStore;
	private Configuration oldConfiguration;
	private Configuration newConfiguration;
	
	public SaveConfigurationEMFCommand(ProcessConfigurationFileStore processConfigurationFileStore,Configuration oldConfiguration,Configuration newConfiguration){
		this.processConfigurationFileStore = processConfigurationFileStore;
		this.oldConfiguration = oldConfiguration;
		this.newConfiguration = newConfiguration;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#canExecute()
	 */
	@Override
	public boolean canExecute() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	@Override
	public void execute() {
		processConfigurationFileStore.save(newConfiguration);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#undo()
	 */
	@Override
	public void undo() {
		processConfigurationFileStore.save(oldConfiguration);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	@Override
	public void redo() {
		processConfigurationFileStore.save(newConfiguration);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#getResult()
	 */
	@Override
	public Collection<?> getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#getAffectedObjects()
	 */
	@Override
	public Collection<?> getAffectedObjects() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#getLabel()
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#dispose()
	 */
	@Override
	public void dispose() {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#chain(org.eclipse.emf.common.command.Command)
	 */
	@Override
	public Command chain(Command command) {
		return null;
	}

}
