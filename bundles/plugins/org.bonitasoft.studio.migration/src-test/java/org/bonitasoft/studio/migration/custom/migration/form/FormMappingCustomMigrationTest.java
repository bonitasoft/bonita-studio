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
package org.bonitasoft.studio.migration.custom.migration.form;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.migration.custom.migration.InstanceBuilder.aModel;
import static org.bonitasoft.studio.migration.custom.migration.InstanceBuilder.aStringDataInstance;
import static org.bonitasoft.studio.migration.custom.migration.InstanceBuilder.anInstance;

import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EEnumLiteral;
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
public class FormMappingCustomMigrationTest {

    private FormMappingCustomMigration formMappingCustomMigration;
    private Instance poolInstance;
    private Instance aTaskInstance;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        formMappingCustomMigration = new FormMappingCustomMigration();

        final Model model = aModel();
        final Type type = MigrationFactory.eINSTANCE.createType();
        type.setEClass(ProcessPackage.Literals.POOL);
        model.getTypes().add(type);
        final Type type2 = MigrationFactory.eINSTANCE.createType();
        type2.setEClass(ProcessPackage.Literals.TASK);
        model.getTypes().add(type2);
        final Type type3 = MigrationFactory.eINSTANCE.createType();
        type3.setEClass(ProcessPackage.Literals.FORM_MAPPING);
        model.getTypes().add(type3);
        poolInstance = anInstance(model.getType(ProcessPackage.Literals.POOL)).
                withFeature(ProcessPackage.Literals.ELEMENT__NAME, aStringDataInstance("MyPool"))
                .build();
        aTaskInstance = anInstance(model.getType(ProcessPackage.Literals.TASK))
                .withFeature(ProcessPackage.Literals.ELEMENT__NAME, aStringDataInstance("MyTask"))
                .withFeature(ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING, anInstance(model.getType(ProcessPackage.Literals.FORM_MAPPING)).build())
                .build();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_create_an_new_instance_of_FormMapping_on_all_pageflow() throws Exception {
        final Model aModel = aModel();
        formMappingCustomMigration.migrateAfter(aModel, aModel.getMetamodel());

        final Instance processFormMapping = poolInstance.get("formMapping");
        assertThat(processFormMapping).isNotNull();
        assertThat((EEnumLiteral)processFormMapping.get("type")).isEqualTo(aModel.getMetamodel().getEEnum("process.FormMappingType").getEEnumLiteral("LEGACY"));
        assertThat((Instance)processFormMapping.get("targetForm")).isNotNull();

        final Instance targetFormMapping = aTaskInstance.get("formMapping");
        assertThat(targetFormMapping).isNotNull();
        assertThat((FormMappingType)targetFormMapping.get("type")).isEqualTo(FormMappingType.INTERNAL);
    }
}
