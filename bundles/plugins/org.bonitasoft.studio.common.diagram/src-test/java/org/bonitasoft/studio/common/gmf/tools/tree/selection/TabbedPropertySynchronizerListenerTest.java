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
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.ITabbedPropertySelectionProvider;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    @Before
    public void setUp() throws Exception {
        when(processEditorReference.getId()).thenReturn("org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorID");
        when(processEditorReference.getPart(false)).thenReturn(editorPart);
        when(activePage.getActiveEditor()).thenReturn(editorPart);
        when(activePage.showView(anyString())).thenReturn(viewPart);
        when(viewPart.getAdapter(TabbedPropertySheetPage.class)).thenReturn(page);
        when(editorPart.getDiagramEditPart()).thenReturn(new DiagramEditPart(NotationFactory.eINSTANCE.createDiagram()));
        when(editorPart.getDiagramGraphicalViewer()).thenReturn(viewer);
        doReturn(new IEditorReference[] { processEditorReference }).when(activePage).getEditorReferences();
    }

    @Test
    public void should_show_provided_viewId_when_handling_selection_change_event() throws Exception {
        when(registry.findSelectionProvider(notNull(EObject.class), eq(processEditorReference))).thenReturn(selectionProvider);
        when(selectionProvider.viewId()).thenReturn("aViewId");
        final TabbedPropertySynchronizerListener listener = newFixture();

        listener.selectionChanged(new SelectionChangedEvent(source, new StructuredSelection(aTask().build())));

        verify(activePage).showView("aViewId");
    }

    @Test
    public void should_update_diagram_selection_when_handling_selection_change_event() throws Exception {
        when(registry.findSelectionProvider(notNull(EObject.class), eq(processEditorReference))).thenReturn(selectionProvider);
        final DiagramEditPart diagramEditPart = mock(DiagramEditPart.class);
        when(editorPart.getDiagramEditPart()).thenReturn(diagramEditPart);
        final Task task = aTask().build();
        when(diagramEditPart.resolveSemanticElement()).thenReturn(task);
        final TabbedPropertySynchronizerListener listener = newFixture();

        listener.selectionChanged(new SelectionChangedEvent(source, new StructuredSelection(task)));

        verify(viewer).select(diagramEditPart);
        verify(viewer).reveal(diagramEditPart);
    }

    @Test
    public void should_update_selectedTab_when_handling_selection_change_event() throws Exception {
        when(registry.findSelectionProvider(notNull(EObject.class), eq(processEditorReference))).thenReturn(selectionProvider);
        final DiagramEditPart diagramEditPart = mock(DiagramEditPart.class);
        when(editorPart.getDiagramEditPart()).thenReturn(diagramEditPart);
        final Task task = aTask().build();
        when(diagramEditPart.resolveSemanticElement()).thenReturn(task);
        when(selectionProvider.tabId(task)).thenReturn("aTabId");
        final TabbedPropertySynchronizerListener listener = newFixture();

        listener.selectionChanged(new SelectionChangedEvent(source, new StructuredSelection(task)));

        verify(page).selectionChanged(editorPart, new StructuredSelection(diagramEditPart));
        verify(page).setSelectedTab("aTabId");
    }

    private TabbedPropertySynchronizerListener newFixture() {
        return new TabbedPropertySynchronizerListener(registry, activePage);
    }

}
