/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms.adapters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.palette.DefaultElementNameProvider;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.exporter.ExporterService;
import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
import org.bonitasoft.studio.exporter.application.HtmlTemplateGenerator;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.properties.Activator;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class WidgetAddedOrRemoved extends AdapterImpl {

    private final Form form;
    private final DefaultElementNameProvider elementNameProvider;

    public WidgetAddedOrRemoved(final Form form) {
        this.form = form;
        elementNameProvider = new DefaultElementNameProvider();
    }

    @Override
    public void notifyChanged(final Notification notification) {
        // Listen for changes to features.
        switch (notification.getFeatureID(Form.class)) {
            case FormPackage.FORM__WIDGETS:
                handleNotificationOnFormWidgets(notification);
        }
    }

    private void handleNotificationOnFormWidgets(final Notification notification) {
        if (notification.getEventType() == Notification.ADD) {
            handleAddFormWidgetNotification(notification);
        } else if (notification.getEventType() == Notification.REMOVE) {
            handleRemoveFormWidgetNotification();
        }
    }

    private void handleRemoveFormWidgetNotification() {
        if (form.getHtmlTemplate() != null && form.getHtmlTemplate().getPath() != null
                && !form.getHtmlTemplate().getPath().isEmpty()) {
            // there is a template
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                            Messages.widgetRemovedWarning_title, Messages.widgetRemovedWarning_msg);
                }
            });
        }
    }

    private void handleAddFormWidgetNotification(final Notification notification) {
        final Widget widget = (Widget) notification.getNewValue();
        if (ModelHelper.formIsCustomized(form)) {
            BonitaStudioLog.info("Updating Custom Form Template. Adding widget: " + widget.getName(), Activator.PLUGIN_ID);
            final File file = WebTemplatesUtil.getFile(form.getHtmlTemplate().getPath());
            File tempFile = null;
            try (FileInputStream fis = new FileInputStream(file)) {
                tempFile = File.createTempFile("tempForm", ".html");
                final FileWriter fileWriter = new FileWriter(tempFile);
                addDivForAddedWidget(widget, fis, fileWriter);
                fileWriter.close();
                FileUtil.copy(tempFile, file);
                WebTemplatesUtil.refreshFile(form.getHtmlTemplate().getPath());
            } catch (final Exception e) {
                handleExceptionWhileAddingWidgetInTemplate(e);
            } finally {
                tempFile.delete();
            }
        }
    }

    private void handleExceptionWhileAddingWidgetInTemplate(final Exception e) {
        BonitaStudioLog.error(e);
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                new BonitaErrorDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.Error,
                        "Unexpected error", e).open();
            }
        });
    }

    private void addDivForAddedWidget(final Widget widget, final FileInputStream fis, final FileWriter fileWriter)
            throws IOException {
        final HtmlTemplateGenerator generator = (HtmlTemplateGenerator) ExporterService.getInstance()
                .getExporterService(SERVICE_TYPE.HtmlTemplateGenerator);
        String label = elementNameProvider.getNameFor(widget);
        label = NamingUtils.convertToId(label);
        int number = NamingUtils.getMaxElements(form, label);
        number++;
        label += number;
        generator.addDivInTemplate(label, fis, fileWriter);
    }
}
