package org.bonitasoft.studio.contract.ui.property.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


public class DescriptionCellLabelProviderTest {

    @Mock
    private IPropertySourceProvider propertySourceLabelProvider;

    private DescriptionCellLabelProvider descriptionCellLabelProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        descriptionCellLabelProvider = spy(new DescriptionCellLabelProvider(propertySourceLabelProvider, new ContractDefinitionValidator()));
        doReturn(null).when(descriptionCellLabelProvider).getErrorBackgroundColor();
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
    public void should_getToolTipText_returns_null() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        assertThat(descriptionCellLabelProvider.getToolTipText(input)).isNull();
    }

    @Test
    public void should_getToolTipText_returns_error_message_for_empty_name() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        input.setDescription("too longggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
        assertThat(descriptionCellLabelProvider.getToolTipText(input)).isNotEmpty().contains(org.bonitasoft.studio.common.Messages.fieldIsTooLong);
    }



    @Test
    public void should_getBackgroundColor_returns_red_color_for_too_long_description() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        input.setDescription("too longggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
        descriptionCellLabelProvider.getBackground(input);
        verify(descriptionCellLabelProvider).getErrorBackgroundColor();
    }

    @Test
    public void should_getBackgroundColor_returns_standard_color() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        descriptionCellLabelProvider.getBackground(input);
        verify((PropertyColumnLabelProvider) descriptionCellLabelProvider).getBackground(input);
        verify(descriptionCellLabelProvider, never()).getErrorBackgroundColor();
    }
}
