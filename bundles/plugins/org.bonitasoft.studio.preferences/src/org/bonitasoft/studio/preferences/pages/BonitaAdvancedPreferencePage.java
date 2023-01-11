/*
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences.pages;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.i18n.Messages;
import org.eclipse.jface.preference.BooleanFieldEditor;

/**
 * @author Romain Bioteau
 */

public class BonitaAdvancedPreferencePage extends AbstractBonitaPreferencePage {

    public static final String HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING = "HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING";

    private static final String ADVANCED_CONTRIBUTOR_ID = "Advanced";

    public BonitaAdvancedPreferencePage() {
        super(GRID);
        setPreferenceStore(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore());
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common
     * GUI blocks needed to manipulate various types of preferences. Each field
     * editor knows how to save and restore itself.
     */
    @Override
    public void createFieldEditors() {

        createTitleBar(Messages.BonitaPreferenceDialog_Advanced, Pics.getImage(PicsConstants.preferenceAdvanced), false);

        var showConnectorEditionConfirmation = new BooleanFieldEditor(
                HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING,
                Messages.doNotDisplayConnectorDefConfirmationMessage, getFieldEditorParent());
        addField(showConnectorEditionConfirmation);

        var notifyBdmDeploymentRequired = new BooleanFieldEditor(BonitaPreferenceConstants.NOTIFY_BDM_DEPLOYMENT_REQUIRED,
                Messages.notifyBdmDeploymentRequiredMessage, getFieldEditorParent());
        addField(notifyBdmDeploymentRequired);

        createPreferenceEditorContributions(ADVANCED_CONTRIBUTOR_ID);
    }

}
