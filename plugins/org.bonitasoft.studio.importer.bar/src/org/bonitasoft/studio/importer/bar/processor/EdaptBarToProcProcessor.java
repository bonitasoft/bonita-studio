/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.importer.ToProcProcessor;
import org.bonitasoft.studio.importer.bar.BarImporterPlugin;
import org.bonitasoft.studio.importer.bar.exception.IncompatibleVersionException;
import org.bonitasoft.studio.importer.bar.preferences.BarImporterPreferenceConstants;
import org.bonitasoft.studio.importer.bar.ui.MigrationWarningWizard;
import org.bonitasoft.studio.migration.migrator.BOSMigrator;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.BundleClassLoader;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.ValidationLevel;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;


/**
 * @author Romain Bioteau
 *
 */
public class EdaptBarToProcProcessor extends ToProcProcessor {

    private static final String SUPPORTED_VERSION = "5.9";
    private static final String MIGRATION_HISTORY_PATH = "models/v60/process.history";
    private File migratedProc;
    private BOSMigrator migrator;

    public EdaptBarToProcProcessor(){
        final URI migratorURI = URI.createPlatformPluginURI("/" + BarImporterPlugin.getDefault().getBundle().getSymbolicName() + "/" + MIGRATION_HISTORY_PATH, true);
        try {
            migrator =  new BOSMigrator(migratorURI, new BundleClassLoader(BarImporterPlugin.getDefault().getBundle()));
        } catch (MigrationException e) {
            BonitaStudioLog.error(e,BarImporterPlugin.PLUGIN_ID);
        }
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#createDiagram(java.net.URL, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public File createDiagram(URL sourceFileURL, IProgressMonitor progressMonitor) throws Exception {
        if(!FileActionDialog.getDisablePopup() && displayMigrationWarningPopup()){
            int result =  new WizardDialog(Display.getDefault().getActiveShell(), new MigrationWarningWizard()).open();
            if(result != Dialog.OK){
                return null;
            }
        }

        final File archiveFile = new File(URI.decode(sourceFileURL.getFile())) ;
        final File barProcFile = getProcFormBar(archiveFile);

        final TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();

        final Resource resource = editingDomain.getResourceSet().createResource(URI.createFileURI(barProcFile.getAbsolutePath()));
        if(resource == null){
            throw new Exception("Failed to create an EMF resource for "+barProcFile.getName());
        }
        final Map<Object, Object> loadOptions = new HashMap<Object, Object>(editingDomain.getResourceSet().getLoadOptions());
        //Ignore unknown features
        loadOptions.put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        resource.load(loadOptions);

        if(resource.getContents().size() > 1){
            final EObject mainProcess = resource.getContents().get(0);
            Object sourceVersion = null;
            if(mainProcess instanceof AnyType){
            	sourceVersion = ((AnyType) mainProcess).getAnyAttribute().getValue(3);
            }else if( mainProcess instanceof MainProcess){
            	sourceVersion = ((MainProcess)mainProcess).getBonitaModelVersion();
            }
            if(sourceVersion == null || !sourceVersion.toString().equals(SUPPORTED_VERSION)){
                throw new IncompatibleVersionException((String) sourceVersion,SUPPORTED_VERSION);
            }
            resource.unload();
            final URI resourceURI = resource.getURI();
            final Release release = migrator.getRelease(0);
            performMigration(migrator, resourceURI, release,progressMonitor);
        }else{
            throw new Exception("Invalid source proc file");
        }

        migratedProc = barProcFile;
        return barProcFile;
    }

    private boolean displayMigrationWarningPopup() {
        final IPreferenceStore store = BarImporterPlugin.getDefault().getPreferenceStore();
        return store.getBoolean(BarImporterPreferenceConstants.DISPLAY_MIGRATION_WARNING);
    }

    private File getProcFormBar(File archiveFile) throws Exception {
        final ZipInputStream zin = new ZipInputStream(new FileInputStream(archiveFile));
        ZipEntry zipEntry = zin.getNextEntry();
        while (zipEntry != null && !zipEntry.getName().endsWith(".proc")) {
            zipEntry = zin.getNextEntry();
        }
        if (zipEntry == null) {
            throw new FileNotFoundException("can't find a .proc file in bar "+ archiveFile.getName());
        }
        String entryName = zipEntry.getName();
        if(entryName.indexOf("/") != -1){
            entryName.substring(entryName.lastIndexOf("/"));
        }
        final File tempFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), entryName) ;
        byte[] buf = new byte[1024];
        tempFile.delete();
        int len;
        FileOutputStream out = new FileOutputStream(tempFile);
        while ((len = zin.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        zin.close();
        out.close();
        return tempFile;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#getResources()
     */
    @Override
    public List<File> getResources() {
        if(migratedProc != null){
            return Collections.singletonList(migratedProc);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#getExtension()
     */
    @Override
    public String getExtension() {
        return ".bar";
    }


    private void performMigration(final BOSMigrator migrator,final URI resourceURI, final Release release,IProgressMonitor monitor) throws MigrationException {
        migrator.setLevel(ValidationLevel.RELEASE);
        migrator.migrateAndSave(
                Collections.singletonList(resourceURI), release,
                null, monitor);
    }

}
