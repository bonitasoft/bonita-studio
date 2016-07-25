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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.OutputDocument;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.exporter.ExporterTools.TemplateType;
import org.bonitasoft.studio.common.exporter.QvtTransformationExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.exporter.application.service.CssGeneratorService;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.w3._1999.xhtml.DivType;

/**
 * 
 * Generate the templates for the given form
 * 
 * @author Baptiste Mesta
 * 
 */
public class HtmlTemplateGenerator {

    private static final String TMP_DIR = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();
    private static final String form_container = "bonita_form_container";
    private static final String form_header = "bonita_form_step_header";
    private static final String generated_css = "\t\t<link href=\"css/generatedcss.css\" rel=\"stylesheet\" type=\"text/css\"/>\n";

    /**
     * 
     * create an xhtml template based on the form definition
     * 
     * generate the widget table and put it in the global page template
     * 
     * @param toGenerate
     *            the form to generate a template on
     * @param isPreview
     * @return the path to the template file
     * @throws CoreException
     */
    public List<String> createXhtmlTemplate(List<EObject> toGenerate,boolean isPreview, ApplicationLookNFeelFileStore artifact,boolean instantiationForm) throws CoreException {


        Form form = (Form) toGenerate.get(0);
        AbstractProcess process = ModelHelper.getParentProcess(form);
        File pageTemplateFile = null;
        File consultationTemplateFile = null;
        InputStream pageTemplate = null;
        boolean useBlackTemplate = false;
        //check if the default template should be used
        StringBuilder cssContents = new StringBuilder();
        CssGeneratorService.getInstance().getCssGenerator().addToCss(process!=null?process:form, cssContents);
        boolean useGeneratedCss = cssContents.length()>0;


        if(process != null){
            if(process.getPageTemplate()!= null ){
                pageTemplateFile = WebTemplatesUtil.getFile(process.getPageTemplate().getPath());

                if(pageTemplateFile != null && !pageTemplateFile.exists()){
                    pageTemplateFile = null;
                } else {
                    if(process.getProcessTemplate() == null || process.getProcessTemplate().getPath() == null
                            || process.getProcessTemplate().getPath().length() ==0){
                        //no process template we add the black page template to be compliant with the default bonita template
                        useBlackTemplate = true;
                    }
                }
            }
            if(process.getConsultationTemplate()!=null){
                consultationTemplateFile = WebTemplatesUtil.getFile(process.getConsultationTemplate().getPath());
                if(consultationTemplateFile != null && !consultationTemplateFile.exists()){
                    consultationTemplateFile = null;
                }
            }
        }

        List<String> divs = generateFormContainerDivs(toGenerate);
        for (String string : divs) {
            Source pageTemplateSource;
            try {

                form = (Form)toGenerate.get(divs.indexOf(string));

                File templateFile = new File(string);
                if(form instanceof ViewForm){
                    //use global consultation form if it exists
                    if(artifact != null && ((IFile)artifact.getConsultationTemplate()).exists()){
                        pageTemplate = ((IFile)artifact.getConsultationTemplate()).getContents() ;
                    }
                    if(pageTemplate == null){
                        if(consultationTemplateFile != null && consultationTemplateFile.exists()){
                            pageTemplate = new FileInputStream(consultationTemplateFile);
                        }else{
                            pageTemplate = HtmlTemplateGenerator.class.getResourceAsStream("default_page_template.html");
                        }
                    }
                } else {

                    if(artifact != null && ((IFile)artifact.getGlobalPageTemplate()).exists()){
                        pageTemplate = ((IFile)artifact.getGlobalPageTemplate()).getContents() ;
                    }
                    if(pageTemplate == null){
                        if ( pageTemplateFile == null || !pageTemplateFile.exists()) {
                            if (useBlackTemplate) {
                                pageTemplate = HtmlTemplateGenerator.class.getResourceAsStream("black_page_template.html");
                            } else {
                                pageTemplate = HtmlTemplateGenerator.class.getResourceAsStream("default_page_template.html");
                            }
                        } else {
                            pageTemplate = new FileInputStream(pageTemplateFile);
                        }
                    }
                }

                pageTemplateSource = new Source(pageTemplate);
                OutputDocument resultingTemplate = new OutputDocument(pageTemplateSource);

                FileInputStream fis = new FileInputStream(templateFile);
                Source divSource = new Source(fis);
                //the content of the generated bonita_form_container
                Segment toInject = divSource.getFirstElementByClass(form_container).getContent();
                net.htmlparser.jericho.Element divTarget = pageTemplateSource.getFirstElementByClass(form_container);
                int injectIndex;
                if(divTarget == null){
                    BonitaStudioLog.log("No bonita_form_container found in the consultation template");
                    injectIndex = 0;
                }else{
                    //index if the inside of bonita_form_container in the page template
                    injectIndex = divTarget.getContent().getBegin();
                }
                //inject it
                resultingTemplate.insert(injectIndex, toInject);

                //inject the generated css link
                if (useGeneratedCss) {
                    net.htmlparser.jericho.Element head = pageTemplateSource.getFirstElement("head");
                    if (head == null) {
                        // inject a new head if there is none
                        net.htmlparser.jericho.Element htmlTag = pageTemplateSource.getFirstElement("html");
                        if (htmlTag != null) {
                            injectIndex = htmlTag.getStartTag().getEnd();
                        } else {
                            injectIndex = 0;
                        }
                        resultingTemplate.insert(injectIndex, "<head>" + generated_css + "</head>");
                    } else {
                        injectIndex = head.getEndTag().getBegin();
                        resultingTemplate.insert(injectIndex, generated_css);
                    }
                }
                //instantiation form
                if(instantiationForm || form.eContainer() instanceof AbstractProcess){
                    //remove the bonita_form_step_header
                    net.htmlparser.jericho.Element element = pageTemplateSource.getElementById(form_header);
                    List<net.htmlparser.jericho.Element> headers = pageTemplateSource.getAllElementsByClass(form_header);
                    HashSet<net.htmlparser.jericho.Element> hashSet = new HashSet<net.htmlparser.jericho.Element>(headers);
                    hashSet.add(element);
                    for (net.htmlparser.jericho.Element bonita_header : hashSet) {
                        if(bonita_header != null){
                            Attributes divAttributes = bonita_header.getAttributes();
                            Map<String, String> attributesMap = resultingTemplate.replace(divAttributes, true);
                            String classAttr;
                            if(attributesMap.containsKey("class")){
                                classAttr = attributesMap.get("class") + " " + CssGenerator.HIDDEN_STYLE_CLASS;
                            }else{
                                classAttr = CssGenerator.HIDDEN_STYLE_CLASS;
                            }
                            attributesMap.put("class", classAttr);
                        }
                    }
                }


                FileOutputStream outputStream = new FileOutputStream(templateFile);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,Charset.forName("UTF-8"));
                resultingTemplate.writeTo(outputStreamWriter);
                outputStream.close();
                outputStreamWriter.close();
                pageTemplate.close();
                pageTemplate = null ;
                fis.close();

            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }

        return divs;
    }

    /**
     * 
     * create form containers divs containing the table of widgets
     * @param toGenerate
     * @return
     */
    private List<String> generateFormContainerDivs(List<EObject> toGenerate) {
        URI transformationURI = URI.createPlatformPluginURI("/org.bonitasoft.studio.exporter/transforms/Form2Xhtml.qvto", false); //$NON-NLS-1$

        List<Object> list = new ArrayList<Object>();
        List<EObject> copiedToGenerate = createCopy(toGenerate);

        list.add(copiedToGenerate);
        List<EObject> outObjects = QvtTransformationExecutor.executeQvtTransformation(transformationURI, list, false);

        try {
            List<String> files = new ArrayList<String>();
            // let's persist them using a resource
            ResourceSetImpl resourceSet = new ResourceSetImpl();
            for (EObject eObject : toGenerate) {
                // put it in workspace....

                File htmlFile = new File(TMP_DIR + File.separatorChar + ExporterTools.getPageTemplateWarFileName((Form) eObject));
                htmlFile.delete();
                htmlFile.createNewFile();
                FileOutputStream os = new FileOutputStream(htmlFile);
                URI uri = URI.createPlatformResourceURI("tmp/" + htmlFile.getName(), false); //$NON-NLS-1$
                Resource outResource = resourceSet.createResource(uri);
                final EObject htmlForm = getXhtmlForm(outObjects, eObject);
                if(htmlForm == null){
                    if(os != null){
                        os.close();
                    }
                    throw new RuntimeException("Form template generation failed");
                }
                outResource.getContents().add(htmlForm);
                Map<String, String> options = new HashMap<String, String>();
                options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
                outResource.save(os, options);
                files.add(htmlFile.getAbsolutePath());
            }
            return files;

        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /**
     * we create a copy because it does not work with multiple object contained in a same parent
     * we put also an id in "documentation" of each form in order to be able to know wich form is linked with which template after
     * 
     * @param toGenerate
     * @return
     */
    private List<EObject> createCopy(List<EObject> toGenerate) {

        //put the name in Documentation to keep the parent id (bad...)
        List<EObject> newList = new ArrayList<EObject>();
        for (EObject eObject : toGenerate) {
            Form newForm = (Form) EcoreUtil.copy(eObject);
            if(eObject.eContainer() != null){
                String suffix = "";
                if(eObject.eContainer() instanceof AbstractProcess){
                    suffix= "_"+ExporterTools.PROCESS;
                }
                //if it's a consultation form we add consultation to the id
                if(eObject.eContainmentFeature().equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM)){
                    newForm.setDocumentation(ModelHelper.getEObjectID(eObject.eContainer())+suffix+"_" + ExporterTools.CONSULTATION+ModelHelper.getEObjectID(eObject));
                }else if(eObject.eContainmentFeature().equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS)){
                    newForm.setDocumentation(ModelHelper.getEObjectID(eObject.eContainer())+suffix+"_" + ExporterTools.RECAP+ModelHelper.getEObjectID(eObject));
                }else{
                    newForm.setDocumentation(ModelHelper.getEObjectID(eObject.eContainer())+suffix+"_"+ModelHelper.getEObjectID(eObject));
                }
            } else {
                newForm.setDocumentation("");
            }

            newList.add(newForm);
        }
        return newList;
    }

    /**
     * Since qvto do not retreive elements in the same order that they were given
     * we have to put in the root object the id of the object (it's put in "documentation" of the form before the execution)
     * @param outObjects
     * @param eObject
     * @return
     */
    private EObject getXhtmlForm(List<EObject> outObjects, EObject eObject) {
        if (eObject instanceof Form) {
            Form form = (Form) eObject;
            String name = ExporterTools.getTemplateWarFileName(form, TemplateType.PAGE);
            name = name.substring(0, name.length() - 5);//remove .html extension
            for (EObject xhtml : outObjects) {
                if (xhtml instanceof DivType) {
                    if (((DivType) xhtml).getId()!= null && ((DivType) xhtml).getId().equals(name)) {
                        ((DivType) xhtml).setId(null);
                        return xhtml;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 
     * create an xhtml template based on the form definition
     * 
     * @param form
     * @param isPreview
     * @return
     */
    public String createXhtmlTemplate(Form form,boolean isPreview, ApplicationLookNFeelFileStore artifact,boolean instantiationForm) {
        List<EObject> forms = new ArrayList<EObject>();
        forms.add(form);

        try {
            return createXhtmlTemplate(forms,isPreview,artifact,instantiationForm).get(0);
        } catch (CoreException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param name
     * @param outputFileWriter
     * @param fis2
     * @throws IOException
     */
    public void addDivInTemplate(String name, FileInputStream pageTemplate, Writer outputFileWriter) throws IOException {
        Source pageTemplateSource = new Source(pageTemplate);
        OutputDocument resultingTemplate = new OutputDocument(pageTemplateSource);
        net.htmlparser.jericho.Element formContainer = pageTemplateSource.getFirstElementByClass("bonita_form_container");
        int injectIndex = formContainer.getEndTag().getBegin();
        //inject it
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t<div id=\"");
        stringBuilder.append(name);
        stringBuilder.append("\"></div>\n\t<div id=\"");
        stringBuilder.append(name);
        stringBuilder.append("_default_validator\"></div>\n");

        resultingTemplate.insert(injectIndex, stringBuilder);

        resultingTemplate.writeTo(outputFileWriter);
    }

    /**
     * @param oldId
     * @param newId
     * @param fis
     * @param fileWriter
     * @throws IOException
     */
    public void changeDivId(String oldId, String newId, FileInputStream pageTemplate, FileWriter outputFileWriter) throws IOException {
        Source pageTemplateSource = new Source(pageTemplate);
        OutputDocument resultingTemplate = new OutputDocument(pageTemplateSource);
        net.htmlparser.jericho.Element div = pageTemplateSource.getElementById(oldId);
        if (div != null) {
            Attributes divAttributes = div.getAttributes();
            Map<String, String> attributesMap = resultingTemplate.replace(divAttributes, true);
            attributesMap.put("id", newId);
        }
        net.htmlparser.jericho.Element validator_div = pageTemplateSource.getElementById(oldId+"_default_validator");
        if (validator_div != null) {
            Attributes divAttributes = validator_div.getAttributes();
            Map<String, String> attributesMap = resultingTemplate.replace(divAttributes, true);
            attributesMap.put("id", newId+"_default_validator");
        }
        resultingTemplate.writeTo(outputFileWriter);
    }

}
