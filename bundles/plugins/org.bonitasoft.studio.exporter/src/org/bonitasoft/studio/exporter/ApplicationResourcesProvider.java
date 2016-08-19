/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.exporter.application.FormsXMLExporter;
import org.bonitasoft.studio.exporter.application.ResourcesExporter;
import org.bonitasoft.studio.exporter.application.TemplatesExporter;
import org.bonitasoft.studio.exporter.application.service.CssGeneratorService;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Strings;

/**
 * @author Romain Bioteau
 */
public class ApplicationResourcesProvider implements BARResourcesProvider {

    public static final String AUTO_LOGIN_PROPERTY = "forms.application.login.auto";
    public static final String AUTO_LOGIN_USERNAME_PROPERTY = "forms.application.login.auto.username";
    public static final String AUTO_LOGIN_PASSWORD_PROPERTY = "forms.application.login.auto.password";

    protected static final String CSS_BONITA_FORM_DEFAULT = "css/bonita_form_default.css";
    private final File tmpDir = ProjectUtil.getBonitaStudioWorkFolder();
    private final String[] urlThatWontBeReplaced = new String[] { "scripts/bonita.js", "scripts/changeCSS.js", "console.nocache.js", "pictures/favicon2.ico",
            "javascript:''", "application.nocache.js" };
    private final Map<String, Boolean> existingResource = new HashMap<String, Boolean>();

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.application.actions.barresource.BARResourcesProvider
     * #
     * getResourcesForProcess(org.bonitasoft.studio.model.process.AbstractProcess
     * )
     */
    @Override
    public void addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process, final Configuration configuration,
            final Set<EObject> excludedObject) throws Exception {
        final List<BarResource> res = new ArrayList<BarResource>();
        addHTMLTemplates(res, process);
        addApplicationResources(res, process);
        addFormsXML(res, process, excludedObject);
        addApplicationDependencies(res, process, configuration);
        addAutologin(res, process, configuration);
        for (final BarResource barResource : res) {
            builder.addExternalResource(barResource);
        }
    }

    protected void addApplicationDependencies(final List<BarResource> res, final AbstractProcess process, final Configuration configuration) throws Exception {
        if (configuration != null) {
            final File libFile = new File(tmpDir, "lib");
            libFile.delete();
            libFile.mkdir();
            ResourcesExporter.exportJars(process, configuration, libFile, Repository.NULL_PROGRESS_MONITOR);
            if (libFile.exists()) {
                addFolder(libFile, FORMS_FOLDER_IN_BAR, res);
                PlatformUtil.delete(libFile, new NullProgressMonitor());
            }
        }
    }

    protected void addHTMLTemplates(final List<BarResource> res, final AbstractProcess process) throws Exception {
        final File templateDir = new File(tmpDir, "templates");
        templateDir.delete();
        templateDir.mkdir();
        TemplatesExporter.exportTemplates(process, templateDir, Repository.NULL_PROGRESS_MONITOR);
        final ApplicationResourceRepositoryStore resourceStore = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
        if (templateDir.exists()) {
            final File htmlDir = findDirectory(templateDir, "html");
            if (htmlDir != null) {
                AbstractProcess targetProcess = process;
                if (process instanceof SubProcessEvent) {
                    targetProcess = ModelHelper.getParentProcess(process);
                }
                final String id = ModelHelper.getEObjectID(targetProcess);
                final ApplicationResourceFileStore artifact = resourceStore.getChild(id);
                if (artifact != null) {
                    replaceUrl(htmlDir, artifact.getResourcesApplicationFolder().getFolder("application").getLocation().toFile().toURI(), "application/",
                            process.getName(), process.getVersion());
                }
                addFolder(htmlDir, FORMS_FOLDER_IN_BAR, res);
                PlatformUtil.delete(templateDir, Repository.NULL_PROGRESS_MONITOR);
            }
        }
    }

    protected File findDirectory(final File root, final String dirName) {
        File founded = null;
        if (root.getName().equals(dirName)) {
            return root;
        } else if (root.isDirectory()) {
            for (final File f : root.listFiles()) {
                if (founded == null) {
                    founded = findDirectory(f, dirName);
                } else {
                    break;
                }
            }
        }
        return founded;

    }

    protected void addApplicationResources(final List<BarResource> res, AbstractProcess process) throws Exception {
        final File resourceFile = new File(tmpDir, "resources");
        resourceFile.delete();
        if (process instanceof SubProcessEvent) {
            process = ModelHelper.getParentProcess(process);
        }
        ResourcesExporter.exportResources(process, resourceFile, new NullProgressMonitor());
        exportFileWidgetResource(process, res);
        CssGeneratorService.getInstance().getCssGenerator().addCssToWar(process, resourceFile, new NullProgressMonitor());
        if (resourceFile.exists()) {
            final ApplicationResourceRepositoryStore resourceStore = RepositoryManager.getInstance().getRepositoryStore(
                    ApplicationResourceRepositoryStore.class);
            final String processUUID = ModelHelper.getEObjectID(process);
            final ApplicationResourceFileStore artifact = resourceStore.getChild(processUUID);
            if (artifact != null) {
                replaceUrl(resourceFile, artifact.getResourcesApplicationFolder().getLocation().toFile().toURI(), "", process.getName(), process.getVersion());
            }
            addFolder(resourceFile, FORMS_FOLDER_IN_BAR, res);
            PlatformUtil.delete(resourceFile, Repository.NULL_PROGRESS_MONITOR);
        } else {
            throw new Exception("resources in BAR doesn't export correctly");
        }
    }

    private void exportFileWidgetResource(final AbstractProcess process, final List<BarResource> res) throws Exception {
        final List<FileWidget> fileWidgets = ModelHelper.getAllItemsOfType(process, FormPackage.Literals.FILE_WIDGET);
        if (!fileWidgets.isEmpty()) {
            final File documentFolder = new File(tmpDir, "documents");
            documentFolder.delete();
            documentFolder.mkdirs();
            final DocumentRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DocumentRepositoryStore.class);
            for (final FileWidget w : fileWidgets) {
                if (w.getInputType() == FileWidgetInputType.RESOURCE) {
                    final String initialResourcePath = w.getInitialResourcePath();
                    if (initialResourcePath != null) {
                        final IRepositoryFileStore fileStore = store.getChild(initialResourcePath);
                        if (fileStore != null) {
                            final File f = fileStore.getResource().getLocation().toFile();
                            final File target = new File(documentFolder, f.getName());
                            if (!target.exists()) {
                                FileUtil.copy(f, target);
                            }
                        }
                    }
                }
            }
            addFolder(documentFolder, FORMS_FOLDER_IN_BAR, res);
        }

    }

    private void replaceUrl(final File file, final URI baseURI, final String baseFolder, final String processName, final String processVersion)
            throws Exception {
        existingResource.clear();
        replaceUrl(file, baseURI, baseFolder, true, processName, processVersion, System.currentTimeMillis());
    }

    private void replaceUrl(final File file, final URI baseURI, final String baseFolder, final boolean firstCall, final String processName,
            final String processVersion, final long timestamp) throws Exception {
        if (file.isDirectory()) {
            String baseFolder2;
            if (firstCall) {
                baseFolder2 = baseFolder;
            } else {
                baseFolder2 = baseFolder + URLEncoder.encode(file.getName(), "UTF-8") + "/";
            }

            for (final File f : file.listFiles()) {
                replaceUrl(f, f.isDirectory() ? baseURI.resolve(URLEncoder.encode(f.getName(), "UTF-8") + '/') : baseURI, baseFolder2, false, processName,
                        processVersion, timestamp);
            }
        } else if (file.getName().endsWith(".html") || file.getName().endsWith(".htm") || file.getName().endsWith(".xhtml") || file.getName().endsWith(".css")
                || file.getName().endsWith(".jsp")) {
            final FileInputStream fis = new FileInputStream(file);
            final String content = PlatformUtil.getFileContent(fis);
            fis.close();
            String newContent = new String(content);
            final String regex = "(src=\")([^\"]*)(\")|(href=\")([^\"]*)(\")|(url\\(')\"{0,1}([^'\\)\"]*)\"{0,1}('\\))|(url\\()\"{0,1}([^\\)\"]*)\"{0,1}(\\))|(background=\")([^\"]*)(\")";
            final Pattern pattern = Pattern.compile(regex.toString());
            final Matcher matcher = pattern.matcher(newContent);
            final StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                int i;
                if (matcher.group(1) != null) {
                    i = 0;
                } else if (matcher.group(4) != null) {
                    i = 3;
                } else if (matcher.group(7) != null) {
                    i = 6;
                } else if (matcher.group(10) != null) {
                    i = 9;
                } else {
                    i = 12;
                }
                final String url = matcher.group(2 + i);
                if (i == 0 && "application.nocache.js".equals(url)) {//link src="application.nocache.js" should be replace by "console.nocache.js" for all in bar mode
                    final String replacement = matcher.group(1 + i) + "console.nocache.js" + matcher.group(3 + i);
                    matcher.appendReplacement(stringBuffer, replacement);
                } else if (shouldReplaceUrl(url, baseURI)) {
                    final String replacement = matcher.group(1 + i)
                            + ExporterTools.toApplicationResourceURL(baseFolder + url, processName, processVersion, timestamp) + matcher.group(3 + i);
                    matcher.appendReplacement(stringBuffer, replacement);
                }
            }
            matcher.appendTail(stringBuffer);
            newContent = stringBuffer.toString();
            final FileOutputStream fos = new FileOutputStream(file);
            fos.write(newContent.getBytes(Charset.forName("UTF-8")));
            fos.flush();
            fos.close();

        }
    }

    private boolean shouldReplaceUrl(final String url, final URI baseURI) {
        boolean shouldReplace = !(url.startsWith("http://") || url.startsWith("https://") || url.startsWith("file://") || url.startsWith("ftp://"));
        if (shouldReplace) {
            for (int i = 0; i < urlThatWontBeReplaced.length && shouldReplace; i++) {
                shouldReplace = !urlThatWontBeReplaced[i].equals(url);
            }
        }
        if (url.equals("css/generatedcss.css")) {
            return true;
        }
        if (shouldReplace) {
            if (!existingResource.containsKey(url)) {//the resource exists (already checked it)
                try {
                    final URI resource = baseURI.resolve(url);//resolve the uri
                    final File file = new File(resource);
                    shouldReplace = file.exists();
                    existingResource.put(url, shouldReplace);
                } catch (final IllegalArgumentException e) {
                    existingResource.put(url, false);
                    return false;
                }
            } else {
                return existingResource.get(url);
            }
        }
        return shouldReplace;
    }

    protected void addFolder(final File resourceFile, final String currentPathInBar, final List<BarResource> res) throws Exception {
        if (resourceFile.getName().startsWith(".")) {
            return; // remove .svn folders and more
        }
        if (resourceFile.isDirectory()) {
            for (final File f : resourceFile.listFiles()) {
                addFolder(f, currentPathInBar + resourceFile.getName() + "/", res);
            }
        } else {
            final FileInputStream is = new FileInputStream(resourceFile);
            final byte[] fileBytes = new byte[(int) resourceFile.length()];
            is.read(fileBytes);
            is.close();
            res.add(new BarResource(currentPathInBar + resourceFile.getName(), fileBytes));
        }
    }

    protected void addFormsXML(final List<BarResource> res, final AbstractProcess process, final Set<EObject> excludedObject) throws Exception {
        final File formsXmlFile = new File(tmpDir, "forms.xml");
        formsXmlFile.delete();
        FormsXMLExporter.exportFormsXML(process, tmpDir, true, excludedObject, Repository.NULL_PROGRESS_MONITOR);
        if (formsXmlFile.exists()) {
            final FileInputStream is = new FileInputStream(formsXmlFile);
            final byte[] fileBytes = new byte[(int) formsXmlFile.length()];
            is.read(fileBytes);
            is.close();
            res.add(new BarResource(FORMS_FOLDER_IN_BAR + formsXmlFile.getName(), fileBytes));
            formsXmlFile.delete();
        } else {
            throw new Exception("forms.xml doesn't export correctly");
        }
    }

    protected void addAutologin(final List<BarResource> res, final AbstractProcess process, final Configuration conf) throws Exception {
        if (process.isAutoLogin() && process.getFormMapping().getType() == FormMappingType.LEGACY) {
            final Properties properties = new Properties();
            properties.setProperty(AUTO_LOGIN_PROPERTY, Boolean.TRUE.toString());
            final String autoLoginUserName = conf.getAnonymousUserName();
            if(Strings.isNullOrEmpty( autoLoginUserName)){
                throw new Exception(String.format("Username for autologin is not defined for process configuration '%s'",conf.getName()));
            }
            final String autoLoginPassword = conf.getAnonymousPassword();
            properties.setProperty(AUTO_LOGIN_USERNAME_PROPERTY, autoLoginUserName);
            properties.setProperty(AUTO_LOGIN_PASSWORD_PROPERTY, autoLoginPassword != null && !autoLoginPassword.isEmpty() ? autoLoginPassword : "");
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            properties.store(os, null);
            res.add(new BarResource(FORMS_FOLDER_IN_BAR + BOSEngineManager.SECURITY_CONFIG_PROPERTIES, os.toByteArray()));
            os.close();
        }
    }

}
