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
package org.bonitasoft.studio.common.exporter;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * Utility class used by the exporter
 * 
 * @author Baptiste Mesta
 * 
 */
public class ExporterTools {

    public static final String HTML_ATTR = "html"; //$NON-NLS-1$
    public static final String STYLE_ATTR = "style"; //$NON-NLS-1$
    public static final String ATTR_ENABLE = "enabled"; //$NON-NLS-1$
    public static final String CLASS_ATTR = "class"; //$NON-NLS-1$
    public static final String PREFIX_INPUT = "input_"; //$NON-NLS-1$
    public static final String PREFIX_LABEL = "label_"; //$NON-NLS-1$
    public static final String PREFIX_WIDGET = "widget_"; //$NON-NLS-1$
    public static final String PREFIX_TABLE = "table_"; //$NON-NLS-1$
    public static final String PREFIX_TABLE_CELLS = "table_cells_"; //$NON-NLS-1$
    public static final String PREFIX_TABLE_HEADERS = "table_headers_"; //$NON-NLS-1$
    public static final String FONT_SIZE = "css:font-size"; //$NON-NLS-1$
    public static final String FONT_FAMILY = "css:font-family"; //$NON-NLS-1$
    public static final String FONT_STYLE = "css:font-style"; //$NON-NLS-1$
    public static final String TEXT_ALIGN = "css:text-align"; //$NON-NLS-1$
    public static final String TEXT_DECORATION = "css:text-decoration"; //$NON-NLS-1$
    public static final String TEXT_COLOR = "css:color"; //$NON-NLS-1$
    public static final String TEXT_UNDERLINE = "css:text-underline"; //$NON-NLS-1$
    public static final String TEXT_STRIKE = "css:text-strike"; //$NON-NLS-1$
    public static final String TEXT_ITALIC = "css:text-italic"; //$NON-NLS-1$
    public static final String TEXT_BOLD = "css:text-bold"; //$NON-NLS-1$
    public static final String TEXT_LEFT = "css:text-left"; //$NON-NLS-1$
    public static final String TEXT_CENTER = "css:text-center"; //$NON-NLS-1$
    public static final String TEXT_RIGHT = "css:text-right"; //$NON-NLS-1$
    public static final String WIDGET_WIDTH = "css:width"; //$NON-NLS-1$
    public static final String WIDGET_HEIGHT = "css:height"; //$NON-NLS-1$
    public static final String VISIBILITY_HIDDEN = "css:hidden"; //$NON-NLS-1$
    public static final String ATTR_MAXLENGTH = "attr:maxlength"; //$NON-NLS-1$
    public static final String ATTR_MAXHEIGHT = "attr:maxheight"; //$NON-NLS-1$
    public static final String TEXT_JUSTIFY = "css:text-justify"; //$NON-NLS-1$
    public static final String GROOVY_PREFIX = "${"; //$NON-NLS-1$
    public static final String GROOVY_SUFFIX = "}"; //$NON-NLS-1$
    public static final String CONSULTATION = "consultation";
    public static final String RECAP = "recap";
    public static final String PROCESS = "process";
    public static final String PREFIX_ITEMS = "items_"; //$NON-NLS-1$
    public static final String UTF_8 = "UTF-8"; //$NON-NLS-1$
    public static final String PREFIX_IMAGE = "image_";

    public enum TemplateType {
        PAGE, CONFIRMATION, ERROR, PROCESS, WELCOME, HOST_PAGE, GLOBAL_PAGE, GLOBAL_CONSULTATION, LOGIN_PAGE
    }

    /**
     * 
     * get the path where the page template must be put in the exported war
     * 
     * @param form
     * @return the path
     */
    public static String getPageTemplateWarFileName(Form form) {
        return getFormUID(form) + ".html";

    }

    public static String getPageTemplateWarPath(Form form) {
        return "html/" + getPageTemplateWarFileName(form); //$NON-NLS-1$
    }

    public static String getTemplateWarFileName(Element element, TemplateType templateType) {
    	final String id = ModelHelper.getEObjectID(element);
        switch (templateType) {
            case PAGE:
                return getPageTemplateWarFileName((Form) element);

            case ERROR:
                return id + "_error_template.html"; //$NON-NLS-1$
            case WELCOME:
                return id + "_welcome_page.html"; //$NON-NLS-1$

            case CONFIRMATION:
                // af.setWarPath("html/" + pageFlow.getName() +
                // "_confirmation_template.html");
                // we use a process so we put it in process template
                if (element instanceof AbstractProcess) {
                    return id + "_confirmation_template.html"; //$NON-NLS-1$
                } else {
                    AbstractProcess process = null;
                    EObject container = element.eContainer();
                    while (container != null && !(container instanceof AbstractProcess)) {
                        container = container.eContainer();
                    }
                    if (container != null) {
                        process = (AbstractProcess) container;
                    }
                    String procId="" ;
                    if(process!= null){
                    	  procId = ModelHelper.getEObjectID(process)+"_";
                    }
                   
                    return procId + id + "_confirmation_template.html"; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                }

            case PROCESS:
                return id + "_process_template.html"; //$NON-NLS-1$
            case HOST_PAGE:
                return id + "_host_page.html";
            default:
                return ""; //$NON-NLS-1$
        }
    }

    public static String getTemplateWarPath(Element element, TemplateType templateType) {
        return "html/" + getTemplateWarFileName(element, templateType); //$NON-NLS-1$
    }

    public static String getWidgetUID(Widget w) {
        Form form = ModelHelper.getForm(w);
        Element pageFlow = (Element) form.eContainer();
        if (pageFlow != null) {
            return getFormUID(form) + "_" + ModelHelper.getEObjectID(w); //$NON-NLS-1$
        } else {
            return "_" + ModelHelper.getEObjectID(form) + "_" + ModelHelper.getEObjectID(w); //$NON-NLS-1$
        }

    }

    /**
     * 
     * @param form
     * @return the form uuid for the exporter
     */
    public static String getFormUID(Form form) {
        Element pageFlow = (Element) form.eContainer();
        String uuid = "";
        if (pageFlow != null) {
            String suffix = "";
            if (pageFlow instanceof AbstractProcess) {
                suffix = "_" + PROCESS;
            }
            if (form.eContainmentFeature().equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM)) {
                uuid = ModelHelper.getEObjectID(pageFlow) + suffix + "_" + CONSULTATION  + ModelHelper.getEObjectID(form); //$NON-NLS-1$ //$NON-NLS-2$
            } else if (form.eContainmentFeature().equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS)) {
                uuid = ModelHelper.getEObjectID(pageFlow) + suffix + "_" + RECAP  + ModelHelper.getEObjectID(form); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                uuid = ModelHelper.getEObjectID(pageFlow) + suffix + "_" + ModelHelper.getEObjectID(form); //$NON-NLS-1$
            }
        } else {
            uuid = "_" + form.getName(); //$NON-NLS-1$
        }
        return uuid;
    }

    public static File getWidgetFile(ImageWidget w) {
        if (w.getImgPath() != null && w.getImgPath().getContent() != null) {
            File file = new File(w.getImgPath().getContent());
            if (file.exists()) {
                return file;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String toSimpleExpression(String expression) {
        if (isGroovyExpression(expression)) {
            expression = expression.substring(expression.indexOf(GROOVY_PREFIX) + GROOVY_PREFIX.length(), expression.lastIndexOf(GROOVY_SUFFIX));
        }
        return expression;
    }

    /**
     * @param expression
     * @return
     */
    public static String toGroovyExpression(String expression) {
        if (isGroovyExpression(expression)) {
            return expression;
        } else {
            return GROOVY_PREFIX + expression + GROOVY_SUFFIX;
        }
    }

    public static boolean isGroovyExpression(String text) {
        return text.startsWith(GROOVY_PREFIX) && text.endsWith(GROOVY_SUFFIX);
    }

    /**
     * @param url
     * @param timestamp TODO
     * @param baseFolder
     * @param prefix
     * @param suffix
     * @return
     */
    public static String toApplicationResourceURL(String url, String processName, String processVersion, long timestamp) {
        String newUrl = null;
        try {
            newUrl = "applicationResource?location=" + URLEncoder.encode(url, UTF_8) + "&process=" + URLEncoder.encode(processName,UTF_8) + "--" +  URLEncoder.encode(processVersion,UTF_8) + "&timestamp=" + timestamp;
        } catch (UnsupportedEncodingException e) {
            BonitaStudioLog.error(e);
        }
        if (newUrl == null) {
            try {
                newUrl = "applicationResource?location=" + url + "&process=" + URLEncoder.encode(processName,UTF_8) + "--" + URLEncoder.encode(processVersion,UTF_8) + "&timestamp=" + timestamp;
            } catch (UnsupportedEncodingException e) {
                BonitaStudioLog.error(e);
            }
        }
        return newUrl;
    }

}
