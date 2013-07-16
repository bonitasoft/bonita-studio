/**
 * 
 */
package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connectors.expression.DataExpressionNatureProvider;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.wizard.page.sqlutil.SQLQueryUtil;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationsComposite;
import org.bonitasoft.studio.expression.editor.viewer.ReadOnlyExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 *
 */
public class DatabaseConnectorOutputWizardPage extends AbstractConnectorOutputWizardPage implements DatabaseConnectorConstants{

	private String outputType;
	private StackLayout stackLayout;
	private Composite defaultOutputComposite;
	private OperationsComposite lineComposite;
	private Composite singleOutputComposite;
	private AvailableExpressionTypeFilter leftFilter;
	private AvailableExpressionTypeFilter rightFilter;
	private Composite topComposite;
	private Text singleColumnText;
	private Composite oneRowNColsOutputComposite;
	private Composite oneRowNColsComposite;
	private ScrolledComposite oneRowNColsscrolledComposite;

	public DatabaseConnectorOutputWizardPage(){
		super();
		setTitle(Messages.outputOperationsDefinitionTitle);
		leftFilter =  new AvailableExpressionTypeFilter(new String[]{ 
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.DOCUMENT_REF_TYPE}) ;
		rightFilter =  new AvailableExpressionTypeFilter(new String[]{ 
				ExpressionConstants.CONSTANT_TYPE,
				ExpressionConstants.CONNECTOR_OUTPUT_TYPE,
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.PARAMETER_TYPE,
				ExpressionConstants.SCRIPT_TYPE,
				ExpressionConstants.DOCUMENT_TYPE
		}) ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.connectors.ui.wizard.page.AbstractConnectorOutputWizardPage#doCreateControl(org.eclipse.swt.widgets.Composite, org.eclipse.emf.databinding.EMFDataBindingContext)
	 */
	@Override
	protected Control doCreateControl(Composite parent,
			EMFDataBindingContext context) {
		topComposite = new Composite(parent, SWT.NONE) ;
		topComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		stackLayout = new StackLayout();
		topComposite.setLayout(stackLayout) ;

		defaultOutputComposite = createDefaultOuputControl(topComposite, context);
		singleOutputComposite = createSingleOuputControl(topComposite, context);
		oneRowNColsOutputComposite = createOneRowNColsOuputControl(topComposite, context);
		stackLayout.topControl = defaultOutputComposite;

		return topComposite;
	}


	protected Composite createOneRowNColsOuputControl(Composite parent,
			EMFDataBindingContext context) {
		oneRowNColsscrolledComposite = new ScrolledComposite(parent,SWT.V_SCROLL);
		oneRowNColsscrolledComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		buildListOfOutputForOneRowNCols(oneRowNColsscrolledComposite, context);

		return oneRowNColsscrolledComposite;
	}

	protected void buildListOfOutputForOneRowNCols(ScrolledComposite scrolledComposite,EMFDataBindingContext context) {
		if(scrolledComposite.getContent() != null){
			scrolledComposite.getContent().dispose();
		}
		final Composite oneRowNColsComposite = new Composite(scrolledComposite, SWT.NONE) ;
		oneRowNColsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		oneRowNColsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).create());


		Operation oneRowModeOuputOperation = getOuputOperationsFor(ONEROW_NCOL_RESULT_OUTPUT);
		if(oneRowModeOuputOperation == null){
			oneRowModeOuputOperation = createDefaultOutput(ONEROW_NCOL_RESULT_OUTPUT, getDefinition());
			getConnector().getOutputs().add(oneRowModeOuputOperation);
		}
		for(int i = 0 ; i<5 ; i++){
			final ReadOnlyExpressionViewer targetDataExpressionViewer = new ReadOnlyExpressionViewer(oneRowNColsComposite,SWT.BORDER,null,null,ExpressionPackage.Literals.OPERATION__LEFT_OPERAND) ;
			targetDataExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());
			targetDataExpressionViewer.addFilter(leftFilter);
			targetDataExpressionViewer.setContext(getElementContainer());
			targetDataExpressionViewer.setInput(oneRowModeOuputOperation);

			context.bindValue(ViewersObservables.observeSingleSelection(targetDataExpressionViewer), EMFObservables.observeValue(oneRowModeOuputOperation, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND)) ;

			final Label takeValueOfLabel = new Label(oneRowNColsComposite, SWT.NONE);
			takeValueOfLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
			takeValueOfLabel.setText(Messages.takeValueOf);

			singleColumnText = new Text(oneRowNColsComposite,SWT.BORDER | SWT.READ_ONLY) ;
			singleColumnText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());
		}
		scrolledComposite.setMinSize(oneRowNColsComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setContent(oneRowNColsComposite);
	}

	protected Composite createSingleOuputControl(Composite parent,
			EMFDataBindingContext context) {
		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(20, 20).create());

		Operation singleModeOuputOperation = getOuputOperationFor(SINGLE_RESULT_OUTPUT);
		if(singleModeOuputOperation == null){
			singleModeOuputOperation = createDefaultOutput(SINGLE_RESULT_OUTPUT, getDefinition());
			getConnector().getOutputs().add(singleModeOuputOperation);
		}

		final ReadOnlyExpressionViewer targetDataExpressionViewer = new ReadOnlyExpressionViewer(mainComposite,SWT.BORDER,null,null,ExpressionPackage.Literals.OPERATION__LEFT_OPERAND) ;
		targetDataExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());
		targetDataExpressionViewer.addFilter(leftFilter);
		targetDataExpressionViewer.setContext(getElementContainer());
		targetDataExpressionViewer.setInput(singleModeOuputOperation);

		context.bindValue(ViewersObservables.observeSingleSelection(targetDataExpressionViewer), EMFObservables.observeValue(singleModeOuputOperation, ExpressionPackage.Literals.OPERATION__LEFT_OPERAND)) ;

		final Label takeValueOfLabel = new Label(mainComposite, SWT.NONE);
		takeValueOfLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
		takeValueOfLabel.setText(Messages.takeValueOf);

		singleColumnText = new Text(mainComposite,SWT.BORDER | SWT.READ_ONLY) ;
		singleColumnText.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create());

		return mainComposite;
	}

	protected Operation getOuputOperationFor(String outputName) {
		for(Operation output : getConnector().getOutputs()){
			if(output.getRightOperand().getName() != null 
					&& output.getRightOperand().getName().equals(outputName) 
					&& output.getRightOperand().getContent() != null 
					&& output.getRightOperand().getContent().equals(outputName)
					&& ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(output.getRightOperand().getType())){
				return output;
			}
		}
		return null;
	}

	protected Composite createDefaultOuputControl(Composite parent,EMFDataBindingContext context){
		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

		lineComposite = new OperationsComposite(null, mainComposite, rightFilter, leftFilter) ;
		lineComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
		lineComposite.setContext(context) ;
		lineComposite.setContext(getElementContainer());

		if(getOutputDataFeature() != null){
			lineComposite.setStorageExpressionNatureContentProvider(new DataExpressionNatureProvider(getOutputDataFeature())) ;
		}

		return mainComposite;
	}

	@Override
	public void setVisible(boolean visible) {
		if(visible){
			final ConnectorConfiguration configuration = getConnector().getConfiguration();
			final Expression outputTypeExpression = (Expression) getConnectorParameter(configuration, getInput(OUTPUT_TYPE_KEY)).getExpression();
			boolean changed = false;
			if(outputType != null){
				if(!outputType.equals(outputTypeExpression.getContent())){
					changed = true;
				}
			}
			outputType = outputTypeExpression.getContent();
			if(SINGLE.equals(outputType)){
				final Expression scriptExpression = (Expression) getConnectorParameter(configuration, getInput(SCRIPT_KEY)).getExpression();
				String column = SQLQueryUtil.getSelectedColumn(scriptExpression);
				if(column != null){
					singleColumnText.setText(column);
				}
				stackLayout.topControl = singleOutputComposite;
				setDescription(Messages.singleDatabaseOutputDescription);
			}else if(ONE_ROW.equals(outputType)){
				stackLayout.topControl = oneRowNColsOutputComposite ;
				setDescription(Messages.oneRowDatabaseOutputDescription);
			}else if(N_ROW.equals(outputType)){
				setDescription(Messages.oneColDatabaseOutputDescription);
			}else if(TABLE.equals(outputType)){
				setDescription(Messages.nRowsNColsDatabaseOutputDescription);
			}else{
				stackLayout.topControl = defaultOutputComposite ;
				List<Operation> toRemove = new ArrayList<Operation>();
				for(Operation outputOp : getConnector().getOutputs()){
					for(EObject connectorOutput : outputOp.getRightOperand().getReferencedElements()){
						if(connectorOutput instanceof Output){
							if(!((Output) connectorOutput).getName().equals(RESULTSET_OUTPUT)){
								toRemove.add(outputOp);
							}
						}
					}
				}
				getConnector().getOutputs().removeAll(toRemove);
				lineComposite.setEObject(getConnector());
				lineComposite.removeLinesUI();
				lineComposite.fillTable();
				lineComposite.refresh();
				setDescription(Messages.outputMappingDesc);
			}
			topComposite.layout();
		}
		super.setVisible(visible);
	}

	protected Input getInput(String inputName) {
		for(Input input : getDefinition().getInput()){
			if(input.getName().equals(inputName)){
				return input;
			}
		}
		throw new IllegalArgumentException("Input "+inputName +" not found in connector definition "+getDefinition().getId());
	}

	protected ConnectorParameter getConnectorParameter(ConnectorConfiguration configuration,Input input) {
		for(ConnectorParameter param : configuration.getParameters()){
			if(param.getKey().equals(input.getName())){
				return param ;	
			}
		}
		return null ;
	}

	protected Operation createDefaultOutput(String outputName,ConnectorDefinition definition) {
		for(Output output : definition.getOutput()){
			if(output.getName().equals(outputName)){
				final Operation operation = ExpressionFactory.eINSTANCE.createOperation() ;
				final Operator assignment = ExpressionFactory.eINSTANCE.createOperator() ;
				assignment.setType(ExpressionConstants.ASSIGNMENT_OPERATOR) ;
				operation.setOperator(assignment) ;

				final Expression rightOperand = ExpressionFactory.eINSTANCE.createExpression() ;
				rightOperand.setName(output.getName()) ;
				rightOperand.setContent(output.getName()) ;
				rightOperand.setReturnType(output.getType()) ;
				rightOperand.setType(ExpressionConstants.CONNECTOR_OUTPUT_TYPE) ;
				rightOperand.getReferencedElements().add(EcoreUtil.copy(output)) ;
				operation.setRightOperand(rightOperand) ;

				final Expression leftOperand = ExpressionFactory.eINSTANCE.createExpression() ;
				operation.setLeftOperand(leftOperand) ;
				return operation;
			}
		}
		return null;
	}
}
