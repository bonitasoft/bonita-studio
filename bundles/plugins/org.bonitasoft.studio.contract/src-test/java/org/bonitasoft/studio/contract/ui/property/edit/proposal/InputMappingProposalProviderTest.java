package org.bonitasoft.studio.contract.ui.property.edit.proposal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class InputMappingProposalProviderTest {

    private InputMappingProposalProvider inputMappingProposalProvider;

    @Before
    public void setUp() throws Exception {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        inputMappingProposalProvider = spy(new InputMappingProposalProvider(contractInput));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getProposals_returns_a_proposal_for_text_data_in_scope() throws Exception {
        final Data textData = ProcessFactory.eINSTANCE.createData();
        textData.setName("myTextData");
        final DataType dt = ProcessFactory.eINSTANCE.createStringType();
        dt.setName(Messages.StringType);
        textData.setDataType(dt);
        doReturn(Arrays.asList(textData)).when(inputMappingProposalProvider).getDataInScope();
        assertThat(inputMappingProposalProvider.getProposals("", 0)).hasSize(1);
        final IContentProposal[] proposals = inputMappingProposalProvider.getProposals("", 0);
        assertThat(proposals[0].getContent()).isEqualTo("myTextData");
    }

    @Test
    public void should_getProposals_returns_nothing_for_invalid_start_word() throws Exception {
        final Data textData = ProcessFactory.eINSTANCE.createData();
        textData.setName("myTextData");
        final DataType dt = ProcessFactory.eINSTANCE.createStringType();
        dt.setName(Messages.StringType);
        textData.setDataType(dt);
        doReturn(Arrays.asList(textData)).when(inputMappingProposalProvider).getDataInScope();
        assertThat(inputMappingProposalProvider.getProposals("myTx", 0)).hasSize(0);
        assertThat(inputMappingProposalProvider.getProposals("myTextData2", 0)).hasSize(0);
    }

    @Test
    public void should_getProposals_returns_multiple_proposals_for_java_data_in_scope() throws Exception {
        final JavaObjectData javaObjectData = ProcessFactory.eINSTANCE.createJavaObjectData();
        javaObjectData.setName("employee");
        javaObjectData.setClassName("com.model.Employee");
        final DataType dt = ProcessFactory.eINSTANCE.createJavaType();
        dt.setName(Messages.JavaType);
        javaObjectData.setDataType(dt);
        doReturn(Arrays.asList(javaObjectData)).when(inputMappingProposalProvider).getDataInScope();
        final IType employeeType = mock(IType.class);
        doReturn(employeeType).when(inputMappingProposalProvider).getType(javaObjectData.getClassName());
        final IMethod setNameMethod = mock(IMethod.class);
        when(setNameMethod.getElementName()).thenReturn("setName");
        when(setNameMethod.getDeclaringType()).thenReturn(employeeType);
        when(setNameMethod.getParameterTypes()).thenReturn(new String[] { String.class.getName() });
        doReturn(String.class.getName()).when(inputMappingProposalProvider).toJavaType(setNameMethod);
        doReturn(new IMethod[] { setNameMethod }).when(inputMappingProposalProvider).getSetters(employeeType);
        assertThat(inputMappingProposalProvider.getProposals("", 0)).hasSize(1);
        final IContentProposal[] proposals = inputMappingProposalProvider.getProposals("", 0);
        assertThat(proposals[0].getContent()).isEqualTo("employee.name");
    }

    @Test
    public void should_getProposals_returns__nothing_for_java_data_in_scope_with_incompatible_type() throws Exception {
        final JavaObjectData javaObjectData = ProcessFactory.eINSTANCE.createJavaObjectData();
        javaObjectData.setName("employee");
        javaObjectData.setClassName("com.model.Employee");
        final DataType dt = ProcessFactory.eINSTANCE.createJavaType();
        dt.setName(Messages.JavaType);
        javaObjectData.setDataType(dt);
        doReturn(Arrays.asList(javaObjectData)).when(inputMappingProposalProvider).getDataInScope();
        final IType employeeType = mock(IType.class);
        doReturn(employeeType).when(inputMappingProposalProvider).getType(javaObjectData.getClassName());
        final IMethod setNameMethod = mock(IMethod.class);
        when(setNameMethod.getElementName()).thenReturn("setManager");
        when(setNameMethod.getDeclaringType()).thenReturn(employeeType);
        when(setNameMethod.getParameterTypes()).thenReturn(new String[] { "com.model.Employee" });
        doReturn("com.model.Employee").when(inputMappingProposalProvider).toJavaType(setNameMethod);
        doReturn(new IMethod[] { setNameMethod }).when(inputMappingProposalProvider).getSetters(employeeType);
        assertThat(inputMappingProposalProvider.getProposals("", 0)).hasSize(0);

    }

    @Test
    public void should_getProposals_returns_nothing_for_java_data_in_scope_without_mehtod() throws Exception {
        final JavaObjectData javaObjectData = ProcessFactory.eINSTANCE.createJavaObjectData();
        javaObjectData.setName("employee");
        javaObjectData.setClassName("com.model.Employee");
        final DataType dt = ProcessFactory.eINSTANCE.createJavaType();
        dt.setName(Messages.JavaType);
        javaObjectData.setDataType(dt);
        doReturn(Arrays.asList(javaObjectData)).when(inputMappingProposalProvider).getDataInScope();
        final IType employeeType = mock(IType.class);
        doReturn(employeeType).when(inputMappingProposalProvider).getType(javaObjectData.getClassName());
        final IField setNameMethod = mock(IField.class);
        doReturn(new IField[] { setNameMethod }).when(inputMappingProposalProvider).getSetters(employeeType);
        assertThat(inputMappingProposalProvider.getProposals("", 0)).hasSize(0);
    }

    @Test
    public void should_getProposals_returns_nothing_for_java_data_in_scope_without_setters() throws Exception {
        final JavaObjectData javaObjectData = ProcessFactory.eINSTANCE.createJavaObjectData();
        javaObjectData.setName("employee");
        javaObjectData.setClassName("com.model.Employee");
        final DataType dt = ProcessFactory.eINSTANCE.createJavaType();
        dt.setName(Messages.JavaType);
        javaObjectData.setDataType(dt);
        doReturn(Arrays.asList(javaObjectData)).when(inputMappingProposalProvider).getDataInScope();
        final IType employeeType = mock(IType.class);
        doReturn(employeeType).when(inputMappingProposalProvider).getType(javaObjectData.getClassName());
        final IMethod getNameMethod = mock(IMethod.class);
        when(getNameMethod.getElementName()).thenReturn("getName");
        doReturn(new IMethod[] { getNameMethod }).when(inputMappingProposalProvider).getSetters(employeeType);
        assertThat(inputMappingProposalProvider.getProposals("", 0)).hasSize(0);
    }

    @Test
    public void should_getProposals_returns_nothing_for_java_data_in_scope_with_null_setters() throws Exception {
        final JavaObjectData javaObjectData = ProcessFactory.eINSTANCE.createJavaObjectData();
        javaObjectData.setName("employee");
        javaObjectData.setClassName("com.model.Employee");
        final DataType dt = ProcessFactory.eINSTANCE.createJavaType();
        dt.setName(Messages.JavaType);
        javaObjectData.setDataType(dt);
        doReturn(Arrays.asList(javaObjectData)).when(inputMappingProposalProvider).getDataInScope();
        final IType employeeType = mock(IType.class);
        doReturn(employeeType).when(inputMappingProposalProvider).getType(javaObjectData.getClassName());
        doReturn(null).when(inputMappingProposalProvider).getSetters(employeeType);
        assertThat(inputMappingProposalProvider.getProposals("", 0)).hasSize(0);
    }

}
