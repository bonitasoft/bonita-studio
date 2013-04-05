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
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.data.attachment.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.importer.ToProcProcessor;
import org.bonitasoft.studio.importer.bar.BarImporterPlugin;
import org.bonitasoft.studio.importer.bar.exception.IncompatibleVersionException;
import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.migrator.BOSMigrator;
import org.bonitasoft.studio.migration.preferences.BarImporterPreferenceConstants;
import org.bonitasoft.studio.migration.ui.wizard.MigrationWarningWizard;
import org.bonitasoft.studio.validators.repository.ValidatorSourceRepositorySotre;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLOptions;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLOptionsImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.BundleClassLoader;
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

	private static final String ATTACHMENTS_FOLDER = "attachments";
	private static final Set<String> SUPPORTED_VERSIONS = new HashSet<String>() ;
	static{
		SUPPORTED_VERSIONS.add("5.9");
		SUPPORTED_VERSIONS.add("5.10");
	}
	private static final String MIGRATION_HISTORY_PATH = "models/v60/process.history";
	private static final String FORMS_LIBS = "forms/lib";
	private static final String VALIDATORS = "validators";
	private static final String LIBS = "lib";

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

		importAttachment(archiveFile,progressMonitor);
		importProcessJarDependencies(archiveFile,progressMonitor);
		importFormJarDependencies(archiveFile,progressMonitor);
		importApplicationResources(archiveFile,progressMonitor);

		final TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();

		final Resource resource =  new XMLResourceFactoryImpl().createResource(URI.createFileURI(barProcFile.getAbsolutePath()));

		if(resource == null){
			throw new Exception("Failed to create an EMF resource for "+barProcFile.getName());
		}
		final Map<Object, Object> loadOptions = new HashMap<Object, Object>(editingDomain.getResourceSet().getLoadOptions());
		//Ignore unknown features
		loadOptions.put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		XMLOptions options = new XMLOptionsImpl() ;
		options.setProcessAnyXML(true) ;
		loadOptions.put(XMLResource.OPTION_XML_OPTIONS, options);
		resource.load(loadOptions);

		if(!resource.getContents().isEmpty()){
			Object sourceVersion = null;
			EObject root = resource.getContents().get(0); 
			if(root instanceof XMLTypeDocumentRoot ){
				if(!((XMLTypeDocumentRoot) root).getMixed().isEmpty()){
					AnyType anyType = (AnyType) ((XMLTypeDocumentRoot)root).getMixed().get(0).getValue();
					if(anyType != null && anyType.getMixed().size() > 1){
						AnyType mainProc = (AnyType) anyType.getMixed().get(1).getValue();
						if(mainProc != null && mainProc.getMixed().size() > 4){
							Iterator<?> it=  mainProc.getAnyAttribute().iterator();
							while (it.hasNext()) {
								SimpleFeatureMapEntry object = (SimpleFeatureMapEntry) it.next();
								if("bonitaModelVersion".equals(object.getEStructuralFeature().getName())){
									sourceVersion = object.getValue();
								}
							}
						}
					}
				}
			}
			if(sourceVersion == null || !SUPPORTED_VERSIONS.contains(sourceVersion.toString())){
				throw new IncompatibleVersionException((String) sourceVersion,SUPPORTED_VERSIONS.toString());
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

	private void importApplicationResources(File archiveFile,IProgressMonitor progressMonitor) {
		File tmpBar = new File(ProjectUtil.getBonitaStudioWorkFolder(), "tmpBar");
		PlatformUtil.unzipZipFiles(archiveFile, tmpBar, progressMonitor);
		File resourceFile = FileUtil.findDirectory(tmpBar, "studio-templates");
		if (resourceFile != null) {
			ApplicationResourceRepositoryStore store = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
			File toCopy = null;
			for (File f : resourceFile.listFiles()) {
				String webTemplateId = f.getName();
				IRepositoryFileStore webTemplateArtifact = store.getChild(webTemplateId);
				if (webTemplateArtifact == null) {
					webTemplateArtifact = store.createRepositoryFileStore(webTemplateId);
				}

				toCopy = f;
				if(toCopy.exists()){
					PlatformUtil.copyResource(webTemplateArtifact.getResource().getLocation().toFile(), toCopy, progressMonitor);
				}
			}
			PlatformUtil.delete(tmpBar, progressMonitor);
		}
	}

	private void importFormJarDependencies(File archiveFile,IProgressMonitor monitor) throws ZipException, IOException {
		final DependencyRepositoryStore store = (DependencyRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
		final ZipFile zipfile = new ZipFile(archiveFile);
		Enumeration<?> enumEntries = zipfile.entries();
		ZipEntry zipEntry = null;
		while (enumEntries.hasMoreElements()){
			zipEntry=(ZipEntry)enumEntries.nextElement();
			File currentFile = new File (zipEntry.getName());
			if (!zipEntry.isDirectory() && zipEntry.getName().contains(FORMS_LIBS) && zipEntry.getName().endsWith(".jar") ){
				store.importInputStream(currentFile.getName(), zipfile.getInputStream(zipEntry));
			}
			if (!zipEntry.isDirectory() && zipEntry.getName().contains(VALIDATORS) && zipEntry.getName().endsWith(".jar") ){
				if(store.getChild(currentFile.getName()) == null){
					store.importInputStream(currentFile.getName(), zipfile.getInputStream(zipEntry));
				}
				importValidatorSource(currentFile,monitor);
			}
		}
		zipfile.close();
	}


	private void importValidatorSource(File currentFile,IProgressMonitor monitor) {
		final ValidatorSourceRepositorySotre validatorSourceStore = (ValidatorSourceRepositorySotre)RepositoryManager.getInstance().getRepositoryStore(ValidatorSourceRepositorySotre.class);
		// TODO Import validator sources in repository
	}

	private void importProcessJarDependencies(File archiveFile,IProgressMonitor monitor) throws ZipException, IOException {
		final DependencyRepositoryStore store = (DependencyRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
		final ZipFile zipfile = new ZipFile(archiveFile);
		Enumeration<?> enumEntries = zipfile.entries();
		ZipEntry zipEntry = null;
		while (enumEntries.hasMoreElements()){
			zipEntry=(ZipEntry)enumEntries.nextElement();
			File currentFile = new File (zipEntry.getName());
			if (!zipEntry.isDirectory() && zipEntry.getName().contains(LIBS) && zipEntry.getName().endsWith(".jar") ){
				if(store.getChild(currentFile.getName()) == null){
					store.importInputStream(currentFile.getName(), zipfile.getInputStream(zipEntry));
				}
			}
		}
		zipfile.close();
	}

	private boolean displayMigrationWarningPopup() {
		final IPreferenceStore store = MigrationPlugin.getDefault().getPreferenceStore();
		return store.getBoolean(BarImporterPreferenceConstants.DISPLAY_MIGRATION_WARNING);
	}

	private void importAttachment(File archiveFile,IProgressMonitor monitor) throws IOException{
		final DocumentRepositoryStore store = (DocumentRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(DocumentRepositoryStore.class);
		ZipFile zipfile=new ZipFile(archiveFile);
		Enumeration<?> enumEntries = zipfile.entries();
		ZipEntry zipEntry = null;
		while (enumEntries.hasMoreElements()){
			zipEntry=(ZipEntry)enumEntries.nextElement();
			File currentFile = new File (zipEntry.getName());
			if (!zipEntry.isDirectory() && zipEntry.getName().contains(ATTACHMENTS_FOLDER)){
				store.importInputStream(currentFile.getName(), zipfile.getInputStream(zipEntry));
			}

		}
		zipfile.close();
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
