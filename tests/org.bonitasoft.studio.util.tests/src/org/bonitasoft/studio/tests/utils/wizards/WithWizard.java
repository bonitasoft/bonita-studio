package org.bonitasoft.studio.tests.utils.wizards;

import java.lang.annotation.*;

import org.eclipse.jface.wizard.IWizard;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface WithWizard {
	Class<? extends IWizard> value();

}