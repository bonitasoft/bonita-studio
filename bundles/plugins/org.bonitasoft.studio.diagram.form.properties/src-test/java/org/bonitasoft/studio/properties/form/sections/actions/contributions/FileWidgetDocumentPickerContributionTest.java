/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormFactory;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.junit.Test;


public class FileWidgetDocumentPickerContributionTest {

    @Test
    public void testCreateEnabledUrlWhenInputIsResourceStrategyTargetToModel() {
        final FileWidgetDocumentPickerContribution fileWidgetDocumentPickerContribution = new FileWidgetDocumentPickerContribution();
        final FileWidget fileWidget = FormFactory.eINSTANCE.createFileWidget();
        fileWidgetDocumentPickerContribution.setEObject(fileWidget);
        final UpdateValueStrategy strategy = fileWidgetDocumentPickerContribution.createEnabledUrlWhenInputIsResourceStrategyTargetToModel();

        Assertions.assertThat((Boolean) strategy.convert(FileWidgetInputType.RESOURCE)).isFalse();
        Assertions.assertThat((Boolean) strategy.convert(FileWidgetInputType.DOCUMENT)).isTrue();

        fileWidget.setDownloadOnly(true);
        Assertions.assertThat((Boolean) strategy.convert(FileWidgetInputType.DOCUMENT)).isFalse();
    }

    @Test
    public void testCreateEnabledStrategyForDownloadOnlyURLCase() {
        final FileWidgetDocumentPickerContribution fileWidgetDocumentPickerContribution = new FileWidgetDocumentPickerContribution();
        final FileWidget fileWidget = FormFactory.eINSTANCE.createFileWidget();
        fileWidgetDocumentPickerContribution.setEObject(fileWidget);
        final UpdateValueStrategy strategy = fileWidgetDocumentPickerContribution.createEnabledStrategyForDownloadOnlyURLCase();

        fileWidget.setInputType(FileWidgetInputType.RESOURCE);
        Assertions.assertThat((Boolean) strategy.convert(true)).isFalse();
        Assertions.assertThat((Boolean) strategy.convert(false)).isFalse();
        fileWidget.setInputType(FileWidgetInputType.DOCUMENT);
        Assertions.assertThat((Boolean) strategy.convert(true)).isFalse();
        Assertions.assertThat((Boolean) strategy.convert(false)).isTrue();
    }

    @Test
    public void testCreateEnabledStrategy() {
        final FileWidgetDocumentPickerContribution fileWidgetDocumentPickerContribution = new FileWidgetDocumentPickerContribution();
        final UpdateValueStrategy strategy = fileWidgetDocumentPickerContribution.createEnabledStrategy();
        Assertions.assertThat((Boolean) strategy.convert(true)).isFalse();
        Assertions.assertThat((Boolean) strategy.convert(false)).isTrue();
    }

}
