/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.preference;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;

/**
 * @author Romain
 *
 */
public class DropBusinessDataDBFieldEditorContribution implements IPreferenceFieldEditorContribution {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution#createFieldEditors(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public List<FieldEditor> createFieldEditors(final Composite parent) {
        final List<FieldEditor> editors = new ArrayList<FieldEditor>();

        final BooleanFieldEditor dropOnExitFieldEditor = new BooleanFieldEditor(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_EXIT_PREF,
                Messages.dropBusinessDataDBOnExit,
                BooleanFieldEditor.DEFAULT, parent);
        dropOnExitFieldEditor.setPreferenceStore(EnginePlugin.getDefault().getPreferenceStore());
        editors.add(dropOnExitFieldEditor);

        final BooleanFieldEditor dropOnInstallFieldEditor = new BooleanFieldEditor(EnginePreferenceConstants.DROP_BUSINESS_DATA_DB_ON_INSTALL,
                Messages.dropBusinessDataDBOnBDMChanges,
                BooleanFieldEditor.DEFAULT, parent);
        dropOnInstallFieldEditor.setPreferenceStore(EnginePlugin.getDefault().getPreferenceStore());
        editors.add(dropOnInstallFieldEditor);
        return editors;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution#init(org.eclipse.ui.IWorkbench)
     */
    @Override
    public void init(final IWorkbench workbench) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution#performOk()
     */
    @Override
    public boolean performOk() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution#appliesTo(java.lang.String)
     */
    @Override
    public boolean appliesTo(final String categoryName) {
        return "Database".equals(categoryName);
    }

}
