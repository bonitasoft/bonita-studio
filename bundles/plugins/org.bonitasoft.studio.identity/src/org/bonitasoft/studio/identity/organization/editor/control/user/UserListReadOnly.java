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
package org.bonitasoft.studio.identity.organization.editor.control.user;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.Objects;

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.widgets.Composite;

public class UserListReadOnly extends UserList {

    public UserListReadOnly(Composite parent, AbstractOrganizationFormPage formPage, DataBindingContext ctx) {
        super(parent, formPage, ctx);
        
        var buttonContainer = formPage.getToolkit().createComposite(section);
        buttonContainer.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(2, LayoutConstants.getSpacing().y).create());
        new DynamicButtonWidget.Builder()
                .withImage(Pics.getImage(PicsConstants.edit_simple))
                .withHotImage(Pics.getImage(PicsConstants.edit_simple_hot))
                .withToolkit(formPage.getToolkit())
                .onClick(e -> formPage.getEditor().setActivePage("users"))
                .createIn(buttonContainer);
        var setDefaultButton = new DynamicButtonWidget.Builder()
                .withImage(Pics.getImage(PicsConstants.organization_user))
                .withTooltipText("Set as default")
                .withToolkit(formPage.getToolkit())
                .onClick(e -> updateDefaultUserPreference(selectionObservable.getValue().getUserName()))
                .createIn(buttonContainer);
        
        ctx.bindValue(selectionObservable, 
        		setDefaultButton.observeEnable(), 
        		updateValueStrategy().withConverter(IConverter.<User,Boolean>create(user -> user != null && !formPage.isDefaultUser(user))).create(), 
        		neverUpdateValueStrategy().create());

        section.setTextClient(buttonContainer);
    }
    
	private void updateDefaultUserPreference(String newName) {
		if (!Objects.equals(newName, activeOrganizationProvider.getDefaultUser())) {
			activeOrganizationProvider.saveDefaultUser(newName);
			BonitaNotificator.openInfoNotification(Messages.defaultUserUpdatedTitle,
					String.format(Messages.defaultUserUpdated, newName));
			refreshUserList();
		}
	}


    @Override
    protected void createToolbar(Composite parent) {
        // Nothing
    }

    @Override
    protected void enableButtons() {
        // Nothing
    }

    @Override
    protected void createSearchField(Composite parent) {
        // Nothing
    }

}
