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
package org.bonitasoft.studio.diagram.custom.operation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.diagram.dialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 */
public class DuplicateDiagramOperation implements IRunnableWithProgress {

    private MainProcess diagram;
    private String diagramVersion;
    private String diagramName;
    private List<ProcessesNameVersion> pools = new ArrayList<>();

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(diagram);
        Assert.isNotNull(diagramVersion);
        Assert.isNotNull(diagramName);
        monitor.beginTask(Messages.duplicatingDiagram, IProgressMonitor.UNKNOWN);

        final String oldName = diagram.getName();
        final String oldVersion = diagram.getVersion();

        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        DiagramFileStore newFildeStore = null;
        if (!(oldName.equals(diagramName) && oldVersion.equals(diagramVersion))) {
            newFildeStore = copyDiagram();
        }
        if (newFildeStore == null) {
            newFildeStore = diagramStore
                    .createRepositoryFileStore(NamingUtils.toDiagramFilename(diagramName, diagramVersion));
        }
        final MainProcess newDiagram = newFildeStore.getContent();
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(newFildeStore.getEMFResource());
        editingDomain.getCommandStack().execute(
                SetCommand.create(editingDomain, newDiagram, ProcessPackage.Literals.ABSTRACT_PROCESS__AUTHOR,
                        System.getProperty("user.name", "Unknown")));

        boolean poolRenamed = false;
        for (final ProcessesNameVersion pnv : pools) {
            final AbstractProcess fromPool = pnv.getAbstractProcess();
            final String fromPoolName = fromPool.getName();
            final String fromPoolVersion = fromPool.getVersion();
            /* Find corresponding element in the duplicated model */
            for (final Element element : newDiagram.getElements()) {
                if (element instanceof AbstractProcess) {
                    if (element.getName().equals(fromPoolName)
                            && ((AbstractProcess) element).getVersion().equals(fromPoolVersion)) {
                        if (!pnv.getNewName().equals(fromPoolName) || !pnv.getNewVersion().equals(fromPoolVersion)) {
                            changeProcessNameAndVersion((AbstractProcess) element, editingDomain, pnv.getNewName(),
                                    pnv.getNewVersion());
                            poolRenamed = true;
                            break;
                        }
                    }
                }
            }

        }
        if (poolRenamed) {
            newFildeStore.save(null);
        }
    }

    private DiagramFileStore copyDiagram() {
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);

        final Copier copier = new Copier(true, false);
        final Collection<EObject> copiedElements = copier.copyAll(diagram.eResource().getContents());
        copier.copyReferences();//don't forget this line otherwise we loose link between diagrams and model
        final DiagramFileStore store = diagramStore
                .createRepositoryFileStore(NamingUtils.toDiagramFilename(diagramName, diagramVersion));
        store.save(copiedElements);

        final MainProcess newDiagram = store.getContent();
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(newDiagram.eResource());
        changeProcessNameAndVersion(newDiagram, editingDomain, diagramName, diagramVersion);
        editingDomain.getCommandStack().execute(
                SetCommand.create(editingDomain, newDiagram, ProcessPackage.Literals.MAIN_PROCESS__CONFIG_ID,
                        ConfigurationIdProvider
                                .getConfigurationIdProvider().getConfigurationId(newDiagram)));
        duplicateConfigurations(diagram, newDiagram);
        return store;
    }

    private void duplicateConfigurations(final MainProcess sourceDiagram, final MainProcess newDiagram) {
        final ProcessConfigurationRepositoryStore confStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        final List<Pool> pools = ModelHelper.getAllItemsOfType(sourceDiagram, ProcessPackage.Literals.POOL);
        for (final Pool p : pools) {
            final ProcessConfigurationFileStore file = confStore
                    .getChild(ModelHelper.getEObjectID(p) + "." + ProcessConfigurationRepositoryStore.CONF_EXT);
            if (file != null) {
                final Copier copier = new Copier(true, false);
                final Collection<EObject> copiedElements = copier.copyAll(file.getContent().eResource().getContents());
                copier.copyReferences();
                if (!copiedElements.isEmpty()) {
                    final int index = sourceDiagram.getElements().indexOf(p);
                    final Pool newPool = (Pool) newDiagram.getElements().get(index);
                    final ProcessConfigurationFileStore newFile = confStore
                            .createRepositoryFileStore(ModelHelper.getEObjectID(newPool) + "."
                                    + ProcessConfigurationRepositoryStore.CONF_EXT);
                    newFile.save(copiedElements.iterator().next());
                }
            }
        }
    }

    private void replaceIdInFile(final IFile file, final Copier copier) throws IOException, CoreException {
        final File fileToModify = file.getLocation().toFile();
        StringBuilder sb = new StringBuilder();
        String line = ""; //$NON-NLS-1$
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileToModify));) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        }
        for (final java.util.Map.Entry<EObject, EObject> entry : copier.entrySet()) {
            final String originalId = ModelHelper.getEObjectID(entry.getKey());
            final String newId = ModelHelper.getEObjectID(entry.getValue());
            final String content = sb.toString();
            if (content.contains(originalId)) {
                sb = new StringBuilder(content.replaceAll(originalId, newId));
            }
        }

        try (final FileWriter writer = new FileWriter(fileToModify.getAbsolutePath());) {
            writer.write(sb.toString());
        }
        file.refreshLocal(IResource.DEPTH_ONE, Repository.NULL_PROGRESS_MONITOR);
    }


    private void changeProcessNameAndVersion(final AbstractProcess process, final TransactionalEditingDomain editingDomain,
            final String newProcessLabel,
            final String newProcessVersion) {
        editingDomain.getCommandStack()
                .execute(SetCommand.create(editingDomain, process, ProcessPackage.Literals.ELEMENT__NAME, newProcessLabel));
        editingDomain.getCommandStack()
                .execute(SetCommand.create(editingDomain, process, ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION,
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
