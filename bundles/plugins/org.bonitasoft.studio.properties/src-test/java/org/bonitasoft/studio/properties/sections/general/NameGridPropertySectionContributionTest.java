package org.bonitasoft.studio.properties.sections.general;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
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
public class NameGridPropertySectionContributionTest {

    @Mock
    private NameGridPropertySectionContribution nameGridPropertySectionContribution;
    @Mock
    private TabbedPropertySheetPage sheetPage;

    private ExtensibleGridPropertySection gridPropertySection;
    @Mock
    private Display displayMock;
    @Mock
    private IGraphicalEditPart editPart;
    @Mock
    private ITextAwareEditPart textAwareEP;

    @Before
    public void setUp() throws Exception {
        nameGridPropertySectionContribution = new NameGridPropertySectionContribution(sheetPage, gridPropertySection);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_updatePartName_call_refresh_on_pool_edit_part_asynchronously() throws Exception {
        when(editPart.getChildren()).thenReturn(Arrays.asList(textAwareEP));

        nameGridPropertySectionContribution.setSelection(new StructuredSelection(editPart));

        nameGridPropertySectionContribution.updatePartName("new name", displayMock);

        verify(displayMock).asyncExec(any(Runnable.class));
    }

}
