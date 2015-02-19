package org.bonitasoft.studio.properties.sections.general;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProcessElementNameContributionTest extends AbstractSWTTestCase {

    @Mock
    private ProcessElementNameContribution nameGridPropertySectionContribution;
    @Mock
    private TabbedPropertySheetPage sheetPage;

    private ExtensibleGridPropertySection gridPropertySection;
    @Mock
    private Display displayMock;
    @Mock
    private IGraphicalEditPart editPart;
    @Mock
    private ITextAwareEditPart textAwareEP;

    private EMFDataBindingContext dbc;

    @Before
    public void setUp() throws Exception {
        createDisplayAndRealm();
        dbc = new EMFDataBindingContext();
        nameGridPropertySectionContribution = new ProcessElementNameContribution(sheetPage, gridPropertySection);
    }

    @After
    public void tearDown() throws Exception {
        if (dbc != null) {
            dbc.dispose();
        }

        dispose();
    }

    @Test
    public void should_updatePartName_call_refresh_on_pool_edit_part_asynchronously() throws Exception {
        final Task task = aTask().build();
        nameGridPropertySectionContribution.setEObject(task);
        when(editPart.resolveSemanticElement()).thenReturn(task);
        when(editPart.getChildren()).thenReturn(Arrays.asList(textAwareEP));

        nameGridPropertySectionContribution.setSelection(new StructuredSelection(editPart));

        nameGridPropertySectionContribution.updatePartName("new name", displayMock);

        verify(displayMock).asyncExec(any(Runnable.class));
    }

    @Test
    public void should_validationStatusProvider_accept_valid_name() throws Exception {
        final WritableValue targetValue = new WritableValue();
        final WritableValue modelValue = new WritableValue();
        dbc.bindValue(targetValue, modelValue);
        final MultiValidator validationStatusProvider = nameGridPropertySectionContribution.nameValidationStatusProvider(targetValue);
        dbc.addValidationStatusProvider(validationStatusProvider);

        targetValue.setValue("a valid name");

        assertThat(((IStatus) validationStatusProvider.getValidationStatus().getValue()).isOK()).isTrue();
        assertThat(modelValue.getValue()).isEqualTo(targetValue.getValue()).isEqualTo("a valid name");
    }

    @Test
    public void should_validationStatusProvider_fail_name_with_special_chars() throws Exception {
        final WritableValue targetValue = new WritableValue();
        final WritableValue modelValue = new WritableValue();
        dbc.bindValue(targetValue, modelValue);
        final MultiValidator validationStatusProvider = nameGridPropertySectionContribution.nameValidationStatusProvider(targetValue);
        dbc.addValidationStatusProvider(validationStatusProvider);

        targetValue.setValue("a #invalid name");

        assertThat(((IStatus) validationStatusProvider.getValidationStatus().getValue()).isOK()).isFalse();
        assertThat(modelValue.getValue()).isEqualTo(targetValue.getValue()).isEqualTo("a #invalid name");
    }
}
