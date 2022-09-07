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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.gmf.tools.tree;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class EmptyContractInputMappingViewerFilterTest {

    private EmptyContractInputMappingViewerFilter emptyContractInputMappingViewerFilter;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        emptyContractInputMappingViewerFilter = new EmptyContractInputMappingViewerFilter();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_select_returns_true_if_contrat_input_mapping_has_data() throws Exception {
        final ContractInputMapping mapping = ProcessFactory.eINSTANCE.createContractInputMapping();
        mapping.setData(ProcessFactory.eINSTANCE.createData());
        assertThat(emptyContractInputMappingViewerFilter.select(null, null, mapping)).isTrue();
    }

    @Test
    public void should_select_returns_false_if_contrat_input_mapping_has_no_data() throws Exception {
        final ContractInputMapping mapping = ProcessFactory.eINSTANCE.createContractInputMapping();
        assertThat(emptyContractInputMappingViewerFilter.select(null, null, mapping)).isFalse();
    }

}
