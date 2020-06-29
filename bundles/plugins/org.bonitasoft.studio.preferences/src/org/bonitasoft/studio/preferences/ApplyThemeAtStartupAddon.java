/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.preferences;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.ui.PlatformUI;

public class ApplyThemeAtStartupAddon {

    /**
     * Synchronise active eclipse theme with the Bonita preference,
     * to ensure that specifics adjustments for Dark theme are applied.
     * The preference value can be outdated if the user update the theme from the eclipse preference panel.
     */
    @PostConstruct
    public void execute(IEventBroker eventBroker) {
        eventBroker.subscribe(UIEvents.UIElement.TOPIC_TOBERENDERED, e -> apply());
    }

    private void apply() {
        IThemeEngine engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
        String currentValue = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getString(BonitaPreferenceConstants.STUDIO_THEME_PREFERENCE);
        if (engine.getActiveTheme() == null) {
            BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                    .setValue(BonitaPreferenceConstants.STUDIO_THEME_PREFERENCE, BonitaPreferenceConstants.LIGHT_THEME);
        } else if (!Objects.equals(currentValue, engine.getActiveTheme().getId())) {
            BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                    .setValue(BonitaPreferenceConstants.STUDIO_THEME_PREFERENCE, engine.getActiveTheme().getId());
        }
    }

}
