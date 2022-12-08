/*
 * Copyright (C) 2009 BonitaSoft S.A.
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
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Button;

/**
 * @author Romain Bioteau
 */

public class BonitaDatabasePreferencePage extends AbstractBonitaPreferencePage {

    private static final String DATABASE_CONTRIBUTOR_ID = "Database";

    private BooleanFieldEditor dropDB;

    private BooleanFieldEditor usersAndRoles;

    private Button checkbox;

    public BonitaDatabasePreferencePage() {
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

        createTitleBar(Messages.BonitaPreferenceDialog_database, Pics.getImage(PicsConstants.preferenceDB), false);

        dropDB = new BooleanFieldEditor(BonitaPreferenceConstants.DELETE_TENANT_ON_EXIT, Messages.deleteTenantOnExit,
                getFieldEditorParent());
        addField(dropDB);
        usersAndRoles = new BooleanFieldEditor(BonitaPreferenceConstants.LOAD_ORGANIZATION,
                Messages.reloadDefaultOrganization, getFieldEditorParent()) {

            @Override
            protected org.eclipse.swt.widgets.Button getChangeControl(final org.eclipse.swt.widgets.Composite parent) {
                checkbox = super.getChangeControl(parent);
                return checkbox;
            }

        };
        addField(usersAndRoles);
        usersAndRoles.setEnabled(
                BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                        .getBoolean(BonitaPreferenceConstants.DELETE_TENANT_ON_EXIT),
                getFieldEditorParent());

        createPreferenceEditorContributions(DATABASE_CONTRIBUTOR_ID);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.preference.FieldEditorPreferencePage#propertyChange
     * (org.eclipse.jface.util.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        if (event.getSource() == dropDB) {
            if (!(Boolean) event.getNewValue()) {
                BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                        .setValue(BonitaPreferenceConstants.LOAD_ORGANIZATION, false);
                checkbox.setSelection(false);
            }
            usersAndRoles.setEnabled((Boolean) event.getNewValue(), getFieldEditorParent());
        }
        super.propertyChange(event);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performDefaults()
     */
    @Override
    protected void performDefaults() {
        super.performDefaults();
        usersAndRoles.setEnabled(dropDB.getBooleanValue(), getFieldEditorParent());
    }

}
