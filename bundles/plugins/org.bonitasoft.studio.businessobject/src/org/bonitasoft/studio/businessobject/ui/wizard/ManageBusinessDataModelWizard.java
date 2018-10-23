/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.engine.api.result.Status;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Index;
import org.bonitasoft.engine.bdm.model.UniqueConstraint;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.validator.BusinessObjectModelValidator;
import org.bonitasoft.engine.bdm.validator.ValidationStatus;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMStackTraceResolver;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.status.BusinessDataModelStatusMapper;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.core.runtime.jobs.JobGroup;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class ManageBusinessDataModelWizard extends Wizard {

    private BusinessDataModelWizardPage page;

    private final BusinessObjectModelFileStore fStore;

    private BusinessObjectModel businessObjectModel;

    private boolean newBdm = false;

    private IDiffLogger diffLogger;

    private Runnable deployFinishRunnable;

    public ManageBusinessDataModelWizard(final BusinessObjectModelFileStore fStore, IDiffLogger diffLogger,
            Runnable deployFinishRunnable) {
        this.fStore = fStore;
        this.deployFinishRunnable = deployFinishRunnable;
        businessObjectModel = fStore.getContent();
        if (businessObjectModel == null) {
            newBdm = true;
            businessObjectModel = new BusinessObjectModel();
        }
        this.diffLogger = diffLogger;
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.manageBusinessDataModelTitle);
        setNeedsProgressMonitor(true);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        page = new BusinessDataModelWizardPage(businessObjectModel, diffLogger);
        page.setTitle(Messages.manageBusinessDataModelTitle);
        page.setDescription(Messages.manageBusinessDataModelDesc);
        addPage(page);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        if (businessObjectModel != null) {
            boolean confirm = true;
            if (!newBdm) {
                final IPreferenceStore preferenceStore = getPreferenceStore();
                final MessageDialogWithToggle confirmDialog = MessageDialogWithPrompt.openOkCancelConfirm(getShell(),
                        Messages.bdmCompatibilityTitle,
                        Messages.bdmCompatibilityMsg,
                        Messages.clearExistingBusinessData,
                        preferenceStore.getBoolean(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_INSTALL),
                        preferenceStore,
                        EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_INSTALL);
                confirm = confirmDialog.getReturnCode() == Dialog.OK;
            }
            if (confirm && validateAndSaveBDM()) {
                return installBDM();
            }
        }
        return false;
    }

    private boolean validateAndSaveBDM() {
        try {
            getContainer().run(false, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    if (businessObjectModel.getBusinessObjects().isEmpty()) {
                        fStore.delete();
                    } else {
                        monitor.beginTask(Messages.validatingBDM, IProgressMonitor.UNKNOWN);
                        ValidationStatus validate = new BusinessObjectModelValidator().validate(businessObjectModel);
                        if (!validate.getStatuses().isEmpty()) {
                            MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);
                            for (Status engineStatus : validate.getStatuses()) {
                                String translatedMessage = BusinessDataModelStatusMapper.instance()
                                        .localizedMessage(engineStatus);
                                if (engineStatus.getLevel().equals(Status.Level.ERROR)) {
                                    status.add(org.eclipse.core.databinding.validation.ValidationStatus
                                            .error(translatedMessage));
                                } else {
                                    status.add(org.eclipse.core.databinding.validation.ValidationStatus
                                            .warning(translatedMessage));
                                }
                            }
                            if (!manageErrors(status, getShell())) {
                                throw new InterruptedException();
                            }
                        }
                        save(monitor);
                        monitor.done();
                    }
                }
            });
        } catch (final InvocationTargetException e) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.modelValidationFailedTitle,
                    e.getCause().getMessage());
            BonitaStudioLog.error(e);
            return false;
        } catch (final InterruptedException e) {
            return false;
        }
        return true;
    }

    private boolean manageErrors(MultiStatus status, Shell shell) throws RuntimeException {
        int open = new MultiStatusDialog(shell,
                Messages.modelValidationFailedTitle,
                Messages.modelValidationFailedMsg,
                status.getSeverity() < IStatus.ERROR ? MessageDialog.WARNING : MessageDialog.ERROR,
                new String[] { IDialogConstants.CANCEL_LABEL, Messages.continueDeploy },
                status,
                1,
                s -> s.getSeverity() < IStatus.ERROR).open();
        return open > 0; // continue
    }

    protected void save(final IProgressMonitor monitor) {
        monitor.setTaskName(Messages.saving);
        fStore.save(businessObjectModel);
    }

    protected boolean installBDM() {
        JobGroup installBDMJobGroup = new JobGroup("Install BDM", 0, 2);
        Job generateBDMJarJob = new Job(Messages.generatingJarFromBDMModel) {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                GenerateBDMOperation generateBDMOperation = new GenerateBDMOperation(fStore);
                try {
                    generateBDMOperation.run(monitor);
                } catch (InvocationTargetException | InterruptedException e) {
                    return new org.eclipse.core.runtime.Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID,
                            e.getMessage(), e);
                }
                return org.eclipse.core.runtime.Status.OK_STATUS;
            }
        };
        Job deployBDMJarJob = new Job(Messages.generatingJarFromBDMModel) {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                return runDeployBDM(monitor);
            }

        };
        deployBDMJarJob.addJobChangeListener(new JobChangeAdapter() {

            @Override
            public void done(IJobChangeEvent event) {
                Display.getDefault().asyncExec(deployFinishRunnable);
            }

        });
        generateBDMJarJob.setJobGroup(installBDMJobGroup);
        deployBDMJarJob.setJobGroup(installBDMJobGroup);
        generateBDMJarJob.schedule();
        deployBDMJarJob.schedule();
        return true;
    }

    protected IStatus runDeployBDM(IProgressMonitor monitor) {
        DeployBDMOperation deployBDMOperation = new DeployBDMOperation(fStore);
        try {
            deployBDMOperation.run(monitor);
        } catch (InvocationTargetException e) {
            return new org.eclipse.core.runtime.Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID,
                    Messages.installFailedMessage, new DeployBDMStackTraceResolver().reduceHibernateException(e));
        } catch (InterruptedException e) {
            return new org.eclipse.core.runtime.Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID,
                    Messages.installFailedMessage, e);
        }
        return org.eclipse.core.runtime.Status.OK_STATUS;
    }

    protected IDiffLogger getDiffLogger() {
        return diffLogger;
    }

    protected IPreferenceStore getPreferenceStore() {
        return EnginePlugin.getDefault().getPreferenceStore();
    }

    protected void validateUniqueConstraint(final UniqueConstraint uc, final BusinessObject bo)
            throws InvocationTargetException {
        if (uc.getFieldNames() == null || uc.getFieldNames().isEmpty()) {
            throw new InvocationTargetException(
                    new Exception(Messages.bind(Messages.atLeastOneAttributeShouldBelongToConstraint, uc.getName())));
        }
        for (final String fName : uc.getFieldNames()) {
            boolean exists = false;
            for (final Field f : bo.getFields()) {
                if (f.getName().equals(fName)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                throw new InvocationTargetException(
                        new Exception(Messages.bind(Messages.attributeInConstraintNotExists, fName, uc.getName())));
            }
        }
    }

    protected void validateIndex(final Index index, final BusinessObject bo) throws InvocationTargetException {
        if (index.getFieldNames() == null || index.getFieldNames().isEmpty()) {
            throw new InvocationTargetException(
                    new Exception(Messages.bind(Messages.atLeastOneAttributeShouldBelongToIndex, index.getName())));
        }
        for (final String fName : index.getFieldNames()) {
            boolean exists = false;
            for (final Field f : bo.getFields()) {
                if (f.getName().equals(fName)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                throw new InvocationTargetException(
                        new Exception(Messages.bind(Messages.attributeInIndexNotExists, fName, index.getName())));
            }
        }
    }

}
