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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.bonitasoft.studio.common.Activator;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.DefaultTabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.ITabbedPropertySelectionProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.ITabbedSectionPropertyProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.PropertySectionWithTabs;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.TabContents;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.base.Objects;

public class TabbedPropertySynchronizerListener implements ISelectionChangedListener {

    private final TabbedPropertySelectionProviderRegistry registry;
    private final IWorkbenchPage activePage;
    private final EditPartResolver editPartResolver;

    public TabbedPropertySynchronizerListener(final EditPartResolver editPartResolver, final TabbedPropertySelectionProviderRegistry registry,
            final IWorkbenchPage activePage) {
        this.registry = registry;
        this.activePage = activePage;
        this.editPartResolver = editPartResolver;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        final ISelection selection = event.getSelection();
        if (selection.isEmpty()) {
            return;
        }
        final EObject element = unwrap(selection);
        final IEditorReference activeEditorReference = activeEditorReference(activePage);
        final IWorkbenchPart editorPart = activeEditorReference.getPart(false);
        if (editorPart instanceof DiagramEditor) {
            final DiagramEditor diagramEditor = (DiagramEditor) editorPart;
            try {
                final IGraphicalEditPart editPart = editPartResolver.findEditPart(diagramEditor.getDiagramEditPart(), element);
                final ITabbedPropertySelectionProvider defaultProvider = findDefaultProvider(editorPart, editPart);
                updateDiagramSelection(diagramEditor, editPart);
                final ITabbedPropertySelectionProvider selectionProvider = registry.findSelectionProvider(element, activeEditorReference, defaultProvider);
                IViewPart part = null;
                try {
                    part = activePage.showView(selectionProvider.viewId());
                } catch (final PartInitException e1) {
                    return;
                }
                if (part != null) {
                    updateSelectedTabInPage(element, selectionProvider, part);
                }
            } catch (final EditPartNotFoundException e) {
                BonitaStudioLog.debug("No edit part found for semantic element: " + element, Activator.PLUGIN_ID);
            }
        }
    }

    private void updateSelectedTabInPage(final EObject element, final ITabbedPropertySelectionProvider selectionProvider, final IViewPart part) {
        final TabbedPropertySheetPage page = (TabbedPropertySheetPage) part.getAdapter(TabbedPropertySheetPage.class);
        if (page != null) {
            page.setSelectedTab(selectionProvider.tabId(element));
            if (selectionProvider instanceof ITabbedSectionPropertyProvider) {
                final PropertySectionWithTabs sectionWithTabs = findSectionWithTabs(page);
                if (sectionWithTabs != null) {
                    sectionWithTabs.setSelectedTab(((ITabbedSectionPropertyProvider) selectionProvider).tabIndex());
                }
            }
        }
    }

    private ITabbedPropertySelectionProvider findDefaultProvider(final IWorkbenchPart editorPart,
            final IGraphicalEditPart editPart) {
        final String selectedViewId = activePropertyView(activePage);
        String selectedTabId = null;
        ITabbedPropertySelectionProvider defaultProvider = null;
        if (selectedViewId != null) {
            selectedTabId = activePropertyTabId(activePage, selectedViewId);
            final TabbedPropertySheetPage propertySheetPage = activePropertyTab(activePage, selectedViewId);
            if (propertySheetPage != null && propertySheetPage.getSelectedTab() != null) {
                final List<ISectionDescriptor> sectionDescriptors = propertySheetPage.getSelectedTab().getSectionDescriptors();
                for (final ISectionDescriptor descriptor : sectionDescriptors) {
                    if (descriptor.appliesTo(editorPart, new StructuredSelection(editPart))) {
                        defaultProvider = DefaultTabbedPropertyProvider.defaultProvider(selectedViewId, selectedTabId);
                        break;
                    }
                }
            }
        }
        return defaultProvider;
    }

    private String activePropertyView(final IWorkbenchPage activePage) {
        for (final IViewReference ref : activePage.getViewReferences()) {
            final IWorkbenchPart part = ref.getPart(false);
            if (part != null && activePage.isPartVisible(part)) {
                final TabbedPropertySheetPage sheetPage = (TabbedPropertySheetPage) part.getAdapter(TabbedPropertySheetPage.class);
                if (sheetPage != null) {
                    return ref.getId();
                }
            }
        }
        return null;
    }

    private String activePropertyTabId(final IWorkbenchPage activePage, final String viewId) {
        final IViewPart view = activePage.findView(viewId);
        final TabbedPropertySheetPage propertySheetPage = (TabbedPropertySheetPage) view.getAdapter(TabbedPropertySheetPage.class);
        if (propertySheetPage != null && propertySheetPage.getSelectedTab() != null) {
            return propertySheetPage.getSelectedTab().getId();
        }
        return null;
    }

    private TabbedPropertySheetPage activePropertyTab(final IWorkbenchPage activePage, final String viewId) {
        final IViewPart view = activePage.findView(viewId);
        final TabbedPropertySheetPage propertySheetPage = (TabbedPropertySheetPage) view.getAdapter(TabbedPropertySheetPage.class);
        if (propertySheetPage != null) {
            return propertySheetPage;
        }
        return null;
    }

    private void updateDiagramSelection(final DiagramEditor diagramEditor, final IGraphicalEditPart editPart) {
        final IDiagramGraphicalViewer diagramGraphicalViewer = diagramEditor.getDiagramGraphicalViewer();
        diagramGraphicalViewer.select(editPart);
        diagramGraphicalViewer.reveal(editPart);
        diagramGraphicalViewer.getControl().getDisplay().asyncExec(new RefreshPropertyViewsSelection(diagramEditor, new StructuredSelection(editPart)));
    }

    private IEditorReference activeEditorReference(final IWorkbenchPage activePage) {
        final IEditorPart activeEditor = activePage.getActiveEditor();
        for (final IEditorReference ref : activePage.getEditorReferences()) {
            if (Objects.equal(activeEditor, ref.getPart(false))) {
                return ref;
            }
        }
        return null;
    }

    private PropertySectionWithTabs findSectionWithTabs(final TabbedPropertySheetPage page) {
        final TabContents currentTab = page.getCurrentTab();
        if (currentTab != null) {
            return (PropertySectionWithTabs) find(newArrayList(currentTab.getSections()), instanceOf(PropertySectionWithTabs.class), null);
        }
        return null;
    }

    private EObject unwrap(final ISelection selection) {
        checkArgument(selection instanceof IStructuredSelection);
        final Object element = ((IStructuredSelection) selection).getFirstElement();
        checkState(element instanceof EObject);
        return (EObject) element;
    }

}
