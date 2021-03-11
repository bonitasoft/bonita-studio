/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.ui.preferences;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.AbstractBonitaPreferencePage;
import org.eclipse.gmf.runtime.common.ui.preferences.CheckBoxFieldEditor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class DBConnectorsPreferencePage extends AbstractBonitaPreferencePage implements IWorkbenchPreferencePage {

    @Override
    protected Control createContents(Composite parent) {
        setPreferenceStore(BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore());
        final Composite titleComposite = new Composite(parent, SWT.NONE);
        titleComposite.setLayout(new GridLayout(2, false));
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        final Label imageLabel = new Label(titleComposite, SWT.NONE);
        imageLabel.setImage(Pics.getImage(PicsConstants.preferenceAdvanced));

        final Label title = new Label(titleComposite, SWT.NONE);
        title.setText(Messages.BonitaPreferenceDialog_DBConnectors);
        title.setFont(BonitaStudioFontRegistry.getPreferenceTitleFont());

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Composite booleanEditorComposite = new Composite(composite, SWT.NONE);
        booleanEditorComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true)
                .create());
        booleanEditorComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        BooleanFieldEditor editor = new CheckBoxFieldEditor(BonitaPreferenceConstants.ALWAYS_USE_SCRIPTING_MODE,
                Messages.alwaysUseScriptingModeOutputPref, booleanEditorComposite);
        addField(editor);

        return composite;
    }

    @Override
    protected void createFieldEditors() {

    }


}
