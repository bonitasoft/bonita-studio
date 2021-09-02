/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.core;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;

public class EntryPage implements Comparable<EntryPage> {

    private String pageId;
    private String description;
    private boolean custom;
    private String displayName;
    private boolean isHidden;

    public EntryPage(String pageId, String displayName, String description, boolean isHidden, boolean custom) {
        this.pageId = pageId;
        this.displayName = displayName;
        this.description = description;
        this.isHidden = isHidden;
        this.custom = custom;
    }

    public String getPageId() {
        return pageId;
    }

    public String getDescription() {
        String locale = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getString(BonitaPreferenceConstants.CURRENT_STUDIO_LOCALE);
        return PageLocalizationService.getInstance().translate(description, locale);
    }

    public boolean isCustom() {
        return custom;
    }

    public boolean isHidden() {
        return isHidden;
    }

    @Override
    public int compareTo(EntryPage entry) {
        return (pageId.toLowerCase()).compareTo(entry.getPageId().toLowerCase());
    }

    public String getDisplayName() {
        String locale = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .getString(BonitaPreferenceConstants.CURRENT_STUDIO_LOCALE);
        return PageLocalizationService.getInstance().translate(displayName, locale);
    }

    @Override
    public String toString() {
        String displayName = getDisplayName();
        String pageId = getPageId();
        return displayName != null && !displayName.isEmpty() && !displayName.equals(pageId)
                ? String.format("%s (%s)", displayName, pageId)
                : pageId;
    }

}
