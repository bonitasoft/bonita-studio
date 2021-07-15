/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.contribution;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog;
import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog.DeployStrategy;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class DeployContributionItem extends ContributionItem {

    public static final String ID = "org.bonitasoft.studio.identity.organization.editor.deploy";

    private static final String DEPLOY_COMMAND = "org.bonitasoft.studio.organization.publish";
    private static final String DEPLOY_ORGA_PARAMETER = "organization";

    private AbstractOrganizationFormPage formPage;
    private ToolItem item;
    private CommandExecutor commandExecutor;

    public DeployContributionItem(AbstractOrganizationFormPage formPage) {
        super(ID);
        this.formPage = formPage;
        commandExecutor = new CommandExecutor();
    }

    @Override
    public void fill(ToolBar parent, int index) {
        item = new ToolItem(parent, SWT.PUSH);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ID);
        item.setText(Messages.deploy);
        item.setToolTipText(Messages.deploy);
        item.setImage(Pics.getImage(PicsConstants.deploy));
        item.addListener(SWT.Selection, event -> deploy());
        item.setEnabled(isEnabled());
    }

    protected void deploy() {
        if (formPage.getEditor().isDirty()) {
            String name = formPage.getEditor().getEditorInput().getName();
            DeployStrategy choice = SaveBeforeDeployDialog.open(name);
            if (choice == DeployStrategy.SAVE_AND_DEPLOY) {
                formPage.getEditor().doSave(new NullProgressMonitor());
            } else if (choice == DeployStrategy.CANCEL) {
                return;
            }
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(DEPLOY_ORGA_PARAMETER, formPage.getEditorInput().getName());
        commandExecutor.executeCommand(DEPLOY_COMMAND, parameters);
    }

    @Override
    public void update() {
        if (item != null && !item.isDisposed()) {
            item.setEnabled(isEnabled());
        }
    }

}
