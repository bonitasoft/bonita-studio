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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.StringTokenizer;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.model.form.Column;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ItemContainer;
import org.bonitasoft.studio.model.form.Line;
import org.bonitasoft.studio.model.form.RichTextAreaFormField;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EMap;

/**
 *
 * create a css file using widgets styles and other attributes
 *
 * @author Baptiste Mesta
 *
 */
public class CssGenerator {

    public static final String HIDDEN_STYLE_CLASS = "hidden_on_instantiation";

    public File createCssFile(final String targetFolder,final Element element) {
        // TODO consultation form
        final StringBuilder cssContents = new StringBuilder();

        addToCss(element, cssContents);
        addTableContainerClass(cssContents) ;
        addHiddenStyle(cssContents);

        if (cssContents.toString().length() > 0) {
            File generatedCss = new File(targetFolder  + File.separatorChar + "application" + File.separatorChar + "css"); //$NON-NLS-1$
            generatedCss.mkdirs();
            generatedCss = new File(generatedCss.getAbsolutePath() + File.separatorChar + "generatedcss.css"); //$NON-NLS-1$
            generatedCss.delete();
            Writer writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(generatedCss));
                writer.write(cssContents.toString());
                writer.flush();
                return generatedCss;

            } catch (final FileNotFoundException e1) {
                BonitaStudioLog.error(e1);
            } catch (final IOException e2) {
                BonitaStudioLog.error(e2);
            } finally{
                if(writer != null){
                    try {
                        writer.close();
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }

        return null;

    }

    private void addHiddenStyle(final StringBuilder cssContents) {
        cssContents.append("\n.");
        cssContents.append(HIDDEN_STYLE_CLASS);
        cssContents.append(" {\n");
        cssContents.append("\tdisplay: none;\n");
        cssContents.append("}\n");
    }

    private void addTableContainerClass(final StringBuilder cssContents) {
        cssContents.append("\n.bonita_table_container {\n");
        cssContents.append("\ttable-layout: fixed;\n");
        cssContents.append("}\n");
    }

    public void addCssToWar(final AbstractProcess process, final File destFolderFile, final IProgressMonitor monitor) {
        final File cssFile = createCssFile(ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath(),process);
        if (cssFile != null) {
            final File destFile = new File(destFolderFile.getAbsolutePath() + File.separatorChar + "application" + File.separatorChar + "css"); //$NON-NLS-1$ //$NON-NLS-2$
            destFile.mkdirs();
            PlatformUtil.copyResource(destFile, cssFile, monitor);
            PlatformUtil.delete(cssFile.getParentFile().getParentFile(), monitor) ;
        }
    }

    protected void addToCss(final Element element, final StringBuilder cssContents) {

        if (element instanceof Form) {
            for (final Line line : ((Form) element).getLines()) {
                addHeightRule(cssContents, element, line);
            }
            for (final Column column : ((Form) element).getColumns()) {
                addWidthRule(cssContents, element, column);
            }
            for (final Widget widget : ((Form) element).getWidgets()) {
                addToCss(widget, cssContents);
            }
        }

        if (element instanceof PageFlow) {
            for (final Form form : ((PageFlow) element).getForm()) {
                addToCss(form, cssContents);
            }
            if(element instanceof AbstractProcess){
                for (final Form form : ((AbstractProcess) element).getRecapForms()) {
                    addToCss(form, cssContents);
                }
            }
        }

        if(element instanceof ViewPageFlow){
            for (final Form form : ((ViewPageFlow) element).getViewForm()) {
                addToCss(form, cssContents);
            }
        }
        if (element instanceof Container) {
            for (final Element el : ((Container) element).getElements()) {
                // do not add contained process
                if (!(el instanceof AbstractProcess)) {
                    addToCss(el, cssContents);
                }
            }
        }

    }

    protected void addToCss(final Widget widget, final StringBuilder cssContents) {

        // browse widget for css attr
        final EMap<String, String> map = widget.getHtmlAttributes();

        if (FormPackage.eINSTANCE.getItemContainer().isSuperTypeOf(widget.eClass())) {
            createWidgetRule(cssContents, (ItemContainer) widget);
        }
        addTdRule(cssContents, widget);
        if (map != null && map.size() > 0) {
            if (map.size() > 0) {
                createRules(cssContents, map, widget);
            }
        }

    }

    protected void createRules(final StringBuilder cssContents, final EMap<String, String> map, final Widget widget) {
        createWidgetRule(cssContents, map, widget);

        // create input rule
        createRule(cssContents, map, ExporterTools.PREFIX_INPUT, widget);

        // create label rule
        createRule(cssContents, map, ExporterTools.PREFIX_LABEL, widget);

        // create table rules
        createRule(cssContents, map, ExporterTools.PREFIX_TABLE, widget);
        createRule(cssContents, map, ExporterTools.PREFIX_TABLE_CELLS, widget);
        createRule(cssContents, map, ExporterTools.PREFIX_TABLE_HEADERS, widget);
        if(widget instanceof ItemContainer || widget instanceof SuggestBox){
            createRule(cssContents, map, ExporterTools.PREFIX_ITEMS, widget);
        }

        // create image rules
        createRule(cssContents, map, ExporterTools.PREFIX_IMAGE, widget);
    }

    /**
     * @param cssContents
     * @param object
     */
    private void createWidgetRule(final StringBuilder cssContents, final ItemContainer widget) {
        if (widget.getItemClass() != null) {
            cssContents.append("\n." + ExporterTools.getWidgetUID((Widget) widget) + "_align" + "{\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            if (widget.getItemClass().equals("v")) { //$NON-NLS-1$
                cssContents.append("clear:both;"); //$NON-NLS-1$
                cssContents.append("margin-top:5px;"); //$NON-NLS-1$
            } else {
                cssContents.append("float:left;"); //$NON-NLS-1$
            }

            // close Css rule
            cssContents.append("\n}\n"); //$NON-NLS-1$
        }

    }

    private void createWidgetRule(final StringBuilder cssContents, final EMap<String, String> map, final Widget widget) {
        if(widget instanceof RichTextAreaFormField){
            cssContents.append("\n." + "widget_" + ExporterTools.getWidgetUID(widget) + " div.bonita_rich_text {\n"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        } else {
            cssContents.append("\n." + "widget_" + ExporterTools.getWidgetUID(widget) + "{\n"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        }
        if (map.containsKey(ExporterTools.PREFIX_WIDGET + ExporterTools.STYLE_ATTR)) {

            // add style property
            final StringTokenizer st = new StringTokenizer(map.get(ExporterTools.PREFIX_WIDGET +ExporterTools. STYLE_ATTR).replace("\n", ""), ";");
            while (st.hasMoreElements())
            {
                cssContents.append("\t" + st.nextToken() + ";" + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }

        }
        if (map.containsKey(ExporterTools.PREFIX_WIDGET + ExporterTools.WIDGET_HEIGHT)) {
            try {
                // by default px format
                final int height = Integer.valueOf(map.get(ExporterTools.PREFIX_WIDGET + ExporterTools.WIDGET_HEIGHT));
                cssContents.append("\theight:" + height + "px;\n"); //$NON-NLS-1$ //$NON-NLS-2$
            } catch (final NumberFormatException e) {
                cssContents.append("\t" + "height : " + map.get(ExporterTools.PREFIX_WIDGET + ExporterTools.WIDGET_HEIGHT) + ";" + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

            }
        }
        if (map.containsKey(ExporterTools.PREFIX_WIDGET + ExporterTools.WIDGET_WIDTH)) {
            try {
                // by default px format
                final int width = Integer.valueOf(map.get(ExporterTools.PREFIX_WIDGET + ExporterTools.WIDGET_WIDTH));
                cssContents.append("\t width :" + width + "px;\n");
            } catch (final NumberFormatException e) {
                cssContents.append("\t width : " + map.get(ExporterTools.PREFIX_WIDGET + ExporterTools.WIDGET_WIDTH) + ";" + "\n");

            }
        }
        // close Css rule
        cssContents.append("\n}\n");
    }

    protected void addHeightRule(final StringBuilder cssContents, final Element element, final Line line) {
        if (element instanceof Form) {
            cssContents.append("\n." + ExporterTools.getFormUID((Form) element) + "_Row" + line.getNumber() + "{\n");
        } else {
            cssContents.append("\n." + ExporterTools.getFormUID(ModelHelper.getForm((Widget) element)) + "_" + element.getName() + "_Row" + line.getNumber() + "{\n");
        }
        try {
            // by default px format
            final int height = Integer.valueOf(line.getHeight());
            cssContents.append("\theight:" + height + "px;\n");
        } catch (final NumberFormatException e) {
            cssContents.append("\theight:" + line.getHeight() + ";\n");

        }
        // close Css rule
        cssContents.append("\n}\n");

    }

    protected void addWidthRule(final StringBuilder cssContents, final Element element, final Column column) {
        if (element instanceof Form) {
            cssContents.append("\n." + ExporterTools.getFormUID((Form) element) + "_Column" + column.getNumber() + "{\n");
        } else {
            cssContents.append("\n." + ExporterTools.getFormUID(ModelHelper.getForm((Widget) element)) + "_" + element.getName() + "_Column" + column.getNumber() + "{\n");
        }
        try {
            // by default px format
            final int width = Integer.valueOf(column.getWidth());
            cssContents.append("\twidth:" + width + "px;\n");
        } catch (final NumberFormatException e) {
            cssContents.append("\twidth:" + column.getWidth() + ";\n");
        }
        // close Css rule
        cssContents.append("\n}\n");

    }

    private void addTdRule(final StringBuilder cssContents, final Widget w) {
        if (w instanceof FormButton && w.eContainer().eContainer() != null) {
            cssContents.append("\n." + ExporterTools.getFormUID(ModelHelper.getForm(w)) + "_td_" + w.getName() + "{\n");

            cssContents.append("\t" + "text-align:center;" + "\n");
            // close Css rule
            cssContents.append("\n}\n");
        }

    }

    protected void createRule(final StringBuilder cssContents, final EMap<String, String> map, final String prefix, final Widget widget) {
        if(widget instanceof RichTextAreaFormField){
            cssContents.append("\n." + prefix + ExporterTools.getWidgetUID(widget) + " div.bonita_rich_text {\n");
        } else {
            cssContents.append("\n." + prefix + ExporterTools.getWidgetUID(widget) + "{\n");
        }


        if (map.containsKey(prefix + ExporterTools.STYLE_ATTR)) {

            // add style property
            final StringTokenizer st = new StringTokenizer(map.get(prefix + ExporterTools.STYLE_ATTR).replace("\n", ""), ";");
            while (st.hasMoreElements()) {
                cssContents.append("\t" + st.nextToken() + ";" + "\n");
            }

        }

        if (map.containsKey(prefix + ExporterTools.FONT_SIZE)) {
            cssContents.append("\t" + "font-size : " + map.get(prefix + ExporterTools.FONT_SIZE) + ";" + "\n");
        }
        if (map.containsKey(prefix + ExporterTools.FONT_FAMILY)) {
            cssContents.append("\t" + "font-family : " + map.get(prefix + ExporterTools.FONT_FAMILY) + ";" + "\n");
        }
        // TEXT-STYLE
        if (map.containsKey(prefix + ExporterTools.TEXT_ITALIC)) {
            cssContents.append("\t" + "font-style : " + "italic" + ";" + "\n");
        }
        // TEXT-WEIGHT
        if (map.containsKey(prefix + ExporterTools.TEXT_BOLD)) {
            cssContents.append("\t" + "font-weight : " + "bold" + ";" + "\n");
        }

        // TEXT-ALIGN
        if (map.containsKey(prefix + ExporterTools.TEXT_LEFT)) {
            cssContents.append("\t" + "text-align : " + "left" + ";" + "\n");
        }
        if (map.containsKey(prefix + ExporterTools.TEXT_CENTER)) {
            cssContents.append("\t" + "text-align : " + "center" + ";" + "\n");
        }
        if (map.containsKey(prefix + ExporterTools.TEXT_RIGHT)) {
            cssContents.append("\t" + "text-align : " + "right" + ";" + "\n");
        }
        if (map.containsKey(prefix + ExporterTools.TEXT_JUSTIFY)) {
            cssContents.append("\t" + "text-align : " + "justify" + ";" + "\n");
        }

        // TEXT_DECORATION
        if (map.containsKey(prefix + ExporterTools.TEXT_UNDERLINE)) {
            cssContents.append("\t" + "text-decoration : " + "underline" + ";" + "\n");
        }

        if (map.containsKey(prefix + ExporterTools.TEXT_STRIKE)) {
            cssContents.append("\t" + "text-decoration : " + "line-through" + ";" + "\n");
        }
        if (map.containsKey(prefix + ExporterTools.TEXT_COLOR)) {
            cssContents.append("\t" + "color : " + map.get(prefix + ExporterTools.TEXT_COLOR) + ";" + "\n");
        }
        if (map.containsKey(ExporterTools.VISIBILITY_HIDDEN)) {
            cssContents.append("\t" + "visibility : hidden" + ";" + "\n");
        }
        if (map.containsKey(prefix + ExporterTools.WIDGET_HEIGHT)) {
            try {
                // by default px format
                final int height = Integer.valueOf(map.get(prefix + ExporterTools.WIDGET_HEIGHT));
                cssContents.append("\t height :" + height + "px;\n");
            } catch (final NumberFormatException e) {
                cssContents.append("\t height : " + map.get(prefix + ExporterTools.WIDGET_HEIGHT) + ";" + "\n");
            }

        }
        if (map.containsKey(prefix + ExporterTools.WIDGET_WIDTH)) {
            try {
                // by default px format
                final int height = Integer.valueOf(map.get(prefix + ExporterTools.WIDGET_WIDTH));
                if (widget instanceof RichTextAreaFormField) {
                    cssContents.append("\t width :" + height + "px;height: auto; overflow: hidden;\n");
                } else {
                    cssContents.append("\t width :" + height + "px;\n");
                }
            } catch (final NumberFormatException e) {
                cssContents.append("\t width : " + map.get(prefix + ExporterTools.WIDGET_WIDTH) + ";" + "\n");

            }
        }

        // close Css rule
        cssContents.append("\n}\n");

    }

}
