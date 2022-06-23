/**
 * Copyright (C) 2009-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences.pages;

import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.LocaleUtil;
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

/**
 * @author Romain Bioteau
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

        createTitleBar(Messages.BonitaPreferenceDialog_language, Pics.getImage(PicsConstants.preferenceLanguage), false);

        studioLocale = new ComboFieldEditor(BonitaPreferenceConstants.CURRENT_STUDIO_LOCALE,
                Messages.bind(Messages.studioLocalLabel,
                        new Object[] { bonitaStudioModuleName }),
                toLocales(LocaleUtil.getStudioLocales()), getFieldEditorParent());
        webLocale = new ComboFieldEditor(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, Messages.consoleLocaleLabel,
                toLocales(LocaleUtil.getProtalLocales()),
                getFieldEditorParent());

        addField(studioLocale);
        addField(webLocale);

    }

    protected String[][] toLocales(Locale[] locales) {
        String[][] localeTable = new String[locales.length][];
        for (int i = 0; i < locales.length; i++) {
            Locale locale = locales[i];
            String displayLanguage = locale.getDisplayLanguage(locale);
            displayLanguage = displayLanguage.substring(0, 1).toUpperCase() + displayLanguage.substring(1);
            if (locale.getCountry() != null && locale.getCountry().length() > 0) {
                displayLanguage += " (" + locale.getDisplayCountry() + ")";
            }
            String[] local_s = { displayLanguage, locale.toString() };
            localeTable[i] = local_s;
        }
        return localeTable;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.preference.FieldEditorPreferencePage#propertyChange
     * (org.eclipse.jface.util.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getSource() == studioLocale) {
            newLocale = (String) event.getNewValue();
            String v = getPreferenceStore().getString(webLocale.getPreferenceName());
            String defaultValue = getPreferenceStore().getDefaultString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE);
            getPreferenceStore().setDefault(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, "");
            getPreferenceStore().setValue(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, v);
            getPreferenceStore().setDefault(BonitaPreferenceConstants.CURRENT_UXP_LOCALE, defaultValue);
        }
        super.propertyChange(event);
    }

    @Override
    protected void performDefaults() {
        super.performDefaults();
        newLocale = getPreferenceStore().getString(BonitaPreferenceConstants.CURRENT_STUDIO_LOCALE);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
     */
    @Override
    public boolean performOk() {
        boolean ok = super.performOk();
        try {
            ((ScopedPreferenceStore) getPreferenceStore()).save();
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
        if (newLocale != null && !newLocale.isEmpty()) {
            changeLocale(newLocale);
            if (MessageDialog.openQuestion(getShell(),
                    Messages.bind(Messages.restartQuestion_title, new Object[] { bonitaStudioModuleName }),
                    Messages.bind(Messages.restartQuestion_msg, new Object[] { bonitaStudioModuleName }))) {
                PlatformUI.getWorkbench().restart();
            }
        }
        return ok;
    }

    /*
     * (non-Javadoc)
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
        try {
            File configIniFile = new File(
                    new URL(configArea.getURL().toExternalForm() + File.separatorChar + "configuration" + File.separatorChar
                            + "config.ini").getFile());
            if (configIniFile.exists()) {
                Properties configIniProperties = new Properties();
                final FileInputStream inStream = new FileInputStream(configIniFile);
                configIniProperties.load(inStream);
                configIniProperties.setProperty("osgi.nl", locale);
                final FileOutputStream out = new FileOutputStream(configIniFile);
                configIniProperties.store(out, "");
                inStream.close();
                out.close();
            }
        } catch (MalformedURLException e1) {
            BonitaStudioLog.error(e1);
        } catch (FileNotFoundException e) {
            BonitaStudioLog.error(e);
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }

        File installFolder = new File(configArea.getURL().getFile());
        File clearStateFile = installFolder.toPath().resolve(".clearState").toFile();
        try {
            clearStateFile.delete();
            clearStateFile.createNewFile();
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
    }

}
