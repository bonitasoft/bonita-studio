package org.eclipse.emf.edapt.internal.migration.internal;

import java.util.List;

import org.eclipse.emf.edapt.spi.migration.Instance;

/**
 * Helper methods for migration.
 *
 * @author herrmi
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public final class MigrationUtils {

	/** Constructor. */
	private MigrationUtils() {
		// hidden, since this class only provides static helper methods
	}

	/**
	 * Choose a value from a list of values for a context element based on a
	 * message.
	 */
	public static <V> V choose(Instance context, List<V> values, String message) {
		return MigratorOptions.getInstance().getOracle()
			.choose(context, values, message);
	}

	/** Debug the migration for a context element based on a message. */
	public static void debug(Instance context, String message) {
		MigratorOptions.getInstance().getDebugger().debug(context, message);
	}
}
