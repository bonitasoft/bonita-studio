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
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
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
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


public abstract class OperationsComposite extends Composite implements IBonitaVariableContext {

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

    public OperationsComposite(final TabbedPropertySheetPage tabbedPropertySheetPage,
            final Composite mainComposite, final ViewerFilter actionExpressionFilter,
            final ViewerFilter storageExpressionFilter, final boolean isPageFlowContext) {
        super(mainComposite, SWT.NONE);
        parent = mainComposite;
        setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(0, 20, 0, 0).create());
        operationComposite = new Composite(this, SWT.NONE);
        operationComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(0, 5).create());
        operationComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Composite buttonComposite = new Composite(this, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        this.isPageFlowContext = isPageFlowContext;
        if (tabbedPropertySheetPage != null) {
            widgetFactory = tabbedPropertySheetPage.getWidgetFactory();
            if (widgetFactory != null) {
                widgetFactory.adapt(this);
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
            public void handleListChange(ListChangeEvent event) {
                for (final Object operation : event.getObservableList()) {
                    final int operationIndex = event.getObservableList().indexOf(operation);
                    if (operationViewers.size() > operationIndex) {
                        operationViewers.get(operationIndex).setOperation((Operation) operation);
                    }
                }
            }
        };
    }

    public OperationsComposite(final TabbedPropertySheetPage tabbedPropertySheetPage,
            final Composite mainComposite, final ViewerFilter actionExpressionFilter,
            final ViewerFilter storageExpressionFilter) {
        this(tabbedPropertySheetPage, mainComposite, actionExpressionFilter, storageExpressionFilter, false);

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
                final Operation action = createOperation();
                addLineUI(action);
                refresh();
            }

            private Operation createOperation() {
                final CompoundCommand cc = new CompoundCommand("Init Operands of Operation");

                final Operation action = ExpressionFactory.eINSTANCE.createOperation();
                final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
                operator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
                action.setOperator(operator);
                final EditingDomain domain = getEditingDomain();
                if (domain != null) {
                    domain.getCommandStack().execute(
                            AddCommand.create(domain, getEObject(),
                                    getActionTargetFeature(), action));
                } else {
                    getActions().add(action);
                }

                Expression actionExp = action.getRightOperand();
                if (actionExp == null) {
                    actionExp = createInitialRightOperand(cc, action);
                }
                final Expression storageExp = action.getLeftOperand();
                if (storageExp == null) {
                    createInitialLeftOperand(cc, action);
                }
                if (getEditingDomain() != null) {
                    getEditingDomain().getCommandStack().execute(cc);
                }

                return action;
            }

            private void createInitialLeftOperand(final CompoundCommand cc, final Operation action) {
                Expression storageExp;
                storageExp = ExpressionFactory.eINSTANCE.createExpression();
                if (getEditingDomain() != null) {
                    cc.append(
                            SetCommand.create(getEditingDomain(), action, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND, storageExp));
                } else {
                    action.setLeftOperand(storageExp);
                }
            }

            private Expression createInitialRightOperand(final CompoundCommand cc, final Operation action) {
                Expression actionExp;
                actionExp = ExpressionFactory.eINSTANCE.createExpression();
                if (actionExpressionFilter instanceof AvailableExpressionTypeFilter) {
                    final Set<String> possibleContentTypes = ((AvailableExpressionTypeFilter) actionExpressionFilter).getContentTypes();
                    if (!possibleContentTypes.contains(ExpressionConstants.CONSTANT_TYPE)) {
                        if (!possibleContentTypes.isEmpty()) {
                            actionExp.setType(possibleContentTypes.iterator().next());
                        }
                    }
                }
                if (getEditingDomain() != null) {
                    cc.append(
                            SetCommand.create(getEditingDomain(), action, ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND, actionExp));
                } else {
                    action.setRightOperand(actionExp);
                }
                return actionExp;
            }
        });
    }

    protected EditingDomain getEditingDomain() {
        return TransactionUtil.getEditingDomain(getEObject());
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

    private void removeLine(final int i) {
        final int lineNumber = removeLineUI(i);
        final EditingDomain domain = getEditingDomain();
        if (domain != null) {
            getEditingDomain().getCommandStack().execute(
                    RemoveCommand.create(getEditingDomain(), getEObject(),
                            getActionTargetFeature(),
                            getActions().get(lineNumber)));
        } else {
            getActions().remove(i);
        }
        updateOrderButtons();
        refresh();
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
                final EList<Operation> actions = (EList<Operation>) getActions();
                final EditingDomain editingDomain = getEditingDomain();
                if (editingDomain != null) {
                    editingDomain.getCommandStack()
                            .execute(MoveCommand.create(getEditingDomain(), getEObject(), getActionTargetFeature(), actions.get(index), index - 1));
                } else {
                    actions.move(index - 1, actions.get(index));
                }
            }

        });
        final ToolItem moveDown = new ToolItem(moveTB, SWT.FLAT | SWT.NO_FOCUS);
        moveDown.setImage(Pics.getImage(PicsConstants.arrowDownOrder));
        moveDown.setToolTipText(Messages.downRowSort);
        moveDown.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final EList<Operation> actions = (EList<Operation>) getActions();
                final int index = moveToolbars.indexOf(moveTB);
                final EditingDomain editingDomain = getEditingDomain();
                if (editingDomain != null) {
                    getEditingDomain().getCommandStack()
                            .execute(MoveCommand.create(getEditingDomain(), getEObject(), getActionTargetFeature(), actions.get(index), index + 1));
                } else {
                    actions.move(index + 1, actions.get(index));
                }
            }
        });
        return moveTB;
    }

    protected OperationViewer createOperationViewer() {
        final OperationViewer viewer = new OperationViewer(operationComposite, widgetFactory, getEditingDomain(), actionExpressionFilter,
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
                removeLine(removes.indexOf(e.getSource()));
            }
        });
        opViewer.layout(true, true);
        return remove;
    }

    /**
     * add lines from the form
     */
    public void fillTable() {
        final IObservableList observableList = CustomEMFEditObservables.observeList(Realm.getDefault(), getEObject(), getActionTargetFeature());
        observableList.removeListChangeListener(operationListlistener);
        observableList.addListChangeListener(operationListlistener);
        for (final Operation action : getActions()) {
            addLineUI(action);
        }
        updateOrderButtons();
    }

    private void updateOrderButtons() {
        for (final ToolBar tb : moveToolbars) {
            if (moveToolbars.indexOf(tb) == getActions().size() - 1 && moveToolbars.indexOf(tb) == 0) {
                tb.getItem(0).setEnabled(false);
                tb.getItem(1).setEnabled(false);
            } else if (moveToolbars.indexOf(tb) == 0) {
                tb.getItem(1).setEnabled(true);
                tb.getItem(0).setEnabled(false);
            } else if (moveToolbars.indexOf(tb) == getActions().size() - 1) {
                tb.getItem(1).setEnabled(false);
                tb.getItem(0).setEnabled(true);
            } else {
                tb.getItem(0).setEnabled(true);
                tb.getItem(1).setEnabled(true);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @seeorg.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
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

    public abstract void refresh();

    protected EReference getActionTargetFeature() {
        return operationContainmentFeature;
    }

    @SuppressWarnings("unchecked")
    private List<Operation> getActions() {
        final EObject eObject = getEObject();
        return (List<Operation>) eObject.eGet(getActionTargetFeature());
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

}
