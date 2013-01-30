
package org.bonitasoft.studio.condition;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class ConditionModelStandaloneSetup extends ConditionModelStandaloneSetupGenerated{

	public static void doSetup() {
		new ConditionModelStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

