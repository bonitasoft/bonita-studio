/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.pages.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Maxence Raoux
 * 
 */
public class AbstractDialog extends Dialog {

	protected AbstractDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void initializeBounds() {
		super.initializeBounds();
		int parentCenterX = getParentShell().getLocation().x+ getParentShell().getSize().x / 2;
		int parentCenterY = getParentShell().getLocation().y+ getParentShell().getSize().y / 2;
		getShell().setLocation(parentCenterX - getShell().getSize().x / 2,	parentCenterY - getShell().getSize().y / 2);
	}

}
