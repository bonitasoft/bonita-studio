/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter.bpmn;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.bonitasoft.bonita2bpmn.extension.BonitaModelExporterImpl;
import org.bonitasoft.bonita2bpmn.transfo.BonitaToBPMNExporter;
import org.bonitasoft.bonita2bpmn.transfo.ConnectorTransformationXSLProvider;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.exporter.Activator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;

public class BPMNBarResourceProvider implements BARResourcesProvider {

    @Override
    public IStatus addResourcesForConfiguration(final BusinessArchiveBuilder builder,
            final AbstractProcess process, final Configuration configuration) throws Exception {
        File destFile = null;
        try {
            Resource eResource = process.eResource();
            if (eResource != null) {
                destFile = File.createTempFile("process", ".bpmn");
                DiagramRepositoryStore diagramRepoStore = RepositoryManager.getInstance()
                        .getRepositoryStore(DiagramRepositoryStore.class);
                ConnectorDefRepositoryStore connectorDefRepoStore = RepositoryManager.getInstance()
                        .getRepositoryStore(ConnectorDefRepositoryStore.class);
                List<AbstractProcess> allProcesses = diagramRepoStore.hasComputedProcesses()
                        ? diagramRepoStore.getComputedProcesses() : diagramRepoStore.getAllProcesses();
                List<ConnectorDefinition> definitions = connectorDefRepoStore.getDefinitions();
                IModelSearch modelSearch = new ModelSearch(
                        () -> allProcesses);
                new BonitaToBPMNExporter().export(new BonitaModelExporterImpl(eResource, modelSearch), modelSearch,
                        () -> definitions,
                        destFile, ConnectorTransformationXSLProvider.DEFAULT, ProductVersion.CURRENT_VERSION);
                builder.addExternalResource(new BarResource("process.bpmn", Files.readAllBytes(destFile.toPath())));
            } else {
                BonitaStudioLog.warning(
                        String.format(
                                "Process %s (%s) is not contained in a Resource. BPMN file will not be added to the bar.",
                                process.getName(), process.getVersion()),
                        Activator.PLUGIN_ID);
            }
        } finally {
            if (destFile != null) {
                destFile.delete();
            }
        }
        return Status.OK_STATUS;
    }

}
