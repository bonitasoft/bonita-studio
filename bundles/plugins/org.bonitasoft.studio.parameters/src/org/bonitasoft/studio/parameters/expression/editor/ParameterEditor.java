/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.expression.editor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterNameLabelProvider;
import org.bonitasoft.studio.parameters.wizard.page.AddParameterWizard;
import org.bonitasoft.studio.parameters.wizard.page.ParameterWizardDialog;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 * 
 */
public class ParameterEditor extends SelectionAwareExpressionEditor implements
        IExpressionEditor {

    private TableViewer viewer;

    private GridLayout gridLayout;

    private Expression editorInputExpression;

    private Composite mainComposite;

    private Text typeText;

    private Button addExpressionButton;

    private boolean isPageFlowContext = false;

    @Override
    public Control createExpressionEditor(Composite parent, EMFDataBindingContext ctx) {

        createTableViewer(parent);

        createReturnTypeComposite(parent);

        createAddExpressionButton(parent);

        return mainComposite;
    }

    private void createTableViewer(Composite parent) {
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        gridLayout = new GridLayout(1, false);
        mainComposite.setLayout(gridLayout);
        viewer = new TableViewer(mainComposite, SWT.FULL_SELECTION | SWT.BORDER
                | SWT.SINGLE | SWT.V_SCROLL);
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100, false));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).create());

        TableViewerColumn columnViewer = new TableViewerColumn(viewer, SWT.NONE);
        TableColumn column = columnViewer.getColumn();
        column.setText(Messages.name);

        TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(column);

        viewer.getTable().setHeaderVisible(true);
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new ParameterNameLabelProvider());

        viewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (!event.getSelection().isEmpty()) {
                    ParameterEditor.this.fireSelectionChanged();
                }
            }
        });
    }

    private void createAddExpressionButton(Composite parent) {
        addExpressionButton = new Button(parent, SWT.FLAT);
        addExpressionButton.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.LEFT, SWT.CENTER).hint(85, SWT.DEFAULT).create());
        addExpressionButton.setText(Messages.addData);
    }

    protected void createReturnTypeComposite(Composite parent) {
        Composite typeComposite = new Composite(parent, SWT.NONE);
        typeComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        GridLayout gl = new GridLayout(2, false);
        gl.marginWidth = 0;
        gl.marginHeight = 0;
        typeComposite.setLayout(gl);

        Label typeLabel = new Label(typeComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.FILL, SWT.CENTER).create());

        typeText = new Text(typeComposite, SWT.BORDER | SWT.READ_ONLY);
        typeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .align(SWT.FILL, SWT.CENTER).create());

    }

    protected void handleSpecificDatatypeEdition(Data data) {
        if (gridLayout.numColumns > 1) {
            mainComposite.getChildren()[1].dispose();
            gridLayout.numColumns--;
            viewer.getTable().setLayoutData(
                    GridDataFactory.fillDefaults().grab(true, true).create());
            mainComposite.layout();
        }

    }

    private void fillViewerInput(EObject context) {
        Set<Parameter> input = new HashSet<Parameter>();
        IExpressionProvider provider = ExpressionEditorService.getInstance()
                .getExpressionProvider(ExpressionConstants.PARAMETER_TYPE);
        for (Expression e : provider.getExpressions(context)) {
            if (editorInputExpression.isReturnTypeFixed()) {
                if (compatibleReturnType(editorInputExpression, e)) {
                    input.add((Parameter) e.getReferencedElements().get(0));
                }
            } else {
                input.add((Parameter) e.getReferencedElements().get(0));
            }
        }
        viewer.setInput(input);
    }

    private void expressionButtonListener(EObject context) {
        ParameterWizardDialog parameterDialog = new ParameterWizardDialog(
                Display.getCurrent().getActiveShell(), new AddParameterWizard(ModelHelper.getParentProcess(context), TransactionUtil.getEditingDomain(context)));
        if (parameterDialog.open() == Dialog.OK) {
            fillViewerInput(context);
        }
    }

    @Override
    public void bindExpression(EMFDataBindingContext dataBindingContext,
            EObject context, Expression inputExpression, ViewerFilter[] filters, ExpressionViewer expressionViewer) {
        final EObject finalContext = context;
        addExpressionButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                expressionButtonListener(finalContext);
            }
        });
        editorInputExpression = inputExpression;
        fillViewerInput(context);

        IObservableValue contentObservable = EMFObservables
                .observeValue(inputExpression,
                        ExpressionPackage.Literals.EXPRESSION__CONTENT);
        IObservableValue nameObservable = EMFObservables.observeValue(
                inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME);
        IObservableValue returnTypeObservable = EMFObservables.observeValue(
                inputExpression,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        IObservableValue referenceObservable = EMFObservables.observeValue(
                inputExpression,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);

        UpdateValueStrategy selectionToName = new UpdateValueStrategy();
        IConverter nameConverter = new Converter(Parameter.class, String.class) {

            @Override
            public Object convert(Object parameter) {
                return ((Parameter) parameter).getName();
            }

        };
        selectionToName.setConverter(nameConverter);

        UpdateValueStrategy selectionToContent = new UpdateValueStrategy();
        IConverter contentConverter = new Converter(Parameter.class,
                String.class) {

            @Override
            public Object convert(Object parameter) {
                return ((Parameter) parameter).getName();
            }

        };
        selectionToContent.setConverter(contentConverter);

        UpdateValueStrategy selectionToReturnType = new UpdateValueStrategy();
        IConverter returnTypeConverter = new Converter(Parameter.class,
                String.class) {

            @Override
            public Object convert(Object parameter) {
                return ((Parameter) parameter).getTypeClassname();
            }

        };
        selectionToReturnType.setConverter(returnTypeConverter);

        UpdateValueStrategy selectionToReferencedData = new UpdateValueStrategy();
        IConverter referenceConverter = new Converter(Parameter.class,
                List.class) {

            @Override
            public Object convert(Object parameter) {
                return Collections.singletonList(parameter);
            }

        };
        selectionToReferencedData.setConverter(referenceConverter);

        UpdateValueStrategy referencedDataToSelection = new UpdateValueStrategy();
        IConverter referencetoDataConverter = new Converter(List.class,
                Parameter.class) {

            @SuppressWarnings("unchecked")
            @Override
            public Object convert(Object parameterList) {
                Parameter p = ((List<Parameter>) parameterList).get(0);
                Collection<Parameter> inputParameters = (Collection<Parameter>) viewer
                        .getInput();
                for (Parameter param : inputParameters) {
                    if (param.getName().equals(p.getName())
                            && param.getTypeClassname().equals(
                                    p.getTypeClassname())) {
                        return param;
                    }
                }
                return null;
            }

        };
        referencedDataToSelection.setConverter(referencetoDataConverter);

        dataBindingContext.bindValue(ViewersObservables
                .observeSingleSelection(viewer), nameObservable,
                selectionToName, new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(ViewersObservables
                .observeSingleSelection(viewer), contentObservable,
                selectionToContent, new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(
                ViewersObservables.observeSingleSelection(viewer),
                returnTypeObservable, selectionToReturnType,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(
                ViewersObservables.observeSingleSelection(viewer),
                referenceObservable, selectionToReferencedData,
                referencedDataToSelection);
        dataBindingContext.bindValue(
                SWTObservables.observeText(typeText, SWT.Modify),
                returnTypeObservable);
    }

    @Override
    public boolean canFinish() {
        return !viewer.getSelection().isEmpty();
    }

    @Override
    public void okPressed() {
        if (!editorInputExpression.getContent().equals(
                editorInputExpression.getName())) {
            editorInputExpression.setName(editorInputExpression.getContent());
        }
    }

    @Override
    public Control getTextControl() {
        return null;
    }

    @Override
    public boolean isPageFlowContext() {

        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(boolean isOverviewContext) {
    }

}
