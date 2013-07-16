/**
 * 
 */
package org.bonitasoft.studio.connectors.ui.wizard.page;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connectors.expression.DataExpressionNatureProvider;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationsComposite;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public class DatabaseConnectorOutputWizardPage extends AbstractConnectorOutputWizardPage implements DatabaseConnectorConstants{

	private String outputType;
	private StackLayout stackLayout;
	private Composite defaultOutputComposite;

	public DatabaseConnectorOutputWizardPage(){
		super();
		setTitle(Messages.outputOperationsDefinitionTitle);
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.connectors.ui.wizard.page.AbstractConnectorOutputWizardPage#doCreateControl(org.eclipse.swt.widgets.Composite, org.eclipse.emf.databinding.EMFDataBindingContext)
	 */
	@Override
	protected Control doCreateControl(Composite parent,
			EMFDataBindingContext context) {
		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		stackLayout = new StackLayout();
		mainComposite.setLayout(stackLayout) ;
		
		defaultOutputComposite = createDefaultOuputControl(mainComposite, context);
		stackLayout.topControl = defaultOutputComposite;
		mainComposite.layout();
		return mainComposite;
	}


	protected Composite createDefaultOuputControl(Composite parent,EMFDataBindingContext context){
		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		final AvailableExpressionTypeFilter leftFilter =  new AvailableExpressionTypeFilter(new String[]{ 
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.DOCUMENT_REF_TYPE}) ;
		final AvailableExpressionTypeFilter rightFilter =  new AvailableExpressionTypeFilter(new String[]{ 
				ExpressionConstants.CONSTANT_TYPE,
				ExpressionConstants.CONNECTOR_OUTPUT_TYPE,
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.PARAMETER_TYPE,
				ExpressionConstants.SCRIPT_TYPE,
				ExpressionConstants.DOCUMENT_TYPE
		}) ;

		OperationsComposite lineComposite = new OperationsComposite(null, mainComposite, rightFilter, leftFilter) ;
		lineComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
		lineComposite.setContext(context) ;
		lineComposite.setContext(getElementContainer());
		lineComposite.setEObject(getConnector()) ;
		if(getOutputDataFeature() != null){
			lineComposite.setStorageExpressionNatureContentProvider(new DataExpressionNatureProvider(getOutputDataFeature())) ;
		}
		lineComposite.fillTable() ;
		return mainComposite;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if(visible){
			final ConnectorConfiguration configuration = getConnector().getConfiguration();
			final Expression outputTypeExpression = (Expression) getConnectorParameter(configuration, getInput(OUTPUT_TYPE_KEY)).getExpression();
			outputType = outputTypeExpression.getContent();
			if(SINGLE.equals(outputType)){
				setDescription(Messages.singleDatabaseOutputDescription);
			}else if(ONE_ROW.equals(outputType)){
				setDescription(Messages.oneRowDatabaseOutputDescription);
			}else if(N_ROW.equals(outputType)){
				setDescription(Messages.oneColDatabaseOutputDescription);
			}else if(TABLE.equals(outputType)){
				setDescription(Messages.nRowsNColsDatabaseOutputDescription);
			}else{
				setDescription(Messages.outputMappingDesc);
			}

		}
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
}
