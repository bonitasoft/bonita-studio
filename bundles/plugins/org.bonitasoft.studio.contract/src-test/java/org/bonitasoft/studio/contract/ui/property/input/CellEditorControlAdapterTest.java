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
package org.bonitasoft.studio.contract.ui.property.input;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
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
public class CellEditorControlAdapterTest {

    private CellEditorControlAdapter cellEditorControlAdapter;

    @Mock
    private Control control;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        cellEditorControlAdapter = new CellEditorControlAdapter(control);
        when(control.getLocation()).thenReturn(new Point(0, 0));
        when(control.getSize()).thenReturn(new Point(50, 20));
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_controlMoved_adjust_control_location_and_size() throws Exception {
        final Event e = new Event();
        e.widget = control;
        final ControlEvent event = new ControlEvent(e);
        cellEditorControlAdapter.controlMoved(event);
        verify(control).setLocation(CellEditorControlAdapter.OFFSET, 0);
        verify(control).setSize(50 - CellEditorControlAdapter.OFFSET, 20);
    }

    @Test
    public void should_controlMoved_doNothing() throws Exception {
        when(control.getLocation()).thenReturn(new Point(CellEditorControlAdapter.OFFSET, 0));

        final Event e = new Event();
        e.widget = control;
        final ControlEvent event = new ControlEvent(e);
        cellEditorControlAdapter.controlMoved(event);
        verify(control, never()).setLocation(anyInt(), anyInt());
        verify(control, never()).setSize(anyInt(), anyInt());
    }

}
