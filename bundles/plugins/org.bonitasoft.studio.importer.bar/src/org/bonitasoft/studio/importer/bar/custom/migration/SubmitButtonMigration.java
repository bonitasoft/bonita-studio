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
package org.bonitasoft.studio.importer.bar.custom.migration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class SubmitButtonMigration extends ReportCustomMigration {

	private Map<String, List<Instance>> buttonActions = new HashMap<String,List<Instance>>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance button : model.getAllInstances("form.SubmitFormButton")){
			if(!(button.getContainer().instanceOf("expression.Expression"))){
				final List<Instance> actions = button.get("scripts");
				if(!actions.isEmpty()){
					final StringToExpressionConverter converter = getConverter(model,getScope(button));
					final List<Instance> operations = new ArrayList<Instance>();
					for(Instance action : actions){
						final Instance operation = converter.parseOperation(action, String.class.getName(), false);
						operations.add(operation);
						model.delete(action);
					}
					buttonActions.put(button.getUuid(), operations);
				}
			}
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance button : model.getAllInstances("form.SubmitFormButton")){
			if(!(button.getContainer().instanceOf("expression.Expression"))){
				if(buttonActions.containsKey(button.getUuid())){
					for(Instance operation : buttonActions.get(button.getUuid())){
						button.add("actions", operation);
					}
					addReportChange((String) button.get("name"),button.getEClass().getName(), button.getUuid(), Messages.buttonActionsMigrationDescription, Messages.actionProperty, IStatus.WARNING);
				}
			}
		}
	}

}
