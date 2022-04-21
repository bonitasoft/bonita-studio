/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.ui.wizard;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.ui.browser.OpenSystemBrowserListener;
import org.bonitasoft.studio.ui.widget.BulletPointWidget;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;

public class FirstPushAbortedDialog extends MessageDialog {

    private static final String DOCUMENTATION_GIT_URL = "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=672&bos_redirect_product=bos&bos_redirect_major_version=%s";

    public FirstPushAbortedDialog(Shell parentShell) {
        super(parentShell, Messages.shareRepositoryProgressTitle, null, null, 0,
                new String[] { IDialogConstants.OK_LABEL }, 0);
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(500, SWT.DEFAULT).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().create());

        new BulletPointWidget.Builder()
                .addBulletPoint(Messages.gitInit, BulletPointWidget.BulletPointState.DONE)
                .addBulletPoint(Messages.createGitIgnore, BulletPointWidget.BulletPointState.DONE)
                .addBulletPoint(Messages.firstCommit, BulletPointWidget.BulletPointState.DONE)
                .addBulletPoint(Messages.firstPush, BulletPointWidget.BulletPointState.ABORTED)
                .createIn(composite);

        Link link = new Link(composite, SWT.WRAP);
        link.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).indent(0, 10).create());
        link.setText(Messages.warningRepoNotPushedMsg);
        link.addListener(SWT.Selection, new OpenSystemBrowserListener(String.format(DOCUMENTATION_GIT_URL, ProductVersion.minorVersion())));
        return composite;
    }

}
