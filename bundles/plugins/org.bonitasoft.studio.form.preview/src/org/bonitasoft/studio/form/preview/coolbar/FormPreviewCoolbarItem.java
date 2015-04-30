/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.form.preview.coolbar;

import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.form.preview.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.profiles.manager.BonitaProfilesManager;
import org.bonitasoft.studio.profiles.manager.IBonitaActivitiesCategory;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * @author Romain Bioteau
 */
public class FormPreviewCoolbarItem extends ContributionItem implements IBonitaContributionItem {

    private Command getCommand() {
        final ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        return service.getCommand("org.bonitasoft.studio.application.preview");
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#getId()
     */
    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.formPreview";
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        final Command cmd = getCommand();
        return cmd.isEnabled();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#isVisible()
     */
    @Override
    public boolean isVisible() {
        return BonitaProfilesManager.getInstance().isEnabled(IBonitaActivitiesCategory.FORMS_MODELING);
    }

    @Override
    public void fill(final ToolBar toolbar, final int index, final int iconSize) {
        final ToolItem item = new ToolItem(toolbar, SWT.PUSH);
        item.setToolTipText(Messages.previewButton);
        if (iconSize < 0) {
            item.setText(Messages.previewButton);
            item.setImage(Pics.getImage(PicsConstants.coolbar_preview_48));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_preview_disabled_48));
        } else {
            item.setImage(Pics.getImage(PicsConstants.coolbar_preview_16));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_preview_disabled_16));
        }
        item.setEnabled(false);
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Command command = getCommand();
                try {
                    command.executeWithChecks(new ExecutionEvent());
                } catch (final Exception e1) {
                    BonitaStudioLog.error(e1);
                }
            }
        });
    }

}
