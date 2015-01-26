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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.bonitasoft.engine.operation.OperatorType;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl;
import org.eclipse.emf.edapt.spi.migration.impl.ModelImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Florine Boudin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FileWidgetDocumentMigrationTest {


    private FileWidgetDocumentMigration migrationUnderTest;

    @Mock
    private ModelImpl model;

    @Mock
    private MetamodelImpl metamodel;

    @Mock
    private Instance fileWidgetInstance;

    @Mock
    private Instance operationInstance;
    @Mock
    private Instance operatorInstance;
    @Mock
    private Instance leftOperandInstance;
    @Mock
    private Instance documentInstance;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        migrationUnderTest = new FileWidgetDocumentMigration();


        leftOperandInstance.set("name", "");
        leftOperandInstance.set("content", "");

        operatorInstance.set("type", OperatorType.DOCUMENT_CREATE_UPDATE.toString());

        operationInstance.set("leftOperand", leftOperandInstance);
        operationInstance.set("operator", operatorInstance);

        fileWidgetInstance.set("action", operationInstance);
        fileWidgetInstance.set("document", documentInstance);

        final EList<Instance> fileWidgetList = new BasicEList<Instance>();
        fileWidgetList.add(fileWidgetInstance);


        final EList<Instance> docList = new BasicEList<Instance>();
        docList.add(documentInstance);

        when(fileWidgetInstance.get("action")).thenReturn(operationInstance);

        when(operationInstance.get("leftOperand")).thenReturn(leftOperandInstance);
        when(operationInstance.get("operator")).thenReturn(operatorInstance);

        when(leftOperandInstance.get("name")).thenReturn("");
        when(leftOperandInstance.get("content")).thenReturn("");
        when(leftOperandInstance.get("referencedElements")).thenReturn(new ArrayList<Instance>());

        when(model.getAllInstances("form.FileWidget")).thenReturn(fileWidgetList);
        when(model.getAllInstances("process.Document")).thenReturn(docList);

        when(documentInstance.get("name")).thenReturn("myDoc");

    }

    @Test
    public void should_migrateAfter_Set_FileWidget_operatorType_action_to_assignment_if_leftOperand_empty() throws Exception {
        when(leftOperandInstance.get("type")).thenReturn("");
        migrationUnderTest.migrateAfter(model, metamodel);
        verify(operatorInstance).set("type", OperatorType.ASSIGNMENT.toString());
    }

    @Test
    public void should_migrateAfter_getReferencedDocument_when_leftOperand_has_Document_type() throws Exception {
        when(leftOperandInstance.get("type")).thenReturn(ExpressionConstants.DOCUMENT_REF_TYPE.toString());
        when(leftOperandInstance.get("name")).thenReturn("myDoc");
        migrationUnderTest.migrateAfter(model, metamodel);
        verify(leftOperandInstance).add("referencedElements", documentInstance.copy());
    }

    @Test
    public void should_migrateBefore_RemoveExistingDocumentAttributeInFileWidget() throws Exception {
        when(fileWidgetInstance.get("document")).thenReturn(documentInstance);
        migrationUnderTest.migrateBefore(model, metamodel);
        verify(fileWidgetInstance).remove("document", documentInstance);

    }

}
