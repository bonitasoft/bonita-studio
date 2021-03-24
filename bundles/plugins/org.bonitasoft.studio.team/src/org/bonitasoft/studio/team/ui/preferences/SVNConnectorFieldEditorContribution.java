/*******************************************************************************
 * Copyright (C) 2009, 2014 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.gmf.runtime.common.ui.preferences.ComboFieldEditor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.team.svn.core.SVNTeamPlugin;
import org.eclipse.team.svn.core.extension.CoreExtensionsManager;
import org.eclipse.team.svn.core.extension.factory.ISVNConnectorFactory;
import org.eclipse.team.svn.core.extension.factory.SVNConnectorHelper;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.ui.SVNTeamUIPlugin;
import org.eclipse.team.svn.ui.preferences.SVNTeamPreferences;

/**
 * @author Romain Bioteau
 *
 */
public class SVNConnectorFieldEditorContribution implements IPreferenceFieldEditorContribution {


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.preferences.extension.IPrefrenceFieldEditorContribution#createFieldEditor(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public List<FieldEditor> createFieldEditors(final Composite parent) {
        createWarningMessage(parent);

        final List<FieldEditor> editors = new ArrayList<FieldEditor>() ;
        final ComboFieldEditor svnConnectorField = createComboFieldEditor(parent);
        editors.add(svnConnectorField);
        return editors;
    }

    protected ComboFieldEditor createComboFieldEditor(final Composite parent) {
        final List<ISVNConnectorFactory> factoriesList = retrieveSortedListOfFactories();
        final ComboFieldEditor svnConnectorField = new ComboFieldEditor("",
                Messages.svnConnector, parent,
                ComboFieldEditor.STRING_TYPE, false, 0, 400,
                true) {

            @Override
            protected void doLoadDefault() {
                final String defaultValue = getPreferenceStore().getDefaultString(SVNTeamPreferences.fullCoreName(SVNTeamPreferences.CORE_SVNCONNECTOR_NAME));
                convertPrefrenceToSelection(factoriesList, defaultValue);
            }

            protected void convertPrefrenceToSelection(final List<ISVNConnectorFactory> factoriesList, final String connectorId) {
                if (connectorId != null && !connectorId.isEmpty()) {
                    final ISVNConnectorFactory connectorFactory = CoreExtensionsManager.instance().getSVNConnectorFactory(connectorId);
                    final int selection = factoriesList.indexOf(connectorFactory);
                    combo.select(selection);
                    SVNTeamPreferences.setCoreString(getPreferenceStore(), SVNTeamPlugin.CORE_SVNCLIENT_NAME, connectorFactory.getId());
                }
            }

            @Override
            protected void doLoad() {
                convertPrefrenceToSelection(factoriesList, SVNTeamPreferences.getCoreString(getPreferenceStore(), SVNTeamPlugin.CORE_SVNCLIENT_NAME));
            }
        };
        svnConnectorField.setPreferenceStore(getPreferenceStore());
        for (final ISVNConnectorFactory factory : factoriesList) {
            svnConnectorField.addIndexedItemToCombo(SVNConnectorHelper.getConnectorName(factory), factoriesList.indexOf(factory));
        }
        svnConnectorField.getComboControl().addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final String svnConnector = factoriesList.get(svnConnectorField.getComboControl().getSelectionIndex()).getId();
                SVNTeamPreferences.setCoreString(getPreferenceStore(), SVNTeamPlugin.CORE_SVNCLIENT_NAME, svnConnector);
                // destroy all cached proxies
                SVNRemoteStorage.instance().dispose();
            }
        });
        return svnConnectorField;
    }

    protected IPreferenceStore getPreferenceStore() {
        return SVNTeamUIPlugin.instance().getPreferenceStore();
    }

    protected void createWarningMessage(final Composite parent) {
        final Label label = new Label(parent, SWT.WRAP);
        label.setText(Messages.warningSVNConnectorKitVersionChange);
        label.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
    }

    protected List<ISVNConnectorFactory> retrieveSortedListOfFactories() {
        final Collection<?> fullSet = CoreExtensionsManager.instance().getAccessibleClients();
        final ISVNConnectorFactory[] factories = fullSet.toArray(new ISVNConnectorFactory[fullSet.size()]);
        Arrays.sort(factories, new Comparator<ISVNConnectorFactory>() {
            @Override
            public int compare(final ISVNConnectorFactory o1, final ISVNConnectorFactory o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        final List<ISVNConnectorFactory> factoriesList = Arrays.asList(factories);
        return factoriesList;
    }

    @Override
    public boolean performOk() {
        SVNTeamUIPlugin.instance().savePreferences();
        // destroy all cached proxies
        SVNRemoteStorage.instance().dispose();
        return true;
    }

    @Override
    public boolean appliesTo(final String categoryName) {
        return categoryName.equals("Advanced");
    }

}
