/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.ui.wizard;

import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.BulletPointWidget;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.google.common.base.Strings;

public class FirstCommitDialog extends MessageDialog {

    public static final int COMMIT_AND_PUSH_ID = 0;

    private String commitMessage;

    public FirstCommitDialog(Shell parentShell, String commitMessage) {
        super(parentShell, Messages.shareRepositoryProgressTitle, null, null, 0,
                new String[] { Messages.commitAndPush }, COMMIT_AND_PUSH_ID);
        this.commitMessage = commitMessage;
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(500, SWT.DEFAULT).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().create());

        new BulletPointWidget.Builder()
                .addBulletPoint(Messages.gitInit, BulletPointWidget.BulletPointState.DONE)
                .addBulletPoint(Messages.createGitIgnore, BulletPointWidget.BulletPointState.DONE)
                .addBulletPoint(Messages.firstCommit, BulletPointWidget.BulletPointState.TODO)
                .addBulletPoint(Messages.firstPush, BulletPointWidget.BulletPointState.TODO)
                .createIn(composite);

        Label label = new Label(composite, SWT.WRAP);
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        label.setText(Messages.shareRepositoryProgress);

        new TextAreaWidget.Builder()
                .withLabel(Messages.firstCommitMessage)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .heightHint(100)
                .bindTo(PojoProperties.value("commitMessage").observe(this))
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(o -> validateCommitMessage((String) o)))
                .withModelToTargetStrategy(UpdateStrategyFactory.updateValueStrategy())
                .inContext(new DataBindingContext())
                .createIn(composite);
        return composite;
    }

    private IStatus validateCommitMessage(String message) {
        IStatus status = Strings.isNullOrEmpty(message)
                ? ValidationStatus.error(org.bonitasoft.studio.ui.i18n.Messages.required)
                : ValidationStatus.ok();
        getButton(COMMIT_AND_PUSH_ID).setEnabled(status.isOK());
        return status;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

}
