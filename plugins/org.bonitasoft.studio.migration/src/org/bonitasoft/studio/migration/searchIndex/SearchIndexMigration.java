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
 * @author aurelie
 *
 */
public class SearchIndexMigration extends CustomMigration {



	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance pool:model.getAllInstances("process.Pool")){
			pool.add("searchIndexs", createSearchIndex());
		}
	}

	private List<SearchIndex> createSearchIndex(){

		List<SearchIndex> searchIndexs = new ArrayList<SearchIndex>(5);
		for (int i = 0; i < 5; i++) {
			SearchIndex searchIndex = ProcessFactory.eINSTANCE
					.createSearchIndex();
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
			searchIndexs.add(searchIndex);
		}
		return searchIndexs;
	}
}
