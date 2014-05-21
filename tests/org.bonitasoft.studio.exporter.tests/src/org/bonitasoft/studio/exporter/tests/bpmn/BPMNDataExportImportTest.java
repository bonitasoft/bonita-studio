/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.exporter.tests.bpmn;

import java.io.IOException;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.XMLData;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TAssignment;
import org.omg.spec.bpmn.model.TDataObject;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TProperty;
import org.omg.spec.bpmn.model.TRootElement;

/**
 * @author Aurelien Pupier
 *
 */
public class BPMNDataExportImportTest extends TestCase {

    public void testProcessTextData() throws ExecutionException, IOException{
        final String structureRef = "java.lang.String";
        Data data = createTextDataSample(structureRef);
        String dataType = DataTypeLabels.stringDataType;

        DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareProcessData(data, mainProcess);
    }

    public void testProcessIntegerData() throws ExecutionException, IOException{
        final String structureRef = "java.lang.Integer";
        Data data = createIntegerDataSample(structureRef);
        String dataType = DataTypeLabels.integerDataType;

        DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareProcessData(data, mainProcess);
    }

    public void testProcessDoubleData() throws ExecutionException, IOException{
        Data data = createDoubleDataSample();
        String dataType = DataTypeLabels.doubleDataType;

        DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(data.getDefaultValue().getReturnType(), model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareProcessData(data, mainProcess);
    }

    protected Data createDoubleDataSample() {
        Data data = ProcessFactory.eINSTANCE.createData();
        Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        textDefaultValue.setName("Double data sample");
        textDefaultValue.setReturnType(Double.class.getName());
        textDefaultValue.setContent("12.3");
        textDefaultValue.setType(ExpressionConstants.CONSTANT_TYPE);
        data.setDefaultValue(textDefaultValue);
        return data;
    }

    public void testProcessBooleanData() throws ExecutionException, IOException{
        final String structureRef = "java.lang.Boolean";
        Data data = createBooleanDataSample(structureRef);
        String dataType = DataTypeLabels.booleanDataType;

        DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareProcessData(data, mainProcess);
    }

    public void testProcessDateData() throws ExecutionException, IOException{
        Data data = ProcessFactory.eINSTANCE.createData();
        Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        final String structureRef = "java.util.Date";
        textDefaultValue.setName("date data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("Now");
        textDefaultValue.setType(ExpressionConstants.CONSTANT_TYPE);
        data.setDefaultValue(textDefaultValue);
        String dataType = DataTypeLabels.dateDataType;

        DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareProcessData(data, mainProcess);
    }

    public void testProcessJavaData() throws ExecutionException, IOException{
        final String structureRef = "java.util.List";
        JavaObjectData data = createJavaDataSample(structureRef);
        String dataType = DataTypeLabels.javaDataType;

        DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure(structureRef, model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareProcessData(data, mainProcess);
    }

    public void testProcessXMLData() throws ExecutionException, IOException{
        final String structureRef = "java.util.List";
        XMLData data = ProcessFactory.eINSTANCE.createXMLData();
        Expression xmlDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        final String nameSpace = "http://aa";
        data.setNamespace(nameSpace);
        data.setName("zzz");
        data.setType("xmlDataTestType");
        xmlDefaultValue.setName("xml data sample");
        xmlDefaultValue.setReturnType(structureRef);
        xmlDefaultValue.setContent("myXpath");
        xmlDefaultValue.setType(ExpressionConstants.XPATH_TYPE);
        data.setDefaultValue(xmlDefaultValue);
        String dataType = DataTypeLabels.xmlDataType;

        DocumentRoot model2 = exportToBPMNProcessWithData(data, dataType);
        checkDataExistAtProcessLevelWithGoodStructure("xmlDataTestType", model2);
        for(TRootElement rootElement : model2.getDefinitions().getRootElement()){
            if(rootElement instanceof TItemDefinition
                    && rootElement.getId().equals("zzz")){
                assertEquals("Namespace has been lost", nameSpace, ((TItemDefinition) rootElement).getStructureRef().getNamespaceURI());
            }
        }

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareProcessData(data, mainProcess);
    }

    public void testStepTransientTextData() throws ExecutionException, IOException{
        final String structureRef = "java.lang.String";
        Data data = createTextDataSample(structureRef);
        data.setTransient(true);
        String dataType = DataTypeLabels.stringDataType;

        DocumentRoot model2 = exportToBPMNProcessWithStepData(data, dataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(),  model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareStepTransientData(data, mainProcess);
    }

    public void testStepTransientXMLData() throws ExecutionException, IOException{
        final String structureRef = "java.util.List";
        XMLData data = ProcessFactory.eINSTANCE.createXMLData();
        Expression xmlDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        final String nameSpace = "http://aa";
        data.setNamespace(nameSpace);
        data.setName("zzz");
        data.setType("xmlDataTestType");
        data.setTransient(true);
        xmlDefaultValue.setName("xml data sample");
        xmlDefaultValue.setReturnType(structureRef);
        xmlDefaultValue.setContent("myXPath");
        xmlDefaultValue.setType(ExpressionConstants.XPATH_TYPE);
        data.setDefaultValue(xmlDefaultValue);
        String dataType = DataTypeLabels.xmlDataType;

        DocumentRoot model2 = exportToBPMNProcessWithStepData(data, dataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(),  model2);
        for(TRootElement rootElement : model2.getDefinitions().getRootElement()){
            if(rootElement instanceof TItemDefinition
                    && rootElement.getId().equals("zzz")){
                assertEquals("Namespace has been lost", nameSpace, ((TItemDefinition) rootElement).getStructureRef().getNamespaceURI());
            }
        }

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareStepTransientData(data, mainProcess);
    }

    public void testStepTransientTextDataWithScriptInitialValue() throws ExecutionException, IOException{
        final String structureRef = "java.lang.String";
        Data data = ProcessFactory.eINSTANCE.createData();
        Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        textDefaultValue.setName("text data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("\"valuetTest\"");
        textDefaultValue.setInterpreter(ExpressionConstants.GROOVY);
        textDefaultValue.setType(ExpressionConstants.SCRIPT_TYPE);
        data.setDefaultValue(textDefaultValue);
        data.setTransient(true);
        String dataType = DataTypeLabels.stringDataType;

        DocumentRoot model2 = exportToBPMNProcessWithStepData(data, dataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(),  model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareStepTransientData(data, mainProcess);
    }

    public void testStepTransientFloatData() throws ExecutionException, IOException{
        Data data = createDoubleDataSample();
        data.setTransient(true);
        String dataType = DataTypeLabels.floatDataType;

        DocumentRoot model2 = exportToBPMNProcessWithStepData(data, dataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(),  model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);

        compareStepTransientData(data, mainProcess);
    }

    public void testStepTransientIntegerData() throws ExecutionException, IOException{
        final String structureRef = "java.lang.Long";
        Data data = createIntegerDataSample(structureRef);
        String dataType = DataTypeLabels.integerDataType;
        data.setTransient(true);

        DocumentRoot model2 = exportToBPMNProcessWithStepData(data, dataType);
        checkPropertyExistWithGoodStructure(data.getDefaultValue(),  model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepTransientData(data, mainProcess);
    }

    public void testStepTransientJavaData() throws ExecutionException, IOException{
        final String structureRef = "java.util.List";
        JavaObjectData initialData = createJavaDataSample(structureRef);
        initialData.setTransient(true);
        String dataType = DataTypeLabels.javaDataType;

        DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkPropertyExistWithGoodStructure(initialData.getDefaultValue(),  model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepTransientData(initialData, mainProcess);
    }

    public void testStepTransientBooleanData() throws ExecutionException, IOException{
        final String structureRef = "java.lang.Boolean";
        Data initialData = createBooleanDataSample(structureRef);
        initialData.setTransient(true);
        String dataType = DataTypeLabels.booleanDataType;

        DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkPropertyExistWithGoodStructure(initialData.getDefaultValue(),  model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepTransientData(initialData, mainProcess);
    }

    public void testStepBooleanData() throws ExecutionException, IOException{
        final String structureRef = "java.lang.Boolean";
        Data initialData = createBooleanDataSample(structureRef);
        String dataType = DataTypeLabels.booleanDataType;

        DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkDataExistAtProcessLevelWithGoodStructureButNotInitialized(structureRef,  model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepData(initialData, mainProcess);
    }

    public void testStepJavaData() throws ExecutionException, IOException{
        final String structureRef = "java.util.List";
        JavaObjectData initialData = createJavaDataSample(structureRef);
        String dataType = DataTypeLabels.javaDataType;

        DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkDataExistAtProcessLevelWithGoodStructureButNotInitialized(structureRef,  model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepData(initialData, mainProcess);
    }

    public void testStepIntegerData() throws ExecutionException, IOException{
        final String structureRef = "java.lang.Integer";
        Data initialData = createIntegerDataSample(structureRef);
        String dataType = DataTypeLabels.integerDataType;

        DocumentRoot model2 = exportToBPMNProcessWithStepData(initialData, dataType);
        checkDataExistAtProcessLevelWithGoodStructureButNotInitialized(structureRef,  model2);

        MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareStepData(initialData, mainProcess);
    }

    private void compareStepData(Data initialData, MainProcess mainProcess) {
        compareProcessData(initialData, mainProcess);//Step data jumped to process level...
    }

    protected void compareStepTransientData(Data data, MainProcess mainProcess) {
        for(Element element : ModelHelper.getAllProcesses(mainProcess).get(0).getElements()){
            if(element instanceof Task){
                Data reImportedData = ((Task) element).getData().get(0);
                compareDatas(data, reImportedData, true);
            }
        }
    }

    private void compareProcessData(Data data, MainProcess mainProcess) {
        compareDatas(data, ModelHelper.getAllProcesses(mainProcess).get(0).getData().get(0), false);
    }

    protected JavaObjectData createJavaDataSample(final String structureRef) {
        JavaObjectData initialData = ProcessFactory.eINSTANCE.createJavaObjectData();
        initialData.setClassName(structureRef);
        Expression javDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        javDefaultValue.setName("java data sample");
        javDefaultValue.setReturnType(structureRef);
        final String defaultValue = "new ArrayList();";
        javDefaultValue.setContent(defaultValue);
        javDefaultValue.setType(ExpressionConstants.JAVA_TYPE);
        initialData.setDefaultValue(javDefaultValue);
        return initialData;
    }

    protected Data createBooleanDataSample(final String structureRef) {
        Data data = ProcessFactory.eINSTANCE.createData();
        Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        textDefaultValue.setName("boolean data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("true");
        textDefaultValue.setType(ExpressionConstants.CONSTANT_TYPE);
        data.setDefaultValue(textDefaultValue);
        return data;
    }

    protected Data createTextDataSample(final String structureRef) {
        Data data = ProcessFactory.eINSTANCE.createData();
        Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        textDefaultValue.setName("text data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("valuetTest");
        textDefaultValue.setType(ExpressionConstants.CONSTANT_TYPE);
        data.setDefaultValue(textDefaultValue);
        return data;
    }

    protected Data createIntegerDataSample(final String structureRef) {
        Data data = ProcessFactory.eINSTANCE.createData();
        Expression textDefaultValue = ExpressionFactory.eINSTANCE.createExpression();
        textDefaultValue.setName("integer data sample");
        textDefaultValue.setReturnType(structureRef);
        textDefaultValue.setContent("12");
        textDefaultValue.setType(ExpressionConstants.CONSTANT_TYPE);
        data.setDefaultValue(textDefaultValue);
        return data;
    }

    private void compareDatas(Data initialData, Data reImportedData, boolean checkDefaultValue) {
        assertEquals("Not the same type of data (Java)", initialData instanceof JavaObjectData, reImportedData instanceof JavaObjectData);
        assertEquals("Not the same type of data (XML)", initialData instanceof XMLData, reImportedData instanceof XMLData);


        if(initialData instanceof JavaObjectData){
            assertEquals("Java classname not correct",((JavaObjectData) initialData).getClassName(), ((JavaObjectData)reImportedData).getClassName());
        }
        if(initialData instanceof XMLData){
            assertEquals("Namespace not correct",((XMLData) initialData).getNamespace(), ((XMLData) reImportedData).getNamespace());
            assertEquals("Namespace not correct",((XMLData) initialData).getType(), ((XMLData) reImportedData).getType());
        }

        if(checkDefaultValue){
            Expression initialDefaultValueExpression = initialData.getDefaultValue();
            Expression reImportedDefaultValueExpression = reImportedData.getDefaultValue();
            assertEquals("Return type is not correct",initialDefaultValueExpression.getReturnType(),reImportedDefaultValueExpression.getReturnType());
            assertEquals("Type is not correct",initialDefaultValueExpression.getType(),reImportedDefaultValueExpression.getType());
            assertEquals("Interpreter is not correct",initialDefaultValueExpression.getInterpreter(),reImportedDefaultValueExpression.getInterpreter());
            assertEquals("Content is not correct",initialDefaultValueExpression.getContent(),reImportedDefaultValueExpression.getContent());
        }
        assertEquals("Datatype is not correct",initialData.getDataType().getName(), reImportedData.getDataType().getName());
        assertEquals("Interpreter is not correct",initialData.getDefaultValue().getInterpreter(), reImportedData.getDefaultValue().getInterpreter());
    }

    private void checkPropertyExistWithGoodStructure(Expression defaultValueExpression, DocumentRoot model2) {
        for(TRootElement rootElement : model2.getDefinitions().getRootElement()){
            if(rootElement instanceof TProcess){
                for (TFlowElement fe : ((TProcess) rootElement).getFlowElement()) {
                    if(fe instanceof TActivity){
                        TProperty property = ((TActivity) fe).getProperty().get(0);
                        property.getItemSubjectRef().getLocalPart();
                        TAssignment assignment = ((TActivity) fe).getDataInputAssociation().get(0).getAssignment().get(0);
                        assertEquals(defaultValueExpression.getContent(),assignment.getFrom().getMixed().get(0).getValue());
                    }
                }
            }
        }
    }

    protected void checkDataExistAtProcessLevelWithGoodStructure(final String structureRef, DocumentRoot model2) {
        String dataObjectItemSubjectRef = "";
        String dataInputItemSubjectRef = "";
        TItemDefinition itemDef = null;
        for(TRootElement rootElement : model2.getDefinitions().getRootElement()){
            if(rootElement instanceof TProcess){
                for (TFlowElement fe : ((TProcess) rootElement).getFlowElement()) {
                    if(fe instanceof TDataObject){
                        dataObjectItemSubjectRef = ((TDataObject) fe).getItemSubjectRef().getLocalPart();
                        break;
                    }
                }
                dataInputItemSubjectRef = ((TProcess) rootElement).getIoSpecification().getDataInput().get(0).getItemSubjectRef().getLocalPart();
            } else if(rootElement instanceof TItemDefinition){
                itemDef = (TItemDefinition) rootElement;
            }
        }

        assertTrue("A reference name is not good", dataObjectItemSubjectRef.equals(dataInputItemSubjectRef) && dataObjectItemSubjectRef.equals(itemDef.getId()));
        assertEquals("The exported type is not the good one", structureRef, itemDef.getStructureRef().getLocalPart());
    }

    protected void checkDataExistAtProcessLevelWithGoodStructureButNotInitialized(final String structureRef, DocumentRoot model2) {
        String dataObjectItemSubjectRef = "";
        TItemDefinition itemDef = null;
        for(TRootElement rootElement : model2.getDefinitions().getRootElement()){
            if(rootElement instanceof TProcess){
                for (TFlowElement fe : ((TProcess) rootElement).getFlowElement()) {
                    if(fe instanceof TDataObject){
                        dataObjectItemSubjectRef = ((TDataObject) fe).getItemSubjectRef().getLocalPart();
                        break;
                    }
                }
                assertTrue("We shouldn't have data initialized at process level because there were step data",((TProcess) rootElement).getIoSpecification().getDataInput().isEmpty());
            } else if(rootElement instanceof TItemDefinition
                    && !rootElement.getId().equals("bonitaConnectorInput")
                    && !rootElement.getId().equals("bonitaConnectorOutput")){
                itemDef = (TItemDefinition) rootElement;
            }
        }

        assertTrue("A reference name is not good", dataObjectItemSubjectRef.equals(itemDef.getId()));
        assertEquals("The exported type is not the good one", structureRef, itemDef.getStructureRef().getLocalPart());
    }

    private DocumentRoot exportToBPMNProcessWithStepData(Data data, String dataType) throws ExecutionException, IOException {
        final NewDiagramCommandHandler newDiagramCommandHandler = new NewDiagramCommandHandler();
        newDiagramCommandHandler.execute(null);
        final DiagramFileStore newDiagramFileStore = newDiagramCommandHandler.getNewDiagramFileStore();
        AbstractProcess abstractProcess = newDiagramFileStore.getProcesses().get(0);
        for(Element element : abstractProcess.getElements()){
            if(element instanceof Lane){
                for(Element child : ((Lane) element).getElements()){
                    if(child instanceof Task){
                        final EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(child);
                        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, child, ProcessPackage.Literals.DATA_AWARE__DATA, data));
                    }
                }
            }
        }
        dataType = NamingUtils.convertToId(dataType);
        for(DataType dt : newDiagramFileStore.getContent().getDatatypes()){
            if(dataType.equals(dt.getName())){
                final EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(data);
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, data, ProcessPackage.Literals.DATA__DATA_TYPE, dt));
                break;
            }
        }
        newDiagramFileStore.getOpenedEditor().doSave(Repository.NULL_PROGRESS_MONITOR);
        return BPMNTestUtil.exportToBpmn(newDiagramFileStore);
    }


    protected DocumentRoot exportToBPMNProcessWithData(Data data, String dataType) throws ExecutionException, IOException {
        final NewDiagramCommandHandler newDiagramCommandHandler = new NewDiagramCommandHandler();
        newDiagramCommandHandler.execute(null);
        final DiagramFileStore newDiagramFileStore = newDiagramCommandHandler.getNewDiagramFileStore();
        AbstractProcess abstractProcess = newDiagramFileStore.getProcesses().get(0);
        final EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(abstractProcess);
        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, abstractProcess, ProcessPackage.Literals.DATA_AWARE__DATA, data));
        dataType = NamingUtils.convertToId(dataType);
        for(DataType dt : newDiagramFileStore.getContent().getDatatypes()){
            if(dataType.equals(dt.getName())){
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, data, ProcessPackage.Literals.DATA__DATA_TYPE, dt));
                break;
            }
        }
        newDiagramFileStore.getOpenedEditor().doSave(Repository.NULL_PROGRESS_MONITOR);
        return BPMNTestUtil.exportToBpmn(newDiagramFileStore);
    }


}
