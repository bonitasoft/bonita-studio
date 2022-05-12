/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.custom.repository;

import java.util.Collection;
import java.util.Collections;

import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;


/**
 * @author Romain Bioteau
 *
 */
public class SaveDiagramResourceCommand extends AbstractTransactionalCommand {

    private final Object content;
    private final Resource resource;

    public SaveDiagramResourceCommand(final Object content, final TransactionalEditingDomain domain, final Resource resourceToSave) {
        super(domain, SaveDiagramResourceCommand.class.getName(), Collections.singletonList(WorkspaceSynchronizer.getFile(resourceToSave)));
        this.content = content;
        resource = resourceToSave;
    }

    /* (non-Javadoc)
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
        if (content instanceof MainProcess) {
            if (!resource.getContents().isEmpty()) {
                resource.getContents().remove(0);
            }
            resource.getContents().add(0, (MainProcess) content);
        } else if (content instanceof Diagram) {
            if (resource.getContents().size() > 1) {
                resource.getContents().remove(1);
            }
            resource.getContents().add(1, (EObject) content);
        } else if (content instanceof Collection) {
            final Collection<EObject> collectionsOfContents = (Collection<EObject>) content;
            resource.getContents().addAll(collectionsOfContents);
        }
        return CommandResult.newOKCommandResult(resource);
    }

    @Override
    public boolean canExecute() {
        return super.canExecute() &&
                content instanceof MainProcess || content instanceof Diagram || content instanceof Collection;
    }

}
