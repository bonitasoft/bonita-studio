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
package org.bonitasoft.studio.legacy6x.preferences;

import org.bonitasoft.studio.legacy6x.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.AbstractBonitaPreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.xtext.ui.editor.preferences.fields.LabelFieldEditor;


public class Legacy6xPreferencePage extends AbstractBonitaPreferencePage {

    public Legacy6xPreferencePage() {
        super(GRID);
        setPreferenceStore(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore());
    }

    @Override
    protected void createFieldEditors() {
        createTitleBar(org.bonitasoft.studio.preferences.i18n.Messages.BonitaPreferenceDialog_legacy6x, Pics.getImage(PicsConstants.preferenceLegacy6x), false);
        createDescription();
        createBooleanField();
    }

    private void createBooleanField() {
        final BooleanFieldEditor legacyModeFieldEditor = new BooleanFieldEditor(
                BonitaPreferenceConstants.SHOW_LEGACY_6X_MODE,
                Messages.showLegacyMode,
                getFieldEditorParent());
        addField(legacyModeFieldEditor);
    }

    private void createDescription() {
        final String descriptionText = Messages.bind(Messages.showLegcyModeDescriptionPreferncePage,
                org.bonitasoft.studio.common.Messages.bonitaStudioModuleName);
        addField(new LabelFieldEditor(descriptionText, getFieldEditorParent()));
    }

}
