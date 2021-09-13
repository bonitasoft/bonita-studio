/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.gmf.tools.tree.selection;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RefreshPropertyViewsSelectionTest {

    @Mock
    private IWorkbenchPart part;
    @Mock
    private ISelection newSelection;

    @Test
    public void should_update_property_views_with_new_selection() throws Exception {
        final RefreshPropertyViewsSelection refreshPropertyViewsSelection = spy(new RefreshPropertyViewsSelection(part, newSelection));
        final TabbedPropertySheetPage propertySheetPage1 = mock(TabbedPropertySheetPage.class);
        final TabbedPropertySheetPage propertySheetPage2 = mock(TabbedPropertySheetPage.class);
        doReturn(new IViewReference[] { propertyViewRef(propertySheetPage1), propertyViewRef(propertySheetPage2), propertyViewRef(null) }).when(
                refreshPropertyViewsSelection)
                .viewReferences();

        refreshPropertyViewsSelection.run();

        verify(propertySheetPage1).selectionChanged(part, newSelection);
        verify(propertySheetPage2).selectionChanged(part, newSelection);
    }

    private IViewReference propertyViewRef(final TabbedPropertySheetPage sheetPage) {
        final IViewReference viewReference = mock(IViewReference.class);
        final IViewPart iViewPart = mock(IViewPart.class);
        doReturn(iViewPart).when(viewReference).getView(true);
        doReturn(sheetPage).when(iViewPart).getAdapter(TabbedPropertySheetPage.class);
        return viewReference;
    }
}
