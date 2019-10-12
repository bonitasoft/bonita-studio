package org.eclipse.emf.edapt.internal.migration.internal;

/**
 * Singleton class to define options for migration.
 *
 * @author herrmi
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public final class MigratorOptions {

	/** Singleton instance. */
	private static final MigratorOptions instance = new MigratorOptions();

	/** Registered oracle. */
	private IOracle oracle;

	/** Registered debugger. */
	private IDebugger debugger;

	/** Private default constructor. */
	private MigratorOptions() {
		// singleton class
	}

	/** Get the singleton instance. */
	public static MigratorOptions getInstance() {
		return instance;
	}

	/** Returns the oracle. */
	public IOracle getOracle() {
		return oracle;
	}

	/** Sets oracle. */
	public void setOracle(IOracle oracle) {
		this.oracle = oracle;
	}

	/** Returns debugger. */
	public IDebugger getDebugger() {
		return debugger;
	}

	/** Sets debugger. */
	public void setDebugger(IDebugger debugger) {
		this.debugger = debugger;
	}
}
