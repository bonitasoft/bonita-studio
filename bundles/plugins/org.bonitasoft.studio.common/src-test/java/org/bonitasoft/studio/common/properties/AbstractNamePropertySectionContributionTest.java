package org.bonitasoft.studio.common.properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;



public class AbstractNamePropertySectionContributionTest {

    private AbstractNamePropertySectionContribution nameContribution;

    @Mock
    private TabbedPropertySheetPage tabbedPropertySheetPage;

    @Mock
    ExtensibleGridPropertySection extensibleGridPropertySection;


    @Before
    public void setUp(){
        nameContribution = spy(new NamePropertySectionContributionForTest(tabbedPropertySheetPage, extensibleGridPropertySection));
    }

    @Test
    public void testIsContainerIsAFormOrAGroup() {
        final Form form = FormFactory.eINSTANCE.createForm();
        final Group group1 = FormFactory.eINSTANCE.createGroup();
        form.getWidgets().add(group1);
        final Group group2 = FormFactory.eINSTANCE.createGroup();
        group1.getWidgets().add(group2);
        assertTrue(nameContribution.isContainerIsAFormOrAGroup(group2));
    }

    @Test
    public void testIsNotContainerIsAFormOrAGroup() {
        final Group group1 = FormFactory.eINSTANCE.createGroup();
        final Group group2 = FormFactory.eINSTANCE.createGroup();
        group1.getWidgets().add(group2);
        assertFalse(nameContribution.isContainerIsAFormOrAGroup(group2));
    }

}
