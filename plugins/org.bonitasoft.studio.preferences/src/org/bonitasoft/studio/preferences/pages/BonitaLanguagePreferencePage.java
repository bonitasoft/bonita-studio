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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.i18n.Messages;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
/**
 * @author Romain Bioteau
 *
 */

public class BonitaLanguagePreferencePage extends AbstractBonitaPreferencePage implements IWorkbenchPreferencePage {

    private String newLocale;
    private ComboFieldEditor studioLocale;
    private ComboFieldEditor webLocale;

    public BonitaLanguagePreferencePage() {
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

        createTitleBar(Messages.BonitaPreferenceDialog_language, Pics.getImage(PicsConstants.preferenceLanguage),false) ;

        studioLocale = new ComboFieldEditor(BonitaPreferenceConstants.CURRENT_STUDIO_LOCALE, Messages.bind(Messages.studioLocalLabel, new Object[]{bonitaStudioModuleName}), BonitaPreferenceConstants.getAvailableLocales(BonitaPreferenceConstants.AVAILABLE_LOCALES), getFieldEditorParent()) ;
        webLocale = new ComboFieldEditor(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, Messages.consoleLocaleLabel, BonitaPreferenceConstants.getAvailableLocales(BonitaPreferenceConstants.AVAILABLE_LOCALES_USER_XP), getFieldEditorParent());

        addField(studioLocale);
        addField(webLocale) ;


    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.preference.FieldEditorPreferencePage#propertyChange
     * (org.eclipse.jface.util.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getSource() == studioLocale) {
            newLocale = (String) event.getNewValue();
            String v = getPreferenceStore().getString(webLocale.getPreferenceName()) ;
            String defaultValue = getPreferenceStore().getDefaultString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE) ;
            getPreferenceStore().setDefault(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, "") ;
            getPreferenceStore().setValue(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, v) ;
            getPreferenceStore().setDefault(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, defaultValue) ;
        }
        super.propertyChange(event);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        boolean ok =  super.performOk() ;
        try {
            ((ScopedPreferenceStore)getPreferenceStore()).save() ;
        } catch (IOException e) {
            BonitaStudioLog.error(e) ;
        }
        if (newLocale != null && newLocale.length() != 0) {
            changeLocale(newLocale);
            if (MessageDialog.openQuestion(getShell(), Messages.restartQuestion_title, Messages.bind(Messages.restartQuestion_msg, new Object[]{bonitaStudioModuleName}))){
                PlatformUI.getWorkbench().close();
            }
        }
        return ok ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
    }

    private static void changeLocale(String locale) {
        Location configArea = Platform.getInstallLocation();
        if (configArea == null) {
            return;
        }
        ArrayList<URL> locations = new ArrayList<URL>();

        try {
            locations.add(new URL(configArea.getURL().toExternalForm() + "BonitaStudio" + ".ini"));
            locations.add(new URL(configArea.getURL().toExternalForm() + "BonitaStudio-x86_64" + ".ini"));
            locations.add(new URL(configArea.getURL().toExternalForm() + "BonitaStudio-linux" + ".ini"));
            locations.add(new URL(configArea.getURL().toExternalForm() + "BonitaStudio-linux64" + ".ini"));
            locations.add(new URL(configArea.getURL().toExternalForm() + "BonitaStudio-mac.app/Contents/MacOS/BonitaStudio" + ".ini"));

        } catch (MalformedURLException e) {
            // This should never happen
        }

        for (URL location : locations) {

            try {
                String fileName = location.getFile();
                File file = new File(fileName);
                fileName += ".bak";
                file.renameTo(new File(fileName));
                BufferedReader in = new BufferedReader(new FileReader(fileName));
                BufferedWriter out = new BufferedWriter(new FileWriter(location.getFile()));
                try {
                    boolean isNl = false;
                    boolean isNlWiritten = false;
                    String line = in.readLine();
                    while (line != null) {
                        if (!isNl) {
                            out.write(line);
                        } else {
                            out.write(locale);
                            isNl = false;
                            isNlWiritten = true;
                        }
                        out.newLine();
                        if (line.equals("-nl")) {
                            isNl = true;
                        }
                        line = in.readLine();
                    }
                    out.flush();
                    out.close();
                    in.close();
                    if (!isNlWiritten) {
                        // add -nl at the start of the file
                        out = new BufferedWriter(new FileWriter(location.getFile()));
                        in = new BufferedReader(new FileReader(fileName));
                        out.write("-nl");
                        out.newLine();
                        out.write(locale);
                        out.newLine();
                        line = in.readLine();
                        while (line != null) {
                            out.write(line);
                            out.newLine();
                            line = in.readLine();
                        }
                        out.flush();
                    }
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}