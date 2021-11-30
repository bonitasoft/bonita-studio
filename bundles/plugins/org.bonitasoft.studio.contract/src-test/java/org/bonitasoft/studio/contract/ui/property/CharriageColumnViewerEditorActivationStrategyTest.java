/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class CharriageColumnViewerEditorActivationStrategyTest {

    @Mock
    private ColumnViewer viewer;

    @Mock
    private Tree tree;

    private CharriageColumnViewerEditorActivationStrategy contractInputColumnViewerEditorActivationStrategy;

    @Mock
    private ViewerCell cell;

    @Before
    public void setUp() throws Exception {
        when(viewer.getStructuredSelection()).thenReturn(new StructuredSelection());
        contractInputColumnViewerEditorActivationStrategy = new CharriageColumnViewerEditorActivationStrategy(viewer);
    }

    @Test
    public void should_isEditorActivationEvent_returns_true_if_pressed_enter_on_a_cell() throws Exception {
        final Event e = new Event();
        e.widget = tree;
        final KeyEvent event = new KeyEvent(e);
        event.keyCode = SWT.CR;
        final ColumnViewerEditorActivationEvent activationEvent = new ColumnViewerEditorActivationEvent(cell, event);
        activationEvent.eventType = ColumnViewerEditorActivationEvent.KEY_PRESSED;
        assertThat(contractInputColumnViewerEditorActivationStrategy.isEditorActivationEvent(activationEvent)).isTrue();
    }

    @Test
    public void should_isEditorActivationEvent_returns_false_if_pressed_another_key() throws Exception {
        final Event e = new Event();
        e.widget = tree;
        final KeyEvent event = new KeyEvent(e);
        event.keyCode = SWT.ARROW_DOWN;
        final ColumnViewerEditorActivationEvent activationEvent = new ColumnViewerEditorActivationEvent(cell, event);
        activationEvent.eventType = ColumnViewerEditorActivationEvent.KEY_PRESSED;
        assertThat(contractInputColumnViewerEditorActivationStrategy.isEditorActivationEvent(activationEvent)).isFalse();
    }

}
