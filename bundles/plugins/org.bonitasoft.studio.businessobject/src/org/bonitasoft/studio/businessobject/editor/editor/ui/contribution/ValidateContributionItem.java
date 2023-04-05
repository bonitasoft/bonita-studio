/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.contribution;

import java.util.Collection;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.BusinessObjectListValidator;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ValidateContributionItem extends ContributionItem {

    public static final String ID = "org.bonitasoft.studio.bdm.editor.validate";
    private final AbstractBdmFormPage formPage;
    protected ToolItem item;

    public ValidateContributionItem(AbstractBdmFormPage formPage) {
        super(ID);
        this.formPage = formPage;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        item = new ToolItem(parent, SWT.PUSH);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ID);
        item.setText(Messages.validate);
        item.setToolTipText(Messages.validateTooltip);
        item.setImage(Pics.getImage(PicsConstants.validate));
        item.addListener(SWT.Selection, e -> validate());
        item.setEnabled(true);
    }

    private void validate() {
        MultiStatus status = new MultiStatus(BusinessObjectPlugin.PLUGIN_ID, 0, "", null);
        if (formPage.observeWorkingCopy().getValue().getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream).count() == 0) {
            status.add(ValidationStatus.error(Messages.emptyBdm));
        } else {
            BusinessObjectListValidator validator = new BusinessObjectListValidator(formPage.observeWorkingCopy());
            formPage.observeWorkingCopy().getValue().getPackages().stream()
                    .map(validator::validate)
                    .forEach(status::add);
        }
        if (!status.isOK()) {
            new MultiStatusDialog(Display.getDefault().getActiveShell(),
                    Messages.validationStatus,
                    Messages.validatioNStatusDesc,
                    new String[] { IDialogConstants.OK_LABEL },
                    status).open();
        } else {
            MessageDialog.openInformation(Display.getDefault().getActiveShell(),
                    Messages.validationStatus,
                    Messages.bdmValidMessage);
        }
    }

}
