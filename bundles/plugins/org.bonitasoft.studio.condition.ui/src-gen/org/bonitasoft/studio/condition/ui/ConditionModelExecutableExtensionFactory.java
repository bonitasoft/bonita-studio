/*
 * generated by Xtext
 */
package org.bonitasoft.studio.condition.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ConditionModelExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return ConditionModelActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return ConditionModelActivator.getInstance().getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
	}
	
}
