/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.model.definition.wizard;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 *
 */
public class SelectConnectorConfigurationWizard extends Wizard  {


	private final ConnectorConfiguration currentConfiguraiton;
	private SelectConnectorConfigurationWizardPage page;
	private final IRepositoryStore<? extends IRepositoryFileStore> configurationStore;

	public SelectConnectorConfigurationWizard(ConnectorConfiguration currentConfiguraiton,IRepositoryStore<? extends IRepositoryFileStore> configurationStore) {
		setDefaultPageImageDescriptor(Pics.getWizban()) ;
		this.currentConfiguraiton = currentConfiguraiton ;
		this.configurationStore = configurationStore ;
	}


	@Override
	public void addPages() {
		page = new SelectConnectorConfigurationWizardPage(currentConfiguraiton,configurationStore);
		addPage(page);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		ConnectorConfiguration selectedConfiguration =  page.getSelectedConfiguration() ;
		currentConfiguraiton.getParameters().clear() ;
		for(ConnectorParameter parameter : selectedConfiguration.getParameters()){
			ConnectorParameter newParam = EcoreUtil.copy(parameter);
			for(EObject exp : ModelHelper.getAllItemsOfType(newParam, ExpressionPackage.Literals.EXPRESSION)){
				Expression expression = (Expression) exp;
				if(!expression.getReferencedElements().isEmpty()){
					expression.getReferencedElements().clear();
				}
				if(ExpressionConstants.VARIABLE_TYPE.equals(expression.getType())){
					for(Data d : ModelHelper.getAccessibleData(currentConfiguraiton)){
						if(d.getName().equals(expression.getContent())){
							expression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(d));
							break;
						}
					}
					if(expression.getReferencedElements().isEmpty()){
						expression.setType(ExpressionConstants.CONSTANT_TYPE);
					}
				}else if(ExpressionConstants.PARAMETER_TYPE.equals(expression.getType())){
					for(Parameter p : ModelHelper.getParentProcess(currentConfiguraiton).getParameters()){
						if(p.getName().equals(expression.getContent())){
							expression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(p));
							break;
						}
					}
					if(expression.getReferencedElements().isEmpty()){
						expression.setType(ExpressionConstants.CONSTANT_TYPE);
					}
				}else if(ExpressionConstants.FORM_FIELD_TYPE.equals(expression.getType())){
					Form parentForm = ModelHelper.getParentForm(currentConfiguraiton);
					if(parentForm != null){
						for(Widget w : ModelHelper.getAllAccessibleWidgetInsideForm(parentForm)){
							if((WidgetHelper.FIELD_PREFIX+w.getName()).equals(expression.getContent())){
								expression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(w));
								break;
							}
						}
					}
					if(expression.getReferencedElements().isEmpty()){
						expression.setType(ExpressionConstants.CONSTANT_TYPE);
					}
				}
			}
			currentConfiguraiton.getParameters().add(newParam) ;
		}
		return true;
	}
}
