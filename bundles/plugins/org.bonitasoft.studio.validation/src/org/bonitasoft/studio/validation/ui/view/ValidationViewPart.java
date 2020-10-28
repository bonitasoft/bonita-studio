/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.validation.ui.view;

import java.util.Arrays;
import java.util.Map;

import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorUtil;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.internal.resources.Marker;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.markers.MarkerSupportInternalUtilities;
import org.eclipse.ui.part.ViewPart;

public class ValidationViewPart extends ViewPart implements ISelectionListener,
        ISelectionChangedListener, ISelectionProvider {

    public static String ID = "org.bonitasoft.studio.validation.view";

    private TableViewer tableViewer;
    private ISelectionProvider selectionProvider;
    private TableViewerColumn severityColumn;
    private ValidationViewAction validateAction;

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createPartControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults()
                .extendedMargins(5, 0, 3, 1).create());
        mainComposite.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        createValidateButton(mainComposite);
        createTableComposite(mainComposite);

        final ISelectionService ss = getSite().getWorkbenchWindow()
                .getSelectionService();
        ss.addPostSelectionListener(this);
        if (getSite().getPage().getActiveEditor() != null) {
            selectionProvider = getSite().getPage().getActiveEditor()
                    .getEditorSite().getSelectionProvider();
            getSite().setSelectionProvider(this);

        }

        final TableColumnSorter sorter = new TableColumnSorter(tableViewer);
        sorter.setColumn(severityColumn.getColumn());
    }

    private void createValidateButton(final Composite mainComposite) {

        final IWorkbenchPage activePage = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage();
        validateAction = new ValidationViewAction();
        validateAction.setActivePage(activePage);
        validateAction.setTableViewer(tableViewer);

        final Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults()
                .margins(0, 0).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        final Button validateButton = new Button(buttonComposite, SWT.PUSH);
        validateButton.setLayoutData(GridDataFactory.fillDefaults().create());
        validateButton.setText(Messages.validationViewValidateButtonLabel);
        validateButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see
             * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
             * .swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                validateAction.run();
                tableViewer.refresh(true, true);
            }
        });
    }

    private void createTableComposite(final Composite mainComposite) {
        final Composite tableComposite = new Composite(mainComposite, SWT.NONE);
        tableComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        tableComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());

        tableViewer = new TableViewer(tableComposite, SWT.SINGLE | SWT.BORDER
                | SWT.FULL_SELECTION);
        final Table table = tableViewer.getTable();
        table.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(400, SWT.DEFAULT).create());
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        addSeverityDescriptionColumn();
        addElementNameColumn();
        addErrorDescriptionColumn();

        final TableColumnLayout tcLayout = new TableColumnLayout();
        tcLayout.setColumnData(table.getColumn(0), new ColumnWeightData(1));
        tcLayout.setColumnData(table.getColumn(1), new ColumnWeightData(5));
        tcLayout.setColumnData(table.getColumn(2), new ColumnWeightData(11));
        table.getParent().setLayout(tcLayout);

        tableViewer.setContentProvider(new ValidationMarkerContentProvider());
        final IEditorPart activeEditor = getSite().getPage().getActiveEditor();
        tableViewer.setInput(activeEditor);

        tableViewer.addSelectionChangedListener(this);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
     */
    @Override
    public void setFocus() {

    }

    private void addElementNameColumn() {
        final TableViewerColumn elements = new TableViewerColumn(tableViewer,
                SWT.NONE);
        elements.getColumn().setText(Messages.validationViewElementColumnName);
        elements.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                final Marker marker = (Marker) element;
                try {

                    final String elementId = (String) marker
                            .getAttribute(org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID);
                    String location = (String) marker.getAttribute(IMarker.LOCATION);
                    int idx = location.lastIndexOf(":");
                    if (idx > 0) {
                        String result = location.substring(idx + 1);

                        // get parent name if name is empty
                        if (result.trim().isEmpty() && idx - 1 > 0) {
                            location = location.substring(0, idx - 1);
                            idx = location.lastIndexOf(":");
                            result = location.substring(idx + 1);
                        }

                        if (!(result.startsWith("<") && result.endsWith(">"))) {
                            return result;
                        } else {
                            final DiagramRepositoryStore store = RepositoryManager
                                    .getInstance().getRepositoryStore(
                                            DiagramRepositoryStore.class);
                            final DiagramFileStore file = store.getChild(marker
                                    .getResource().getLocation().toFile()
                                    .getName(), true);
                            if (file != null) {
                                final EObject view = file.getEMFResource()
                                        .getEObject(elementId);
                                if (view != null) {
                                    for (final EObject object : view
                                            .eCrossReferences()) {
                                        if (object instanceof Element) {
                                            return ((Element) object).getName();
                                        }
                                    }
                                }
                            }
                            return "";
                        }
                    }

                    return location;
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                    return "";
                }
            }
        });

    }

    private void addErrorDescriptionColumn() {
        final TableViewerColumn elements = new TableViewerColumn(tableViewer,
                SWT.NONE);
        elements.getColumn().setText(
                Messages.validationViewDescriptionColumnName);
        elements.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {

                final Marker marker = (Marker) element;
                try {
                    return (String) marker.getAttribute("message");
                } catch (final CoreException e) {
                    return "";
                }
            }
        });
    }

    private void addSeverityDescriptionColumn() {
        severityColumn = new TableViewerColumn(tableViewer, SWT.NONE);
        severityColumn.getColumn().setText(
                Messages.validationViewSeverityColumnName);
        severityColumn.setLabelProvider(new SeverityColumnLabelProvider());
        ColumnViewerToolTipSupport.enableFor(severityColumn.getViewer());
    }

    @Override
    public void dispose() {

    }

    @Override
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {

    }

    @Override
    public ISelection getSelection() {
        return selectionProvider.getSelection();
    }

    @Override
    public void removeSelectionChangedListener(
            final ISelectionChangedListener listener) {
        selectionProvider.removeSelectionChangedListener(listener);
    }

    @Override
    public void setSelection(final ISelection selection) {
        selectionProvider.setSelection(selection);
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        if (event.getSelection() instanceof StructuredSelection
                && ((StructuredSelection) event.getSelection()).getFirstElement() instanceof Marker) {
            updateStatusLine((IStructuredSelection) event.getSelection());
            final Marker m = (Marker) ((StructuredSelection) event.getSelection())
                    .getFirstElement();
            final String elementId = m.getAttribute(org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID, null);
            if (elementId == null
                    || !(getSite().getPage().getActiveEditor() instanceof DiagramEditor)) {
                return;
            }
            final DiagramEditor editor = (DiagramEditor) getSite().getPage()
                    .getActiveEditor();
            final Map editPartRegistry = editor.getDiagramGraphicalViewer()
                    .getEditPartRegistry();
            final EObject targetView = editor.getDiagram().eResource()
                    .getEObject(elementId);
            if (targetView == null) {
                return;
            }
            final EditPart targetEditPart = (EditPart) editPartRegistry
                    .get(targetView);
            if (targetEditPart != null) {
                ProcessDiagramEditorUtil.selectElementsInDiagram(editor,
                        Arrays.asList(new EditPart[] { targetEditPart }));
            }
        }

    }

    @Override
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        if (selection instanceof StructuredSelection
                && !tableViewer.getTable().isDisposed()) {
            final Object selectedEP = ((StructuredSelection) selection)
                    .getFirstElement();
            if (selectedEP instanceof IGraphicalEditPart) {
                final IEditorPart editorPart = getSite().getPage().getActiveEditor();
                if (editorPart != null) {
                    if (!editorPart.equals(tableViewer.getInput())) {
                        selectionProvider = editorPart.getEditorSite()
                                .getSelectionProvider();
                        tableViewer.setInput(editorPart);
                    } else {
                        tableViewer.refresh();
                    }
                }
                tableViewer.getTable().layout(true, true);
            }

            // change Validate Action
            final IWorkbenchPage activePage = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow().getActivePage();

            validateAction = new ValidationViewAction();
            validateAction.setActivePage(activePage);
            validateAction.setTableViewer(tableViewer);
            tableViewer.refresh();
        }
    }
    
    /**
     * Update the status line with the new selection
     *
     * @param newSelection
     */
    void updateStatusLine(IStructuredSelection newSelection) {
        String message = MarkerSupportInternalUtilities.EMPTY_STRING;

        if (newSelection.size() == 1) {
            // Use the Message attribute of the marker
            try {
                message = (String) ((Marker) newSelection.getFirstElement()).getAttribute("message");
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        } 
        getViewSite().getActionBars().getStatusLineManager().setMessage(message);
    }

    public void refreshViewer() {
        if (tableViewer != null && !tableViewer.getTable().isDisposed()) {
            tableViewer.setInput(tableViewer.getInput());
        }
    }

}
