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
package org.bonitasoft.studio.engine.preferences;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.server.PortConfigurator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.AbstractBonitaPreferencePage;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.wst.server.core.util.SocketUtil;

public class ServerPreferencePage extends AbstractBonitaPreferencePage implements IWorkbenchPreferencePage {

    private Integer newPort = new Integer(-1);
    private IntegerFieldEditor port;
    private IntegerFieldEditor xmxOption;
    private Integer xmx = new Integer(-1);
    private String newExtraParams;
    private StringFieldEditor extraParamsField;
    private StringFieldEditor uidExtraParamsField;
    private String newuidExtraParams;

    public ServerPreferencePage() {
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

        createTitleBar(Messages.BonitaPreferenceDialog_UserXP_Settings, Pics.getImage(PicsConstants.preferenceLogin), false);

        BooleanFieldEditor lazyEditor = new BooleanFieldEditor(EnginePreferenceConstants.LAZYLOAD_ENGINE,
                Messages.engineLazyLoad,
                getFieldEditorParent());
        addField(lazyEditor);
        getContributedEditors().put(lazyEditor, EnginePlugin.getDefault().getPreferenceStore());
        port = new IntegerFieldEditor(BonitaPreferenceConstants.CONSOLE_PORT, Messages.consolePreferencePortLabel,
                getFieldEditorParent());
        port.setValidRange(PortConfigurator.MIN_PORT_NUMBER, PortConfigurator.MAX_PORT_NUMBER);
        addField(port);

        xmxOption = new IntegerFieldEditor(EnginePreferenceConstants.TOMCAT_XMX_OPTION, Messages.tomcatXmxOption,
                getFieldEditorParent());
        addField(xmxOption);
        getContributedEditors().put(xmxOption, EnginePlugin.getDefault().getPreferenceStore());

        extraParamsField = new StringFieldEditor(EnginePreferenceConstants.TOMCAT_EXTRA_PARAMS, Messages.tomcatExtraParams,
                getFieldEditorParent());
        addField(extraParamsField);
        getContributedEditors().put(extraParamsField, EnginePlugin.getDefault().getPreferenceStore());
        
        
        uidExtraParamsField = new StringFieldEditor(BonitaPreferenceConstants.UID_JVM_OPTS, Messages.uidExtraParams,
                getFieldEditorParent());
        addField(uidExtraParamsField);
    }

    @Override
    public boolean performOk() {
        if (!port.isValid()) {
            return false;
        }
        if (!xmxOption.isValid()) {
            return false;
        }

        if (newPort != -1) {
            final String informationMessage = NLS.bind(Messages.updatePortWarningMessage,
                    org.bonitasoft.studio.common.Messages.uiDesignerModuleName);
            if (!MessageDialog.openConfirm(getShell(), Messages.updatePortWarningTitle, informationMessage)) {
                return false;
            }
        }

        final boolean ok = super.performOk();
        if (newPort != -1) {
            updatePortConfiguration(newPort);
            newPort = -1;
        } else if (xmx != -1) {
            restartServer();
            xmx = -1;
        } else if (newExtraParams != null) {
            restartServer();
            newExtraParams = null;
        }
        if(newuidExtraParams != null) {
            if (MessageDialog.openConfirm(getShell(), Messages.restartServer, Messages.restartServerConfirmationMsg)) {
                new Job(Messages.restartingWebServer) {

                    @Override
                    protected IStatus run(IProgressMonitor monitor) {
                        UIDesignerServerManager.getInstance().stop();
                        UIDesignerServerManager.getInstance().start(RepositoryManager.getInstance().getCurrentRepository(), monitor);
                        return Status.OK_STATUS;
                    }
                }.schedule();
            }
            newuidExtraParams = null;
        }
        return ok;
    }

    private void updatePortConfiguration(final Integer newPort) {
        if (SocketUtil.isPortInUse(newPort)) {
            MessageDialog.openWarning(getShell(), Messages.portAlreadyUseTitle,
                    Messages.bind(Messages.portAlreadyUseMsg, newPort));
            return;
        }
        getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_PORT, newPort);
        restartServer();
    }

    private void restartServer() {
        if (MessageDialog.openConfirm(getShell(), Messages.restartServer, Messages.restartServerConfirmationMsg)) {
            new Job(Messages.restartingWebServer) {

                @Override
                protected IStatus run(IProgressMonitor monitor) {
                    BOSWebServerManager.getInstance().resetServer(monitor);
                    return Status.OK_STATUS;
                }
            }.schedule();
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        if (event.getSource().equals(port)) {
            if (port.isValid()) {
                newPort = port.getIntValue();
            }
        }
        if (event.getSource().equals(xmxOption)) {
            if (xmxOption.isValid()) {
                xmx = xmxOption.getIntValue();
            }
        }
        if (event.getSource().equals(extraParamsField)) {
            newExtraParams = extraParamsField.getStringValue();
        }
        if( event.getSource().equals(uidExtraParamsField)) {
            newuidExtraParams = uidExtraParamsField.getStringValue();
        }
        super.propertyChange(event);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    @Override
    public void init(final IWorkbench workbench) {
    }

}
