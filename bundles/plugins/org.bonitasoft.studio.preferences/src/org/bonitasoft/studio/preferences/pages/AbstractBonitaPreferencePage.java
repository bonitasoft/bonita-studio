/**
 * Copyright (C) 2011 BonitaSoft S.A.
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public abstract class AbstractBonitaPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private static final String FIELD_EDITOR_CONTRIBUTION_ID = "org.bonitasoft.studio.preferences.prefrenceFieldEditorContribution";
    private final List<IPreferenceFieldEditorContribution> contributions = new ArrayList<IPreferenceFieldEditorContribution>();
    private final Map<FieldEditor, IPreferenceStore> contributedEditors = new HashMap<FieldEditor, IPreferenceStore>();

    public AbstractBonitaPreferencePage() {
        super();
    }

    public AbstractBonitaPreferencePage(final int style) {
        super(style);
    }

    public AbstractBonitaPreferencePage(final String title, final int style) {
        super(title, style);
    }

    public AbstractBonitaPreferencePage(final String title, final ImageDescriptor image,
            final int style) {
        super(title, image, style);
    }

    protected void createTitleBar(final String titleLabel, final Image image, final boolean useSeparator) {
        if (useSeparator) {
            new Label(getFieldEditorParent(), SWT.NONE);
            final Label separator = new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
            separator.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).grab(true, false).create());
        }
        final Composite composite = new Composite(getFieldEditorParent(), SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());

        final Label imageLabel = new Label(composite, SWT.NONE);
        imageLabel.setImage(image);

        final Label title = new Label(composite, SWT.NONE);
        title.setText(titleLabel);
        title.setFont(BonitaStudioFontRegistry.getPreferenceTitleFont());
    }

    protected void createPreferenceEditorContributions(final String contributorId) {
        final List<IPreferenceFieldEditorContribution> prefEditorContributions = getFieldEditorContibutions();
        for (final IPreferenceFieldEditorContribution prefEditorContrib : prefEditorContributions) {
            if (prefEditorContrib.appliesTo(contributorId)) {
                addContribution(prefEditorContrib);
                for (final FieldEditor fe : prefEditorContrib.createFieldEditors(getFieldEditorParent())) {
                    addField(fe);
                    contributedEditors.put(fe, fe.getPreferenceStore());
                }
            }
        }
    }

    protected void addContribution(final IPreferenceFieldEditorContribution prefEditorContrib) {
        contributions.add(prefEditorContrib);
    }

    protected List<IPreferenceFieldEditorContribution> getFieldEditorContibutions() {
        final List<IPreferenceFieldEditorContribution> result = new ArrayList<IPreferenceFieldEditorContribution>();
        final IConfigurationElement[] elems = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                FIELD_EDITOR_CONTRIBUTION_ID);
        for (final IConfigurationElement elem : elems) {
            try {
                final IPreferenceFieldEditorContribution prefEditorContrib = (IPreferenceFieldEditorContribution) elem.createExecutableExtension("class");
                result.add(prefEditorContrib);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, BonitaStudioPreferencesPlugin.PLUGIN_ID);
            }
        }
        return result;
    }

    protected List<IPreferenceFieldEditorContribution> getContributions() {
        return contributions;
    }

    protected Map<FieldEditor, IPreferenceStore> getContributedEditors() {
        return contributedEditors;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    @Override
    public void init(final IWorkbench workbench) {
        for (final IPreferenceFieldEditorContribution contrib : getContributions()) {
            contrib.init(workbench);
        }
    }

    @Override
    protected void initialize() {
        super.initialize();
        for (final Entry<FieldEditor, IPreferenceStore> pe : getContributedEditors().entrySet()) {
            if (pe.getValue() != null) {
                pe.getKey().setPreferenceStore(pe.getValue());
                pe.getKey().load();
            }
        }
    }

    @Override
    public boolean performOk() {
        for (final IPreferenceFieldEditorContribution contrib : getContributions()) {
            if (!contrib.performOk()) {
                return false;
            }
        }
        return super.performOk();
    }

}