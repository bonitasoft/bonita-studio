/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmf.tools.tree;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.MainProcessBuilder.aMainProcess;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.MainProcess;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class MainProcessFormMappingViewerFilterTest {

    private MainProcessFormMappingViewerFilter mainProcessFormMappingViewerFilter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mainProcessFormMappingViewerFilter = new MainProcessFormMappingViewerFilter();
    }

    @Test
    public void should_select_form_mappings_contained_in_PageFlow() throws Exception {
        //Given
        final FormMapping aFormMappingInAPageflow = aFormMappingInAPageflow();

        //When
        final boolean isSelcted = mainProcessFormMappingViewerFilter.select(null, null, aFormMappingInAPageflow);

        //Then
        assertThat(isSelcted).isTrue();
    }

    @Test
    public void should_not_select_form_mappings_contained_in_MainProcess() throws Exception {
        //Given
        final FormMapping aFormMappingInAMainProcess = aFormMappingInAMainProcess();

        //When
        final boolean isSelcted = mainProcessFormMappingViewerFilter.select(null, null, aFormMappingInAMainProcess);

        //Then
        assertThat(isSelcted).isFalse();
    }

    private FormMapping aFormMappingInAPageflow() {
        return aPool().havingFormMapping(aFormMapping().havingTargetForm(anExpression().withContent("form-id"))).build().getFormMapping();
    }

    private FormMapping aFormMappingInAMainProcess() {
        final MainProcess mainProcess = aMainProcess().build();
        mainProcess.setFormMapping(aFormMapping().build());
        return mainProcess.getFormMapping();
    }
}
