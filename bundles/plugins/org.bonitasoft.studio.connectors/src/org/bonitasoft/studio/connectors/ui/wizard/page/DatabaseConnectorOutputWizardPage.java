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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.wizard.page.sqlutil.SQLQueryUtil;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationsComposite;
import org.bonitasoft.studio.expression.editor.operation.WizardPageOperationsComposite;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.viewer.ReadOnlyExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class DatabaseConnectorOutputWizardPage extends AbstractConnectorOutputWizardPage
        implements DatabaseConnectorConstants, IValueChangeListener {

    protected String outputType;
    protected StackLayout stackLayout;
    protected Composite defaultOutputComposite;
    private OperationsComposite lineComposite;
    protected Composite singleOutputComposite;
    private final AvailableExpressionTypeFilter leftFilter;
    protected AvailableExpressionTypeFilter rightFilter;
    protected Composite topComposite;
    protected Text singleColumnText;
    protected Composite oneRowNColsOutputComposite;
    protected ScrolledComposite oneRowNColsscrolledComposite;
    protected Composite nRowsOneColumOutputComposite;
    protected Text nRowsOneColumnColumnText;
    protected Composite nRowsNColumsOutputComposite;

    public DatabaseConnectorOutputWizardPage() {
        super();
        setTitle(Messages.outputOperationsDefinitionTitle);
        leftFilter = new AvailableExpressionTypeFilter(new String[] {
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.DOCUMENT_REF_TYPE });
        rightFilter = new AvailableExpressionTypeFilter(new String[] {
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.CONNECTOR_OUTPUT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.DOCUMENT_TYPE
        });
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.connectors.ui.wizard.page.AbstractConnectorOutputWizardPage#doCreateControl(org.eclipse.swt.
     * widgets.Composite, org.eclipse.emf.databinding.EMFDataBindingContext)
     */
    @Override
    protected Control doCreateControl(final Composite parent,
            final EMFDataBindingContext context) {
        topComposite = new Composite(parent, SWT.NONE);
        topComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        stackLayout = new StackLayout();
        topComposite.setLayout(stackLayout);

        defaultOutputComposite = createDefaultOuputControl(topComposite, context);
        singleOutputComposite = createSingleOuputControl(topComposite, context);
        oneRowNColsOutputComposite = createOneRowNColsOuputControl(topComposite, context);
        nRowsOneColumOutputComposite = createNRowsOneColumOuputControl(topComposite, context);
        nRowsNColumsOutputComposite = createNRowsNColsOuputControl(topComposite, context);

        updateStackLayout();

        final ConnectorConfiguration configuration = getConnector().getConfiguration();
        final Expression outputTypeExpression = (Expression) getConnectorParameter(configuration, getInput(OUTPUT_TYPE_KEY))
                .getExpression();
        if (outputTypeExpression != null) {
            final IObservableValue outputTypeObservalbe = EMFObservables.observeValue(outputTypeExpression,
                    ExpressionPackage.Literals.EXPRESSION__CONTENT);
            outputTypeObservalbe.addValueChangeListener(this);
        }
        return topComposite;
    }

    protected Composite createNRowsOneColumOuputControl(final Composite parent,
            final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(20, 20).create());

        Operation singleModeOuputOperation = getOuputOperationFor(NROW_ONECOL_RESULT_OUTPUT);
        if (singleModeOuputOperation == null) {
            singleModeOuputOperation = createDefaultOutput(NROW_ONECOL_RESULT_OUTPUT, getDefinition());
            getConnector().getOutputs().add(singleModeOuputOperation);
        }
        singleModeOuputOperation.getLeftOperand().setReturnType(Collection.class.getName());
        singleModeOuputOperation.getLeftOperand().setReturnTypeFixed(true);

        final ReadOnlyExpressionViewer targetDataExpressionViewer = new ReadOnlyExpressionViewer(mainComposite, SWT.BORDER,
                null, null, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
        targetDataExpressionViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(250, SWT.DEFAULT).create());

        final IExpressionNatureProvider storageExpressionProvider = getStorageExpressionProvider();
        if (storageExpressionProvider != null) {
            targetDataExpressionViewer.setExpressionNatureProvider(storageExpressionProvider);
        }
        targetDataExpressionViewer.addFilter(leftFilter);
        targetDataExpressionViewer.setContext(getElementContainer());
        targetDataExpressionViewer.setInput(singleModeOuputOperation);

        context.bindValue(ViewersObservables.observeSingleSelection(targetDataExpressionViewer),
                EMFObservables.observeValue(singleModeOuputOperation, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND));

        final Label takeValueOfLabel = new Label(mainComposite, SWT.NONE);
        takeValueOfLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
        takeValueOfLabel.setText(Messages.takeValueOf);

        nRowsOneColumnColumnText = new Text(mainComposite, SWT.BORDER | SWT.READ_ONLY);
        nRowsOneColumnColumnText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label hintLabel = new Label(mainComposite, SWT.WRAP);
        hintLabel.setLayoutData(GridDataFactory.swtDefaults().span(3, 1).create());
        hintLabel.setText(Messages.nRowsOneColOutputHint);

        return mainComposite;
    }

    protected Composite createNRowsNColsOuputControl(final Composite parent,
            final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(20, 20).create());

        Operation singleModeOuputOperation = getOuputOperationFor(TABLE_RESULT_OUTPUT);
        if (singleModeOuputOperation == null) {
            singleModeOuputOperation = createDefaultOutput(TABLE_RESULT_OUTPUT, getDefinition());
            getConnector().getOutputs().add(singleModeOuputOperation);
        }
        singleModeOuputOperation.getLeftOperand().setReturnType(Collection.class.getName());
        singleModeOuputOperation.getLeftOperand().setReturnTypeFixed(true);

        final ReadOnlyExpressionViewer targetDataExpressionViewer = new ReadOnlyExpressionViewer(mainComposite, SWT.BORDER,
                null, null, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
        targetDataExpressionViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(250, SWT.DEFAULT).create());
        final IExpressionNatureProvider storageExpressionProvider = getStorageExpressionProvider();
        if (storageExpressionProvider != null) {
            targetDataExpressionViewer.setExpressionNatureProvider(storageExpressionProvider);
        }
        targetDataExpressionViewer.addFilter(leftFilter);
        targetDataExpressionViewer.setContext(getElementContainer());
        targetDataExpressionViewer.setInput(singleModeOuputOperation);

        context.bindValue(ViewersObservables.observeSingleSelection(targetDataExpressionViewer),
                EMFObservables.observeValue(singleModeOuputOperation, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND));

        final Label takeValueOfLabel = new Label(mainComposite, SWT.NONE);
        takeValueOfLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
        takeValueOfLabel.setText(Messages.takeValueOf);

        final Text nRowsNColumnsColumnText = new Text(mainComposite, SWT.BORDER | SWT.READ_ONLY);
        nRowsNColumnsColumnText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        nRowsNColumnsColumnText.setText(singleModeOuputOperation.getRightOperand().getName());

        final Label hintLabel = new Label(mainComposite, SWT.WRAP);
        hintLabel.setLayoutData(GridDataFactory.swtDefaults().span(3, 1).create());
        hintLabel.setText(Messages.nRowsNColsOutputHint);

        return mainComposite;
    }

    protected Composite createOneRowNColsOuputControl(final Composite parent,
            final EMFDataBindingContext context) {
        oneRowNColsscrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);
        oneRowNColsscrolledComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        return oneRowNColsscrolledComposite;
    }

    protected void buildListOfOutputForOneRowNCols(final ScrolledComposite scrolledComposite,
            final EMFDataBindingContext context) {
        if (scrolledComposite.getContent() != null) {
            scrolledComposite.getContent().dispose();
            scrolledComposite.setContent(null);
        }
        final Composite oneRowNColsComposite = new Composite(scrolledComposite, SWT.NONE);
        oneRowNColsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        oneRowNColsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).create());

        final ConnectorConfiguration configuration = getConnector().getConfiguration();
        final Expression scriptExpression = (Expression) getConnectorParameter(configuration, getInput(SCRIPT_KEY))
                .getExpression();
        final List<Operation> operations = getOuputOperationsFor(ONEROW_NCOL_RESULT_OUTPUT, scriptExpression);
        for (final Operation op : operations) {
            final ReadOnlyExpressionViewer targetDataExpressionViewer = new ReadOnlyExpressionViewer(oneRowNColsComposite,
                    SWT.BORDER, null, null, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
            targetDataExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
            final IExpressionNatureProvider storageExpressionProvider = getStorageExpressionProvider();
            if (storageExpressionProvider != null) {
                targetDataExpressionViewer.setExpressionNatureProvider(storageExpressionProvider);
            }
            targetDataExpressionViewer.addFilter(leftFilter);
            targetDataExpressionViewer.setContext(getElementContainer());
            targetDataExpressionViewer.setInput(op);

            context.bindValue(ViewersObservables.observeSingleSelection(targetDataExpressionViewer),
                    EMFObservables.observeValue(op, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND));

            final Label takeValueOfLabel = new Label(oneRowNColsComposite, SWT.NONE);
            takeValueOfLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
            takeValueOfLabel.setText(Messages.takeValueOf);

            final Text columnText = new Text(oneRowNColsComposite, SWT.BORDER | SWT.READ_ONLY);
            columnText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
            columnText.setText(op.getRightOperand().getName());
        }
        scrolledComposite.setMinSize(oneRowNColsComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        scrolledComposite.setAlwaysShowScrollBars(false);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setContent(oneRowNColsComposite);
    }

    private Operation createOutputOperation(
            final String outputName, final String columName, final ConnectorDefinition definition) {
        Output connectorOutput = null;
        for (final Output out : definition.getOutput()) {
            if (out.getName().equals(outputName)) {
                connectorOutput = out;
                break;
            }
        }
        Assert.isNotNull(connectorOutput);

        final Operation operation = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
        assignment.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operation.setOperator(assignment);

        final Expression rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setName(columName);
        rightOperand.setContent(outputName + "[" + indexOf(columName) + "]");
        rightOperand.setReturnType(Object.class.getName());
        rightOperand.setType(ExpressionConstants.SCRIPT_TYPE);
        rightOperand.setInterpreter(ExpressionConstants.GROOVY);
        rightOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(connectorOutput));
        operation.setRightOperand(rightOperand);

        final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        operation.setLeftOperand(leftOperand);
        return operation;
    }

    private int indexOf(final String columName) {
        final ConnectorConfiguration configuration = getConnector().getConfiguration();
        final Expression scriptExpression = (Expression) getConnectorParameter(configuration, getInput(SCRIPT_KEY))
                .getExpression();
        final List<String> columns = SQLQueryUtil.getSelectedColumns(scriptExpression);
        return columns.indexOf(columName);
    }

    protected List<Operation> getOuputOperationsFor(final String outputName, final Expression query) {
        final List<Operation> outputs = new ArrayList<>();
        final List<String> columnNames = SQLQueryUtil.getSelectedColumns(query);
        for (final Operation output : getConnector().getOutputs()) {
            final String name = output.getRightOperand().getName();
            if (name != null
                    && columnNames.contains(name)
                    && output.getRightOperand().getContent() != null
                    && ExpressionConstants.SCRIPT_TYPE.equals(output.getRightOperand().getType())
                    && isAReferencedOuput(output.getRightOperand(), outputName)) {
                output.getRightOperand().setContent(outputName + "[" + indexOf(name) + "]");
                outputs.add(output);
                columnNames.remove(name);
            }
        }
        getConnector().getOutputs().clear();
        for (final String columnName : columnNames) {
            final Operation op = createOutputOperation(outputName, columnName, getDefinition());
            outputs.add(op);
        }
        getConnector().getOutputs().addAll(outputs);
        return outputs;
    }

    private boolean isAReferencedOuput(final Expression expression,
            final String outputName) {
        for (final EObject dep : expression.getReferencedElements()) {
            if (dep instanceof Output) {
                if (((Output) dep).getName().equals(outputName)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Composite createSingleOuputControl(final Composite parent,
            final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(20, 20).create());

        Operation singleModeOuputOperation = getOuputOperationFor(SINGLE_RESULT_OUTPUT);
        if (singleModeOuputOperation == null) {
            singleModeOuputOperation = createDefaultOutput(SINGLE_RESULT_OUTPUT, getDefinition());
            getConnector().getOutputs().add(singleModeOuputOperation);
        }

        final ReadOnlyExpressionViewer targetDataExpressionViewer = new ReadOnlyExpressionViewer(mainComposite, SWT.BORDER,
                null, null, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND);
        targetDataExpressionViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(250, SWT.DEFAULT).create());
        final IExpressionNatureProvider storageExpressionProvider = getStorageExpressionProvider();
        if (storageExpressionProvider != null) {
            targetDataExpressionViewer.setExpressionNatureProvider(storageExpressionProvider);
        }
        targetDataExpressionViewer.addFilter(leftFilter);
        targetDataExpressionViewer.setContext(getElementContainer());
        targetDataExpressionViewer.setInput(singleModeOuputOperation);

        context.bindValue(ViewersObservables.observeSingleSelection(targetDataExpressionViewer),
                EMFObservables.observeValue(singleModeOuputOperation, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND));

        final Label takeValueOfLabel = new Label(mainComposite, SWT.NONE);
        takeValueOfLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
        takeValueOfLabel.setText(Messages.takeValueOf);

        singleColumnText = new Text(mainComposite, SWT.BORDER | SWT.READ_ONLY);
        singleColumnText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        return mainComposite;
    }

    protected Operation getOuputOperationFor(final String outputName) {
        for (final Operation output : getConnector().getOutputs()) {
            if (output.getRightOperand().getName() != null
                    && output.getRightOperand().getName().equals(outputName)
                    && output.getRightOperand().getContent() != null
                    && output.getRightOperand().getContent().equals(outputName)
                    && ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(output.getRightOperand().getType())) {
                return output;
            }
        }
        return null;
    }

    protected Composite createDefaultOuputControl(final Composite parent, final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        lineComposite = new WizardPageOperationsComposite(null, mainComposite,
                new DefaultDatabaseOutputAvailableExpressionTypeFilter(), leftFilter);
        lineComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final IExpressionNatureProvider storageExpressionProvider = getStorageExpressionProvider();
        if (storageExpressionProvider != null) {
            lineComposite.setStorageExpressionNatureContentProvider(storageExpressionProvider);
        }
        lineComposite.setContext(context);
        lineComposite.setContext(getElementContainer());

        return mainComposite;
    }

    protected void updateStackLayout() {
        final ConnectorConfiguration configuration = getConnector().getConfiguration();
        final Expression scriptExpression = (Expression) getConnectorParameter(configuration, getInput(SCRIPT_KEY))
                .getExpression();
        final Expression outputTypeExpression = (Expression) getConnectorParameter(configuration, getInput(OUTPUT_TYPE_KEY))
                .getExpression();
        if (outputTypeExpression == null) {
            outputType = null;
        } else {
            outputType = outputTypeExpression.getContent();
        }
        if (SINGLE.equals(outputType)) {
            updateSingleOutput();
            final String column = SQLQueryUtil.getSelectedColumn(scriptExpression);
            if (column != null) {
                singleColumnText.setText(column);
            }
            stackLayout.topControl = singleOutputComposite;
            setDescription(Messages.singleDatabaseOutputDescription);
        } else if (ONE_ROW.equals(outputType)) {
            updateOneRowOutput(scriptExpression);
            buildListOfOutputForOneRowNCols(oneRowNColsscrolledComposite, context);
            stackLayout.topControl = oneRowNColsOutputComposite;
            setDescription(Messages.oneRowDatabaseOutputDescription);
        } else if (N_ROW.equals(outputType)) {
            updateNRowOutput();
            final String column = SQLQueryUtil.getSelectedColumn(scriptExpression);
            if (column != null) {
                nRowsOneColumnColumnText.setText(column);
            }
            stackLayout.topControl = nRowsOneColumOutputComposite;
            setDescription(Messages.oneColDatabaseOutputDescription);
        } else if (TABLE.equals(outputType)) {
            updateTableOutput();
            stackLayout.topControl = nRowsNColumsOutputComposite;
            setDescription(Messages.nRowsNColsDatabaseOutputDescription);
        } else {
            updateDefaultOutput();
            lineComposite.setEObject(getConnector());
            if (lineComposite.getNbLines() > 0) {
                lineComposite.removeLinesUI();
                lineComposite.fillTable();
                lineComposite.refresh();
            } else {
                lineComposite.fillTable();
            }
            stackLayout.topControl = defaultOutputComposite;
            setDescription(Messages.outputMappingDesc);
        }
        topComposite.layout();
    }

    public void updateOutputs(final String outputType) {
        final ConnectorConfiguration configuration = getConnector().getConfiguration();
        final Expression scriptExpression = (Expression) getConnectorParameter(configuration, getInput(SCRIPT_KEY))
                .getExpression();
        if (SINGLE.equals(outputType)) {
            updateSingleOutput();
        } else if (ONE_ROW.equals(outputType)) {
            updateOneRowOutput(scriptExpression);
        } else if (N_ROW.equals(outputType)) {
            updateNRowOutput();

        } else if (TABLE.equals(outputType)) {
            updateTableOutput();
        } else {
            updateDefaultOutput();
        }
    }

    protected void updateSingleOutput() {
        final List<Operation> toRemove = new ArrayList<>();
        for (final Operation outputOp : getConnector().getOutputs()) {
            for (final EObject connectorOutput : outputOp.getRightOperand().getReferencedElements()) {
                if (connectorOutput instanceof Output) {
                    if (!((Output) connectorOutput).getName().equals(SINGLE_RESULT_OUTPUT)) {
                        toRemove.add(outputOp);
                    }
                }
            }
        }
        getConnector().getOutputs().removeAll(toRemove);
        if (getConnector().getOutputs().isEmpty()) {
            getConnector().getOutputs().add(createDefaultOutput(SINGLE_RESULT_OUTPUT, getDefinition()));
        }
    }

    protected void updateOneRowOutput(final Expression scriptExpression) {
        final List<Operation> toRemove = new ArrayList<>();
        for (final Operation outputOp : getConnector().getOutputs()) {
            for (final EObject connectorOutput : outputOp.getRightOperand().getReferencedElements()) {
                if (connectorOutput instanceof Output) {
                    if (((Output) connectorOutput).getName().equals(SINGLE_RESULT_OUTPUT)
                            || ((Output) connectorOutput).getName().equals(NROW_ONECOL_RESULT_OUTPUT)
                            || ((Output) connectorOutput).getName().equals(TABLE_RESULT_OUTPUT)
                            || ((Output) connectorOutput).getName().equals(RESULTSET_OUTPUT)
                            || ((Output) connectorOutput).getName().isEmpty()) {
                        toRemove.add(outputOp);
                    }
                }
            }
        }
        getConnector().getOutputs().removeAll(toRemove);
        getOuputOperationsFor(ONEROW_NCOL_RESULT_OUTPUT, scriptExpression);
    }

    protected void updateNRowOutput() {
        final List<Operation> toRemove = new ArrayList<>();
        for (final Operation outputOp : getConnector().getOutputs()) {
            for (final EObject connectorOutput : outputOp.getRightOperand().getReferencedElements()) {
                if (connectorOutput instanceof Output) {
                    if (!((Output) connectorOutput).getName().equals(NROW_ONECOL_RESULT_OUTPUT)) {
                        toRemove.add(outputOp);
                    }
                }
            }
        }
        getConnector().getOutputs().removeAll(toRemove);
        if (getConnector().getOutputs().isEmpty()) {
            getConnector().getOutputs().add(createDefaultOutput(NROW_ONECOL_RESULT_OUTPUT, getDefinition()));
        }
    }

    protected void updateDefaultOutput() {
        final List<Operation> toRemove = new ArrayList<>();
        for (final Operation outputOp : getConnector().getOutputs()) {
            for (final EObject connectorOutput : outputOp.getRightOperand().getReferencedElements()) {
                if (connectorOutput instanceof Output) {
                    if (!((Output) connectorOutput).getName().equals(RESULTSET_OUTPUT)) {
                        toRemove.add(outputOp);
                    }
                }
            }
        }
        getConnector().getOutputs().removeAll(toRemove);
        if (getConnector().getOutputs().isEmpty()) {
            getConnector().getOutputs().add(createDefaultOutput(RESULTSET_OUTPUT, getDefinition()));
        }
    }

    protected void updateTableOutput() {
        final List<Operation> toRemove = new ArrayList<>();
        for (final Operation outputOp : getConnector().getOutputs()) {
            for (final EObject connectorOutput : outputOp.getRightOperand().getReferencedElements()) {
                if (connectorOutput instanceof Output) {
                    if (!((Output) connectorOutput).getName().equals(TABLE_RESULT_OUTPUT)) {
                        toRemove.add(outputOp);
                    }
                }
            }
        }
        getConnector().getOutputs().removeAll(toRemove);
        if (getConnector().getOutputs().isEmpty()) {
            getConnector().getOutputs().add(createDefaultOutput(TABLE_RESULT_OUTPUT, getDefinition()));
        }
    }

    protected Input getInput(final String inputName) {
        for (final Input input : getDefinition().getInput()) {
            if (input.getName().equals(inputName)) {
                return input;
            }
        }
        throw new IllegalArgumentException(
                "Input " + inputName + " not found in connector definition " + getDefinition().getId());
    }

    protected ConnectorParameter getConnectorParameter(final ConnectorConfiguration configuration, final Input input) {
        for (final ConnectorParameter param : configuration.getParameters()) {
            if (param.getKey().equals(input.getName())) {
                return param;
            }
        }
        return null;
    }

    protected Operation createDefaultOutput(final String outputName, final ConnectorDefinition definition) {
        for (final Output output : definition.getOutput()) {
            if (output.getName().equals(outputName)) {
                return ExpressionHelper.createDefaultConnectorOutputOperation(output);
            }
        }
        return null;
    }

    @Override
    public void handleValueChange(final ValueChangeEvent event) {
        updateStackLayout();
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
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }
}
