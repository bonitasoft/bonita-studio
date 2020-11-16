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
package org.bonitasoft.studio.preferences.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaCoolBarPreferenceConstant;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.preferences.i18n.Messages;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * @author Romain Bioteau
 */

public class BonitaAppearancePreferencePage extends AbstractBonitaPreferencePage {

    private static final String APPEARANCE_CONTRIBUTOR_ID = "Appearance";
    private static final String NOTIFY_RESTART_COMMAND = "org.bonitasoft.studio.ui.notify.restart.command";

    private RadioGroupFieldEditor coolbarFiled;

    public BonitaAppearancePreferencePage() {
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

        createTitleBar(Messages.BonitaPreferenceDialog_appearance, Pics.getImage(PicsConstants.preferenceAppearance), false);

        final Composite coolbarRadioComposite = new Composite(getFieldEditorParent(), SWT.NONE);
        coolbarRadioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        coolbarFiled = new RadioGroupFieldEditor(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE,
                Messages.defaultCoolbarAppearance,
                2,
                new String[][] {
                        { Messages.normal, BonitaCoolBarPreferenceConstant.NORMAL },
                        { Messages.small, BonitaCoolBarPreferenceConstant.SMALL } },
                coolbarRadioComposite);

        coolbarRadioComposite.setLayout(new GridLayout(2, false));
        coolbarFiled.getRadioBoxControl(coolbarRadioComposite).setLayoutData(GridDataFactory.fillDefaults().create());

        addField(coolbarFiled);

        final Composite themeComposite = new Composite(getFieldEditorParent(), SWT.NONE);
        themeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        ComboFieldEditor themeField = new ComboFieldEditor(BonitaThemeConstants.STUDIO_THEME_PREFERENCE,
                Messages.theme,
                new String[][] {
                        { Messages.light, BonitaThemeConstants.LIGHT_THEME },
                        { Messages.dark, BonitaThemeConstants.DARK_THEME } },
                themeComposite);
        themeComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        addField(themeField);

        new Label(getFieldEditorParent(), SWT.NONE);
        new Label(getFieldEditorParent(), SWT.NONE);

        final Label separator = new Label(getFieldEditorParent(), SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        new Label(getFieldEditorParent(), SWT.NONE);
        new Label(getFieldEditorParent(), SWT.NONE);

        createPreferenceEditorContributions(APPEARANCE_CONTRIBUTOR_ID);
    }

    @Override
    public boolean performOk() {
        if (super.performOk()) {
            applyCoolbarPreference();
            applyThemePreference();
        }
        return false;
    }

    @SuppressWarnings("restriction")
    private void applyThemePreference() {
        IThemeEngine engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
        String value = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getString(BonitaThemeConstants.STUDIO_THEME_PREFERENCE);

        if (engine.getActiveTheme() == null || !Objects.equals(value, engine.getActiveTheme().getId())) {
            BonitaStudioLog.info(String.format("Applying theme %s", value), BonitaStudioPreferencesPlugin.PLUGIN_ID);
            engine.setTheme(value, true);
            Map<String, Object> parameters = new HashMap();
            parameters.put("title", Messages.themeChangedTitle);
            parameters.put("content", Messages.themeChanged);
            new CommandExecutor().executeCommand(NOTIFY_RESTART_COMMAND, parameters);
            PlatformUtil.openIntroIfNoOtherEditorOpen();
        }
    }

    private void applyCoolbarPreference() {
        final String value = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getString(BonitaCoolBarPreferenceConstant.COOLBAR_DEFAULT_SIZE);
        try {
            final ICommandService service = PlatformUI.getWorkbench().getService(ICommandService.class);
            if (value.equals(BonitaCoolBarPreferenceConstant.SMALL)) {
                service.getCommand("org.bonitasoft.studio.application.smallCoolbar")
                        .executeWithChecks(new ExecutionEvent());
            } else if (value.equals(BonitaCoolBarPreferenceConstant.NORMAL)) {
                service.getCommand("org.bonitasoft.studio.application.normalCoolbar")
                        .executeWithChecks(new ExecutionEvent());

            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

}
