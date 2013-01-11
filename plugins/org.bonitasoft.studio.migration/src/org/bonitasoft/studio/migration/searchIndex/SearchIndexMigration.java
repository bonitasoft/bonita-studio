/**
 * 
 */
package org.bonitasoft.studio.migration.searchIndex;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author aurelie zara
 *
 */
public class SearchIndexMigration extends CustomMigration {
	
	private final int MAX=5;

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance pool:model.getAllInstances("process.Pool")){
			for (int i=0;i<MAX;i++){
				pool.add("SearchIndexs", createSearchIndex(model));
			}
		}
	}

	private Instance createSearchIndex(Model model){
		Instance searchIndex = model.newInstance(ProcessPackage.Literals.SEARCH_INDEX);
		Instance nameExpression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		searchIndex.set("Name", nameExpression);
		Instance valueExpression = StringToExpressionConverter.createExpressionInstance(model, "", "",String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		searchIndex.set("Value",valueExpression);
		return searchIndex;
	}
}
