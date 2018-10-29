/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.operation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.diagram.dialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class RenameDiagramOperation implements IRunnableWithProgress {

    private MainProcess diagram;
    private String diagramVersion;
    private String diagramName;
    private List<ProcessesNameVersion> pools = new ArrayList<>();

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(diagram);
        Assert.isNotNull(diagramVersion);
        Assert.isNotNull(diagramName);
        monitor.beginTask(Messages.renamingDiagram, IProgressMonitor.UNKNOWN);

        final String oldName = diagram.getName();
        final String oldVersion = diagram.getVersion();

        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        DiagramFileStore diagramFileStore = diagramStore.getDiagram(oldName, oldVersion);
        if (!(oldName.equals(diagramName) && oldVersion.equals(diagramVersion))) {
            IFile resource = diagramFileStore.getResource();
            try {
                resource.move(Path.fromOSString(NamingUtils.toDiagramFilename(diagramName, diagramVersion)), true, monitor);
            } catch (CoreException e) {
                throw new InvocationTargetException(e);
            }
            try {
                resource.getParent().refreshLocal(IResource.DEPTH_ONE, monitor);
            } catch (CoreException e) {
                throw new InvocationTargetException(e);
            }
        }
        diagramFileStore = diagramStore.getChild(NamingUtils.toDiagramFilename(diagramName, diagramVersion));
        diagram = diagramFileStore.getContent();
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagram);

        CompoundCommand compoundCommand = new CompoundCommand();
        if (!(oldName.equals(diagramName) && oldVersion.equals(diagramVersion))) {
            changeProcessNameAndVersion(diagram, compoundCommand, diagramName, diagramVersion, editingDomain);
        }
        for (final ProcessesNameVersion pnv : pools) {
            final AbstractProcess fromPool = pnv.getAbstractProcess();
            final String fromPoolName = fromPool.getName();
            final String fromPoolVersion = fromPool.getVersion();
            /* Find corresponding element in the duplicated model */
            for (final Element element : diagram.getElements()) {
                if (element instanceof AbstractProcess) {
                    if (element.getName().equals(fromPoolName)
                            && ((AbstractProcess) element).getVersion().equals(fromPoolVersion)) {
                        if (!pnv.getNewName().equals(fromPoolName) || !pnv.getNewVersion().equals(fromPoolVersion)) {
                            changeProcessNameAndVersion((AbstractProcess) element, compoundCommand, pnv.getNewName(),
                                    pnv.getNewVersion(), editingDomain);
                            break;
                        }
                    }
                }
            }

        }
        editingDomain.getCommandStack().execute(compoundCommand);
        diagramFileStore.save(null);
    }

    private void changeProcessNameAndVersion(final AbstractProcess process, final CompoundCommand cc,
            final String newProcessLabel,
            final String newProcessVersion, EditingDomain editingDomain) {
        cc.append(SetCommand.create(editingDomain, process, ProcessPackage.Literals.ELEMENT__NAME, newProcessLabel));
        cc.append(SetCommand.create(editingDomain, process, ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION,
                newProcessVersion));
    }

    public void setDiagramToDuplicate(final MainProcess diagram) {
        this.diagram = diagram;
    }

    public void setNewDiagramName(final String diagramName) {
        this.diagramName = diagramName;
    }

    public void setPoolsRenamed(final List<ProcessesNameVersion> pools) {
        this.pools = pools;
    }

    public void setNewDiagramVersion(final String diagramVersion) {
        this.diagramVersion = diagramVersion;
    }

}
