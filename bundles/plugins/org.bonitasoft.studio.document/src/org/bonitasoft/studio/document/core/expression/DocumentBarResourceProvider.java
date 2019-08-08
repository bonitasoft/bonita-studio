/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.document.core.expression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.document.core.repository.DocumentFileStore;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;

/**
 * @author Romain Bioteau
 */
public class DocumentBarResourceProvider implements BARResourcesProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.BARResourcesProvider#getResourcesForConfiguration(org.bonitasoft.studio.model.process.AbstractProcess,
     * org.bonitasoft.studio.model.configuration.Configuration, org.bonitasoft.engine.bpm.model.DesignProcessDefinition, java.util.Map)
     */
    @Override
    public void addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process,
            final Configuration configuration) {
        final List<BarResource> resources = new ArrayList<>();
        if (process instanceof Pool) {
            final List<Document> documents = ((Pool) process).getDocuments();
            final DocumentRepositoryStore store = RepositoryManager.getInstance()
                    .getRepositoryStore(DocumentRepositoryStore.class);
            for (final Document document : documents) {
                if (document.getDocumentType().equals(org.bonitasoft.studio.model.process.DocumentType.INTERNAL)) {
                    final String documentID = document.getDefaultValueIdOfDocumentStore();
                    if (documentID != null) {
                        final DocumentFileStore artifact = store.getChild(documentID);
                        if (artifact != null) {
                            try {
                                addFileContents(resources, artifact.getResource().getLocation().toFile(), "");
                            } catch (final IOException e) {
                                BonitaStudioLog.error(e);
                            }
                        }
                    }
                }
            }

            for (final BarResource barResource : resources) {
                builder.addDocumentResource(barResource);
            }
        }
    }

    private void addFileContents(final List<BarResource> resources, final File file, String barPathPrefix)
            throws FileNotFoundException, IOException {
        if (file.exists()) {
            try (final InputStream stream = new FileInputStream(file);) {
                final byte[] jarBytes = new byte[(int) file.length()];
                stream.read(jarBytes);
                if (barPathPrefix != null && !barPathPrefix.isEmpty() && !barPathPrefix.endsWith("/")) {
                    barPathPrefix = barPathPrefix + "/";
                }
                resources.add(new BarResource(barPathPrefix + file.getName(), jarBytes));
            }
        }
    }

}
