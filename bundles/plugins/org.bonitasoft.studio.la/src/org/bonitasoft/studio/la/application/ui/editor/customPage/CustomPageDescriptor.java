/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor.customPage;

import java.util.Objects;

public class CustomPageDescriptor {

    public static final String CUSTOMPAGE_PREFIX = "custompage_";
    public static final String DEFAULT_LAYOUT_ID = "custompage_layoutBonita";
    public static final String LEGACY_DEFAULT_LAYOUT_ID = "custompage_defaultlayout";
    public static final String BONITA_THEME_ID = "custompage_themeBonita";
    public static final CustomPageDescriptor DEFAULT_LAYOUT = new CustomPageDescriptor(DEFAULT_LAYOUT_ID,
            "Bonita layout", null);
    public static final CustomPageDescriptor LEGACY_DEFAULT_LAYOUT = new CustomPageDescriptor(LEGACY_DEFAULT_LAYOUT_ID,
            "Default living application layout (deprecated)", null);
    public static final CustomPageDescriptor DEFAULT_THEME = new CustomPageDescriptor("custompage_bootstrapdefaulttheme",
            "Bootstrap default theme",  null);


    private String id;
    private String displayName;
    private String description;

    public CustomPageDescriptor(String id, String displayName, String description) {
        this.id = Objects.requireNonNull(id);
        this.displayName = Objects.requireNonNull(displayName);
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        String displayName = getDisplayName();
        String pageId = getId();
        return displayName != null && !displayName.isEmpty() && !displayName.equals(pageId) ?
                String.format("%s (%s)",displayName,pageId)
                : pageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomPageDescriptor other = (CustomPageDescriptor) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
