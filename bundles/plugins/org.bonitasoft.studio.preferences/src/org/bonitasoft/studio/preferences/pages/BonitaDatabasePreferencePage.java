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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution;
import org.bonitasoft.studio.preferences.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author Romain Bioteau
 * 
 */

public class BonitaDatabasePreferencePage extends AbstractBonitaPreferencePage implements IWorkbenchPreferencePage {

    private BooleanFieldEditor dropDB;

    private BooleanFieldEditor usersAndRoles;

    private Button checkbox;

    private List<IPreferenceFieldEditorContribution> contributions;

    private Map<FieldEditor, IPreferenceStore> contributedEditors;

    public BonitaDatabasePreferencePage() {
        super(GRID);
        setPreferenceStore(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore());
        contributions = new ArrayList<IPreferenceFieldEditorContribution>();
        contributedEditors = new HashMap<FieldEditor, IPreferenceStore>();
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common
     * GUI blocks needed to manipulate various types of preferences. Each field
     * editor knows how to save and restore itself.
     */
    public void createFieldEditors() {

        createTitleBar(Messages.BonitaPreferenceDialog_database, Pics.getImage(PicsConstants.preferenceDB), false);

        dropDB = new BooleanFieldEditor(BonitaPreferenceConstants.DELETE_TENANT_ON_EXIT, Messages.deleteTenantOnExit, getFieldEditorParent());
        addField(dropDB);
        usersAndRoles = new BooleanFieldEditor(BonitaPreferenceConstants.LOAD_ORGANIZATION, Messages.reloadDefaultOrganization, getFieldEditorParent()) {

            protected org.eclipse.swt.widgets.Button getChangeControl(org.eclipse.swt.widgets.Composite parent) {
                checkbox = super.getChangeControl(parent);
                return checkbox;
            }

        };
        addField(usersAndRoles);
        usersAndRoles.setEnabled(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.DELETE_TENANT_ON_EXIT),
                getFieldEditorParent());

        IConfigurationElement[] elems = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                "org.bonitasoft.studio.preferences.prefrenceFieldEditorContribution"); //$NON-NLS-1$
        IPreferenceFieldEditorContribution prefEditorContrib = null;
        for (IConfigurationElement elem : elems) {
            try {
                prefEditorContrib = (IPreferenceFieldEditorContribution) elem.createExecutableExtension("class"); //$NON-NLS-1$
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            if (prefEditorContrib.appliesTo("Database")) {
                contributions.add(prefEditorContrib);
                for (FieldEditor fe : prefEditorContrib.createFieldEditors(getFieldEditorParent())) {
                    addField(fe);
                    contributedEditors.put(fe, fe.getPreferenceStore());
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.preference.FieldEditorPreferencePage#propertyChange
     * (org.eclipse.jface.util.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getSource() == dropDB) {
            if (!(Boolean) event.getNewValue()) {
                BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.LOAD_ORGANIZATION, false);
                checkbox.setSelection(false);
            }
            usersAndRoles.setEnabled((Boolean) event.getNewValue(), getFieldEditorParent());
        }
        super.propertyChange(event);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        for (IPreferenceFieldEditorContribution contrib : contributions) {
            contrib.init(workbench);
        }
    }

    @Override
    public boolean performOk() {
        boolean ok = true;
        for (IPreferenceFieldEditorContribution contrib : contributions) {
            if (ok) {
                ok = ok && contrib.performOk();
            }
        }
        return super.performOk();
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

    @Override
    protected void initialize() {
        super.initialize();
        for (Entry<FieldEditor, IPreferenceStore> pe : contributedEditors.entrySet()) {
            if (pe.getValue() != null) {
                pe.getKey().setPreferenceStore(pe.getValue());
                pe.getKey().load();
            }
        }

    }
}
