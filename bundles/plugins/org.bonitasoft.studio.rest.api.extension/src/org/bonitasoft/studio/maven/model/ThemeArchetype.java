/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.model;

import org.eclipse.m2e.core.project.IArchetype;

public class ThemeArchetype {

    public static final String ARTIFACT_ID = "bonita-theme-archetype";
    public static final String GROUP_ID = "org.bonitasoft.archetypes";
    public static final String VERSION = "1.0.3";
    public static final IArchetype INSTANCE = new Archetype(GROUP_ID, ARTIFACT_ID, VERSION);

    private ThemeArchetype() {

    }

}
