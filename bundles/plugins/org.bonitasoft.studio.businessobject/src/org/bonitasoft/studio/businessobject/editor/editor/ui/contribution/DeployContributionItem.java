/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.contribution;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.handler.CleanDeployBDMHandler;
import org.bonitasoft.studio.businessobject.ui.handler.DeployBDMHandler;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog;
import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog.DeployStrategy;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class DeployContributionItem extends ContributionItem {

    public static final String ID = "org.bonitasoft.studio.bdm.editor.deploy";
    private final AbstractBdmFormPage formPage;
    protected ToolItem item;

    public DeployContributionItem(AbstractBdmFormPage formPage) {
        super(ID);
        this.formPage = formPage;
    }

    @Override
    public void fill(ToolBar parent, int index) {
        item = new ToolItem(parent, SWT.PUSH);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ID);
        item.setText(Messages.deploy);
        item.setToolTipText(Messages.deploy);
        Image image = PreferenceUtil.isDarkTheme()
                ? Pics.getImage(PicsConstants.deploy_16_dark)
                : Pics.getImage(PicsConstants.deploy_16);
        item.setImage(image);
        item.addListener(SWT.Selection, event -> deploy(false));
        item.setEnabled(isEnabled());
    }

    protected void deploy(boolean clean) {
        if (formPage.getEditor().isDirty()) {
            String name = formPage.getEditor().getEditorInput().getName();
            DeployStrategy choice = SaveBeforeDeployDialog.open(name);
            if (choice == DeployStrategy.SAVE_AND_DEPLOY) {
                formPage.getEditor().doSave(new NullProgressMonitor());
                if (formPage.getEditor().isDirty()) {
                    MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.deployCancelTitle,
                            Messages.deployCancel);
                    return;
                }
            } else if (choice == DeployStrategy.CANCEL) {
                return;
            }
        }
        if (clean) {
            new CleanDeployBDMHandler().deploy(formPage.getRepositoryAccessor(), Display.getDefault().getActiveShell());
        } else {
            new DeployBDMHandler().deploy(formPage.getRepositoryAccessor(), Display.getDefault().getActiveShell());
        }
        formPage.getEditorContribution().observeDeployRequired().setValue(false);
    }

    @Override
    public void update() {
        if (item != null && !item.isDisposed()) {
            item.setEnabled(isEnabled());
        }
    }

    @Override
    public boolean isEnabled() {
        return formPage.getRepositoryAccessor()
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, false) != null;
    }

}
