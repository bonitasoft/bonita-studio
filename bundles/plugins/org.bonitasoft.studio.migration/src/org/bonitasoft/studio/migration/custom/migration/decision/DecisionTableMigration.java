/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.custom.migration.decision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Florine Boudin
 *
 */
public class DecisionTableMigration extends CustomMigration {


	Map<String , List<Instance>> map = new HashMap<String, List<Instance>>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {

		for(Instance instance : model.getAllInstances("process.decision.DecisionTableLine")){

			
			List<Instance> list = new ArrayList<Instance>();
			
			List<Instance> listCondition = instance.get("conditions");
			for(Instance condition : listCondition){

				String op1 = condition.get("operand1");
				String operator = condition.get("operator");
				String op2 = condition.get("operand2");
				String s = op1+operator+op2;

				Instance exp = StringToExpressionConverter.createExpressionInstance(model, s, s, Boolean.class.getName(), ExpressionConstants.CONDITION_TYPE, true) ;
				getConverter(model, getScope(condition)).resolveDataDependencies(exp);
				list.add(exp);
				model.delete(condition);
			}
			
			map.put(instance.getUuid(), list);
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance instance : model.getAllInstances("process.decision.DecisionTableLine")){
			List<Instance> listCondition = instance.get("conditions");

			listCondition.addAll((Collection<? extends Instance>) map.get(instance.getUuid()));
			
		}
	}
	
	public StringToExpressionConverter getConverter(final Model model,final Instance container) {
		return new StringToExpressionConverter(model,container);
	}

	/**
	 *
	 * @param element
	 * @return the parent process instance
	 */
	protected Instance getScope(final Instance element){
		Instance container = element;
		while(container != null && !container.instanceOf("process.AbstractProcess")){
			container = container.getContainer();
		}
		return container;
	}


}
