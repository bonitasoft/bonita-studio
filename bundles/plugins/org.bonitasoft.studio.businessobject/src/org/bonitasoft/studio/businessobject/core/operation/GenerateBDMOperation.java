/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.core.operation;

import static com.google.common.io.ByteStreams.toByteArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.business.data.generator.AbstractBDMJarBuilder;
import org.bonitasoft.engine.business.data.generator.client.ClientBDMJarBuilder;
import org.bonitasoft.engine.business.data.generator.client.ResourcesLoader;
import org.bonitasoft.engine.business.data.generator.compiler.JDTCompiler;
import org.bonitasoft.engine.business.data.generator.filter.OnlyDAOImplementationFileFilter;
import org.bonitasoft.engine.business.data.generator.filter.WithoutDAOImplementationFileFilter;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleWiring;

public class GenerateBDMOperation implements IRunnableWithProgress {

    private static final String BDM_DEPLOYED_TOPIC = "bdm/deployed";
    private static final String BDM_CLIENT = "bdm-client";
    private static final String BDM_DAO = "bdm-dao";
    private static final String MODEL = "model";
    private static final String FILE_CONTENT = "fileContent";
    private static final String BDM_ARTIFACT_DESCRIPTOR = "artifactDescriptor";

    private final BusinessObjectModelFileStore fileStore;

    private static Object generationLock = new Object();

    public GenerateBDMOperation(final BusinessObjectModelFileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        synchronized (generationLock) {
            doGenerateBDM(monitor);
        }
    }

    protected void doGenerateBDM(IProgressMonitor monitor) throws InvocationTargetException {
        if (monitor == null) {
            monitor = Repository.NULL_PROGRESS_MONITOR;
        }
        final BusinessObjectModel model = fileStore.getContent();
        if (containsBusinessObjects(model)) {
            monitor.beginTask(Messages.generatingJarFromBDMModel, IProgressMonitor.UNKNOWN);
            BonitaStudioLog.debug(Messages.generatingJarFromBDMModel, BusinessObjectPlugin.PLUGIN_ID);
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            try {
                final Map<String, byte[]> resources = new HashMap<>();
                JDTCompiler compiler = new JDTCompiler();
                // Build jar with Model
                ResourcesLoader bundleResourcesLoader = new ResourcesLoader();
                AbstractBDMJarBuilder builder = new ClientBDMJarBuilder(compiler, bundleResourcesLoader);
                final byte[] modelJarContent = builder.build(model, new WithoutDAOImplementationFileFilter());
                resources.put(BDM_CLIENT, modelJarContent);

                // Build jar with DAO
                builder = new ClientBDMJarBuilder(compiler, bundleResourcesLoader);
                final byte[] daoJarContent = builder.build(model, new OnlyDAOImplementationFileFilter());
                resources.put(BDM_DAO, daoJarContent);

                updateDependency(resources.get(BDM_CLIENT));
                final Map<String, Object> data = new HashMap<>();
                data.put(MODEL, model);
                data.put(BDM_DAO, resources.get(BDM_DAO));
                data.put(BDM_ARTIFACT_DESCRIPTOR,fileStore.loadArtifactDescriptor());
                data.put(FILE_CONTENT, new String(((BusinessObjectModelRepositoryStore) fileStore.getParentStore()).getConverter().marshall(model)));
                eventBroker().send(BDM_DEPLOYED_TOPIC, data);
            } catch (final Exception e) {
                throw new InvocationTargetException(e);
            }
        } else {
            removeDependency();
        }
    }

    protected IEventBroker eventBroker() {
        return (IEventBroker) PlatformUI.getWorkbench().getService(IEventBroker.class);
    }

    protected void removeDependency() {
        final DependencyRepositoryStore dependencyRepositoryStore = fileStore.getRepository()
                .getRepositoryStore(DependencyRepositoryStore.class);
        final DependencyFileStore bdmFileStore = dependencyRepositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (bdmFileStore != null) {
            bdmFileStore.delete();
        }
    }

    private boolean containsBusinessObjects(final BusinessObjectModel bom) {
        return bom != null && !bom.getBusinessObjects().isEmpty();
    }

    protected Map<String, byte[]> retrieveContent(final byte[] zipContent) throws IOException {
        Assert.isNotNull(zipContent);
        final Map<String, byte[]> result = new HashMap<>();
        try (ByteArrayInputStream is = new ByteArrayInputStream(zipContent);
                ZipInputStream zis = new ZipInputStream(is);) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String entryName = entry.getName();
                if (entryName.contains(MODEL) && entryName.endsWith(".jar")) {
                    result.put(BDM_CLIENT, toByteArray(zis));
                }
                if (entryName.contains("dao") && entryName.endsWith(".jar")) {
                    result.put(BDM_DAO, toByteArray(zis));
                }
            }
        }
        return result;
    }

    protected void updateDependency(final byte[] jarContent) throws InvocationTargetException {
        try (ByteArrayInputStream is = new ByteArrayInputStream(jarContent)) {
            final DependencyRepositoryStore store = fileStore.getRepository()
                    .getRepositoryStore(DependencyRepositoryStore.class);
            final DependencyFileStore depFileStore = store.getChild(fileStore.getDependencyName(), true);
            if (depFileStore != null) {
                depFileStore.delete();
            }
            final DependencyFileStore bdmJarFileStore = store.createRepositoryFileStore(fileStore.getDependencyName());
            bdmJarFileStore.save(is);
            fileStore.getRepository().build(Repository.NULL_PROGRESS_MONITOR);
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        }
    }

}
