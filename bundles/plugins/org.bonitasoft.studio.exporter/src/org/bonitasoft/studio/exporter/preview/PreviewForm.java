/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter.preview;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.OutputDocument;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.SourceFormatter;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.exporter.ExporterTools.TemplateType;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.exporter.ExporterService;
import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
import org.bonitasoft.studio.exporter.application.HtmlTemplateGenerator;
import org.bonitasoft.studio.exporter.application.ResourcesExporter;
import org.bonitasoft.studio.exporter.application.service.CssGeneratorService;
import org.bonitasoft.studio.model.form.AbstractTable;
import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.IFrameWidget;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.ItemContainer;
import org.bonitasoft.studio.model.form.LabelPosition;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.NextFormButton;
import org.bonitasoft.studio.model.form.PasswordFormField;
import org.bonitasoft.studio.model.form.PreviousFormButton;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.RichTextAreaFormField;
import org.bonitasoft.studio.model.form.SelectFormField;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.TextInfo;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.osgi.framework.FrameworkUtil;

/**
 * 
 * Preview a form in the internal browser
 * 
 * @author Baptiste Mesta
 * 
 */
public class PreviewForm {

	public static final String INSTANTIATION_ATTR = "isInstantiationForm";


	public static final String STEP_CANDIDATES = "bonita_step_candidates";
	public static final String STEP_ASSIGNEE = "bonita_step_assignee";
	public static final String STEP_PRIORITY = "bonita_step_priority";
	public static final String STEP_DESCRIPTION = "bonita_step_description";
	public static final String STEP_STARTDATE = "bonita_step_startedDate";
	public static final String STEP_ENDDATE = "bonita_step_endedDate";
	public static final String STEP_READYDATE = "bonita_step_readyDate";
	public static final String STEP_EXPECTEDENDDATE = "bonita_step_expectedEndDate";
	public static final String NB_LINE = "nbLine";
	public static final String NB_COLUMN = "nbCol";
	public static final String SELECTED_FORM = "selectedEntry";

	private static String TMP_DIR  ; //$NON-NLS-1$






	private Map<String, Object> previewVariableContext;

	public PreviewForm(){
		this.previewVariableContext = new HashMap<String, Object>() ;
	}

	public Map<String, Object> getPreviewVariableContext() {
		return previewVariableContext;
	}

	public void setPreviewVariableContext(Map<String, Object> previewVariableContext) {
		this.previewVariableContext = previewVariableContext;
	}

	public File previewForm(Form form,ApplicationLookNFeelFileStore artifact) {
		File html;
		if(TMP_DIR != null){
			File f  = new File(TMP_DIR) ;
			if(f.exists()){
				PlatformUtil.delete(f, new NullProgressMonitor()) ;
			}
		}

		File tmp = new File(ProjectUtil.getBonitaStudioWorkFolder(),"preview"+System.currentTimeMillis() );
		tmp.mkdir() ;
		TMP_DIR =tmp.getAbsolutePath() ;
		if(artifact != null){
			prepareResources(form,artifact);
		}else{
			prepareResources(form);
		}
		org.bonitasoft.studio.model.process.Element elementToCreateCss = null;
		if(ModelHelper.getParentProcess(form) != null){
			elementToCreateCss = ModelHelper.getParentProcess(form);
		} else {
			elementToCreateCss = form;
		}
		File cssFile = CssGeneratorService.getInstance().getCssGenerator().createCssFile(TMP_DIR,elementToCreateCss);
		ArrayList<File> htmlFiles = new ArrayList<File>();
		PageFlow pageFlow = (PageFlow) form.eContainer();
		EStructuralFeature feature = form.eContainingFeature();
		if(pageFlow != null){
			int index = ((List<Form>) pageFlow.eGet(feature)).indexOf(form);

			for (Form aForm : (List<Form>)pageFlow.eGet(feature)) {
				html = createHtmlTemplate(aForm,artifact);
				html = createProcessPreview(aForm, html, cssFile,artifact);
				htmlFiles.add(html);
			}
			return htmlFiles.get(index);
		} else {
			html = createHtmlTemplate(form,artifact);
			html = createProcessPreview(form, html, cssFile,artifact);
			return html;
		}

	}


	private void prepareResources(org.bonitasoft.studio.model.process.Element form,ApplicationLookNFeelFileStore artifact) {
		// copy css to temp
		File appFolder = new File(TMP_DIR +File.separatorChar + "application");
		if(appFolder.exists()){
			FileUtil.deleteDir(appFolder);
		}
		appFolder.mkdir();
		File cssTemp = new File(appFolder, "css");
		cssTemp.mkdir();
		File imagesTemp = new File(appFolder,"images");
		imagesTemp.mkdir();



		PlatformUtil.copyResource(new File(TMP_DIR), ProjectUtil.getConsoleLibsBundle(), "/webapp/bonita-app.war", new NullProgressMonitor());

		// css
		FileUtil.getFilesFromZip(new File(TMP_DIR + File.separatorChar + "bonita-app.war"), "application/css/", cssTemp);
		FileUtil.getFilesFromZip(new File(TMP_DIR + File.separatorChar + "bonita-app.war"), "application/gwt/chrome/", cssTemp);


		// images
		FileUtil.getFilesFromZip(new File(TMP_DIR + File.separatorChar + "bonita-app.war"), "application/images/", imagesTemp);
		PlatformUtil.copyResource(imagesTemp, FrameworkUtil.getBundle(Pics.class), "/icons/form.ico", new NullProgressMonitor());
		PlatformUtil.copyResource(imagesTemp, FrameworkUtil.getBundle(Pics.class), "/icons/forms/preview_rich_text_area.png", new NullProgressMonitor());

		if(artifact != null){
			PlatformUtil.copyResource(new File(TMP_DIR),artifact.getResourcesApplicationFolder(),new NullProgressMonitor()) ;
		}

	}


	/**
	 * prepare resource to make the preview: copy files from the war
	 * @param form
	 */
	private void prepareResources(Form form) {

		PlatformUtil.copyResource(new File(TMP_DIR), ProjectUtil.getConsoleLibsBundle(), "/webapp/bonita-app.war", new NullProgressMonitor());

		// copy css to temp
		File appFolder = new File(TMP_DIR +File.separatorChar + "application");
		if(appFolder.exists()){
			FileUtil.deleteDir(appFolder);
		}


		File cssTemp = new File(appFolder, "css");
		cssTemp.mkdir();
		File imagesTemp = new File(appFolder,"images");
		imagesTemp.mkdir();

		String warPath = ProjectUtil.getConsoleLibsBundle().getLocation();
		warPath = warPath.substring(warPath.lastIndexOf(':') + 1);
		AbstractProcess process = ModelHelper.getParentProcess(form);
		//		// css
		FileUtil.getFilesFromZip(new File(TMP_DIR + File.separatorChar + "bonita-app.war"), "application/css/", cssTemp);
		FileUtil.getFilesFromZip(new File(TMP_DIR + File.separatorChar + "bonita-app.war"), "application/gwt/chrome/", cssTemp);

		// images

		FileUtil.getFilesFromZip(new File(TMP_DIR + File.separatorChar + "bonita-app.war"), "application/images/", imagesTemp);
		PlatformUtil.copyResource(imagesTemp, FrameworkUtil.getBundle(Pics.class), "/icons/form.ico", new NullProgressMonitor());
		PlatformUtil.copyResource(imagesTemp, FrameworkUtil.getBundle(Pics.class), "/icons/forms/preview_rich_text_area.png", new NullProgressMonitor());
		if (process != null) {		
			importResources(process);
		}
	}

	public File generateHtmlTemplate(Form form,ApplicationLookNFeelFileStore artifact) {
		boolean isInstatiationForm = (Boolean) (previewVariableContext.get(INSTANTIATION_ATTR) != null ? previewVariableContext.get(INSTANTIATION_ATTR) : false);
		String filePath = ((HtmlTemplateGenerator) ExporterService.getInstance().getExporterService(SERVICE_TYPE.HtmlTemplateGenerator)).createXhtmlTemplate(form,true,artifact,isInstatiationForm);
		if (filePath != null) {
			File file = new File(filePath);
			if (file.exists()) {
				return file;
			}
		}
		return null;
	}

	/**
	 * create the template with the file given by the user
	 * 
	 * @param form
	 * @return
	 */
	public File createHtmlTemplate(Form form,ApplicationLookNFeelFileStore artifact) {
		File userHtmlFile = null;

		if (form.getHtmlTemplate() != null) {
			AssociatedFile htmlTemplate = form.getHtmlTemplate();
			// user template
			userHtmlFile = new File(htmlTemplate.getPath());
		} else {
			userHtmlFile = generateHtmlTemplate(form,artifact);
		}
		// generated html file
		File htmlFile = null;
		try {
			htmlFile = File.createTempFile(form.getName(), ".html", ProjectUtil.getBonitaStudioWorkFolder());
		} catch (IOException e1) {
			BonitaStudioLog.error(e1);
		}
		Writer writer = null;
		FileInputStream fileInputStream = null;
		try {
			if(!userHtmlFile.exists()){
				userHtmlFile = WebTemplatesUtil.getFile(userHtmlFile.getPath());
			}
			fileInputStream = new FileInputStream(userHtmlFile);
			Source source = new Source(fileInputStream);

			writer = new BufferedWriter(new FileWriter(htmlFile));

			// init widgets map
			HashMap<String, Widget> ids = new HashMap<String, Widget>();
			for (Widget w : form.getWidgets()) {
				addWidgetToMap(w, ids);
			}

			LinkedList<Widget> queue = new LinkedList<Widget>();
			for (Entry<String, Widget> widget : ids.entrySet()) {
				queue.offer(widget.getValue());
			}

			OutputDocument outputDocument = new OutputDocument(source);

			while (!queue.isEmpty()) {
				Widget w = queue.poll();
				Element element = source.getElementById(w.getName());
				if(element != null){
					outputDocument.insert(element.getStartTag().getEnd(), getHtmlWidget(w));
					outputDocument.insert(element.getEndTag().getBegin(), getHtmlWidgetAfter(w));
				}
			}


			Element pageLabel = source.getElementById("bonita_form_page_label");

			if(pageLabel != null){

				boolean inserted = false;
				boolean classExists = false ;
				for (Attribute attr : pageLabel.getAttributes()) {
					if(attr.getName().toLowerCase().equals("class")){
						classExists = true ;
						inserted = true;
						break;
					}
				}
				if(!classExists && !inserted){
					outputDocument.insert(pageLabel.getStartTag().getEnd() - 1," class=\"bonita_form_page_label\"");
				}
				outputDocument.insert(pageLabel.getStartTag().getEnd(),"<div>") ;
				outputDocument.insert(pageLabel.getStartTag().getEnd(), form.getName());
				outputDocument.insert(pageLabel.getEndTag().getBegin(),"</div>") ;

			}

			writer.write(injectStepAttributesInTemplate(outputDocument.toString()));
			writer.flush();
			source = new Source(new FileInputStream(htmlFile));
			new SourceFormatter(source).setIndentString("  ").setTidyTags(true).writeTo(new FileWriter(htmlFile));

		} catch (FileNotFoundException e) {
			BonitaStudioLog.error(e);
		} catch (IOException e) {
			BonitaStudioLog.error(e);
		} finally{
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e);
				}
			}
			if( fileInputStream != null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e);
				}
			}
		}
		return htmlFile;
	}

	/**
	 * @param w
	 * @return
	 */
	protected String getHtmlWidgetAfter(Widget w) {
		//nothing to add after a widget
		return "";
	}

	private String injectStepAttributesInTemplate(String template) {
		ResourceBundle labels = ResourceBundle.getBundle("locale.i18n.ActivityAttributeLabels");
		StringBuilder regex = new StringBuilder();
		regex.append("\\$(");
		throw new RuntimeException("not implemented") ;
//		for (String stepAttribute : ActivityAttribute.attributeValues()) {
//			if (regex.length() > 3) {
//				regex.append('|');
//			}
//			regex.append(stepAttribute);
//			regex.append('|');
//			regex.append("label.");
//			regex.append(stepAttribute);
//		}
//		
//		regex.append(')');
//		Pattern pattern = Pattern.compile(regex.toString());
//		Matcher matcher = pattern.matcher(template);
//		StringBuffer stringBuffer = new StringBuffer();
//		while(matcher.find()) {
//			String itemFound = matcher.group(1);
//			if (itemFound.startsWith("label.")) {
//				String label = labels.getString(itemFound);
//				if (label != null) {
//					matcher.appendReplacement(stringBuffer, label);
//				} else {
//					matcher.appendReplacement(stringBuffer, "");
//				}
//			} else {
//				String value =  previewVariableContext.get(itemFound) != null ? (String) previewVariableContext.get(itemFound) : "" ;
//				matcher.appendReplacement(stringBuffer,  value );
//			}
//		}
//		matcher.appendTail(stringBuffer);
//
//		//"bonita_step_readyDate"
//
//		return stringBuffer.toString();
	}

	protected void addWidgetToMap(Widget w, HashMap<String, Widget> ids) {
		ids.put(w.getName(), w);
	}

	/**
	 * 
	 * merge page template and process template
	 * 
	 */
	private File createProcessPreview(Form form, File pageTemplate, File cssFile,ApplicationLookNFeelFileStore artifact) {
		// TODO add body class
		File processTemplate = prepareProcessTemplate(form,artifact);

		File outputFile = null;
		FileWriter fileWriter = null;
		try {
			outputFile = new File(TMP_DIR + File.separatorChar +"application"+File.separatorChar+ ExporterTools.getTemplateWarFileName(form, TemplateType.PAGE));

			outputFile.delete();
			outputFile.createNewFile();

			Source pageTemplateSource = new Source(new FileInputStream(pageTemplate));
			Source processTemplateSource = new Source(new FileInputStream(processTemplate));

			// get the insertion index of where the page should be put
			Element htmlElement = processTemplateSource.getElementById("bonita_form");
			if (htmlElement == null) {
				throw new RuntimeException("The process layout must contains the \"bonita_form\" div") ;
			}
			
			int insertionIndex = htmlElement.getStartTag().getEnd();

			OutputDocument outputDocument = new OutputDocument(processTemplateSource);
			// must insert body contents in bonita_form div
			Element pageElement = pageTemplateSource.getFirstElement("body");
			String contents;
			if (pageElement != null) {
				contents = pageElement.getContent().toString();
			}else{
				contents = pageTemplateSource.toString();
			}
			StringBuilder toInsert = new StringBuilder();
			toInsert.append(contents);
			outputDocument.insert(insertionIndex, toInsert.toString());

			// insert scripts and link in head:
			htmlElement = pageTemplateSource.getFirstElement("head");
			List<Element> links = htmlElement.getContent().getAllElements("link");
			List<Element> scripts = htmlElement.getContent().getAllElements("script");

			htmlElement = processTemplateSource.getFirstElement("head");
			insertionIndex = htmlElement.getStartTag().getEnd();
			toInsert = new StringBuilder();
			toInsert.append("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/bonita_forms.css\">");
			for (Element element : scripts) {
				toInsert.append(element.toString());
			}
			for (Element element : links) {
				toInsert.append(element.toString());
			}

			if (cssFile != null) {
				toInsert.append("\t<link href=\"css/" + cssFile.getName() + "\" rel=\"stylesheet\" type=\"text/css\" />\n");
			}
			toInsert.append("<link rel=\"icon\" type=\"image/png\" href=\"images/form.ico\"/>");
			toInsert.append("<link rel=\"shortcut icon\" href=\"images/form.ico\" type=\"image/x-icon\">");

			// add navigation arrows

			outputDocument.insert(insertionIndex, toInsert.toString());

			Element processLabel = processTemplateSource.getElementById("bonita_process_label") ;
			if(processLabel != null){
				outputDocument.insert(processLabel.getStartTag().getEnd(), "<div>") ;
				outputDocument.insert(processLabel.getEndTag().getBegin(), "</div>") ;
			}

			Element username = processTemplateSource.getElementById("bonita_username") ;
			if(username != null){
				outputDocument.insert(username.getStartTag().getEnd(), "<div class=\"bonita_username_label\">admin</div>") ;
			}

			Element userXpLink = processTemplateSource.getElementById("bonita_user_xp_link") ;
			if(userXpLink != null){
				outputDocument.insert(userXpLink.getStartTag().getEnd(), "<div class=\"bonita_user_xp_label\">Bonita User Experience</div><img src=\"images/cleardot.gif\" class=\"bonita_user_xp_icon\" title=\"Open Bonita User Experience\">") ;
			}

			Element logoutLink = processTemplateSource.getElementById("bonita_logout_button") ;
			if(logoutLink != null){
				outputDocument.insert(logoutLink.getStartTag().getEnd(), "<a class=\"bonita_logout_label\" href=\"\">Logout</a>") ;
			}
			
			outputDocument.insert(0, "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
			fileWriter = new FileWriter(outputFile);
			outputDocument.writeTo(fileWriter);

		} catch (FileNotFoundException e) {
			BonitaStudioLog.error(e);
		} catch (IOException e) {
			BonitaStudioLog.error(e);
		} finally {
			if(fileWriter != null){
				try {
					fileWriter.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e);
				}
			}
		}

		return outputFile;
	}

	/**
	 * get the template form user's location or the default one, import
	 * resources and import default template images when needed
	 * 
	 * @param form
	 * @return
	 */
	private File prepareProcessTemplate(Form form,ApplicationLookNFeelFileStore artifact) {
		// path to the console bundle
		//String warPath = ConsoleManagement.getConsoleLibsBundle().getLocation();
		//warPath = warPath.substring(warPath.lastIndexOf(':') + 1);

		// process template
		File processTemplate = null, tempProcessTemplate = null;
		AbstractProcess process = ModelHelper.getParentProcess(form);
		FileWriter fileWriter = null;



		try {
			if(artifact != null && artifact.getProcessTemplate() != null && artifact.getProcessTemplate().exists() ) {
				tempProcessTemplate = File.createTempFile("process",".html", ProjectUtil.getBonitaStudioWorkFolder());
				FileUtil.copy(artifact.getProcessTemplate(), tempProcessTemplate);
			}

			if (tempProcessTemplate == null && process != null && process.getProcessTemplate() != null) {
				processTemplate = WebTemplatesUtil.getFile(process.getProcessTemplate().getPath());
				if(processTemplate == null || !processTemplate.exists()){
					BonitaStudioLog.log("file not found:" + process.getProcessTemplate() + " . Using default process template");
				} else {
					tempProcessTemplate = File.createTempFile(processTemplate.getName(), ".html", ProjectUtil.getBonitaStudioWorkFolder());
					FileUtil.copy(processTemplate, tempProcessTemplate);
				}
			}
			if (tempProcessTemplate == null) {
				// use default process template
				tempProcessTemplate = FileUtil.getFileFromZip(new File(TMP_DIR + File.separatorChar + "bonita-app.war"),"WEB-INF/classes/html/bonita_process_default.html");
			}

			//insert the process name
			String processName = form.getName() ;
			if(process != null){
				processName = process.getName() ;
			}

			Source processTemplateSource = new Source(new FileInputStream(tempProcessTemplate));
			OutputDocument outputDocument = new OutputDocument(processTemplateSource);
			Element htmlElement = processTemplateSource.getElementById("bonita_process_label");
			if(htmlElement != null){
				int insertionIndex = htmlElement.getStartTag().getEnd();
				outputDocument.insert(insertionIndex, processName);

				fileWriter = new FileWriter(tempProcessTemplate);
				outputDocument.writeTo(fileWriter);
			}


			// tempProcessTemplate is our process page and it's in /tmp
		} catch (IOException e) {
			BonitaStudioLog.error(e);
		} finally {
			if(fileWriter != null){
				try {
					fileWriter.close();
				} catch (IOException e) {
					BonitaStudioLog.error(e);
				}
			}
		}

		return tempProcessTemplate;

	}

	/**
	 * add all resources in tmp_dir
	 * 
	 * @param process
	 */
	private void importResources(AbstractProcess process) {
		HashMap<File, String> toImport = new HashMap<File, String>();
		ResourcesExporter.addWidgetImages(process, toImport);
		ResourcesExporter.addResources(process, toImport);
		Set<Entry<File, String>> toImportEntries = toImport.entrySet();
		for(Entry<File, String> toImportEntry : toImportEntries){
			File file = toImportEntry.getKey();
			String s = toImportEntry.getValue();
			String tempFilePath = TMP_DIR + file.getPath().substring(file.getPath().indexOf(s) + s.length());
			try {
				File tempFile = new File(tempFilePath);
				if (!tempFile.getParentFile().exists())
					tempFile.mkdirs();
				tempFile.delete();
				FileUtil.copyFile(file, tempFile);
			} catch (IOException e) {
				BonitaStudioLog.error(e);
			}
		}
	}


	private String getDisplayLabel(Widget w) {

		if (w.getShowDisplayLabel() == null || w.getShowDisplayLabel().booleanValue()) {

			if (w.getDisplayLabel() != null && !w.getDisplayLabel().getName().isEmpty()) {
				return w.getDisplayLabel().getName();
			} else {
				return w.getName();
			}
		} else {
			return "";
		}
	}

	protected String getHtmlWidget(Widget widget) {
		String htmlCode = "";
		htmlCode += "<div>";
		if (widget instanceof FormButton) {

			if (((FormButton) widget).getLabelBehavior()) {
				htmlCode += "\n<div class=\"bonita_form_buttonlabel_entry " + addClassToWidget(widget) + "\">\n";
				htmlCode += "\t<div class=\"bonita_form_buttonlabel "+addLinkOnButton(widget)+" " + addClassToWidgetField(widget) + "\">" + getDisplayLabel(widget) + "</div>\n";
			} else {
				htmlCode += "\n<div class=\"bonita_form_button_entry " + addClassToWidget(widget) + "\">\n";
				htmlCode += "\t<button class=\"bonita_form_button " + addClassToWidgetField(widget) + "\""+addLinkOnButton(widget)+" tabindex=\"0\" type=\"button\">"
				+ getDisplayLabel(widget) + "</button>\n";

			}
			htmlCode += "</div>\n";

		} else if (widget instanceof MessageInfo || widget instanceof HtmlWidget) {
			htmlCode += "\n<div class=\"bonita_form_entry " + addClassToWidget(widget) + "\">\n";
			htmlCode += "\t<div class=\"bonita_form_message " + addClassToWidgetField(widget) + "\">" + getInputValue(widget) + "</div>";
			htmlCode += "</div>";
		} else if (widget instanceof IFrameWidget) {
			if(widget.getHtmlAttributes().get(ExporterTools.PREFIX_INPUT+ExporterTools.WIDGET_HEIGHT)!= null){
				htmlCode += "<div style=\"background-color:gray;\" class=\"bonita_iframe " + addClassToWidgetField(widget) + "\"><div style=\"color:white; font-size:12px; font-style:italic\">< iFrame /></div></div>";				
			}else{
				htmlCode += "<div style=\"background-color:gray; height:150px;\" class=\"bonita_iframe " + addClassToWidgetField(widget) + "\"><div style=\"color:white; font-size:12px; font-style:italic\">< iFrame /></div></div>";
			}
		} else if(widget instanceof HiddenWidget){

		} else {
			htmlCode += "\n<div class=\"bonita_form_entry " + addClassToWidget(widget) + "\">\n";

			if (widget.getLabelPosition().equals(LabelPosition.DOWN) || widget.getLabelPosition().equals(LabelPosition.RIGHT)) {
				htmlCode += getHtmlWidgetField(widget);
				htmlCode += getHtmlWidgetLabel(widget);
			} else {
				htmlCode += getHtmlWidgetLabel(widget);
				htmlCode += getHtmlWidgetField(widget);
			}
			htmlCode += "</div>";
			htmlCode += getMandatorySymbol(widget);

		}

		htmlCode += "</div>\n";

		return htmlCode;
	}

	/**
	 * @param widget
	 * @return
	 */
	private String getMandatorySymbol(Widget widget) {
		if(widget.isMandatory()){
			if(ModelHelper.getParentProcess(widget) != null){
				return "<div class=\"bonita_form_mandatory\">"+ModelHelper.getParentProcess(widget).getMandatorySymbol()+"</div>";
			}else{
				return "*" ;
			}
		}
		return "";
	}

	/**
	 * @param widget
	 * @return
	 */
	private String addLinkOnButton(Widget widget) {
		Form form = ModelHelper.getForm(widget);
		EStructuralFeature feature = form.eContainingFeature();
		Form linkedForm = null;
		if(form.eContainer() != null){
			List<Form> forms = (List<Form>) form.eContainer().eGet(feature);
			if(widget instanceof NextFormButton){
				if(forms.size()>1 && forms.indexOf(form)<forms.size()-1){
					linkedForm = forms.get(forms.indexOf(form)+1);
				}
			}
			if(widget instanceof PreviousFormButton){
				if(forms.size()>1 && forms.indexOf(form)>0){
					linkedForm = forms.get(forms.indexOf(form)-1);
				}
			}	
		}
		if (linkedForm != null) {
			return "onClick=\"window.location='"+ExporterTools.getTemplateWarFileName(linkedForm, TemplateType.PAGE)+"'\"";
		}else{
			return "";	
		}
	}

	/**
	 * @param widget
	 */
	private String getHtmlWidgetLabel(Widget widget) {
		String htmlCode = "";
		if (widget.getShowDisplayLabel())
			htmlCode += "\t<div class=\"bonita_form_label " + addClassToLabel(widget) + "\">" + getDisplayLabel(widget) + " </div>";
		return htmlCode;
	}

	/**
	 * @param widget
	 */
	private String getHtmlWidgetField(Widget widget) {
		String htmlCode = "";
		if (widget instanceof TextFormField || widget instanceof SuggestBox) {
			htmlCode += "\t<input class=\"bonita_form_field " + addClassToWidgetField(widget) +"\""+addReadOnly(widget) +" type=\"text\" title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\" value=\"" + getInputValue(widget) + "\" tabindex=\"0\"/>";
		} else if (widget instanceof TextAreaFormField) {
			htmlCode += "\t<textarea class=\"bonita_form_field " + addClassToWidgetField(widget) + "\""+addReadOnly(widget) +"  title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\" tabindex=\"0\">" + getInputValue(widget) + "</textarea>";
		} else if (widget instanceof RichTextAreaFormField) {
			htmlCode += "\t<div style=\"background-image:url(images/preview_rich_text_area.png); background-repeat:no-repeat; background-position:top left; background-color:#EBEBEB; height:20px; margin-bottom:0px; padding-right:2px; padding-left:2px; padding-top:2px; padding-bottom:2px; border:1px solid; border-bottom:0px;\" class=\"bonita_form_field " + addClassToWidgetField(widget) + "\">"+"</div>"+
			"<textarea style=\"margin-top:0px;\" class=\"bonita_form_field " + addClassToWidgetField(widget) + "\""+addReadOnly(widget) +"  title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\" tabindex=\"0\">" + getInputValue(widget) + "</textarea>";
		} else if (widget instanceof CheckBoxSingleFormField) {
			htmlCode += "\t<span class=\"bonita_form_field " + addClassToWidgetField(widget) + "\">\n";
			htmlCode += "\t\t<input id=\"" + widget.getName() + "cb" + "\"  "+addReadOnly(widget)+" type=\"checkbox\" title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\" tabindex=\"0\"/>\n";
			htmlCode += "\t\t<label for=\"" + widget.getName() + "cb" + "\"/>\n";
			htmlCode += "\t</span>\n";
		} else if (widget instanceof CheckBoxMultipleFormField) {
			String alignClass = "";
			if (((ItemContainer) widget).getItemClass() != null && ((ItemContainer) widget).getItemClass().equals("h"))
				alignClass = ExporterTools.getWidgetUID(widget) + "_align";
			htmlCode += "\t<div class=\"bonita_form_field " + addClassToWidgetField(widget) + "\">\n";

			htmlCode += "\t<span class=\"bonita_form_radio " + alignClass + "\">\n";
			htmlCode += "\t\t<input id=\"" + widget.getName() + "cb1" + "\"" +addReadOnly(widget) +" type=\"checkbox\" title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\" tabindex=\"0\"/>\n";
			htmlCode += "\t\t<label for=\"" + widget.getName() + "cb1" + "\">item1</label>\n";
			htmlCode += "\t<span class=\"bonita_form_radio " + alignClass + "\">\n";
			htmlCode += "\t</span>\n";
			htmlCode += "\t\t<input id=\"" + widget.getName() + "cb2" + "\" "+ addReadOnly(widget) +"type=\"checkbox\" title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\" tabindex=\"0\"/>\n";
			htmlCode += "\t\t<label for=\"" + widget.getName() + "cb2" + "\">item2</label>\n";
			htmlCode += "\t</span>\n";
			htmlCode += "\t</div>\n";
		} else if (widget instanceof PasswordFormField) {
			htmlCode += "\t<input class=\"bonita_form_field " + addClassToWidgetField(widget) + "\" " +addReadOnly(widget) +"  title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\"   type=\"password\" value=\"" + widget.getName() + "\" tabindex=\"0\"/>";
		} else if (widget instanceof ListFormField) {
			htmlCode += "\t<select class=\"bonita_form_field " + addClassToWidgetField(widget) + "\" " +addReadOnly(widget) +"   title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\"  multiple=\"\" tabindex=\"0\">\n";
			htmlCode += "\t<option value=\"item1\">item1</option>\n";
			htmlCode += "\t<option value=\"item2\">item2</option>\n";
			htmlCode += "\t</select>\n";
		} else if (widget instanceof RadioFormField) {
			String alignClass = "";
			if (((ItemContainer) widget).getItemClass() != null && ((ItemContainer) widget).getItemClass().equals("h"))
				alignClass = ExporterTools.getWidgetUID(widget) + "_align";
			htmlCode += "\t<div class=\"bonita_form_field " + addClassToWidgetField(widget) + "\">\n";
			htmlCode += "\t<span class=\"bonita_form_radio " + alignClass + "\">\n";
			htmlCode += "\t\t<input id=\"" + widget.getName() + "radio1" + "\""+addReadOnly(widget) +"  type=\"radio\" name=\"Radio_Group1\"   title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\"  tabindex=\"0\" value=\"item11\"/>\n";
			htmlCode += "\t\t<label for=\"" + widget.getName() + "radio1" + "\">item1</label>\n";
			htmlCode += "\t</span>\n";
			htmlCode += "\t<span class=\"bonita_form_radio " + alignClass + "\">\n";
			htmlCode += "\t\t<input id=\"" + widget.getName() + "radio2" + "\""+addReadOnly(widget) +"  type=\"radio\" name=\"Radio_Group1\"   title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\"  tabindex=\"0\" value=\"item22\"/>\n";
			htmlCode += "\t\t<label for=\"" + widget.getName() + "radio2" + "\">item2</label>\n";
			htmlCode += "\t</span>\n";
			htmlCode += "</div>";
		} else if (widget instanceof SelectFormField) {
			htmlCode += "\t<select class=\"bonita_form_field" + addClassToWidgetField(widget) + "\" "+addReadOnly(widget) +"   title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\"  tabindex=\"0\">\n";
			htmlCode += "\t<option value=\"item1\">item1</option>\n";
			htmlCode += "\t<option value=\"item2\">item2</option>\n";
			htmlCode += "\t</select>\n";
		} else if (widget instanceof TextInfo) {
			htmlCode += "\t<div class=\"bonita_form_text " + addClassToWidgetField(widget) + "\">" + getInputValue(widget) + "</div>";
		} else if (widget instanceof DateFormField) {
			htmlCode += "\t<input class=\"bonita_form_field " + addClassToWidgetField(widget) + "\" "+addReadOnly(widget) +" type=\"text\"   title=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\" tabindex=\"0\"/>";
		} else if (widget instanceof FileWidget) {

			if (((FileWidget) widget).isDownloadOnly() || isReadOnly(widget)) {
				htmlCode += "\t<div  class=\"" + addClassToWidgetField(widget) + "\"><a tabindex=\"0\" class=\"bonita_download_link\" href=\"http://www.bonitasoft.org/\">Attachment</a></div>";
			}else{
				htmlCode += "\t<input class=\"bonita_form_field " + addClassToWidgetField(widget) + "\" type=\"file\"  title=\""
				+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\" tabindex=\"0\"/>";
			}
		} else if (widget instanceof DurationFormField) {
			DurationFormField duration = (DurationFormField) widget;
			String alignClass = "";
			if (duration.getItemClass() != null && duration.getItemClass().equals("v"))
				alignClass = ExporterTools.getWidgetUID(widget) + "_align";
			htmlCode += "\t<div class=\"bonita_form_field " + addClassToWidgetField(widget) + "\">\n";
			htmlCode += "\t<div class=\"bonita_datetime_item " + alignClass + "\">";
			if (duration.getDay()) {
				htmlCode += "<select class=\"bonita_datetime_list_item\""+addReadOnly(widget) +"  >";

				htmlCode += "</select>";
				htmlCode += "<div class=\"bonita_datetime_label_item\">days</div>";
				htmlCode += "</div>";
			}
			if (duration.getHour()) {
				htmlCode += "<div class=\"bonita_datetime_item " + alignClass + "\">";
				htmlCode += "<select class=\"bonita_datetime_list_item\""+addReadOnly(widget) +" >";
				htmlCode += "</select>";
				htmlCode += "<div class=\"bonita_datetime_label_item\">hours</div>";
				htmlCode += "</div>";
			}
			if (duration.getMin()) {
				htmlCode += "<div class=\"bonita_datetime_item " + alignClass + "\">";
				htmlCode += "<select class=\"bonita_datetime_list_item\""+addReadOnly(widget) +" >";
				htmlCode += "</select>";
				htmlCode += "<div class=\"bonita_datetime_label_item\">minutes</div>";
				htmlCode += "</div>";
			}
			if (duration.getSec()) {
				htmlCode += "<div class=\"bonita_datetime_item " + alignClass + "\">";
				htmlCode += "<select class=\"bonita_datetime_list_item\""+addReadOnly(widget) +" >";
				htmlCode += "</select>";
				htmlCode += "<div class=\"bonita_datetime_label_item\">seconds</div>";
				htmlCode += "</div>";
			}
			htmlCode += "</div>";
		} else if (widget instanceof ImageWidget) {
			File file = ExporterTools.getWidgetFile((ImageWidget) widget);
			String path = "";
			if (file != null) {
				path = file.getName();
			} else {
				path = ((ImageWidget) widget).getImgPath().getContent();
			}
			htmlCode += "\t<img class=\"bonita_form_image " + addClassToWidgetField(widget) + "\" src=\"" + path + "\"  alt=\""
			+ (widget.getTooltip() != null ? widget.getTooltip() : "") + "\" />";
		} else if (widget instanceof AbstractTable) {
			AbstractTable table = (AbstractTable) widget;
			htmlCode += "\t<div class=\"bonita_form_field " + addClassToWidgetField(widget) + "\">\n";
			htmlCode += "<table class=\"bonita_form_table class="+addClassToTable(widget)+"\">";
			htmlCode += "<colgroup>";
			htmlCode += "<col>";
			htmlCode += "</colgroup>";
			htmlCode += "<tbody>";
			int k = 1;
			for (int i = 0; i < 3; i++) {					
				htmlCode += "<tr>";
				for (int j = 0; j < 3; j++) {
					htmlCode += "<td " + "class=\"bonita_form_table_cell " +addClassToCellsTable(widget, true, (j == 0 && table.isLeftColumnIsHeader() || j == 2 && table.isRightColumnIsHeader()
							|| (i == 0 && table.isFirstRowIsHeader() || i == 2 && table.isLastRowIsHeader())))
							+ "\">";
					htmlCode += "<div>item " + k++ + "</div>";
					htmlCode += "</td>";
				}
				htmlCode += "</tr>";
			}
			htmlCode += "</tbody>";
			htmlCode += "</table>";
			htmlCode += "\t</div>";
		} else {
			htmlCode = "";
		}
		return htmlCode;
	}



	/**
	 * @param widget 
	 * @return
	 */
	protected String addReadOnly(Widget widget) {
		if (isReadOnly(widget)) {
			return " disabled=\"disabled\" ";
		}else{
			return "";	
		}
	}

	protected boolean isReadOnly(Widget widget){
		return widget.isReadOnly() ||ModelHelper.getForm(widget) instanceof ViewForm;
	}

	private String getInputValue(Widget w){
		return ( w.getInputExpression() != null  && w.getInputExpression().getContent() !=null ?  w.getInputExpression().getContent():"" );
	}

	/**
	 * @param widget
	 * @param isHeader
	 * @return
	 */
	private String addClassToCellsTable(Widget w, boolean isCell, boolean isHeader) {
		String class_attr = "";
		if (isHeader || w.getHtmlAttributes() != null && !w.getHtmlAttributes().isEmpty()) {
			if (isCell) {
				class_attr += " " + ExporterTools.PREFIX_TABLE_CELLS + ExporterTools.getWidgetUID(w);
				if (w.getHtmlAttributes().containsKey(ExporterTools.PREFIX_TABLE_CELLS + ExporterTools.CLASS_ATTR)) {
					class_attr += " " + w.getHtmlAttributes().get(ExporterTools.PREFIX_TABLE_CELLS + ExporterTools.CLASS_ATTR);
				}
			}
			if (isHeader) {
				class_attr += " bonita_form_table_headings " + ExporterTools.PREFIX_TABLE_HEADERS + ExporterTools.getWidgetUID(w);
				if (w.getHtmlAttributes().containsKey(ExporterTools.PREFIX_TABLE_HEADERS + ExporterTools.CLASS_ATTR)) {
					class_attr += " " + w.getHtmlAttributes().get(ExporterTools.PREFIX_TABLE_HEADERS + ExporterTools.CLASS_ATTR);
				}
			}
		}
		return class_attr;
	}

	private String addClassToTable(Widget widget) {
		String class_attr = "";
		class_attr += " " + ExporterTools.PREFIX_TABLE + ExporterTools.getWidgetUID(widget);
		if (widget.getHtmlAttributes().containsKey(ExporterTools.PREFIX_TABLE +ExporterTools. CLASS_ATTR)) {
			class_attr += " " + widget.getHtmlAttributes().get(ExporterTools.PREFIX_TABLE + ExporterTools.CLASS_ATTR);
		}

		return class_attr;
	}

	/**
	 * 
	 * same than assClassToWidget but for widget's label
	 * 
	 * @see PreviewForm#addClassToWidgetField(Widget)
	 * @param w
	 * @return
	 */
	private String addClassToLabel(Widget w) {

		String class_attr = "";
		switch (w.getLabelPosition()) {
		case DOWN:
			class_attr += " " + "bonita_form_label_bottom";
			break;
		case UP:
			class_attr += " " + "bonita_form_label_top";
			break;
		case LEFT:
			class_attr += " " + "bonita_form_label_left";
			break;
		case RIGHT:
			class_attr += " " + "bonita_form_label_right";
			break;

		default:
			break;
		}
		if (w.getHtmlAttributes() != null && !w.getHtmlAttributes().isEmpty()) {
			class_attr += " " + ExporterTools.PREFIX_LABEL + ExporterTools.getWidgetUID(w);
			if (w.getHtmlAttributes().containsKey(ExporterTools.PREFIX_LABEL + ExporterTools.CLASS_ATTR)) {
				class_attr += " " + w.getHtmlAttributes().get(ExporterTools.PREFIX_LABEL + ExporterTools.CLASS_ATTR);
			}
		}
		return class_attr;
	}

	/**
	 * return the classes of the field part of the widget
	 * 
	 * @param w
	 * @return a string with all the classes in it
	 */
	private String addClassToWidgetField(Widget w) {


		StringBuilder class_attr = new StringBuilder();
		if (!(w instanceof HiddenWidget) && !(w instanceof MessageInfo) && !(w instanceof FormButton)) {
			class_attr.append(" bonita_form_");
			if(w instanceof TextInfo ||w instanceof ImageWidget || (w instanceof FileWidget && ((FileWidget) w).isDownloadOnly()) ){
				class_attr.append("static_field_");
			}else{
				class_attr.append(WidgetHelper.FIELD_PREFIX);
			}
			switch (w.getLabelPosition()) {
			case DOWN:
				class_attr.append("top");
				break;
			case UP:
				class_attr.append("bottom");
				break;
			case LEFT:
				class_attr.append("right");
				break;
			case RIGHT:
				class_attr.append("left");
				break;

			default:
				break;
			}

		}
		if (w.getHtmlAttributes() != null && !w.getHtmlAttributes().isEmpty()) {
			class_attr.append(" ");
			class_attr.append(ExporterTools.PREFIX_INPUT);
			class_attr.append(ExporterTools.getWidgetUID(w));
			if (w.getHtmlAttributes().containsKey(ExporterTools.PREFIX_INPUT + ExporterTools.CLASS_ATTR)) {
				class_attr.append(" ");
				class_attr.append(w.getHtmlAttributes().get(ExporterTools.PREFIX_INPUT + ExporterTools.CLASS_ATTR));
			}
		}
		throw new RuntimeException("not implemented") ;
//		if(w.getRealHtmlAttributes() != null && w.getRealHtmlAttributes().length()>0){
//			Map<String, String> parseToExtractHtmlAttributes = FormsExporter.parseToExtractHtmlAttributes(w.getRealHtmlAttributes());
//			Set<Entry<String, String>> entrySet = parseToExtractHtmlAttributes.entrySet();
//			for (Entry<String, String> entry : entrySet) {
//				class_attr.append("\" ");
//				class_attr.append(entry.getKey());
//				class_attr.append("=\"");
//				class_attr.append(entry.getValue());
//			}
//		}
//		return class_attr.toString();
	}

	/**
	 * return the classes of the field part of the widget
	 * 
	 * @param w
	 * @return a string with all the classes in it
	 */
	private String addClassToWidget(Widget w) {

		String class_attr = "";
		if (w.getHtmlAttributes() != null && !w.getHtmlAttributes().isEmpty()) {
			class_attr += " " + ExporterTools.PREFIX_WIDGET + ExporterTools.getWidgetUID(w);
			if (w.getHtmlAttributes().containsKey(ExporterTools.PREFIX_WIDGET + ExporterTools.CLASS_ATTR)) {
				class_attr += " " + w.getHtmlAttributes().get(ExporterTools.PREFIX_WIDGET + ExporterTools.CLASS_ATTR);
			}
		}
		return class_attr;
	}
}
