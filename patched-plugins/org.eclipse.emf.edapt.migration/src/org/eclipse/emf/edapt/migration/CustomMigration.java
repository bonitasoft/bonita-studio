package org.eclipse.emf.edapt.migration;

/**
 * Base class for custom migrations.
 * 
 * @author herrmama
 * @author $Author: mherrmannsd $
 * @version $Rev: 138 $
 * @levd.rating YELLOW Hash: 96A14ADC5A23DF56A44B210EF3A99D8C
 */
public abstract class CustomMigration {

	/** Migration that needs to be performed before the metamodel change. */
	@SuppressWarnings("unused")
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		// to be implemented by sub classes
	}

	/** Migration that needs to be performed after the metamodel change. */
	@SuppressWarnings("unused")
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		// to be implemented by sub classes
	}
}
