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
package org.bonitasoft.studio.contract.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.operation.Operation;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class ContractMappingFactoryTest {

    private ContractMappingFactory contractMappingFactory;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contractMappingFactory = new ContractMappingFactory();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_createOperation_throw_IllegalArgumentException_for_null_mapping() throws Exception {
        contractMappingFactory.createOperation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_createOperation_throw_IllegalArgumentException_for_invalid_mapping() throws Exception {
        final ContractInputMapping mapping = ProcessFactory.eINSTANCE.createContractInputMapping();
        contractMappingFactory.createOperation(mapping);
    }

    @Test
    public void should_createOperation_create_a_new_instance_of_OperationBuilder() throws Exception {
        final ContractInputMapping mapping = ProcessFactory.eINSTANCE.createContractInputMapping();
        mapping.setData(createTextData("textData"));
        assertThat(contractMappingFactory.createOperation(mapping)).isNotNull().isInstanceOf(Operation.class);
    }

    private Data createTextData(final String name) {
        final Data textData = ProcessFactory.eINSTANCE.createData();
        textData.setName(name);
        textData.setDataType(ModelHelper.createStringDataType());
        return textData;
    }


}
