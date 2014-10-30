/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.data.ui.wizard;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IProposalListener;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Maxence Raoux
 *
 */
public class CreateVariableProposalListener implements IProposalListener {

    private boolean isPageFlowContext = false;

    private EStructuralFeature feature;

    @Override
    public String handleEvent(EObject context, final String fixedReturnType) {
        Assert.isNotNull(context);
        while (!(context instanceof AbstractProcess || context instanceof Activity)) {
            context = context.eContainer();
        }
        if (feature == null) {
            feature = ProcessPackage.Literals.DATA_AWARE__DATA;
        }
        final Set<EStructuralFeature> res = new HashSet<EStructuralFeature>();
        res.add(feature);
        final DataWizard newWizard = new DataWizard(TransactionUtil.getEditingDomain(context), context, feature, res, true, fixedReturnType);
        newWizard.setIsPageFlowContext(isPageFlowContext);
        Shell activeShell = Display
                .getDefault().getActiveShell();
        if (activeShell.getParent() != null) {
            activeShell = activeShell.getParent().getShell();
        }
        final DataWizardDialog wizardDialog = new DataWizardDialog(activeShell,
                newWizard, null);
        if (wizardDialog.open() == Dialog.OK) {
            final EObject obj = newWizard.getWorkingCopy();
            if (obj instanceof Data) {
                final Data d = (Data) obj;
                if (d != null) {
                    return d.getName();
                }
            }
        }

        return null;

    }

    @Override
    public String toString() {
        return Messages.createVariable;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isPageFlowContext()
     */
    @Override
    public boolean isPageFlowContext() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsPageFlowContext(boolean)
     */
    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IProposalListener#setEStructuralFeature(org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public void setEStructuralFeature(final EStructuralFeature feature) {
        this.feature = feature;

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }

    @Override
    public boolean isRelevant(final EObject context) {
        return true;
    }

}
