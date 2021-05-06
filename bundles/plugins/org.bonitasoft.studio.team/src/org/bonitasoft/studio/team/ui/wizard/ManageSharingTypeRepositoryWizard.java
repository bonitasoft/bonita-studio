/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.wizard;

import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.ui.wizard.page.ManageSharingTypeRepositoryWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Aurelien Pupier
 */
public class ManageSharingTypeRepositoryWizard extends Wizard {

	@Override
	public void addPages() {
		super.addPages();
		addPage(new ManageSharingTypeRepositoryWizardPage());
	}

	@Override
	public boolean performFinish() {
		return true;
	}

    @Override
    public void createPageControls(final Composite pageContainer) {
        super.createPageControls(pageContainer);
        setWindowTitle(Messages.manageSharingTypeRepositoryWIzardpageTitle);
    }

}
