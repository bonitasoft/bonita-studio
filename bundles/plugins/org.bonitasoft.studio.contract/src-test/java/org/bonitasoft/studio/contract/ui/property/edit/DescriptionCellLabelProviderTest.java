package org.bonitasoft.studio.contract.ui.property.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DescriptionCellLabelProviderTest {

    private static final String LONG_DESC = "too longggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg";

    @Mock
    private IPropertySourceProvider propertySourceLabelProvider;

    private DescriptionCellLabelProvider descriptionCellLabelProvider;

    @Mock
    private TableViewer viewer;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        descriptionCellLabelProvider = spy(new DescriptionCellLabelProvider(viewer, propertySourceLabelProvider));
        doReturn(null).when(descriptionCellLabelProvider).getErrorImage();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getImage_returns_null() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        assertThat(descriptionCellLabelProvider.getImage(input)).isNull();
    }

    @Test
    public void should_getToolTipText_returns_null_if_no_error() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        assertThat(descriptionCellLabelProvider.getToolTipText(input)).isNull();
    }

    @Test
    public void should_getToolTipText_returns_description_if_error_but_activeEditor() throws Exception {
        when(viewer.isCellEditorActive()).thenReturn(true);
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setDescription(LONG_DESC);
        assertThat(descriptionCellLabelProvider.getToolTipText(input)).isEqualTo(LONG_DESC);
    }

    @Test
    public void should_getToolTipText_returns_error_message_for_empty_name() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        input.setDescription(LONG_DESC);
        assertThat(descriptionCellLabelProvider.getToolTipText(input)).isNotEmpty();
    }



    @Test
    public void should_getImage_returns_error_icon_for_too_long_description() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        input.setDescription(LONG_DESC);
        descriptionCellLabelProvider.getImage(input);
        verify(descriptionCellLabelProvider).getErrorImage();
    }

    @Test
    public void should_getImage_returns_null_if_description_is_valid() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        assertThat(descriptionCellLabelProvider.getImage(input)).isNull();
        verify(descriptionCellLabelProvider, never()).getErrorImage();
    }
}
