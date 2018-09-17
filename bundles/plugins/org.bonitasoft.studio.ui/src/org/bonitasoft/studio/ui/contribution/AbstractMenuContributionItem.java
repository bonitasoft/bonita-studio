/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.contribution;

import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.ui.bindings.EBindingService;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractMenuContributionItem extends ContributionItem {

    protected ECommandService eCommandService;
    protected EHandlerService eHandlerService;
    protected EBindingService eBindingService;

    public AbstractMenuContributionItem() {
        eCommandService = PlatformUI.getWorkbench().getService(ECommandService.class);
        eHandlerService = PlatformUI.getWorkbench().getService(EHandlerService.class);
        eBindingService = PlatformUI.getWorkbench().getService(EBindingService.class);
    }

    protected MenuItem createMenu(Menu parent, String command) {
        return createMenu(parent, command, Optional.empty());
    }

    @SuppressWarnings("restriction")
    protected MenuItem createMenu(Menu parent, String command, Optional<Image> image) {
        MenuItem item = new MenuItem(parent, SWT.NONE);
        ParameterizedCommand parameterizedCommand = createParametrizedCommand(command);
        try {
            item.setText(parameterizedCommand.getName());
        } catch (NotDefinedException e) {
            BonitaStudioLog.error(
                    String.format("The command '%s' doesn't have any name defined.", parameterizedCommand.getId()), e);
            item.setText(parameterizedCommand.getId());
        }
        item.addListener(SWT.Selection, e -> {
            if (eHandlerService.canExecute(parameterizedCommand)) {
                eHandlerService.executeHandler(parameterizedCommand);
            } else {
                throw new RuntimeException(String.format("Can't execute command %s", parameterizedCommand.getId()));
            }
        });
        item.setEnabled(true);
        appendShortcut(parameterizedCommand, item);
        image.ifPresent(item::setImage);
        return item;
    }

    @SuppressWarnings("restriction")
    protected ParameterizedCommand createParametrizedCommand(String command) {
        return eCommandService.createCommand(command, null);
    }

    private void appendShortcut(ParameterizedCommand parameterizedCommand, MenuItem item) {
        TriggerSequence triggerSequence = eBindingService.getBestSequenceFor(parameterizedCommand);
        if (triggerSequence != null && triggerSequence instanceof KeySequence) {
            KeySequence keySequence = (KeySequence) triggerSequence;
            String rawLabel = item.getText();
            item.setText(String.format("%s  %s%s", rawLabel, '\t', keySequence.format()));
        }
    }

    protected Menu createCascadeMenu(Menu menu, String label) {
        return createCascadeMenu(menu, label, Optional.empty());
    }

    protected Menu createCascadeMenu(Menu menu, String label, Optional<Image> image) {
        MenuItem cascadeMenuItem = new MenuItem(menu, SWT.CASCADE);
        cascadeMenuItem.setText(label);
        image.ifPresent(cascadeMenuItem::setImage);
        Menu cascadeMenu = new Menu(menu);
        cascadeMenuItem.setMenu(cascadeMenu);
        return cascadeMenu;
    }

}
