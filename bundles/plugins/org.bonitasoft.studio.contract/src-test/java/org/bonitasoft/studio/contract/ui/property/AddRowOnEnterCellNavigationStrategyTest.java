/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.jface.viewers.CellNavigationStrategy;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerRow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AddRowOnEnterCellNavigationStrategyTest {

    private AddRowOnEnterCellNavigationStrategy cellNavigationStrategy;

    @Mock
    private TreeViewer viewer;

    @Mock
    private IViewerController controller;

    @Mock
    private ViewerCell currentSelectedCell;

    @Mock
    private Tree tree;

    @Mock
    private ViewerCell viewerCell;

    @Mock
    private ViewerRow viewerRow;

    @Mock
    private TreeItem treeItem;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        cellNavigationStrategy = spy(new AddRowOnEnterCellNavigationStrategy(viewer, controller));
        when(currentSelectedCell.getNeighbor(anyInt(), anyBoolean())).thenReturn(viewerCell);
        when(viewerCell.getItem()).thenReturn(treeItem);
        when(treeItem.getData()).thenReturn(new Object());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_isNavigationEvent_return_true_if_key_is_CR_or_DEL_or_ARROWS() throws Exception {
        final Event event = new Event();
        event.keyCode = SWT.CR;
        assertThat(cellNavigationStrategy.isNavigationEvent(viewer, event)).isTrue();
        event.keyCode = SWT.DEL;
        assertThat(cellNavigationStrategy.isNavigationEvent(viewer, event)).isTrue();
        event.keyCode = SWT.ARROW_RIGHT;
        assertThat(cellNavigationStrategy.isNavigationEvent(viewer, event)).isTrue();
        event.keyCode = 'a';
        assertThat(cellNavigationStrategy.isNavigationEvent(viewer, event)).isFalse();
    }

    @Test
    public void should_findSelectedCell_not_add_a_new_input_on_Enter_if_below_row_exists() throws Exception {
        when(currentSelectedCell.getNeighbor(anyInt(), anyBoolean())).thenReturn(viewerCell);
        final Event event = new Event();
        event.keyCode = SWT.CR;
        cellNavigationStrategy.findSelectedCell(viewer, currentSelectedCell, event);
        verify(controller, never()).add(viewer);
        verify(tree, never()).setSelection(treeItem);
    }

    @Test
    public void should_findSelectedCell_add_a_new_input_on_Enter_if_below_row_not_exists() throws Exception {
        when(currentSelectedCell.getNeighbor(anyInt(), anyBoolean())).thenReturn(null);
        final Event event = new Event();
        event.keyCode = SWT.CR;
        cellNavigationStrategy.findSelectedCell(viewer, currentSelectedCell, event);
        verify(controller).add(viewer);
    }

    @Test
    public void should_findSelectedCell_not_add_a_new_input_on_Enter_if_not_on_first_column() throws Exception {
        when(currentSelectedCell.getColumnIndex()).thenReturn(1);
        final Event event = new Event();
        event.keyCode = SWT.CR;
        cellNavigationStrategy.findSelectedCell(viewer, currentSelectedCell, event);
        verify(controller, never()).add(viewer);
    }

    @Test
    public void should_findSelectedCell_remove_input_on_Delete() throws Exception {
        final Event event = new Event();
        event.keyCode = SWT.DEL;
        cellNavigationStrategy.findSelectedCell(viewer, currentSelectedCell, event);
        verify(controller).remove(viewer);
    }

    @Test
    public void should_findSelectedCell_call_super() throws Exception {
        final Event event = new Event();
        event.keyCode = SWT.ARROW_LEFT;
        cellNavigationStrategy.findSelectedCell(viewer, null, event);
        verify((CellNavigationStrategy) cellNavigationStrategy).findSelectedCell(viewer, null, event);

        cellNavigationStrategy.findSelectedCell(viewer, currentSelectedCell, event);
        verify((CellNavigationStrategy) cellNavigationStrategy).findSelectedCell(viewer, currentSelectedCell, event);
    }

}
