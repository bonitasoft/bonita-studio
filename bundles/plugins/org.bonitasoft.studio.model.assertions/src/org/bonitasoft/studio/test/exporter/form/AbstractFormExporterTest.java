/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.test.exporter.form;

import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author aurelie
 *
 */
public class AbstractFormExporterTest {

    protected Document createDocument(final String name, final boolean isMultiple) {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName(name);
        document.setMultiple(isMultiple);
        return document;
    }

    protected FileWidget createFileWidget(final FileWidgetInputType type) {
        final FileWidget fileWidget = FormFactory.eINSTANCE.createFileWidget();
        fileWidget.setInputType(type);
        return fileWidget;

    }

}
