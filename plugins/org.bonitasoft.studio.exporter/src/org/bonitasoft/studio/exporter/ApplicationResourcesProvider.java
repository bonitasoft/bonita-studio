/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.exporter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bonitasoft.console.common.server.preferences.properties.SecurityProperties;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.BonitaHomeUtil;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.data.attachment.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.exporter.application.FormsXMLExporter;
import org.bonitasoft.studio.exporter.application.ResourcesExporter;
import org.bonitasoft.studio.exporter.application.TemplatesExporter;
import org.bonitasoft.studio.exporter.application.service.CssGeneratorService;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Romain Bioteau
 * 
 */
public class ApplicationResourcesProvider implements BARResourcesProvider {


	protected static final String CSS_BONITA_FORM_DEFAULT = "css/bonita_form_default.css";
	private static final long TENANT_ID = 1;
	private final File tmpDir = ProjectUtil.getBonitaStudioWorkFolder();
	private final String[] urlThatWontBeReplaced = new String[] { "scripts/bonita.js", "scripts/changeCSS.js", "console.nocache.js", "pictures/favicon2.ico", "javascript:''","application.nocache.js" };
	private final Map<String,Boolean> existingResource = new HashMap<String,Boolean>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bonitasoft.studio.application.actions.barresource.BARResourcesProvider
	 * #
	 * getResourcesForProcess(org.bonitasoft.studio.model.process.AbstractProcess
	 * )
	 */
	@Override
	public List<BarResource> addResourcesForConfiguration(BusinessArchiveBuilder builder,AbstractProcess process,Configuration configuration) throws Exception {
		final List<BarResource> res = new ArrayList<BarResource>();
		addHTMLTemplates(res, process);
		addApplicationResources(res, process);
		addFormsXML(res, process);
		addApplicationDependencies(res, process,configuration);
		addAutologin(res, process) ;
		for(BarResource barResource : res){
			builder.addExternalResource(barResource) ;
		}
		return res;
	}

	protected void addApplicationDependencies(List<BarResource> res, AbstractProcess process, Configuration configuration) throws Exception {
		if(configuration != null){
			File libFile = new File(tmpDir, "lib");
			libFile.delete();
			libFile.mkdir();
			ResourcesExporter.exportJars(process, configuration,libFile,Repository.NULL_PROGRESS_MONITOR);
			if (libFile.exists()) {
				addFolder(libFile, FORMS_FOLDER_IN_BAR, res);
				PlatformUtil.delete(libFile, new NullProgressMonitor());
			}
		}
	}

	protected void addHTMLTemplates(List<BarResource> res, AbstractProcess process) throws Exception {
		File templateDir = new File(tmpDir, "templates");
		templateDir.delete();
		templateDir.mkdir();
		TemplatesExporter.exportTemplates(process, templateDir, Repository.NULL_PROGRESS_MONITOR);
		ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
		if (templateDir.exists()) {
			File htmlDir = findDirectory(templateDir, "html");
			if (htmlDir != null) {
				AbstractProcess targetProcess = process;
				if(process instanceof SubProcessEvent){
					targetProcess = ModelHelper.getParentProcess(process);
				}
				String id = ModelHelper.getEObjectID(targetProcess) ;
				ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(id);
				if (artifact != null) {
					replaceUrl(htmlDir, artifact.getResourcesApplicationFolder().getFolder("application").getLocation().toFile().toURI(), "application/", process.getName(),process.getVersion()) ;
				}
				addFolder(htmlDir, FORMS_FOLDER_IN_BAR, res);
				PlatformUtil.delete(templateDir, Repository.NULL_PROGRESS_MONITOR);
			}
		}
	}

	protected File findDirectory(File root, String dirName) {
		File founded = null;
		if (root.getName().equals(dirName)) {
			return root;
		} else if (root.isDirectory()) {
			for (File f : root.listFiles()) {
				if (founded == null) {
					founded = findDirectory(f, dirName);
				} else {
					break;
				}
			}
		}
		return founded;

	}

	protected void addApplicationResources(List<BarResource> res, AbstractProcess process) throws Exception {
		File resourceFile = new File(tmpDir, "resources");
		resourceFile.delete();
		if(process instanceof SubProcessEvent){
			process = ModelHelper.getParentProcess(process);
		}
		ResourcesExporter.exportResources(process, resourceFile, new NullProgressMonitor());
		exportFileWidgetResource(process,res);
		CssGeneratorService.getInstance().getCssGenerator().addCssToWar(process, resourceFile, new NullProgressMonitor());
		if (resourceFile.exists()) {
			ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
			String processUUID = ModelHelper.getEObjectID(process) ;
			ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
			if(artifact != null){
				replaceUrl(resourceFile,artifact.getResourcesApplicationFolder().getLocation().toFile().toURI(), "", process.getName(),process.getVersion());
			}
			addFolder(resourceFile, FORMS_FOLDER_IN_BAR, res);
			PlatformUtil.delete(resourceFile, Repository.NULL_PROGRESS_MONITOR);
		} else {
			throw new Exception("resources in BAR doesn't export correctly");
		}
	}

	private void exportFileWidgetResource(AbstractProcess process, List<BarResource> res) throws Exception {
		List<FileWidget> fileWidgets = ModelHelper.getAllItemsOfType(process, FormPackage.Literals.FILE_WIDGET);
		if(!fileWidgets.isEmpty()){
			final File documentFolder = new File(tmpDir, "documents");
			documentFolder.delete();
			documentFolder.mkdirs();
			final DocumentRepositoryStore store = (DocumentRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DocumentRepositoryStore.class);
			for(FileWidget w : fileWidgets){
				if(w.getInputType() == FileWidgetInputType.RESOURCE){
					final String initialResourcePath = w.getInitialResourcePath();
					if(initialResourcePath != null){
						final IRepositoryFileStore fileStore = store.getChild(initialResourcePath);
						if(fileStore != null){
							File f = fileStore.getResource().getLocation().toFile();
							final File target = new File(documentFolder, f.getName());
							if(!target.exists()){
								FileUtil.copy(f, target);
							}
						}
					}
				}
			}
			addFolder(documentFolder, FORMS_FOLDER_IN_BAR, res);
		}

	}

	private void replaceUrl(File file, URI baseURI, String baseFolder, String processName, String processVersion) throws Exception {
		existingResource.clear();
		replaceUrl( file, baseURI, baseFolder, true, processName, processVersion, System.currentTimeMillis());
	}
	private void replaceUrl(File file, URI baseURI, String baseFolder, boolean firstCall, String processName, String processVersion, long timestamp) throws Exception {
		if (file.isDirectory()) {
			String baseFolder2;
			if(firstCall){
				baseFolder2 = baseFolder;
			}else{
				baseFolder2 = baseFolder + file.getName() + "/";
			}

			for (File f : file.listFiles()) {
				replaceUrl(f, f.isDirectory()?baseURI.resolve(f.getName()+'/'):baseURI, baseFolder2,false, processName, processVersion, timestamp);
			}
		} else if (file.getName().endsWith(".html") || file.getName().endsWith(".htm") || file.getName().endsWith(".xhtml") || file.getName().endsWith(".css")
				|| file.getName().endsWith(".jsp")) {
			FileInputStream fis = new FileInputStream(file);
			String content = PlatformUtil.getFileContent(fis);
			fis.close();
			String newContent = new String(content);
			String regex = "(src=\")([^\"]*)(\")|(href=\")([^\"]*)(\")|(url\\(')\"{0,1}([^'\\)\"]*)\"{0,1}('\\))|(url\\()\"{0,1}([^\\)\"]*)\"{0,1}(\\))|(background=\")([^\"]*)(\")";
			Pattern pattern = Pattern.compile(regex.toString());
			Matcher matcher = pattern.matcher(newContent);
			StringBuffer stringBuffer = new StringBuffer();
			while(matcher.find()) {
				int i;
				if(matcher.group(1)!= null){
					i = 0;
				}else if(matcher.group(4)!= null){
					i = 3;
				}else if(matcher.group(7)!= null){
					i = 6;
				}else if(matcher.group(10)!= null){
					i = 9;
				}else{
					i = 12;
				}
				String url = matcher.group(2+i);
				if(i==0 && "application.nocache.js".equals(url)){//link src="application.nocache.js" should be replace by "console.nocache.js" for all in bar mode
					String replacement = matcher.group(1+i) + "console.nocache.js" + matcher.group(3+i);
					matcher.appendReplacement(stringBuffer, replacement);
				}else if (shouldReplaceUrl(url,baseURI)) {
					String replacement = matcher.group(1+i) + ExporterTools.toApplicationResourceURL(baseFolder+url, processName, processVersion, timestamp) + matcher.group(3+i);
					matcher.appendReplacement(stringBuffer, replacement);
				}
			}
			matcher.appendTail(stringBuffer);
			newContent = stringBuffer.toString();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(newContent.getBytes(Charset.forName("UTF-8")));
			fos.flush();
			fos.close();

		}
	}

	private boolean shouldReplaceUrl(String url, URI baseURI) {
		boolean shouldReplace = !(url.startsWith("http://") || url.startsWith("https://") || url.startsWith("file://") || url.startsWith("ftp://"));
		if(shouldReplace){
			for (int i = 0; i < urlThatWontBeReplaced.length && shouldReplace; i++) {
				shouldReplace = !urlThatWontBeReplaced[i].equals(url);
			}
		}
		if(url.equals("css/generatedcss.css")){
			return true;
		}
		if(shouldReplace){
			if(!existingResource.containsKey(url)){//the resource exists (already checked it)
				try{
					URI resource = baseURI.resolve(url);//resolve the uri
					File file = new File(resource);
					shouldReplace = file.exists();
					existingResource.put(url,shouldReplace);
				} catch (IllegalArgumentException e ) {
					existingResource.put(url,false);
					return false;
				}
			}else{
				return existingResource.get(url);
			}
		}
		return shouldReplace;
	}

	protected void addFolder(File resourceFile, String currentPathInBar, List<BarResource> res) throws Exception {
		if (resourceFile.getName().startsWith(".")) {
			return; // remove .svn folders and more
		}
		if (resourceFile.isDirectory()) {
			for (File f : resourceFile.listFiles()) {
				addFolder(f, currentPathInBar + resourceFile.getName() + "/", res);
			}
		} else {
			FileInputStream is = new FileInputStream(resourceFile);
			byte[] fileBytes = new byte[(int) resourceFile.length()];
			is.read(fileBytes);
			is.close();
			res.add(new BarResource(currentPathInBar + resourceFile.getName(), fileBytes));
		}
	}

	protected void addFormsXML(List<BarResource> res, AbstractProcess process ) throws Exception {
		File formsXmlFile = new File(tmpDir, "forms.xml");
		formsXmlFile.delete();
		FormsXMLExporter.exportFormsXML(process, tmpDir, true, Repository.NULL_PROGRESS_MONITOR);
		if (formsXmlFile.exists()) {
			FileInputStream is = new FileInputStream(formsXmlFile);
			byte[] fileBytes = new byte[(int) formsXmlFile.length()];
			is.read(fileBytes);
			is.close();
			res.add(new BarResource(FORMS_FOLDER_IN_BAR + formsXmlFile.getName(), fileBytes));
			formsXmlFile.delete();
		} else {
			throw new Exception("forms.xml doesn't export correctly");
		}
	}

	protected void addAutologin(List<BarResource> res, AbstractProcess process) throws Exception {
		if(process.isAutoLogin()){
			File securityConfig = BonitaHomeUtil.getDefaultTenantSecurityConfigFile(TENANT_ID);
			if (securityConfig.exists()) {
				Properties properties = new Properties();
				FileInputStream is = new FileInputStream(securityConfig);
				properties.load(is) ;
				properties.setProperty(SecurityProperties.AUTO_LOGIN_PROPERTY, Boolean.TRUE.toString());
				String autoLoginId =process.getAutoLoginId() ;
				if (autoLoginId != null && !autoLoginId.isEmpty()) {
					properties.setProperty(SecurityProperties.AUTO_LOGIN_USERNAME_PROPERTY, autoLoginId);
				}
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				properties.store(os, null);
				res.add(new BarResource(FORMS_FOLDER_IN_BAR + securityConfig.getName(), os.toByteArray()));
				is.close() ;
				os.close() ;
			}
		}
	}

}
