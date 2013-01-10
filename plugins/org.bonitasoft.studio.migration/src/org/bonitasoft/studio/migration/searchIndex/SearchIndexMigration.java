/**
 * 
 */
package org.bonitasoft.studio.migration.searchIndex;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.ProcessFactory;
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
				pool.add("searchIndexs", createSearchIndex());
			}
		}
	}

	private SearchIndex createSearchIndex(){
		SearchIndex searchIndex = ProcessFactory.eINSTANCE.createSearchIndex();
		Expression name = ExpressionFactory.eINSTANCE.createExpression();
		name.setContent("");
		name.setType(ExpressionConstants.CONSTANT_TYPE);
		name.setReturnType(String.class.getName());
		name.setReturnTypeFixed(true);
		searchIndex.setName(name);
		Expression value = ExpressionFactory.eINSTANCE.createExpression();
		value.setContent("");
		value.setReturnType(String.class.getName());
		value.setReturnTypeFixed(true);
		searchIndex.setValue(value);
		return searchIndex;
	}
}
