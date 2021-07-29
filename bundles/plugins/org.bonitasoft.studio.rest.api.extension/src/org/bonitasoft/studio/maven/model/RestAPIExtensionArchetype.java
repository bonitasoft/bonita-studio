/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.model;

import java.util.Arrays;

import org.apache.maven.archetype.catalog.Archetype;

public class RestAPIExtensionArchetype {

    public static final String ARTIFACT_ID = "bonita-rest-api-extension-archetype";
    public static final String GROUP_ID = "org.bonitasoft.archetypes";
    public static final String VERSION = "1.4.2";
    public static final Archetype INSTANCE = new Archetype();

    static {
        INSTANCE.setGroupId(GROUP_ID);
        INSTANCE.setArtifactId(ARTIFACT_ID);
        INSTANCE.setVersion(VERSION);
        INSTANCE.setGoals(Arrays.asList("archetype:generate"));
    }

    private RestAPIExtensionArchetype() {

    }

}
