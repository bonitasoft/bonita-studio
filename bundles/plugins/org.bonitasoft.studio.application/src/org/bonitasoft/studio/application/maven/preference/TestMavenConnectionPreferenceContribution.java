/**
 * Copyright (C) 2023 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.maven.preference;

import java.util.List;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.maven.BonitaMavenConfigurationManager;
import org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.widgets.Composite;


public class TestMavenConnectionPreferenceContribution implements IPreferenceFieldEditorContribution {

    @Override
    public List<FieldEditor> createFieldEditors(Composite parent) {
        var editor = new BooleanFieldEditor(BonitaMavenConfigurationManager.SKIP_MAVEN_CONFIGURATION_CKECK_PREFERENCE, 
                Messages.ignoreConnectionTestAtStartup, 
                parent);
        editor.setPreferenceStore(ApplicationPlugin.getDefault().getPreferenceStore());
        return List.of(editor);
    }

    @Override
    public boolean performOk() {
        return true;
    }

    @Override
    public boolean appliesTo(String categoryName) {
        return "Advanced".equals(categoryName);
    }

}
