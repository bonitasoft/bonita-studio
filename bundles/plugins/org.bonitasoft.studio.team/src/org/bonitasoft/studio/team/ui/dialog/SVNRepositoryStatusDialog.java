/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.dialog;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.repository.Repository;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;

/**
 * @author Baptiste Mesta
 */
public class SVNRepositoryStatusDialog extends Dialog {

    private final String currentRepository;
    private String repositoryUrl;
    private String connectionUsername;
    private String isOnline;

    /**
     * @param shell
     */
    public SVNRepositoryStatusDialog(final Shell shell) {
        super(shell);

        currentRepository = CommonRepositoryPlugin.getCurrentRepository();
        try {
            final Repository repository = (Repository) RepositoryManager.getInstance()
                    .getCurrentRepository();
            final IRepositoryLocation location = repository.getLocationForRepository();
            repositoryUrl = location.getUrl();
            connectionUsername = repository.getUser();
            final boolean isOnlineRepo = !TeamRepositoryUtil.isLocalRepository()
                    && TeamRepositoryUtil.isRepositoryValid(location);
            if (isOnlineRepo) {
                isOnline = Messages.repositoryStatusDialog_online;
            } else {
                isOnline = Messages.repositoryStatusDialog_offline;
            }
        } catch (final Throwable t) {
            BonitaStudioLog.error(t);
            repositoryUrl = "Unavailable";
            connectionUsername = "Unavailable";
            isOnline = "Unavailable";
        }

    }

    @Override
    protected Control createDialogArea(final Composite parent) {
        getShell().setText(Messages.SVNStatusDialog);
        final Composite composite = (Composite) super.createDialogArea(parent);
        final GridLayout gl_composite = new GridLayout(2, false);
        gl_composite.horizontalSpacing = 20;
        composite.setLayout(gl_composite);
        final Label title = new Label(composite, SWT.NONE);
        title.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
        title.setText(Messages.repositoryStatusDialog_title);

        final Label repoName_label = new Label(composite, SWT.NONE);
        final GridData gd_repoName_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_repoName_label.verticalIndent = 10;
        repoName_label.setLayoutData(gd_repoName_label);
        repoName_label.setText(Messages.repositoryStatusDialog_repositoryName);

        final Label repoName_Value = new Label(composite, SWT.NONE);
        final GridData gd_repoName_Value = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_repoName_Value.verticalIndent = 10;
        repoName_Value.setLayoutData(gd_repoName_Value);
        repoName_Value.setText(currentRepository);

        final Label repoKind_label = new Label(composite, SWT.NONE);
        repoKind_label.setText(Messages.repositoryStatusDialog_kind);

        final Label repoKind_value = new Label(composite, SWT.NONE);
        repoKind_value.setText(Messages.repositoryStatusDialog_connected);

        final Group grpConnectionInformation = new Group(composite, SWT.NONE);
        grpConnectionInformation.setText(Messages.repositoryStatusDialog_connectionInfo);
        final GridLayout gl_grpConnectionInformation = new GridLayout(2, false);
        gl_grpConnectionInformation.horizontalSpacing = 20;
        grpConnectionInformation.setLayout(gl_grpConnectionInformation);
        grpConnectionInformation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

        final Label connectionStatus_label = new Label(grpConnectionInformation, SWT.NONE);
        connectionStatus_label.setSize(116, 17);
        connectionStatus_label.setText(Messages.repositoryStatusDialog_connectionStatus);

        final Label connectionStatus_value = new Label(grpConnectionInformation, SWT.NONE);
        connectionStatus_value.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        connectionStatus_value.setText(isOnline);

        final Label repoUrl_Label = new Label(grpConnectionInformation, SWT.NONE);
        repoUrl_Label.setSize(95, 17);
        repoUrl_Label.setText(Messages.repositoryStatusDialog_repoURL);

        final Label repoUrl_value = new Label(grpConnectionInformation, SWT.WRAP);
        repoUrl_value.setSize(65, 17);
        repoUrl_value.setText(repositoryUrl);
        repoUrl_value.setLayoutData(GridDataFactory.fillDefaults().hint(300, SWT.DEFAULT).create());
        final Label connectionUsername_label = new Label(grpConnectionInformation, SWT.NONE);
        connectionUsername_label.setText(Messages.repositoryStatusDialog_connectionUsername);

        final Label connectionUsername_value = new Label(grpConnectionInformation, SWT.NONE);
        connectionUsername_value.setText(connectionUsername);

        return composite;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButton(org.eclipse.swt.widgets.Composite, int, java.lang.String, boolean)
     */
    @Override
    protected Button createButton(final Composite parent, final int id, final String label,
            final boolean defaultButton) {
        if (id == IDialogConstants.CANCEL_ID) {
            return null;
        }
        return super.createButton(parent, id, label, defaultButton);
    }

}
