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

import static com.google.common.base.Strings.isNullOrEmpty;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Index;
import org.bonitasoft.engine.bdm.model.UniqueConstraint;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ManageBusinessDataModelWizard extends Wizard {

    private BusinessDataModelWizardPage page;

    private final BusinessObjectModelFileStore fStore;

    private BusinessObjectModel businessObjectModel;

    private boolean newBdm = false;

    public ManageBusinessDataModelWizard(final BusinessObjectModelFileStore fStore) {
        this.fStore = fStore;
        businessObjectModel = fStore.getContent();
        if (businessObjectModel == null) {
            newBdm = true;
            businessObjectModel = new BusinessObjectModel();
        }
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
        page = new BusinessDataModelWizardPage(businessObjectModel);
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
                final MessageDialogWithToggle confirmDialog = MessageDialogWithPrompt.openOkCancelConfirm(getShell(), Messages.bdmCompatibilityTitle,
                        Messages.bdmCompatibilityMsg,
                        Messages.clearExistingBusinessData, preferenceStore.getBoolean(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_INSTALL),
                        preferenceStore,
                        EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_INSTALL);
                confirm = confirmDialog.getReturnCode() == Dialog.OK;
            }
            if (confirm) {
                final boolean isValid = validateAndSaveBDM();
                if (isValid) {
                    return installBDM();
                }
            }
        }
        return false;
    }


    private boolean validateAndSaveBDM() {
        try {
            getContainer().run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    if (businessObjectModel.getBusinessObjects().isEmpty()) {
                        fStore.delete();
                    } else {
                        monitor.beginTask(Messages.validatingBDM, IProgressMonitor.UNKNOWN);
                        for (final BusinessObject bo : businessObjectModel.getBusinessObjects()) {
                            if (bo.getFields().isEmpty()) {
                                throw new InvocationTargetException(new Exception(Messages.bind(Messages.atLeastOneAttributeShouldExists,
                                        NamingUtils.getSimpleName(bo.getQualifiedName()))));
                            }
                            for (final Index index : bo.getIndexes()) {
                                validateIndex(index, bo);
                            }
                            for (final UniqueConstraint uc : bo.getUniqueConstraints()) {
                                validateUniqueConstraint(uc, bo);
                            }
                        }
                        monitor.setTaskName(Messages.saving);
                        fStore.save(businessObjectModel);
                        monitor.done();
                    }
                }
            });
        } catch (final InvocationTargetException e) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.modelValidationFailedTitle, e.getCause().getMessage());
            return false;
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
            return false;
        }
        return true;
    }

    private boolean installBDM() {
        try {
            getContainer().run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    new DeployBDMOperation(fStore).run(monitor);
                }
            });
        } catch (final InvocationTargetException e) {
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.installFailedTitle, Messages.installFailedMessage,
                    handleTargetExceptionStacktrace(e))
            .open();
            return false;
        } catch (final InterruptedException e) {
            return false;
        }
        return true;
    }

    private Throwable handleTargetExceptionStacktrace(final InvocationTargetException e) {
        final Throwable targetException = e.getTargetException();
        int index = -1;
        for (int i = 0; i < targetException.getStackTrace().length; i++) {
            final StackTraceElement element = targetException.getStackTrace()[i];
            final String className = element.getClassName();
            if (!isNullOrEmpty(className) && className.contains("org.hibernate.HibernateException")) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            targetException.setStackTrace(Arrays.copyOfRange(targetException.getStackTrace(), index, targetException.getStackTrace().length));
        }
        return targetException;
    }

    protected IPreferenceStore getPreferenceStore() {
        return EnginePlugin.getDefault().getPreferenceStore();
    }

    protected void validateUniqueConstraint(final UniqueConstraint uc, final BusinessObject bo) throws InvocationTargetException {
        if (uc.getFieldNames() == null || uc.getFieldNames().isEmpty()) {
            throw new InvocationTargetException(new Exception(Messages.bind(Messages.atLeastOneAttributeShouldBelongToConstraint, uc.getName())));
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
                throw new InvocationTargetException(new Exception(Messages.bind(Messages.attributeInConstraintNotExists, fName, uc.getName())));
            }
        }
    }

    protected void validateIndex(final Index index, final BusinessObject bo) throws InvocationTargetException {
        if (index.getFieldNames() == null || index.getFieldNames().isEmpty()) {
            throw new InvocationTargetException(new Exception(Messages.bind(Messages.atLeastOneAttributeShouldBelongToIndex, index.getName())));
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
                throw new InvocationTargetException(new Exception(Messages.bind(Messages.attributeInIndexNotExists, fName, index.getName())));
            }
        }
    }

}
