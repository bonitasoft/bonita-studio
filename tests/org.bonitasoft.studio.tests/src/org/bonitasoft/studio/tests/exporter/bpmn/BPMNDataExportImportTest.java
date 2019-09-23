/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.exporter.bpmn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.studio.model.process.builders.DoubleDataTypeBuilder.aDoubleDataType;
import static org.bonitasoft.studio.model.process.builders.XMLDataBuilder.anXMLData;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.model.process.builders.XMLDataTypeBuilder;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Test;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TAssignment;
import org.omg.spec.bpmn.model.TDataObject;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TProperty;
import org.omg.spec.bpmn.model.TRootElement;

public class BPMNDataExportImportTest {

    @After
    public void closeEditor() throws Exception {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
    }

    @Test
    public void testProcessTextData() throws IOException {
        final String structureRef = "java.lang.String";
        final Data data = createTextDataSample(structureRef);
        final String dataType = DataTypeLabels.stringDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareProcessData(data, mainProcess);
    }

    @Test
    public void testProcessIntegerData() throws IOException {
        final String structureRef = "java.lang.Integer";
        final Data data = createIntegerDataSample(structureRef);
        final String dataType = DataTypeLabels.integerDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareProcessData(data, mainProcess);
    }

    @Test
    public void testProcessDoubleData() throws IOException {
        final Data data = createDoubleDataSample();
        final String dataType = DataTypeLabels.doubleDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(data.getDefaultValue().getReturnType(), model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareProcessData(data, mainProcess);
    }

    protected Data createDoubleDataSample() {
        return aData().havingDataType(aDoubleDataType())
                .havingDefaultValue(aConstantExpression()
                        .withName("12.3")
                        .withContent("12.3")
                        .withReturnType(Double.class.getName()))
                .build();
    }

    @Test
    public void testProcessBooleanData() throws IOException {
        final String structureRef = "java.lang.Boolean";
        final Data data = createBooleanDataSample(structureRef);
        final String dataType = DataTypeLabels.booleanDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareProcessData(data, mainProcess);
    }

    @Test
    public void testProcessDateData() throws IOException {
        final Data data = ProcessFactory.eINSTANCE.createData();
        final Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        final String structureRef = "java.util.Date";
        textDefaultValue.setName("date data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("Now");
        textDefaultValue.setType(ExpressionConstants.CONSTANT_TYPE);
        data.setDefaultValue(textDefaultValue);
        final String dataType = DataTypeLabels.dateDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareProcessData(data, mainProcess);
    }

    @Test
    public void testProcessJavaData() throws IOException {
        final String structureRef = "java.util.List";
        final JavaObjectData data = createJavaDataSample(structureRef);
        final String dataType = DataTypeLabels.javaDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareProcessData(data, mainProcess);
    }

    @Test
    public void testProcessXMLData() throws IOException {
        final String structureRef = "java.util.List";
        final XMLData data = ProcessFactory.eINSTANCE.createXMLData();
        final Expression xmlDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        final String nameSpace = "http://aa";
        data.setNamespace(nameSpace);
        data.setName("zzz");
        data.setType("xmlDataTestType");
        xmlDefaultValue.setName("xml data sample");
        xmlDefaultValue.setReturnType(structureRef);
        xmlDefaultValue.setContent("myXpath");
        xmlDefaultValue.setType(ExpressionConstants.XPATH_TYPE);
        data.setDefaultValue(xmlDefaultValue);
        final String dataType = DataTypeLabels.xmlDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure("xmlDataTestType", model2);
        for (final TRootElement rootElement : model2.getDefinitions().getRootElement()) {
            if (rootElement instanceof TItemDefinition && rootElement.getId().equals("zzz")) {
                assertEquals("Namespace has been lost", nameSpace,
                        ((TItemDefinition) rootElement).getStructureRef().getNamespaceURI());
            }
        }

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareProcessData(data, mainProcess);
    }

    @Test
    public void testStepTransientTextData() throws IOException {
        final String structureRef = "java.lang.String";
        final Data data = createTextDataSample(structureRef);
        data.setTransient(true);
        final String dataType = DataTypeLabels.stringDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(data, dataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(), model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareStepTransientData(data, mainProcess);
    }

    @Test
    public void testStepTransientXMLData() throws IOException {
        XMLData data = anXMLData()
                .havingDataType(XMLDataTypeBuilder.create())
                .withName("zzz")
                .withNamespace("http://aa")
                .isTransient()
                .withElementType("xmlDataTestType")
                .havingDefaultValue(anExpression()
                        .withName("xml data sample")
                        .withContent("myXPath")
                        .withReturnType("java.util.List")
                        .withExpressionType(ExpressionConstants.XPATH_TYPE))
                .build();

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(data, DataTypeLabels.xmlDataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(), model2);
        for (final TRootElement rootElement : model2.getDefinitions().getRootElement()) {
            if (rootElement instanceof TItemDefinition && rootElement.getId().equals("zzz")) {
                assertEquals("Namespace has been lost", "http://aa",
                        ((TItemDefinition) rootElement).getStructureRef().getNamespaceURI());
            }
        }

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareStepTransientData(data, mainProcess);
    }

    @Test
    public void testStepTransientTextDataWithScriptInitialValue() throws IOException {
        final String structureRef = "java.lang.String";
        final Data data = ProcessFactory.eINSTANCE.createData();
        final Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        textDefaultValue.setName("text data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("\"valuetTest\"");
        textDefaultValue.setInterpreter(ExpressionConstants.GROOVY);
        textDefaultValue.setType(ExpressionConstants.SCRIPT_TYPE);
        data.setDefaultValue(textDefaultValue);
        data.setTransient(true);
        final String dataType = DataTypeLabels.stringDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(data, dataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(), model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareStepTransientData(data, mainProcess);
    }

    @Test
    public void testStepTransientDoubleData() throws IOException {
        final Data data = createDoubleDataSample();
        data.setTransient(true);
        final String dataType = DataTypeLabels.doubleDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(data, dataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(), model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareStepTransientData(data, mainProcess);
    }

    @Test
    public void testStepTransientIntegerData() throws IOException {
        final String structureRef = "java.lang.Long";
        final Data data = createIntegerDataSample(structureRef);
        final String dataType = DataTypeLabels.integerDataType;
        data.setTransient(true);

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(data, dataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(), model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepTransientData(data, mainProcess);
    }

    @Test
    public void testStepTransientJavaData() throws IOException {
        final String structureRef = "java.util.List";
        final JavaObjectData initialData = createJavaDataSample(structureRef);
        initialData.setTransient(true);
        final String dataType = DataTypeLabels.javaDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkPropertyExistWithGoodStructure(initialData.getDefaultValue(), model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepTransientData(initialData, mainProcess);
    }

    @Test
    public void testStepTransientBooleanData() throws IOException {
        final String structureRef = "java.lang.Boolean";
        final Data initialData = createBooleanDataSample(structureRef);
        initialData.setTransient(true);
        final String dataType = DataTypeLabels.booleanDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkPropertyExistWithGoodStructure(initialData.getDefaultValue(), model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepTransientData(initialData, mainProcess);
    }

    @Test
    public void testStepBooleanData() throws IOException {
        final String structureRef = "java.lang.Boolean";
        final Data initialData = createBooleanDataSample(structureRef);
        final String dataType = DataTypeLabels.booleanDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkDataExistAtProcessLevelWithGoodStructureButNotInitialized(structureRef, model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepData(initialData, mainProcess);
    }

    public void testStepJavaData() throws IOException {
        final String structureRef = "java.util.List";
        final JavaObjectData initialData = createJavaDataSample(structureRef);
        final String dataType = DataTypeLabels.javaDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkDataExistAtProcessLevelWithGoodStructureButNotInitialized(structureRef, model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepData(initialData, mainProcess);
    }

    @Test
    public void testStepIntegerData() throws IOException {
        final String structureRef = "java.lang.Integer";
        final Data initialData = createIntegerDataSample(structureRef);
        final String dataType = DataTypeLabels.integerDataType;

        final DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkDataExistAtProcessLevelWithGoodStructureButNotInitialized(structureRef, model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepData(initialData, mainProcess);
    }

    private void compareStepData(final Data initialData, final MainProcess mainProcess) {
        compareProcessData(initialData, mainProcess);//Step data jumped to process level...
    }

    protected void compareStepTransientData(final Data data, final MainProcess mainProcess) {
        for (final Element element : ModelHelper.getAllProcesses(mainProcess).get(0).getElements()) {
            if (element instanceof Task) {
                final Data reImportedData = ((Task) element).getData().get(0);
                compareDatas(data, reImportedData, true);
            }
        }
    }

    private void compareProcessData(final Data data, final MainProcess mainProcess) {
        compareDatas(data, ModelHelper.getAllProcesses(mainProcess).get(0).getData().get(0), false);
    }

    protected JavaObjectData createJavaDataSample(final String structureRef) {
        final JavaObjectData initialData = ProcessFactory.eINSTANCE.createJavaObjectData();
        initialData.setClassName(structureRef);
        final Expression javDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        javDefaultValue.setName("java data sample");
        javDefaultValue.setReturnType(structureRef);
        final String defaultValue = "new ArrayList();";
        javDefaultValue.setContent(defaultValue);
        javDefaultValue.setType(ExpressionConstants.JAVA_TYPE);
        initialData.setDefaultValue(javDefaultValue);
        return initialData;
    }

    protected Data createBooleanDataSample(final String structureRef) {
        final Data data = ProcessFactory.eINSTANCE.createData();
        final Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        textDefaultValue.setName("boolean data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("true");
        textDefaultValue.setType(ExpressionConstants.CONSTANT_TYPE);
        data.setDefaultValue(textDefaultValue);
        return data;
    }

    protected Data createTextDataSample(final String structureRef) {
        final Data data = ProcessFactory.eINSTANCE.createData();
        final Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        textDefaultValue.setName("text data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("valuetTest");
        textDefaultValue.setType(ExpressionConstants.CONSTANT_TYPE);
        data.setDefaultValue(textDefaultValue);
        return data;
    }

    protected Data createIntegerDataSample(final String structureRef) {
        final Data data = ProcessFactory.eINSTANCE.createData();
        final Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        textDefaultValue.setName("integer data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("12");
        textDefaultValue.setType(ExpressionConstants.CONSTANT_TYPE);
        data.setDefaultValue(textDefaultValue);
        return data;
    }

    private void compareDatas(final Data initialData, final Data reImportedData, final boolean checkDefaultValue) {
        assertEquals("Not the same type of data (Java)", initialData instanceof JavaObjectData,
                reImportedData instanceof JavaObjectData);
        assertEquals("Not the same type of data (XML)", initialData instanceof XMLData,
                reImportedData instanceof XMLData);

        if (initialData instanceof JavaObjectData) {
            assertEquals("Java classname not correct", ((JavaObjectData) initialData).getClassName(),
                    ((JavaObjectData) reImportedData).getClassName());
        }
        if (initialData instanceof XMLData) {
            assertEquals("Namespace not correct", ((XMLData) initialData).getNamespace(),
                    ((XMLData) reImportedData).getNamespace());
            assertEquals("Namespace not correct", ((XMLData) initialData).getType(),
                    ((XMLData) reImportedData).getType());
        }

        if (checkDefaultValue) {
            final Expression initialDefaultValueExpression = initialData.getDefaultValue();
            final Expression reImportedDefaultValueExpression = reImportedData.getDefaultValue();
            assertEquals("Return type is not correct", initialDefaultValueExpression.getReturnType(),
                    reImportedDefaultValueExpression.getReturnType());
            assertEquals("Type is not correct", initialDefaultValueExpression.getType(),
                    reImportedDefaultValueExpression.getType());
            assertEquals("Interpreter is not correct", initialDefaultValueExpression.getInterpreter(),
                    reImportedDefaultValueExpression.getInterpreter());
            assertEquals("Content is not correct", initialDefaultValueExpression.getContent(),
                    reImportedDefaultValueExpression.getContent());
        }
        assertEquals("Datatype is not correct", initialData.getDataType().getName(),
                reImportedData.getDataType().getName());
        assertEquals("Interpreter is not correct", initialData.getDefaultValue().getInterpreter(),
                reImportedData.getDefaultValue().getInterpreter());
    }

    private void checkPropertyExistWithGoodStructure(final Expression defaultValueExpression,
            final DocumentRoot model2) {
        for (final TRootElement rootElement : model2.getDefinitions().getRootElement()) {
            if (rootElement instanceof TProcess) {
                for (final TFlowElement fe : ((TProcess) rootElement).getFlowElement()) {
                    if (fe instanceof TActivity) {
                        final TProperty property = ((TActivity) fe).getProperty().get(0);
                        property.getItemSubjectRef().getLocalPart();
                        final TAssignment assignment = ((TActivity) fe).getDataInputAssociation().get(0).getAssignment()
                                .get(0);
                        assertEquals(defaultValueExpression.getContent(),
                                assignment.getFrom().getMixed().get(0).getValue());
                    }
                }
            }
        }
    }

    protected void checkDataExistAtProcessLevelWithGoodStructure(final String structureRef, final DocumentRoot model2) {
        String dataObjectItemSubjectRef = "";
        String dataInputItemSubjectRef = "";
        TItemDefinition itemDef = null;
        for (final TRootElement rootElement : model2.getDefinitions().getRootElement()) {
            if (rootElement instanceof TProcess) {
                for (final TFlowElement fe : ((TProcess) rootElement).getFlowElement()) {
                    if (fe instanceof TDataObject) {
                        dataObjectItemSubjectRef = ((TDataObject) fe).getItemSubjectRef().getLocalPart();
                        break;
                    }
                }
                dataInputItemSubjectRef = ((TProcess) rootElement).getIoSpecification().getDataInput().get(0)
                        .getItemSubjectRef().getLocalPart();
            } else if (rootElement instanceof TItemDefinition) {
                itemDef = (TItemDefinition) rootElement;
            }
        }

        assertTrue("A reference name is not good", dataObjectItemSubjectRef.equals(dataInputItemSubjectRef)
                && dataObjectItemSubjectRef.equals(itemDef.getId()));
        assertEquals("The exported type is not the good one", structureRef, itemDef.getStructureRef().getLocalPart());
    }

    protected void checkDataExistAtProcessLevelWithGoodStructureButNotInitialized(final String structureRef,
            final DocumentRoot model2) {
        String dataObjectItemSubjectRef = "";
        TItemDefinition itemDef = null;
        for (final TRootElement rootElement : model2.getDefinitions().getRootElement()) {
            if (rootElement instanceof TProcess) {
                for (final TFlowElement fe : ((TProcess) rootElement).getFlowElement()) {
                    if (fe instanceof TDataObject) {
                        dataObjectItemSubjectRef = ((TDataObject) fe).getItemSubjectRef().getLocalPart();
                        break;
                    }
                }
                assertTrue("We shouldn't have data initialized at process level because there were step data",
                        ((TProcess) rootElement).getIoSpecification().getDataInput().isEmpty());
            } else if (rootElement instanceof TItemDefinition
                    && !rootElement.getId().equals("bonitaConnectorInput")
                    && !rootElement.getId().equals("bonitaConnectorOutput")) {
                itemDef = (TItemDefinition) rootElement;
            }
        }

        assertTrue("A reference name is not good", dataObjectItemSubjectRef.equals(itemDef.getId()));
        assertEquals("The exported type is not the good one", structureRef, itemDef.getStructureRef().getLocalPart());
    }

    private DocumentRoot exportToBPMNProcessWithStepData(final Data data, String dataType) throws IOException {
        final NewDiagramCommandHandler newDiagramCommandHandler = new NewDiagramCommandHandler();
        final DiagramFileStore newDiagramFileStore = newDiagramCommandHandler.newDiagram();
        Resource emfResource = newDiagramFileStore.getEMFResource();
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(emfResource);
        final AbstractProcess abstractProcess = newDiagramFileStore.getProcesses().get(0);
        abstractProcess.getElements().stream()
                .filter(Lane.class::isInstance)
                .map(Lane.class::cast)
                .map(Lane::getElements)
                .flatMap(Collection::stream)
                .filter(Task.class::isInstance)
                .findFirst()
                .ifPresent(task -> editingDomain.getCommandStack().execute(
                        AddCommand.create(editingDomain, task, ProcessPackage.Literals.DATA_AWARE__DATA, data)));
        MainProcess mainProcess = newDiagramFileStore.getContent();
        mainProcess.getDatatypes().stream()
                .filter(dt -> Objects.equals(NamingUtils.convertToId(NamingUtils.convertToId(dataType)), dt.getName()))
                .findFirst()
                .ifPresent(dt -> editingDomain.getCommandStack()
                        .execute(SetCommand.create(editingDomain, data, ProcessPackage.Literals.DATA__DATA_TYPE, dt)));
        assertThat(data.getDataType())
                .overridingErrorMessage("No datatype '%s' set on data %s", NamingUtils.convertToId(dataType), data)
                .isNotNull();
        newDiagramFileStore.save(mainProcess);
        return BPMNTestUtil.exportToBpmn(emfResource);
    }

    protected DocumentRoot exportToBPMNProcessWithData(final Data data, final String dataType) throws IOException {
        final NewDiagramCommandHandler newDiagramCommandHandler = new NewDiagramCommandHandler();
        final DiagramFileStore newDiagramFileStore = newDiagramCommandHandler.newDiagram();
        Resource emfResource = newDiagramFileStore.getEMFResource();
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(emfResource);
        MainProcess mainProcess = newDiagramFileStore.getContent();
        final AbstractProcess abstractProcess = newDiagramFileStore.getProcesses().get(0);
        editingDomain.getCommandStack()
                .execute(AddCommand.create(editingDomain, abstractProcess, ProcessPackage.Literals.DATA_AWARE__DATA,
                        data));
        mainProcess.getDatatypes().stream()
                .filter(dt -> Objects.equals(NamingUtils.convertToId(dataType), dt.getName()))
                .findFirst()
                .ifPresent(dt -> editingDomain.getCommandStack()
                        .execute(SetCommand.create(editingDomain, data, ProcessPackage.Literals.DATA__DATA_TYPE, dt)));
        assertThat(data.getDataType())
                .overridingErrorMessage("No datatype '%s' set on data %s", NamingUtils.convertToId(dataType), data)
                .isNotNull();
        newDiagramFileStore.save(mainProcess);
        return BPMNTestUtil.exportToBpmn(emfResource);
    }

}
