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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.exception.UpdateException;
import org.bonitasoft.engine.platform.PlatformLoginException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
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
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.server.core.util.SocketUtil;

public class ServerPreferencePage extends AbstractBonitaPreferencePage implements IWorkbenchPreferencePage {

    private static final String ACM_CONTRIBUTOR_ID = "Acm";
    private Integer newPort = new Integer(-1);
    private IntegerFieldEditor port;
    private IntegerFieldEditor xmxOption;
    private Integer xmx = new Integer(-1);
    private String newExtraParams;
    private StringFieldEditor extraParamsField;
    private StringFieldEditor uidExtraParamsField;
    private String newuidExtraParams;
    private BooleanFieldEditor debugCustomPage;

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

        createTitleBar(Messages.BonitaPreferenceDialog_UserXP_Settings, Pics.getImage(PicsConstants.preferenceLogin),
                false);
        
       var userAppTokenField = new StringFieldEditor(EnginePreferenceConstants.USER_APP_TOKEN,
                Messages.userAppToken,
                getFieldEditorParent());
       getContributedEditors().put(userAppTokenField, EnginePlugin.getDefault().getPreferenceStore());
       userAppTokenField.setEmptyStringAllowed(false);
       addField(userAppTokenField);    

        BooleanFieldEditor lazyEditor = new BooleanFieldEditor(EnginePreferenceConstants.LAZYLOAD_ENGINE,
                Messages.engineLazyLoad,
                BooleanFieldEditor.SEPARATE_LABEL,
                getFieldEditorParent());
        addField(lazyEditor);

        debugCustomPage = new BooleanFieldEditor(BonitaPreferenceConstants.CUSTOM_PAGE_DEBUG,
                Messages.debugCustomPageMode,
                BooleanFieldEditor.SEPARATE_LABEL,
                getFieldEditorParent());
        addField(debugCustomPage);
        Control descriptionControl = debugCustomPage.getDescriptionControl(getFieldEditorParent());
        ControlDecoration debugHintControlDecorator = new ControlDecoration(descriptionControl, SWT.RIGHT);
        debugHintControlDecorator.setDescriptionText(Messages.debugCustomPageModeHint);
        debugHintControlDecorator.setMarginWidth(3);
        debugHintControlDecorator.setImage(FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());

        createPreferenceEditorContributions(ACM_CONTRIBUTOR_ID);

        getContributedEditors().put(lazyEditor, EnginePlugin.getDefault().getPreferenceStore());
        port = new IntegerFieldEditor(BonitaPreferenceConstants.CONSOLE_PORT, Messages.consolePreferencePortLabel,
                getFieldEditorParent());
        port.setValidRange(PortConfigurator.MIN_PORT_NUMBER, PortConfigurator.MAX_PORT_NUMBER);
        addField(port);

        xmxOption = new IntegerFieldEditor(EnginePreferenceConstants.TOMCAT_XMX_OPTION, Messages.tomcatXmxOption,
                getFieldEditorParent());
        addField(xmxOption);
        getContributedEditors().put(xmxOption, EnginePlugin.getDefault().getPreferenceStore());

        extraParamsField = new StringFieldEditor(EnginePreferenceConstants.TOMCAT_EXTRA_PARAMS,
                Messages.tomcatExtraParams,
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
        boolean needRestartEngineServer = false;

        if (getContributions().stream().anyMatch(field -> field.shouldRestart())) {
            needRestartEngineServer = true;
        }

        try {
            PlatformUI.getWorkbench().getProgressService().run(true, false, new GetApiSessionOperation());
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.cannotStartTomcatTitle,
                    Messages.cannotStartTomcatMessage, e).open();
            return false;
        }

        Properties consoleProperties = readConsoleConfigProperties();
        Boolean currentCustomPageDebug = Boolean
                .valueOf(consoleProperties.getProperty(BonitaPreferenceConstants.CUSTOM_PAGE_DEBUG));
        if (!currentCustomPageDebug.equals(debugCustomPage.getBooleanValue())) {
            consoleProperties.setProperty(BonitaPreferenceConstants.CUSTOM_PAGE_DEBUG,
                    String.valueOf(debugCustomPage.getBooleanValue()));
            updateConsoleConfig(consoleProperties);
            needRestartEngineServer = true;
        }

        if (newPort != -1) {
            updatePortConfiguration(newPort);
            newPort = -1;
        } else if (xmx != -1) {
            needRestartEngineServer = true;
            xmx = -1;
        } else if (newExtraParams != null) {
            needRestartEngineServer = true;
            newExtraParams = null;
        }
        if (needRestartEngineServer) {
            restartServer();
        }

        if (newuidExtraParams != null) {
            if (MessageDialog.openConfirm(getShell(), Messages.restartServer, Messages.restartServerConfirmationMsg)) {
                new Job(Messages.restartingWebServer) {

                    @Override
                    protected IStatus run(IProgressMonitor monitor) {
                        UIDesignerServerManager.getInstance().stop();
                        UIDesignerServerManager.getInstance()
                                .start(RepositoryManager.getInstance().getCurrentRepository(), monitor);
                        return Status.OK_STATUS;
                    }
                }.schedule();
            }
            newuidExtraParams = null;
        }
        IPreferenceStore store = getPreferenceStore();
        if (store instanceof IPersistentPreferenceStore) {
            try {
                ((IPersistentPreferenceStore) store).save();
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        return ok;
    }

    private void updateConsoleConfig(Properties properties) {
        try {
            BOSEngineManager.getInstance().updateTenantConfigResourceContent(BOSEngineManager.CONSOLE_CONFIG_PROPERTIES,
                    toByteArray(properties));
        } catch (PlatformLoginException | BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException
                | UpdateException | PlatformLogoutException | SessionNotFoundException e) {
            BonitaStudioLog.error(e);
        }
    }

    private byte[] toByteArray(Properties properties) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            properties.store(byteArrayOutputStream, "");
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private Properties readConsoleConfigProperties() {
        Properties properties = new Properties();
        try {
            byte[] content = BOSEngineManager.getInstance().getTenantConfigResourceContent(
                    BOSEngineManager.CONSOLE_CONFIG_PROPERTIES, AbstractRepository.NULL_PROGRESS_MONITOR);
            try (InputStream is = new ByteArrayInputStream(content)) {
                properties.load(is);
            }
        } catch (PlatformLoginException | BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException
                | PlatformLogoutException | SessionNotFoundException | IOException e) {
            BonitaStudioLog.error(e);
        }
        return properties;
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
        if (event.getSource().equals(uidExtraParamsField)) {
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
