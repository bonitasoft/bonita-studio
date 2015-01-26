/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.custom.migration.form;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class FileWidgetActionMigration extends CustomMigration {

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance fileWidget : model.getAllInstances("form.FileWidget")){
			if(fileWidget.getType()==null){
				continue;
			}
			String documentName = fileWidget.get("outputDocumentName");
			if(documentName != null){
				Instance storageExpression = StringToExpressionConverter.createExpressionInstance(model, 
						documentName, 
						documentName, 
						String.class.getName(), 
						ExpressionConstants.DOCUMENT_REF_TYPE,
						false);
				String widgetId = WidgetHelper.FIELD_PREFIX+fileWidget.get("name");
				Instance actionExpression = StringToExpressionConverter.createExpressionInstanceWithDependency(model, 
						widgetId, 
						widgetId, 
						ExpressionConstants.DOCUMENT_VALUE_RETURN_TYPE, 
						ExpressionConstants.FORM_FIELD_TYPE,
						false,
						fileWidget);
				final Instance operator = model.newInstance("expression.Operator");
				operator.set("type", ExpressionConstants.SET_DOCUMENT_OPERATOR);
				final Instance oldOperation = fileWidget.get("action");
				if(oldOperation != null){
					model.delete(oldOperation);
				}
				final Instance actionOperation = StringToExpressionConverter.createOperation(model, storageExpression, operator, actionExpression);
				fileWidget.set("action", actionOperation);
			}
		}
		for(Instance imageWidget : model.getAllInstances("form.ImageWidget")){
			Instance document = imageWidget.get("document");
			boolean isDocument = imageWidget.get("isADocument");
			if(document != null && isDocument ){
				String documentName = document.get("name");
				Instance inputExpression = StringToExpressionConverter.createExpressionInstance(model, 
						documentName, 
						documentName, 
						String.class.getName(), 
						ExpressionConstants.DOCUMENT_REF_TYPE,
						false);
				Instance oldExpressionInstance = imageWidget.get("imgPath");
				if(oldExpressionInstance != null){
					model.delete(oldExpressionInstance);
				}
				imageWidget.set("imgPath", inputExpression);
			}
		}
	}
	
}
