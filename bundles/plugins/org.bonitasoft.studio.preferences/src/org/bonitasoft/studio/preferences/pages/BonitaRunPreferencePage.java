/*
 * Copyright (C) 2009-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.preferences.pages;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.i18n.Messages;
import org.eclipse.gmf.runtime.common.ui.preferences.CheckBoxFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author Romain Bioteau This class represents a preference page that is
 *         contributed to the Preferences dialog. By subclassing
 *         <samp>FieldEditorPreferencePage</samp>, we can use the field support
 *         built into JFace that allows us to create a page that is small and
 *         knows how to save, restore and apply itself.
 *         <p>
 *         This page is used to modify preferences only. They are stored in the
 *         preference store that belongs to the main plug-in class. That way,
 *         preferences can be accessed directly via the preference store.
 */

public class BonitaRunPreferencePage extends AbstractBonitaPreferencePage  implements IWorkbenchPreferencePage{

    public BonitaRunPreferencePage() {
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
        createTitleBar(Messages.BonitaPreferenceDialog_RunMode, Pics.getImage(PicsConstants.preferenceDeploy),false) ;

        final CheckBoxFieldEditor validateBeforeRunEditor = new CheckBoxFieldEditor(BonitaPreferenceConstants.VALIDATION_BEFORE_RUN, Messages.validateBeforeRun, getFieldEditorParent());
        addField(validateBeforeRunEditor) ;
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    @Override
    public void init(IWorkbench workbench) {
    }

}