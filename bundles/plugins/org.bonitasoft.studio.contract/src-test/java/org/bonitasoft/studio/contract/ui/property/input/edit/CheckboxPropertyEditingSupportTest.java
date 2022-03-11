package org.bonitasoft.studio.contract.ui.property.input.edit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CheckboxPropertyEditingSupportTest {

    @Mock
    private ColumnViewer viewer;

    @Mock
    private IPropertySourceProvider sourceProvider;

    private CheckboxPropertyEditingSupport editingSupport;

    @Mock
    private IPropertySource value;

    @Before
    public void setUp() throws Exception {
        editingSupport = new CheckboxPropertyEditingSupport(sourceProvider, viewer, "name");
        when(sourceProvider.getPropertySource(any())).thenReturn(value);
    }

    @Test
    public void should_getCellEditor_returns_a_CheckboxCellEditor() throws Exception {
        assertThat(editingSupport.getCellEditor(null)).isInstanceOf(CheckboxCellEditor.class);
    }

    @Test
    public void should_setValue_updates_viewer_after_super_call() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        editingSupport.setValue(input, Boolean.FALSE);
        verify(viewer).update(input, null);
    }
}
