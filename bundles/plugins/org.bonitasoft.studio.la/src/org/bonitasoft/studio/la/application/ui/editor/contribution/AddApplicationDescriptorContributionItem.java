/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.editor.contribution;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.la.application.ui.editor.listener.AddApplicationDescriptorListener;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class AddApplicationDescriptorContributionItem extends ContributionItem {

    private static final String ID = "org.bonitasoft.studio.la.ui.editor.addDescriptor";

    private final AddApplicationDescriptorListener listener;
    private ToolItem item;
    private ApplicationFormPage applicationOverviewPage;

    public AddApplicationDescriptorContributionItem(Shell shell, ApplicationFormPage applicationOverviewPage,
            RepositoryAccessor repositoryAccessor) {
        super(ID);
        this.applicationOverviewPage = applicationOverviewPage;
        this.listener = new AddApplicationDescriptorListener(shell, applicationOverviewPage, repositoryAccessor);
    }

    @Override
    public void fill(ToolBar parent, int index) {
        item = new ToolItem(parent, SWT.PUSH);
        item.setText(Messages.add);
        item.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ID);
        item.setToolTipText(Messages.addApplicationDescriptorTitle);
        item.setImage(Pics.getImage(PicsConstants.add_item));
        item.addListener(SWT.Selection, listener::handleEvent);
    }

    @Override
    public boolean isEnabled() {
        return !applicationOverviewPage.isErrorState();
    }

    @Override
    public void update() {
        if (item != null && !item.isDisposed()) {
            item.setEnabled(isEnabled());
        }
    }

}
