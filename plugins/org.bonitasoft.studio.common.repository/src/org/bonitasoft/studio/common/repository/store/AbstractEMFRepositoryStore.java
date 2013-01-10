/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.BundleClassLoader;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.MigratorRegistry;
import org.eclipse.emf.edapt.migration.execution.ValidationLevel;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;


/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractEMFRepositoryStore<T extends EMFFileStore> extends AbstractRepositoryStore<T> implements IRepositoryStore<T> {

    private static final String MIGRATION_HISTORY_PATH = "process.history";
	private static final String PROCESS_NS_URI = "http://www.bonitasoft.org/ns/studio/process";
    private AdapterFactoryLabelProvider labelProvider;
    private final ComposedAdapterFactory adapterFactory;
    private Migrator migrator;
    public AbstractEMFRepositoryStore(){
        super();
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        addAdapterFactory(adapterFactory);
        final URI migratorURI = URI.createPlatformPluginURI("/" + Platform.getBundle("org.bonitasoft.studio-models").getSymbolicName() + "/" + MIGRATION_HISTORY_PATH, true);
        try {
            migrator =  new Migrator(migratorURI,new BundleClassLoader(MigrationPlugin.getDefault().getBundle()));
        } catch (MigrationException e) {
            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        }
    }

    public AdapterFactoryLabelProvider getLabelProvider(){
        if(labelProvider != null){
            labelProvider.dispose();
        }
        labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
        return labelProvider;
    }

    protected abstract void addAdapterFactory(ComposedAdapterFactory adapterFactory);

    public EditingDomain getEditingDomain() {
        return new AdapterFactoryEditingDomain(adapterFactory,new BasicCommandStack(), new HashMap<Resource, Boolean>());
    }

    @Override
    protected InputStream handlePreImport(String fileName, InputStream inputStream) {
        final InputStream is = super.handlePreImport(fileName, inputStream);
        final CopyInputStream copyIs = new CopyInputStream(is);
        final InputStream originalStream = copyIs.getCopy();
        final Resource resource = getTmpEMFResource(fileName,copyIs.getCopy());
        if(resource == null){
            BonitaStudioLog.debug("Failed to retrieve EMF Resource for migration", CommonRepositoryPlugin.PLUGIN_ID);
            copyIs.close();
            return originalStream;
        }
        final URI resourceURI = resource.getURI();
        final File tmpFile = new File(resource.getURI().toFileString());
        String nsURI = ReleaseUtils.getNamespaceURI(resourceURI);
        Migrator targetMigrator = migrator;
       if(migrator.getNsURIs().contains(nsURI) ){
            targetMigrator = migrator;
        }else{
            targetMigrator = MigratorRegistry.getInstance().getMigrator(nsURI);
        }

        if (targetMigrator != null) {
        	 Release release = null;
        	if(nsURI.equals(PROCESS_NS_URI)){
        		release = getRelease(targetMigrator,resource);
             }else{
            	 release = targetMigrator.getRelease(resourceURI).iterator().next();
             }
            if (!release.isLatestRelease()) {
                try {
                    performMigration(targetMigrator, resourceURI, release);
                    copyIs.close();
                    final FileInputStream newIs = new FileInputStream(tmpFile);
                    tmpFile.delete();
                    return newIs;
                } catch (MigrationException e) {
                    BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
                } catch (FileNotFoundException e) {
                    BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
                }
            }
        }

        tmpFile.delete();
        copyIs.close();
        return originalStream;
    }

    private Release getRelease(Migrator targetMigrator, Resource resource) {
		for(Release release : targetMigrator.getReleases()){
			for(EObject root : resource.getContents()){
				if(root instanceof MainProcess){
					String modelVersion = ((MainProcess) root).getBonitaModelVersion();
					if(release.getLabel().equals(modelVersion)){
						return release;
					}
				}
			}
		}
		return targetMigrator.getReleases().iterator().next(); //First release of all time
	}

	private void performMigration(Migrator migrator, URI resourceURI, Release release) throws MigrationException {
        migrator.setLevel(ValidationLevel.RELEASE);
        migrator.migrateAndSave(
                Collections.singletonList(resourceURI), release,
                null, Repository.NULL_PROGRESS_MONITOR);
    }

    private Resource getTmpEMFResource(String fileName,InputStream inputStream) {
        final TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
        FileOutputStream fos = null;
        File tmpFile = null ;
        try{
            tmpFile = File.createTempFile("tmp", fileName, ProjectUtil.getBonitaStudioWorkFolder());
            fos = new FileOutputStream(tmpFile);
            FileUtil.copy(inputStream, fos);
            final Resource resource = editingDomain.getResourceSet().createResource(URI.createFileURI(tmpFile.getAbsolutePath()));
            return resource;
        }catch (Exception e) {
            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    BonitaStudioLog.error(e,CommonRepositoryPlugin.PLUGIN_ID);
                }
            }
            if(inputStream != null){
                try{
                    inputStream.close();
                } catch (IOException e) {
                    BonitaStudioLog.error(e,CommonRepositoryPlugin.PLUGIN_ID);
                }
            }
        }

        return null;
    }

}
