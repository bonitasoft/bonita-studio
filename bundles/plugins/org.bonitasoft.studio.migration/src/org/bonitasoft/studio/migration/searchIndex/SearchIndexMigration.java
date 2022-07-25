/**
 *
 */
package org.bonitasoft.studio.migration.searchIndex;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author aurelie zara
 *
 */
public class SearchIndexMigration extends CustomMigration {

	private final int MAX=5;

	@Override
	public void migrateAfter(final Model model, final Metamodel metamodel)
			throws MigrationException {
		for (final Instance pool:model.getAllInstances("process.Pool")){
			for (int i=0;i<MAX;i++){
				pool.add("searchIndexes", createSearchIndex(model));
			}
		}
	}

	private Instance createSearchIndex(final Model model){
		final Instance searchIndex = model.newInstance("process.SearchIndex");
		final Instance nameExpression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		searchIndex.set("name", nameExpression);
		final Instance valueExpression = StringToExpressionConverter.createExpressionInstance(model, "", "",String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		searchIndex.set("value",valueExpression);
		return searchIndex;
	}
}
