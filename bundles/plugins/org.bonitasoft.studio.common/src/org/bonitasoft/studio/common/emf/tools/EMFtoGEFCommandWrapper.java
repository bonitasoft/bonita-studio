/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *    Jacques Lescot (Anyware Technologies) - initial API and implementation
 *******************************************************************************/
package org.bonitasoft.studio.common.emf.tools;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.commands.Command;

// TODO: Auto-generated Javadoc
/**
 * A GEF Command that wraps an EMF command. Each method is redirected to the EMF one. <br>
 * Adapts an {@link org.eclipse.emf.common.command.Command EMF Command} to be a {@link org.eclipse.gef.commands.Command GEF Command}.
 * 
 * Creation : 21 fev. 2006
 * 
 * @author aarong, <a href="mailto:jacques.lescot@anyware-tech.com">Jacques LESCOT</a>
 */
public class EMFtoGEFCommandWrapper extends Command {

	/**
	 * The wrapped EMF Command. Package-level visibility so that the command stack wrapper can
	 * access the field.
	 */
	private final org.eclipse.emf.common.command.Command emfCommand;
	private EditingDomain domain;

	/**
	 * Constructor.
	 * 
	 * @param command
	 *        the wrapped EMF command
	 */
	public EMFtoGEFCommandWrapper(final org.eclipse.emf.common.command.Command command,EditingDomain domain) {
		super(command.getLabel());
		emfCommand = command;
		this.domain = domain;
	}

	/**
	 * Returns the wrapped EMF command.
	 * 
	 * @return the EMF command
	 */
	// @unused
	public org.eclipse.emf.common.command.Command getEMFCommand() {
		return emfCommand;
	}

	/**
	 * Dispose.
	 * 
	 * @see org.eclipse.gef.commands.Command#dispose()
	 */
	@Override
	public void dispose() {
		emfCommand.dispose();
	}

	/**
	 * Can execute.
	 * 
	 * @return true, if can execute
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public boolean canExecute() {
		return emfCommand.canExecute();
	}

	/**
	 * Can undo.
	 * 
	 * @return true, if can undo
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return emfCommand.canUndo();
	}

	/**
	 * Execute.
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		domain.getCommandStack().execute(emfCommand);
	}

	/**
	 * Redo.
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	@Override
	public void redo() {
		emfCommand.redo();
	}

	/**
	 * Undo.
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		emfCommand.undo();
	}
}