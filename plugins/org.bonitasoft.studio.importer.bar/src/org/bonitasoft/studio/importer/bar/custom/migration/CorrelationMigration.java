/**
 * 
 */
package org.bonitasoft.studio.importer.bar.custom.migration;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author aurelie zara
 *
 */
public class CorrelationMigration extends CustomMigration {

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)throws MigrationException{
		System.out.println("");
//		for (Instance correlationInstance : model.getAllInstances("process.Correlation")){
//			final Instance correlationAssociationInstance = correlationInstance.get("correlationAssociation");
//			final Instance correlationKeyInstance = correlationAssociationInstance.get("correlationKey");
//			final Instance correlationExpressionInstance = correlationAssociationInstance.get("correlationExpression");
//			 
//		}
	}
}
