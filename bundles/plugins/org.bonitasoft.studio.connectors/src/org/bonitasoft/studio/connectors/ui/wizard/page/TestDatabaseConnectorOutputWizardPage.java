/**
 *
 */
package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.wizard.page.sqlutil.SQLQueryUtil;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class TestDatabaseConnectorOutputWizardPage extends DatabaseConnectorOutputWizardPage implements DatabaseConnectorConstants, IValueChangeListener{


	private ExpressionViewer outputExpressionViewer;

	public TestDatabaseConnectorOutputWizardPage(){
		super();
		setTitle(Messages.outputOperationsDefinitionTitle);
		rightFilter =  new AvailableExpressionTypeFilter(new String[]{
				ExpressionConstants.CONNECTOR_OUTPUT_TYPE,
				ExpressionConstants.SCRIPT_TYPE
		}) ;
	}


	@Override
	protected Composite createNRowsOneColumOuputControl(final Composite parent,
			final EMFDataBindingContext context) {
		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 20).create());

		Operation singleModeOuputOperation = getOuputOperationFor(NROW_ONECOL_RESULT_OUTPUT);
		if(singleModeOuputOperation == null){
			singleModeOuputOperation = createDefaultOutput(NROW_ONECOL_RESULT_OUTPUT, getDefinition());
			getConnector().getOutputs().add(singleModeOuputOperation);
		}
		singleModeOuputOperation.getLeftOperand().setReturnType(Collection.class.getName());
		singleModeOuputOperation.getLeftOperand().setReturnTypeFixed(true);

		final Label expressionLabel = new Label(mainComposite, SWT.READ_ONLY);
		expressionLabel.setText(Messages.connectorExpressionViewerLabel);

		nRowsOneColumnColumnText = new Text(mainComposite,SWT.BORDER | SWT.READ_ONLY) ;
		nRowsOneColumnColumnText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());

		final Label hintLabel = new Label(mainComposite, SWT.WRAP);
		hintLabel.setLayoutData(GridDataFactory.swtDefaults().span(3, 1).create());
		hintLabel.setText(Messages.nRowsOneColOutputHint);

		return mainComposite;
	}

	@Override
	protected Composite createNRowsNColsOuputControl(final Composite parent,
			final EMFDataBindingContext context) {
		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 20).create());

		Operation singleModeOuputOperation = getOuputOperationFor(TABLE_RESULT_OUTPUT);
		if(singleModeOuputOperation == null){
			singleModeOuputOperation = createDefaultOutput(TABLE_RESULT_OUTPUT, getDefinition());
			getConnector().getOutputs().add(singleModeOuputOperation);
		}
		singleModeOuputOperation.getLeftOperand().setReturnType(Collection.class.getName());
		singleModeOuputOperation.getLeftOperand().setReturnTypeFixed(true);

		final Label expressionLabel = new Label(mainComposite, SWT.READ_ONLY);
		expressionLabel.setText(Messages.connectorExpressionViewerLabel);

		final Text nRowsNColumnsColumnText = new Text(mainComposite,SWT.BORDER | SWT.READ_ONLY) ;
		nRowsNColumnsColumnText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());
		nRowsNColumnsColumnText.setText(singleModeOuputOperation.getRightOperand().getName());

		final Label hintLabel = new Label(mainComposite, SWT.WRAP);
		hintLabel.setLayoutData(GridDataFactory.swtDefaults().span(3, 1).create());
		hintLabel.setText(Messages.nRowsNColsOutputHint);

		return mainComposite;
	}

	@Override
	protected void buildListOfOutputForOneRowNCols(final ScrolledComposite scrolledComposite,final EMFDataBindingContext context) {
		if(scrolledComposite.getContent() != null){
			scrolledComposite.getContent().dispose();
			scrolledComposite.setContent(null);
		}
		final Composite oneRowNColsComposite = new Composite(scrolledComposite, SWT.NONE) ;
		oneRowNColsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		oneRowNColsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());


		final ConnectorConfiguration configuration = getConnector().getConfiguration();
		final Expression scriptExpression = (Expression) getConnectorParameter(configuration, getInput(SCRIPT_KEY)).getExpression();
		final List<Operation> operations = getOuputOperationsFor(ONEROW_NCOL_RESULT_OUTPUT,scriptExpression);
		for(final Operation op : operations){
			final Label expressionLabel = new Label(oneRowNColsComposite, SWT.READ_ONLY);
			expressionLabel.setText(Messages.connectorExpressionViewerLabel);

			final Text columnText = new Text(oneRowNColsComposite,SWT.BORDER | SWT.READ_ONLY) ;
			columnText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());
			columnText.setText(op.getRightOperand().getName());
		}
		scrolledComposite.setMinSize(oneRowNColsComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setContent(oneRowNColsComposite);
	}


	@Override
	protected Composite createSingleOuputControl(final Composite parent,
			final EMFDataBindingContext context) {
		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 20).create());

		final Label expressionLabel = new Label(mainComposite, SWT.READ_ONLY);
		expressionLabel.setText(Messages.connectorExpressionViewerLabel);

		Operation singleModeOuputOperation = getOuputOperationFor(SINGLE_RESULT_OUTPUT);
		if(singleModeOuputOperation == null){
			singleModeOuputOperation = createDefaultOutput(SINGLE_RESULT_OUTPUT, getDefinition());
			getConnector().getOutputs().add(singleModeOuputOperation);
		}

		singleColumnText = new Text(mainComposite,SWT.BORDER | SWT.READ_ONLY) ;
		singleColumnText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());

		return mainComposite;
	}

	@Override
	protected Composite createDefaultOuputControl(final Composite parent,final EMFDataBindingContext context){
		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

		final Label expressionLabel = new Label(mainComposite, SWT.READ_ONLY);
		expressionLabel.setText(Messages.connectorExpressionViewerLabel);

		outputExpressionViewer = new ExpressionViewer(mainComposite, SWT.BORDER, ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND);
		outputExpressionViewer.getControl().setLayoutData( GridDataFactory.fillDefaults().grab(true, false).create());
		outputExpressionViewer.addFilter(rightFilter);
		outputExpressionViewer.setContext(getConnector());
		outputExpressionViewer.setMessage(Messages.connectorExpressionViewerMessage);
		outputExpressionViewer.setExternalDataBindingContext(context);
		outputExpressionViewer.setProposalsFiltering(false);
        outputExpressionViewer.addExpressionValidator(new IExpressionValidator() {

			private Expression inputExpression;

			@Override
			public IStatus validate(final Object value) {
				final Expression exp = inputExpression;
				if(exp.getType().equals(ExpressionConstants.SCRIPT_TYPE) || exp.getType().equals(ExpressionConstants.CONNECTOR_OUTPUT_TYPE)) {
					return ValidationStatus.ok();
				}
				return ValidationStatus.error(Messages.connectorTypeValidationMessage);
			}

			@Override
			public void setInputExpression(final Expression inputExpression) {
				this.inputExpression = inputExpression;
			}

			@Override
			public void setDomain(final EditingDomain domain) {

			}

			@Override
			public void setContext(final EObject context) {

			}

            @Override
            public boolean isRelevantForExpressionType(final String type) {
                return true;
            }
		});
		final Operation output = getOuputOperationFor(RESULTSET_OUTPUT);
		if(output != null){
			outputExpressionViewer.setInput(output);
			context.bindValue(ViewersObservables.observeSingleSelection(outputExpressionViewer), EMFObservables.observeValue(output, ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND));
		}

		return mainComposite;
	}

	@Override
	protected void updateStackLayout() {
		final ConnectorConfiguration configuration = getConnector().getConfiguration();
		final Expression scriptExpression = (Expression) getConnectorParameter(configuration, getInput(SCRIPT_KEY)).getExpression();
		final Expression outputTypeExpression = (Expression) getConnectorParameter(configuration, getInput(OUTPUT_TYPE_KEY)).getExpression();
		if(outputTypeExpression == null){
			outputType = null;
		}else{
			outputType = outputTypeExpression.getContent();
		}
		if(SINGLE.equals(outputType)){
			updateSingleOutput();
			final String column = SQLQueryUtil.getSelectedColumn(scriptExpression);
			if(column != null){
				singleColumnText.setText(column);
			}
			stackLayout.topControl = singleOutputComposite;
			setDescription(Messages.singleDatabaseOutputDescription);
		}else if(ONE_ROW.equals(outputType)){
			updateOneRowOutput(scriptExpression);
			buildListOfOutputForOneRowNCols(oneRowNColsscrolledComposite, context);
			stackLayout.topControl = oneRowNColsOutputComposite ;
			setDescription(Messages.oneRowDatabaseOutputDescription);
		}else if(N_ROW.equals(outputType)){
			updateNRowOutput();
			final String column = SQLQueryUtil.getSelectedColumn(scriptExpression);
			if(column != null){
				nRowsOneColumnColumnText.setText(column);
			}
			stackLayout.topControl =  nRowsOneColumOutputComposite ;
			setDescription(Messages.oneColDatabaseOutputDescription);
		}else if(TABLE.equals(outputType)){
			updateTableOutput();
			stackLayout.topControl = nRowsNColumsOutputComposite;
			setDescription(Messages.nRowsNColsDatabaseOutputDescription);
		}else{
			updateDefaultOutput();
			final Operation ouputOperationFor = getOuputOperationFor(RESULTSET_OUTPUT);
			outputExpressionViewer.setInput(ouputOperationFor);
			outputExpressionViewer.setSelection(new StructuredSelection(ouputOperationFor.getRightOperand()));
			stackLayout.topControl = defaultOutputComposite ;
			setDescription(Messages.outputMappingDesc);
		}
		topComposite.layout();
	}

}
