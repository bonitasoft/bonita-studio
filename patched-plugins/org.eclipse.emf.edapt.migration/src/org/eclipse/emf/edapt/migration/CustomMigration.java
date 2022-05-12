package org.eclipse.emf.edapt.migration;

import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * Base class for custom migrations.
 *
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 96A14ADC5A23DF56A44B210EF3A99D8C
 */
public abstract class CustomMigration {

	/** Migration that needs to be performed before the metamodel change. */
	public void migrateBefore(Model model, Metamodel metamodel)
		throws MigrationException {
		// to be implemented by sub classes
	}

	/** Migration that needs to be performed after the metamodel change. */
	public void migrateAfter(Model model, Metamodel metamodel)
		throws MigrationException {
		// to be implemented by sub classes
	}
}
