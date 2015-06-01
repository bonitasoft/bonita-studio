/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Group;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AbstractNamePropertySectionContributionTest {

    @Mock
    private AbstractNamePropertySectionContribution nameContribution;

    @Mock
    private TabbedPropertySheetPage tabbedPropertySheetPage;

    @Mock
    ExtensibleGridPropertySection extensibleGridPropertySection;


    @Before
    public void setUp(){
        when(nameContribution.isContainerIsAFormOrAGroup(any(EObject.class))).thenCallRealMethod();
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

    @Test
    public void testIsNotContainerIsAFormOrAGroupWithNull() {
        assertFalse(nameContribution.isContainerIsAFormOrAGroup(null));
    }

}
