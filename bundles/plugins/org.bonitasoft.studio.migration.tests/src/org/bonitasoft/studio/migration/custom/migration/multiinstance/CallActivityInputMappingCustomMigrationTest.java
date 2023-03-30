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
package org.bonitasoft.studio.migration.custom.migration.multiinstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.migration.custom.migration.InstanceBuilder.aModel;
import static org.bonitasoft.studio.migration.custom.migration.InstanceBuilder.aStringDataInstance;
import static org.bonitasoft.studio.migration.custom.migration.InstanceBuilder.anInstance;
import static org.bonitasoft.studio.migration.custom.migration.InstanceBuilder.clearModel;

import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.MigrationFactory;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class CallActivityInputMappingCustomMigrationTest {

    private CallActivityInputMappingCustomMigration callActivityInputMappingCustomMigration;
    private Instance inputMapping1;
    private Instance inputMapping2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        callActivityInputMappingCustomMigration = new CallActivityInputMappingCustomMigration();

        final Model model = aModel();
        final Type type = MigrationFactory.eINSTANCE.createType();
        type.setEClass(ProcessPackage.Literals.INPUT_MAPPING);
        model.getTypes().add(type);
        inputMapping1 = anInstance(model.getType(ProcessPackage.Literals.INPUT_MAPPING)).
                withFeature(ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE, aStringDataInstance("name"))
                .build();
        final Instance ageDataInstance = aStringDataInstance("age");
        ageDataInstance.set(ProcessPackage.Literals.DATA__DEFAULT_VALUE, null);
        inputMapping2 = anInstance(model.getType(ProcessPackage.Literals.INPUT_MAPPING))
                .withFeature(ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE, ageDataInstance)
                .build();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        clearModel();
    }

    @Test
    public void should_migrateBefore_stores_data_instances_from_processSource_of_InputMappings() throws Exception {
        final Model model = aModel();

        callActivityInputMappingCustomMigration.migrateBefore(model, model.getMetamodel());

        assertThat(callActivityInputMappingCustomMigration.getDataInstances()).containsKeys(inputMapping1.getUuid(), inputMapping2.getUuid());
    }

    @Test
    public void should_migrateAfter_convert_data_instances_to_expression_in_processSource_of_InputMappings() throws Exception {
        final Model model = aModel();

        callActivityInputMappingCustomMigration.migrateBefore(model, model.getMetamodel());
        callActivityInputMappingCustomMigration.migrateAfter(model, model.getMetamodel());

        assertThat((Instance)inputMapping1.get(ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE)).isNotNull().isInstanceOf(Instance.class);
        assertThat((Instance)inputMapping2.get(ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE)).isNotNull().isInstanceOf(Instance.class);

        assertThat(((Instance) inputMapping1.get(ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE)).getType().getEClass()).isEqualTo(
                ExpressionPackage.Literals.EXPRESSION);
        assertThat(((Instance) inputMapping2.get(ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE)).getType().getEClass()).isEqualTo(
                ExpressionPackage.Literals.EXPRESSION);

        //Data instances are deleted
        assertThat(callActivityInputMappingCustomMigration.getDataInstances().values()).hasSize(2).extracting("type").containsExactly(null, null);
    }
}
