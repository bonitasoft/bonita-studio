/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import org.codehaus.groovy.eclipse.wizards.NewTypeWizard;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.PlatformUI;

public class NewGroovyClassHandler extends NewWizardHandler {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.rest.api.extension.ui.handler.NewWizardAction#createWizard(org.eclipse.jface.viewers.IStructuredSelection)
     */
    @Override
    protected IWizard createWizard(IStructuredSelection selection) {
        final NewTypeWizard wizard = new NewTypeWizard();
        wizard.init(PlatformUI.getWorkbench(), selection);
        return wizard;
    }

}
