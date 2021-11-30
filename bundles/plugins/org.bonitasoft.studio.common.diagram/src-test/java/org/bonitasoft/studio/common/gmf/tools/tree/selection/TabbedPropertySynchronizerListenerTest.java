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

import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.ITabbedPropertySelectionProvider;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TabbedPropertySynchronizerListenerTest {

    @Mock
    private IWorkbenchPage activePage;
    @Mock
    private TabbedPropertySelectionProviderRegistry registry;
    @Mock
    private ISelectionProvider source;
    @Mock
    private IEditorReference processEditorReference;
    @Mock
    private DiagramEditor editorPart;
    @Mock
    private ITabbedPropertySelectionProvider selectionProvider;
    @Mock
    private IViewPart viewPart;
    @Mock
    private IDiagramGraphicalViewer viewer;
    @Mock
    private TabbedPropertySheetPage page;
    @Mock
    private EditPartResolver editPartResolver;
    @Mock
    private IGraphicalEditPart editPart;
    @Mock
    private Display display;

    @Before
    public void setUp() throws Exception {
        when(processEditorReference.getPart(false)).thenReturn(editorPart);
        when(activePage.getActiveEditor()).thenReturn(editorPart);
        when(activePage.getViewReferences()).thenReturn(new IViewReference[] {});
        when(activePage.showView(any())).thenReturn(viewPart);
        when(viewPart.getAdapter(TabbedPropertySheetPage.class)).thenReturn(page);
        when(editorPart.getDiagramEditPart()).thenReturn(new DiagramEditPart(NotationFactory.eINSTANCE.createDiagram()));
        when(editorPart.getDiagramGraphicalViewer()).thenReturn(viewer);
        doReturn(aControlWithDisplay(display)).when(viewer).getControl();
        when(editPartResolver.findEditPart(any(), notNull())).thenReturn(editPart);
        doReturn(new IEditorReference[] { processEditorReference }).when(activePage).getEditorReferences();
    }

    private Control aControlWithDisplay(final Display display) {
        final Control control = mock(Control.class);
        doReturn(display).when(control).getDisplay();
        return control;
    }

    @Test
    public void should_show_provided_viewId_when_handling_selection_change_event() throws Exception {
        when(registry.findSelectionProvider(notNull(), eq(processEditorReference), any())).thenReturn(
                selectionProvider);
        when(selectionProvider.viewId()).thenReturn("aViewId");
        final TabbedPropertySynchronizerListener listener = newFixture();

        listener.selectionChanged(new SelectionChangedEvent(source, new StructuredSelection(aTask().build())));

        verify(activePage).showView("aViewId");
    }

    @Test
    public void should_update_diagram_selection_when_handling_selection_change_event() throws Exception {
        when(registry.findSelectionProvider(notNull(), eq(processEditorReference), any())).thenReturn(
                selectionProvider);
        final TabbedPropertySynchronizerListener listener = newFixture();

        listener.selectionChanged(new SelectionChangedEvent(source, new StructuredSelection(aTask().build())));

        verify(viewer).select(editPart);
        verify(viewer).reveal(editPart);
        verify(display).asyncExec(notNull());
    }

    @Test
    public void should_update_selectedTab_when_handling_selection_change_event() throws Exception {
        when(registry.findSelectionProvider(notNull(), eq(processEditorReference), any())).thenReturn(
                selectionProvider);
        final Task task = aTask().build();
        when(selectionProvider.tabId(task)).thenReturn("aTabId");
        final TabbedPropertySynchronizerListener listener = newFixture();

        listener.selectionChanged(new SelectionChangedEvent(source, new StructuredSelection(task)));

        verify(page).setSelectedTab("aTabId");
    }

    private TabbedPropertySynchronizerListener newFixture() {
        return new TabbedPropertySynchronizerListener(editPartResolver, registry, activePage);
    }

}
