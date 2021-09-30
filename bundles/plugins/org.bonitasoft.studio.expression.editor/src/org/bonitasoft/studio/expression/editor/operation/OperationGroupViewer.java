/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.properties.BusyFormHandler;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.OperationContainer;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class OperationGroupViewer implements IBonitaVariableContext, ISelectionProvider {

    public static final String SWTBOT_ID_REMOVE_LINE = "actionLinesCompositeRemoveButton";
    protected List<OperationViewer> operationViewers = new ArrayList<>();
    protected List<Button> removes = new ArrayList<>();
    protected List<ToolBar> moveToolbars = new ArrayList<>();
    protected TabbedPropertySheetPage tabbedPropertySheetPage;
    protected EMFDataBindingContext context;
    protected ArrayList<List<Binding>> bindings = new ArrayList<>();
    private TabbedPropertySheetWidgetFactory widgetFactory;
    private EObject eObject;
    protected final Composite parent;
    private EReference operationContainmentFeature;
    private final ViewerFilter storageExpressionFilter;
    private final ViewerFilter actionExpressionFilter;
    private IExpressionNatureProvider storageExpressionNatureProvider;
    private IExpressionNatureProvider actionExpressionNatureProvider;
    private final List<IExpressionValidator> validators = new ArrayList<>();
    private EObject eObjectContext;
    private boolean isPageFlowContext = false;
    private final Composite operationComposite;
    private final IListChangeListener operationListlistener;
    protected Composite container;
    private Object input;
    private EditingDomain editingDomain;
    private ISelection selection;
    private final List<ISelectionChangedListener> listeners = new ArrayList<>();
    private BusyFormHandler busyFormHandler;

    public OperationGroupViewer(final TabbedPropertySheetPage tabbedPropertySheetPage,
            final Composite mainComposite, final ViewerFilter actionExpressionFilter,
            final ViewerFilter storageExpressionFilter,
            final boolean isPageFlowContext,
            final BusyFormHandler busyFormHandler) {
        parent = mainComposite;
        this.busyFormHandler = busyFormHandler;
        container = new Composite(mainComposite, SWT.NONE);
        container.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(0, 20, 0, 0).create());
        operationComposite = new Composite(container, SWT.NONE);
        operationComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(0, 5).create());
        operationComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Composite buttonComposite = new Composite(container, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        this.isPageFlowContext = isPageFlowContext;
        if (tabbedPropertySheetPage != null) {
            widgetFactory = tabbedPropertySheetPage.getWidgetFactory();
            if (widgetFactory != null) {
                widgetFactory.adapt(container);
                widgetFactory.adapt(operationComposite);
                widgetFactory.adapt(buttonComposite);
            }
            this.tabbedPropertySheetPage = tabbedPropertySheetPage;
        }
        this.actionExpressionFilter = actionExpressionFilter;
        this.storageExpressionFilter = storageExpressionFilter;

        createAddButton(buttonComposite);

        operationListlistener = new IListChangeListener() {

            @Override
            public void handleListChange(final ListChangeEvent event) {
                if (busyFormHandler != null) {
                    busyFormHandler.setBusy(true);
                }
                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        final IObservableList observableList = event.getObservableList();
                        while (operationViewers.size() != observableList.size()) {
                            if (operationViewers.size() > observableList.size()) {
                                removeLineUI(operationViewers.size() - 1);
                            } else if (operationViewers.size() < observableList.size()) {
                                addLineUI((Operation) observableList.get(operationViewers.size()));
                            }
                        }
                        for (final Object operation : observableList) {
                            final int operationIndex = observableList.indexOf(operation);
                            operationViewers.get(operationIndex).setOperation((Operation) operation);
                            operationViewers.get(operationIndex).setEObject(getEObject());
                        }
                        updateOrderButtons();
                        refresh();
                        if (busyFormHandler != null) {
                            busyFormHandler.setBusy(false);
                        }
                    }
                });

            }
        };
    }

    public OperationGroupViewer(final TabbedPropertySheetPage tabbedPropertySheetPage,
            final Composite mainComposite, final ViewerFilter actionExpressionFilter,
            final ViewerFilter storageExpressionFilter) {
        this(tabbedPropertySheetPage, mainComposite, actionExpressionFilter, storageExpressionFilter, false, null);
    }

    private void createAddButton(final Composite mainComposite) {
        Button addButton = null;
        if (widgetFactory != null) {
            addButton = widgetFactory.createButton(mainComposite, "", SWT.PUSH);
        } else {
            addButton = new Button(mainComposite, SWT.PUSH);
        }
        addButton.setText(Messages.addAction);
        addButton.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.BEGINNING, SWT.TOP).hint(85, SWT.DEFAULT).create());
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                createOperation();
            }

            private Operation createOperation() {
                final CompoundCommand cc = new CompoundCommand("Init Operands of Operation");

                final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
                final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
                operator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
                operation.setOperator(operator);

                Expression actionExp = operation.getRightOperand();
                if (actionExp == null) {
                    actionExp = createInitialRightOperand(cc, operation);
                }
                final Expression storageExp = operation.getLeftOperand();
                if (storageExp == null) {
                    createInitialLeftOperand(cc, operation);
                }
                if (getEditingDomain() != null) {
                    getEditingDomain().getCommandStack().execute(cc);
                }
                getOperations().add(operation);
                return operation;
            }

            private void createInitialLeftOperand(final CompoundCommand cc, final Operation action) {
                Expression storageExp;
                storageExp = ExpressionFactory.eINSTANCE.createExpression();
                if (getEditingDomain() != null) {
                    cc.append(
                            SetCommand.create(getEditingDomain(), action,
                                    ExpressionPackage.Literals.OPERATION__LEFT_OPERAND,
                                    storageExp));
                } else {
                    action.setLeftOperand(storageExp);
                }
            }

            private Expression createInitialRightOperand(final CompoundCommand cc, final Operation action) {
                Expression actionExp;
                actionExp = ExpressionFactory.eINSTANCE.createExpression();
                if (actionExpressionFilter instanceof AvailableExpressionTypeFilter) {
                    final Set<String> possibleContentTypes = ((AvailableExpressionTypeFilter) actionExpressionFilter)
                            .getContentTypes();
                    if (!possibleContentTypes.contains(ExpressionConstants.CONSTANT_TYPE)) {
                        if (!possibleContentTypes.isEmpty()) {
                            actionExp.setType(possibleContentTypes.iterator().next());
                        }
                    }
                }
                if (getEditingDomain() != null) {
                    cc.append(
                            SetCommand.create(getEditingDomain(), action,
                                    ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND, actionExp));
                } else {
                    action.setRightOperand(actionExp);
                }
                return actionExp;
            }
        });
    }

    protected EditingDomain getEditingDomain() {
        return editingDomain;
    }

    public void setEditingDomain(EditingDomain editingDomain) {
        this.editingDomain = editingDomain;
        for (final OperationViewer v : operationViewers) {
            v.setEditingDomain(editingDomain);
        }
    }

    public int getNbLines() {
        return operationViewers.size();
    }

    public void removeLinesUI() {
        if (!operationViewers.isEmpty()) {
            for (int i = operationViewers.size() - 1; i >= 0; i--) {
                removeLineUI(i);
            }
            refresh();
        }
    }

    protected int removeLineUI(final int i) {
        final int lineNumber = i;
        removes.get(lineNumber).dispose();
        removes.remove(lineNumber);
        final OperationViewer operationViewer = operationViewers.get(lineNumber);
        operationViewer.dispose();
        operationViewers.remove(lineNumber);
        final ToolBar toolBar = moveToolbars.remove(lineNumber);
        toolBar.dispose();
        return lineNumber;
    }

    public void addLineUI(final Operation operation) {
        moveToolbars.add(createMoveToolbar());
        final OperationViewer opViewer = createOperationViewer();
        opViewer.setOperation(operation);
        opViewer.setEObject(eObject);
        opViewer.createDatabinding(context);
        operationViewers.add(opViewer);
        removes.add(createRemoveButton(opViewer));
        updateOrderButtons();
    }

    private ToolBar createMoveToolbar() {
        final ToolBar moveTB = new ToolBar(operationComposite, SWT.FLAT | SWT.NO_FOCUS | SWT.VERTICAL);
        moveTB.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).create());
        if (widgetFactory != null) {
            widgetFactory.adapt(moveTB);
        }
        final ToolItem moveTop = new ToolItem(moveTB, SWT.FLAT | SWT.NO_FOCUS);
        moveTop.setImage(Pics.getImage(PicsConstants.arrowUpOrder));
        moveTop.setToolTipText(Messages.upRowSort);
        moveTop.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final int index = moveToolbars.indexOf(moveTB);
                getOperations().move(index, index - 1);
            }

        });
        final ToolItem moveDown = new ToolItem(moveTB, SWT.FLAT | SWT.NO_FOCUS);
        moveDown.setImage(Pics.getImage(PicsConstants.arrowDownOrder));
        moveDown.setToolTipText(Messages.downRowSort);
        moveDown.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final int index = moveToolbars.indexOf(moveTB);
                getOperations().move(index, index + 1);
            }
        });
        return moveTB;
    }

    protected OperationViewer createOperationViewer() {
        final OperationViewer viewer = new OperationViewer(operationComposite, widgetFactory, editingDomain,
                actionExpressionFilter,
                storageExpressionFilter, isPageFlowContext);
        viewer.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());
        if (eObjectContext != null) {
            viewer.setContext(eObjectContext);
        }
        if (storageExpressionNatureProvider != null) {
            viewer.setStorageExpressionNatureProvider(storageExpressionNatureProvider);
        }
        if (actionExpressionNatureProvider != null) {
            viewer.setActionExpressionNatureProvider(actionExpressionNatureProvider);
        }
        for (final IExpressionValidator validator : validators) {
            viewer.addActionExpressionValidator(validator);
        }
        return viewer;
    }

    public void setContext(final EObject context) {
        eObjectContext = context;
    }

    public void addActionExpressionValidator(final IExpressionValidator validator) {
        validators.add(validator);
    }

    protected Button createRemoveButton(final OperationViewer opViewer) {
        final GridLayout gridLayout = (GridLayout) opViewer.getLayout();
        gridLayout.numColumns++;
        final Button remove = new Button(opViewer, SWT.FLAT);
        if (widgetFactory != null) {
            widgetFactory.adapt(remove, false, false);
        }
        remove.setData("org.eclipse.swtbot.widget.key", SWTBOT_ID_REMOVE_LINE);
        remove.setLayoutData(GridDataFactory.swtDefaults().create());
        remove.setImage(Pics.getImage("delete.png"));
        remove.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                getOperations().remove(removes.indexOf(e.getSource()));
            }
        });
        return remove;
    }

    private void updateOrderButtons() {
        for (final ToolBar tb : moveToolbars) {
            if (moveToolbars.indexOf(tb) == getOperations().size() - 1 && moveToolbars.indexOf(tb) == 0) {
                tb.getItem(0).setEnabled(false);
                tb.getItem(1).setEnabled(false);
            } else if (moveToolbars.indexOf(tb) == 0) {
                tb.getItem(1).setEnabled(true);
                tb.getItem(0).setEnabled(false);
            } else if (moveToolbars.indexOf(tb) == getOperations().size() - 1) {
                tb.getItem(1).setEnabled(false);
                tb.getItem(0).setEnabled(true);
            } else {
                tb.getItem(0).setEnabled(true);
                tb.getItem(1).setEnabled(true);
            }
        }
    }

    public void dispose() {
        for (final OperationViewer text : operationViewers) {
            if (text != null) {
                text.dispose();
            }
        }
        for (final Button toDispose : removes) {
            if (toDispose != null && !toDispose.isDisposed()) {
                toDispose.dispose();
            }
        }
        getOperations().removeListChangeListener(operationListlistener);
    }

    public void setContext(final EMFDataBindingContext emfDataBindingContext) {
        context = emfDataBindingContext;
    }

    public void setEObject(final EObject eObject) {
        this.eObject = eObject;
        if (eObject instanceof AbstractCatchMessageEvent) {
            setOperationContainmentFeature(ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT);
        } else if (eObject instanceof Connector) {
            setOperationContainmentFeature(ProcessPackage.Literals.CONNECTOR__OUTPUTS);
        } else if (eObject instanceof OperationContainer) {
            setOperationContainmentFeature(ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS);
        }
    }

    public void setOperationContainmentFeature(final EReference actionTargetFeature) {
        operationContainmentFeature = actionTargetFeature;
    }

    public EObject getEObject() {
        if (eObject instanceof Lane) {
            return getEObject().eContainer();
        }
        return eObject;
    }

    public void refresh() {
        if (!parent.isDisposed()) {
            final Composite shell = parent.getParent().getParent().getParent().getParent();
            shell.layout(true, true);
            if (tabbedPropertySheetPage != null) {
                tabbedPropertySheetPage.resizeScrolledComposite();
            }
        }
    }

    @Override
    public boolean isOverViewContext() {
        return false;
    }

    @Override
    public void setIsOverviewContext(boolean isOverviewContext) {
        //NOTHING TO DO
    }

    protected EReference getOperationContainmentFeature() {
        return operationContainmentFeature;
    }

    private IObservableList getOperations() {
        return (IObservableList) input;
    }

    /**
     * Set a specify IExpressionNatureProvider for the left operand
     *
     * @param dataFeature
     */
    public void setStorageExpressionNatureContentProvider(
            final IExpressionNatureProvider provider) {
        storageExpressionNatureProvider = provider;
    }

    public void setActionExpressionNatureContentProvider(
            final IExpressionNatureProvider provider) {
        actionExpressionNatureProvider = provider;
    }

    @Override
    public boolean isPageFlowContext() {
        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;

    }

    public void refreshViewers() {
        for (final OperationViewer v : operationViewers) {
            v.refresh();
        }
    }

    public List<OperationViewer> getOperationViewers() {
        return operationViewers;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.Viewer#getControl()
     */
    public Control getControl() {
        return container;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.Viewer#getInput()
     */
    public Object getInput() {
        return input;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.Viewer#setInput(java.lang.Object)
     */
    public void setInput(Object input) {
        final Control control = getControl();
        if (control == null || control.isDisposed()) {
            throw new IllegalStateException(
                    "Need an underlying widget to be able to set the input." + //$NON-NLS-1$
                            "(Has the widget been disposed?)"); //$NON-NLS-1$
        }
        this.input = input;
        if (input instanceof IObservableList) {
            final IObservableList observableList = (IObservableList) input;
            observableList.removeListChangeListener(operationListlistener);
            observableList.addListChangeListener(operationListlistener);
        }

    }

    @Override
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ISelectionProvider#getSelection()
     */
    @Override
    public ISelection getSelection() {
        return selection;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ISelectionProvider#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(ISelection selection) {
        this.selection = selection;
        if (!selection.isEmpty()) {
            final EObject eObject = (EObject) ((IStructuredSelection) selection).getFirstElement();
            setEObject(eObject);
            setEditingDomain(TransactionUtil.getEditingDomain(eObject));
            for (final OperationViewer v : operationViewers) {
                v.setEObject(eObject);
            }
        }

    }

}
