/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.designer.ui.property.section.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.databinding.conversion.IConverter;
import org.junit.Test;


public class InfoMessageConverterTest {

    @Test
    public void should_infoConverter_return_processUIDesignerMessage() {
        final Pool pool = aPool().havingFormMapping(aFormMapping()).build();
        final IConverter converter = new InfoMessageConverter(FormMappingType.INTERNAL);
        assertThat(converter.convert(pool.getFormMapping())).isEqualTo(Messages.processUIDesignerInfo);
    }

    @Test
    public void should_infoConverter_return_stepUIDesignerMessage() {
        final Task task = aTask().havingFormMapping(aFormMapping()).build();
        final IConverter converter = new InfoMessageConverter(FormMappingType.INTERNAL);
        assertThat(converter.convert(task.getFormMapping())).isEqualTo(Messages.stepUIDesignerInfo);
    }

    @Test
    public void should_infoConverter_return_overviewUIDesignerInfo() {
        final Pool pool = aPool().havingOverviewFormMapping(aFormMapping()).build();
        final IConverter converter = new InfoMessageConverter(FormMappingType.INTERNAL);
        assertThat(converter.convert(pool.getOverviewFormMapping())).isEqualTo(Messages.overviewUIDesignerInfo);
    }

    @Test
    public void should_infoConverter_return_processURLMessage() {
        final Pool pool = aPool().havingFormMapping(aFormMapping()).build();
        final IConverter converter = new InfoMessageConverter(FormMappingType.URL);
        assertThat(converter.convert(pool.getFormMapping())).isEqualTo(Messages.processURLInfo);
    }

    @Test
    public void should_infoConverter_return_stepURLMessage() {
        final Task task = aTask().havingFormMapping(aFormMapping()).build();
        final IConverter converter = new InfoMessageConverter(FormMappingType.URL);
        assertThat(converter.convert(task.getFormMapping())).isEqualTo(Messages.stepURLInfo);
    }

    @Test
    public void should_infoConverter_return_overviewURLInfo() {
        final Pool pool = aPool().havingOverviewFormMapping(aFormMapping()).build();
        final IConverter converter = new InfoMessageConverter(FormMappingType.URL);
        assertThat(converter.convert(pool.getOverviewFormMapping())).isEqualTo(Messages.overviewURLInfo);
    }

}
