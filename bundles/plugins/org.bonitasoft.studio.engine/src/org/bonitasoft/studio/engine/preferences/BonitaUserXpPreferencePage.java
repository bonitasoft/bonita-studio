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
package org.bonitasoft.studio.engine.preferences;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.console.common.server.preferences.constants.WebBonitaConstantsUtils;
import org.bonitasoft.studio.common.BonitaHomeUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.AbstractBonitaPreferencePage;
import org.bonitasoft.studio.preferences.pages.PasswordFieldEditor;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.bonitasoft.studio.repository.themes.UserXpFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import static org.bonitasoft.studio.common.Messages.bonitaPortalModuleName;

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

public class BonitaUserXpPreferencePage extends AbstractBonitaPreferencePage implements IWorkbenchPreferencePage {


    private Integer newPort = new Integer(-1);
    private final Integer oldPort = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getInt(BonitaPreferenceConstants.CONSOLE_PORT);
    private IntegerFieldEditor port;
    private ComboFieldEditor defaultTheme;
    private String newTheme;
    private StringFieldEditor host;
    private String newHost;
    private final static WebBonitaConstantsUtils WEB_CONSTANTS =  WebBonitaConstantsUtils.getInstance(1)  ;

    public BonitaUserXpPreferencePage() {
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


        createTitleBar(Messages.BonitaPreferenceDialog_UserXP_Settings, Pics.getImage(PicsConstants.preferenceLogin),false) ;


        host = new StringFieldEditor(BonitaPreferenceConstants.CONSOLE_HOST, Messages.consolePreferenceHostLabel, getFieldEditorParent()) ;
        addField(host);

        port = new IntegerFieldEditor(BonitaPreferenceConstants.CONSOLE_PORT, Messages.consolePreferencePortLabel, getFieldEditorParent()) ;
        addField(port) ;

        Group loginGroup = new Group(getFieldEditorParent(), SWT.NONE) ;
        loginGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create()) ;
        loginGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create()) ;
        loginGroup.setText(Messages.loginAs) ;

        StringFieldEditor user = new StringFieldEditor(BonitaPreferenceConstants.USER_NAME, Messages.userNameLLabel, loginGroup) ;
        addField(user);
        user.getTextControl(loginGroup).setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(0, 15).create()) ;
        user.getLabelControl(loginGroup).setLayoutData(GridDataFactory.fillDefaults().grab(false, false).indent(0, 15).create()) ;

        final PasswordFieldEditor password = new PasswordFieldEditor(BonitaPreferenceConstants.USER_PASSWORD, Messages.userPasswordLabel, loginGroup);
        addField(password);

        defaultTheme = new ComboFieldEditor(BonitaPreferenceConstants.DEFAULT_USERXP_THEME, Messages.bind(Messages.defaultUserXPThemeLabel, new Object[]{bonitaPortalModuleName}),getAvailableThemes(), getFieldEditorParent()) ;
        addField(defaultTheme);



    }

    private String[][] getAvailableThemes() {
        LookNFeelRepositoryStore store = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class) ;
        List<UserXpFileStore> artifacts = store.getUserXPLookNFeels();
        String[][]  result = new String[artifacts.size()][] ;
        for(int i = 0 ; i < artifacts.size() ; i++){
            String[] item = {  artifacts.get(i).getDisplayName(), artifacts.get(i).getName() };
            result[i] = item ;
        }

        return result;
    }


    public static void updateBonitaHome() {
        LookNFeelRepositoryStore store = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class) ;
        String themeId = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.DEFAULT_USERXP_THEME) ;
        List<UserXpFileStore> artifacts = store.getUserXPLookNFeels() ;
        UserXpFileStore artifact = null ;
        for(UserXpFileStore item : artifacts){
            if(item.getName().equals(themeId)){
                artifact = item ;
            }
        }
        if(artifact != null){
            File target = null;
            target = new File(BonitaHomeUtil.getBonitaHome(),WEB_CONSTANTS.getPortalThemeFolder().getAbsolutePath()+File.separatorChar+artifact.getName());
            target.delete() ;
            target.mkdirs() ;
            File zipFile = new File(target,artifact.getName()+".zip") ;
            zipFile.delete() ;
            artifact.export(zipFile.getAbsolutePath()) ;
            try{
            	PlatformUtil.unzipZipFiles(zipFile, target, new NullProgressMonitor()) ;
            }catch(Exception e){
            	BonitaStudioLog.error(e);
            }
            if(zipFile!=null){
            	zipFile.delete() ;
            }
        }
    }

    @Override
    public boolean performOk() {
        boolean ok =  super.performOk();
        if(newPort != -1 || newHost != null ){
            updatePortConfiguration(host.getStringValue(),port.getIntValue()) ;
        }
        if(newTheme != null){
            IProgressService progress = PlatformUI.getWorkbench().getProgressService() ;
            try {
                progress.run(true, false,new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
                        monitor.beginTask(Messages.applyingLooknFeel, IProgressMonitor.UNKNOWN) ;
                        updateBonitaHome() ;
                    }
                });
            } catch (InvocationTargetException e) {
                BonitaStudioLog.error(e) ;
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e) ;
            }
        }
        return ok;
    }

    private void updatePortConfiguration(String host,Integer newPort) {
        if(host.equals("localhost") || host.equals("127.0.0.1")){
            if(!BOSWebServerManager.isPortAvailable(newPort)){
                MessageDialog.openWarning(getShell(), Messages.portAlreadyUseTitle, Messages.bind(Messages.portAlreadyUseMsg,newPort));
                return;
            }

            try {
                BOSWebServerManager.getInstance().updatePortConfiguration(oldPort,newPort);
                BonitaHomeUtil.configureBonitaClient(BonitaHomeUtil.HTTP,host, newPort);
                IProgressService service = PlatformUI.getWorkbench().getProgressService();
                service.run(true, false, new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        monitor.beginTask(Messages.updatingServerPort, IProgressMonitor.UNKNOWN);
                        BOSWebServerManager.getInstance().resetServer(monitor);
                    }
                });

            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            } catch (InvocationTargetException e) {
                BonitaStudioLog.error(e);
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getSource().equals(port)) {
            newPort = Integer.valueOf((String) event.getNewValue());
        }else if(event.getSource().equals(defaultTheme)){
            newTheme = (String) event.getNewValue() ;
        }else if(event.getSource().equals(host)){
            newHost = (String) event.getNewValue() ;
        }

        super.propertyChange(event);
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

    public static String getInstalledThemeId() {
      //String id = PropertiesFactory.getPlatformProperties().getProperty("currentTheme") ;
        return "default" ;
    }





}