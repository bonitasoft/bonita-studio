/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.data.ui.property.section;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.DataPlugin;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.refactoring.core.RefactorDataOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class RemoveDataHandler {

    public void execute(final IStructuredSelection structuredSelection, final EObject container, final EStructuralFeature dataFeature) {
        final String[] buttonList = { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
        final OutlineDialog dialog = new OutlineDialog(Display.getDefault().getActiveShell(),
                org.bonitasoft.studio.common.Messages.removalConfirmationDialogTitle, Display.getCurrent().getSystemImage(
                        SWT.ICON_WARNING), createMessage(structuredSelection), MessageDialog.CONFIRM, buttonList, 1, structuredSelection.toList());
        if (dialog.open() == Dialog.OK) {
            final IProgressService service = PlatformUI.getWorkbench().getProgressService();
            final RefactorDataOperation op = new RefactorDataOperation(RefactoringOperationType.REMOVE);
            for (final Object d : structuredSelection.toList()) {
                op.setEditingDomain(TransactionUtil.getEditingDomain(container));
                op.addItemToRefactor(null, (Data) d);
                op.setDataContainer((DataAware) container);
                op.setDataContainmentFeature(dataFeature);
                op.setAskConfirmation(true);
            }
            try {
                if (op.canExecute()) {
                    service.run(true, true, op);
                }
            } catch (final InvocationTargetException e) {
                BonitaStudioLog.error(e, DataPlugin.PLUGIN_ID);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e, DataPlugin.PLUGIN_ID);
            }
        }
    }

    private String createMessage(final IStructuredSelection structruredSelection) {
        final Object[] selection = structruredSelection.toArray();
        final StringBuilder res = new StringBuilder(Messages.deleteDialogConfirmMessage);
        res.append(' ');
        res.append(((Data) selection[0]).getName());
        for (int i = 1; i < selection.length; i++) {
            res.append(", ");res.append(((Data) selection[i]).getName()); //$NON-NLS-1$
        }
        res.append(" ?"); //$NON-NLS-1$
        return res.toString();
    }
}
