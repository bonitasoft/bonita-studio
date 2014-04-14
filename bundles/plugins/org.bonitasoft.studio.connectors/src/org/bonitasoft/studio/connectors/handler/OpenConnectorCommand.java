///**
// * Copyright (C) 2009 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.connectors.handler;
//
//import org.bonitasoft.studio.connectors.ui.wizard.OpenConnectorWizard;
//import org.eclipse.core.commands.AbstractHandler;
//import org.eclipse.core.commands.ExecutionEvent;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.jface.wizard.WizardDialog;
//import org.eclipse.ui.PlatformUI;
//
///**
// * @author Mickael Istria
// *
// */
//public class OpenConnectorCommand extends AbstractHandler {
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
//	 */
//	public Object execute(ExecutionEvent event) throws ExecutionException {
//		OpenConnectorWizard openConnectorWizard = new OpenConnectorWizard();
//		new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), openConnectorWizard).open();
//		return null;
//	}
//
//}
