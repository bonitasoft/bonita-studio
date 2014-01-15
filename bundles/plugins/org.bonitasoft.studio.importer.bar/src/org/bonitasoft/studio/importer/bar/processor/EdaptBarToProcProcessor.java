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
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.bonitasoft.studio.common.ConfigurationIdProvider;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.data.attachment.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bar.BarImporterPlugin;
import org.bonitasoft.studio.importer.bar.custom.migration.connector.mapper.ConnectorDescriptorToConnectorDefinition;
import org.bonitasoft.studio.importer.bar.exception.IncompatibleVersionException;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.migrator.BOSMigrator;
import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.model.report.MigrationReportFactory;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.migration.preferences.BarImporterPreferenceConstants;
import org.bonitasoft.studio.migration.ui.wizard.MigrationWarningWizard;
import org.bonitasoft.studio.migration.utils.DeadlineMigrationStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.BundleClassLoader;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.ValidationLevel;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.ow2.bonita.connector.core.Connector;
import org.ow2.bonita.connector.core.ConnectorDescription;


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
	private static final String LIBS = "libs/";
	private static final String CONNECTORS = "connectors/";
	private List<Change> additionalChanges = new ArrayList<Change>();
	private File migratedProc;
	private BOSMigrator migrator;
	private List<String> connectorsJars = new ArrayList<String>();
	private List<File> toDelete = new ArrayList<File>();
	private boolean continueImport = true;

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
			Display.getDefault().syncExec(new Runnable() {

				@Override
				public void run() {
					final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), new MigrationWarningWizard());
					int result =  dialog.open();
					if(result != Dialog.OK){
						continueImport = false;
					}
				}
			});

		}
		if(!continueImport){
			return null;
		}
		final File archiveFile = new File(URI.decode(sourceFileURL.getFile())) ;
		final File barProcFile = getProcFormBar(archiveFile);

		importAttachment(archiveFile,progressMonitor);
		importCustomConnectors(archiveFile,progressMonitor);
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
			performMigration(migrator, resourceURI, release,progressMonitor);//Migrate from 5.9 to 6.0-Alpha

			//Migrate from 6.0-Alpha to current release
			DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
			String nsURI = ReleaseUtils.getNamespaceURI(resourceURI);
			Migrator nextMigrator = store.getMigrator(nsURI);
			nextMigrator.setLevel(ValidationLevel.RELEASE);
			nextMigrator.migrateAndSave(
					Collections.singletonList(resourceURI),getAlphaRelease(nextMigrator),
					null, Repository.NULL_PROGRESS_MONITOR);
			addMigrationReport(migrator,resourceURI,(String) sourceVersion,progressMonitor);
			DeadlineMigrationStore.clearDeadlines();
		}else{
			throw new Exception("Invalid source proc file");
		}

		migratedProc = barProcFile;
		return barProcFile;
	}


	private void importCustomConnectors(File archiveFile,IProgressMonitor progressMonitor) throws Exception {
		connectorsJars.clear();
		final ZipFile zipfile = new ZipFile(archiveFile);
		Enumeration<?> enumEntries = zipfile.entries();
		ZipEntry zipEntry = null;
		while (enumEntries.hasMoreElements()){
			zipEntry=(ZipEntry)enumEntries.nextElement();
			File currentFile = new File (zipEntry.getName());
			if (!zipEntry.isDirectory() && (zipEntry.getName().startsWith(CONNECTORS) || zipEntry.getName().startsWith(LIBS)) && zipEntry.getName().endsWith(".jar") ){
				proceedCustomConnector(currentFile.getName(),zipfile.getInputStream(zipEntry),archiveFile);
			}
		}
		zipfile.close();
	}

	private void proceedCustomConnector(String fileName,InputStream inputStream, File archiveFile) throws Exception {
		final File tmpConnectorJarFile = new File(ProjectUtil.getBonitaStudioWorkFolder(),fileName);
		if(tmpConnectorJarFile.exists()){
			tmpConnectorJarFile.delete();
		}
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(tmpConnectorJarFile);
			FileUtil.copy(inputStream, fos);
		}finally{
			if(fos != null){
				fos.close();
			}
		}
		final URLClassLoader customURLClassLoader = createBarClassloader(archiveFile,tmpConnectorJarFile);
		String connectorClassname = findCustomConnectorClassName(tmpConnectorJarFile);
		if(connectorClassname != null){
			connectorsJars.add(tmpConnectorJarFile.getName());
			Class<? extends Connector> instanceClass = (Class<? extends Connector>) customURLClassLoader.loadClass(connectorClassname);
			try{
				final ConnectorDescription descriptor = new ConnectorDescription(instanceClass,Locale.ENGLISH);
				final ConnectorDescriptorToConnectorDefinition cdtocd = new ConnectorDescriptorToConnectorDefinition(descriptor,tmpConnectorJarFile);
				cdtocd.importConnectorDefinitionResources();
				cdtocd.createConnectorDefinition();
				cdtocd.createConnectorImplementation();
				additionalChanges.add(cdtocd.createReportChange());
			}catch(NoClassDefFoundError e){
				additionalChanges.add(createImportFailureReport(connectorClassname,e));
			}
		}
		if(!toDelete.isEmpty()){
			for(File f : toDelete){
				f.delete();
			}
		}

	}

	/***
	 * Create an URLClassloader with all jar inside the archive file
	 * @param archiveFile
	 * @param tmpConnectorJarFile 
	 * @return
	 * @throws MalformedURLException
	 * @throws ZipException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	protected URLClassLoader createBarClassloader(File archiveFile, File tmpConnectorJarFile) throws MalformedURLException,
	ZipException, IOException, FileNotFoundException {
		List<URL> urls = new ArrayList<URL>();
		urls.add(tmpConnectorJarFile.toURI().toURL());
		Enumeration<URL> urlEnum = BarImporterPlugin.getDefault().getBundle().findEntries("lib/", "*.jar", true);
		while (urlEnum.hasMoreElements()) {
			URL type = (URL) urlEnum.nextElement();
			urls.add(type);
		}
		final ZipFile zipfile = new ZipFile(archiveFile);
		Enumeration<?> enumEntries = zipfile.entries();
		ZipEntry zipEntry = null;
		while (enumEntries.hasMoreElements()){
			zipEntry=(ZipEntry)enumEntries.nextElement();
			if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".jar") ){
				InputStream is = zipfile.getInputStream(zipEntry);
				File tmpJar = new File(ProjectUtil.getBonitaStudioWorkFolder(),System.currentTimeMillis()+".jar");
				tmpJar.createNewFile();
				FileOutputStream os = new FileOutputStream(tmpJar);
				FileUtil.copy(is,os);
				is.close();
				os.close();
				urls.add(tmpJar.toURI().toURL());
				toDelete.add(tmpJar);
			}
		}
		zipfile.close();

		final URLClassLoader customURLClassLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]),getClass().getClassLoader());
		return customURLClassLoader;
	}

	private Change createImportFailureReport(String connectorClassname,Throwable e) {
		Change change = MigrationReportFactory.eINSTANCE.createChange();
		change.setElementName(connectorClassname);
		change.setElementType(Messages.customConnector);
		change.setElementUUID("");
		change.setDescription(Messages.bind(Messages.customConnectorMigrationFailureDescription,e.getMessage()));
		change.setPropertyName(Messages.development);
		change.setStatus(IStatus.ERROR);
		return change;
	}

	private String findCustomConnectorClassName(File archiveFile) throws ZipException, IOException {
		final ZipFile zipfile = new ZipFile(archiveFile);
		Enumeration<?> enumEntries = zipfile.entries();
		ZipEntry zipEntry = null;
		String className = null;
		String startWith = null;
		while (enumEntries.hasMoreElements()){
			zipEntry=(ZipEntry)enumEntries.nextElement();
			if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".class") ){
				startWith =	zipEntry.toString().replace(".class","");
				className = zipEntry.toString().replace("/", ".").replace(".class","");
				Enumeration<? extends ZipEntry> newEntries = zipfile.entries();
				while (newEntries.hasMoreElements()){
					ZipEntry newEntry = (ZipEntry)newEntries.nextElement();
					if (!newEntry.isDirectory() && newEntry.toString().endsWith(startWith+".properties") ){
						return className;
					}
				}
			}
		}

		return null;
	}

	private Release getAlphaRelease(Migrator nextMigrator) {
		for(Release r : nextMigrator.getReleases()){
			if(r.getLabel().equals(ProductVersion.VERSION_6_0_0_ALPHA)){
				return r;
			}
		}
		return null;
	}

	/**
	 * @throws MigrationException
	 */
	protected void addMigrationReport(final BOSMigrator migrator,URI resourceURI,final String sourceRelease,IProgressMonitor monitor) throws MigrationException {
		final TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		final Resource resource = editingDomain.getResourceSet().createResource(resourceURI);
		try {
			resource.load(Collections.EMPTY_MAP);
			AbstractEMFOperation emfOperation = new AbstractEMFOperation(editingDomain, "Update report") {



				@Override
				protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {
					final Report report = migrator.getReport();
					String diagramName = "";
					for(EObject root : resource.getContents()){
						if(root instanceof MainProcess){
							diagramName = ((MainProcess) root).getName()+"--"+((MainProcess) root).getVersion();
							((MainProcess) root).setBonitaModelVersion(ModelVersion.CURRENT_VERSION);
							((MainProcess) root).setBonitaVersion(ProductVersion.CURRENT_VERSION);
							((MainProcess) root).setConfigId(ConfigurationIdProvider.getConfigurationIdProvider().getConfigurationId((MainProcess) root));
						}
					}
					report.setName(Messages.bind(Messages.migrationReportOf,diagramName));
					report.setSourceRelease(sourceRelease);
					report.setTargetRelease(ProductVersion.CURRENT_VERSION);
					report.getChanges().addAll(additionalChanges);
					additionalChanges.clear();
					resource.getContents().add(report);
					return Status.OK_STATUS;
				}
			};
			IOperationHistory history = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
			try {
				history.execute(emfOperation, monitor, null);
			} catch (ExecutionException e) {
				BonitaStudioLog.error(e);
			}
			resource.save(Collections.emptyMap());
			resource.unload();
			editingDomain.dispose();
		} catch (IOException e) {
			throw new MigrationException("Model could not be loaded", e);
		}
	}

	protected Change addReportChange(String elementName,String elementType,String elementUUID,String description,String propertyName, int status){
		Change change = MigrationReportFactory.eINSTANCE.createChange();
		change.setElementName(elementName);
		change.setElementType(elementType);
		change.setElementUUID(elementUUID);
		change.setDescription(description);
		change.setPropertyName(propertyName);
		change.setStatus(status);
		additionalChanges.add(change);
		return change;
	}

	private void importApplicationResources(File archiveFile,IProgressMonitor progressMonitor) {
		File tmpBar = new File(ProjectUtil.getBonitaStudioWorkFolder(), "tmpBar");
		try{
			PlatformUtil.unzipZipFiles(archiveFile, tmpBar, progressMonitor);
		}catch(Exception e){
			BonitaStudioLog.error(e);
		}
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
				if(!connectorsJars.contains(currentFile.getName()) && store.getChild(currentFile.getName()) == null){
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
		ZipInputStream zin = null;
		FileOutputStream out = null;
		try{
			zin = new ZipInputStream(new FileInputStream(archiveFile));
			ZipEntry zipEntry = zin.getNextEntry();
			while (zipEntry != null && !zipEntry.getName().endsWith(".proc")) {
				zipEntry = zin.getNextEntry();
			}
			if (zipEntry == null) {
				throw new FileNotFoundException(Messages.bind(Messages.invalidArchiveStructure, archiveFile.getName()));
			}
			String entryName = zipEntry.getName();
			if(entryName.indexOf("/") != -1){
				entryName.substring(entryName.lastIndexOf("/"));
			}
			final File tempFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), entryName) ;
			byte[] buf = new byte[1024];
			tempFile.delete();
			int len;
			out = new FileOutputStream(tempFile);
			while ((len = zin.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			return tempFile;
		}finally{
			if(zin != null){
				zin.close();
			}
			if(out != null){
				out.close();
			}
		}

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
