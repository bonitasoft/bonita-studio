/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.ui.view;

import java.io.IOException;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.model.report.MigrationReportPackage;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.migration.ui.action.ExportMigrationReportAsPDFAction;
import org.bonitasoft.studio.migration.ui.action.HideReviewedAction;
import org.bonitasoft.studio.migration.ui.action.HideValidStatusAction;
import org.bonitasoft.studio.migration.ui.action.ToggleLinkingAction;
import org.bonitasoft.studio.migration.ui.wizard.MigrationWarningWizard;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * @author Aurelien Pupier
 * @author Romain Bioteau
 */
public class MigrationStatusView extends ViewPart implements ISelectionListener, ISelectionChangedListener, ISelectionProvider {

    public static String ID = "org.bonitasoft.studio.migration.view";
    private TableViewer tableViewer;
    private ISelectionProvider selectionProvider;
    private ExportMigrationReportAsPDFAction exportAction;
    private String searchQuery;
    private ToggleLinkingAction linkAction;
    private Text descripitonText;

    @Override
    public void createPartControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 0, 5).create());

        createTopComposite(mainComposite);
        createTableComposite(mainComposite);
        createBottomComposite(mainComposite);

        final ISelectionService ss = getSite().getWorkbenchWindow().getSelectionService();
        ss.addPostSelectionListener(this);
        final IEditorPart activeEditor = getSite().getPage().getActiveEditor();
        if (activeEditor instanceof DiagramEditor) {
            selectionProvider = activeEditor.getEditorSite().getSelectionProvider();
        }
        getSite().setSelectionProvider(this);
        createActions();
    }

    protected void createActions() {
        final IEditorPart editor = getSite().getPage().getActiveEditor();

        final IActionBars actionBars = getViewSite().getActionBars();
        final IMenuManager dropDownMenu = actionBars.getMenuManager();
        final IToolBarManager toolBar = actionBars.getToolBarManager();
        exportAction = new ExportMigrationReportAsPDFAction();
        if (editor instanceof DiagramEditor) {
            exportAction.setReport(getReportFromEditor(editor));
        }
        exportAction.setViewer(tableViewer);
        dropDownMenu.add(exportAction);

        linkAction = new ToggleLinkingAction();
        linkAction.setChecked(true);
        linkAction.setViewer(tableViewer);
        linkAction.run();
        if (editor instanceof DiagramEditor) {
            linkAction.setEditor((DiagramEditor) editor);
        }
        toolBar.add(linkAction);

        final HideValidStatusAction hideStatusAction = new HideValidStatusAction();
        hideStatusAction.setViewer(tableViewer);
        dropDownMenu.add(hideStatusAction);

        final HideReviewedAction hideReviewedAction = new HideReviewedAction();
        hideReviewedAction.setViewer(tableViewer);
        dropDownMenu.add(hideReviewedAction);
    }

    protected void createBottomComposite(final Composite mainComposite) {
        final Composite bottomComposite = new Composite(mainComposite, SWT.NONE);
        bottomComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(5, 10, 0, 0).create());
        bottomComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        createHelpButton(bottomComposite);
        createMarkAsCompletedButton(bottomComposite);

    }

    private void createHelpButton(final Composite bottomComposite) {
        final ToolBar helpToolbar = new ToolBar(bottomComposite, SWT.NONE);
        helpToolbar.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).create());
        final ToolItem helpItem = new ToolItem(helpToolbar, SWT.PUSH);
        helpItem.setImage(JFaceResources.getImage(Dialog.DLG_IMG_HELP));
        helpItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                new WizardDialog(Display.getDefault().getActiveShell(), new MigrationWarningWizard()).open();
            }
        });
    }

    protected void createMarkAsCompletedButton(final Composite bottomComposite) {
        final Button markAsCompletedButton = new Button(bottomComposite, SWT.NONE);
        markAsCompletedButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).grab(true, false).create());
        markAsCompletedButton.setText(Messages.completeImport);
        markAsCompletedButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                final MessageDialogWithToggle mdwt = MessageDialogWithToggle.openOkCancelConfirm(
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                        Messages.completeImport,
                        Messages.completeImportMessage,
                        Messages.completeImportToggleMessage,
                        true,
                        MigrationPlugin.getDefault().getPreferenceStore(),
                        "toggleStateForImportExportStatus");
                if (IDialogConstants.OK_ID == mdwt.getReturnCode()) {
                    if (mdwt.getToggleState()) {
                        exportAction.run();
                    }
                    try {
                        clearMigrationReport(true);
                    } catch (final IOException e1) {
                        BonitaStudioLog.error(e1, MigrationPlugin.PLUGIN_ID);
                    }
                    final String id = BonitaPerspectivesUtils.getPerspectiveId((IEditorPart) tableViewer.getInput());
                    if (id != null) {
                        BonitaPerspectivesUtils.switchToPerspective(id);
                    }
                }

            }
        });
    }

    private void clearMigrationReport(final boolean save) throws IOException {
        final IEditorPart editorPart = (IEditorPart) tableViewer.getInput();
        if (editorPart != null && editorPart instanceof DiagramEditor) {
            final Resource emfResource = ((DiagramEditor) editorPart).getDiagramEditPart().getNotationView().eResource();
            final Report report = getMigrationReport(emfResource);
            if (report != null) {
                final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(emfResource);
                if (domain != null) {
                    domain.getCommandStack().execute(new RecordingCommand(domain) {

                        @Override
                        protected void doExecute() {
                            emfResource.getContents().remove(report);
                        }
                    });
                    if (save) {
                        final ICommandService service = (ICommandService) getSite().getService(ICommandService.class);
                        final Command cmd = service.getCommand("org.eclipse.ui.file.save");
                        try {
                            cmd.executeWithChecks(new ExecutionEvent());
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e, MigrationPlugin.PLUGIN_ID);
                        }
                    }
                }
            }
        }
    }

    private Report getMigrationReport(final Resource resource) {
        for (final EObject r : resource.getContents()) {
            if (r instanceof Report) {
                return (Report) r;
            }
        }
        return null;
    }

    protected void createTableComposite(final Composite mainComposite) {
        final Composite tableComposite = new Composite(mainComposite, SWT.NONE);
        tableComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        tableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        tableViewer = new TableViewer(tableComposite, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        tableViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_MIGRATION_REPORT_TABLE);
        tableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(400, SWT.DEFAULT).create());
        tableViewer.getTable().setHeaderVisible(true);
        tableViewer.getTable().setLinesVisible(true);
        tableViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return viewerSelect(element, searchQuery);
            }
        });

        addElementTypeColumn();
        addElementNameColumn();
        addPropertyColumn();
        addStatusColumn();
        addReviewedColumn();

        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(25));
        layout.addColumnData(new ColumnWeightData(25));
        layout.addColumnData(new ColumnWeightData(25));
        layout.addColumnData(new ColumnWeightData(10));
        layout.addColumnData(new ColumnWeightData(15));

        tableViewer.getTable().setLayout(layout);
        tableViewer.getTable().setLayoutDeferred(true);
        tableViewer.setContentProvider(new ReportContentProvider());
        final IEditorPart activeEditor = getSite().getPage().getActiveEditor();
        tableViewer.setInput(activeEditor);
        tableViewer.addSelectionChangedListener(this);
        tableViewer.getTable().addListener(SWT.MeasureItem, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                event.height = 25;
            }
        });

        tableViewer.getTable().addControlListener(new ControlListener() {

            @Override
            public void controlResized(final ControlEvent e) {
                if (tableViewer != null && !tableViewer.getTable().isDisposed() && tableViewer.getTable().getParent().getBounds().width > 300) {
                    Display.getDefault().asyncExec(new Runnable() {

                        @Override
                        public void run() {
                            if (!tableViewer.getTable().isDisposed()) {
                                tableViewer.getTable().setLayoutDeferred(false);
                                final Point oldSize = tableViewer.getTable().getSize();
                                final Point s = tableViewer.getTable().computeSize(SWT.DEFAULT, tableViewer.getTable().getBounds().height);
                                if (oldSize.x != s.x) {
                                    oldSize.x = s.x;
                                    tableViewer.getTable().setSize(oldSize);
                                }
                                tableViewer.getTable().layout(true, true);

                            }
                        }
                    });
                }
            }

            @Override
            public void controlMoved(final ControlEvent e) {

            }
        });

        ColumnViewerToolTipSupport.enableFor(tableViewer, ToolTip.NO_RECREATE);

        final Label descriptionLabel = new Label(tableComposite, SWT.NONE);
        descriptionLabel.setText(Messages.description);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        descripitonText = new Text(tableComposite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.READ_ONLY | SWT.WRAP);
        descripitonText.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        descripitonText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 60).create());

    }

    protected void addElementNameColumn() {
        final TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
        column.getColumn().setText(Messages.name);
        column.getColumn().setAlignment(SWT.LEFT);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element instanceof Change) {
                    return ((Change) element).getElementName();
                }
                return Messages.unknown;
            }

            @Override
            public String getToolTipText(final Object element) {
                if (element instanceof Change) {
                    return ((Change) element).getElementName();
                }

                return null;
            }

            @Override
            public int getToolTipTimeDisplayed(final Object object) {
                return 4000;
            }

            @Override
            public int getToolTipDisplayDelayTime(final Object object) {
                return 50;
            }

            @Override
            public Point getToolTipShift(final Object object) {
                return new Point(5, 5);
            }
        });
    }

    protected void addElementTypeColumn() {
        final TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
        column.getColumn().setText(Messages.elementType);
        column.getColumn().setAlignment(SWT.LEFT);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element instanceof Change) {
                    return ((Change) element).getElementType();
                }
                return Messages.unknown;
            }

            @Override
            public String getToolTipText(final Object element) {
                if (element instanceof Change) {
                    return ((Change) element).getElementType();
                }

                return null;
            }

            @Override
            public int getToolTipTimeDisplayed(final Object object) {
                return 4000;
            }

            @Override
            public int getToolTipDisplayDelayTime(final Object object) {
                return 50;
            }

            @Override
            public Point getToolTipShift(final Object object) {
                return new Point(5, 5);
            }
        });
    }

    protected void addPropertyColumn() {
        final TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
        column.getColumn().setText(Messages.property);
        column.getColumn().setAlignment(SWT.LEFT);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element instanceof Change) {
                    return ((Change) element).getPropertyName();
                }
                return Messages.unknown;
            }

            @Override
            public String getToolTipText(final Object element) {
                if (element instanceof Change) {
                    return ((Change) element).getPropertyName();
                }

                return null;
            }

            @Override
            public int getToolTipTimeDisplayed(final Object object) {
                return 4000;
            }

            @Override
            public int getToolTipDisplayDelayTime(final Object object) {
                return 50;
            }

            @Override
            public Point getToolTipShift(final Object object) {
                return new Point(5, 5);
            }
        });
    }

    protected void addStatusColumn() {
        final TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
        column.getColumn().setText(Messages.status);
        column.getColumn().setAlignment(SWT.CENTER);

        column.setLabelProvider(new StatusColumnLabelProvider());

    }

    protected void addReviewedColumn() {
        final TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
        column.getColumn().setText(Messages.reviewed);
        column.getColumn().setAlignment(SWT.CENTER);
        column.setLabelProvider(new ReviewStatusCheckboxLabelProvider(column.getViewer()));

        column.setEditingSupport(new EditingSupport(tableViewer) {

            @Override
            protected void setValue(final Object element, final Object value) {
                final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(element);
                if (editingDomain != null) {
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, MigrationReportPackage.Literals.CHANGE__REVIEWED, value));
                }
                getViewer().update(element, null);
            }

            @Override
            protected Object getValue(final Object element) {
                return ((Change) element).isReviewed();
            }

            @Override
            protected CellEditor getCellEditor(final Object element) {
                return new CheckboxCellEditor(tableViewer.getTable(), SWT.CHECK);
            }

            @Override
            protected boolean canEdit(final Object element) {
                return true;
            }
        });

        final TableColumnSorter sorter = new TableColumnSorter(tableViewer);
        sorter.setColumn(column.getColumn());
    }

    protected void createTopComposite(final Composite mainComposite) {
        final Composite topComposite = new Composite(mainComposite, SWT.NONE);
        topComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(5, 5, 5, 0).create());
        topComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        createFilterComposite(topComposite);

    }

    protected void createFilterComposite(final Composite topComposite) {
        final Text findText = new Text(topComposite, SWT.BORDER | SWT.SEARCH | SWT.ICON_CANCEL | SWT.ICON_SEARCH);
        findText.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).align(SWT.RIGHT, SWT.CENTER).hint(150, SWT.DEFAULT).create());
        findText.setMessage(Messages.find);
        findText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                searchQuery = findText.getText();
                tableViewer.refresh();

            }
        });
    }

    protected boolean viewerSelect(final Object element, final String searchQuery) {
        if (searchQuery == null || searchQuery.isEmpty()
                || ((Change) element).getElementType() != null && ((Change) element).getElementType().toLowerCase().contains(searchQuery.toLowerCase())
                || ((Change) element).getElementName() != null && ((Change) element).getElementName().toLowerCase().contains(searchQuery.toLowerCase())
                || ((Change) element).getPropertyName() != null && ((Change) element).getPropertyName().toLowerCase().contains(searchQuery.toLowerCase())) {
            return true;
        }
        return false;
    }

    @Override
    public void setFocus() {

    }

    @Override
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        if (selection instanceof StructuredSelection && !tableViewer.getTable().isDisposed()) {
            final Object selectedEP = ((StructuredSelection) selection).getFirstElement();
            if (selectedEP instanceof IGraphicalEditPart) {
                final IEditorPart editorPart = getSite().getPage().getActiveEditor();
                if (editorPart instanceof DiagramEditor && !editorPart.equals(tableViewer.getInput())) {
                    selectionProvider = editorPart.getEditorSite().getSelectionProvider();
                    tableViewer.setInput(editorPart);
                    exportAction.setReport(getReportFromEditor(editorPart));
                    linkAction.setEditor((DiagramEditor) editorPart);
                } else if (editorPart != null && editorPart.equals(tableViewer.getInput())) {
                    tableViewer.refresh();
                }
                tableViewer.getTable().layout(true, true);
            }
        }
    }

    private Report getReportFromEditor(final IEditorPart editorPart) {
        if (editorPart instanceof DiagramEditor) {
            final Resource resource = ((DiagramEditor) editorPart).getDiagramEditPart().getNotationView().eResource();
            if (resource != null) {
                for (final EObject r : resource.getContents()) {
                    if (r instanceof Report) {
                        return (Report) r;
                    }
                }
            }
        }
        return null;
    }

    private List<AbstractProcess> getProcesses(final IEditorPart editorPart) {
        if (editorPart instanceof DiagramEditor) {
            final DiagramEditor diagramEditor = (DiagramEditor) editorPart;
            final MainProcess diagram = (MainProcess) diagramEditor.getDiagramEditPart().resolveSemanticElement();
            final List<AbstractProcess> procs = ModelHelper.getAllProcesses(diagram);
            return procs;
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object getAdapter(final Class adapter) {
        if (adapter == IPropertySheetPage.class) {
            return getSite().getPage().getActiveEditor().getAdapter(adapter);
        } else if (adapter == IEditingDomainProvider.class) {
            return new IEditingDomainProvider() {

                @Override
                public EditingDomain getEditingDomain() {
                    final IEditorPart part = getSite().getPage().getActiveEditor();
                    if (part instanceof DiagramEditor) {
                        return ((DiagramEditor) part).getEditingDomain();
                    }
                    return null;
                }
            };
        }
        return super.getAdapter(adapter);
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        if (!event.getSelection().isEmpty()) {
            descripitonText.setText(((Change) ((IStructuredSelection) event.getSelection()).getFirstElement()).getDescription());
        }
    }

    @Override
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        if (selectionProvider != null) {
            selectionProvider.addSelectionChangedListener(listener);
        }
    }

    @Override
    public ISelection getSelection() {
        return selectionProvider != null ? selectionProvider.getSelection() : new StructuredSelection();
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

}
