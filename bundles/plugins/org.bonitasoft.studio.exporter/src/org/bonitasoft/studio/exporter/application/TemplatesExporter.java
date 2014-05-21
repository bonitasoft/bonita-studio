/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.exporter.ExporterTools.TemplateType;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.exporter.ExporterService;
import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.RecapFlow;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

public class TemplatesExporter {
    private static final String TMP_DIR = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();

    /**
     * 
     * export and add process templates to the war file
     * 
     * WARNING: must be done before generating forms.xml, because when templates
     * do not exists they are created and must be put in the forms.xml
     * 
     * @param process
     *            from which resources are exported
     * @param warFile
     *            were to put resources
     * @param monitor
     * @return true if all files are exported
     * @throws CoreException
     */
    public static boolean exportTemplates(AbstractProcess process, File destFolderFile, IProgressMonitor monitor) throws CoreException {
        ArrayList<File> toAdd = new ArrayList<File>();
        List<EObject> toGenerate = new ArrayList<EObject>();
        boolean succes = true;

        try {

            addTemplates(process, toAdd, toGenerate);

            File[] files = new File[toAdd.size()];
            for (int i = 0; i < toAdd.size(); i++) {
                files[i] = toAdd.get(i);

            }
            File destFile = new File(destFolderFile.getAbsolutePath() + File.separatorChar
                    + "WEB-INF" + File.separatorChar + "classes" + File.separatorChar + "html"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            if (toGenerate.size() > 0) {
                List<String> paths = ((HtmlTemplateGenerator) ExporterService.getInstance().getExporterService(SERVICE_TYPE.HtmlTemplateGenerator))
                        .createXhtmlTemplate(toGenerate, false,null,false);
                for (String path : paths) {
                    File f = new File(path);
                    if (!destFile.exists()) {
                        destFile.mkdirs();
                    }
                    PlatformUtil.copyResource(destFile, f, monitor);
                    PlatformUtil.delete(f, monitor);
                }
            }
            for (File f : toAdd) {
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                PlatformUtil.copyResource(destFile, f, monitor);
            }

            for (File f : toAdd) {
                PlatformUtil.delete(f, monitor);
            }
        } catch (IOException e) {
            succes = false;
            BonitaStudioLog.error(e);
        }

        return succes;
    }

    private static void addTemplates(Element element, ArrayList<File> toAdd, List<EObject> toGenerate) throws IOException {
        File temp;
        if (element instanceof PageFlow) {
            PageFlow pageFlow = (PageFlow) element;
            for (Form form : pageFlow.getForm()) {
                addTemplates(form, toAdd, toGenerate);
            }
            if (pageFlow.getConfirmationTemplate() != null && pageFlow.getConfirmationTemplate().getPath() != null) {
                temp = addTemplate(pageFlow, TemplateType.CONFIRMATION, toGenerate);
                if (temp != null && !toAdd.contains(temp)) {
                    toAdd.add(temp);
                }
            }

        }
        if(element instanceof ViewPageFlow){
            for (Form form : ((ViewPageFlow)element).getViewForm()) {
                addTemplates(form, toAdd, toGenerate);
            }
        }
        if (element instanceof RecapFlow) {
            RecapFlow recapFlow = (RecapFlow) element;
            for (Form form : recapFlow.getRecapForms()) {
                addTemplates(form, toAdd, toGenerate);
            }
        }
        if (element instanceof Form) {
            temp = addTemplate(element, TemplateType.PAGE, toGenerate);
            if (temp != null && !toAdd.contains(temp)) {
                toAdd.add(temp);
            }
        }
        if (element instanceof AbstractProcess) {
            AbstractProcess process = (AbstractProcess) element;
            if(process instanceof SubProcessEvent){
                process = ModelHelper.getParentProcess(process);
            }
            if (process.getProcessTemplate() != null) {
                addTemplateInToAddList(toAdd, toGenerate, process, TemplateType.PROCESS);
            }
            if (process.getErrorTemplate() != null) {
                addTemplateInToAddList(toAdd, toGenerate, process, TemplateType.ERROR);
            }
            if (process.getWelcomePage() != null && process.getWelcomePageInternal()) {
                addTemplateInToAddList(toAdd, toGenerate, process, TemplateType.WELCOME);
            }
            if (process.getHostPage() != null) {
                addTemplateInToAddList(toAdd, toGenerate, process, TemplateType.HOST_PAGE);
            }

        }

        if (element instanceof Container) {
            for (Element el : ((Container) element).getElements()) {
                if (!(el instanceof AbstractProcess)) {
                    addTemplates(el, toAdd, toGenerate);
                }
            }
        }
    }

    private static void addTemplateInToAddList(ArrayList<File> toAdd, List<EObject> toGenerate, AbstractProcess process, TemplateType templateType) throws IOException {
        File temp;
        temp = addTemplate(process, templateType, toGenerate);
        if (temp != null && !toAdd.contains(temp)) {
            toAdd.add(temp);
        }
    }

    private static File addTemplate(Element element, TemplateType type, List<EObject> toGenerate) throws IOException {
        File newFile;
        // in case of page template (on each form)
        switch (type) {
            case PAGE:
                final AssociatedFile htmlTemplate = ((Form) element).getHtmlTemplate();
                if (htmlTemplate != null && htmlTemplate.getPath() != null && !htmlTemplate.getPath().isEmpty()) {
                    File template = WebTemplatesUtil.getFile(htmlTemplate.getPath());
                    if (template != null && template.isFile()) {
                        newFile = new File(TMP_DIR + File.separatorChar + ExporterTools.getPageTemplateWarFileName(((Form) element)));
                        newFile.createNewFile();
                        FileUtil.copy(new FileInputStream(template), new FileOutputStream(newFile));

                        return newFile;
                    }
                } else {
                    // no page template, we generate one
                    toGenerate.add(element);
                    return null;
                }
                break;
            case CONFIRMATION:
                final AssociatedFile confirmationTemplate = ((PageFlow) element).getConfirmationTemplate();
                return exportTemplate(element, type, confirmationTemplate);
            case ERROR:
                final AssociatedFile errorTemplate = ((AbstractProcess) element).getErrorTemplate();
                return exportTemplate(element, type, errorTemplate);
            case WELCOME:
                final AssociatedFile welcomePage = ((AbstractProcess) element).getWelcomePage();
                return exportTemplate(element, type, welcomePage);
            case PROCESS:
                final AssociatedFile processTemplate = ((AbstractProcess) element).getProcessTemplate();
                return exportTemplate(element, type, processTemplate);
            case HOST_PAGE:
                final AssociatedFile hostPage = ((AbstractProcess) element).getHostPage();
                return exportTemplate(element, type, hostPage);
            default:
                break;
        }
        return null;

    }

    /**
     * @param element
     * @param type
     * @param page
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    protected static File exportTemplate(Element element, TemplateType type, final AssociatedFile page) throws IOException, FileNotFoundException {
        File newFile;
        if (page != null && page.getPath() != null  && !page.getPath().isEmpty()) {
            File template = WebTemplatesUtil.getFile(page.getPath());
            if (template != null && template.exists()) {
                newFile = new File(TMP_DIR + File.separatorChar + ExporterTools.getTemplateWarFileName(element, type));
                newFile.delete();
                FileUtil.copy(new FileInputStream(template), new FileOutputStream(newFile));
                return newFile.exists() ? newFile : null;
            }
        }
        return null;
    }

}
